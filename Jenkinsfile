#!/usr/bin/env groovy
pipeline {
    agent any
    environment {
        GIT_CREDENTIALS_ID = 'dscadmin'
        ARTIFACTORY_SERVER = '1508412728@1439723571527'
        //SUFFIX = '/artifactory/aem-accelerator'
        CRX_DEPLOY_CREDENTIALS_ID = 'CRX_DEPLOY_CREDENTIALS_ID'
        CRX_DEPLOY_AUTHOR_URL = 'http://10.127.126.134:4502'
        //CRX_DEPLOY_PUBLISH_URL = 'http://host.docker.internal:4503'
    }
    stages {
        stage('Build') {
            agent any
            steps {
                script {
                    SCM_VARS = checkout(scm)
                    def rtMaven = Artifactory.newMavenBuild()
                    def server = Artifactory.server(ARTIFACTORY_SERVER)
                    rtMaven.resolver server: server, releaseRepo: 'aem-accelerator', snapshotRepo: 'aem-accelerator'
                    //rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
                    def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'clean deploy'
                    server.publishBuildInfo buildInfo
                }
                milestone(1)
            }
            post {
                success { junit '**/target/surefire-reports/TEST-*.xml' }
                cleanup { deleteDir() }
            }
        }
        stage('Deploy?') {
            steps {
                script {
                    def userInput = input message: 'Deploy Content Packages',
                                          ok: 'Deploy',
                                          parameters: [
                                              choice(name: 'Content Packages',
                                                     choices: 'ui.apps\nall',
                                                     description: 'Deploy the main application content package, or all?')
                                          ]
                    CRX_DEPLOY_PKG_ID_FILTERS = 'aem-accelerator.ui.apps-*.zip'
                    if (userInput == 'all') {
                        CRX_DEPLOY_PKG_ID_FILTERS = 'aem-accelerator.dependencies-*.zip\naem-accelerator.ui.apps-*.zip\naem-accelerator.ui.content-*.zip'
                    }
                }
                milestone(2)
            }
        }
        stage('Deploy') {
            agent any
            steps {
                script {
                    def downloadSpec = """{
                        "files": [
                            {
                                "aql": { "items.find": {
                                    "name":{"\$match":"*.zip"},
                                    "repo": "libs-snapshot-local",
                                    "artifact.module.build.name": { "\$eq": "${JOB_NAME}" },
                                    "artifact.module.build.number": { "\$eq": "${BUILD_NUMBER}" }
                                } },
                                "flat": true
                            }
                        ]
                    }"""
                    def server = Artifactory.server(ARTIFACTORY_SERVER)
                    server.download(downloadSpec)
                }
                lock(resource: 'aem-deploy', inversePrecedence: true) {
                    crxDeploy baseUrls: CRX_DEPLOY_AUTHOR_URL,
                          credentialsId: CRX_DEPLOY_CREDENTIALS_ID,
                          behavior: 'Overwrite',
                          packageIdFilters: CRX_DEPLOY_PKG_ID_FILTERS,
                          recursive: true,
                          disableForJobTesting: true
//                    crxDeploy baseUrls: CRX_DEPLOY_PUBLISH_URL,
//                          credentialsId: CRX_DEPLOY_CREDENTIALS_ID,
//                          behavior: 'Overwrite',
//                          packageIdFilters: CRX_DEPLOY_PKG_ID_FILTERS,
//                          recursive: true,
//                          disableForJobTesting: true
                    milestone(3)
                }
            }
            post { cleanup { deleteDir() } }
        }
        stage('Release?') {
            steps {
                script {
                    RELEASE_VERSION = input message: 'Release Version',
                                            ok: 'Ok',
                                            parameters: [
                                                string(name: 'Release Version',
                                                       description: 'Release version? (i.e. 1.2.3)')
                                            ]
                }
                milestone(4)
            }
        }
        stage('Release') {
            agent any
            steps {
                checkout([ $class: 'GitSCM',
                           branches: [[ name: SCM_VARS.GIT_COMMIT ]],
                           userRemoteConfigs: [[ credentialsId: GIT_CREDENTIALS_ID, url: SCM_VARS.GIT_URL]]
                         ])
                // create release branch
                sh script: """
                git checkout -b release/v${RELEASE_VERSION} ${SCM_VARS.GIT_COMMIT}
                """
                script {
                    // set release verison
                    def descriptor = Artifactory.mavenDescriptor()
                    descriptor.failOnSnapshot = true
                    descriptor.version = RELEASE_VERSION
                    descriptor.transform()
                }
                script {
                    // build release and deploy to artifactory
                    def buildInfo = Artifactory.newBuildInfo()
                    buildInfo.env.capture = true
                    def rtMaven = Artifactory.newMavenBuild()
                    rtMaven.opts = '-Dmaven.test.skip=true'
                    def server = Artifactory.server(ARTIFACTORY_SERVER)
                    rtMaven.resolver server: server, releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot'
                    rtMaven.deployer server: server, releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local'
                    rtMaven.run pom: 'pom.xml', goals: 'clean install', buildInfo: buildInfo
                    server.publishBuildInfo buildInfo
                }
                // commit release branch & tag, then push
                sshagent (credentials: [GIT_CREDENTIALS_ID]) {
                    sh script: """
                    git commit -a -m \"[artifactory-release] [ci-skip] Release version ${RELEASE_VERSION}\"
                    git push -u origin release/v${RELEASE_VERSION}
                    git tag v${RELEASE_VERSION}
                    git push origin v${RELEASE_VERSION}
                    """
                }
            }
            post { cleanup { deleteDir() } }
        }
    }
}
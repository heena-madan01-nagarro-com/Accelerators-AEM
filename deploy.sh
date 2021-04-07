#!/bin/bash
# Doc
HELP_TEXT="
    A shell script to install packages to AEM

    Params:
      -f, --file     The zip file to instal (cannot be used with -m, --module param)
      -m, --module   The folder name of maven module that produces a zip package (cannot be used with -f, --file param)
      -h, --host     The instance host
      -p, --port     The instance port
      -u, --user     The user name to use for auth

    Password should be set via env variable:
      CRX_PASSWORD   Set from env variable, default to 'admin'

    Other variables you can set via environment variables:
      CRX_USER       Set via user argument above, defaults to 'admin'
      CRX_PORT       Set via port argument above, defaults to '4502'
      CRX_HOST       Set via host argument above, defaults to 'localhost'

"
# if the first arg is "--help", "help" or "-h" print help message and exit
if [ $1 = "--help" ] || [ $1 = "help" ] || [ $1 = "-h" ]; then
    printf "$HELP_TEXT"
    exit 1
fi
# parse params as per: https://stackoverflow.com/questions/192249/how-do-i-parse-command-line-arguments-in-bash
while [[ $# -gt 0 ]]
do
key="$1"
case $key in
    # parse file argument
    -f|--file)
        FILE="$2"
        shift # past argument
        shift # past value
    ;;
    # parse file argument
    -m|--module)
        MVN_MODULE="$2"
        shift # past argument
        shift # past value
    ;;
    # parse host argument
    -h|--host)
        CRX_HOST="$2"
        shift # past argument
        shift # past value
    ;;
    # parse port argument
    -p|--port)
        CRX_PORT="$2"
        shift # past argument
        shift # past value
    ;;
    # parse user argument
    -u|--user)
        CRX_USER="$2"
        shift # past argument
        shift # past value
    ;;
    # unknown option
    *)
        echo "WARNING: unknown option: $1"
        shift # past argument
    ;;
esac
done
# use defaults if param was not passed
CRX_USER="${CRX_USER:-admin}"
CRX_PASSWORD="${CRX_PASSWORD:-admin}" # password should be passed as an env variable
CRX_HOST="${CRX_HOST:-10.127.126.134}"
CRX_PORT="${CRX_PORT:-4502}"
# If an module folder name is passed, use that to find the zip package to install
if [ ! -z "$MVN_MODULE" ]
then
    if [ -d "$MVN_MODULE" ]
    then
      ZIP_PATTERN="$MVN_MODULE/target/*.zip"
      ZIP_PACKAGES=( $ZIP_PATTERN )
      FILE="${ZIP_PACKAGES[0]}"
    else
      echo "ERROR: The module folder: $MVN_MODULE does not exist}"
      exit 1
    fi
else
    echo "INFO: no module param was passed. Using file param if exists"
fi
# sets INSTALL_COMMAND, first arg is the password
setInstallCommand(){
    # --fai           : https://curl.haxx.se/docs/manpage.html#-f fails on server error
    # -u              : user:password for basic auth
    # -F force=true   : force install
    # -F install=true : install after upload
    # -F strict=true  : fails if package fails to deploy
    eval "INSTALL_COMMAND='curl --fail -u $CRX_USER:$1 -F file=@$FILE -F name=$FILE  -F force=true -F install=true -F strict=true http://$CRX_HOST:$CRX_PORT/crx/packmgr/service.jsp'"
}
# print command that will be executed, sub password with "*****"
setInstallCommand "*****"
echo "Executing command: $INSTALL_COMMAND"
# Execute command
setInstallCommand "$CRX_PASSWORD"
eval "$INSTALL_COMMAND"
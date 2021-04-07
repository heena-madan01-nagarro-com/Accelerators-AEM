package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.log4j.Logger;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.settings.SlingSettingsService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables= { Resource.class},
        resourceType = "aem-accelerator/components/content/footer",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class FooterModel {

    private static Logger log = Logger.getLogger(FooterModel.class);
    @Self
    private SlingHttpServletRequest request;
    @Self
    private Resource resource;
    @OSGiService
    private SlingSettingsService settings;
    @Inject
    @Optional
    private String heading;
    @Inject
    @Optional
    private String altText;
    @Inject
    @Optional
    private String imagePath;
    private List<Section> sectionsList = new ArrayList<>();

    public String getHeading() {
        return heading;
    }

    public String getAltText() {
        return altText;
    }

    public String getImagePath() {
        return imagePath;
    }

    @PostConstruct
    protected void init() {

    }


    public List<Section> getPrivacySections() {

        Resource childResource = resource.getChild("privacySection");
        sectionsList.clear();
        log.info("Resource--"+ childResource.getPath());
        if (childResource != null) {
            Iterator<Resource> linkResources = childResource.listChildren();
            log.info(linkResources.toString());
            linkResources.forEachRemaining(res -> populateModel(res));
        }
        return sectionsList;


    }

    public void populateModel(Resource resource) {
        log.info("in resource"+ resource.getPath());
        Section adaptedSection = resource.adaptTo(Section.class);
        if (adaptedSection != null) {
            sectionsList.add(adaptedSection);
        }
    }

}

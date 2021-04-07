package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.log4j.Logger;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.settings.SlingSettingsService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables= { Resource.class},
        resourceType = "aem-accelerator/components/content/header",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class HeaderModel {

    @OSGiService
    private SlingSettingsService settings;

    @Self
    private SlingHttpServletRequest request;

    @Self
    private Resource resource;


    private List<Section> sectionsList = new ArrayList<>();

    public List<Section> getSectionsList() {
        return sectionsList;
    }

    public void setSectionsList(List<Section> sectionsList) {
        this.sectionsList = sectionsList;
    }

    private static Logger log = Logger.getLogger(HeaderModel.class);

    @PostConstruct
    protected void init() {
        log.info("In init of"+ this.getClass().getName());
    }


    public List<Section> getSections() {
        Resource childResource = resource.getChild("sections");
        sectionsList.clear();
        if (childResource != null) {
            Iterator<Resource> linkResources = childResource.listChildren();
            log.info(linkResources.toString());
            linkResources.forEachRemaining(res -> populateModel(res));
        }
        return sectionsList;
    }

    public void populateModel(Resource resource) {
        Section adaptedSection = resource.adaptTo(Section.class);
        if (adaptedSection != null) {
            sectionsList.add(adaptedSection);
        }
    }
}

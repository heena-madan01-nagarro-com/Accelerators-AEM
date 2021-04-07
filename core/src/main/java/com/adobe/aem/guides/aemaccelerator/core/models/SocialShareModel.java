package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.log4j.Logger;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.settings.SlingSettingsService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables= { Resource.class},
        resourceType = "aem-accelerator/components/content/socialsharing",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class SocialShareModel {

    @OSGiService
    private SlingSettingsService settings;

    @Inject
    @Optional
    private List<Resource> sociallinks;

    @Inject
    @Optional
    private String socialShareHeading;

    public String getSocialShareHeading() {
        return socialShareHeading;
    }

    @Optional
    private List<SocialShareDetails> sectionsList = new ArrayList<>();

    public List<SocialShareDetails> getSocialShareList() {
        return sectionsList;
    }

    public void setSectionsList(List<SocialShareDetails> sectionsList) {
        this.sectionsList = sectionsList;
    }

    private static Logger log = Logger.getLogger(SocialShareModel.class);

    @PostConstruct
    protected void init() {
        log.info("In init of"+ this.getClass().getName());
        if (!sociallinks.isEmpty()) {
            for (Resource resource : sociallinks) {
                SocialShareDetails sections = resource.adaptTo(SocialShareDetails.class);
                sectionsList.add(sections);
            }
        }
    }
}

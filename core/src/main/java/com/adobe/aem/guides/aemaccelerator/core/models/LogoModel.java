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
        resourceType = "aem-accelerator/components/content/logo",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class LogoModel {

    @OSGiService
    private SlingSettingsService settings;

    @Inject
    @Optional
    private String title;

    @Inject
    @Optional
    private String imagePath;

    @Inject
    @Optional
    private String altText;

    @Inject
    @Optional
    private String targetUrl;

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAltText() {
        return altText;
    }

    public String getTargetUrl() {
        return targetUrl;
    }
}
///libs/wcm/foundation/components/page/tab_basic/items



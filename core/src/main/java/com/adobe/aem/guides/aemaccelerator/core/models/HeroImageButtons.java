package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroImageButtons {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String backgroundColor;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }
}

package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class Section {

    @Inject
    @Optional
    private String sectionTitle;

    @Inject
    @Optional
    private String altText;

    @Inject
    @Optional
    private String linkPath;

    @Inject
    @Optional
    private String sectionImageRef;

    public String getSectionTitle() {
        return sectionTitle;
    }

    public String getAltText() {
        return altText;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public String getSectionImageRef() {
        return sectionImageRef;
    }

    @PostConstruct
    protected void init() {

    }
}

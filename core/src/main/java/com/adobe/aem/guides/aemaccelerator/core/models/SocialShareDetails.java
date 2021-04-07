package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class SocialShareDetails {

    @Inject
    @Optional
    private String linkText;

    @Inject
    @Optional
    private String linkTitle;

    @Inject
    @Optional
    private String linkStyle;

    @Inject
    @Optional
    private String shareUrl;

    @Inject
    @Optional
    private String linkTarget;

    @Inject
    @Optional
    private String socialImageRef;

    @Inject
    @Optional
    private String altText;


    public String getLinkText() {
        return linkText;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public String getLinkStyle() {
        return linkStyle;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public String getLinkTarget() {
        return linkTarget;
    }

    public String getSocialImageRef() {
        return socialImageRef;
    }

    public String getAltText() {
        return altText;
    }

    @PostConstruct
    protected void init() {

    }
}

package com.adobe.aem.guides.aemaccelerator.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson", extensions = "json", options = { @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })
public class AnalyticsConfigurationModel {

    @Inject
    Boolean enable;

    @Inject
    String event;

    @Inject
    String eventCategory;
    @Inject
    String eventAction;
    @Inject
    String eventLabel;
    @Inject
    @Named("sling:resourceType")
    String slingResourceType;

    public String getEvent() {
        return event;
    }

    public Boolean isEnable() {
        return enable;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public String getEventAction() {
        return eventAction;
    }

    public String getEventLabel() {
        return eventLabel;
    }

    public String getSlingResourceType() {
        return slingResourceType;
    }
}

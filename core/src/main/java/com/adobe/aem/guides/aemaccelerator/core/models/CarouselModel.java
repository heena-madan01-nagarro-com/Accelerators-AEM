package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = {Resource.class},
        resourceType = "release/components/content/carousel",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class CarouselModel {

    @Inject
    boolean autoplay;

    @Inject
    String delay;

    @Inject
    boolean autopauseDisabled;

    @Inject
    List<CarouselSlides> carouselComponents;

    @Inject
    boolean analytics;

    public boolean isAutoplay() {
        return autoplay;
    }

    public String getDelay() {
        return delay;
    }

    public boolean isAutopauseDisabled() {
        return autopauseDisabled;
    }

    public List<CarouselSlides> getCarouselComponents() {
        return carouselComponents;
    }

    public boolean isAnalytics() {
        return analytics;
    }
}

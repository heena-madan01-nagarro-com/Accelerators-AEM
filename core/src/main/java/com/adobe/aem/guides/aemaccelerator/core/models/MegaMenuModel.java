package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = {Resource.class},
        resourceType = "aem-accelerator/components/content/megamenu",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public interface MegaMenuModel {

	@Inject
	String getFileReference();

    @Inject
    List<MegamenuItems> getMegamenuItems();

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    interface MegamenuItems {
        @Inject
        String getTitle();

        @Inject
        List<MegamenuSubitems> getMegamenuSubitems();
    }

    @Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
    interface MegamenuSubitems {
        @Inject
        String getTitle();

        @Inject
        String getLink();
    }

    @Inject
    String getImageText();

    @Inject
    boolean getAnalytics();
}

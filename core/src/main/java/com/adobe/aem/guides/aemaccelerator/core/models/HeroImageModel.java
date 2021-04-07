package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.log4j.Logger;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = {Resource.class},
        resourceType = "aem-accelerator/components/content/heroimage",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class HeroImageModel {

    private final static Logger log = Logger.getLogger(HeroImageModel.class);

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private boolean addButton;

    @ValueMapValue
    private boolean addSearch;

    @ValueMapValue
    private String fileReference;

    @ValueMapValue
    private String searchPosition;

    public String getSearchPosition() {
        return searchPosition;
    }

    public boolean isAddSearch() {
        return addSearch;
    }

    public String getHeading() {
        return heading;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAddButton() {
        return addButton;
    }

    @Inject
    private List<HeroImageButtons> buttonslist;

    public List<HeroImageButtons> getButtonslist() {
        return buttonslist;
    }

    public String getFileReference() {
        return fileReference;
    }

}

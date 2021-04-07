package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.commons.io.FilenameUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CarouselSlides {
    @ValueMapValue
    String heading;
    @ValueMapValue
    String title;
    @ValueMapValue
    boolean addSearch;
    @ValueMapValue
    String searchPosition;
    @ValueMapValue
    String carouselImageRef;
    @ValueMapValue
    boolean addButton;
    @ValueMapValue
    String buttontitle;
    @ValueMapValue
    String buttonlink;
    @ValueMapValue
    String backgroundColor;

    public String getHeading() {
        return heading;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAddSearch() {
        return addSearch;
    }

    public String getSearchPosition() {
        return searchPosition;
    }

    public String getCarouselImageRef() {
        return carouselImageRef;
    }

    public boolean isAddButton() {
        return addButton;
    }

    public String getButtontitle() {
        return buttontitle;
    }

    public String getButtonlink() {
        return buttonlink;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getExtension() {
        return FilenameUtils.getExtension(getCarouselImageRef());
    }

    public String getFileType() {
        String ext = getExtension();
        if (ext.equals("mp4") ||
                ext.equals("obb") ||
                ext.equals("webm") ||
                ext.equals("mkv") ||
                ext.equals("flv") ||
                ext.equals("vob") ||
                ext.equals("ogv") ||
                ext.equals("ogg") ||
                ext.equals("drc") ||
                ext.equals("3gp")) {
            return "video";
        }
        else if(ext.equals("png") ||
                ext.equals("jpg") ||
                ext.equals("jpeg") ||
                ext.equals("gif") ||
                ext.equals("bmp")
        ){
            return "image";
        }
        return null;
    }
}

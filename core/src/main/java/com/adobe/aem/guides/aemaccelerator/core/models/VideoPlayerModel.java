package com.adobe.aem.guides.aemaccelerator.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class},
        resourceType = "aem-accelerator/components/content/videoplayer",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class VideoPlayerModel {

    @ValueMapValue
    private String youtubeId;

    @ValueMapValue
    private boolean control;

    @ValueMapValue
    private boolean mute;

    @ValueMapValue
    private boolean keyboard;

    @ValueMapValue
    private boolean fullscreen;

    @ValueMapValue
    private boolean autoHideControls;


    public String getYoutubeId() {
        return youtubeId;
    }

    public boolean isControl() {
        return control;
    }

    public boolean isMute() {
        return mute;
    }

    public boolean isKeyboard() {
        return keyboard;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public boolean isAutoHideControls() {
        return autoHideControls;
    }
}

package com.adobe.aem.guides.aemaccelerator.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.log4j.Logger;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.settings.SlingSettingsService;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

@Model(adaptables = Resource.class)
public class GTMConfigurationModel {

    @ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy=InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
    protected String resourceType;

    @OSGiService
    private SlingSettingsService settings;
    @SlingObject
    private Resource currentResource;
    @SlingObject
    private ResourceResolver resourceResolver;
//TODO get from site configuration
    String configPath = "/etc/nagarro";

    private static Logger log = Logger.getLogger(GTMConfigurationModel.class);

    @PostConstruct
    protected void init() {
    }


    public Map<String, String> getData() {
        List<String> list = new ArrayList<>();
        Map<String, String> configMap = new HashMap<>();
        Resource etcResource = resourceResolver.getResource(configPath);
        log.info("In init of"+ this.getClass().getName());
        Resource gtmResource = etcResource.getChild("gtmconfig");
        log.info("In gem resourcee of"+ gtmResource.getPath()+ gtmResource);
        if(Objects.nonNull(gtmResource)) {
            log.info("In gem resourcee of"+ gtmResource.getValueMap().size());
            ValueMap map = gtmResource.getValueMap();
            configMap.put("containerId", map.get("containerId", ""));
            configMap.put("headerScript", map.get("headerScript", ""));
            configMap.put("bodyScript", map.get("bodyScript", ""));


        }
        return configMap;
    }
}
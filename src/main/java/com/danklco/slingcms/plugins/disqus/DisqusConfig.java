/*
 * Copyright (C) 2019 Dan Klco
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danklco.slingcms.plugins.disqus;

import java.util.Optional;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.SyntheticResource;
import org.apache.sling.cms.ComponentConfiguration;
import org.apache.sling.cms.PageManager;
import org.apache.sling.models.annotations.Model;

/**
 * Configuration for the Disqus component.
 */
@Model(adaptables = { SlingHttpServletRequest.class })
public class DisqusConfig {

    private Resource resource;

    private SlingHttpServletRequest request;

    private ComponentConfiguration config;

    public DisqusConfig(SlingHttpServletRequest request) {
        this.request = request;
        resource = new SyntheticResource(request.getResource().getResourceResolver(), request.getResource().getPath(),
                request.getResource().getResourceType());
        this.config = resource.adaptTo(ComponentConfiguration.class);
    }

    private String getConfig(String key, String defaultVal) {
        return config.getProperties().get(key, defaultVal);
    }

    public String getShortName() {
        return getConfig("shortName", "");
    }

    public String getUrl() {
        if (request.getResource().getValueMap().containsKey("url")) {
            return request.getResource().getValueMap().get("url", String.class);
        } else {
            String path = Optional.ofNullable(resource.adaptTo(PageManager.class)).map(pm -> pm.getPage().getPath())
                    .orElse(resource.getPath());
            return getConfig("urlPrefix", "") + request.getResourceResolver().map(request, path)
                    + getConfig("urlSuffix", ".html");
        }
    }
}

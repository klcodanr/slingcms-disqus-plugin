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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.wrappers.ValueMapDecorator;
import org.apache.sling.cms.ComponentConfiguration;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

public class DisqusConfigTest {
    @Rule
    public final SlingContext context = new SlingContext();

    private static final String EXPECTED_SHORT_NAME = "test";

    @Before
    public void init() {
        context.addModelsForPackage("com.danklco.slingcms.plugins.disqus");
        context.load().json("/resource.json", "/content");

        Map<String, Object> data = new HashMap<>();
        data.put("shortName", EXPECTED_SHORT_NAME);
        data.put("urlPrefix", "https://sling.apache.org");
        ComponentConfiguration cc = Mockito.mock(ComponentConfiguration.class);
        Mockito.when(cc.getProperties()).thenReturn(new ValueMapDecorator(data));
        context.registerAdapter(Resource.class, ComponentConfiguration.class, cc);
    }

    @Test
    public void testDefault() {
        context.currentResource("/content/default");
        DisqusConfig config = context.request().adaptTo(DisqusConfig.class);
        assertNotNull(config);
        assertEquals(EXPECTED_SHORT_NAME, config.getShortName());
        assertEquals("https://sling.apache.org/content/default.html", config.getUrl());
    }


    @Test
    public void testWithUrl() {
        context.currentResource("/content/url");
        DisqusConfig config = context.request().adaptTo(DisqusConfig.class);
        assertNotNull(config);
        assertEquals(EXPECTED_SHORT_NAME, config.getShortName());
        assertEquals("https://www.danklco.com", config.getUrl());
    }
}

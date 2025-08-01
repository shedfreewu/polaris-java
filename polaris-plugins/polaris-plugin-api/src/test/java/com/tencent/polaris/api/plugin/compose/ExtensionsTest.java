/*
 * Tencent is pleased to support the open source community by making polaris-java available.
 *
 * Copyright (C) 2021 Tencent. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.tencent.polaris.api.plugin.compose;

import com.sun.net.httpserver.HttpHandler;
import com.tencent.polaris.api.exception.PolarisException;
import com.tencent.polaris.api.plugin.Plugin;
import com.tencent.polaris.api.plugin.PluginType;
import com.tencent.polaris.api.plugin.Supplier;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ExtensionsTest {

    @Test
    public void testInitHttpServer() {
        MockHttpServerAware server1 = new MockHttpServerAware();
        server1.getHandlers().put("/test1", null);
        server1.setName("server1");
        MockHttpServerAware server2 = new MockHttpServerAware();
        server2.getHandlers().put("/test2", null);
        server2.setName("server2");
        MockHttpServerAware server3 = new MockHttpServerAware();
        server3.getHandlers().put("/test3", null);
        server3.setName("server3");

        Extensions extensions = new Extensions();
        Map<String, HttpHandler> nodeMapMap = extensions.buildHttpHandlers(new Supplier() {
            @Override
            public Plugin getPlugin(PluginType type, String name) throws PolarisException {
                return null;
            }

            @Override
            public Plugin getOptionalPlugin(PluginType type, String name) {
                return null;
            }

            @Override
            public Collection<Plugin> getPlugins(PluginType type) throws PolarisException {
                return null;
            }

            @Override
            public Collection<Plugin> getAllPlugins() throws PolarisException {
                List<Plugin> pluginList = new ArrayList<>();
                pluginList.add(server1);
                pluginList.add(server2);
                pluginList.add(server3);
                return pluginList;
            }
        });
        Assert.assertEquals(3, nodeMapMap.size());
    }
}

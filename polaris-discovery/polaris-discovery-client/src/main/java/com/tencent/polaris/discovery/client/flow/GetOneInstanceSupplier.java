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

package com.tencent.polaris.discovery.client.flow;

import com.tencent.polaris.api.rpc.InstancesResponse;
import java.util.function.Supplier;

/**
 * GetOneInstanceSupplier
 */
public class GetOneInstanceSupplier implements Supplier<InstancesResponse> {

    private final CommonInstancesRequest request;

    private final SyncFlow syncFlow;

    public GetOneInstanceSupplier(CommonInstancesRequest request, SyncFlow syncFlow) {
        this.request = request;
        this.syncFlow = syncFlow;
    }

    @Override
    public InstancesResponse get() {
        return syncFlow.commonSyncGetOneInstance(request);
    }
}

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

package com.tencent.polaris.metadata.core;

public enum TransitiveType {

    /**
     * 不透传：标签只在当前进程中扭转，不会透传到下一跳。
     */
    NONE,

    /**
     * 单跳透传：标签只透传一跳。
     */
    DISPOSABLE,

    /**
     * 一直透传：标签会在整个微服务链路中一直透传。
     */
    PASS_THROUGH
}

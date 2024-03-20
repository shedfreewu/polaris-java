/*
 * Tencent is pleased to support the open source community by making Polaris available.
 *
 * Copyright (C) 2019 THL A29 Limited, a Tencent company. All rights reserved.
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

package com.tencent.polaris.metadata.core.impl;

import com.tencent.polaris.metadata.core.MetadataStringValue;
import com.tencent.polaris.metadata.core.TransitiveType;

import java.util.Objects;

public class MetadataStringValueImpl implements MetadataStringValue {

    private final TransitiveType transitiveType;
    private final String stringValue;

    public MetadataStringValueImpl(TransitiveType transitiveType, String stringValue) {
        this.transitiveType = transitiveType;
        this.stringValue = stringValue;
    }

    @Override
    public String getStringValue() {
        return stringValue;
    }

    @Override
    public TransitiveType getTransitiveType() {
        return transitiveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetadataStringValueImpl that = (MetadataStringValueImpl) o;
        return transitiveType == that.transitiveType && Objects.equals(stringValue, that.stringValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transitiveType, stringValue);
    }

    @Override
    public String toString() {
        return "MetadataStringValueImpl{" +
                "transitiveType=" + transitiveType +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}
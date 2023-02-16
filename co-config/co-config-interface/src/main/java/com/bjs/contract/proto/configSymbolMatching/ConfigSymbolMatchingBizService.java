/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.bjs.contract.proto.configSymbolMatching;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface ConfigSymbolMatchingBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.configSymbolMatching.ConfigSymbolMatchingBizService";
    static final String SERVICE_NAME = "configSymbolMatching.ConfigSymbolMatchingBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = ConfigSymbolMatchingBizServiceDubbo.init();

    ConfigSymbolMatchingPO getById(ConfigSymbolMatchingRequest request);

    default CompletableFuture<ConfigSymbolMatchingPO> getByIdAsync(ConfigSymbolMatchingRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    ConfigSymbolMatchingListReply selectListByIds(ConfigSymbolMatchingIdsRequest request);

    default CompletableFuture<ConfigSymbolMatchingListReply> selectListByIdsAsync(ConfigSymbolMatchingIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    ConfigSymbolMatchingListReply selectAll(ConfigSymbolMatchingPageRequest request);

    default CompletableFuture<ConfigSymbolMatchingListReply> selectAllAsync(ConfigSymbolMatchingPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    ConfigSymbolMatchingListReply selectList(ConfigSymbolMatchingPageRequest request);

    default CompletableFuture<ConfigSymbolMatchingListReply> selectListAsync(ConfigSymbolMatchingPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    ConfigSymbolMatchingReply insertOne(ConfigSymbolMatchingRequest request);

    default CompletableFuture<ConfigSymbolMatchingReply> insertOneAsync(ConfigSymbolMatchingRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    ConfigSymbolMatchingListReply insertBatch(ConfigSymbolMatchingListRequest request);

    default CompletableFuture<ConfigSymbolMatchingListReply> insertBatchAsync(ConfigSymbolMatchingListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    ConfigSymbolMatchingReply updateById(ConfigSymbolMatchingRequest request);

    default CompletableFuture<ConfigSymbolMatchingReply> updateByIdAsync(ConfigSymbolMatchingRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    ConfigSymbolMatchingReply updateBatch(ConfigSymbolMatchingListRequest request);

    default CompletableFuture<ConfigSymbolMatchingReply> updateBatchAsync(ConfigSymbolMatchingListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    ConfigSymbolMatchingReply removeById(ConfigSymbolMatchingRequest request);

    default CompletableFuture<ConfigSymbolMatchingReply> removeByIdAsync(ConfigSymbolMatchingRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    ConfigSymbolMatchingReply removeAll(ConfigSymbolMatchingIdsRequest request);

    default CompletableFuture<ConfigSymbolMatchingReply> removeAllAsync(ConfigSymbolMatchingIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }




}

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

package com.bjs.contract.proto.contractConfig;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface ContractConfigBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.contractConfig.ContractConfigBizService";
    static final String SERVICE_NAME = "contractConfig.ContractConfigBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = ContractConfigBizServiceDubbo.init();

    ContractConfigPO getById(ContractConfigRequest request);

    default CompletableFuture<ContractConfigPO> getByIdAsync(ContractConfigRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    ContractConfigPO getBySymbol(ContractConfigSymbolRequest request);

    default CompletableFuture<ContractConfigPO> getBySymbolAsync(ContractConfigSymbolRequest request){
        return CompletableFuture.supplyAsync(() -> getBySymbol(request));
    }

    ContractConfigListReply selectListByIds(ContractConfigIdsRequest request);

    default CompletableFuture<ContractConfigListReply> selectListByIdsAsync(ContractConfigIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    ContractConfigListReply selectAll(ContractConfigPageRequest request);

    default CompletableFuture<ContractConfigListReply> selectAllAsync(ContractConfigPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    ContractConfigListReply selectList(ContractConfigPageRequest request);

    default CompletableFuture<ContractConfigListReply> selectListAsync(ContractConfigPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    ContractConfigReply insertOne(ContractConfigRequest request);

    default CompletableFuture<ContractConfigReply> insertOneAsync(ContractConfigRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    ContractConfigListReply insertBatch(ContractConfigListRequest request);

    default CompletableFuture<ContractConfigListReply> insertBatchAsync(ContractConfigListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    ContractConfigReply updateById(ContractConfigRequest request);

    default CompletableFuture<ContractConfigReply> updateByIdAsync(ContractConfigRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    ContractConfigReply updateBatch(ContractConfigListRequest request);

    default CompletableFuture<ContractConfigReply> updateBatchAsync(ContractConfigListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    ContractConfigReply removeById(ContractConfigRequest request);

    default CompletableFuture<ContractConfigReply> removeByIdAsync(ContractConfigRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    ContractConfigReply removeAll(ContractConfigIdsRequest request);

    default CompletableFuture<ContractConfigReply> removeAllAsync(ContractConfigIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }




}

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

package com.bjs.contract.proto.settlement;

import java.util.concurrent.CompletableFuture;

public interface SettlementBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.settlement.SettlementBizService";
    static final String SERVICE_NAME = "settlement.SettlementBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = SettlementBizServiceDubbo.init();

    SettlementPO getById(SettlementRequest request);

    default CompletableFuture<SettlementPO> getByIdAsync(SettlementRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    SettlementListReply selectListByIds(SettlementIdsRequest request);

    default CompletableFuture<SettlementListReply> selectListByIdsAsync(SettlementIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    SettlementListReply selectAll(SettlementPageRequest request);

    default CompletableFuture<SettlementListReply> selectAllAsync(SettlementPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    SettlementListReply selectList(SettlementPageRequest request);

    default CompletableFuture<SettlementListReply> selectListAsync(SettlementPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    SettlementReply insertOne(SettlementRequest request);

    default CompletableFuture<SettlementReply> insertOneAsync(SettlementRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    SettlementListReply insertBatch(SettlementListRequest request);

    default CompletableFuture<SettlementListReply> insertBatchAsync(SettlementListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    SettlementReply updateById(SettlementRequest request);

    default CompletableFuture<SettlementReply> updateByIdAsync(SettlementRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    SettlementReply updateBatch(SettlementListRequest request);

    default CompletableFuture<SettlementReply> updateBatchAsync(SettlementListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    SettlementReply removeById(SettlementRequest request);

    default CompletableFuture<SettlementReply> removeByIdAsync(SettlementRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    SettlementReply removeAll(SettlementIdsRequest request);

    default CompletableFuture<SettlementReply> removeAllAsync(SettlementIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }




}

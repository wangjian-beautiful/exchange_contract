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

package com.bjs.contract.proto.coTrade;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface CoTradeBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.coTrade.CoTradeBizService";
    static final String SERVICE_NAME = "coTrade.CoTradeBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = CoTradeBizServiceDubbo.init();

    CoTradePO getById(CoTradeRequest request);

    default CompletableFuture<CoTradePO> getByIdAsync(CoTradeRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    CoTradeListReply selectListByIds(CoTradeIdsRequest request);

    default CompletableFuture<CoTradeListReply> selectListByIdsAsync(CoTradeIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    CoTradeListReply selectAll(CoTradePageRequest request);

    default CompletableFuture<CoTradeListReply> selectAllAsync(CoTradePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    CoTradeListReply selectList(CoTradePageRequest request);

    default CompletableFuture<CoTradeListReply> selectListAsync(CoTradePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    CoTradeReply insertOne(CoTradeRequest request);

    default CompletableFuture<CoTradeReply> insertOneAsync(CoTradeRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    CoTradeListReply insertBatch(CoTradeListRequest request);

    default CompletableFuture<CoTradeListReply> insertBatchAsync(CoTradeListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    CoTradeReply updateById(CoTradeRequest request);

    default CompletableFuture<CoTradeReply> updateByIdAsync(CoTradeRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    CoTradeReply updateBatch(CoTradeListRequest request);

    default CompletableFuture<CoTradeReply> updateBatchAsync(CoTradeListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    CoTradeReply removeById(CoTradeRequest request);

    default CompletableFuture<CoTradeReply> removeByIdAsync(CoTradeRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    CoTradeReply removeAll(CoTradeIdsRequest request);

    default CompletableFuture<CoTradeReply> removeAllAsync(CoTradeIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }




}

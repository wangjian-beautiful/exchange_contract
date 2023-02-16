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

package com.bjs.contract.proto.coTriggerOrder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface CoTriggerOrderBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.coTriggerOrder.CoTriggerOrderBizService";
    static final String SERVICE_NAME = "coTriggerOrder.CoTriggerOrderBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = CoTriggerOrderBizServiceDubbo.init();

    CoTriggerOrderPO getById(CoTriggerOrderRequest request);

    default CompletableFuture<CoTriggerOrderPO> getByIdAsync(CoTriggerOrderRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    CoTriggerOrderListReply selectListByIds(CoTriggerOrderIdsRequest request);

    default CompletableFuture<CoTriggerOrderListReply> selectListByIdsAsync(CoTriggerOrderIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    CoTriggerOrderListReply selectAll(CoTriggerOrderPageRequest request);

    default CompletableFuture<CoTriggerOrderListReply> selectAllAsync(CoTriggerOrderPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    CoTriggerOrderListReply selectList(CoTriggerOrderPageRequest request);

    default CompletableFuture<CoTriggerOrderListReply> selectListAsync(CoTriggerOrderPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    CoTriggerOrderReply insertOne(CoTriggerOrderRequest request);

    default CompletableFuture<CoTriggerOrderReply> insertOneAsync(CoTriggerOrderRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    CoTriggerOrderListReply insertBatch(CoTriggerOrderListRequest request);

    default CompletableFuture<CoTriggerOrderListReply> insertBatchAsync(CoTriggerOrderListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    CoTriggerOrderReply updateById(CoTriggerOrderRequest request);

    default CompletableFuture<CoTriggerOrderReply> updateByIdAsync(CoTriggerOrderRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    CoTriggerOrderReply updateBatch(CoTriggerOrderListRequest request);

    default CompletableFuture<CoTriggerOrderReply> updateBatchAsync(CoTriggerOrderListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    CoTriggerOrderReply removeById(CoTriggerOrderRequest request);

    default CompletableFuture<CoTriggerOrderReply> removeByIdAsync(CoTriggerOrderRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    CoTriggerOrderReply removeAll(CoTriggerOrderIdsRequest request);

    default CompletableFuture<CoTriggerOrderReply> removeAllAsync(CoTriggerOrderIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }

    CoTriggerOrderReply create(CoTriggerOrderRequest request);

    default CompletableFuture<CoTriggerOrderReply> createAsync(CoTriggerOrderRequest request){
        return CompletableFuture.supplyAsync(() -> create(request));
    }

    CoTriggerOrderReply cancelTriggerOrderById(CancelTriggerOrderByIdRequest request);

    default CompletableFuture<CoTriggerOrderReply> cancelTriggerOrderByIdAsync(CancelTriggerOrderByIdRequest request){
        return CompletableFuture.supplyAsync(() -> cancelTriggerOrderById(request));
    }

    CoTriggerOrderReply cancelAllTriggerOrder(CancelAllTriggerOrderRequest request);

    default CompletableFuture<CoTriggerOrderReply> cancelAllTriggerOrderAsync(CancelAllTriggerOrderRequest request){
        return CompletableFuture.supplyAsync(() -> cancelAllTriggerOrder(request));
    }

    CoTriggerOrderListReply selectListByStatus(CoTriggerOrderPageByStatusRequest request);

    default CompletableFuture<CoTriggerOrderListReply> selectListByStatusAsync(CoTriggerOrderPageByStatusRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByStatus(request));
    }




}

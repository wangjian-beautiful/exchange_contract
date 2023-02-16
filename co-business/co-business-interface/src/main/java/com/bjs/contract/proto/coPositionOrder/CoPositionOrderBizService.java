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

package com.bjs.contract.proto.coPositionOrder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface CoPositionOrderBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.coPositionOrder.CoPositionOrderBizService";
    static final String SERVICE_NAME = "coPositionOrder.CoPositionOrderBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = CoPositionOrderBizServiceDubbo.init();

    CoPositionOrderPO getById(CoPositionOrderRequest request);

    default CompletableFuture<CoPositionOrderPO> getByIdAsync(CoPositionOrderRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    CoPositionOrderListReply selectListByIds(CoPositionOrderIdsRequest request);

    default CompletableFuture<CoPositionOrderListReply> selectListByIdsAsync(CoPositionOrderIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    CoPositionOrderListReply selectAll(CoPositionOrderPageRequest request);

    default CompletableFuture<CoPositionOrderListReply> selectAllAsync(CoPositionOrderPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    CoPositionOrderListReply selectList(CoPositionOrderPageRequest request);

    default CompletableFuture<CoPositionOrderListReply> selectListAsync(CoPositionOrderPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    CoPositionOrderReply insertOne(CoPositionOrderRequest request);

    default CompletableFuture<CoPositionOrderReply> insertOneAsync(CoPositionOrderRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    CoPositionOrderListReply insertBatch(CoPositionOrderListRequest request);

    default CompletableFuture<CoPositionOrderListReply> insertBatchAsync(CoPositionOrderListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    CoPositionOrderReply updateById(CoPositionOrderRequest request);

    default CompletableFuture<CoPositionOrderReply> updateByIdAsync(CoPositionOrderRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    CoPositionOrderReply updateBatch(CoPositionOrderListRequest request);

    default CompletableFuture<CoPositionOrderReply> updateBatchAsync(CoPositionOrderListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    CoPositionOrderReply removeById(CoPositionOrderRequest request);

    default CompletableFuture<CoPositionOrderReply> removeByIdAsync(CoPositionOrderRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    CoPositionOrderReply removeAll(CoPositionOrderIdsRequest request);

    default CompletableFuture<CoPositionOrderReply> removeAllAsync(CoPositionOrderIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }

    CoPositionOrderReply fundingRateSettleMargin(FundingRateSettleMarginRequest request);

    default CompletableFuture<CoPositionOrderReply> fundingRateSettleMarginAsync(FundingRateSettleMarginRequest request){
        return CompletableFuture.supplyAsync(() -> fundingRateSettleMargin(request));
    }

    CoPositionOrderListReply findAllInSymbolList(FindAllInSymbolListRequest request);

    default CompletableFuture<CoPositionOrderListReply> findAllInSymbolListAsync(FindAllInSymbolListRequest request){
        return CompletableFuture.supplyAsync(() -> findAllInSymbolList(request));
    }




}

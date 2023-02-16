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

package com.bjs.contract.proto.coOrder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface CoOrderBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.coOrder.CoOrderBizService";
    static final String SERVICE_NAME = "coOrder.CoOrderBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = CoOrderBizServiceDubbo.init();

    CoOrderPO getById(CoOrderRequest request);

    default CompletableFuture<CoOrderPO> getByIdAsync(CoOrderRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    CoOrderListReply selectListByIds(CoOrderIdsRequest request);

    default CompletableFuture<CoOrderListReply> selectListByIdsAsync(CoOrderIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    CoOrderListReply selectAll(CoOrderPageRequest request);

    default CompletableFuture<CoOrderListReply> selectAllAsync(CoOrderPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    CoOrderListReply selectList(CoOrderPageRequest request);

    default CompletableFuture<CoOrderListReply> selectListAsync(CoOrderPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    CoOrderReply insertOne(CoOrderRequest request);

    default CompletableFuture<CoOrderReply> insertOneAsync(CoOrderRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    CoOrderListReply insertBatch(CoOrderListRequest request);

    default CompletableFuture<CoOrderListReply> insertBatchAsync(CoOrderListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    CoOrderReply updateById(CoOrderRequest request);

    default CompletableFuture<CoOrderReply> updateByIdAsync(CoOrderRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    CoOrderReply updateBatch(CoOrderListRequest request);

    default CompletableFuture<CoOrderReply> updateBatchAsync(CoOrderListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    CoOrderReply removeById(CoOrderRequest request);

    default CompletableFuture<CoOrderReply> removeByIdAsync(CoOrderRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    CoOrderReply removeAll(CoOrderIdsRequest request);

    default CompletableFuture<CoOrderReply> removeAllAsync(CoOrderIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }

    CoOrderReply create(CoOrderRequest request);

    default CompletableFuture<CoOrderReply> createAsync(CoOrderRequest request){
        return CompletableFuture.supplyAsync(() -> create(request));
    }

    CoOrderReply close(CoOrderRequest request);

    default CompletableFuture<CoOrderReply> closeAsync(CoOrderRequest request){
        return CompletableFuture.supplyAsync(() -> close(request));
    }

    CoOrderReply cancelOrder(CoOrderRequest request);

    default CompletableFuture<CoOrderReply> cancelOrderAsync(CoOrderRequest request){
        return CompletableFuture.supplyAsync(() -> cancelOrder(request));
    }

    CoOrderStatusReply cancelUserOrders(CoOrderCancelRequest request);

    default CompletableFuture<CoOrderStatusReply> cancelUserOrdersAsync(CoOrderCancelRequest request){
        return CompletableFuture.supplyAsync(() -> cancelUserOrders(request));
    }

    CoOrderReply cancelUserOrdersNotFilled(CoOrderCancelRequest request);

    default CompletableFuture<CoOrderReply> cancelUserOrdersNotFilledAsync(CoOrderCancelRequest request){
        return CompletableFuture.supplyAsync(() -> cancelUserOrdersNotFilled(request));
    }

    CoUserOrderCountReply getUserOrderCount(CoUserOrderRequest request);

    default CompletableFuture<CoUserOrderCountReply> getUserOrderCountAsync(CoUserOrderRequest request){
        return CompletableFuture.supplyAsync(() -> getUserOrderCount(request));
    }

    CoOrderListReply selectListByStatus(CoOrderPageByStatusRequest request);

    default CompletableFuture<CoOrderListReply> selectListByStatusAsync(CoOrderPageByStatusRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByStatus(request));
    }




}

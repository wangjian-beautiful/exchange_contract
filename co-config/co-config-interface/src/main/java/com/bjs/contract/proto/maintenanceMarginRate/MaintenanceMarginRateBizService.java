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

package com.bjs.contract.proto.maintenanceMarginRate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface MaintenanceMarginRateBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRateBizService";
    static final String SERVICE_NAME = "maintenanceMarginRate.MaintenanceMarginRateBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = MaintenanceMarginRateBizServiceDubbo.init();

    MaintenanceMarginRatePO getById(MaintenanceMarginRateRequest request);

    default CompletableFuture<MaintenanceMarginRatePO> getByIdAsync(MaintenanceMarginRateRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    MaintenanceMarginRatePO getByNominalValue(NominalValueRequest request);

    default CompletableFuture<MaintenanceMarginRatePO> getByNominalValueAsync(NominalValueRequest request){
        return CompletableFuture.supplyAsync(() -> getByNominalValue(request));
    }

    MaintenanceMarginRatePO symbolMaxLeverage(SymbolMaxLeverageRequest request);

    default CompletableFuture<MaintenanceMarginRatePO> symbolMaxLeverageAsync(SymbolMaxLeverageRequest request){
        return CompletableFuture.supplyAsync(() -> symbolMaxLeverage(request));
    }

    SymbolIntervalReply symbolInterval(SymbolIntervalRequest request);

    default CompletableFuture<SymbolIntervalReply> symbolIntervalAsync(SymbolIntervalRequest request){
        return CompletableFuture.supplyAsync(() -> symbolInterval(request));
    }

    MaintenanceMarginRateListReply selectListByIds(MaintenanceMarginRateIdsRequest request);

    default CompletableFuture<MaintenanceMarginRateListReply> selectListByIdsAsync(MaintenanceMarginRateIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    MaintenanceMarginRateListReply selectAll(MaintenanceMarginRatePageRequest request);

    default CompletableFuture<MaintenanceMarginRateListReply> selectAllAsync(MaintenanceMarginRatePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    MaintenanceMarginRateListReply selectList(MaintenanceMarginRatePageRequest request);

    default CompletableFuture<MaintenanceMarginRateListReply> selectListAsync(MaintenanceMarginRatePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    MaintenanceMarginRateReply insertOne(MaintenanceMarginRateRequest request);

    default CompletableFuture<MaintenanceMarginRateReply> insertOneAsync(MaintenanceMarginRateRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    MaintenanceMarginRateListReply insertBatch(MaintenanceMarginRateListRequest request);

    default CompletableFuture<MaintenanceMarginRateListReply> insertBatchAsync(MaintenanceMarginRateListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    MaintenanceMarginRateReply updateById(MaintenanceMarginRateRequest request);

    default CompletableFuture<MaintenanceMarginRateReply> updateByIdAsync(MaintenanceMarginRateRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    MaintenanceMarginRateReply updateBatch(MaintenanceMarginRateListRequest request);

    default CompletableFuture<MaintenanceMarginRateReply> updateBatchAsync(MaintenanceMarginRateListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    MaintenanceMarginRateReply removeById(MaintenanceMarginRateRequest request);

    default CompletableFuture<MaintenanceMarginRateReply> removeByIdAsync(MaintenanceMarginRateRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    MaintenanceMarginRateReply removeAll(MaintenanceMarginRateIdsRequest request);

    default CompletableFuture<MaintenanceMarginRateReply> removeAllAsync(MaintenanceMarginRateIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }

    MaintenanceMarginRateMinReply getMaintenanceMarginRateMin(MaintenanceMarginRateMinRequest request);

    default CompletableFuture<MaintenanceMarginRateMinReply> getMaintenanceMarginRateMinAsync(MaintenanceMarginRateMinRequest request){
        return CompletableFuture.supplyAsync(() -> getMaintenanceMarginRateMin(request));
    }




}

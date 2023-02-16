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

package com.bjs.contract.proto.fundingRate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface FundingRateBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.fundingRate.FundingRateBizService";
    static final String SERVICE_NAME = "fundingRate.FundingRateBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = FundingRateBizServiceDubbo.init();

    FundingRatePO getById(FundingRateRequest request);

    default CompletableFuture<FundingRatePO> getByIdAsync(FundingRateRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    FundingRateListReply selectListByIds(FundingRateIdsRequest request);

    default CompletableFuture<FundingRateListReply> selectListByIdsAsync(FundingRateIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    FundingRateListReply selectAll(FundingRatePageRequest request);

    default CompletableFuture<FundingRateListReply> selectAllAsync(FundingRatePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    FundingRateListReply selectList(FundingRatePageRequest request);

    default CompletableFuture<FundingRateListReply> selectListAsync(FundingRatePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    FundingRateReply insertOne(FundingRateRequest request);

    default CompletableFuture<FundingRateReply> insertOneAsync(FundingRateRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    FundingRateListReply insertBatch(FundingRateListRequest request);

    default CompletableFuture<FundingRateListReply> insertBatchAsync(FundingRateListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    FundingRateReply updateById(FundingRateRequest request);

    default CompletableFuture<FundingRateReply> updateByIdAsync(FundingRateRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    FundingRateReply updateBatch(FundingRateListRequest request);

    default CompletableFuture<FundingRateReply> updateBatchAsync(FundingRateListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    FundingRateReply removeById(FundingRateRequest request);

    default CompletableFuture<FundingRateReply> removeByIdAsync(FundingRateRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    FundingRateReply removeAll(FundingRateIdsRequest request);

    default CompletableFuture<FundingRateReply> removeAllAsync(FundingRateIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }




}

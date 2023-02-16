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

package com.bjs.contract.proto.userLeverage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface UserLeverageBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.userLeverage.UserLeverageBizService";
    static final String SERVICE_NAME = "userLeverage.UserLeverageBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = UserLeverageBizServiceDubbo.init();

    UserLeveragePO getById(UserLeverageRequest request);

    default CompletableFuture<UserLeveragePO> getByIdAsync(UserLeverageRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    UserLeveragePO getBySymbol(GetBySymbolRequest request);

    default CompletableFuture<UserLeveragePO> getBySymbolAsync(GetBySymbolRequest request){
        return CompletableFuture.supplyAsync(() -> getBySymbol(request));
    }

    UserLeverageListReply selectListByIds(UserLeverageIdsRequest request);

    default CompletableFuture<UserLeverageListReply> selectListByIdsAsync(UserLeverageIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    UserLeverageListReply selectAll(UserLeveragePageRequest request);

    default CompletableFuture<UserLeverageListReply> selectAllAsync(UserLeveragePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    UserLeverageListReply selectList(UserLeveragePageRequest request);

    default CompletableFuture<UserLeverageListReply> selectListAsync(UserLeveragePageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    UserLeverageReply insertOne(UserLeverageRequest request);

    default CompletableFuture<UserLeverageReply> insertOneAsync(UserLeverageRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    UserLeverageListReply insertBatch(UserLeverageListRequest request);

    default CompletableFuture<UserLeverageListReply> insertBatchAsync(UserLeverageListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    UserLeverageReply updateById(UserLeverageRequest request);

    default CompletableFuture<UserLeverageReply> updateByIdAsync(UserLeverageRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    UserLeverageReply updateBatch(UserLeverageListRequest request);

    default CompletableFuture<UserLeverageReply> updateBatchAsync(UserLeverageListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    UserLeverageReply removeById(UserLeverageRequest request);

    default CompletableFuture<UserLeverageReply> removeByIdAsync(UserLeverageRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    UserLeverageReply removeAll(UserLeverageIdsRequest request);

    default CompletableFuture<UserLeverageReply> removeAllAsync(UserLeverageIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }

    UserLeverageReply updateLeverage(UpdateLeverageRequest request);

    default CompletableFuture<UserLeverageReply> updateLeverageAsync(UpdateLeverageRequest request){
        return CompletableFuture.supplyAsync(() -> updateLeverage(request));
    }




}

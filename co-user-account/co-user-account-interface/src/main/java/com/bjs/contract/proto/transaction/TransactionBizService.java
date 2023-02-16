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

package com.bjs.contract.proto.transaction;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface TransactionBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.transaction.TransactionBizService";
    static final String SERVICE_NAME = "transaction.TransactionBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = TransactionBizServiceDubbo.init();

    TransactionPO getById(TransactionRequest request);

    default CompletableFuture<TransactionPO> getByIdAsync(TransactionRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    TransactionListReply selectListByIds(TransactionIdsRequest request);

    default CompletableFuture<TransactionListReply> selectListByIdsAsync(TransactionIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    TransactionListReply selectAll(TransactionPageRequest request);

    default CompletableFuture<TransactionListReply> selectAllAsync(TransactionPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    TransactionListReply selectList(TransactionPageRequest request);

    default CompletableFuture<TransactionListReply> selectListAsync(TransactionPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    TransactionReply insertOne(TransactionRequest request);

    default CompletableFuture<TransactionReply> insertOneAsync(TransactionRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    TransactionListReply insertBatch(TransactionListRequest request);

    default CompletableFuture<TransactionListReply> insertBatchAsync(TransactionListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    TransactionReply updateById(TransactionRequest request);

    default CompletableFuture<TransactionReply> updateByIdAsync(TransactionRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    TransactionReply updateBatch(TransactionListRequest request);

    default CompletableFuture<TransactionReply> updateBatchAsync(TransactionListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    TransactionReply removeById(TransactionRequest request);

    default CompletableFuture<TransactionReply> removeByIdAsync(TransactionRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    TransactionReply removeAll(TransactionIdsRequest request);

    default CompletableFuture<TransactionReply> removeAllAsync(TransactionIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }

    UserTransactionReply getUserTransactionList(UserTransactionRequest request);

    default CompletableFuture<UserTransactionReply> getUserTransactionListAsync(UserTransactionRequest request){
        return CompletableFuture.supplyAsync(() -> getUserTransactionList(request));
    }




}

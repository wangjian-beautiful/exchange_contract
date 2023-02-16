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

package com.bjs.contract.proto.account;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface AccountBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.account.AccountBizService";
    static final String SERVICE_NAME = "account.AccountBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = AccountBizServiceDubbo.init();

    AccountPO getById(AccountRequest request);

    default CompletableFuture<AccountPO> getByIdAsync(AccountRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    AccountListReply selectListByIds(AccountIdsRequest request);

    default CompletableFuture<AccountListReply> selectListByIdsAsync(AccountIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    AccountListReply selectAll(AccountPageRequest request);

    default CompletableFuture<AccountListReply> selectAllAsync(AccountPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    AccountListReply selectList(AccountPageRequest request);

    default CompletableFuture<AccountListReply> selectListAsync(AccountPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    AccountReply insertOne(AccountRequest request);

    default CompletableFuture<AccountReply> insertOneAsync(AccountRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    AccountListReply insertBatch(AccountListRequest request);

    default CompletableFuture<AccountListReply> insertBatchAsync(AccountListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    AccountReply updateById(AccountRequest request);

    default CompletableFuture<AccountReply> updateByIdAsync(AccountRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    AccountReply updateBatch(AccountListRequest request);

    default CompletableFuture<AccountReply> updateBatchAsync(AccountListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    AccountReply removeById(AccountRequest request);

    default CompletableFuture<AccountReply> removeByIdAsync(AccountRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    AccountReply removeAll(AccountIdsRequest request);

    default CompletableFuture<AccountReply> removeAllAsync(AccountIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }

    AccountReply accountOperate(AccountOperate request);

    default CompletableFuture<AccountReply> accountOperateAsync(AccountOperate request){
        return CompletableFuture.supplyAsync(() -> accountOperate(request));
    }

    AccountReply getUserBalance(AccountRequest request);

    default CompletableFuture<AccountReply> getUserBalanceAsync(AccountRequest request){
        return CompletableFuture.supplyAsync(() -> getUserBalance(request));
    }

    AccountReply getUidBalance(AccountUserIdRequest request);

    default CompletableFuture<AccountReply> getUidBalanceAsync(AccountUserIdRequest request){
        return CompletableFuture.supplyAsync(() -> getUidBalance(request));
    }

    AccountPO getByUidAndType(AccountRequest request);

    default CompletableFuture<AccountPO> getByUidAndTypeAsync(AccountRequest request){
        return CompletableFuture.supplyAsync(() -> getByUidAndType(request));
    }

    AccountReply freeze(AccountRequest request);

    default CompletableFuture<AccountReply> freezeAsync(AccountRequest request){
        return CompletableFuture.supplyAsync(() -> freeze(request));
    }

    UserAccountReply accountRateDeduction(UserAccountTransferListRequest request);

    default CompletableFuture<UserAccountReply> accountRateDeductionAsync(UserAccountTransferListRequest request){
        return CompletableFuture.supplyAsync(() -> accountRateDeduction(request));
    }

    AccountReply accountRateAdd(UserAccountTransferListRequest request);

    default CompletableFuture<AccountReply> accountRateAddAsync(UserAccountTransferListRequest request){
        return CompletableFuture.supplyAsync(() -> accountRateAdd(request));
    }

    UserAccountListReply getUserAccountList(AccountUidRequest request);

    default CompletableFuture<UserAccountListReply> getUserAccountListAsync(AccountUidRequest request){
        return CompletableFuture.supplyAsync(() -> getUserAccountList(request));
    }

    UserTagResponse getUserTagAccount(AccountUserTagRequest request);

    default CompletableFuture<UserTagResponse> getUserTagAccountAsync(AccountUserTagRequest request){
        return CompletableFuture.supplyAsync(() -> getUserTagAccount(request));
    }

    AccountReply accountTransfer(AccountTransferReq request);

    default CompletableFuture<AccountReply> accountTransferAsync(AccountTransferReq request){
        return CompletableFuture.supplyAsync(() -> accountTransfer(request));
    }




}

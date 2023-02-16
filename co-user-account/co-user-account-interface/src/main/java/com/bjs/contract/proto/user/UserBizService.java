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

package com.bjs.contract.proto.user;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

public interface UserBizService {

    static final String JAVA_SERVICE_NAME = "com.bjs.contract.proto.user.UserBizService";
    static final String SERVICE_NAME = "user.UserBizService";

    // FIXME, initialize Dubbo3 stub when interface loaded, thinking of new ways doing this.
    static final boolean inited = UserBizServiceDubbo.init();

    UserPO getById(UserRequest request);

    default CompletableFuture<UserPO> getByIdAsync(UserRequest request){
        return CompletableFuture.supplyAsync(() -> getById(request));
    }

    UserListReply selectListByIds(UserIdsRequest request);

    default CompletableFuture<UserListReply> selectListByIdsAsync(UserIdsRequest request){
        return CompletableFuture.supplyAsync(() -> selectListByIds(request));
    }

    UserListReply selectAll(UserPageRequest request);

    default CompletableFuture<UserListReply> selectAllAsync(UserPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectAll(request));
    }

    UserListReply selectList(UserPageRequest request);

    default CompletableFuture<UserListReply> selectListAsync(UserPageRequest request){
        return CompletableFuture.supplyAsync(() -> selectList(request));
    }

    UserReply insertOne(UserRequest request);

    default CompletableFuture<UserReply> insertOneAsync(UserRequest request){
        return CompletableFuture.supplyAsync(() -> insertOne(request));
    }

    UserListReply insertBatch(UserListRequest request);

    default CompletableFuture<UserListReply> insertBatchAsync(UserListRequest request){
        return CompletableFuture.supplyAsync(() -> insertBatch(request));
    }

    UserReply updateById(UserRequest request);

    default CompletableFuture<UserReply> updateByIdAsync(UserRequest request){
        return CompletableFuture.supplyAsync(() -> updateById(request));
    }

    UserReply updateBatch(UserListRequest request);

    default CompletableFuture<UserReply> updateBatchAsync(UserListRequest request){
        return CompletableFuture.supplyAsync(() -> updateBatch(request));
    }

    UserReply removeById(UserRequest request);

    default CompletableFuture<UserReply> removeByIdAsync(UserRequest request){
        return CompletableFuture.supplyAsync(() -> removeById(request));
    }

    UserReply removeAll(UserIdsRequest request);

    default CompletableFuture<UserReply> removeAllAsync(UserIdsRequest request){
        return CompletableFuture.supplyAsync(() -> removeAll(request));
    }




}

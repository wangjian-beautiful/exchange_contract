package com.bjs.contract.action;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.controller.response.UserTransactionListResp;
import com.bjs.contract.controller.response.UserTransactionResp;
import com.bjs.contract.proto.transaction.TransactionBizService;

import com.bjs.contract.proto.transaction.UserTransactionList;
import com.bjs.contract.proto.transaction.UserTransactionReply;
import com.bjs.contract.proto.transaction.UserTransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nike
 * @date 2022年12月01日 15:00
 */
@Component
@Slf4j
public class TransactionAction {

    @DubboReference(filter = "SetTokenFilter")
    private TransactionBizService transactionBizService;



    public UserTransactionResp  getUserTransactionList(int page,int limit,String beginTime,String endTime,String symbol,
                                                       String type,Long uid){
        UserTransactionRequest.Builder request= UserTransactionRequest.newBuilder();

        if (StringUtils.isNotEmpty(beginTime)) {
            request.setBeginTime(beginTime);
        }
        if (StringUtils.isNotEmpty(endTime)){
            request.setEndTime(endTime);
        }
        if (StringUtils.isNotEmpty(symbol)){
            request.setSymbol(symbol);
        }
        if (StringUtils.isNotEmpty(type)){
            request.setType(type);
        }
        request.setPage(page);
        request.setUid(uid);
        request.setLimit(limit);
        UserTransactionReply userTransactionReply= transactionBizService.getUserTransactionList(request.build());
        List<UserTransactionList> lists=userTransactionReply.getTransactionListList();
        List<UserTransactionListResp> listResp= ProtoBeanUtils.toPojoBeanList(UserTransactionListResp.class,lists);
        UserTransactionResp resp=new UserTransactionResp();
        resp.setCount(userTransactionReply.getCount());
        resp.setList(listResp);
        return resp;
    }

    public static void main(String[] args) {


        UserTransactionReply.Builder userTransactionReply=UserTransactionReply.newBuilder();
        userTransactionReply.setCount(10);
        List<UserTransactionList> lists=new ArrayList<>();
        UserTransactionList.Builder transactionList=UserTransactionList.newBuilder();
        transactionList.setContractName("");
        transactionList.setCtime("123");
        transactionList.setAmount("321");
        transactionList.setType("1");
        transactionList.setSymbol("321");

        lists.add(transactionList.build());


        userTransactionReply.addAllTransactionList(lists);

        List<UserTransactionListResp> listResp= ProtoBeanUtils.toPojoBeanList(UserTransactionListResp.class, userTransactionReply.getTransactionListList());

        System.out.println(listResp);
    }
}

package com.bjs.contract.action;

import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.controller.response.UserAccountListResp;
import com.bjs.contract.controller.response.UserAccountResp;
import com.bjs.contract.proto.account.AccountBizService;
import com.bjs.contract.proto.account.AccountUidRequest;
import com.bjs.contract.proto.account.UserAccountList;
import com.bjs.contract.proto.account.UserAccountListReply;
import com.bjs.contract.proto.coOrder.CoOrderBizService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nike
 * @date 2022年12月01日 10:50
 */
@Component
@Slf4j
public class UserAccountAction {


    @DubboReference(filter = "SetTokenFilter")
    private AccountBizService accountBizService;


    public UserAccountResp getUserAccountList(Long uid) {
        AccountUidRequest request = AccountUidRequest.newBuilder().setUid(uid).build();
        UserAccountListReply reply = accountBizService.getUserAccountList(request);

        List<UserAccountList> list = reply.getAccountListList();

        log.info("获取数据：{}", list);

        UserAccountResp resp = new UserAccountResp();
        List<UserAccountListResp> accountListResp = ProtoBeanUtils.toPojoBeanList(UserAccountListResp.class, list);
        resp.setAccountListResp(accountListResp);
        resp.setTotalBalance(reply.getTotalBalance());
        resp.setTotalBalanceSymbol(reply.getTotalBalanceSymbol());
        return resp;
    }
}

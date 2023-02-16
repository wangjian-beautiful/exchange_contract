package com.bjs.contract.action;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.controller.response.UserAccountResp;
import com.bjs.contract.proto.account.AccountBizService;
import com.bjs.contract.proto.account.AccountUidRequest;
import com.bjs.contract.proto.account.UserAccountListReply;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author nike
 * @date 2022年12月01日 10:50
 */
@Component
public class UserAccountAction {


    @DubboReference(filter = "SetTokenFilter")
    private AccountBizService accountBizService;


    public UserAccountResp getUserAccountList(Long uid) {
        AccountUidRequest request = AccountUidRequest.newBuilder().setUid(uid).build();
        UserAccountListReply reply = accountBizService.getUserAccountList(request);

        UserAccountResp resp = new UserAccountResp();
        List<UserAccountResp> accountListResp = ProtoBeanUtils.toPojoBeanList(UserAccountResp.class, reply.getAccountListList());
        resp.setAccountListResp(accountListResp);
        resp.setTotalBalance(reply.getTotalBalance());
        resp.setTotalBalanceSymbol(reply.getTotalBalanceSymbol());
        return resp;
    }
}

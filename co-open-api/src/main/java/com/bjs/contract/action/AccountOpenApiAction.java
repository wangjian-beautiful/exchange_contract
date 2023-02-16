package com.bjs.contract.action;

import com.bjs.contract.entity.request.AccountTransferReq;
import com.bjs.contract.proto.account.AccountBizService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author nike
 * @date 2022年12月14日 14:20
 */
@Component
public class AccountOpenApiAction {

    @DubboReference
    private AccountBizService accountBizService;


    public void  accountTransfer(AccountTransferReq req,Long uid,String type){
            com.bjs.contract.proto.account.AccountTransferReq request=com.bjs.contract.proto.account.AccountTransferReq.
                    newBuilder().setAmount(req.getAmount()).
                    setSymbol(req.getSymbol()).
                    setUid(uid).setType(type).build();
        accountBizService.accountTransfer(request);
    }
}

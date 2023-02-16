package com.bjs.contract.action;

import com.bijinsuo.common.domain.AccountDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.proto.account.AccountBizService;
import com.bjs.contract.proto.account.AccountOperate;
import com.bjs.contract.proto.account.AccountReply;
import com.bjs.contract.proto.account.AccountUserIdRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class AccountAction {
    @DubboReference
    private AccountBizService accountBizService;



    public AccountDTO getByUidAndType(Long uid, Long type) {
        AccountUserIdRequest request = AccountUserIdRequest.newBuilder()
            .setUid(uid)
            .setType(type)
            .build();
        AccountReply reply = accountBizService.getUidBalance(request);
        if (!reply.getStatus()) {
            log.error("getByUidAndType msg={}" + reply.toString());
            throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
        } else {
            return ProtoBeanUtils.toPojoBean(AccountDTO.class, reply.getAccountPO());
        }
    }
}

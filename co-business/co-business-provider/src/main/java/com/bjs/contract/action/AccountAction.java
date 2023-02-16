package com.bjs.contract.action;

import com.bijinsuo.common.domain.AccountDTO;
import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.AccountSymbolConstant;
import com.bjs.contract.proto.account.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class AccountAction {
    @DubboReference
    private AccountBizService accountBizService;

    /**
     * @param uid            uid
     * @param amount         金额
     * @param symbol         币对
     * @param scene          TransactionSceneEnum
     * @param refType
     * @param refId
     * @param profitAndLoss  利润
     * @param ventureCapital 风险准备金
     * @return
     */
    public boolean operateAccount(Long uid, BigDecimal amount, String symbol, String scene, String refType, Long refId, BigDecimal profitAndLoss, BigDecimal ventureCapital, BigDecimal serviceCharge) {
        AccountOperate accountOperate = AccountOperate.newBuilder()
                .setUid(uid)
                .setAmount(amount.toPlainString())
                .setSymbol(symbol)
                .setScene(scene)
                .setRefType(refType)
                .setRefId(refId)
                .setProfitAndLoss(profitAndLoss.toPlainString())
                .setVentureCapital(ventureCapital.toPlainString())
                .setServiceCharge(serviceCharge.toPlainString()).build();
        log.info("AccountAction operateAccount账户操作 accountOperate={},", accountOperate.toString());
        AccountReply accountReply = accountBizService.accountOperate(accountOperate);
        log.info("AccountAction operateAccount账户操作 accountReply={}", accountReply.toString());
        return accountReply.getStatus();
    }

    public AccountDTO getByUidAndType(Long uid, Long type) {
        AccountUserIdRequest request = AccountUserIdRequest.newBuilder()
                .setUid(uid)
                .setType(type)
                .build();
        AccountReply reply = accountBizService.getUidBalance(request);
        if (!reply.getStatus()) {
            log.error("getByUidAndType msg={}" + reply.toString());
            throw new BizException(CommonEnum.NOT_ENOUGH_BALANCE);
        }
        return ProtoBeanUtils.toPojoBean(AccountDTO.class, reply.getAccountPO());
    }

    public UserTagResponse getUserTagAccount (Long uid, String tag) {
        AccountUserTagRequest request = AccountUserTagRequest.newBuilder().setUid(uid).setTag(tag).build();
        UserTagResponse userTagAccount = accountBizService.getUserTagAccount(request);
        return userTagAccount;
    }

    public BigDecimal getUserTotalAmount(Long uid) {
        AccountUserTagRequest request = AccountUserTagRequest.newBuilder()
                .setUid(uid)
                .setTag(AccountSymbolConstant.USDT)
                .build();
        UserTagResponse userTagResponse = accountBizService.getUserTagAccount(request);
        if (userTagResponse.getAmount() != null) {
            return new BigDecimal(userTagResponse.getAmount());
        } else {
            return BigDecimal.ZERO;
        }

    }
}

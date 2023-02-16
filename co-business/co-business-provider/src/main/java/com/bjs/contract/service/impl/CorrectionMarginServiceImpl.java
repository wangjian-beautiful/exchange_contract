package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bijinsuo.common.utils.enums.AccountTypeEnum;
import com.bijinsuo.common.utils.enums.TransactionSceneEnum;
import com.bjs.contract.AccountSymbolConstant;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.entity.CoPositionOrder;
import com.bjs.contract.service.CoPositionOrderService;
import com.bjs.contract.service.CorrectionMarginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

/**
 * @author Winter
 */
@Service
public class CorrectionMarginServiceImpl implements CorrectionMarginService {
  @Autowired
  private CoPositionOrderService positionService;
  @Autowired
  private AccountAction accountAction;

  @Override
  public void correct(long uid) {
    final var positions = positionService.list(Wrappers.lambdaQuery(CoPositionOrder.class).eq(CoPositionOrder::getUid, uid));
    if (CollectionUtils.isEmpty(positions)) {
      final var margin = accountAction.getByUidAndType(uid, Long.valueOf(AccountTypeEnum.bond.getCode()));
      if (margin.getBalance().compareTo(BigDecimal.ZERO) > 0) {
        accountAction.operateAccount(uid,
                margin.getBalance(),
                AccountSymbolConstant.USDT,
                TransactionSceneEnum.CORRECT_BOND.getValue(),
                "correct_margin",
                0l,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO);
      }
    }
  }
}

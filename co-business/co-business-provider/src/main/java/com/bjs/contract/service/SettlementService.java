package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bijinsuo.common.utils.enums.TransactionSceneEnum;
import com.bjs.contract.domain.UserPositionDetail;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoPositionOrder;
import com.bjs.contract.entity.Settlement;

import java.math.BigDecimal;

/**
 * 
 *
 * @author bjs code generator
 * @date 2022-11-11 10:54:13
 */
public interface SettlementService extends IService<Settlement> {

    void settlement(CoOrder coOrder, CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO, BigDecimal deltaMargin, BigDecimal fee, TransactionSceneEnum sceneEnum);
}

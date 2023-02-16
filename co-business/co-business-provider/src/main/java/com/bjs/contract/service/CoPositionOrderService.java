package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bijinsuo.common.domain.FundingRateSettlePositionDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDetailsDTO;
import com.bijinsuo.common.utils.entity.PositionLiquidationDTO;
import com.bijinsuo.common.utils.entity.RiskDataDTO;
import com.bijinsuo.common.utils.enums.PositionOrderStatusEnum;
import com.bijinsuo.common.utils.enums.PositionSideEnum;
import com.bjs.contract.domain.UserPositionDetail;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoPositionOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author bjs code generator
 * @date 2022-11-11 17:58:51
 */
public interface CoPositionOrderService extends IService<CoPositionOrder> {


    CoPositionOrder getByUidAndStatusAndSide(Long uid, String symbol, PositionOrderStatusEnum positionOrderStatusEnum, PositionSideEnum positionSide);


    CoPositionOrder getByUidAndStatusAndSideWithLock(Long uid, String symbol, PositionSideEnum positionSide);

    /**
     * 这个方法用于第一次开仓
     *
     * @return
     */
    UserPositionDetail initPosition(MatchTradeDetailsDTO matchTradeDetailsDTO, CoOrder coOrder, PositionSideEnum positionSideEnum, String tradeNumber);


    void initPositionCacheAndSendMq(CoPositionOrder coPositionOrder);

    void updatePositionCacheAndSendMq(CoPositionOrder coPositionOrder);

    void deletePositionCache(CoPositionOrder coPositionOrder);

    UserPositionDetail mergePositionForTrade(CoOrder coOrder, CoPositionOrder coPositionOrder, MatchTradeDetailsDTO matchTradeDetailsDTO, String tradeNumber);


    /**
     * 资金费率 结算 扣除保证金
     *
     * @param symbol                币队
     * @param positionSide          方向
     * @param settlePositionDTOList 具体扣除的用户
     * @return
     */
    boolean updatePositionForFundingRateSettle(String symbol, String positionSide
            , List<FundingRateSettlePositionDTO> settlePositionDTOList);

    void settleMarginDb(String symbol, String positionSide
            , List<FundingRateSettlePositionDTO> settlePositionDTOList);

    List<CoPositionOrder> findAllInSymbolList(Integer status, List<String> symbolList);

    void cancelOrder(CoOrder coOrder);

    /**
     * 系统强制市价平所有仓位
     *
     * @param uid            用户id
     * @param waitTimeSecond 等待时间 秒
     * @return
     */
    boolean sysCloseUserAllPosition(Long uid, Integer waitTimeSecond) throws InterruptedException;

    void liquidation(PositionLiquidationDTO positionLiquidationDTO);

    List<UserPositionDetail> handlePositionForTrade(MatchTradeDTO dto);

    UserPositionDetail handleUserPosition(Long orderId, MatchTradeDetailsDTO dto, String tradeNumber);

    BigDecimal selectPositionNominalValueWithLock(Long uid, String symbol, String side);

    CoPositionOrder selectPositionWithLock(Long uid, String symbol, String side);

    void updatePositionForLeverageChange(Integer oldLeverage, Integer newLeverage, Long uid, String symbol);

    RiskDataDTO getRiskData(Long uid);

    RiskDataDTO getRiskDataBySymbol(Long uid, String symbol);

    void calculateAndSetPositionProperties(CoPositionOrder target, CoOrder coOrder, Integer quotePrecision);


    void delaySyncCache(CoPositionOrder coPositionOrder);
}

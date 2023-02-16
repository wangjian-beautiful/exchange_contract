package com.bjs.contract.service;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.utils.entity.MatchOrderDTO;
import com.bijinsuo.common.utils.entity.MatchTradeDTO;
import com.bijinsuo.common.utils.entity.OrderCancelResultDTO;
import com.bijinsuo.common.utils.entity.PositionLiquidationDTO;
import com.bijinsuo.common.utils.enums.MatchInfoTypeEnum;
import com.bijinsuo.common.utils.enums.OperateSideEnum;
import com.bijinsuo.common.utils.enums.OrderStatus;
import com.bijinsuo.common.utils.enums.OrderTypeEnum;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoPositionOrder;
import com.bjs.contract.entity.UserLeverage;
import com.bjs.contract.proto.coOrder.CoOrderRequest;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author bjs code generator
 * @date 2022-11-12 14:25:01
 */
public interface CoOrderService extends IService<CoOrder> {

    default List<CoOrder> findOrdersToCancel(long uid, String symbol, String side) {
        return list(Wrappers.lambdaQuery(CoOrder.class)
                .eq(CoOrder::getUid, uid)
                .eq(StringUtils.isNotBlank(symbol), CoOrder::getSymbol, symbol)
                .eq(StringUtils.isNotBlank(side), CoOrder::getOperateSide, side)
                .in(CoOrder::getStatus, OrderStatus.getCanCancelValueList()));
    }

    CoOrder createOrder(CoOrderRequest request);

    CoOrder cancelOrder(Long orderId);

    void cancelOrderAfterMatch(MatchTradeDTO dto);

    boolean cancelUserOrdersNotFilled(Long uid);

    List<OrderCancelResultDTO> cancelUserOrders(long uid, String symbol, String side, int timeout);


    MatchOrderDTO createMatchOrder(CoOrder coOrder, MatchInfoTypeEnum matchInfoTypeEnum);


    void createLiquidationOrdersAndSettlement(List<CoPositionOrder> list, PositionLiquidationDTO positionLiquidationDTO);

    List<CoOrder> selectUserOnTheWayOrderList(Long uid, String symbol);

    BigDecimal selectUserOnTheWayTotalQuote(Long uid, String symbol);


    /**
     * 因为新的杠杠倍数计算 新的金额
     */
    void handleNewAmountForNewLeverage(Integer newLeverage, UserLeverage oldLeverage, Long uid, String symbol);

    int getOrderCount(long id);

    void refundOrderFeeAmount(Long orderId);


    CoOrder orderClose(CoOrderDTO coOrderDTO, ContractConfigDTO contractConfigDTO);

    CoOrderDTO orderCloseForNewTransaction(CoOrderDTO coOrderDTO, ContractConfigDTO contractConfigDTO);

    /**
     * 初始化一个市价系统平仓单
     *
     * @param positionOrder
     * @param configDTO
     * @param orderTypeEnum
     * @return
     */
    CoOrder initSysClosePositionMarketOrderWIthInMatch(CoPositionOrder positionOrder, ContractConfigDTO configDTO, OrderTypeEnum orderTypeEnum);

    CoOrderDTO initSysClosePositionMarketDtoOrderWithInMatch(CoPositionOrder positionOrder, ContractConfigDTO configDTO, OrderTypeEnum orderTypeEnum);

    void handleMatchForRobot(MatchTradeDTO dto);

    void handleCompleteOrders(List<MatchOrderDTO> completedOrders);

    CoOrder getOrderFromMongo(Long orderId, MatchTradeDTO dto, OperateSideEnum sideEnum);

    List<CoOrder> createOrders(List<CoOrder> coOrderList);
}

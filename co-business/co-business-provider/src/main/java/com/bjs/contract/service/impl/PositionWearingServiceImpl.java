package com.bjs.contract.service.impl;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bijinsuo.common.constants.PrecisionConstant;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisCacheUtil;
import com.bijinsuo.common.redis.utils.RedisLockUtil;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bijinsuo.common.utils.PositionUtil;
import com.bijinsuo.common.utils.entity.OrderCancelResultDTO;
import com.bijinsuo.common.utils.entity.PositionLiquidationDTO;
import com.bijinsuo.common.utils.enums.*;
import com.bjs.contract.AccountSymbolConstant;
import com.bjs.contract.action.AccountAction;
import com.bjs.contract.action.ContractConfigAction;
import com.bjs.contract.entity.CoOrder;
import com.bjs.contract.entity.CoPositionOrder;
import com.bjs.contract.entity.CoTrade;
import com.bjs.contract.entity.Settlement;
import com.bjs.contract.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

/**
 * @author Winter
 */
@Slf4j
@Service
public class PositionWearingServiceImpl implements PositionWearingService {
  private static final long ROBOT_ID = 0L;
  @Autowired
  private CoOrderService orderService;
  @Autowired
  private CoPositionOrderService positionService;
  @Autowired
  private ContractConfigAction configAction;
  @Autowired
  private AccountAction accountAction;
  @Autowired
  private CoTradeService coTradeService;
  @Autowired
  private SettlementService settlementService;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void wear(PositionLiquidationDTO dto) {
    // ????????????
    var locked = false;
    while (!locked) {
      locked = RedisLockUtil.instance().tryLock(RedisCacheUtil.getUserLockKey(dto.getUid()), 1, 1, TimeUnit.MINUTES);
    }
    // ???????????????
    if (!cancelOrders(dto.getUid(), null, null)) {
      log.error("????????????????????????????????????????????????uid={}", dto.getUid());
      RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
      return;
    }
    // ?????????????????????????????????????????????
    final var positions = positionService.list(Wrappers.lambdaQuery(CoPositionOrder.class).eq(CoPositionOrder::getUid, dto.getUid()));
    if (!CollectionUtils.isEmpty(positions)) {
      positions.forEach(p -> doWearPosition(p, dto.getSymbolPriceMap().get(p.getSymbol())));
    }
    //??????????????????
    accountAction.operateAccount(dto.getUid(),
        BigDecimal.ZERO,
        AccountSymbolConstant.USDT,
        TransactionSceneEnum.WEAR_POSITIONS.getValue(),
        "user",
        dto.getUid(),
        BigDecimal.ZERO,
        BigDecimal.ZERO,
        BigDecimal.ZERO);
    RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
  }

  private boolean preOperation(PositionLiquidationDTO dto) {
    // ????????????
    var locked = false;
    while (!locked) {
      locked = RedisLockUtil.instance().tryLock(RedisCacheUtil.getUserLockKey(dto.getUid()), 1, 1, TimeUnit.MINUTES);
    }
    // ???????????????
    if (!cancelOrders(dto.getUid(), null, null)) {
      RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
      return false;
    }
    // ????????????????????????
    final var positions = positionService.list(Wrappers.lambdaQuery(CoPositionOrder.class).eq(CoPositionOrder::getUid, dto.getUid()));
    if (CollectionUtils.isEmpty(positions)) {
      RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
      return false;
    }
    return true;
  }

  /**
   * ??????????????????
   */
  private void doWearPosition(CoPositionOrder position, BigDecimal price) {
    final var config = configAction.getBySymbol(position.getSymbol());
    // ??????????????????????????????????????????????????????
    if (hasLostMoney(position, price)) {
      // ????????????????????????
      // TODO: 2022/11/26 ?????????????????????????????????
      final var underweightLimit = 10000.;
      final var monetizedUsers = RedisUtil.instance().zRangeByScore(RedisCacheKey.POSITION_SORT_PNL_KEY, underweightLimit, Double.MAX_VALUE);
      // ?????????????????????????????????????????????
      if (monetizedUsers != null) {
        for (Object uid : monetizedUsers) {
          // ??????????????????????????????????????????
          final var counterPosition = positionService.getByUidAndStatusAndSide(((Integer) uid).longValue(),
              position.getSymbol(),
              PositionOrderStatusEnum.position,
              PositionSideEnum.valueOf(position.getPositionSide().toUpperCase()).opposite());
          if (counterPosition == null) {
            continue;
          }
          // ???????????????????????????????????????
          if (!cancelOrders(counterPosition.getUid(), position.getSymbol(), null)) {
            // ?????????????????????????????????????????????
            continue;
          }
          // ??????????????????
          if (matchInDB(Pair.of(position, counterPosition), config, price)) {
            break;
          }
        }
      }
    }
    // ?????????????????????????????????????????????????????????????????????????????????
    if (position.getDealBase().compareTo(BigDecimal.ZERO) > 0) {
      matchInDB(Pair.of(position, null), config, price);
    }
  }

  private boolean hasLostMoney(CoPositionOrder position, BigDecimal price) {
    var hasLost = true;
    if (position.getPositionSide().equalsIgnoreCase(PositionSideEnum.BUY.name())) {
      if (position.getAvgPrice().subtract(price).compareTo(BigDecimal.ZERO) < 0) {
        hasLost = false;
      }
    } else {
      if (position.getAvgPrice().subtract(price).compareTo(BigDecimal.ZERO) > 0) {
        hasLost = false;
      }
    }
    return hasLost;
  }

  /**
   * ???????????????
   */
  private boolean cancelOrders(long uid, String symbol, String side) {
    final var results = orderService.cancelUserOrders(uid, symbol, side, 0);
    if (CollectionUtils.isEmpty(results)) {
      return true;
    }
    return results.stream().anyMatch(Predicate.not(OrderCancelResultDTO::isStatus));
  }

  /**
   * ??????????????????????????????????????????????????????????????????????????????self??????????????????????????????self???????????????????????????????????????????????????self????????????????????????
   * ????????????counter????????????????????????????????????????????????
   */
  private boolean matchInDB(Pair<CoPositionOrder, CoPositionOrder> positionPair, ContractConfigDTO config, BigDecimal price) {
    // ???????????????????????????????????????????????????
    final var selfHoldPrice = positionPair.getLeft().getAvgPrice();
    BigDecimal counterHoldPrice = BigDecimal.ZERO;
    if (positionPair.getRight() != null) {
      counterHoldPrice = positionPair.getRight().getAvgPrice();
    }
    // ???????????????
    var volume = positionPair.getLeft().getDealBase();
    if (positionPair.getRight() != null && positionPair.getRight().getDealBase().compareTo(volume) < 0) {
      volume = positionPair.getRight().getDealBase();
    }
    // ??????????????????
    final var orderPair = createAndSaveOrderPair(positionPair, config, price, volume);
    // ??????????????????
    updatePosition(positionPair.getLeft(), orderPair.getLeft(), config);
    final var counterMarginDelta = updatePosition(positionPair.getRight(), orderPair.getRight(), config);
    // ??????????????????
    final var tradePair = createAndSaveTradePair(positionPair, orderPair);
    // ??????
    settlement(positionPair.getLeft(), orderPair.getLeft(), tradePair.getLeft(), selfHoldPrice);
    final var counterSettlement = settlement(positionPair.getRight(), orderPair.getRight(), tradePair.getRight(), counterHoldPrice);
    // ??????????????????????????????
    if (positionPair.getRight() != null) {
      accountAction.operateAccount(positionPair.getRight().getUid(),
          counterMarginDelta,
          config.getQuote(),
          TransactionSceneEnum.CLOSE_POSITION.getValue(),
          "co_position_order",
          positionPair.getRight().getId(),
          counterSettlement.getProfit(),
          BigDecimal.ZERO,
          counterSettlement.getFee());
    }
    // ????????????????????????
    return positionPair.getLeft().getDealBase().compareTo(BigDecimal.ZERO) == 0;
  }

  private Settlement settlement(CoPositionOrder position, CoOrder order, CoTrade trade, BigDecimal oldPrice) {
    Date now = new Date();
    Settlement settlement = new Settlement();
    settlement.setSymbol(order.getSymbol());
    settlement.setType(order.getType());
    settlement.setUid(order.getUid());
    settlement.setCoverOrderId(order.getId());
    settlement.setPositionOrderId(ROBOT_ID);
    if (position != null) {
      settlement.setPositionOrderId(position.getId());
    }
    settlement.setSettleBase(order.getDealBase());
    settlement.setSettleQuote(order.getDealQuote());
    settlement.setSettleAvgPrice(order.getAvgPrice());
    settlement.setFee(trade.getFee());
    BigDecimal priceDelta = BigDecimal.ZERO;
    if (position != null) {
      if (PositionSideEnum.BUY.name().equalsIgnoreCase(position.getPositionSide())) {
        priceDelta = trade.getPrice().subtract(oldPrice);
      } else {
        priceDelta = oldPrice.subtract(trade.getPrice());
      }
    }
    settlement.setProfit(priceDelta.multiply(order.getVolumeBase()).setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.DOWN));
    settlement.setCtime(now);
    settlement.setMtime(now);
    settlementService.save(settlement);
    return settlement;
  }

  private BigDecimal updatePosition(CoPositionOrder position, CoOrder order, ContractConfigDTO config) {
    // ????????????????????????????????????
    if (position == null) return BigDecimal.ZERO;
    var deltaMargin = order.getVolumeBase()
        .divide(position.getDealBase(), config.getPricePrecision(), RoundingMode.HALF_UP)
        .multiply(position.getMargin()).setScale(config.getPricePrecision(), RoundingMode.HALF_UP);
    if (position.getDealBase().compareTo(order.getDealBase()) <= 0) {
      positionService.getBaseMapper().deleteById(position.getId());
      deltaMargin = position.getMargin();
      // positionService.updatePositionCache????????????redis?????????margin???0?????????
      position.setMargin(BigDecimal.ZERO);
    } else {
      position.setMargin(position.getMargin().subtract(deltaMargin));
      position.setNominalValue(position.getNominalValue().subtract(order.getVolumeQuote()));
      position.setDealBase(position.getDealBase().subtract(order.getDealBase()));
      position.setDealQuote(position.getDealQuote().subtract(order.getDealQuote()));
      position.setAvgPrice(position.getDealQuote().divide(position.getDealBase(), config.getPricePrecision(), RoundingMode.HALF_UP));
      position.setMtime(new Date());
      positionService.calculateAndSetPositionProperties(position, order, config.getQuotePrecision());
    }
    positionService.updatePositionCacheAndSendMq(position);
    return deltaMargin;
  }


  private Pair<CoTrade, CoTrade> createAndSaveTradePair(Pair<CoPositionOrder, CoPositionOrder> positionPair, Pair<CoOrder, CoOrder> orderPair) {
    final var tn = UUID.randomUUID().toString();
    return Pair.of(createAndSaveTrade(tn, positionPair.getLeft(), orderPair.getLeft(), true), createAndSaveTrade(tn, positionPair.getRight(), orderPair.getRight(), false));
  }

  private CoTrade createAndSaveTrade(String tn, CoPositionOrder position, CoOrder order, boolean isTaker) {
    // ?????????????????????????????????
    final var trade = new CoTrade();
    trade.setTradeNo(tn);
    trade.setSymbol(order.getSymbol());
    trade.setOrderId(order.getId());
    trade.setPositionId(ROBOT_ID);
    if (position != null) {
      trade.setPositionId(position.getId());
    }
    trade.setUid(order.getUid());
    //??????
    trade.setOperateType(order.getOperateType());
    trade.setOperateSide(order.getOperateSide());
    var side = OperateSideEnum.valueOf(order.getOperateSide());
    if (isTaker) {
      side = side.opponent();
    }
    trade.setTrendSide(side.name());
    //??????
    trade.setVolumeBase(order.getVolumeBase());
    trade.setVolumeQuote(order.getVolumeQuote());
    trade.setPrice(order.getPrice());
    BigDecimal fee;
    if (isTaker) {
      fee = NumberUtil.max(order.getMinTakerFee(), order.getCloseTakerFeeRate().multiply(order.getVolumeQuote()).setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.DOWN));
    } else {
      fee = NumberUtil.max(order.getMinMakerFee(), order.getCloseMakerFeeRate().multiply(order.getVolumeQuote()).setScale(PrecisionConstant.ACCOUNT_PRECISION, RoundingMode.DOWN));
    }
    trade.setFee(fee);
    coTradeService.save(trade);
    return trade;
  }

  private Pair<CoOrder, CoOrder> createAndSaveOrderPair(Pair<CoPositionOrder, CoPositionOrder> positionPair,
                                                        ContractConfigDTO config,
                                                        BigDecimal price,
                                                        BigDecimal volume) {
    final CoOrder baseOrder = createBaseOrder(config, price, volume);

    final var selfOrder = new CoOrder();
    BeanUtils.copyProperties(baseOrder, selfOrder);
    selfOrder.setUid(positionPair.getLeft().getUid());
    selfOrder.setOperateSide(PositionUtil.getSide(OperateTypeEnum.CLOSE, OperateSideEnum.valueOf(positionPair.getLeft().getPositionSide().toUpperCase())).name());
    orderService.save(selfOrder);

    final var counterOrder = new CoOrder();
    BeanUtils.copyProperties(baseOrder, counterOrder);
    counterOrder.setOperateSide(positionPair.getLeft().getPositionSide());
    if (positionPair.getRight() != null) {
      counterOrder.setUid(positionPair.getRight().getUid());
      orderService.save(counterOrder);
    } else { // ??????????????????????????????
      counterOrder.setId(ROBOT_ID);
      counterOrder.setUid(ROBOT_ID);
    }
    return Pair.of(selfOrder, counterOrder);
  }

  private CoOrder createBaseOrder(ContractConfigDTO config, BigDecimal price, BigDecimal volume) {
    final var baseOrder = new CoOrder();
    BeanUtils.copyProperties(config, baseOrder);
    baseOrder.setId(null);
    baseOrder.setSymbol(config.getSymbol());
    baseOrder.setPositionType(PositionTypeEnum.CROSS_MARGIN.getValue());
    baseOrder.setOperateType(OperateTypeEnum.CLOSE.name());
    baseOrder.setMatchType(OrderTypeEnum.WEARING.getMatchType());
    baseOrder.setType(OrderTypeEnum.WEARING.getCode());
    baseOrder.setLeverageLevel(1);
    baseOrder.setPrice(price);
    baseOrder.setVolumeBase(volume);
    baseOrder.setVolumeQuote(price.multiply(volume));
    baseOrder.setIp("0.0.0.0");
    baseOrder.setSource(OrderSourceEnum.SYS.value);
    baseOrder.setDealBase(volume);
    baseOrder.setAvgPrice(price);
    baseOrder.setDealQuote(baseOrder.getVolumeQuote());
    baseOrder.setCtime(new Date());
    baseOrder.setMtime(new Date());
    baseOrder.setLockPotentialFee(BigDecimal.ZERO);
    baseOrder.setStatus(OrderStatus.FILLED.value);
    return baseOrder;
  }


  @Override
  public void liquidation(PositionLiquidationDTO dto) {
    if (this.preOperation(dto)) {
      try {
        this.doLiquidation(dto);
      } catch (Exception e) {
        throw e;
      } finally {
        RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
      }
    }
  }

  private void doLiquidation(PositionLiquidationDTO dto) {
    // ??????
    positionService.liquidation(dto);
  }
}

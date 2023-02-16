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
    // 锁定用户
    var locked = false;
    while (!locked) {
      locked = RedisLockUtil.instance().tryLock(RedisCacheUtil.getUserLockKey(dto.getUid()), 1, 1, TimeUnit.MINUTES);
    }
    // 撤销委托单
    if (!cancelOrders(dto.getUid(), null, null)) {
      log.error("穿仓处理失败（撤销委托单失败）：uid={}", dto.getUid());
      RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
      return;
    }
    // 查询用户所有仓位，逐个进行穿仓
    final var positions = positionService.list(Wrappers.lambdaQuery(CoPositionOrder.class).eq(CoPositionOrder::getUid, dto.getUid()));
    if (!CollectionUtils.isEmpty(positions)) {
      positions.forEach(p -> doWearPosition(p, dto.getSymbolPriceMap().get(p.getSymbol())));
    }
    //更新账户余额
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
    // 锁定用户
    var locked = false;
    while (!locked) {
      locked = RedisLockUtil.instance().tryLock(RedisCacheUtil.getUserLockKey(dto.getUid()), 1, 1, TimeUnit.MINUTES);
    }
    // 撤销委托单
    if (!cancelOrders(dto.getUid(), null, null)) {
      RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
      return false;
    }
    // 查询用户所有仓位
    final var positions = positionService.list(Wrappers.lambdaQuery(CoPositionOrder.class).eq(CoPositionOrder::getUid, dto.getUid()));
    if (CollectionUtils.isEmpty(positions)) {
      RedisLockUtil.instance().unLock(RedisCacheUtil.getUserLockKey(dto.getUid()));
      return false;
    }
    return true;
  }

  /**
   * 穿仓一个仓位
   */
  private void doWearPosition(CoPositionOrder position, BigDecimal price) {
    final var config = configAction.getBySymbol(position.getSymbol());
    // 如果仓位有亏损，那么要找对手他们减仓
    if (hasLostMoney(position, price)) {
      // 查询最高盈利用户
      // TODO: 2022/11/26 最低盈利量暂时这么设置
      final var underweightLimit = 10000.;
      final var monetizedUsers = RedisUtil.instance().zRangeByScore(RedisCacheKey.POSITION_SORT_PNL_KEY, underweightLimit, Double.MAX_VALUE);
      // 遍历以上用户，找到对手进行撮合
      if (monetizedUsers != null) {
        for (Object uid : monetizedUsers) {
          // 查询盈利用户是否持有对手仓位
          final var counterPosition = positionService.getByUidAndStatusAndSide(((Integer) uid).longValue(),
              position.getSymbol(),
              PositionOrderStatusEnum.position,
              PositionSideEnum.valueOf(position.getPositionSide().toUpperCase()).opposite());
          if (counterPosition == null) {
            continue;
          }
          // 撤销会导致仓位变化的委托单
          if (!cancelOrders(counterPosition.getUid(), position.getSymbol(), null)) {
            // 这个对手撤单失败，找下一个对手
            continue;
          }
          // 按穿仓价撮合
          if (matchInDB(Pair.of(position, counterPosition), config, price)) {
            break;
          }
        }
      }
    }
    // 真实用户遍历完毕，如果仓位仍有剩余，则与机器人进行撮合
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
   * 撤销委托单
   */
  private boolean cancelOrders(long uid, String symbol, String side) {
    final var results = orderService.cancelUserOrders(uid, symbol, side, 0);
    if (CollectionUtils.isEmpty(results)) {
      return true;
    }
    return results.stream().anyMatch(Predicate.not(OrderCancelResultDTO::isStatus));
  }

  /**
   * 将两个币对和方向相同的持仓按指定价格强行撮合，尽量让self仓位归零，方法结束后self仓位会更新为撮合后的剩余仓位，返回self仓位是否撮合完成
   * 当不传入counter仓位时，会创建机器人订单进行撮合
   */
  private boolean matchInDB(Pair<CoPositionOrder, CoPositionOrder> positionPair, ContractConfigDTO config, BigDecimal price) {
    // 保存初始持仓均价，用于稍后计算盈利
    final var selfHoldPrice = positionPair.getLeft().getAvgPrice();
    BigDecimal counterHoldPrice = BigDecimal.ZERO;
    if (positionPair.getRight() != null) {
      counterHoldPrice = positionPair.getRight().getAvgPrice();
    }
    // 计算平仓量
    var volume = positionPair.getLeft().getDealBase();
    if (positionPair.getRight() != null && positionPair.getRight().getDealBase().compareTo(volume) < 0) {
      volume = positionPair.getRight().getDealBase();
    }
    // 生成两个订单
    final var orderPair = createAndSaveOrderPair(positionPair, config, price, volume);
    // 更新持仓信息
    updatePosition(positionPair.getLeft(), orderPair.getLeft(), config);
    final var counterMarginDelta = updatePosition(positionPair.getRight(), orderPair.getRight(), config);
    // 生成交易信息
    final var tradePair = createAndSaveTradePair(positionPair, orderPair);
    // 结算
    settlement(positionPair.getLeft(), orderPair.getLeft(), tradePair.getLeft(), selfHoldPrice);
    final var counterSettlement = settlement(positionPair.getRight(), orderPair.getRight(), tradePair.getRight(), counterHoldPrice);
    // 对手用户需要操作账户
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
    // 返回穿仓是否完成
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
    // 机器人没有仓位，不用修改
    if (position == null) return BigDecimal.ZERO;
    var deltaMargin = order.getVolumeBase()
        .divide(position.getDealBase(), config.getPricePrecision(), RoundingMode.HALF_UP)
        .multiply(position.getMargin()).setScale(config.getPricePrecision(), RoundingMode.HALF_UP);
    if (position.getDealBase().compareTo(order.getDealBase()) <= 0) {
      positionService.getBaseMapper().deleteById(position.getId());
      deltaMargin = position.getMargin();
      // positionService.updatePositionCache方法会在redis中删除margin为0的仓位
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
    // 机器人不用写入交易信息
    final var trade = new CoTrade();
    trade.setTradeNo(tn);
    trade.setSymbol(order.getSymbol());
    trade.setOrderId(order.getId());
    trade.setPositionId(ROBOT_ID);
    if (position != null) {
      trade.setPositionId(position.getId());
    }
    trade.setUid(order.getUid());
    //方向
    trade.setOperateType(order.getOperateType());
    trade.setOperateSide(order.getOperateSide());
    var side = OperateSideEnum.valueOf(order.getOperateSide());
    if (isTaker) {
      side = side.opponent();
    }
    trade.setTrendSide(side.name());
    //金额
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
    } else { // 机器人订单的特殊设置
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
    // 爆仓
    positionService.liquidation(dto);
  }
}

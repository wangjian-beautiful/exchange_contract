package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.utils.enums.*;
import com.bjs.contract.action.OrderAction;
import com.bjs.contract.entity.TriggerOrder;
import com.bjs.contract.mapper.TriggerMapper;
import com.bjs.contract.service.ConditionTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author Winter
 */
@Slf4j
@Service
public class ConditionTriggerServiceImpl extends ServiceImpl<TriggerMapper, TriggerOrder> implements ConditionTriggerService {
  @Autowired
  private OrderAction orderAction;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void scanTriggerOrders(String symbol, BigDecimal oldPrice, BigDecimal newPrice) {
    // 查找有可能触发的条件订单
    List<TriggerOrder> validTriggerOrders = getTriggerOrders(symbol, oldPrice, newPrice);
    // 保存触发了条件的订单
    List<TriggerOrder> triggeredOrders = new ArrayList<>(validTriggerOrders.size());
    for (TriggerOrder trigger : validTriggerOrders) {
      if (!shouldTrigger(trigger, newPrice)) {
        continue;
      }
      triggeredOrders.add(trigger);
      CoOrderDTO order = createCoOrderDTO(trigger);
      try {
        CoOrderDTO createdOrder;
        if (OperateTypeEnum.OPEN.name().equalsIgnoreCase(trigger.getOperateType())) {
          createdOrder = orderAction.create(order);
        } else {
          createdOrder = orderAction.close(order);
        }
        trigger.setOrderId(createdOrder.getId());
        trigger.setTriggerTime(new Date());
        trigger.setMtime(new Date());
        trigger.setStatus(TriggerStatusEnum.FINISH.getCode());
      } catch (Exception e) {
        log.error("触发条件订单({})失败：", trigger.getId(), e);
        trigger.setStatus(TriggerStatusEnum.TRIGGER_FAILED.getCode());
      }
    }
    updateBatchById(triggeredOrders);
  }

  private List<TriggerOrder> getTriggerOrders(String symbol, BigDecimal oldPrice, BigDecimal newPrice) {
    BigDecimal lowerPrice = oldPrice;
    BigDecimal upperPrice = oldPrice;
    if (oldPrice.compareTo(newPrice) < 0) {
      upperPrice = newPrice;
    } else {
      lowerPrice = newPrice;
    }
    List<TriggerOrder> validTriggerOrders = getBaseMapper().selectList(
        Wrappers.lambdaQuery(TriggerOrder.class)
            .eq(TriggerOrder::getSymbol, symbol)
            .eq(TriggerOrder::getStatus, TriggerStatusEnum.ACTIVE.getCode())
            .ge(TriggerOrder::getTriggerPrice, lowerPrice)
            .le(TriggerOrder::getTriggerPrice, upperPrice));
    if (validTriggerOrders == null) {
      return Collections.emptyList();
    }
    return validTriggerOrders;
  }

  private CoOrderDTO createCoOrderDTO(TriggerOrder trigger) {
    CoOrderDTO order = new CoOrderDTO();
    BeanUtils.copyProperties(trigger, order);
    order.setTriggerOrderId(trigger.getId());
    order.setOperateType(trigger.getOperateType());
    order.setOperateSide(trigger.getOperateSide());
    order.setVolumeBase(trigger.getVolumeBase());
    order.setVolumeQuote(trigger.getVolumeQuote());
    order.setStatus(OrderStatus.INIT.value);
    order.setIp("0.0.0.0");
    order.setSource(OrderSourceEnum.SYS.value);
    order.setCtime(new Date());
    order.setMtime(new Date());
    return order;
  }

  private boolean shouldTrigger(TriggerOrder trigger, BigDecimal price) {
    if (Objects.equals(trigger.getTriggerType(), TriggerTypeEnum.STOP_LOSS.getCode())) { // 条件止损单
      if (OperateSideEnum.SELL.name().equalsIgnoreCase(trigger.getOperateSide())) {
        return price.compareTo(trigger.getTriggerPrice()) <= 0;
      } else {
        return price.compareTo(trigger.getTriggerPrice()) >= 0;
      }
    } else if (Objects.equals(trigger.getTriggerType(), TriggerTypeEnum.TAKE_PROFIT.getCode())) { // 条件止盈单
      if (OperateSideEnum.SELL.name().equalsIgnoreCase(trigger.getOperateSide())) {
        return price.compareTo(trigger.getTriggerPrice()) >= 0;
      } else {
        return price.compareTo(trigger.getTriggerPrice()) <= 0;
      }
    } else { // 条件开仓/平仓单具有相同的触发条件
      if (trigger.getCurrentMarketPrice().compareTo(trigger.getTriggerPrice()) > 0) {
        return price.compareTo(trigger.getTriggerPrice()) >= 0;
      } else {
        return price.compareTo(trigger.getTriggerPrice()) <= 0;
      }
    }
  }
}

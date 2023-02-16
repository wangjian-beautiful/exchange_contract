package com.bjs.contract.service;

import java.math.BigDecimal;

/**
 * @author Winter
 */
public interface ConditionTriggerService {
  void scanTriggerOrders(String symbol, BigDecimal oldPrice, BigDecimal newPrice);
}

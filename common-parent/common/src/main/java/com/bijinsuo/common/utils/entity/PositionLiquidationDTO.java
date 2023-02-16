package com.bijinsuo.common.utils.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Winter
 */
@Data
public class PositionLiquidationDTO {
  /**
   * 用户的id
   */
  private long uid;
  /**
   * 用户的浮动盈亏
   */
  private BigDecimal floatingProfit;
  /**
   * 爆/穿仓类型
   */
  private Type type;
  /**
   * 币对：穿仓价
   */
  private Map<String, BigDecimal> symbolPriceMap;

  public enum Type {
    LIQUIDATION, WEARING
  }
}
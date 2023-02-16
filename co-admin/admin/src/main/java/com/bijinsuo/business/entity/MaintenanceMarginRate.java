package com.bijinsuo.business.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bijinsuo.common.annotation.Excel;
import com.bijinsuo.common.core.domain.BaseEntity;

/**
 * 维持保证金率对象 maintenance_margin_rate
 *
 * @author ruoyi
 * @date 2022-11-12
 */
public class MaintenanceMarginRate extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  private Long id;

  /**
   * 交易对
   */
  @Excel(name = "交易对")
  private String symbol;

  /**
   * 层级
   */
  @Excel(name = "层级")
  private Long tier;

  /**
   * 持仓 USDT名义价值 起始   左闭右开
   */
  @Excel(name = "持仓 USDT名义价值 起始   左闭右开")
  private BigDecimal notionalValueBegin;

  /**
   * 持仓 USDT名义价值 结束   左闭右开
   */
  @Excel(name = "持仓 USDT名义价值 结束   左闭右开")
  private BigDecimal notionalValueEnd;

  /**
   * 可使用最高杠杆倍数
   */
  @Excel(name = "可使用最高杠杆倍数")
  private Long maxLeverage;

  /**
   * 维持保证金比率 例子:0.0040
   */
  @Excel(name = "维持保证金比率 例子:0.0040")
  private BigDecimal maintenanceMarginRate;

  /**
   * 维持保证金速算额
   */
  @Excel(name = "维持保证金速算额")
  private BigDecimal maintenanceAmount;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setTier(Long tier) {
    this.tier = tier;
  }

  public Long getTier() {
    return tier;
  }

  public void setNotionalValueBegin(BigDecimal notionalValueBegin) {
    this.notionalValueBegin = notionalValueBegin;
  }

  public BigDecimal getNotionalValueBegin() {
    return notionalValueBegin;
  }

  public void setNotionalValueEnd(BigDecimal notionalValueEnd) {
    this.notionalValueEnd = notionalValueEnd;
  }

  public BigDecimal getNotionalValueEnd() {
    return notionalValueEnd;
  }

  public void setMaxLeverage(Long maxLeverage) {
    this.maxLeverage = maxLeverage;
  }

  public Long getMaxLeverage() {
    return maxLeverage;
  }

  public void setMaintenanceMarginRate(BigDecimal maintenanceMarginRate) {
    this.maintenanceMarginRate = maintenanceMarginRate;
  }

  public BigDecimal getMaintenanceMarginRate() {
    return maintenanceMarginRate;
  }

  public void setMaintenanceAmount(BigDecimal maintenanceAmount) {
    this.maintenanceAmount = maintenanceAmount;
  }

  public BigDecimal getMaintenanceAmount() {
    return maintenanceAmount;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", getId())
        .append("symbol", getSymbol())
        .append("tier", getTier())
        .append("notionalValueBegin", getNotionalValueBegin())
        .append("notionalValueEnd", getNotionalValueEnd())
        .append("maxLeverage", getMaxLeverage())
        .append("maintenanceMarginRate", getMaintenanceMarginRate())
        .append("maintenanceAmount", getMaintenanceAmount())
        .toString();
  }
}

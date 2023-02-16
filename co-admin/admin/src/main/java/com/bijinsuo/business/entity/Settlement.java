package com.bijinsuo.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.bijinsuo.business.util.jackson.serialize.EnumSerializer;
import com.bijinsuo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 【请填写功能名称】对象 settlement
 *
 * @author ruoyi
 * @date 2022-11-11
 */
@Data
public class Settlement extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * $column.columnComment
   */
  private Long id;

  /**
   * 交易对
   */
  private String symbol;

  /**
   * 用户id
   */
  private Long uid;

  /**
   * 平仓委托订单id
   */
  private Long coverOrderId;

  /**
   * 持仓订单id
   */
  private Long positionOrderId;

  /**
   * 结算数量
   */
  private BigDecimal settleBase;

  /**
   * 结算金额
   */
  private BigDecimal settleQuote;

  /**
   * 结算均价
   */
  private BigDecimal settleAvgPrice;

  /**
   * 创建时间
   */
  private Date ctime;

  /**
   * 更新时间
   */
  private Date mtime;

  /**
   * 结算盈亏
   */
  private BigDecimal profit;

  /**
   * 结算手续费
   */
  private BigDecimal fee;
  private Date startTime;
  private Date endTime;


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", getId())
        .append("symbol", getSymbol())
        .append("uid", getUid())
        .append("coverOrderId", getCoverOrderId())
        .append("positionOrderId", getPositionOrderId())
        .append("settleBase", getSettleBase())
        .append("settleQuote", getSettleQuote())
        .append("settleAvgPrice", getSettleAvgPrice())
        .append("ctime", getCtime())
        .append("mtime", getMtime())
        .append("profit", getProfit())
        .append("fee", getFee())
        .toString();
  }
}

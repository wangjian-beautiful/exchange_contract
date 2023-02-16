package com.bijinsuo.business.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.bijinsuo.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 co_trade
 *
 * @author ruoyi
 * @date 2022-11-10
 */
@Data
public class Trade extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 自增主键
   */
  private Long id;

  /**
   * 交易对
   */
  private String symbol;

  /**
   * 撮合队编号
   */
  private String tradeNo;

  /**
   * 用户uid
   */
  private Long uid;

  /**
   * 持仓订单id
   */
  private Long positionId;

  /**
   * 委托订单id
   */
  private Long orderId;

  /**
   * 开平仓方向(open 开仓，close 平仓)
   */
  private Order.OperateSide positionSide;

  /**
   * 买卖方向（buy 买入，sell 卖出）操作方向
   */
  private Order.OperateSide operateSide;

  /**
   * 成交价格
   */
  private BigDecimal price;

  /**
   * 成交数量 base
   */
  private BigDecimal volumeBase;

  /**
   * 成交数量 quote  价格 * volume_base
   */
  private BigDecimal volumeQuote;

  /**
   * 主动单方向
   */
  private String trendSide;

  /**
   * 手续费
   */
  private BigDecimal fee;

  /**
   * 创建时间
   */
  private Date ctime;

  /**
   * 更新时间
   */
  private Date mtime;
  private Date startTime;
  private Date endTime;


  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", getId())
        .append("symbol", getSymbol())
        .append("tradeNo", getTradeNo())
        .append("uid", getUid())
        .append("positionId", getPositionId())
        .append("orderId", getOrderId())
        .append("positionSide", getPositionSide())
        .append("operateSide", getOperateSide())
        .append("price", getPrice())
        .append("volumeBase", getVolumeBase())
        .append("volumeQuote", getVolumeQuote())
        .append("trendSide", getTrendSide())
        .append("fee", getFee())
        .append("ctime", getCtime())
        .append("mtime", getMtime())
        .toString();
  }
}

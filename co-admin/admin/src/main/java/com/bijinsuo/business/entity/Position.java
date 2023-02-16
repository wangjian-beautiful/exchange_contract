package com.bijinsuo.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.bijinsuo.business.util.jackson.serialize.EnumSerializer;
import com.bijinsuo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 【请填写功能名称】对象 co_position_order
 *
 * @author ruoyi
 * @date 2022-11-11
 */
@Data
public class Position extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
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
   * 持仓类型(1 全仓，2 仓逐)
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Order.PositionType positionType;

  /**
   * 买卖方向（buy 买入，sell 卖出）
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Order.OperateSide positionSide;

  /**
   * 杠杆倍数
   */
  private Long leverageLevel;

  /**
   * 持仓合约名义价值（开仓时价值）
   */
  private BigDecimal nominalValue;

  /**
   * 保证金
   */
  private BigDecimal margin;

  /**
   * 维持保证金
   */
  private BigDecimal maintenanceMargin;
  /**
   * 全部平仓手续费（也就是爆仓手续费:采用taker手续费率计算）
   */
  private BigDecimal closeFee;

  /**
   * 维持保证金+全部平仓手续费（计算保证金率取这个值直接计算）
   */
  private BigDecimal marginRatioMolecule;
  /**
   * 持仓数量 base
   */
  private BigDecimal dealBase;
  /**
   * 平仓冻结的持仓数量 base
   */
  private BigDecimal frozenBase;
  /**
   * 成交均价
   */
  private BigDecimal avgPrice;

  /**
   * 撮合成交的数额 usdt（不等于实时的数额）
   */
  private BigDecimal dealQuote;

  /**
   * 订单状态（订单状态：0：position 1:Settled）
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Status status;

  /**
   * 订单来源ip
   */
  private String ip;

  /**
   * 订单来源（订单来源：1web，2app，3api，4其它）
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Order.Source source;

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

  public enum Status {
    POSITION(0, "POSITION"), SETTLED(1, "SETTLED");
    public final int tag;
    public final String desc;

    Status(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static Status from(int tag) {
      for (Status status : Status.values()) {
        if (Objects.equals(status.tag, tag)) {
          return status;
        }
      }
      return null;
    }

    public static Status from(String desc) {
      for (Status status : Status.values()) {
        if (Objects.equals(status.desc, desc)) {
          return status;
        }
      }
      return null;
    }
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
        .append("id", getId())
        .append("symbol", getSymbol())
        .append("uid", getUid())
        .append("positionType", getPositionType())
        .append("positionSide", getPositionSide())
        .append("leverageLevel", getLeverageLevel())
        .append("nominalValue", getNominalValue())
        .append("margin", getMargin())
        .append("maintenanceMargin", getMaintenanceMargin())
        .append("dealBase", getDealBase())
        .append("avgPrice", getAvgPrice())
        .append("dealQuote", getDealQuote())
        .append("status", getStatus())
        .append("ip", getIp())
        .append("source", getSource())
        .append("ctime", getCtime())
        .append("mtime", getMtime())
        .toString();
  }
}

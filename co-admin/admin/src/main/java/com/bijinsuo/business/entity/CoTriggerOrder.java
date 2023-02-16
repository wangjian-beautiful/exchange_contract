package com.bijinsuo.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 【请填写功能名称】对象 co_trigger_order
 *
 * @author ruoyi
 * @date 2022-11-10
 */
@Data
public class CoTriggerOrder extends BaseEntity {
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
   * 条件单类型（1 stop loss，2 take profit）
   */
  @JsonSerialize(using = EnumSerializer.class)
  private TriggerType triggerType;

  /**
   * 触发价格
   */
  private BigDecimal triggerPrice;

  /** 下单时的市价 */
  private BigDecimal currentMarketPrice;
  /**
   * 持仓类型(1 全仓，2 逐仓)
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Order.PositionType positionType;

  /**
   * 开平仓方向(open 开仓，close 平仓)
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Order.OperateType operateType;

  /**
   * 买卖方向（buy 买入，sell 卖出）
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Order.OperateSide operateSide;

  /**
   * 杠杆倍数
   */
  private Long leverageLevel;

  /**
   * 下单价格
   */
  private BigDecimal price;

  /**
   * 下单数量
   */
  private BigDecimal volumeBase;

  /**
   * 下单数量
   */
  private BigDecimal volumeQuote;

  /**
   * 有效状态（0有效，1已过期，2已完成，3触发失败）
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Status status;

  /**
   * 订单状态备注
   */
  private Integer memo;

  /**
   * 订单过期时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date expireTime;

  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date ctime;

  /**
   * 更新时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date mtime;

  /**
   * 关联的主订单ID
   */
  private Long masterId;

  /**
   * 0 普通条件单，1 OTO类型条件单
   */
  @JsonSerialize(using = EnumSerializer.class)
  private Type type;

  /**
   * 止盈止损关联的仓位ID
   */
  private Long positionId;

  /**
   * 触发成功后的下单ID
   */
  private Long orderId;

  /**
   * 委托下单方式：1 limit（限价的时候会有价格）， 2 market
   */
  @JsonSerialize(using = EnumSerializer.class)
  private TradeType tradeType;

  /**
   * 委托为限价方式的时候，限价价格
   */
  private BigDecimal tradePrice;
  private Date startTime;
  private Date endTime;

  public enum TriggerType {
    STOP_LOSS(1, "止损"), TAKE_PROFIT(2, "止盈");
    public final int tag;
    public final String desc;

    TriggerType(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static CoTriggerOrder.TriggerType from(int tag) {
      for (CoTriggerOrder.TriggerType type : CoTriggerOrder.TriggerType.values()) {
        if (Objects.equals(type.tag, tag)) {
          return type;
        }
      }
      return null;
    }

    public static CoTriggerOrder.TriggerType from(String desc) {
      for (CoTriggerOrder.TriggerType type : CoTriggerOrder.TriggerType.values()) {
        if (Objects.equals(type.desc, desc)) {
          return type;
        }
      }
      return null;
    }
  }

  public enum Status {
    VALID(0,"有效"),EXPIRED(1,"已过期"),COMPLETE(2,"已完成"),FAIL(3,"触发失败");
    public final int tag;
    public final String desc;

    Status(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static CoTriggerOrder.Status from(int tag) {
      for (CoTriggerOrder.Status status : CoTriggerOrder.Status.values()) {
        if (Objects.equals(status.tag, tag)) {
          return status;
        }
      }
      return null;
    }

    public static CoTriggerOrder.Status from(String desc) {
      for (CoTriggerOrder.Status status : CoTriggerOrder.Status.values()) {
        if (Objects.equals(status.desc, desc)) {
          return status;
        }
      }
      return null;
    }
  }

  public enum Type {
    NORMAL(0,"普通条件单"),OTO(1,"OTO类型条件单");
    public final int tag;
    public final String desc;

    Type(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static CoTriggerOrder.Type from(int tag) {
      for (CoTriggerOrder.Type type : CoTriggerOrder.Type.values()) {
        if (Objects.equals(type.tag, tag)) {
          return type;
        }
      }
      return null;
    }

    public static CoTriggerOrder.Type from(String desc) {
      for (CoTriggerOrder.Type type : CoTriggerOrder.Type.values()) {
        if (Objects.equals(type.desc, desc)) {
          return type;
        }
      }
      return null;
    }
  }

  public enum TradeType {
    LIMIT(1,"限价"),MARKET(2,"市价");
    public final int tag;
    public final String desc;

    TradeType(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static CoTriggerOrder.TradeType from(int tag) {
      for (CoTriggerOrder.TradeType type : CoTriggerOrder.TradeType.values()) {
        if (Objects.equals(type.tag, tag)) {
          return type;
        }
      }
      return null;
    }

    public static CoTriggerOrder.TradeType from(String desc) {
      for (CoTriggerOrder.TradeType type : CoTriggerOrder.TradeType.values()) {
        if (Objects.equals(type.desc, desc)) {
          return type;
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
        .append("triggerType", getTriggerType())
        .append("triggerPrice", getTriggerPrice())
        .append("positionType", getPositionType())
        .append("open", getOperateType())
        .append("side", getOperateSide())
        .append("leverageLevel", getLeverageLevel())
        .append("price", getPrice())
        .append("volume", getVolumeBase())
        .append("status", getStatus())
        .append("memo", getMemo())
        .append("expireTime", getExpireTime())
        .append("ctime", getCtime())
        .append("mtime", getMtime())
        .append("masterId", getMasterId())
        .append("type", getType())
        .append("positionId", getPositionId())
        .append("orderId", getOrderId())
        .append("tradeType", getTradeType())
        .append("tradePrice", getTradePrice())
        .toString();
  }
}

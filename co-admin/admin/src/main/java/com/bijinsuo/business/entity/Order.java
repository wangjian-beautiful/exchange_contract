package com.bijinsuo.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.bijinsuo.business.util.jackson.serialize.EnumSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * @author Winter
 */
@Data
public class Order {
  private Long id;
  private String symbol;
  private Long triggerOrderId;
  private Long uid;
  @JsonSerialize(using = EnumSerializer.class)
  private PositionType positionType;
  @JsonSerialize(using = EnumSerializer.class)
  private OperateType operateType;
  @JsonSerialize(using = EnumSerializer.class)
  private OperateSide operateSide;
  private Integer leverageLevel;
  private BigDecimal price;
  private BigDecimal volumeBase;
  private BigDecimal volumeQuote;
  @JsonSerialize(using = EnumSerializer.class)
  private Status status;
  private String ip;
  @JsonSerialize(using = EnumSerializer.class)
  private Source source;
  private BigDecimal dealBase;
  private BigDecimal avgPrice;
  private BigDecimal dealQuote;
  private Date ctime;
  private Date startTime;
  private Date endTime;

  public enum PositionType {
    ALL_WAREHOUSE(1, "全仓"), BY_WAREHOUSE(2, "逐仓");
    public final int tag;
    public final String desc;

    PositionType(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static PositionType from(int tag) {
      if (tag == 1) {
        return ALL_WAREHOUSE;
      } else if (tag == 2) {
        return BY_WAREHOUSE;
      } else {
        return null;
      }
    }

    public static PositionType from(String desc) {
      desc = desc.toUpperCase();
      if (Objects.equals("全仓", desc)) {
        return ALL_WAREHOUSE;
      } else if (Objects.equals("逐仓", desc)) {
        return BY_WAREHOUSE;
      } else {
        return null;
      }
    }
  }

  public enum OperateType {
    OPEN, CLOSE
  }

  public enum OperateSide {
    BUY, SELL
  }

  public enum Type {
    MARKET_CHECK_SURPLUS(0, "市价止盈"),
    LIMIT_CHECK_SURPLUS(1, "限价止盈"),
    CONDITIONAL_MARKET_CHECK_SURPLUS(2, "条件市价止盈"),
    CONDITIONAL_LIMIT_CHECK_SURPLUS(3, "条件限价止盈"),
    MARKET_STOP_LOSS(4, "市价止损"),
    LIMIT_STOP_LOSS(5, "限价止损"),
    CONDITIONAL_MARKET_STOP_LOSS(6, "条件市价止损"),
    CONDITIONAL_LIMIT_STOP_LOSS(7, "条件限价止损"),
    STRONG_FLAT_WAREHOUSE(8, "强平"),
    WEAR_STOREHOUSE(9, "穿仓"),
    OPEN_BY_MARKET(10, "市价开仓"),
    OPEN_BY_LIMIT(11, "限价开仓");
    public final int tag;
    public final String desc;

    Type(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static Type from(int tag) {
      for (Type type : Type.values()) {
        if (Objects.equals(type.tag, tag)) {
          return type;
        }
      }
      return null;
    }

    public static Type from(String desc) {
      desc = desc.toUpperCase();
      for (Type type : Type.values()) {
        if (Objects.equals(type.desc, desc)) {
          return type;
        }
      }
      return null;
    }
  }

  public enum Status {
    INIT(0), NEW(1), FILLED(2), PART_FILLED(3), CANCELED(4), PENDING_CANCEL(5);
    public final int tag;

    Status(int tag) {
      this.tag = tag;
    }

    public static Status from(int tag) {
      for (Status status : Status.values()) {
        if (Objects.equals(status.tag, tag)) {
          return status;
        }
      }
      return null;
    }

    public static Status from(String name) {
      return Status.valueOf(name.toUpperCase());
    }
  }

  public enum Source {
    WEB(1), APP(2), API(3), OTHER(4);
    public final int tag;

    Source(int tag) {
      this.tag = tag;
    }

    public static Source from(int tag) {
      for (Source source : Source.values()) {
        if (Objects.equals(source.tag, tag)) {
          return source;
        }
      }
      return null;
    }

    public static Source from(String name) {
      return Source.valueOf(name.toUpperCase());
    }
  }

}

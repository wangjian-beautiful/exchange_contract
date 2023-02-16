package com.bijinsuo.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.bijinsuo.business.util.jackson.serialize.EnumSerializer;
import com.bijinsuo.common.annotation.Excel;
import com.bijinsuo.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * 合约配置对象 contract_config
 *
 * @author ruoyi
 * @date 2022-11-12
 */
@Data
public class CoConfig extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 自增主键
   */
  private Long id;

  /**
   * 合约币对
   */
  @Excel(name = "合约币对")
  private String symbol;

  /**
   * 交易对base
   */
  @Excel(name = "交易对base")
  private String base;

  /**
   * 交易对quote
   */
  @Excel(name = "交易对quote")
  private String quote;

  /**
   * 合约名称
   */
  @Excel(name = "合约名称")
  private String contractName;

  /**
   * 别名
   */
  @Excel(name = "别名")
  private String contractOtherName;

  /**
   * 资金费率最小值(不带百分比)
   */
  @Excel(name = "资金费率最小值(不带百分比)")
  private BigDecimal capitalIntervalMin;

  /**
   * 资金费率最大值(不带百分比)
   */
  @Excel(name = "资金费率最大值(不带百分比)")
  private BigDecimal capitalIntervalMax;

  /**
   * 开仓maker手续费率
   */
  @Excel(name = "开仓maker手续费率")
  private BigDecimal openMakerFeeRate;

  /**
   * 开仓taker手续费率
   */
  @Excel(name = "开仓taker手续费率")
  private BigDecimal openTakerFeeRate;

  /**
   * 平仓maker手续费率
   */
  @Excel(name = "平仓maker手续费率")
  private BigDecimal closeMakerFeeRate;

  /**
   * 平仓taker手续费率
   */
  @Excel(name = "平仓taker手续费率")
  private BigDecimal closeTakerFeeRate;

  /**
   * 最小maker手续费
   */
  @Excel(name = "最小maker手续费")
  private BigDecimal minMakerFee;

  /**
   * 最小taker手续费
   */
  @Excel(name = "最小taker手续费")
  private BigDecimal minTakerFee;

  /**
   * 保证金风险提示率 返还给前端的时候 是% 带百分号（爆仓和穿仓率 写死到代码，不做配置）
   */
  @Excel(name = "保证金风险提示率 返还给前端的时候 是% 带百分号", readConverterExp = "爆=仓和穿仓率,写=死到代码，不做配置")
  private Long riskAlarmWeak;

  /**
   * 中等级 ，触发这个级别就不能开仓，但是可以减仓,也不能划转出去，但是可以划转进来
   */
  @Excel(name = "中等级 ，触发这个级别就不能开仓，但是可以减仓,也不能划转出去，但是可以划转进来")
  private Long riskAlarmMiddle;

  /**
   * 强登记。
   */
  @Excel(name = "强登记。")
  private Long riskAlarmStrong;

  /**
   * 开仓价格 上下浮动率，只能在这个范围以内下单
   */
  @Excel(name = "开仓价格 上下浮动率，只能在这个范围以内下单")
  private BigDecimal priceLimitRate;

  /**
   * 合约状态（0：不可交易，1：可交易）
   */
  @JsonSerialize(using = EnumSerializer.class)
  @Excel(name = "合约状态", readConverterExp = "0=：不可交易，1：可交易")
  private Status status;

  /**
   * 列表显示状态（0：不显示，1：显示）这个值和status不互相冲突
   */
  @JsonSerialize(using = EnumSerializer.class)
  @Excel(name = "列表显示状态", readConverterExp = "0=：不显示，1：显示")
  private Show show;

  /**
   * 创建时间
   */
  @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
  private Date ctime;

  /**
   * 更新时间
   */
  @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
  private Date mtime;

  /**
   * 最小开仓数量
   */
  @Excel(name = "最小开仓数量")
  private BigDecimal minOpenBase;

  /**
   * 最大开仓数量
   */
  @Excel(name = "最大开仓数量")
  private BigDecimal maxOpenBase;

  /**
   * 最小开仓数额
   */
  @Excel(name = "最小开仓数额")
  private BigDecimal minOpenQuote;

  /**
   * 最大开仓数额
   */
  @Excel(name = "最大开仓数额")
  private BigDecimal maxOpenQuote;

  /**
   * 数量精度
   */
  @Excel(name = "数量精度")
  private Long basePrecision;

  /**
   * 数额精度
   */
  @Excel(name = "数额精度")
  private Long quotePrecision;

  /**
   * 价格精度
   */
  @Excel(name = "价格精度")
  private Long pricePrecision;

  public enum Status {
    INVALID(0, "不可交易"), VALID(1, "可交易");
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

  public enum Show {
    INVALID(0, "不显示"), VALID(1, "显示");
    public final int tag;
    public final String desc;

    Show(int tag, String desc) {
      this.tag = tag;
      this.desc = desc;
    }

    public static Show from(int tag) {
      for (Show show : Show.values()) {
        if (Objects.equals(show.tag, tag)) {
          return show;
        }
      }
      return null;
    }

    public static Show from(String desc) {
      for (Show show : Show.values()) {
        if (Objects.equals(show.desc, desc)) {
          return show;
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
        .append("base", getBase())
        .append("quote", getQuote())
        .append("contractName", getContractName())
        .append("contractOtherName", getContractOtherName())
        .append("capitalIntervalMin", getCapitalIntervalMin())
        .append("capitalIntervalMax", getCapitalIntervalMax())
        .append("openMakerFeeRate", getOpenMakerFeeRate())
        .append("openTakerFeeRate", getOpenTakerFeeRate())
        .append("closeMakerFeeRate", getCloseMakerFeeRate())
        .append("closeTakerFeeRate", getCloseTakerFeeRate())
        .append("minMakerFee", getMinMakerFee())
        .append("minTakerFee", getMinTakerFee())
        .append("riskAlarmWeak", getRiskAlarmWeak())
        .append("riskAlarmMiddle", getRiskAlarmMiddle())
        .append("riskAlarmStrong", getRiskAlarmStrong())
        .append("priceLimitRate", getPriceLimitRate())
        .append("status", getStatus())
        .append("show", getShow())
        .append("ctime", getCtime())
        .append("mtime", getMtime())
        .append("minOpenBase", getMinOpenBase())
        .append("maxOpenBase", getMaxOpenBase())
        .append("minOpenQuote", getMinOpenQuote())
        .append("maxOpenQuote", getMaxOpenQuote())
        .append("basePrecision", getBasePrecision())
        .append("quotePrecision", getQuotePrecision())
        .append("pricePrecision", getPricePrecision())
        .toString();
  }
}
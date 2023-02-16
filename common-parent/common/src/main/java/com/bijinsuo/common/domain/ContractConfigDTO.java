package com.bijinsuo.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合约配置表
 *
 * @author bjs code generator
 * @date 2022-11-11 17:58:42
 */
@Data
@EqualsAndHashCode
public class ContractConfigDTO {

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 合约币对
     */
    private String symbol;

    /**
     * 交易对base
     */
    private String base;

    /**
     * 交易对quote
     */
    private String quote;

    /**
     * 合约名称
     */
    private String contractName;

    /**
     * 别名
     */
    private String contractOtherName;

    /**
     * 资金费率最小值(不带百分比)
     */
    private BigDecimal capitalIntervalMin;

    /**
     * 资金费率最大值(不带百分比)
     */
    private BigDecimal capitalIntervalMax;

    /**
     * 开仓maker手续费率
     */
    private BigDecimal openMakerFeeRate;

    /**
     * 开仓taker手续费率
     */
    private BigDecimal openTakerFeeRate;

    /**
     * 平仓maker手续费率
     */
    private BigDecimal closeMakerFeeRate;

    /**
     * 平仓taker手续费率
     */
    private BigDecimal closeTakerFeeRate;

    /**
     * 最小maker手续费
     */
    private BigDecimal minMakerFee;

    /**
     * 最小taker手续费
     */
    private BigDecimal minTakerFee;

    /**
     * 保证金风险提示率 返还给前端的时候 是% 带百分号（爆仓和穿仓率 写死到代码，不做配置）
     */
    private Integer riskAlarmWeak;

    /**
     * 中等级 ，触发这个级别就不能开仓，但是可以减仓,也不能划转出去，但是可以划转进来
     */
    private Integer riskAlarmMiddle;

    /**
     * 强登记。
     */
    private Integer riskAlarmStrong;

    /**
     * 开仓价格 上下浮动率，只能在这个范围以内下单
     */
    private BigDecimal priceLimitRate;

    /**
     * 合约状态（0：不可交易，1：可交易）
     */
    private Integer status;

    /**
     * 列表显示状态（0：不显示，1：显示）这个值和status不互相冲突
     */
    private Integer show;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;

    /**
     * 最小开仓数量
     */
    private BigDecimal minOpenBase;

    /**
     * 最大开仓数量
     */
    private BigDecimal maxOpenBase;

    /**
     * 最小开仓数额
     */
    private BigDecimal minOpenQuote;

    /**
     * 最大开仓数额
     */
    private BigDecimal maxOpenQuote;

    /**
     * 数量精度
     */
    private Integer basePrecision;

    /**
     * 数额精度
     */
    private Integer quotePrecision;

    /**
     * 价格精度
     */
    private Integer pricePrecision;

}

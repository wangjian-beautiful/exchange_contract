package com.bijinsuo.common.utils.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * redis 里面 需要维护三个 数据结构
 * 1、Zset 一个是使用保证金率 排序 value uid
 * 2、Zset 一个是使用浮动盈亏 排序 value uid
 * 3、Map  一个是 uid - 持仓数据DTO(当前类)
 *
 * @author Watson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionCacheDTO {

    /**
     * 保证金率
     */
    private BigDecimal marginRate = BigDecimal.ZERO;

    /**
     * 浮动盈亏
     */
    private BigDecimal pnl = BigDecimal.ZERO;


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
     * 买卖方向（buy 买入，sell 卖出）
     */
    private String positionSide;


    /**
     * 保证金
     */
    private BigDecimal margin;


    /**
     * 维持保证金+全部平仓手续费（计算保证金率取这个值直接计算）
     */
    private BigDecimal marginRatioMolecule;


}

package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bjs code generator
 * @date 2022-11-11 17:58:51
 */
@Data
@TableName("co_position_order")
@EqualsAndHashCode
public class CoPositionOrder {


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
    private Integer positionType;


    /**
     * 买卖方向（buy 买入，sell 卖出）
     */
    private String positionSide;


    /**
     * 杠杆倍数
     */
    private Integer leverageLevel;


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
     * 已冻结的持仓数量 base
     */
    private BigDecimal frozenQuote;

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
    private Integer status;


    /**
     * 订单来源ip
     */
    private String ip;


    /**
     * 订单来源（订单来源：1web，2app，3api，4其它）
     */
    private Integer source;


    /**
     * 创建时间
     */
    private Date ctime;


    /**
     * 更新时间
     */
    private Date mtime;


}

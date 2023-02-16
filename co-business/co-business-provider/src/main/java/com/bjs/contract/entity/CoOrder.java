package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author bjs code generator
 * @date 2022-11-12 14:25:01
 */
@Data
@TableName("co_order")
@EqualsAndHashCode
public class CoOrder {


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
     * 条件委托id
     */
    private Long triggerOrderId;


    /**
     * 用户id
     */
    private Long uid;

    /**
     * 撮合 类型  0：不进撮合  1:市价 :2限价
     */
    private Integer matchType;

    /**
     * 持仓类型(1 全仓，2 仓逐)
     */
    private Integer positionType;


    /**
     * 开平仓方向(open 开仓，close 平仓)
     */
    private String operateType;


    /**
     * 买卖方向（buy 买入，sell 卖出）
     */
    private String operateSide;


    /**
     * 0: 市价止盈  1: 限价止盈 2:条件市价止盈 3条件限价止盈 4：市价止损 5限价止损 6 条件市价止盈 7条件限价止损 8强平 9穿仓 10市价开仓 11限价开仓
     */
    private Integer type;


    /**
     * 杠杆倍数
     */
    private Integer leverageLevel;


    /**
     * 下单价格
     */
    private BigDecimal price;


    /**
     * 下单数量 base
     */
    private BigDecimal volumeBase;


    /**
     * 下单数额 quote
     */
    private BigDecimal volumeQuote;


    /**
     * 订单状态（订单状态（订单状态：0 init，1 new，2 filled，3 part_filled，4 canceled，5 pending_cancel
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
     * 成交数量
     */
    private BigDecimal dealBase;


    /**
     * 成交均价
     */
    private BigDecimal avgPrice;


    /**
     * 成交数额
     */
    private BigDecimal dealQuote;


    /**
     * 创建时间
     */
    private Date ctime;


    /**
     * 更新时间
     */
    private Date mtime;


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
     * 冻结潜在的手续费
     */
    private BigDecimal lockPotentialFee;
}

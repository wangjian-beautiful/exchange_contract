package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author bjs code generator
 * @date 2022-11-21 15:22:48
 */
@Data
@TableName("co_trigger_order")
@EqualsAndHashCode
public class CoTriggerOrder {


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
     * 条件单类型（1 stop loss，2 take profit）
     */
    private Integer triggerType;



    /**
     * 触发价格
     */
    private BigDecimal triggerPrice;



    /**
     * 下单时的市价
     */
    private BigDecimal currentMarketPrice;



    /**
     * 持仓类型(1 全仓，2 逐仓)
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
     * 杠杆倍数
     */
    private int leverageLevel;



    /**
     * 下单价格
     */
    private BigDecimal price;


    /**
     * 下单数量
     */
    private BigDecimal volumeBase;
    /**
     * 下单数额
     */
    private BigDecimal volumeQuote;

    /**
     * 有效状态（0有效，1已过期，2已完成，3触发失败）
     */
    private Integer status;



    /**
     * 订单状态备注
     */
    private Integer memo;



    /**
     * 订单过期时间
     */
    private Date expireTime;



    /**
     * 创建时间
     */
    private Date ctime;



    /**
     * 更新时间
     */
    private Date mtime;



    /**
     * 关联的主订单ID
     */
    private Long masterId;



    /**
     * 0 普通条件单，1 OTO类型条件单
     */
    private Integer type;



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
    private Integer tradeType;



    /**
     * 委托为限价方式的时候，限价价格
     */
    private BigDecimal tradePrice;



}

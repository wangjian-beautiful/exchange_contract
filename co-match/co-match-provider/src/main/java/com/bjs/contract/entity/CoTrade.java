package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author nike
 * @date 2022年11月10日 13:20
 */
@Data
@TableName("co_trade")
@EqualsAndHashCode
public class CoTrade {


    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
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
    private String operateType;



    /**
     * 买卖方向（buy 买入，sell 卖出）操作方向
     */
    private String operateSide;



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



}

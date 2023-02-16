package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 *
 * @author bjs code generator
 * @date 2022-11-11 10:54:13
 */
@Data
@TableName("settlement")
@EqualsAndHashCode
public class Settlement {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;



    /**
     * 交易对
     */
    private String symbol;



    /**
     * 0: 市价止盈  1: 限价止盈 2:条件市价止盈 3条件限价止盈 4：市价止损 5限价止损 6 条件市价止盈 7条件限价止损 8强平 9穿仓
     */
    private Integer type;



    /**
     * 用户id
     */
    private Long uid;



    /**
     * 平仓委托订单id
     */
    private Long coverOrderId;



    /**
     * 持仓订单id
     */
    private Long positionOrderId;



    /**
     * 结算数量
     */
    private BigDecimal settleBase;



    /**
     * 结算金额
     */
    private BigDecimal settleQuote;



    /**
     * 结算均价
     */
    private BigDecimal settleAvgPrice;



    /**
     * 创建时间
     */
    private Date ctime;



    /**
     * 更新时间
     */
    private Date mtime;



    /**
     * 结算盈亏
     */
    private BigDecimal profit;



    /**
     * 结算手续费
     */
    private BigDecimal fee;



}

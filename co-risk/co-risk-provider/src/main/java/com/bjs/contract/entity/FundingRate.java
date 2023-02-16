package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资金费率
 *
 * @author bjs code generator
 * @date 2022-11-11 11:13:47
 */
@Data
@TableName("funding_rate")
@EqualsAndHashCode
@ToString
public class FundingRate {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 交易对 BTC-USDT
     */
    private String symbol;


    /**
     * 资金费率
     */
    private BigDecimal rate;


    /**
     * 期数时间
     */
    private Date settleTime;


    /**
     * mtime
     */
    private Date mtime;


    /**
     * ctime
     */
    private Date ctime;


}

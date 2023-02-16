package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 维持保证金率
 *
 * @author bjs code generator
 * @date 2022-11-11 11:01:31
 */
@Data
@TableName("maintenance_margin_rate")
@EqualsAndHashCode
public class MaintenanceMarginRate {


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
     * 层级
     */
    private Integer tier;



    /**
     * 持仓 USDT名义价值 起始   左闭右开
     */
    private BigDecimal notionalValueBegin;



    /**
     * 持仓 USDT名义价值 结束   左闭右开
     */
    private BigDecimal notionalValueEnd;



    /**
     * 可使用最高杠杆倍数
     */
    private Integer maxLeverage;



    /**
     * 维持保证金比率 例子:0.0040
     */
    private BigDecimal maintenanceMarginRate;



    /**
     * 维持保证金速算额
     */
    private BigDecimal maintenanceAmount;



}

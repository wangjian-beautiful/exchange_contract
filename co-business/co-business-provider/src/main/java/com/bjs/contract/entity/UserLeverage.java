package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户个人币队杠杆倍数表
 *
 * @author bjs code generator
 * @date 2022-11-30 11:36:13
 */
@Data
@TableName("user_leverage")
@EqualsAndHashCode
public class UserLeverage {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;



    /**
     * 用户uid
     */
    private Long uid;



    /**
     * 交易对
     */
    private String symbol;



    /**
     * 杠杆倍数
     */
    private Integer leverage;



    /**
     * ctime
     */
    private Date ctime;



    /**
     * 更新时间
     */
    private Date mtime;



}

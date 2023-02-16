package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户明细
 *
 * @author bjs code generator
 * @date 2022-11-11 11:27:52
 */
@Data
@TableName("account")
@EqualsAndHashCode
public class Account {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;



    /**
     * UID，10000以内保留，作为公司账户
     */
    private Long uid;



    /**
     * 类型 0 账户余额 1冻结 2保证金 
     */
    private Integer type;



    /**
     * 余额
     */
    private BigDecimal balance;



    /**
     * 标签，冗余，只帮助直观反馈
     */
    private String tag;



    /**
     * 创建时间
     */
    private Date ctime;



    /**
     * 修改时间
     */
    private Date mtime;



}

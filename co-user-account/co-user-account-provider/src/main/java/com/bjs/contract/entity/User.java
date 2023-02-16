package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户表
 *
 * @author bjs code generator
 * @date 2022-11-11 11:09:58
 */
@Data
@TableName("user")
@EqualsAndHashCode
public class User {


    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long uid;



    /**
     * open api 调用时使用的apiKey
     */
    private String apiKey;



    /**
     * 是否有权限交易
     */
    private Integer tradable;



    /**
     * 是否有权限划转
     */
    private Integer transferable;



    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;



    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;



    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;



    /**
     * 更新者
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;



    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;



}

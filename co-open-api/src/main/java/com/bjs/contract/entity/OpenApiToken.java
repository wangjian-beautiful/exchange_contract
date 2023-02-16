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
 * @date 2022-12-12 17:14:01
 */
@Data
@TableName("open_api_token")
@EqualsAndHashCode
public class OpenApiToken {


    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;



    /**
     * 用户id
     */
    private Integer uid;



    /**
     * token值
     */
    private String token;



    /**
     * 密钥
     */
    private String secretKey;



    /**
     * 是否拥有特殊权限，0不是，1是
     */
    private Integer isSuperUser;



    /**
     * 信任的ip，多个ip用","分隔
     */
    private String believeIps;



    /**
     * 权限（冗余字段，第一版不设置权限；多个权限用","分隔）
     */
    private String authority;



    /**
     * 备注
     */
    private String label;



    /**
     * 创建时间
     */
    private Date ctime;



    /**
     * 创建时间
     */
    private Date mtime;



}

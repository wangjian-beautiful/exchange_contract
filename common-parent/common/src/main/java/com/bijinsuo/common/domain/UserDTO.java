package com.bijinsuo.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户表
 *
 * @author bjs code generator
 * @date 2022-11-09 17:57:14
 */
@Data
@EqualsAndHashCode
public class UserDTO {

    /**
     * 用户ID
     */
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
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

}

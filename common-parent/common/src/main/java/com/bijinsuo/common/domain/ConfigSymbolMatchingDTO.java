package com.bijinsuo.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 撮合币对配置表
 *
 * @author bjs code generator
 * @date 2022-11-09 17:55:48
 */
@Data
@EqualsAndHashCode
public class ConfigSymbolMatchingDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 基准货币，symbol的前半段
     */
    private String base;

    /**
     * 计价货币，symbol的后半段
     */
    private String quote;

    /**
     * 是否开放，0否，1是
     */
    private Integer isOpen;

    /**
     * 撮合所在服务器IP
     */
    private String server;

    /**
     * ctime
     */
    private Date ctime;

    /**
     * mtime
     */
    private Date mtime;

}

package com.bjs.contract.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author nike
 * @date 2022年11月10日 9:45
 */
@Data
@TableName("config_symbol_matching")
@EqualsAndHashCode
public class ConfigSymbolMatching {
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
     * 是否开放交易，0否，1是
     */
    private Integer isOpen;

    /**
     *  配置的撮合服务器ip
     */
    private String server;

    private Date ctime;

    private Date mtime;
}

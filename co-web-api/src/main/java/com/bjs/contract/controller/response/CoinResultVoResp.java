package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author nike
 * @date 2022年12月01日 11:05
 */
@Data
@EqualsAndHashCode
@ApiModel("多语言配置")
public class CoinResultVoResp {

    @ApiModelProperty("地址")
    private String backupFileAddress;
    @ApiModelProperty("fundsInStatus")
    private Integer fundsInStatus;
    @ApiModelProperty("fundsOutStatus")
    private Integer fundsOutStatus;
    @ApiModelProperty("marginCoinPrecision")
    private Integer marginCoinPrecision;
    @ApiModelProperty("maxLimitMoney")
    private String maxLimitMoney;
    @ApiModelProperty("maxLimitVolume")
    private String maxLimitVolume;
    @ApiModelProperty("maxMarketMoney")
    private String maxMarketMoney;
    @ApiModelProperty("maxMarketVolume")
    private String maxMarketVolume;
    @ApiModelProperty("地址")
    private Integer minOrderMoney;
    @ApiModelProperty("minOrderVolume")
    private Integer minOrderVolume;
    @ApiModelProperty("priceRange")
    private String priceRange;
    @ApiModelProperty("symbolPricePrecision")
    private Integer symbolPricePrecision;
    @ApiModelProperty("depth")
    private List<String> depth;
}

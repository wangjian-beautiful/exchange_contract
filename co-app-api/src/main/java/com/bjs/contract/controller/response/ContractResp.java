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
@ApiModel("合约配置")
public class ContractResp {

    @ApiModelProperty("交易对base")
    private String base;
    @ApiModelProperty("brokerId")
    private Long brokerId;
    @ApiModelProperty("capitalFrequency")
    private Integer capitalFrequency;
    @ApiModelProperty("capitalStartTime")
    private Integer capitalStartTime;
    @ApiModelProperty("classification")
    private Integer classification;
    @ApiModelProperty("合约名称")
    private String contractName;
    @ApiModelProperty("coType")
    private String coType;
    @ApiModelProperty("别名")
    private String contractOtherName;
    @ApiModelProperty("contractShowType")
    private String contractShowType;
    @ApiModelProperty("contractSide")
    private Integer contractSide;
    @ApiModelProperty("contractType")
    private String contractType;
    @ApiModelProperty("deliveryKind")
    private String deliveryKind;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("marginCoin")
    private String marginCoin;
    @ApiModelProperty("marginRate")
    private Integer marginRate;
    @ApiModelProperty("maxLever")
    private Integer maxLever;
    @ApiModelProperty("minLever")
    private Integer minLever;
    @ApiModelProperty("multiplier")
    private String multiplier;
    @ApiModelProperty("multiplierCoin")
    private String multiplierCoin;
    @ApiModelProperty("originalCoin")
    private String originalCoin;
    @ApiModelProperty("交易对quote")
    private String quote;
    @ApiModelProperty("settlementFrequency")
    private Integer settlementFrequency;
    @ApiModelProperty("sort")
    private Integer sort;
    @ApiModelProperty("subSymbol")
    private String multipsubSymbolierCoin;
    @ApiModelProperty("symbol")
    private String symbol;
    @ApiModelProperty("minOpenBase")
    private String minOpenBase;
    @ApiModelProperty("maxOpenBase")
    private String maxOpenBase;
    @ApiModelProperty("minOpenQuote")
    private String minOpenQuote;
    @ApiModelProperty("maxOpenQuote")
    private String maxOpenQuote;
    @ApiModelProperty("pricePrecision")
    private Integer pricePrecision;
    @ApiModelProperty("symbol")
    private CoinResultVoResp coinResultVo;
}

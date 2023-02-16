package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nike
 * @date 2023年01月03日 10:55
 */
@Data
@ApiModel("合约币对信息")
public class CoSymbolInfoResp {

    @ApiModelProperty("合约代号")
    private String contractName;
    @ApiModelProperty("交割日期")
    private String coType="永续";
    @ApiModelProperty("合约标的")
    private String contractOtherName;
    @ApiModelProperty("保证金币种")
    private String quote;
    @ApiModelProperty("最小价格变动单位")
    private String pricePrecision;
    @ApiModelProperty("最多维持保证金率")
    private String minRate;
}

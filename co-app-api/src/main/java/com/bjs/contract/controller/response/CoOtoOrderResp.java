package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nike
 * @date 2022年12月01日 11:05
 */
@Data
@EqualsAndHashCode
@ApiModel("止盈止损")
public class CoOtoOrderResp {
    @ApiModelProperty("止损价格(0 市价)")
    private String stopLossPrice;
    @ApiModelProperty("0 未生效 1生效")
    private Boolean stopLossStatus;
    @ApiModelProperty("止损触发价格")
    private String stopLossTrigger;
    @ApiModelProperty("止损id")
    private String stopLossTriggerId;
    @ApiModelProperty("止盈价格(0 市价)")
    private String takerProfitPrice;
    @ApiModelProperty("0 未生效 1生效")
    private Boolean takerProfitStatus;
    @ApiModelProperty("止盈触发价格")
    private String takerProfitTrigger;
    @ApiModelProperty("止盈id")
    private String takerProfitTriggerId;
}

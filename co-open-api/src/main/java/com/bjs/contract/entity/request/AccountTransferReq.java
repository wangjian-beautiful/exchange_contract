package com.bjs.contract.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author nike
 * @date 2022年12月14日 14:23
 */
@Data
@ApiModel("账户金额划转")
public class AccountTransferReq {

    @ApiModelProperty("金额")
    private String amount;
    @ApiModelProperty("币种 如 USDT")
    private String symbol;
    @ApiModelProperty("sign")
    private String sign;
    @ApiModelProperty("apiKey")
    private String apiKey;
    @ApiModelProperty("时间戳")
    private Long time;
}

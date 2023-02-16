package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nike
 * @date 2022年12月01日 11:09
 */
@Data
@ApiModel("合约账户详细数据")
public class UserAccountListResp {

    @ApiModelProperty("可用余额")
    private String canUseAmount;
    @ApiModelProperty("总资产")
    private String totalAmount;
    @ApiModelProperty("逐仓保证金")
    private String isolateMargin;
    @ApiModelProperty("冻结保证金")
    private String lockAmount;
    @ApiModelProperty("全仓保证金")
    private String totalMargin;
    @ApiModelProperty("可用余额")
    private String symbol;
}

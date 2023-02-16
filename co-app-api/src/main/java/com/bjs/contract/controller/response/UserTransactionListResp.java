package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nike
 * @date 2022年12月01日 15:03
 */
@Data
@ApiModel("合约账户流水明细")
public class UserTransactionListResp {

    @ApiModelProperty("时间")
    String ctime;
    @ApiModelProperty("金额")
    String amount;
    @ApiModelProperty("币种")
    String symbol;
    @ApiModelProperty("类型")
    String type;
    @ApiModelProperty("")
    String contractName;
}

package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author nike
 * @date 2022年12月01日 15:03
 */
@Data
@EqualsAndHashCode
@ApiModel("合约账户流水明细")
public class UserTransactionListResp {

    @ApiModelProperty("时间")
    private String ctime;
    @ApiModelProperty("金额")
    private String amount;
    @ApiModelProperty("币种")
    private String symbol;
    @ApiModelProperty("类型")
    private String type;
    @ApiModelProperty("")
    private String contractName;
}

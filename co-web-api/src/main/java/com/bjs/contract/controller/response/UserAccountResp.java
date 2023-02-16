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
@ApiModel("用户合约账户")
public class UserAccountResp {

    @ApiModelProperty("总余额")
    private String totalBalance;
    @ApiModelProperty("总余额对应币种")
    private String totalBalanceSymbol;
    @ApiModelProperty("合约账户详细数据")
    private List<UserAccountListResp> accountListResp;
}

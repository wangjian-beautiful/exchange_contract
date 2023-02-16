package com.bjs.contract.controller.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nike
 * @date 2023年01月04日 15:41
 */
@Data
@ApiModel("用户配置信息")
public class UserConfigInfoResp {

    @ApiModelProperty("合约单位")
    private Integer coUnit=1;
    @ApiModelProperty("持仓类型")
    private Integer positionModel=1;
    private Integer pcSecondConfirm=1;
    private Integer positionModelCanSwitch=0;
    private Integer exPireTime=30;
    private Integer couponTag=0;
    private Integer openContract=1;
    private Integer futuresLocalLimit=0;
    private Integer authLevel;
}

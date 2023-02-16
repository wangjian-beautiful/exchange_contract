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
@ApiModel("公共信息")
public class PublicInfoResp {

    @ApiModelProperty("ws 地址")
    private String wsUrl;
    @ApiModelProperty("永续合约简介")
    private String contractProInfo;
    @ApiModelProperty("时间戳")
    private Long currentTimeMillis;
    @ApiModelProperty("margin 币种")
    private List<String> marginCoinList;
    @ApiModelProperty("original 币种")
    private List<String> originalCoinList;
    @ApiModelProperty("多语言配置")
    private List<LangResp> langList;
    @ApiModelProperty("合约配置")
    private List<ContractResp> contractList;
}

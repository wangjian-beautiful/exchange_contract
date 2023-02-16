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
@ApiModel("多语言配置")
public class LangResp {

    @ApiModelProperty("地址")
    private String backupFileAddress;
    @ApiModelProperty("brokerId")
    private Long brokerId;
    @ApiModelProperty("创建时间")
    private String ctime;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("key")
    private String langKey;
    @ApiModelProperty("名字")
    private String langName;
    @ApiModelProperty("更新时间")
    private String mtime;
    @ApiModelProperty("地址")
    private String nowFileAddress;
    @ApiModelProperty("排序")
    private Integer sort;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("类型")
    private Integer type;
}

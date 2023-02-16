package com.bjs.contract.controller;

import com.bijinsuo.common.identify.PublicResource;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bjs.contract.action.PublicAction;
import com.bjs.contract.controller.response.PublicInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "相关接口")
@Slf4j
@RestController
@RequestMapping("common")
public class PublicController {

    @Resource
    private PublicAction publicAction;

    @ApiOperation("公共信息")
    @ResponseBody
    @PublicResource
    @PostMapping(value = "/public_info")
    public ResultBody<PublicInfoResp> publicInfo() {
        ResultBody<PublicInfoResp> resultBody = ResultBody.success(publicAction.getPublicInfo());
        resultBody.setCode("0");
        return resultBody;
    }
}

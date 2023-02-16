package com.bjs.contract.controller;

import com.bijinsuo.common.utils.config.ResultBody;
import com.bjs.contract.action.SymbolAction;
import com.bjs.contract.controller.response.CoSymbolInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nike
 * @date 2023年01月03日 12:00
 */
@Api(tags = "获取合约币对信息")
@Slf4j
@RestController
@RequestMapping("symbol")
public class SymbolController {


    @Resource
    private SymbolAction symbolAction;

    @GetMapping("/info")
    public ResultBody<CoSymbolInfoResp> getSymbolInfo(@ApiParam("币对") @RequestParam("symbol")String symbol){

        return ResultBody.success(symbolAction.getSymbolInfo(symbol));
    }
}

package com.bjs.contract.controller;

import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bjs.contract.action.TransactionAction;
import com.bjs.contract.controller.response.UserTransactionResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author nike
 * @date 2022年12月01日 15:19
 */
@Api(tags = "用户合约账户流水")
@Slf4j
@RestController
@RequestMapping("record")
public class UserTransactionController {

    @Resource
    private TransactionAction transactionAction;


    @GetMapping("/get_transaction_list")
    @ApiOperation("获取用户流水信息")
    @ResponseBody
    public ResultBody<UserTransactionResp> getUserTransactionList(@ApiParam("页码") @RequestParam("page") int page,
                                                                  @ApiParam("数量") @RequestParam("limit")int limit,
                                                                  @ApiParam("开始时间") @RequestParam(value = "beginTime",required = false)String beginTime,
                                                                  @ApiParam("结束时间") @RequestParam(value = "endTime",required = false)String endTime,
                                                                  @ApiParam("币种后缀如 USDT") @RequestParam(value = "symbol",required = false)String symbol,
                                                                  @ApiParam("类型 ")  @RequestParam(value = "type",required = false) String type){


        log.info("****Controller进来了");
        return ResultBody.success(transactionAction.getUserTransactionList(page,limit,beginTime,endTime,symbol,type, UserContextHolder.user.get().getId()));
    }
}

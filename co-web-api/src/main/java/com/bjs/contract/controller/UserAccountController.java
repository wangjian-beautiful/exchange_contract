package com.bjs.contract.controller;

import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bjs.contract.action.UserAccountAction;
import com.bjs.contract.controller.response.UserAccountResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author nike
 * @date 2022年12月01日 11:24
 */
@Api(tags = "用户合约账户")
@Slf4j
@RestController
@RequestMapping("account")
public class UserAccountController {

    @Resource
    private UserAccountAction userAccountAction;


    @ApiOperation("获取用户全部账户信息")
    @ResponseBody
    @GetMapping(value = "/get_assets_list")
    public ResultBody<UserAccountResp> getUserAccountList(){
       return ResultBody.success(userAccountAction.getUserAccountList(UserContextHolder.user.get().getId()));
    }



}

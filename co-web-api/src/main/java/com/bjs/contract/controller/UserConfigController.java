package com.bjs.contract.controller;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bjs.contract.controller.response.UserConfigInfoResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author nike
 * @date 2023年01月04日 15:41
 */
@Api(tags = "获取用户配置信息")
@Slf4j
@RestController
@RequestMapping("user/config")
public class UserConfigController {


    @ApiOperation("用户配置信息")
    @ResponseBody
    @GetMapping(value = "/info")
    public ResultBody<UserConfigInfoResp> getUserConfigInfo(@RequestParam("contractId") Integer contractId) {
        UserConfigInfoResp resp = new UserConfigInfoResp();
        try {
            String result = HttpRequest.post("http://www.bjs83.com/api/user_auth_info").header("uid", UserContextHolder.user.get().getId().toString()).form(JSON.toJSONString(resp)).timeout(20000)//超时，毫秒
                    .execute().body();
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject data = jsonObject.getJSONObject("data");
            resp.setAuthLevel(data.getInteger("auth"));
        } catch (Exception e) {
            resp.setAuthLevel(0);
        }
        return ResultBody.success(resp);
    }


}
package com.bjs.contract.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.result.ApiResultType;

import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.TransactionSceneEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.action.AccountOpenApiAction;
import com.bjs.contract.entity.OpenApiToken;
import com.bjs.contract.entity.request.AccountTransferReq;
import com.bjs.contract.service.OpenApiTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author nike
 * @date 2022年12月14日 13:38
 */
@Api(tags = "用户账户openApi接口")
@Slf4j
@RestController
@RequestMapping("/fapi/v1")
public class AccountOpenController extends BaseOpenApiController {

    @Resource
    private AccountOpenApiAction accountOpenApiAction;
    @Resource
    private OpenApiTokenService openApiTokenService;

    @ApiOperation("资金划进")
    @PostMapping("/account/scratchIn")
    public ResultBody<String> scratchIn(HttpServletRequest request, @RequestBody AccountTransferReq req) {
        ResultBody<String> resultDataBody = new ResultBody<>();
        resultDataBody.setCode(ApiResultType.SUCCESS.getCode());
        resultDataBody.setMessage(ApiResultType.SUCCESS.message);
        String token = request.getParameter("token");
        if (StringUtils.isNotBlank(token)) {
            var user = UserContextHolder.user.get();
            accountOpenApiAction.accountTransfer(req, user.getId(), TransactionSceneEnum.SCRATCH_IN.getValue());
        } else {
            accountTransfer(resultDataBody,request,req,TransactionSceneEnum.SCRATCH_IN.getValue());
        }
        return resultDataBody;
    }

    @ApiOperation("资金划出")
    @PostMapping("/account/crossOut")
    public ResultBody<String> crossOut(HttpServletRequest request, @RequestBody AccountTransferReq req) {
        ResultBody<String> resultDataBody = new ResultBody<>();
        resultDataBody.setCode(ApiResultType.SUCCESS.getCode());
        resultDataBody.setMessage(ApiResultType.SUCCESS.message);
        String token = request.getParameter("token");
        if (StringUtils.isNotBlank(token)) {
            var user = UserContextHolder.user.get();
            accountOpenApiAction.accountTransfer(req, user.getId(), TransactionSceneEnum.CROSS_OUT.getValue());
        } else {
            accountTransfer(resultDataBody,request,req,TransactionSceneEnum.CROSS_OUT.getValue());
        }
        return resultDataBody;
    }





    private void accountTransfer(ResultBody<String> resultDataBody,HttpServletRequest request,
                                 AccountTransferReq req,String type){
        Map<String, String> params = JSON.parseObject(JSON.toJSONString(req), new TypeReference<>() {
        });
        ApiResultType apiResultType = check(request, params);
        resultDataBody.setCode(apiResultType.getCode());
        resultDataBody.setMessage(apiResultType.getMessage());
        Long uid = getUserById(req.getApiKey());
        accountOpenApiAction.accountTransfer(req, uid, type);
    }
    /**
     * 通过token 获取用户信息
     *
     * @param apiKey 用户apiKey
     * @return uid
     */
    public Long getUserById(String apiKey) {
        OpenApiToken tokenObject = openApiTokenService.findByOpenApiToken(apiKey);
        if (tokenObject == null) {
            throw new BizException("参数错误");
        }
        return Long.valueOf(tokenObject.getUid());
    }
}
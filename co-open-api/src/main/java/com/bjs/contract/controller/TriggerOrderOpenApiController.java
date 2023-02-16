package com.bjs.contract.controller;

import com.bijinsuo.common.domain.CoTriggerOrderDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.result.ApiResultType;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.enums.TriggerStatusEnum;
import com.bijinsuo.common.utils.enums.TriggerTypeEnum;
import com.bjs.contract.action.CoTriggerOrderAction;
import com.bjs.contract.entity.request.CoTriggerOrderRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Watson
 */
@Api(tags = "条件单相关接口")
@Slf4j
@RestController
@RequestMapping("/fapi/v1/trigger")
public class TriggerOrderOpenApiController extends BaseOpenApiController {

    @Resource
    private CoTriggerOrderAction coTriggerOrderAction;

    @ApiOperation("创建条件订单")
    @PostMapping("/create")
    public ResultBody<Void> createTrigger(@RequestBody @Validated @ApiParam(required = true) CoTriggerOrderRequest triggerOrderRequest, HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request, requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.getCode()) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }


        final var dto = new CoTriggerOrderDTO();
        BeanUtils.copyProperties(triggerOrderRequest, dto);
        dto.setUid(UserContextHolder.user.get().getId());
        dto.setTriggerType(TriggerTypeEnum.NORMAL.getCode());
        dto.setStatus(TriggerStatusEnum.ACTIVE.getCode());
        dto.setCtime(new Date());
        dto.setMtime(new Date());
        coTriggerOrderAction.create(dto);
        return ResultBody.success();
    }

    @ApiOperation("撤销条件委托")
    @PutMapping("/cancel")
    public ResultBody<Boolean> cancel(@RequestParam Long triggerOrderId, HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request, requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.getCode()) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }

        if (triggerOrderId == null) {
            return ResultBody.error(CommonEnum.PARAMETER_ERROR);
        }
        coTriggerOrderAction.cancel(triggerOrderId);
        return ResultBody.success(true);
    }

    @ApiOperation("撤销所有条件委托")
    @PutMapping("/cancelAll")
    public ResultBody<Boolean> cancelAll(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request, requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.getCode()) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }

        coTriggerOrderAction.cancelAll();
        return ResultBody.success(true);
    }
}

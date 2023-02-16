package com.bjs.contract.controller;

import com.bijinsuo.common.domain.CoTriggerOrderDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.enums.TriggerStatusEnum;
import com.bijinsuo.common.utils.enums.TriggerTypeEnum;
import com.bjs.contract.action.CoTriggerOrderAction;
import com.bjs.contract.controller.request.CoTriggerOrderRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Watson
 */
@Api(tags = "条件单相关接口")
@Slf4j
@RestController
@RequestMapping("trigger")
public class TriggerOrderController {

    @Resource
    private CoTriggerOrderAction coTriggerOrderAction;

    @ApiOperation("创建条件订单")
    @PostMapping("/create")
    public ResultBody<Void> createTrigger(@RequestBody @Validated @ApiParam(required = true) CoTriggerOrderRequest request) {
        final var dto = new CoTriggerOrderDTO();
        BeanUtils.copyProperties(request,dto);
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
    public ResultBody<Boolean> cancel(@RequestParam Long triggerOrderId) {
        if (triggerOrderId == null) {
            return ResultBody.error(CommonEnum.PARAMETER_ERROR);
        }
        coTriggerOrderAction.cancel(triggerOrderId);
        return ResultBody.success(true);
    }

    @ApiOperation("撤销所有条件委托")
    @PutMapping("/cancelAll")
    public ResultBody<Boolean> cancelAll() {
        coTriggerOrderAction.cancelAll();
        return ResultBody.success(true);
    }
}

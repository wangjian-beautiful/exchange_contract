package com.bjs.contract.controller;

import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.*;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.action.CoOrderAction;
import com.bjs.contract.controller.request.CoOrderCloseRequest;
import com.bjs.contract.controller.request.CoOrderRequest;
import com.bjs.contract.controller.request.CurrentOrderRequest;
import com.bjs.contract.controller.request.HistoryOrderRequest;
import com.bjs.contract.controller.response.CoCurrentOrderResp;
import com.bjs.contract.controller.response.HistoryOrderResp;
import com.bjs.contract.controller.response.TriggerOrderResp;
import com.bjs.contract.controller.response.UserOrderCountResp;
import com.bjs.contract.proto.coOrder.CoOrderReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

@Api(tags = "下单相关接口")
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private CoOrderAction coOrderAction;

    @ApiOperation("创建平仓订单")
    @ResponseBody
    @PostMapping(value = "/order_close")
    public ResultBody<Void> orderClose(@RequestBody @Validated @ApiParam(required = true) CoOrderCloseRequest orderRequest) {
        final CoOrderDTO dto = createDtoFrom(orderRequest);
        coOrderAction.close(dto);
        return ResultBody.success();
    }

    @ApiOperation("撤销订单")
    @ResponseBody
    @PostMapping(value = "/order_cancel")
    public ResultBody<Void> orderCancel(@RequestParam Long orderId) {
        CoOrderReply coOrderReply = coOrderAction.cancelOrder(orderId);
        return coOrderReply.getStatus() ? ResultBody.success() : ResultBody.error(coOrderReply.getMessage());
    }

    /**
     * 合约委托单下单开仓接口
     *
     * @param orderRequest
     * @return
     */
    @ApiOperation("创建开仓订单")
    @ResponseBody
    @PostMapping(value = "/order_create")
    public ResultBody<Void> orderCreate(@RequestBody @Validated @ApiParam(required = true) CoOrderRequest orderRequest) {
        checkParameter(orderRequest);
        final CoOrderDTO dto = createDtoFrom(orderRequest);
        coOrderAction.create(dto);
        return ResultBody.success();
    }

    /**
     * 撤销全部委托单
     *
     * @return
     */
    @ApiOperation("撤销全部委托单")
    @ResponseBody
    @PostMapping(value = "/cancelUserOrdersNotFilled")
    public ResultBody<Void> cancelUserOrdersNotFilled() {
        CoOrderReply coOrderReply = coOrderAction.cancelUserOrdersNotFilled(UserContextHolder.user.get().getId());
        return coOrderReply.getStatus() ? ResultBody.success() : ResultBody.error(coOrderReply.getMessage());
    }

    /**
     * 普通委托条件委托数量
     *
     * @return
     */
    @ApiOperation("普通委托条件委托数量")
    @ResponseBody
    @PostMapping(value = "/get_user_order_count")
    public ResultBody<UserOrderCountResp> getUserOrderCount() {
        UserOrderCountResp userOrderCountResp = coOrderAction.getUserOrderCount(UserContextHolder.user.get().getId());
        return ResultBody.success(userOrderCountResp);
    }

    /**
     * 普通委托列表
     *
     * @return
     */
    @ApiOperation("普通委托列表")
    @ResponseBody
    @PostMapping(value = "/current_order_list")
    public ResultBody<CoCurrentOrderResp> getCurrentOrderList(@RequestBody @Validated @ApiParam(required = true) CurrentOrderRequest currentOrderRequest) {
        currentOrderRequest.setUid(UserContextHolder.user.get().getId());
        CoCurrentOrderResp currentOrderResp = coOrderAction.getCurrentOrderList(currentOrderRequest);
        return ResultBody.success(currentOrderResp);
    }

    /**
     * 条件委托列表
     *
     * @return
     */
    @ApiOperation("条件委托列表")
    @ResponseBody
    @PostMapping(value = "/trigger_order_list")
    public ResultBody<TriggerOrderResp> getTriggerOrderList(@RequestBody @Validated @ApiParam(required = true) CurrentOrderRequest currentOrderRequest) {
        currentOrderRequest.setUid(UserContextHolder.user.get().getId());
        TriggerOrderResp triggerOrderResp = coOrderAction.getTriggerOrderList(currentOrderRequest, false);
        return ResultBody.success(triggerOrderResp);
    }

    /**
     * 历史计划委托列表
     *
     * @return
     */
    @ApiOperation("历史计划委托列表")
    @ResponseBody
    @PostMapping(value = "/history_trigger_order_list")
    public ResultBody<TriggerOrderResp> getHistoryTriggerOrderList(@RequestBody @Validated @ApiParam(required = true) CurrentOrderRequest currentOrderRequest) {
        currentOrderRequest.setUid(UserContextHolder.user.get().getId());
        TriggerOrderResp triggerOrderResp = coOrderAction.getTriggerOrderList(currentOrderRequest, true);
        return ResultBody.success(triggerOrderResp);
    }

    /**
     * 历史委托列表
     *
     * @return
     */
    @ApiOperation("历史委托列表")
    @ResponseBody
    @PostMapping(value = "/history_order_list")
    public ResultBody<HistoryOrderResp> getHistoryOrderList(@RequestBody @Validated @ApiParam(required = true) HistoryOrderRequest coHistoryOrderRequest) {
        coHistoryOrderRequest.setUid(UserContextHolder.user.get().getId());
        HistoryOrderResp historyOrderResp = coOrderAction.getHistoryOrderList(coHistoryOrderRequest);
        return ResultBody.success(historyOrderResp);
    }


    private void checkParameter(CoOrderRequest req) {
        if (req.getPrice() == null || req.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            if (req.getVolumeQuote() == null || req.getVolumeQuote().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BizException(CommonEnum.BODY_NOT_MATCH);
            }
        }
    }

    private CoOrderDTO createDtoFrom(CoOrderCloseRequest orderCloseRequest) {
        final var dto = new CoOrderDTO();
        BeanUtils.copyProperties(orderCloseRequest, dto);
        return createDtoFrom(dto, OperateTypeEnum.CLOSE);
    }

    private CoOrderDTO createDtoFrom(CoOrderRequest orderRequest) {
        final var dto = new CoOrderDTO();
        BeanUtils.copyProperties(orderRequest, dto);
        return createDtoFrom(dto, OperateTypeEnum.OPEN);
    }

    private CoOrderDTO createDtoFrom(CoOrderDTO dto, OperateTypeEnum typeEnum) {
        dto.setUid(UserContextHolder.user.get().getId());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest contextRequest = requestAttributes.getRequest();
            dto.setIp(contextRequest.getRemoteAddr());
        } else {
            dto.setIp("");
        }
        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            dto.setMatchType(ExchangeOrderType.MARKET_PRICE.value);
        } else {
            dto.setMatchType(ExchangeOrderType.LIMIT_PRICE.value);
        }
        dto.setPositionType(PositionTypeEnum.CROSS_MARGIN.getValue());
        dto.setOperateType(typeEnum.name());
        dto.setStatus(OrderStatus.NEW_.value);
        dto.setSource(OrderSourceEnum.WEB.value);
        dto.setCtime(new Date());
        dto.setMtime(new Date());
        return dto;
    }
}

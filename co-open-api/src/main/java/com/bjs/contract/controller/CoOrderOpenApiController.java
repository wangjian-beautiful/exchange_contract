package com.bjs.contract.controller;


import cn.hutool.core.util.StrUtil;
import com.bijinsuo.common.domain.CoOrderDTO;
import com.bijinsuo.common.domain.CoTradeDTO;
import com.bijinsuo.common.domain.ContractConfigDTO;
import com.bijinsuo.common.domain.SettlementDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.result.ApiResultType;
import com.bijinsuo.common.utils.MD5Util;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.*;
import com.bijinsuo.common.utils.exception.BizException;
import com.bjs.contract.action.CoOrderOpenApiAction;
import com.bjs.contract.entity.request.OrderRequest;
import com.bjs.contract.proto.coOrder.CoOrderReply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Api(tags = "订单相关openApi接口")
@Slf4j
@RestController
@RequestMapping("/fapi/v1")
public class CoOrderOpenApiController extends BaseOpenApiController {

    @Resource
    private CoOrderOpenApiAction orderOpenApiAction;

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * 合约列表
     * @return
     */
    @ApiOperation("合约列表")
    @GetMapping(value = "/contracts")
    public ResultBody<List<ContractConfigDTO>> contracts(){
        return ResultBody.success(orderOpenApiAction.contracts());
    }

    /**
     * 创建开仓订单
     * @return
     */
    @ApiOperation("创建开仓订单")
    @PostMapping(value = "/orderCreate")
    public ResultBody<Void> orderCreate (@RequestBody @Validated @ApiParam(required = true) OrderRequest orderRequest, HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.code) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        checkParameter(orderRequest);
        final CoOrderDTO dto = createDtoFrom(orderRequest, OperateTypeEnum.OPEN, request);
        orderOpenApiAction.create(dto);
        return ResultBody.success();
    }

    /**
     * 创建平仓订单
     * @return
     */
    @ApiOperation("创建平仓订单")
    @PostMapping(value = "/orderClose")
    public ResultBody<Void> orderClose (@RequestBody @Validated @ApiParam(required = true) OrderRequest orderRequest, HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.code) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        checkParameter(orderRequest);
        final CoOrderDTO dto = createDtoFrom(orderRequest, OperateTypeEnum.CLOSE, request);
        orderOpenApiAction.close(dto);
        return ResultBody.success();
    }

    @ApiOperation("撤销订单")
    @PostMapping(value = "/orderCancel")
    public ResultBody<Void> orderCancel(@RequestParam Long orderId, HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.code) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        CoOrderReply coOrderReply = orderOpenApiAction.cancelOrder(orderId);
        return coOrderReply.getStatus() ? ResultBody.success() : ResultBody.error(coOrderReply.getMessage());
    }

    /**
     * 撤销全部委托单
     *
     * @return
     */
    @ApiOperation("撤销全部委托单")
    @PostMapping(value = "/cancelUserOrdersNotFilled")
    public ResultBody<Void> cancelUserOrdersNotFilled(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.code) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        CoOrderReply coOrderReply = orderOpenApiAction.cancelUserOrdersNotFilled(UserContextHolder.user.get().getId());
        return coOrderReply.getStatus() ? ResultBody.success() : ResultBody.error(coOrderReply.getMessage());
    }

    /**
     * 订单详情
     *
     * @return
     */
    @ApiOperation("订单详情")
    @ResponseBody
    @GetMapping(value = "/order")
    public ResultBody<CoOrderDTO> getOrderByIdAndContractName(HttpServletRequest request) {

        CoOrderDTO coOrderDTO = orderOpenApiAction.getOrderByIdAndContractName(request);
        return ResultBody.success(coOrderDTO);
    }

    /**
     * 当前订单
     *
     * @return
     */
    @ApiOperation("当前订单")
    @ResponseBody
    @GetMapping(value = "/openOrders")
    public ResultBody<List<CoOrderDTO>> getCurrentOrder(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != "0") {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        List<CoOrderDTO> coOrderDTOList = orderOpenApiAction.getCurrentOrder(request);
        return ResultBody.success(coOrderDTOList);
    }

    /**
     * 历史委托
     *
     * @return
     */
    @ApiOperation("历史委托")
    @ResponseBody
    @PostMapping(value = "/orderHistorical")
    public ResultBody<List<CoOrderDTO>> getOrderHistorical(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != "0") {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        List<CoOrderDTO> coOrderDTOList = orderOpenApiAction.getOrderHistorical(request);
        return ResultBody.success(coOrderDTOList);
    }

    /**
     * 盈亏记录
     *
     * @return
     */
    @ApiOperation("盈亏记录")
    @ResponseBody
    @PostMapping(value = "/profitHistorical")
    public ResultBody<List<SettlementDTO>> profitHistorical(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != "0") {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        List<SettlementDTO> settlementList = orderOpenApiAction.getSettlement(request);
        return ResultBody.success(settlementList);
    }

    /**
     * 交易记录
     *
     * @return
     */
    @ApiOperation("交易记录")
    @ResponseBody
    @GetMapping(value = "/myTrades")
    public ResultBody<List<CoTradeDTO>> myTrades(HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request,requestMap);
        if (resultType.getCode() != "0") {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }
        List<CoTradeDTO> tradeHistorical = orderOpenApiAction.getTradeHistorical(request);
        return ResultBody.success(tradeHistorical);
    }

    /**
     * sign test
     *
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/signTest")
    public void signTest() {
        String url = "http://localhost:9030/fapi/v1/order";
        Integer time = curTime();
        Map<String, String> params = new HashMap<>();
        params.put("api_key", "54942ccf2cf956c367a2c52a42da4d44");
        params.put("time", String.valueOf(time));
        String sign = this.sign(params, "88453438e90eaacd42e6d71b632b125f");
        params.put("sign", sign);
		params.put("contractName","BTCUSDT");
		params.put("orderId","60157");
        MultiValueMap paramMap = parseMap(params);
        try {
            String result0 = restTemplate.postForObject(url, paramMap, String.class);
            System.out.println(result0);
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        Integer time = curTime();
        System.out.println(String.valueOf(time));
        Map<String, String> params = new HashMap<>();
        params.put("api_key", "54942ccf2cf956c367a2c52a42da4d44");
        params.put("time", String.valueOf(time));
        String sign = sign(params, "88453438e90eaacd42e6d71b632b125f");
        System.out.println(sign);
    }


    private static Integer curTime() {
        return Math.toIntExact(System.currentTimeMillis() / 1000);
    }

    private MultiValueMap<String, String> parseMap(Map<String, String> params) {
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        params.forEach((k, v) -> {
            paramMap.add(k, v);
        });
        return paramMap;
    }

    /**
     * 签名
     *
     * @param params
     * @return
     */
    private static String sign(Map<String, String> params, String secret) {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"keyvalue"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            if (param.getKey().equals("sign")) {//去掉签名字段
                continue;
            }

            if (!StrUtil.isBlank(param.getValue())) {
                basestring.append(param.getKey());
                basestring.append(param.getValue().toString());
            }
        }
        basestring.append(secret);

        // 使用MD5对待签名串求签
        String curSign = MD5Util.getMD5(basestring.toString());

        return curSign;
    }

    private void checkParameter(OrderRequest req) {
        if (req.getPrice() == null || req.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            if (req.getVolumeQuote() == null || req.getVolumeQuote().compareTo(BigDecimal.ZERO) <= 0) {
                throw new BizException(CommonEnum.BODY_NOT_MATCH);
            }
        }
    }

    private CoOrderDTO createDtoFrom(OrderRequest orderRequest, OperateTypeEnum typeEnum, HttpServletRequest request) {
        final var dto = new CoOrderDTO();
        BeanUtils.copyProperties(orderRequest, dto);
        return createDtoFrom(dto, typeEnum, request);
    }

    private CoOrderDTO createDtoFrom(CoOrderDTO dto, OperateTypeEnum typeEnum, HttpServletRequest request) {
        Long uid = UserContextHolder.user.get().getId();
        dto.setUid(uid);
        dto.setIp(request.getRemoteAddr());
        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            dto.setMatchType(ExchangeOrderType.MARKET_PRICE.value);
        } else {
            dto.setMatchType(ExchangeOrderType.LIMIT_PRICE.value);
        }
        dto.setPositionType(PositionTypeEnum.CROSS_MARGIN.getValue());
        dto.setOperateType(typeEnum.name());
        dto.setStatus(OrderStatus.NEW_.value);
        dto.setSource(OrderSourceEnum.OPEN_API.value);
        dto.setCtime(new Date());
        dto.setMtime(new Date());
        return dto;
    }
}

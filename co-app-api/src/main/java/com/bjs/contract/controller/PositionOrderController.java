package com.bjs.contract.controller;

import com.bijinsuo.common.domain.CoPositionOrderDTO;
import com.bijinsuo.common.domain.SettlementDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bjs.contract.action.CoOrderTradeAction;
import com.bjs.contract.action.CoPositionOrderAction;
import com.bjs.contract.controller.request.HistorySettlementRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "查询接口")
@Slf4j
@RestController
@RequestMapping("position")
public class PositionOrderController {

    @Resource
    private CoOrderTradeAction coOrderTradeAction;
    @Resource
    private CoPositionOrderAction coPositionOrderAction;


    @ApiOperation("持仓历史")
    @ResponseBody
    @GetMapping(value = "/history_position_list")
    public ResultBody<List<CoPositionOrderDTO>> history_position_list() {
        CoPositionOrderDTO dto = new CoPositionOrderDTO();
        dto.setUid(UserContextHolder.user.get().getId());
        List<CoPositionOrderDTO> dtos = coPositionOrderAction.selectList(dto);
        return ResultBody.success(dtos);
    }
    @ApiOperation("当前持仓")
    @ResponseBody
    @GetMapping(value = "/get_assets_list")
    public ResultBody<List<CoPositionOrderDTO>> get_assets_list() {

        CoPositionOrderDTO dto = new CoPositionOrderDTO();
        dto.setUid(UserContextHolder.user.get().getId());
        List<CoPositionOrderDTO> dtos = coPositionOrderAction.selectList(dto);
        if (CollectionUtils.isNotEmpty(dtos)){
            dtos = dtos.stream().filter(o -> o.getStatus() == 0).collect(Collectors.toList());
        }
        return ResultBody.success(dtos);
    }
    @ApiOperation("盈亏记录")
    @ResponseBody
    @PostMapping(value = "/profitHistorical")
    public ResultBody<List<SettlementDTO>> profitHistorical(@RequestBody @Validated @ApiParam(required = true) HistorySettlementRequest request) {
        List<SettlementDTO> settlementList = coOrderTradeAction.getSettlement(request);
        return ResultBody.success(settlementList);
    }

}

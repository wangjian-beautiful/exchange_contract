package com.bjs.contract.controller;

import com.alibaba.nacos.common.utils.CollectionUtils;
import com.bijinsuo.common.domain.CoTradeDTO;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bjs.contract.action.CoOrderTradeAction;
import com.bjs.contract.controller.dto.OldTradeDto;
import com.bjs.contract.controller.request.OrderTradeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "查询接口")
@Slf4j
@RestController
@RequestMapping("order/")
public class OrderTradeController {
    @Resource
    private CoOrderTradeAction coOrderTradeAction;

    /**
     *
     * @param request
     * @return
     */
    @ApiOperation("成交记录")
    @ResponseBody
    @GetMapping(value = "/get_trade_info")
    public ResultBody<List<OldTradeDto>> getTradeInfo(@ApiParam OrderTradeRequest request) {
        List<CoTradeDTO> coTradeDTO = coOrderTradeAction.selectByUid(request, UserContextHolder.user.get().getId());
        return ResultBody.success(oldTradeDto(coTradeDTO));

    }

    @ApiOperation("历史成交")
    @ResponseBody
    @GetMapping(value = "/his_trade_list")
    public ResultBody<List<OldTradeDto>> hisTradeList(@ApiParam OrderTradeRequest request) {
        List<CoTradeDTO> coTradeDTO = coOrderTradeAction.selectByUid(request, UserContextHolder.user.get().getId());
        return ResultBody.success(oldTradeDto(coTradeDTO));
    }

    private List<OldTradeDto> oldTradeDto(List<CoTradeDTO> trades){
        if (CollectionUtils.isEmpty(trades)){
            return new ArrayList<>();
        }
        return trades.stream().map( trade->{
            OldTradeDto old = new OldTradeDto();
            old.setSymbol(trade.getSymbol());
            old.setFee(trade.getFee());
            old.setPrice(trade.getPrice());
            old.setSide(trade.getTrendSide());
            old.setCtime(trade.getCtime().getTime());
            old.setRole(trade.getOperateSide());
            old.setId(trade.getId());
            old.setVolume(trade.getVolumeBase());
            return old;
        }).collect(Collectors.toList());
    }

}

package com.bjs.contract.controller;

import com.bijinsuo.common.domain.UserLeverageDTO;
import com.bijinsuo.common.result.ApiResultType;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bjs.contract.action.MaintenanceMarginRateAction;
import com.bjs.contract.action.UserLeverageAction;
import com.bjs.contract.entity.request.UpdateLeverageReq;
import com.bjs.contract.entity.response.SymbolIntervalResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Watson
 */
@Api(tags = "杠杆相关openApi接口")
@Slf4j
@RestController
@RequestMapping("/fapi/v1/leverage")
public class UserLeverageOpenApiController extends BaseOpenApiController {

    @Resource
    private UserLeverageAction userLeverageAction;
    @Resource
    private MaintenanceMarginRateAction maintenanceMarginRateAction;

    @ApiOperation("查询某个币队杠杆区间")
    @GetMapping("/interval")
    public ResultBody<SymbolIntervalResp> getLeverageInterval(@RequestParam(value = "symbol") String symbol, HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request, requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.getCode()) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }

        SymbolIntervalResp symbolInterval = maintenanceMarginRateAction.getSymbolInterval(symbol);
        if (symbolInterval != null) {
            return ResultBody.success(symbolInterval);
        }
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);

    }

    @ApiOperation("用户当前币队杠杆")
    @GetMapping("")
    public ResultBody<Integer> getLeverage(@RequestParam(value = "symbol") String symbol, HttpServletRequest request) {

        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request, requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.getCode()) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }

        UserLeverageDTO userLeverageDTO = userLeverageAction.getBySymbol(symbol);
        if (userLeverageDTO != null) {
            return ResultBody.success(userLeverageDTO.getLeverage());
        }
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);

    }

    @ApiOperation("用户修改杠杆")
    @PutMapping("")
    public ResultBody<Void> updateLeverage(@RequestBody @Validated @ApiParam(required = true) UpdateLeverageReq req, HttpServletRequest request) {
        Map<String, String> requestMap = new HashMap<>();
        ApiResultType resultType = commonCheck(request, requestMap);
        if (resultType.getCode() != ApiResultType.SUCCESS.getCode()) {
            return ResultBody.error(resultType.getCode(), resultType.getMessage());
        }


        boolean result = userLeverageAction.updateLeverage(req);
        if (!result) {
            return ResultBody.error(CommonEnum.NOT_ENOUGH_BALANCE);
        }
        return ResultBody.success();
    }

}

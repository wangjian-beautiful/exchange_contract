package com.bjs.contract.controller;

import com.bijinsuo.common.domain.UserLeverageDTO;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bjs.contract.action.MaintenanceMarginRateAction;
import com.bjs.contract.action.UserLeverageAction;
import com.bjs.contract.controller.request.UpdateLeverageReq;
import com.bjs.contract.controller.response.SymbolIntervalResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户杠杆控制器
 *
 * @author Watson
 */
@Api(tags = "用户杠杆操作")
@Slf4j
@RestController
@RequestMapping("leverage")
public class UserLeverageController {

    @Resource
    private UserLeverageAction userLeverageAction;
    @Resource
    private MaintenanceMarginRateAction maintenanceMarginRateAction;

    private final Long USER_LOCK_LEASE_TIME_SECOND = 30l;


    @ApiOperation("查询某个币队杠杆区间")
    @GetMapping("/interval")
    public ResultBody<SymbolIntervalResp> getLeverageInterval(@ApiParam(value = "币队", example = "BTCUSDT", required = true) @RequestParam(value = "symbol") String symbol) {
        SymbolIntervalResp symbolInterval = maintenanceMarginRateAction.getSymbolInterval(symbol);
        if (symbolInterval != null) {
            return ResultBody.success(symbolInterval);
        }
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);

    }

    @ApiOperation("用户当前币队杠杆")
    @GetMapping("")
    public ResultBody<Integer> getLeverage(@ApiParam(value = "币队", example = "BTCUSDT", required = true) @RequestParam(value = "symbol") String symbol) {
        UserLeverageDTO userLeverageDTO = userLeverageAction.getBySymbol(symbol);
        if (userLeverageDTO != null) {
            return ResultBody.success(userLeverageDTO.getLeverage());
        }
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);

    }

    @ApiOperation("用户修改杠杆")
    @PutMapping("")
    public ResultBody<Void> updateLeverage(@RequestBody @Validated @ApiParam(required = true) UpdateLeverageReq request) {

        boolean result = userLeverageAction.updateLeverage(request);
        if (!result) {
            return ResultBody.error(CommonEnum.NOT_ENOUGH_BALANCE);
        }
        return ResultBody.success();

//        Long uid = UserContextHolder.user.get().getId();
//        String lockKey = RedisCacheUtil.getUserOrdersLockKey(uid);
//        boolean locked = RedisLockUtil.instance().tryLock(lockKey, 1, USER_LOCK_LEASE_TIME_SECOND, TimeUnit.SECONDS);
//        if (locked) {
//            try {
//                boolean result = userLeverageAction.updateLeverage(request);
//                if (!result) {
//                    return ResultBody.error(CommonEnum.NOT_ENOUGH_BALANCE);
//                }
//            } catch (Exception e) {
//                log.error("update leverage error", e);
//                return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
//            } finally {
//                RedisLockUtil.instance().unLock(lockKey);
//            }
//        } else {
//            return ResultBody.error(CommonEnum.SERVER_BUSY);
//        }

    }


}

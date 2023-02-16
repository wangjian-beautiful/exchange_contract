package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.MaintenanceMarginRate;
import com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRatePO;
import com.bjs.contract.proto.maintenanceMarginRate.NominalValueRequest;

import java.math.BigDecimal;

/**
 * 维持保证金率
 *
 * @author bjs code generator
 * @date 2022-11-11 11:01:31
 */
public interface MaintenanceMarginRateService extends IService<MaintenanceMarginRate> {
    MaintenanceMarginRate getByNominalValue(String symbol, BigDecimal nominalValue);

    MaintenanceMarginRate symbolMaxLeverage(String symbol);
}

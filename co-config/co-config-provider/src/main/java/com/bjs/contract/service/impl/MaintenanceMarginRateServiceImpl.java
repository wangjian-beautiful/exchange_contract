package com.bjs.contract.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjs.contract.entity.MaintenanceMarginRate;
import com.bjs.contract.mapper.MaintenanceMarginRateMapper;
import com.bjs.contract.service.MaintenanceMarginRateService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 维持保证金率
 *
 * @author bjs code generator
 * @date 2022-11-11 11:01:31
 */
@Service
public class MaintenanceMarginRateServiceImpl extends ServiceImpl<MaintenanceMarginRateMapper, MaintenanceMarginRate> implements MaintenanceMarginRateService {


    @Override
    public MaintenanceMarginRate getByNominalValue(String symbol, BigDecimal nominalValue) {
        QueryChainWrapper<MaintenanceMarginRate> queryChainWrapper = super.query();
        return queryChainWrapper.eq("symbol", symbol)
                .lt("notional_value_begin", nominalValue)
                .ge("notional_value_end", nominalValue).one();
    }

    @Override
    public MaintenanceMarginRate symbolMaxLeverage(String symbol) {
        return super.query()
                .eq("symbol", symbol)
                .orderByDesc("max_leverage")
                .last("limit 1")
                .one();

    }
}

package com.bjs.contract.action;

import com.bjs.contract.controller.response.SymbolIntervalResp;
import com.bjs.contract.proto.maintenanceMarginRate.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author Watson
 */
@Component
public class MaintenanceMarginRateAction {

    @DubboReference
    MaintenanceMarginRateBizService maintenanceMarginRateBizService;


    public SymbolIntervalResp getSymbolInterval(String symbol) {
        SymbolMaxLeverageRequest request = SymbolMaxLeverageRequest.newBuilder().setSymbol(symbol).build();
        MaintenanceMarginRatePO maxLeverage = maintenanceMarginRateBizService.symbolMaxLeverage(request);
        SymbolIntervalResp resp = new SymbolIntervalResp();
        resp.setMaxLeverage(maxLeverage.getMaxLeverage().getValue());
        resp.setMinLeverage(1);
        return resp;
    }

}

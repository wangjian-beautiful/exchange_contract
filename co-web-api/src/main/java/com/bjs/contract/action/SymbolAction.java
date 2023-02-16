package com.bjs.contract.action;

import com.bijinsuo.common.utils.ProtoBeanUtils;
import com.bjs.contract.controller.response.CoSymbolInfoResp;
import com.bjs.contract.proto.contractConfig.ContractConfigBizService;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import com.bjs.contract.proto.contractConfig.ContractConfigSymbolRequest;
import com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRateBizService;
import com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRateMinReply;
import com.bjs.contract.proto.maintenanceMarginRate.MaintenanceMarginRateMinRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @author nike
 * @date 2023年01月03日 12:00
 */
@Component
@Slf4j
public class SymbolAction {

    @DubboReference(filter = "SetTokenFilter")
    private ContractConfigBizService contractConfigBizService;
    @DubboReference(filter = "SetTokenFilter")
    private MaintenanceMarginRateBizService maintenanceMarginRateBizService;


    public CoSymbolInfoResp getSymbolInfo(String symbol){
        ContractConfigSymbolRequest request=ContractConfigSymbolRequest.newBuilder().setSymbol(symbol).build();
        ContractConfigPO configPO= contractConfigBizService.getBySymbol(request);
        CoSymbolInfoResp resp= ProtoBeanUtils.toPojoBean(CoSymbolInfoResp.class,configPO);
        MaintenanceMarginRateMinReply reply= maintenanceMarginRateBizService.getMaintenanceMarginRateMin(MaintenanceMarginRateMinRequest.newBuilder().setSymbol(symbol).build());
        assert resp != null;
        resp.setMinRate(reply.getMinRate());
        return resp;
    }
}

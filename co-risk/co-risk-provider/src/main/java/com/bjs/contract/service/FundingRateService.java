package com.bjs.contract.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bjs.contract.entity.FundingRate;
import com.bjs.contract.proto.contractConfig.ContractConfigPO;
import tech.powerjob.worker.core.processor.TaskContext;

import java.util.Date;

/**
 * 资金费率
 *
 * @author bjs code generator
 * @date 2022-11-11 11:13:47
 */
public interface FundingRateService extends IService<FundingRate> {

    void getFundingRate(TaskContext taskContext) throws InterruptedException;


    void settleDistribute(Date settleTime, TaskContext taskContext) throws InterruptedException;

    void settle(ContractConfigPO contractConfigPO, Date settleTime, TaskContext taskContext);


    void settleDistributeV2(Date settleTime, TaskContext taskContext) throws InterruptedException;


    /**
     * 预测下期扣用户资金费率够不够,不够的发短信
     * @param settleTime 结算时间
     * @param taskContext
     */
    void fundingForecast(Date settleTime, TaskContext taskContext);

}

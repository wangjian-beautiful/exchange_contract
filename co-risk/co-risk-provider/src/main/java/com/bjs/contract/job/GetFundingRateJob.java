package com.bjs.contract.job;

import com.bjs.contract.service.FundingRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;

/**
 * 资金费率本来是自己不停的计算的方式生成，但是由于现在我们很多数据都是机器人数据，而且真实的持仓数据很少很少，所以没办法算出来。
 * 所以前期从okex 获取资金费率的数据。
 * 每分钟获取一次
 * @author Watson
 */

@Slf4j
@Component
public class GetFundingRateJob implements BasicProcessor {

    @Autowired
    FundingRateService fundingRateService;


    @Override
    public ProcessResult process(TaskContext context) throws Exception {
        log.info("start get funding rate job ");
        fundingRateService.getFundingRate(context);
        return new ProcessResult(true);
    }
}

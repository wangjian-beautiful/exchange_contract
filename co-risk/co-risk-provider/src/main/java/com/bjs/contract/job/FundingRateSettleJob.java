package com.bjs.contract.job;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.DaoTemplate;
import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.C;
import com.bjs.contract.service.FundingRateService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * 资金费率结算
 * 每8小时执行一次， 分别在 24:00:00  8:00:00  16:00:00
 * 定时任务触发之后，代码里面需要生成一个  24:00:00  8:00:00  16:00:00 的时间去查询数据库对应的期数数据
 *
 * @author Watson
 */

@Slf4j
@Component
public class FundingRateSettleJob implements BasicProcessor {

    @Resource
    FundingRateService fundingRateService;

    private final String JOB_PARAM_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public ProcessResult process(TaskContext context) throws Exception {
        String jobParams = context.getJobParams();
        Date settleTime;
        if (StrUtil.isNotEmpty(jobParams)) {
            //传时间就传时间戳
            settleTime = new SimpleDateFormat(JOB_PARAM_FORMAT).parse(jobParams);
        } else {
            //如果没传时间则使用当前执行时间的整点时间
            settleTime = fundingRateSettle();
        }
        fundingRateService.settleDistributeV2(settleTime, context);
        return new ProcessResult(true);
    }

    private Date fundingRateSettle() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}

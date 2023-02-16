package com.bjs.contract.job;

import com.bjs.contract.service.FundingRateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * 下次资金费率 扣减 可能不足的警报
 *
 * @author Watson
 */
@Slf4j
@Component
public class InsufficientAvailableAlertJob implements BasicProcessor {

    @Resource
    FundingRateService fundingRateService;

    @Override
    public ProcessResult process(TaskContext context) throws Exception {
        Date nextTime = getNextTime();
        fundingRateService.fundingForecast(nextTime,context);
        return new ProcessResult(true);
    }


    private Date getNextTime() {
        Date curDate = new Date();
        Date eight = getTodayWholePoint(8);
        Date sixteen = getTodayWholePoint(16);
        Date twentyFour = getTodayWholePoint(24);
        if (curDate.compareTo(eight) <= 0) {
            return eight;
        } else if (curDate.compareTo(sixteen) <= 0) {
            return sixteen;
        } else {
            return twentyFour;
        }

    }

    private static Date getTodayWholePoint(Integer hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.compareTo(getTodayWholePoint(17)) > 0);
    }
}

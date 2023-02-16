package com.bjs.contract.job;

import com.bijinsuo.common.mq.topic.TopicConstant;
import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.Set;

/**
 * 计算风险相关分发任务
 * 这个定时任务自身不做计算，只是做需要计算的用户进行分发。
 *
 * @author Watson
 */
@Slf4j
@Component
public class CalculateRiskJob implements BasicProcessor {

    @Resource
    RocketMQTemplate rocketMQTemplate;

    private final Integer GROUP_NUM = 3;

    @Override
    public ProcessResult process(TaskContext context) throws Exception {
        Set<Object> uidSet = RedisUtil.instance().sortRange(RedisCacheKey.POSITION_SORT_PNL_KEY, 0, Long.MAX_VALUE);
        //对 uid 进行打包,每GROUP_NUM个用户为一组
        StringBuilder uidBuilder = new StringBuilder();
        if (uidSet.size() > 0) {
            Iterator iterator = uidSet.iterator();
            Integer count = 0;
            while (iterator.hasNext()) {
                if (count == 0) {
                    uidBuilder.append(iterator.next().toString());
                } else {
                    uidBuilder.append("-").append(iterator.next().toString());
                }
                count++;
                if (count == GROUP_NUM) {
                    sendMq(uidBuilder.toString());
                    count = 0;
                    uidBuilder.replace(0, uidBuilder.length(), "");
                }
            }
            if (uidBuilder.length() != 0) {
                sendMq(uidBuilder.toString());
            }
        }
        return new ProcessResult(true);
    }


    private void sendMq(String msg) {
        rocketMQTemplate.asyncSend(TopicConstant.RISK_CALCULATE, msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("send risk uid suc");
            }

            @Override
            public void onException(Throwable e) {
                log.error("send risk uid fail", e);
            }
        });
    }
}

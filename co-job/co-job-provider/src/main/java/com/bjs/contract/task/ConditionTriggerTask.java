package com.bjs.contract.task;

import com.bijinsuo.common.redis.constant.RedisCacheKey;
import com.bijinsuo.common.redis.utils.RedisUtil;
import com.bjs.contract.service.ConditionTriggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tech.powerjob.worker.core.processor.ProcessResult;
import tech.powerjob.worker.core.processor.TaskContext;
import tech.powerjob.worker.core.processor.sdk.BasicProcessor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Winter
 */
@Slf4j
@Component
public class ConditionTriggerTask implements BasicProcessor {
  @Autowired
  private ConditionTriggerService conditionTriggerService;
  private final ConcurrentHashMap<String, BigDecimal> latestPriceMap = new ConcurrentHashMap<>();

  @Override
  public ProcessResult process(TaskContext context) {
    try {
      Map<String, Object> newPriceMap = RedisUtil.instance().getCacheMap(RedisCacheKey.LATEST_PRICE_KEY);
      if (!CollectionUtils.isEmpty(newPriceMap)) {
        for (Map.Entry<String, Object> entry : newPriceMap.entrySet()) {
          String symbol = entry.getKey();
          BigDecimal newPrice = new BigDecimal(entry.getValue().toString());

          if (newPrice.compareTo(latestPriceMap.getOrDefault(symbol, BigDecimal.ZERO)) != 0) {
            conditionTriggerService.scanTriggerOrders(symbol, latestPriceMap.getOrDefault(symbol, BigDecimal.ZERO), newPrice);
            latestPriceMap.put(symbol, newPrice);
          }
        }
      }
    } catch (Exception e) {
      log.error("出错了：", e);
    }
    return new ProcessResult(true);
  }
}

package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.CoinPair;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class CoinPairStatusConverter extends EnumTagConverter<CoinPair.Status> {
  @Override
  protected CoinPair.Status convertSafely(String source) {
    return CoinPair.Status.from(Integer.parseInt(source));
  }
}

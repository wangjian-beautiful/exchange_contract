package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.CoTriggerOrder;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class TradeTypeConverter extends EnumTagConverter<CoTriggerOrder.TradeType> {
  @Override
  protected CoTriggerOrder.TradeType convertSafely(String source) {
    return CoTriggerOrder.TradeType.from(source.toUpperCase());
  }
}

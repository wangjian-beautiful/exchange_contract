package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.CoTriggerOrder;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class TriggerTypeConverter extends EnumTagConverter<CoTriggerOrder.Type> {
  @Override
  protected CoTriggerOrder.Type convertSafely(String source) {
    return CoTriggerOrder.Type.from(source.toUpperCase());
  }
}

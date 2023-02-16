package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.CoTriggerOrder;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class TriggerPurposeConverter extends EnumTagConverter<CoTriggerOrder.TriggerType> {
  @Override
  protected CoTriggerOrder.TriggerType convertSafely(String source) {
    return CoTriggerOrder.TriggerType.from(source.toUpperCase());
  }
}

package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.CoTriggerOrder;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class TriggerStatusConverter extends EnumTagConverter<CoTriggerOrder.Status> {
  @Override
  protected CoTriggerOrder.Status convertSafely(String source) {
    return CoTriggerOrder.Status.from(source.toUpperCase());
  }
}

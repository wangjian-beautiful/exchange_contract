package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class PositionTypeConverter extends EnumTagConverter<Order.PositionType> {
  @Override
  protected Order.PositionType convertSafely(String source) {
    return Order.PositionType.from(Integer.parseInt(source));
  }
}

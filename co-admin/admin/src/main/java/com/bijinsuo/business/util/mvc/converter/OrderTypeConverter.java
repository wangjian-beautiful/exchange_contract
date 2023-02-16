package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class OrderTypeConverter extends EnumTagConverter<Order.Type> {
  @Override
  protected Order.Type convertSafely(String source) {
    return Order.Type.from(Integer.parseInt(source));
  }
}

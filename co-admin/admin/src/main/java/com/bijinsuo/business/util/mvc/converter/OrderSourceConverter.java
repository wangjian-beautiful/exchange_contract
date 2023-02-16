package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class OrderSourceConverter extends EnumTagConverter<Order.Source> {
  @Override
  protected Order.Source convertSafely(String source) {
    return Order.Source.from(Integer.parseInt(source));
  }
}

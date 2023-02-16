package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class OrderStatusConverter extends EnumTagConverter<Order.Status> {
  @Override
  protected Order.Status convertSafely(String source) {
    return Order.Status.from(Integer.parseInt(source));
  }
}

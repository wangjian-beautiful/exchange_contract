package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class OperateSideConverter extends EnumTagConverter<Order.OperateSide> {
  @Override
  protected Order.OperateSide convertSafely(String source) {
    return Order.OperateSide.valueOf(source.toUpperCase());
  }
}

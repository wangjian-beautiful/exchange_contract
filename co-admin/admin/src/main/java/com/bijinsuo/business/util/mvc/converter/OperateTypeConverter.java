package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class OperateTypeConverter extends EnumTagConverter<Order.OperateType> {
  @Override
  protected Order.OperateType convertSafely(String source) {
    return Order.OperateType.valueOf(source.toUpperCase());
  }
}

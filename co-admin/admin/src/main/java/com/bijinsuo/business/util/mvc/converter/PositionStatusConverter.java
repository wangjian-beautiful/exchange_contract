package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.Position;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class PositionStatusConverter extends EnumTagConverter<Position.Status> {
  @Override
  protected Position.Status convertSafely(String source) {
    return Position.Status.from(Integer.parseInt(source));
  }
}

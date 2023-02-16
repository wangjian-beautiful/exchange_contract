package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.CoConfig;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class CoConfigStatusConverter extends EnumTagConverter<CoConfig.Status> {
  @Override
  protected CoConfig.Status convertSafely(String source) {
    return CoConfig.Status.from(Integer.parseInt(source));
  }
}

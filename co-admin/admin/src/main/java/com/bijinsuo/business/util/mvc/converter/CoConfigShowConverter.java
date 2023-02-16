package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.business.entity.CoConfig;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class CoConfigShowConverter extends EnumTagConverter<CoConfig.Show> {
  @Override
  protected CoConfig.Show convertSafely(String source) {
    return CoConfig.Show.from(Integer.parseInt(source));
  }
}

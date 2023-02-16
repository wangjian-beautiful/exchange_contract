package com.bijinsuo.business.util.mvc.converter;

import com.bijinsuo.common.utils.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author Winter
 */
public abstract class EnumTagConverter<T> implements Converter<String, T> {
  @Override
  public T convert(String source) {
    if (StringUtils.isEmpty(source)) {
      return null;
    }
    return convertSafely(source);
  }

  protected abstract T convertSafely(String source);
}

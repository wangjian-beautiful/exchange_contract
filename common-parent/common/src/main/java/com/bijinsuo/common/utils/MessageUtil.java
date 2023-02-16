package com.bijinsuo.common.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Winter
 */
@Component
public class MessageUtil implements MessageSourceAware {
  private static MessageSource messageSource;

  @Override
  public void setMessageSource(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  public static String get(String key) {
    return get(key, key);
  }

  public static String get(String key, String defaultMessage) {
    return get(key, null, defaultMessage);
  }

  public static String get(String key, Object... args) {
    return get(key, key, args);
  }

  public static String get(String key, String defaultMessage, Object... args) {
    try {
      return messageSource.getMessage(key, args, defaultMessage, LocaleContextHolder.getLocale());
    } catch (Exception e) {
      return key;
    }
  }
}

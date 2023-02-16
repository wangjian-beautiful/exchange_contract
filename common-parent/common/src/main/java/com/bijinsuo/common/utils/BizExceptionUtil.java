package com.bijinsuo.common.utils;

import cn.hutool.core.util.ArrayUtil;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Winter
 */
public class BizExceptionUtil {
  private static final String SEPARATOR = "::";
  private static final Pattern PATTERN = Pattern.compile(SEPARATOR);

  private BizExceptionUtil() {
  }

  public static String extractErrorCodeWithArgs(Exception e) {
    String errorCode = CommonEnum.INTERNAL_SERVER_ERROR.getResultCode();
    if (e instanceof BizException) {
      BizException be = (BizException) e;
      errorCode = be.getErrorCode();
      if (be.getArgs() != null && be.getArgs().length > 0) {
        errorCode += SEPARATOR;
        errorCode += ArrayUtil.join(be.getArgs(), SEPARATOR);
      }
    }
    return errorCode;
  }

  public static BizException createFromErrorCodeWithArgs(String errorCodeWithArgs) {
    final var errorCodeAndArgs = PATTERN.split(errorCodeWithArgs);
    if (errorCodeAndArgs.length == 1) {
      return new BizException(errorCodeAndArgs[0], null);
    } else {
      String errorCode = errorCodeAndArgs[0];
      Object[] args = Arrays.copyOfRange(errorCodeAndArgs, 1, errorCodeAndArgs.length);
      return new BizException(errorCode, null).withArgs(args);
    }
  }
}

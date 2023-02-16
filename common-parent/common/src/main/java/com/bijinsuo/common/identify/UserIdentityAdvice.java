package com.bijinsuo.common.identify;

import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;
import com.google.gson.JsonParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * @author Winter
 */
@Aspect
@Component
public class UserIdentityAdvice {
  private final RedisTemplate<String, String> redisTemplate;

  public UserIdentityAdvice(RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Pointcut("execution(* com.bjs.contract.controller.*.*(..))")
  public void apiPoint() {
  }

  @Before("apiPoint()")
  public void doBefore(JoinPoint jp) {
    if (isPublicResource(jp)) {
      UserContextHolder.user.remove();
    } else {
      UserContextHolder.User user = UserContextHolder.user.get();
      if (user == null || StringUtils.isEmpty(user.getToken())) {
        throw new BizException(CommonEnum.USER_AUTHENTICATION_FAILED);
      }
      String userJsonStr = redisTemplate.opsForValue().get(UserIdentityConstant.REDIS_TOKEN_PREFIX + user.getToken());
      if (userJsonStr == null) {
        throw new BizException(CommonEnum.USER_AUTHENTICATION_FAILED);
      }
      user.setId(new JsonParser().parse(userJsonStr).getAsJsonObject().get(UserIdentityConstant.REDIS_UID_KEY).getAsLong());
    }
  }

  private static boolean isPublicResource(JoinPoint jp) {
    MethodSignature signature = (MethodSignature) jp.getSignature();
    PublicResource onMethod = signature.getMethod().getAnnotation(PublicResource.class);
    if (onMethod != null) {
      return true;
    }
    Annotation onClass = signature.getDeclaringType().getAnnotation(PublicResource.class);
    return onClass != null;
  }
}

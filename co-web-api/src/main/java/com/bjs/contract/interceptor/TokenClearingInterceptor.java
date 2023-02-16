package com.bjs.contract.interceptor;

import com.bijinsuo.common.identify.UserContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Winter
 */
@Component
public class TokenClearingInterceptor implements HandlerInterceptor {
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    UserContextHolder.clear();
  }
}

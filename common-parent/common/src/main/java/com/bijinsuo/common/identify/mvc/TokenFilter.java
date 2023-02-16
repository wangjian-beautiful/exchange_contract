package com.bijinsuo.common.identify.mvc;

import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.identify.UserIdentityConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Winter
 */
@Component
public class TokenFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    String token = req.getHeader(UserIdentityConstant.REQUEST_TOKEN_HEADER);
    UserContextHolder.User user = new UserContextHolder.User();
    if (!StringUtils.isEmpty(token)) {
      user.setToken(token);
    }
    UserContextHolder.user.set(user);
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }
}

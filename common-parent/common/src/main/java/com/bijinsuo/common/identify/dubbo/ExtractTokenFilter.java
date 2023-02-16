package com.bijinsuo.common.identify.dubbo;

import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.identify.UserIdentityConstant;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.springframework.util.StringUtils;


/**
 * @author Winter
 */
@Activate(group = CommonConstants.PROVIDER)
public class ExtractTokenFilter implements Filter {
  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    String token = invocation.getAttachment(UserIdentityConstant.RPC_TOKEN_KEY);
    String id = invocation.getAttachment(UserIdentityConstant.RPC_UID_KEY);
    if (!StringUtils.isEmpty(token)) {
      UserContextHolder.User user = new UserContextHolder.User();
      user.setToken(token);
      if (!StringUtils.isEmpty(id)) {
        user.setId(Long.parseLong(id));
      }
      UserContextHolder.set(user);
    }
    return invoker.invoke(invocation);
  }
}

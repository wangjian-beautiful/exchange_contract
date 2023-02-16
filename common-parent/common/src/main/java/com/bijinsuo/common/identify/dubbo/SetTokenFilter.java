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
@Activate(group = CommonConstants.CONSUMER)
public class SetTokenFilter implements Filter {
  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    UserContextHolder.User user = UserContextHolder.user.get();
    if (user != null) {
      if (user.getToken() != null) {
        invocation.setAttachment(UserIdentityConstant.RPC_TOKEN_KEY, user.getToken());
      }
      if (user.getId() != null) {
        invocation.setAttachment(UserIdentityConstant.RPC_UID_KEY, user.getId());
      }
    }
    return invoker.invoke(invocation);
  }
}

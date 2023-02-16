package com.bijinsuo.common.identify.dubbo;

import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.exception.BizException;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.util.StringUtils;


/**
 * @author Winter
 */
@Activate(group = CommonConstants.PROVIDER)
public class ExceptionFilter implements Filter {
  @Override
  public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
    Result result = invoker.invoke(invocation);
    if (result.hasException() && invoker.getInterface() != GenericService.class) {
      Throwable exception = result.getException();
      if (exception instanceof BizException) {
        
      }
    }
    return result;
  }
}

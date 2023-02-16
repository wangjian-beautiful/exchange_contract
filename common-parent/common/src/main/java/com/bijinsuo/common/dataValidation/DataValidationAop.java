package com.bijinsuo.common.dataValidation;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.bijinsuo.common.identify.UserContextHolder;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author nike
 * @date 2022年12月07日 14:30
 */
@Slf4j
@Aspect
@Component
public class DataValidationAop {


    @Pointcut("@annotation(com.bijinsuo.common.dataValidation.DataValidation)")
    public void dataValidation() {


    }


    @Before("dataValidation()")
    public void aroundAdvice(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (null == paramNames) {
            throw new BizException("请求参数为空");
        }
        //判断是否为post请求
        if ("POST".equals(request.getMethod())){
            var user = UserContextHolder.user.get();
            //请求的方法参数值 JSON 格式 null不显示
            if (joinPoint.getArgs().length > 0) {
                Object[] args = joinPoint.getArgs();
                for (Object arg : args) {
                    //请求参数类型判断过滤，防止JSON转换报错
                    if (arg instanceof HttpServletRequest || arg instanceof HttpServletResponse || arg instanceof MultipartFile) {
                        continue;
                    }
                    Map<String, Object> params = JSON.parseObject(JSON.toJSONString(arg));
                    checkSign(params, user.getToken());
                }
            }
        }
    }

    private static void checkSign(Map<String, Object> params, String token){

        log.info("参数：{}",params);
        String sign = params.get("sign")==null? "" : params.get("sign").toString();
        if(sign == null || sign.isEmpty()) {
           throw new BizException(CommonEnum.PARAMETER_ERROR.getResultCode(),CommonEnum.PARAMETER_ERROR.getResultMsg());
        }
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap<>(params);
        Set<Map.Entry<String, Object>> entrySet = sortedParams.entrySet();
        StringBuilder baseString = new StringBuilder();
        for (Map.Entry<String, Object> param : entrySet) {
            //去掉签名字段
            if(param.getKey().equals("sign")){
                continue;
            }
            baseString.append(param.getKey());
            if (param.getValue()!=null) {
                baseString.append(param.getValue());
            }
        }
        baseString.append(token);
        String newSign= SecureUtil.md5(baseString.toString());
        if (!newSign.equals(sign)){
            throw new BizException(CommonEnum.PARAMETER_ERROR.getResultCode(),CommonEnum.PARAMETER_ERROR.getResultMsg());
        }
    }
}

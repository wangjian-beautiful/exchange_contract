package com.bjs.contract.exception;

import com.bijinsuo.common.utils.MessageUtil;
import com.bijinsuo.common.utils.config.ResultBody;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * @author nike
 * @date 2022年11月09日 16:49
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResponseResult
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResultBody<Void> parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        log.error("", e);
        return ResultBody.error(CommonEnum.ERROR_PARAMETER.getResultCode(), MessageUtil.get(CommonEnum.ERROR_PARAMETER.getResultCode(), CommonEnum.ERROR_PARAMETER.getResultMsg(),e.getParameterName()));
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResponseResult
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultBody<Void> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        log.error("", e);
        return ResultBody.error(CommonEnum.ERROR_PARAMETER_BODY.getResultCode(), MessageUtil.get(CommonEnum.ERROR_PARAMETER_BODY.getResultCode(), CommonEnum.ERROR_PARAMETER_BODY.getResultMsg()));
    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return BaseResult
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBody<Void> parameterExceptionHandler(MethodArgumentNotValidException e) {
        log.error("", e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return  ResultBody.error(CommonEnum.ERROR_PARAMETER.getResultCode(), fieldError.getDefaultMessage());
            }
        }
        return  ResultBody.error(CommonEnum.ERROR_PARAMETER.getResultCode());

    }

    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return BaseResult
     */
    @ExceptionHandler(value = BindException.class)
    public ResultBody<Void> bindExceptionHandler(BindException e) {
        log.error("", e);
        // 获取异常信息
        StringBuilder errorMsg = new StringBuilder();
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        for (FieldError fieldError : exceptions.getFieldErrors()) {
            errorMsg.append("参数：")
                    .append(fieldError.getField())
                    .append("错误：")
                    .append(fieldError.getDefaultMessage())
                    .append(" ");
        }
        return  ResultBody.error(CommonEnum.ERROR_PARAMETER.getResultCode(), errorMsg.toString());
    }

    /**
     * 参数转换异常
     *
     * @param e 参数验证异常
     * @return BaseResult
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultBody<Void> constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error("", e);
        // 获取异常信息
        StringBuilder errorMessage = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            errorMessage.append(constraintViolation.getMessageTemplate()).append("\\n");
        }
        return  ResultBody.error(CommonEnum.ERROR_PARAMETER.getResultCode(), String.valueOf(errorMessage));
    }

    /**
     * 参数转换异常
     *
     * @param e 参数验证异常
     * @return BaseResult
     */
    @ExceptionHandler(value = ClassCastException.class)
    public ResultBody<Void> classCastExceptionHandler(ClassCastException e) {
        log.error("", e);
        // 获取异常信息
        String errorMessage = e.getMessage();
        return  ResultBody.error(CommonEnum.ERROR_PARAMETER.getResultCode(), errorMessage);
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(value = BizException.class)
    public ResultBody<Void> bizExceptionHandler(BizException e) {
        log.error("", e);
        return ResultBody.error(e.getErrorCode(),
            MessageUtil.get(e.getErrorCode(), e.getMessage(), e.getArgs()));
    }

    /**
     * 其他异常
     */
    @ExceptionHandler(value = Exception.class)
    public ResultBody<Void> exceptionHandler(Exception e) {
        log.error("", e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR.getResultCode(),
            MessageUtil.get(CommonEnum.INTERNAL_SERVER_ERROR.getResultCode(), CommonEnum.INTERNAL_SERVER_ERROR.getResultMsg()));

    }
}

package com.bijinsuo.common.utils.config;

import cn.hutool.json.JSONUtil;
import com.bijinsuo.common.utils.enums.CommonEnum;
import com.bijinsuo.common.utils.exception.BaseErrorInfoInterface;
import lombok.Data;

/**
 * @author nike
 * @date 2022年11月09日 16:45
 */
@Data
public class ResultBody<T> {
    public static final String DEFAULT_ERROR_CODE = "-1";
    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private T data;

    public ResultBody() {
    }

    public ResultBody(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }


    /**
     * 成功
     *
     * @return
     */
    public static ResultBody<Void> success() {
        return success(null);
    }

    /**
     * 成功
     *
     * @param data
     * @return
     */
    public static <T> ResultBody<T> success(T data) {
        ResultBody<T> rb = new ResultBody<>(CommonEnum.SUCCESS);
        rb.setData(data);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResultBody<T> error(BaseErrorInfoInterface errorInfo) {
        return error(errorInfo.getResultCode(), errorInfo.getResultMsg());
    }

    /**
     * 失败
     */
    public static <T> ResultBody<T> error(String code, String message) {
        ResultBody<T> rb = new ResultBody<>();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    /**
     * 失败
     */
    public static <T> ResultBody<Void> error(String message) {
        return error(DEFAULT_ERROR_CODE, message);
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}

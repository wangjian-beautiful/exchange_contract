package com.bijinsuo.common.utils.exception;

/**
 * @author nike
 * @date 2022年11月09日 16:48
 */
public interface BaseErrorInfoInterface {
    /** 错误码*/
    String getResultCode();

    /** 错误描述*/
    String getResultMsg();
}

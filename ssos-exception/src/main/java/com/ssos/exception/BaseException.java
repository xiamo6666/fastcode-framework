package com.ssos.exception;

/**
 * @ClassName: BaseException
 * @Description: 基本异常信息
 * @Author: xwl
 * @Date: 2019-05-14 17:14
 * @Vsersion: 1.0
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}

package com.fc.core.exception;

/**
 * @ClassName: ServiceException
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/5/6 12:37
 * @Vsersion: 1.0
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }
}

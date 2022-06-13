package com.fc.core.exception;


import com.fc.common.model.enums.CommonResultEnum;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:33
 */
public class ServiceException extends RuntimeException {
    private Integer code = 500;

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(CommonResultEnum errorEnum) {
        super(errorEnum.getResultMessage());
        this.code = errorEnum.getResultCode();
    }

    public ServiceException(String message) {
        super(message);
    }

    public Integer getCode() {
        return code;
    }
}

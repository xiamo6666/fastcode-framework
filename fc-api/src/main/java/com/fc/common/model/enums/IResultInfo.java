package com.fc.common.model.enums;


/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:30
 */
public interface IResultInfo {
    /**
     * Get error message.
     * @return error message
     */
    String getResultMessage();

    /**
     * Get error code.
     * @return error code
     */
    Integer getResultCode();
}

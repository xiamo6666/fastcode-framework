package com.fc.common.model.enums;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 9:25
 */
public enum CommonResultEnum implements IResultInfo {
    /**
     * 服务器错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    SUCCESS(0, "success"),
    LOGIN_FAILED(600100, "账号或密码错误!"),

    DUPLICATE_RECORD(600200, "数据重复"),

    PARAMETER_ERROR(600300, "参数不正确");


    private final String message;

    private final int code;

    CommonResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getResultMessage() {
        return this.message;
    }

    @Override
    public Integer getResultCode() {
        return this.code;
    }
}

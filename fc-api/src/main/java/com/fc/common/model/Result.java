package com.fc.common.model;


import com.fc.common.model.enums.CommonResultEnum;
import com.fc.common.model.enums.IResultInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局r结果返回返回
 *
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 9:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {
    private int code;

    private String message;

    private T data;

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(CommonResultEnum.SUCCESS.getResultCode())
                .message(CommonResultEnum.SUCCESS.getResultMessage())
                .data(data)
                .build();
    }

    public static <T> Result<T> success() {
        return Result.<T>builder()
                .code(CommonResultEnum.SUCCESS.getResultCode())
                .message(CommonResultEnum.SUCCESS.getResultMessage())
                .build();
    }


    public static <T> Result<T> error(String message) {
        return Result.<T>builder()
                .code(CommonResultEnum.INTERNAL_SERVER_ERROR.getResultCode())
                .message(message)
                .build();
    }

    public static <T> Result<T> error(IResultInfo iResultInfo) {
        return Result.<T>builder()
                .code(iResultInfo.getResultCode())
                .message(iResultInfo.getResultMessage())
                .build();
    }

    public static <T> Result<T> error(int code, String message) {
        return Result.<T>builder()
                .code(code)
                .message(message)
                .build();
    }

    public static <T> Result<T> error(IResultInfo iResultInfo, T data) {
        return Result.<T>builder()
                .code(iResultInfo.getResultCode())
                .message(iResultInfo.getResultMessage())
                .data(data)
                .build();
    }

}

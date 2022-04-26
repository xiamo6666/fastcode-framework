package com.fc.common.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {

    public static final int CODE_SUCCESS = 0;
    public static final String MESSAGE_SUCCESS = "success";

    private int code;

    private String message;

    private T data;

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .code(CODE_SUCCESS)
                .message(MESSAGE_SUCCESS)
                .data(data)
                .build();
    }

    public static Result<String> success() {
        return Result.<String>builder()
                .code(0)
                .message(MESSAGE_SUCCESS)
                .build();
    }

    public static Result<String> error() {
        return Result.<String>builder()
                .code(500)
                .message("Internal Server Error")
                .build();
    }

    public static Result<String> error(String message) {
        return Result.<String>builder()
                .code(500)
                .message(message)
                .build();
    }

    public static Result<String> error(int code, String message) {
        return Result.<String>builder()
                .code(code)
                .message(message)
                .build();
    }

}

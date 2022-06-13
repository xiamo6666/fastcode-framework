package com.fc.core.interceptor;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fc.common.model.Result;
import com.fc.common.model.enums.CommonResultEnum;
import com.fc.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;

import javax.validation.ConstraintViolationException;
import java.io.EOFException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理机制
 * @Author: xwl Lxz
 * @Date: 2018-12-26 13:20
 * @Vsersion: 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获参数校验异常
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public Result<?> handleBindExceptionAndMethodArgumentNotValidException(Exception e) {
        List<FieldError> fieldErrors = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if (e instanceof BindException bindException) {
            fieldErrors = bindException.getBindingResult().getFieldErrors();
        }
        if (e instanceof MethodArgumentNotValidException memberException) {

            fieldErrors = memberException.getBindingResult().getFieldErrors();
        }
        if ( fieldErrors.isEmpty()) {
            return Result.error("[参数校验异常]：fieldError is not present");
        }
        fieldErrors.forEach(l ->
                map.put(l.getField(), l.getDefaultMessage()));
        return Result.error(CommonResultEnum.PARAMETER_ERROR, map);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException e) {
        return Result.error(e.getMessage());
    }

    /**
     * 捕获文件结束异常
     */
    @ExceptionHandler(EOFException.class)
    public Result<String> handleEOFException(EOFException e) {
        log.error("[文件结束异常]", e);
        return Result.error(e.getMessage());
    }

    /**
     * 捕获Http请求方法不支持异常
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result<String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("[Http请求方法不支持异常]", e);
        return Result.error(e.getMessage());
    }

    /**
     * 捕获空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<String> handleNullPointerException(NullPointerException e) {
        log.error("[空指针异常]", e);
        return Result.error(e.getMessage());
    }

    /**
     * 捕获参数不可读异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Throwable cause = e.getCause();
        if (cause instanceof InvalidFormatException invalidFormatException) {
            Object value = invalidFormatException.getValue();
            return Result.error("【" + value + "】填写不规范或超出长度限制");
        }
        return Result.error(e.getMostSpecificCause().getMessage());
    }

    /**
     * 捕获未知异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<String> handleServiceException(ServiceException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MultipartException.class)
    public Result<String> multipartException(MultipartException e) {
        log.error(e.getMessage(), e);
        return Result.error("文件上传请求头异常");
    }


    /**
     * 主键重复
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<String> duplicateKeyException(DuplicateKeyException e) {
        return Result.error(CommonResultEnum.DUPLICATE_RECORD);
    }


    /**
     * 参数转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        Object value = e.getValue();
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        return Result.error("无法对参数:[" + value + "]进行转换,请检查改值是否正常!!!");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return Result.error("数据长度超出限制");
    }

    /**
     * 捕获未知异常
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

}
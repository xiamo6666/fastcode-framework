package com.ssos.base.exception;

import com.ssos.base.model.DataResult;
import com.ssos.exception.BaseException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理机制
 * @Author: xwl
 * @Date: 2018-12-26 13:20
 * @Vsersion: 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理 @Valid 校验异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public DataResult handlderBinException(Exception e) {
        if (e instanceof BindException) {
            List<ObjectError> allErrors = ((BindException) e).getBindingResult().getAllErrors();
            if (allErrors != null && allErrors.size() > 0) {
                return DataResult.error(allErrors.get(0).getDefaultMessage());
            }
        } else if (e instanceof MethodArgumentNotValidException) {
            List<ObjectError> allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
            if (allErrors != null && allErrors.size() > 0) {
                return DataResult.error(allErrors.get(0).getDefaultMessage());
            }
        }
        return DataResult.error("请仔细按照原型操作好嘛");
    }

    /**
     * 处理自定义baseException
     *
     * @return
     */
    @ExceptionHandler({BaseException.class})
    public DataResult handlerBaseException(BaseException e) {
        return DataResult.error(e.getMessage());
    }

    /**
     * 处理shiro授权异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({AuthorizationException.class})
    public DataResult handlerAuthorizationException(AuthorizationException e) {
        return DataResult.error("你没有权限进行该操作");
    }

    /**
     * 处理request 请求错误异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public DataResult handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return DataResult.error(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public DataResult NullPointerException(NullPointerException e) {
        return DataResult.error("空指针异常，请联系管理员");
    }
}

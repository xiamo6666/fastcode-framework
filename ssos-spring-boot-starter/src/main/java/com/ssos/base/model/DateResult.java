package com.ssos.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: DateResult
 * @Description: return Object
 * @Author: xwl
 * @Date: 2018-12-26 14:34
 * @Vsersion: 1.0
 */
@Data
public class DateResult<T>  implements Serializable {
    private static final long serialVersionUID = 11456465129L;
    private  boolean state = Boolean.TRUE;
    private  String msg = "操作成功";
    private T result;
    private static final DateResult ERROR_DATARESULT = new DateResult(false,"操作失败");

    private DateResult(){

    }
    private DateResult(T result){
        this.result = result;
    }
    private DateResult(T result,String msg){
        this.result = result;
        this.msg = msg;
    }
    private DateResult(boolean state,String msg){
        this.state = state;
        this.msg = msg;
    }
    public static <T> DateResult ok(){
        return new DateResult();
    }
    public static <T> DateResult<T> ok(T result){
        return new DateResult<>(result);
    }
    public static <T> DateResult<T> ok(T result,String msg){
        return new DateResult<>(result,msg);
    }
    public static <T> DateResult<T> error(){
        return ERROR_DATARESULT;
    }
    public static <T> DateResult<T> error(String msg){
        return new DateResult<>(false,msg);
    }
}

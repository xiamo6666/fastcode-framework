package com.ssos.base.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: DataResult
 * @Description: return Object
 * @Author: xwl
 * @Date: 2018-12-26 14:34
 * @Vsersion: 1.0
 */
@Data
public class DataResult<T>  implements Serializable {
    private static final long serialVersionUID = 11456465129L;
    private  boolean state = Boolean.TRUE;
    private  String msg = "操作成功";
    private T result;
    private static final DataResult ERROR_DATARESULT = new DataResult(false,"操作失败");

    private DataResult(){

    }
    private DataResult(T result){
        this.result = result;
    }
    private DataResult(T result, String msg){
        this.result = result;
        this.msg = msg;
    }
    private DataResult(boolean state, String msg){
        this.state = state;
        this.msg = msg;
    }
    public static <T> DataResult ok(){
        return new DataResult();
    }
    public static <T> DataResult<T> ok(T result){
        return new DataResult<>(result);
    }
    public static <T> DataResult<T> ok(T result, String msg){
        return new DataResult<>(result,msg);
    }
    public static <T> DataResult<T> error(){
        return ERROR_DATARESULT;
    }
    public static <T> DataResult<T> error(String msg){
        return new DataResult<>(false,msg);
    }
}

package com.fc.utils.excel.exception;

import java.util.Map;

/**
 * @ClassName: ExcelValidateException
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/5/10 16:34
 * @Vsersion: 1.0
 */

public class ExcelValidateException extends RuntimeException {

    private Map<Integer, String> params;

    public ExcelValidateException(Map<Integer, String> params) {
        super();
        this.params = params;
    }

    @Override
    public String toString() {
        return "ExcelValidateException{" +
                "params=" + params +
                '}';
    }
}

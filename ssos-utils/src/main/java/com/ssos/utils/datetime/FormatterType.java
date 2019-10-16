package com.ssos.utils.datetime;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

/**
 * @ClassName: FormatterType
 * @Description: 时间类型转换格式
 * @Author: xwl
 * @Date: 2019-07-11 08:58
 * @Vsersion: 1.0
 */
@Getter
@AllArgsConstructor
public enum FormatterType {
    /**
     * 基本类型
     */
    DATETIME(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
    DATE(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


    private DateTimeFormatter dateTimeFormatter;

}

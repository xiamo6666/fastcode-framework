package com.ssos.base.utils;

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
    STANDARD(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    private DateTimeFormatter dateTimeFormatter;

}

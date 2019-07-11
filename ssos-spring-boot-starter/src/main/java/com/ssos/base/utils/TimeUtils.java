package com.ssos.base.utils;

import java.time.LocalDateTime;

/**
 * @ClassName: TimeUtils
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-07-10 17:58
 * @Vsersion: 1.0
 */
public class TimeUtils {

    /**
     * 获取当前时间
     *
     * @param formatterType
     * @return
     */
    public static String getLocalTIme(FormatterType formatterType) {
        return LocalDateTime.now().format(formatterType.getDateTimeFormatter());
    }

}

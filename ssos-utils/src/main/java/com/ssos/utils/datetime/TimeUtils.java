package com.ssos.utils.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @ClassName: TimeUtils
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-08-17 17:56
 * @Vsersion: 1.0
 */
public class TimeUtils {
    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getDateTime() {
        return LocalDateTime.now().format(FormatterType.DATETIME.getDateTimeFormatter());
    }

    public static String getDateTime(FormatterType formatterType) {
        return LocalDateTime.now().format(formatterType.getDateTimeFormatter());
    }

    /**
     * 获取当前年份
     *
     * @return
     */
    public static Integer getYear() {
        return LocalDateTime.now().getYear();
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static Integer getMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    public static Integer getNowDay() {
        return LocalDateTime.now().getDayOfMonth();
    }

    /**
     * 获取过去的时间
     *
     * @param month
     * @return
     */
    public static String getMinusMonths(long month) {
        return LocalDateTime.now().minusMonths(month).format(FormatterType.DATETIME.getDateTimeFormatter());
    }


    /**
     * 获取当前
     *
     * @return
     */
    public static String getFirstDay() {
        LocalDate today = LocalDate.now();
        return LocalDate.of(today.getYear(), today.getMonth(), 1).format(FormatterType.DATETIME.getDateTimeFormatter());
    }

    public static String getLastDay() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).format(FormatterType.DATETIME.getDateTimeFormatter());
    }

}

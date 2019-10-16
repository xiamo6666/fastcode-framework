package com.ssos.utils.calculate;

import java.math.BigDecimal;

/**
 * @ClassName: CalculateUtils
 * @Description: dto
 * @Author: xwl
 * @Date: 2019/9/2 5:35 下午
 * @Vsersion: 1.0
 */
    public class CalculateUtils {
    /**
     * 计算两数百分比
     *
     * @param a
     * @param b
     * @return
     */
    public static float compute(long a, long b) {
        float division = (float) a / b;
        BigDecimal bg = new BigDecimal(division * 100);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}

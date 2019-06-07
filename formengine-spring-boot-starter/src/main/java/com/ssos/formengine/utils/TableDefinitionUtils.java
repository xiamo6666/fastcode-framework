package com.ssos.formengine.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @ClassName: TableDefinitionUtils
 * @Description: 提供表定义的一些操作
 * @Author: xwl
 * @Date: 2019-05-20 12:00
 * @Vsersion: 1.0
 */
public final class TableDefinitionUtils {
    /**
     * 根据传入参数指定标识（比如说用户所在地址、组织架构等等得到一个唯一标识）
     * @param param
     * @return
     */
    public static Integer mark(String... param){
       return  Objects.hash(Arrays.hashCode(param));
    }
}

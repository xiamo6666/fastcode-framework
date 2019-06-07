package com.ssos.mybatilsUtils.provider;

import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BaseMapperProvider
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-20 13:42
 * @Vsersion: 1.0
 */
public abstract class BaseMapperProvider {

    /**
     * 根据泛型获取表名
     * @param providerContext
     * @return
     */
//    protected String getTableName(ProviderContext providerContext){
//        mapperType = providerContext.getMapperType();
//        ParameterizedType type = (ParameterizedType) mapperType.getGenericInterfaces()[0];
//        return toUnderline(((Class)type.getActualTypeArguments()[0]).getSimpleName());
//    }
    /**
     * 大写转下划线
     * @param s
     * @return
     */
    protected   String toUnderline(String s) {
        if (s != null && s.length()>0) {
            StringBuilder builder = new StringBuilder(s.length());
            char c = s.charAt(0);
            if (Character.isUpperCase(c)) {
                builder.append(Character.toLowerCase(c));
            } else {
                builder.append(Character.toLowerCase(c));
            }
            for (int i = 1; i < s.length(); i++) {
                char c1 = s.charAt(i);
                if (Character.isUpperCase(c1)) {
                    builder.append("_");
                    builder.append(Character.toLowerCase(c1));
                } else {
                    builder.append(c1);
                }
            }
            return builder.toString();
        }else {
            return s;
        }
    }

    /**
     * 获取参数中所有的字段名字
     * @param parameterObject
     * @return
     */
    protected List<Map<String,String>> getFieldAll(Object parameterObject){
        Field[] declaredFields = parameterObject.getClass().getDeclaredFields();
        Assert.notEmpty(declaredFields,"参数不能为空");
        Map<String,String> fieldMap = new LinkedHashMap<>();
        Map<String,String> fieldAllMap = new LinkedHashMap<>();
        List<Map<String,String>> list = new ArrayList();
        for (Field field:declaredFields){
            String fieldName = field.getName();
            field.setAccessible(true);
            if (fieldName.equalsIgnoreCase("serialVersionUID")){
                continue;
            }
            try {
                    Object o = field.get(parameterObject);
                    if (o != null) {
                        fieldMap.put(toUnderline(fieldName),fieldName);
                    }
                fieldAllMap.put(toUnderline(fieldName),fieldName);
            } catch (IllegalAccessException e) {
                throw  new RuntimeException("未知错误、请联系管理员");
            }
        }
        list.add(fieldMap);
        list.add(fieldAllMap);
        Assert.notEmpty(list,"参数中没有必要的值");
        return list;
    }

    /**
     * 根据传入的参数获取表名
     * @param parameterObject
     * @return
     */
    protected String getTableName(Object parameterObject ){
      return  toUnderline(parameterObject.getClass().getSimpleName());
    }
}

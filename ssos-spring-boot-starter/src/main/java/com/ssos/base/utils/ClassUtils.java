package com.ssos.base.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @ClassName: ClassUtils
 * @Description: 通过反射注入传过来的token赋值给principals
 * @Author: xwl
 * @Date: 2019-01-02 17:16
 * @Vsersion: 1.0
 */
public class ClassUtils {
    private static Logger logger = LoggerFactory.getLogger(ClassUtils.class);
    public static void par(String authorization){
        Subject object = SecurityUtils.getSubject();
        Class clazz = object.getClass();
        Class superclass = clazz.getSuperclass();
        try {
            Field principals = superclass.getDeclaredField("principals");
            principals.setAccessible(true);
            principals.set(object,new SimplePrincipalCollection(authorization,""));
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }
    /**
     * 设置注解中的字段值
     *
     * @param annotation 要修改的注解实例
     * @param fieldName  要修改的注解字段名
     * @param value      要设置的值
     */
    public static void setAnnotationValue(Annotation annotation, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        InvocationHandler handler = Proxy.getInvocationHandler(annotation);
        Field hField = handler.getClass().getDeclaredField("memberValues");
        hField.setAccessible(true);
        Map memberValues = (Map) hField.get(handler);
        memberValues.put(fieldName, value);
    }
}

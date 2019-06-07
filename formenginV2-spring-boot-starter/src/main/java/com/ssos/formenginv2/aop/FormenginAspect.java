package com.ssos.formenginv2.aop;

import com.ssos.exception.BaseException;
import com.ssos.formenginv2.annotation.Formengin;
import com.ssos.formenginv2.entity.BaseField;
import com.ssos.formenginv2.entity.FieldValue;
import com.ssos.formenginv2.mapper.FieldValueMapper;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: FormenginAspect
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-24 16:38
 * @Vsersion: 1.0
 */
@Aspect
@Component
@SuppressWarnings("all")
public class FormenginAspect {
    @Autowired
    private FieldValueMapper fieldValueMapper;

    @Around("@within(formengin)")
    public Object around(ProceedingJoinPoint joinPoint, Formengin formengin) {
        String key = joinPoint.toString();
        Object[] args = joinPoint.getArgs();
        ClassInfo classInfo = ReflectionCache.getCache(key);
        try {
            if (!ReflectionCache.isExitsCache(key)) {
                ReflectionCache.addCache(joinPoint);
            }
            Type type = classInfo.getType();
            //返回值是泛型
            //判断当前类型是不是泛型类型
            if (type instanceof ParameterizedType) {
                //获取到返回值泛型class类型
                Class resultClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
                if (BaseField.class.isAssignableFrom(resultClass)) {
                    Collection<BaseField> proceed = (List<BaseField>) joinPoint.proceed();
                    proceed.forEach(p -> p.setJson(fieldValueMapper.findValue(formengin.name(), p.getId())));
                    return proceed;
                }
            }
        } catch (Throwable throwable) {
            throw new BaseException(throwable.getMessage());
        }

        //添加
        try {
            Class<?>[] parameterTypes = classInfo.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                if (BaseField.class.isAssignableFrom(parameterType)) {
                    joinPoint.proceed();
                    BaseField baseField = ((BaseField) joinPoint.getArgs()[0]);
                    fieldValueMapper.insert(FieldValue.of(baseField.getId(), formengin.name(), baseField.getJson()));
                } else {
                    //如果是其他方法直接通过
                    return joinPoint.proceed();
                }
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    private final static class ReflectionCache {
        private static Map<String, ClassInfo> cacheMap = new ConcurrentHashMap<>();

        public static void addCache(ProceedingJoinPoint joinPoint) {
            try {
                //反射获取 methodInocation 这个里面有当前执行方法的所有信息
                Field field = joinPoint.getClass().getDeclaredField("methodInvocation");
                field.setAccessible(true);
                ProxyMethodInvocation o = (ProxyMethodInvocation) field.get(joinPoint);
                //得到当前需要代理的这个方法
                Method method = o.getMethod();
                //获取方法参数
                Class<?>[] parameterTypes = method.getParameterTypes();

                //获取到这个方法的返回值
                Type type = method.getGenericReturnType();
                cacheMap.put(joinPoint.toString(), ClassInfo.of(parameterTypes, type));
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        private static ClassInfo getCache(String cacheName) {
            return cacheMap.get(cacheName);
        }

        private static boolean isExitsCache(String cacheName) {
            return cacheMap.containsKey(cacheName);
        }
    }

    @Getter
    @Setter
    @RequiredArgsConstructor(staticName = "of")
    private static final class ClassInfo {
        //参数
        @NonNull
        private Class<?>[] parameterTypes;

        //返回值类型
        @NonNull
        private Type type;
    }
}

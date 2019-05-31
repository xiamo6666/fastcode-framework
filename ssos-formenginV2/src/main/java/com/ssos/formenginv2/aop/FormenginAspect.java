package com.ssos.formenginv2.aop;

import com.ssos.exception.BaseException;
import com.ssos.formenginv2.annotation.Formengin;
import com.ssos.formenginv2.entity.BaseField;
import com.ssos.formenginv2.entity.FieldValue;
import com.ssos.formenginv2.mapper.FieldMapper;
import com.ssos.formenginv2.vo.FieldVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.ProxyGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

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
    private FieldMapper fieldMapper;

    @Around("@within(formengin)")
    public Object around(ProceedingJoinPoint joinPoint, Formengin formengin) {

        Object[] args = joinPoint.getArgs();
        try {
            //反射获取 methodInocation 这个里面有当前执行方法的所有信息
            Field field = joinPoint.getClass().getDeclaredField("methodInvocation");
            field.setAccessible(true);
            ProxyMethodInvocation o = (ProxyMethodInvocation) field.get(joinPoint);
            //得到当前需要代理的这个方法
            Method method = o.getMethod();
            //查询
            //获取到这个方法的返回值
            Type type = method.getGenericReturnType();
            //判断当前类型是不是泛型类型
            if (type instanceof ParameterizedType) {
                //获取到返回值泛型class类型
                Class resultClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
                if (BaseField.class.isAssignableFrom(resultClass)) {
                    List<BaseField> proceed = (List<BaseField>) joinPoint.proceed();
                    proceed.forEach(p -> p.setJson(fieldMapper.findValue(formengin.value(), p.getId())));
                    return proceed;
                }
            }

            //添加
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> parameterType : parameterTypes) {
                if (BaseField.class.isAssignableFrom(parameterType)) {
                    joinPoint.proceed();
                    BaseField baseField = ((BaseField) joinPoint.getArgs()[0]);
                    fieldMapper.insert(FieldValue.of(baseField.getId(), formengin.value(), baseField.getJson()));
                }
            }
        } catch (Throwable throwable) {
            throw  new BaseException("json");
        }
        return null;
    }
}

package com.ssos.formenginv2.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: Formengin
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-24 15:41
 * @Vsersion: 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Formengin {

    String name();

    String notes();
}

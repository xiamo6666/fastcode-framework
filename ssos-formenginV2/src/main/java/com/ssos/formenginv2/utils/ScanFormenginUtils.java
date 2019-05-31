package com.ssos.formenginv2.utils;

import com.ssos.formenginv2.annotation.Formengin;
import com.ssos.formenginv2.mapper.FieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @ClassName: ScanFormenginUtils
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-28 17:48
 * @Vsersion: 1.0
 */
@Component
public class ScanFormenginUtils implements ApplicationListener<ApplicationReadyEvent> {
    public  void init() {
        ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider =
                new ClassPathScanningCandidateComponentProvider(false);
        classPathScanningCandidateComponentProvider.addIncludeFilter(new AnnotationTypeFilter(Formengin.class));
        Set<BeanDefinition> beanDefinition = classPathScanningCandidateComponentProvider
                .findCandidateComponents("com.ssos.formenginv2.service.impl");
        beanDefinition.forEach(p -> {
            try {
                Formengin annotation = Class.forName(p.getBeanClassName()).getAnnotation(Formengin.class);
                System.out.println(annotation.value());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Autowired
    private FieldMapper mapper;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println(mapper);
        init();
    }
}

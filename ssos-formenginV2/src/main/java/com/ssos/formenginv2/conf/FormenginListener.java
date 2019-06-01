package com.ssos.formenginv2.conf;

import com.ssos.formenginv2.annotation.Formengin;
import com.ssos.formenginv2.entity.FieldRelation;
import com.ssos.formenginv2.mapper.FieldRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @ClassName: FormenginListener
 * @Description: 初始化包含动态字段的类
 * @Author: xwl
 * @Date: 2019-05-31 15:58
 * @Vsersion: 1.0
 */
@Component
public class FormenginListener implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private FieldRelationMapper fieldRelationMapper;

    private void init() {
        ClassPathScanningCandidateComponentProvider classPathScanningCandidateComponentProvider =
                new ClassPathScanningCandidateComponentProvider(false);
        classPathScanningCandidateComponentProvider.addIncludeFilter(new AnnotationTypeFilter(Formengin.class));
        Set<BeanDefinition> beanDefinition = classPathScanningCandidateComponentProvider
                .findCandidateComponents("com.ssos.formenginv2.service.impl");
        Set<String> allTableName = fieldRelationMapper.findAllTableName();
        beanDefinition.forEach(p -> {
            try {
                Formengin formengin = Class.forName(p.getBeanClassName()).getAnnotation(Formengin.class);
                String tableName = formengin.name();
                if (allTableName.size() == 0 || !allTableName.contains(tableName)) {
                    fieldRelationMapper.insert(FieldRelation.ofTableName(tableName).setTableMark(formengin.notes()));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        init();
    }
}

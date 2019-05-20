package com.ssos.formengine.service;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.service.impl.SonAutoDefinitionDTO;
import com.ssos.formengine.vo.FormAllShowVO;
import com.ssos.formengine.vo.FormOneShowVO;

/**
 * @ClassName: AutoDefinitionService
 * @Description: 动态字段定义
 * @Author: xwl
 * @Date: 2019-05-10 18:10
 * @Vsersion: 1.0
 */
public interface AutoDefinitionService {
    /**
     * 生成表定义以及动态创建表
     * 如果在list中有子表则遍历创建子表
     *
     * @param definitionDTO
     */
    void add(AutoDefinitionDTO definitionDTO);

    /**
     *单独为添加子表提交的接口
     */
    void sonAdd(SonAutoDefinitionDTO sonAutoDefinition);

    /**
     * 通过X定义来查找当前字段和值(根据标识来查找)
     *
     * @param mark 标识
     * @return
     */
    FormAllShowVO showtable(String mark);

    /**
     * 通过表名和id明细到具体一条数据,如果有子类还要加载子类
     *
     * @param tableName
     * @param id
     * @return
     */
    FormOneShowVO showOnetable(String tableName, Long id);

    /**
     * 更改表定义
     * 同时同步表结果更改
     */
    default void updateDefinition() {

    }
}

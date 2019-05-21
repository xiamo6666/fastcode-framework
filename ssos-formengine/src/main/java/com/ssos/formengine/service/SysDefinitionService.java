package com.ssos.formengine.service;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.dto.SonAutoDefinitionDTO;
import com.ssos.formengine.dto.UpdateDefinitionDTO;
import com.ssos.formengine.vo.FieldShowVO;
import com.ssos.formengine.vo.FieldVO;
import com.ssos.formengine.vo.SysDefinitionVO;

import java.util.List;

/**
 * @ClassName: SysDefinitionService
 * @Description: 表定义已经表结构的更改
 * @Author: xwl
 * @Date: 2019-05-20 16:57
 * @Vsersion: 1.0
 */
public interface SysDefinitionService {
    /**
     * 更改表定义
     * 同时同步表结果更改
     * （原则不是修改，而是在原来的基础上进行添加操作）
     *
     * @param updateDefinitionDTO
     */
    void sysUpdateDefinition(UpdateDefinitionDTO updateDefinitionDTO);

    /**
     * 查询所有父表（后期可能会加一些查询条件）
     */
    List<SysDefinitionVO> SysDefinition();

    /**
     * 根据definition的id
     *
     * @param id
     * @return
     */
    List<FieldVO> findFieldById(Long id);

    /**
     * 生成表定义以及动态创建表
     * 如果在list中有子表则遍历创建子表
     *
     * @param definitionDTO
     */
    void add(AutoDefinitionDTO definitionDTO);

    /**
     * 单独为添加子表提交的接口
     */
    void sonAdd(SonAutoDefinitionDTO sonAutoDefinition);
}

package com.ssos.formenginv2.service;

import com.ssos.formenginv2.dto.FormFieldAddDto;
import com.ssos.formenginv2.vo.FieldRelationVo;
import com.ssos.formenginv2.vo.FieldVo;

import java.util.List;

/**
 * @ClassName: FormenginConfigService
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 10:01
 * @Vsersion: 1.0
 */
public interface FormenginConfigService {
    /**
     *查询所有的动态表单列表
     * @return
     */
    List<FieldRelationVo> findAll();

    /**
     * 根据列表id获取当前的动态字段
     * @param id
     * @return
     */
    List<FieldVo> findFieleById(Long id);


    /**
     * 给表单添加动态字段
     * @param dto
     */
    void addField(FormFieldAddDto dto);
}

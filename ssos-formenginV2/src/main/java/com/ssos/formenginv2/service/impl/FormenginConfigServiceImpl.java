package com.ssos.formenginv2.service.impl;

import com.ssos.exception.BaseException;
import com.ssos.formenginv2.dto.FormFieldAddDto;
import com.ssos.formenginv2.entity.FormField;
import com.ssos.formenginv2.mapper.FieldMapper;
import com.ssos.formenginv2.mapper.FieldRelationMapper;
import com.ssos.formenginv2.mapper.FormFieldMapper;
import com.ssos.formenginv2.service.FormenginConfigService;
import com.ssos.formenginv2.vo.FieldInfoVo;
import com.ssos.formenginv2.vo.FieldRelationVo;
import com.ssos.formenginv2.vo.FieldVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: FormenginConfigServiceImpl
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 10:04
 * @Vsersion: 1.0
 */
@Service
public class FormenginConfigServiceImpl implements FormenginConfigService {

    @Autowired
    private FieldRelationMapper relationMapper;

    @Autowired
    private FormFieldMapper formFieldMapper;

    @Autowired
    private FieldMapper fieldMapper;


    @Override
    public List<FieldRelationVo> findAll() {

        return relationMapper.findAll();
    }


    @Override
    public List<FieldVo> findFieleById(Long id) {
        return relationMapper.findFieleById(id);
    }


    @Override
    public void addField(FormFieldAddDto dto) {
        Set<Long> allFormId = relationMapper.findAllFormId();
        Long formId = dto.getFormId();
        if (!(allFormId.contains(formId))) {
            throw new BaseException("该表单不存在");
        }
        Set<Long> fieldId = formFieldMapper.findFieldId(dto.getFormId());
        if (fieldId.size() > 0) {
            throw new BaseException("当前表单已经添加过动态字段了");
        }

        Set<Long> allFieldId = fieldMapper.findAllId();
        if (!allFieldId.containsAll(dto.getFieldId())) {
            throw new BaseException("要添加的动态字段不存在");
        }
        try {
            dto.getFieldId().forEach(p -> formFieldMapper.insert(FormField.of(dto.getFormId(), p)));
        } catch (Exception e) {
            throw new BaseException("添加失败");
        }
    }

    @Override
    public List<FieldInfoVo> loadField(String mark) {
        return relationMapper.loadField(mark);
    }
}

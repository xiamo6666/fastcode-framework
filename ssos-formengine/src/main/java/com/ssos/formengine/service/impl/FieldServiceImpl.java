package com.ssos.formengine.service.impl;

import com.ssos.formengine.dto.FieldDTO;
import com.ssos.formengine.entity.Field;
import com.ssos.formengine.mapper.FieldMapper;
import com.ssos.formengine.service.FieldService;
import com.ssos.formengine.vo.FieldVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * @ClassName:.l
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 18:16
 * @Vsersion: 1.0
 */
public class FieldServiceImpl implements FieldService {
    @Autowired
    FieldMapper fieldMapper;

    @Override
    public void add(FieldDTO fieldDTO) {
        Field field  = new Field();
        BeanUtils.copyProperties(field,field);
        fieldMapper.insert(field);
    }

    @Override
    public List<FieldVO> findAll() {
        return fieldMapper.findAll();
    }

    @Override
    public List<FieldVO> findByIds(Set<Long> ids) {
        return fieldMapper.findByIds(ids);
    }
}

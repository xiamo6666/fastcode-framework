package com.ssos.formenginv2.mapper;

import com.ssos.formenginv2.entity.FormField;
import com.ssos.mybatilspro.mapper.BaseMapper;

import java.util.Set;


/**
 * @ClassName: FormFieldMapper
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 13:50
 * @Vsersion: 1.0
 */
public interface FormFieldMapper extends BaseMapper<FormField> {

    Set<Long> findFieldId(Long formId);
}

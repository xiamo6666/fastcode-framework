package com.ssos.formengine.service;

import com.ssos.formengine.dto.FieldDTO;
import com.ssos.formengine.vo.FieldVO;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: FieldService
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-10 18:00
 * @Vsersion: 1.0
 */
public interface FieldService {
    void add(FieldDTO fieldDTO);

    List<FieldVO> findAll();

    List<FieldVO> findByIds(Set<Long> ids);

}

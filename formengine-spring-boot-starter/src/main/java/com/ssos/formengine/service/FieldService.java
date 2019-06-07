package com.ssos.formengine.service;

import com.ssos.formengine.dto.FieldDTO;
import com.ssos.formengine.entity.Field;
import com.ssos.formengine.vo.FieldVO;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: FieldService
 * @Description: 字段定义标准，一般添加之后就不能再去更改预留功能
 * @Author: xwl
 * @Date: 2019-05-10 18:00
 * @Vsersion: 1.0
 */
public interface FieldService {
    void add(FieldDTO fieldDTO);

    List<FieldVO> findAll();

    List<FieldVO> findByIds(Set<Long> ids);

    /**
     * 修改字段，对于已经动态生成的表无效
     * @param field
     */
    void update(Field field);

    /**
     * 批量删除字段
     * @param ids
     * @return
     */
    Integer delete(List<Integer> ids);

}

package com.ssos.formengine.mapper;

import com.ssos.formengine.entity.Field;
import com.ssos.formengine.vo.FieldVO;
import com.ssos.mybatilsUtils.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: FieldMapper
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-13 14:56
 * @Vsersion: 1.0
 */
public interface FieldMapper extends BaseMapper<Field> {

    List<FieldVO> findAll();

    List<FieldVO> findByIds(@Param("ids") Set<Long> ids);

}

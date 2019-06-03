package com.ssos.formenginv2.mapper;

import com.ssos.formenginv2.entity.FieldRelation;
import com.ssos.formenginv2.vo.FieldRelationVo;
import com.ssos.formenginv2.vo.FieldVo;
import com.ssos.mybatilsUtils.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: FieldRelationMapper
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-31 16:29
 * @Vsersion: 1.0
 */
public interface FieldRelationMapper extends BaseMapper<FieldRelation> {

    /**
     * 查询所有的表名
     *
     * @return
     */
    Set<String> findAllTableName();

    /**
     * 查询所有类容
     * @return
     */
    List<FieldRelationVo> findAll();


    List<FieldVo> findFieleById(Long id);


    Set<Long> findAllFormId();
}

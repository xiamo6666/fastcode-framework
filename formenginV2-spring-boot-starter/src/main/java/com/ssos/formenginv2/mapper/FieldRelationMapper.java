package com.ssos.formenginv2.mapper;

import com.ssos.formenginv2.entity.FieldRelation;
import com.ssos.formenginv2.vo.FieldInfoVo;
import com.ssos.formenginv2.vo.FieldRelationVo;
import com.ssos.formenginv2.vo.FieldVo;
import com.ssos.mybatilspro.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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
     *
     * @return
     */
    List<FieldRelationVo> findAll();

    /**
     * 根据formId获取FieldId集合
     *
     * @param id
     * @return
     */
    List<FieldVo> findFieleById(Long id);

    /**
     * 查找所有的formId
     *
     * @return
     */

    Set<Long> findAllFormId();

    /**
     * 根据标识加载字段
     *
     * @param mark
     * @return
     */
    List<FieldInfoVo> loadField(@Param("mark") String mark);
}

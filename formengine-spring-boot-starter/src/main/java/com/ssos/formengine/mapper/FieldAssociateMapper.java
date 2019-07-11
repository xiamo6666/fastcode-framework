package com.ssos.formengine.mapper;

import com.ssos.formengine.entity.FieldAssociate;
import com.ssos.formengine.vo.FieldVO;
import com.ssos.formengine.vo.SysDefinitionVO;
import com.ssos.mybatilspro.mapper.BaseMapper;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: FieldAssociateMapper
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-13 15:35
 * @Vsersion: 1.0
 */
public interface FieldAssociateMapper  extends BaseMapper<FieldAssociate> {
    /**
     *
     * 根据定义表id查询详细的字段名称
     * @param id
     * @return
     */
    List<FieldVO> findFieldById(Long id);

    /**
     * 根据定义id查找 字段id
     * @param id
     * @return
     */
    Set<Long> findFieldIdById(Long id);



    List<SysDefinitionVO> findAll();


  }

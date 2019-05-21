package com.ssos.formengine.service;

import com.ssos.formengine.dto.AutoDefinitionDTO;
import com.ssos.formengine.dto.SonAutoDefinitionDTO;
import com.ssos.formengine.vo.FormAllShowVO;
import com.ssos.formengine.vo.FormOneShowVO;

/**
 * @ClassName: AutoDefinitionService
 * @Description: 动态字段定义
 * @Author: xwl
 * @Date: 2019-05-10 18:10
 * @Vsersion: 1.0
 */
public interface AutoDefinitionService {

    /**
     * 通过X定义来查找当前字段和值(根据标识来查找)
     *
     * @param mark 标识
     * @return
     */
    FormAllShowVO showtable(String mark);

    /**
     * 通过表名和id明细到具体一条数据,如果有子类还要加载子类
     *
     * @param tableName
     * @param id
     * @return
     */
    FormOneShowVO showOnetable(String tableName, Long id);
}

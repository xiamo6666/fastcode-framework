package com.ssos.formengine.support;

import com.ssos.formengine.utils.CreateSql;
import com.ssos.formengine.vo.FieldVO;

/**
 * @ClassName: FieldIdType
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 21:06
 * @Vsersion: 1.0
 */
public interface FieldIdType {
    /**
     * 创建sql
     * @param p
     * @param createSql
     * @return
     */
    CreateSql createSql(FieldVO p, CreateSql createSql);
}

package com.ssos.formengine.support.impl;

import com.ssos.formengine.support.FieldIdType;
import com.ssos.formengine.utils.CreateSql;
import com.ssos.formengine.vo.FieldVO;

/**
 * @ClassName: DateFieldIdTypeImpl
 * @Description: 时间框sql
 * @Author: xwl
 * @Date: 2019-06-03 21:14
 * @Vsersion: 1.0
 */
public class DateFieldIdTypeImpl implements FieldIdType {
    @Override
    public CreateSql createSql(FieldVO p, CreateSql createSql) {
        return createSql.Field(p.getFieldMark())
                .TIMESTAMP().NOTNULL()
                .DATEDEFAULT()
                .COMMENT(p.getFieldName());
    }
}

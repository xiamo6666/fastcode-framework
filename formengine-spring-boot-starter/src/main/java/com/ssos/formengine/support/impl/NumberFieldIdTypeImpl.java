package com.ssos.formengine.support.impl;

import com.ssos.formengine.support.FieldIdType;
import com.ssos.formengine.utils.CreateSql;
import com.ssos.formengine.vo.FieldVO;

/**
 * @ClassName: NumberFieldIdTypeImpl
 * @Description: 数字框sql
 * @Author: xwl
 * @Date: 2019-06-03 21:13
 * @Vsersion: 1.0
 */
public class NumberFieldIdTypeImpl implements FieldIdType {
    @Override
    public CreateSql createSql(FieldVO p, CreateSql createSql) {
        return createSql.Field(p.getFieldMark())
                .INT().NOTNULL()
                .INTDEFAULT()
                .COMMENT(p.getFieldName());
    }
}

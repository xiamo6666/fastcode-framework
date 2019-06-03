package com.ssos.formengine.support.impl;

import com.ssos.formengine.support.FieldIdType;
import com.ssos.formengine.utils.CreateSql;
import com.ssos.formengine.vo.FieldVO;

/**
 * @ClassName: InputFieldIdTypeImpl
 * @Description: 文本框sql
 * @Author: xwl
 * @Date: 2019-06-03 21:12
 * @Vsersion: 1.0
 */
public class InputFieldIdTypeImpl implements FieldIdType {
    @Override
    public CreateSql createSql(FieldVO p, CreateSql createSql) {
      return  createSql.Field(p.getFieldMark())
                .VARCHAR(p.getFieldMax() + "")
                .NOTNULL()
                .CHARDEFAULT()
                .COMMENT(p.getFieldName());
    }
}

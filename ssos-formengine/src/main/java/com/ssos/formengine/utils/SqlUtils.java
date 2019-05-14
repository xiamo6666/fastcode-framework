package com.ssos.formengine.utils;


import com.ssos.formengine.vo.FieldVO;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @ClassName: SqlUtils
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-13 17:31
 * @Vsersion: 1.0
 */
public class SqlUtils {
    public static String sqlHelper(Supplier<? extends List<FieldVO>> var) {
        Objects.requireNonNull(var);
        List<FieldVO> fieldVOS = var.get();
        StringBuilder sql = new StringBuilder();
        fieldVOS.forEach(p -> {
            Integer typeId = p.getFieldTypeId();
            if (typeId == 1) {
                varField(sql, p.getFieldMark(), p.getFieldMax() + "");
            } else if (typeId == 2) {
                numberField(sql, p.getFieldMark());
            } else if (typeId == 3) {
                dateField(sql, p.getFieldMark());
            } else {
                varField(sql, p.getFieldMark(), p.getFieldMax() + "");
            }
        });
        return sql.deleteCharAt(sql.length() - 1).toString();
    }

    static StringBuilder varField(StringBuilder sql, String fieldName, String size) {
        return sql.append(fieldName + " varchar(" + size + ") not null default '',");
    }

    static StringBuilder numberField(StringBuilder sql, String fieldName) {
        return sql.append(fieldName + " int not null default 0,");
    }

    static StringBuilder dateField(StringBuilder sql, String fieldName) {
        return sql.append(fieldName + " timestamp not null default current_timestamp ,");
    }
}

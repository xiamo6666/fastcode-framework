package com.ssos.formengine.utils;


import com.ssos.formengine.support.FieldIdType;
import com.ssos.formengine.support.impl.DateFieldIdTypeImpl;
import com.ssos.formengine.support.impl.InputFieldIdTypeImpl;
import com.ssos.formengine.support.impl.NumberFieldIdTypeImpl;
import com.ssos.formengine.vo.FieldVO;

import java.util.*;

/**
 * @ClassName: SqlUtils
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-05-13 17:31
 * @Vsersion: 1.0ØØØØ
 */
public final class SqlUtils {

    public static final Map<Integer, FieldIdType> sqlType = new HashMap<Integer, FieldIdType>() {{
        put(1, new NumberFieldIdTypeImpl());
        put(2, new InputFieldIdTypeImpl());

        put(3, new DateFieldIdTypeImpl());
    }};

    public static final String sqlUpdate(FieldVO var) {
        CreateSql createSql = new CreateSql();
        CreateSql logic = logic(var, createSql);
        StringBuilder sql = logic.createSql;
        return sql.deleteCharAt(sql.length() - 1).toString();
    }


    /**
     * 提供FieldVO字段自动生成创建表的sql
     *
     * @param var
     * @return
     * @Param isSon
     */
    public static final String sqlHelper(List<FieldVO> var, boolean isSon) {
        Objects.requireNonNull(var);
        //生成sql语句
        CreateSql createSql = new CreateSql();
        var.forEach(p -> logic(p, createSql));
        //判断是否是子表，如果是子表还要添加上parent_id 字段
        if (isSon) {
            createSql.Field("parent_id").INT().NOTNULL().INTDEFAULT().COMMENT("父表id");
        }
        StringBuilder sql = createSql.createSql;
        return sql.deleteCharAt(sql.length() - 1).toString();
    }

    /**
     * 生成sql语句
     *
     * @param p
     * @param createSql
     * @return
     */
    private static CreateSql logic(FieldVO p, CreateSql createSql) {
        Integer typeId = p.getFieldTypeId();
        FieldIdType fieldIdType = sqlType.get(typeId);
        return fieldIdType == null ? sqlType.get(1).createSql(p, createSql) :
                fieldIdType.createSql(p, createSql);
    }


    /**
     * 转换表名创建、避免暴露表名导致sql注入攻击
     *
     * @param tableName
     * @return
     */
    public final static String caseTableName(String tableName) {
        return "auto_" + Objects.hash(Arrays.hashCode(tableName.getBytes()));
    }

}

package com.ssos.formengine.utils;


import com.ssos.formengine.entity.Field;
import com.ssos.formengine.vo.FieldVO;

import java.util.Arrays;
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
public final class SqlUtils {

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
    public static final String sqlHelper( List<FieldVO> var, boolean isSon) {
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
     * @param p
     * @param createSql
     * @return
     */
    private static CreateSql logic(FieldVO p, CreateSql createSql) {
        Integer typeId = p.getFieldTypeId();
        if (typeId == 1) {
            createSql.Field(p.getFieldMark())
                    .VARCHAR(p.getFieldMax() + "")
                    .NOTNULL()
                    .CHARDEFAULT()
                    .COMMENT(p.getFieldName());
        } else if (typeId == 2) {
            createSql.Field(p.getFieldMark())
                    .INT().NOTNULL()
                    .INTDEFAULT()
                    .COMMENT(p.getFieldName());
        } else if (typeId == 3) {
            createSql.Field(p.getFieldMark())
                    .TIMESTAMP().NOTNULL()
                    .DATEDEFAULT()
                    .COMMENT(p.getFieldName());
        } else {
            createSql.Field(p.getFieldMark())
                    .VARCHAR(p.getFieldMax() + "")
                    .NOTNULL().CHARDEFAULT()
                    .COMMENT(p.getFieldName());
        }
        return createSql;
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


    /**
     * @ClassName: CreateSql
     * @Description: TODD
     * @Author: xwl
     * @Date: 2019-05-16 15:55
     * @Vsersion: 1.0
     */
    private final static class CreateSql {

        private void add(String sql) {
            createSql.append(sql);
        }

        private final StringBuilder createSql = new StringBuilder();

        private CreateSql VARCHAR(String size) {
            add(" VARCHAR(" + size + ") ");
            return this;
        }

        private CreateSql NOTNULL() {
            add(" NOT NULL ");
            return this;
        }

        private CreateSql COMMENT(String comment) {
            add(" COMMENT '" + comment + "' ,");
            return this;
        }

        private CreateSql INT() {
            add(" INT ");
            return this;
        }

        private CreateSql INTDEFAULT() {
            add(" DEFAULT 0 ");
            return this;
        }

        private CreateSql CHARDEFAULT() {
            add(" DEFAULT '' ");
            return this;
        }

        private CreateSql DATEDEFAULT() {
            add(" default current_timestamp ");
            return this;
        }

        private CreateSql Field(String field) {
            add(" " + field + " ");
            return this;
        }

        private CreateSql TIMESTAMP() {
            add(" timestamp ");
            return this;
        }
    }
}

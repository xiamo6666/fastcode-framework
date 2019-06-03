package com.ssos.formengine.utils;

/**
 * @ClassName: CreateSql
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-06-03 21:09
 * @Vsersion: 1.0
 */
public class CreateSql {
    public void add(String sql) {
        createSql.append(sql);
    }

    public final StringBuilder createSql = new StringBuilder();

    public CreateSql VARCHAR(String size) {
        add(" VARCHAR(" + size + ") ");
        return this;
    }

    public CreateSql NOTNULL() {
        add(" NOT NULL ");
        return this;
    }

    public CreateSql COMMENT(String comment) {
        add(" COMMENT '" + comment + "' ,");
        return this;
    }

    public CreateSql INT() {
        add(" INT ");
        return this;
    }

    public CreateSql INTDEFAULT() {
        add(" DEFAULT 0 ");
        return this;
    }

    public CreateSql CHARDEFAULT() {
        add(" DEFAULT '' ");
        return this;
    }

    public CreateSql DATEDEFAULT() {
        add(" default current_timestamp ");
        return this;
    }

    public CreateSql Field(String field) {
        add(" " + field + " ");
        return this;
    }

    public CreateSql TIMESTAMP() {
        add(" timestamp ");
        return this;
    }
}

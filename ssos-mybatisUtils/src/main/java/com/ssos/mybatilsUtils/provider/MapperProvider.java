package com.ssos.mybatilsUtils.provider;

import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @ClassName: MapperProvider
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-19 15:37
 * @Vsersion: 1.0
 */
public class MapperProvider extends  BaseMapperProvider {


    public String insert(Object parameterObject, ProviderContext providerContext){
        Assert.notNull(parameterObject,"参数不能为空");
        String tableName = getTableName(parameterObject);
        SQL sql = new SQL();
        sql = sql.INSERT_INTO(tableName);
        Map<String, String> fieldAll = getFieldAll(parameterObject).get(0);
        for (Map.Entry e :fieldAll.entrySet()){
            sql.VALUES((String) e.getKey(),"#{"+e.getValue()+"}");
        }
        return sql.toString();
    }

   public String update(Object parameterObject, ProviderContext providerContext){
        Assert.notNull(parameterObject,"参数不能为空");
        SQL sql = new SQL();
       String tableName = getTableName(parameterObject);
       sql = sql.UPDATE(tableName);
       Map<String, String> fieldAll = getFieldAll(parameterObject).get(0);
       for (Map.Entry e :fieldAll.entrySet()){
           if (e.getKey().equals("id")){
               sql.WHERE(e.getKey()+"="+"#{"+e.getValue()+"}");
           }else{
               sql.SET( e.getKey()+"="+"#{"+e.getValue()+"}");
           }
       }
       return sql.toString();
   }
   public String select(Object parameterObject){
       Assert.notNull(parameterObject,"参数不能为空");
       SQL sql = new SQL();

       String tableName = getTableName(parameterObject);
       sql = sql.FROM(tableName);
       for (Map.Entry e :getFieldAll(parameterObject).get(1).entrySet()){
           sql.SELECT((String) e.getKey());
       }

       for (Map.Entry e :getFieldAll(parameterObject).get(0).entrySet()){
               sql.WHERE(e.getKey() +" = #{"+e.getValue()+"}");
       }
       return sql.toString();
   }
}

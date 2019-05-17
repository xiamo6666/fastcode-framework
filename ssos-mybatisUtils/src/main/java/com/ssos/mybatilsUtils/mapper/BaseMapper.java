package com.ssos.mybatilsUtils.mapper;

import com.ssos.mybatilsUtils.provider.MapperProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: BaseMapper
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-19 15:38
 * @Vsersion: 1.0
 */
public interface BaseMapper<T> {

    @InsertProvider(
            method = "insert",
            type = MapperProvider.class)
    @Options(useGeneratedKeys = true)
    @Transactional(rollbackFor = Exception.class)
    boolean insert(T var1);

    @UpdateProvider(
            method = "update",
            type = MapperProvider.class)
    @Transactional(rollbackFor = Exception.class)
    boolean update(T var2);

    @SelectProvider(
            method = "select",
            type = MapperProvider.class)
    List<T> select(T var3);
}

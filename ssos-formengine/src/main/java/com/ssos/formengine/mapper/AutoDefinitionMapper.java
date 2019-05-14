package com.ssos.formengine.mapper;

import com.ssos.formengine.entity.AutoDefinition;
import com.ssos.mybatilsUtils.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AutoDefinitionMapper extends BaseMapper<AutoDefinition> {

    void  autoCreateTable(@Param("table") String table,@Param("sql") String sql);
}

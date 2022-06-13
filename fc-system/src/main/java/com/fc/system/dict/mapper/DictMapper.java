package com.fc.system.dict.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:48
 */
public interface DictMapper {
    /**
     * <p>
     * 根据字典索引id获取字典值数量
     *</p>
     * @param dictIndexId 字典索引id
     * @return int 字段值个数
     */
    int getDictInfoCount(@Param("dictIndexId") Long dictIndexId);
}

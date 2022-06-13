package com.fc.system.dict.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.query.PageDTO;
import com.fc.system.auto.entity.DictIndex;
import com.fc.system.auto.entity.DictInfo;
import com.fc.system.dict.model.dto.DictIndexDTO;
import com.fc.system.dict.model.dto.DictInfoDTO;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:51
 */
public interface DictService {
    /**
     * 添加字段索引
     *
     * @param dictIndexDto {@link DictInfoDTO}
     */
    void addDictIndex(DictIndexDTO dictIndexDto);

    /**
     * 分页查询字段索引
     *
     * @param pageDto 分页dto
     * @return {@link IPage<DictIndex>}
     */
    IPage<DictIndex> pageDictIndexList(PageDTO pageDto);

    /**
     * 添加字典值
     *
     * @param dictInfoDto {@link DictInfoDTO}
     */
    void addDictInfo(DictInfoDTO dictInfoDto);

    /**
     * 通过字段索引获取字典值list
     *
     * @param dictIndexKey 字典索引key
     * @return 字典值列表
     */
    List<DictInfo> getDictInfoList(String dictIndexKey);

    /**
     * 获取字典值
     *
     * @param dictIndexKey 字典索引key
     * @param dictKey      字典值key
     * @return 字典值
     */
    String getDictInfoByDictInfoKey(String dictIndexKey, String dictKey);

    /**
     * 删除字典值
     *
     * @param dictInfoId 字典值id
     */
    void deleteDictInfo(Long dictInfoId);

    /**
     * 删除字典索引
     *
     * @param dictIndexId 字典索引id
     */
    void deleteDictIndex(Long dictIndexId);

}

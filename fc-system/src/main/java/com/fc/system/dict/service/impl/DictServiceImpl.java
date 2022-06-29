package com.fc.system.dict.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.GlobalConstant;
import com.fc.common.model.query.PageDTO;
import com.fc.core.exception.ServiceException;
import com.fc.core.utils.PageDtoUtils;
import com.fc.system.auto.entity.DictIndex;
import com.fc.system.auto.entity.DictInfo;
import com.fc.system.auto.service.DictIndexAutoService;
import com.fc.system.auto.service.DictInfoAutoService;
import com.fc.system.dict.mapper.DictMapper;
import com.fc.system.dict.model.dto.DictIndexDTO;
import com.fc.system.dict.model.dto.DictInfoDTO;
import com.fc.system.dict.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:51
 */
@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictIndexAutoService dictIndexAutoService;
    @Autowired
    private DictInfoAutoService dictInfoAutoService;
    @Resource
    private DictMapper dictMapper;

    @Autowired
    @Qualifier("objectRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addDictIndex(DictIndexDTO dictIndexDto) {
        DictIndex dictIndex = new DictIndex();
        BeanUtils.copyProperties(dictIndexDto, dictIndex);
        dictIndexAutoService.save(dictIndex);
        updateDictCache();

    }

    @Override
    public IPage<DictIndex> pageDictIndexList(PageDTO pageDto) {
        return dictIndexAutoService
                .page(PageDtoUtils.convert(pageDto));
    }

    @Override
    public void addDictInfo(DictInfoDTO dictInfoDto) {
        DictInfo dictInfo = new DictInfo();
        BeanUtils.copyProperties(dictInfoDto, dictInfo);
        dictInfoAutoService.save(dictInfo);
        updateDictCache();
    }

    @Override
    public List<DictInfo> getDictInfoList(List<String> dictIndexKeys) {
        return dictInfoAutoService.lambdaQuery()
                .in(DictInfo::getDictIndexKey, dictIndexKeys)
                .list();
    }

    @Override
    public String getDictInfoByDictInfoKey(String dictIndexKey, String dictKey) {
        DictInfo dictInfo = dictInfoAutoService.lambdaQuery()
                .eq(DictInfo::getDictIndexKey, dictIndexKey)
                .eq(DictInfo::getDictKey, dictKey)
                .one();
        if (ObjectUtils.isEmpty(dictInfo)) {
            throw new ServiceException("未找到目标值!!!");
        }
        return dictInfo.getDictValue();

    }

    @Override
    public void deleteDictInfo(Long dictInfoId) {
        dictInfoAutoService.removeById(dictInfoId);
        updateDictCache();

    }

    @Override
    public void deleteDictIndex(Long dictIndexId) {
        if (dictMapper.getDictInfoCount(dictIndexId) > 0) {
            throw new ServiceException("该字典索引下具有字典值,不允许删除");
        }
        dictIndexAutoService.removeById(dictIndexId);
        updateDictCache();

    }

    private void updateDictCache() {
        //删除缓存
        redisTemplate.delete(GlobalConstant.DICT_CACHE_KEY);
        //从数据库获取最新数据
        List<DictInfo> dictInfos = dictInfoAutoService.list();
        Map<String, Map<String, String>> dictMaps = dictInfos.stream()
                .collect(Collectors.groupingBy(DictInfo::getDictIndexKey,
                        Collectors.toMap(DictInfo::getDictKey, DictInfo::getDictValue)));

        //从新放入Redis缓存
        redisTemplate.opsForHash().putAll(GlobalConstant.DICT_CACHE_KEY, dictMaps);


    }
}

package com.fc.core.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.PageResult;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:28
 */
public class PageResultUtils {
    public static <T> PageResult<T> convert(IPage<T> page) {
        PageResult<T> objectPageResult = new PageResult<>();
        objectPageResult.setCurrent(page.getCurrent());
        objectPageResult.setSize(page.getSize());
        objectPageResult.setTotal(page.getTotal());
        objectPageResult.setRecords(page.getRecords());
        return objectPageResult;
    }
}

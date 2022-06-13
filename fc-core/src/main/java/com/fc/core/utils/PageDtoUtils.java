package com.fc.core.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.common.model.query.PageDTO;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:28
 */
public class PageDtoUtils {

    /**
     * 根据id进行倒叙或者正序排序(全局)
     *
     * @param dto pageDto
     * @return {@link Page}
     */
    public static <T> Page<T> convert(PageDTO dto) {
        Page<T> page = com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO.<T>of(dto.getCurrent(), dto.getSize());
        return dto.getAsc() ? page : page.addOrder(OrderItem.desc("id"));
    }
}

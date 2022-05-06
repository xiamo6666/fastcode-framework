package com.fc.utils.excel.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName: CustomReadListener
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/5/6 13:11
 * @Vsersion: 1.0
 */
@Slf4j
public class CustomReadListener<T> implements ReadListener<T> {
    @Override
    public void invoke(T data, AnalysisContext context) {
        if (MapUtil.isEmpty((Map<?, ?>) data)) {
            System.out.println(data);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println(context);
    }
}

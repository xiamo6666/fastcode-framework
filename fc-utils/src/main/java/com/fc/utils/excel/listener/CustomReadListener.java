package com.fc.utils.excel.listener;

import cn.hutool.core.map.MapUtil;
import cn.hutool.extra.validation.ValidationUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.fc.utils.excel.exception.ExcelValidateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @ClassName: CustomReadListener
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/5/6 13:11
 * @Vsersion: 1.0
 */
@Slf4j

public class CustomReadListener<T> implements ReadListener<T> {
    /**
     * 自增序号
     */
    private final AtomicInteger attempts = new AtomicInteger(0);

    /**
     * 每次读取100行数据
     */
    private final int BATCH_COUNT = 100;
    private List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public Consumer<List<T>> consumer;

    private final Map<Integer, String> validateMessages = new HashMap<>();

    public CustomReadListener(Consumer<List<T>> consumer) {
        this.consumer = consumer;
    }



    @Override
    public void invoke(T data, AnalysisContext context) {
        //验证数据合法性
        if (validate(data)) {
            cachedDataList.add(data);
        }

        if (cachedDataList.size() >= BATCH_COUNT) {
            consumer.accept(cachedDataList);
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (CollectionUtils.isNotEmpty(cachedDataList)) {
            consumer.accept(cachedDataList);
        }
        if (MapUtil.isNotEmpty(validateMessages)) {
            throw new ExcelValidateException(validateMessages);
        }
    }

    /**
     * 校验参数
     *
     * @param data
     * @return
     */
    private boolean validate(T data) {
//        attempts.incrementAndGet();
//        Set<ConstraintViolation<T>> violationSet = ValidationUtil.validate(data, Default.class);
//        if (violationSet.isEmpty()) {
//            return true;
//        }
//        violationSet.forEach(ConstraintViolation::getMessage);
//        String message = violationSet.stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.joining(";"));
//        validateMessages.put(attempts.incrementAndGet(), message);
        return false;
    }
}

package com.fc.utils.recursion;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:41
 */
public class RecursionUtils {
    /**
     * <p>
     * 给定list数据 转成一颗树形结构
     * <p/>
     * 默认parentId = 0
     *
     * @param list T extends {@link RecursionModel}
     * @param <T>  T extends {@link RecursionModel}
     * @return {@link List<RecursionModel>} 树形结构
     */
    public static <T extends RecursionModel<Long, T>> List<T> recursionConvert(List<T> list) {
        return recursionConvert(list, 0L);

    }


    public static <E, T extends RecursionModel<E, T>> List<T> recursionConvert(List<T> list, E parentId) {
        if (CollectionUtil.isEmpty(list)) {
            return new ArrayList<>();
        }
        //筛选根节点
        List<T> rootPermissions = list.stream()
                .filter(
                        p -> p.getParentId().equals(parentId)
                )
                .toList();
        return rootPermissions.stream()
                .peek(
                        p -> p.setChildren(recursion(p, list))
                )
                .toList();
    }


    private static <E, T extends RecursionModel<E, T>> List<T> recursion(T permissionVO, List<T> list) {
        return list.stream()
                .filter(p -> p.getParentId().equals(permissionVO.getId()))
                .peek(p -> p.setChildren(recursion(p, list)))
                .sorted(Comparator.comparing(RecursionModel::getSort))
                .toList();
    }
}

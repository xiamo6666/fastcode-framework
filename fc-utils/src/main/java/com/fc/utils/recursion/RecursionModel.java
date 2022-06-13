package com.fc.utils.recursion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:33
 */
@Data
public abstract class RecursionModel<E, T> {
    @Schema(description = "id")
    private E id;

    @Schema(description = "上级权限id")
    private E parentId;


    @Schema(description = "排序")
    private Integer sort;


    @Schema(description = "下级权限")
    private List<T> children;
}

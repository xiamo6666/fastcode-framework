package com.fc.system.category.model.vo;

import com.fc.utils.recursion.RecursionModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/20 11:32
 */
@Data
public class CategoryVO extends RecursionModel<Long, CategoryVO> {
    @Schema(description = "分类名称")
    private String categoryName;

    @Schema(description = "备注")
    private String remark;
}

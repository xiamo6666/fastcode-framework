package com.fc.system.category.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/20 11:28
 */
@Data
public class CategoryDTO {

    @Schema(description = "分类名称")
    @NotBlank
    @Size(max = 20, message = "")
    private String categoryName;
    @Schema(description = "上级编号")
    private Long parentId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}

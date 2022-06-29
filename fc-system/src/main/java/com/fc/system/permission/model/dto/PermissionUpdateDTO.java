package com.fc.system.permission.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/21 11:29
 */
@Data
public class PermissionUpdateDTO {
    @Schema(description = "标题")
    private String title;

    @Schema(description = "权限地址")
    private String url;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}

package com.fc.system.permission.model.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:07
 */
@Data
public class PermissionDTO {

    @Schema(description ="标题")
    @NotBlank
    private String title;

    @Schema(description ="权限地址")
    @NotBlank
    private String url;

    @Schema(description ="0-菜单;1-按钮;2-api接口")
    private Integer permissionType;

    @Schema(description ="上级权限id")
    private Long parentId;

    @Schema(description ="备注")
    private String remark;

    @Schema(description ="排序")
    private Integer sort;
}

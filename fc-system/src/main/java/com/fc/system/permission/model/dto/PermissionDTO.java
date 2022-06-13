package com.fc.system.permission.model.dto;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("标题")
    @NotBlank
    private String title;

    @ApiModelProperty("权限地址")
    @NotBlank
    private String url;

    @ApiModelProperty("0-菜单;1-按钮;2-api接口")
    private Integer permissionType;

    @ApiModelProperty("上级权限id")
    private Long parentId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("排序")
    private Integer sort;
}

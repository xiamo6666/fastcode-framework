package com.fc.system.permission.model.vo;

import com.fc.utils.recursion.RecursionModel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:07
 */
@Setter
@Getter
@ToString
public class PermissionVO extends RecursionModel<Long,PermissionVO> {


    @Schema(description ="标题")
    private String title;

    @Schema(description ="权限地址")
    private String url;

    @Schema(description ="0-菜单;1-按钮;2-api接口")
    private Integer permissionType;

    @Schema(description ="备注")
    private String remark;


}

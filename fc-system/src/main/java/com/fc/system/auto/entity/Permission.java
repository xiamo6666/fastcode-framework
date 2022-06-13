package com.fc.system.auto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统权限
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Getter
@Setter
@ApiModel(value = "Permission对象", description = "系统权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("序号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("权限地址")
    private String url;

    @ApiModelProperty("0-菜单;1-按钮;2-api接口")
    private Integer permissionType;

    @ApiModelProperty("上级权限id")
    private Long parentId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("排序")
    private Integer sort;


}

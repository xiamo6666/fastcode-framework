package com.fc.system.auto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 角色-权限表
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Getter
@Setter
@TableName("role_permission")
@ApiModel(value = "RolePermission对象", description = "角色-权限表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long roleId;

    private Long permissionId;


}

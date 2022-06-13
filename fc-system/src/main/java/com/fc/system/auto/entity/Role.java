package com.fc.system.auto.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Getter
@Setter
@ApiModel(value = "Role对象", description = "系统角色")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("机构编号")
    @TableField(fill = FieldFill.INSERT)
    private String orgCode;

    @ApiModelProperty("机构名称")
    @TableField(fill = FieldFill.INSERT)
    private String orgName;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("备注")
    private String remark;


}

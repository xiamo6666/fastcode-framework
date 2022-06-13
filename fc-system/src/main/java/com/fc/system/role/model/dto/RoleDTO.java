package com.fc.system.role.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:47
 */
@Data
public class RoleDTO {

    @ApiModelProperty("角色名称")
    @NotBlank
    @Size(max = 20)
    private String roleName;

    @ApiModelProperty("机构编号")
    @TableField(fill = FieldFill.INSERT)
    @NotBlank
    @Size(max = 25)
    private String orgCode;

    @ApiModelProperty("机构名称")
    @TableField(fill = FieldFill.INSERT)
    @NotBlank
    @Size(max = 50)
    private String orgName;


    @ApiModelProperty("备注")
    @Size(max = 200)
    private String remark;
}

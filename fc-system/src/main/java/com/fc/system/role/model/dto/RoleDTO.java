package com.fc.system.role.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import com.fc.core.validation.Insert;
import com.fc.core.validation.Update;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "角色名称")
    @NotBlank(groups = {Insert.class, Update.class})
    @Size(max = 20, groups = {Insert.class, Update.class})
    private String roleName;

    @Schema(description = "机构编号")
    @NotBlank(groups = {Insert.class})
    @Size(max = 25, groups = {Insert.class})
    private String orgCode;

    @Schema(description = "机构名称")
    @NotBlank(groups = {Insert.class})
    @Size(max = 50, groups = {Insert.class})
    private String orgName;


    @Schema(description = "备注")
    @Size(max = 200, groups = {Insert.class, Update.class})
    private String remark;
}

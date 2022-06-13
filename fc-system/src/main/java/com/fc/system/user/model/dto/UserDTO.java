package com.fc.system.user.model.dto;

import com.fc.core.volid.Insert;
import com.fc.core.volid.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:39
 */
@Data
@ScriptAssert.List(
        @ScriptAssert(lang = "javascript", script = "_this.password.equals(_this.confirmPassword)", message = "两次输入的密码不一致", reportOn = "password")
)
public class UserDTO {
    @ApiModelProperty(value = "登录名")
    @Length(max = 20, message = "登录名字段长度超出限制")
    @NotBlank(groups = {Insert.class })
    private String username;
    @ApiModelProperty(value = "密码")
    @NotBlank(groups = {Insert.class })
    @Size(min = 6, max = 20, message = "密码长度应该在6-20之间",groups = {Insert.class, Update.class})
    private String password;
    @ApiModelProperty(value = "确认密码")
    @NotBlank(groups = {Insert.class })
    private String confirmPassword;
    @ApiModelProperty(value = "组织机构code")
    @Size(max = 200, message = "组织机构code字段长度超出限制",groups = {Insert.class, Update.class})
    @NotBlank(groups = {Insert.class })
    private String orgCode;
    @ApiModelProperty(value = "组织机构名称")
    @Size(max = 200, message = "组织机构名称字段长度超出限制",groups = {Insert.class, Update.class})
    private String orgName;
    @ApiModelProperty(value = "名称")
    @Size(max = 200, message = "名称字段长度超出限制",groups = {Insert.class, Update.class})
    @NotBlank(groups = {Insert.class })
    private String fullName;
    @ApiModelProperty(value = "备注")
    @Size(max = 200, message = "备注字段长度超出限制",groups = {Insert.class, Update.class})
    private String remake;
}

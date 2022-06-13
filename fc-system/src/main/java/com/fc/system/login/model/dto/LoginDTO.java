package com.fc.system.login.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:58
 */
@Data
public class LoginDTO {
    @ApiModelProperty(value = "用户名")
    @NotBlank
    @Length(max = 20, message = "用户长度超出限制")
    private String username;
    @ApiModelProperty(value = "密码")
    @NotBlank
    private String password;
}

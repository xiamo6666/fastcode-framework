package com.ssos.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: UserDTO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-24 16:26
 * @Vsersion: 1.0
 */
@Data
public class UserLoginDTO {
    @NotBlank(message = "账号不能为空")
    @ApiModelProperty("登入名")
    @Length(min = 2,max = 50,message = "账号长度在2-50之间")
    private String username;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 4,max = 50,message = "密码长度在4-50之间")
    private String password;


}

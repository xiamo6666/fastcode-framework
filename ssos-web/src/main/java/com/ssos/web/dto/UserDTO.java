package com.ssos.web.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: UserDTO
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-24 16:26
 * @Vsersion: 1.0
 */
@Data
public class UserDTO {
    @NotBlank(message = "not null")
    private String username;
    @NotBlank(message = "not null")
    private String password;
}

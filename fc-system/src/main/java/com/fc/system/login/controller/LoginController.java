package com.fc.system.login.controller;

import com.fc.common.model.Result;
import com.fc.system.login.model.dto.LoginDTO;
import com.fc.system.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:58
 */
@RestController
@RequestMapping("/sys")
@Tag(name = "用户登入")
@Slf4j
@Validated
public class LoginController {
    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<String> login(@Valid @RequestBody LoginDTO loginDto) {
        return Result.success(loginService.login(loginDto.getUsername(), loginDto.getPassword()));
    }

    @GetMapping("/loginByUsername")
    @Operation(summary = "使用用户名登录", description = "作用于第三方调用，不需要密码即可生成token")
    public Result<String> loginByUsername(@RequestParam(required = false) @NotBlank(message = "用户名不能为空") @Size(max = 20, message = "用户名超出长度限制") String username) {
        return Result.success(loginService.loginByUsername(username));
    }

}

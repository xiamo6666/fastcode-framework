package com.ssos.base.controller;

import com.ssos.base.dto.UserDTO;
import com.ssos.base.dto.UserLoginDTO;
import com.ssos.base.entity.User;
import com.ssos.base.model.DateResult;
import com.ssos.base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @ClassName: UserController
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-20 18:08
 * @Vsersion: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("注册")
    public DateResult register(@RequestBody @Valid UserDTO user) {
        userService.addDate(user);
        return DateResult.ok("添加成功");
    }

    @GetMapping("/update")
    public Object update() {
        User user = new User("xxx", "xxx", "xxx");
        user.setId(20L);
        return userService.updateDate(user);
    }

    @GetMapping("/select")
    public Object select() {
        User user = new User();
        user.setId(5L);
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession());
        return userService.selectAll(user);
    }

    @PostMapping("/login")
    public DateResult login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return DateResult.ok(userService.login(userLoginDTO));
    }


}
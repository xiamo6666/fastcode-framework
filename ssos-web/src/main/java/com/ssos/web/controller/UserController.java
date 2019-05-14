package com.ssos.web.controller;

import com.ssos.web.dto.UserDTO;
import com.ssos.web.entity.User;
import com.ssos.web.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public Object add(@Valid UserDTO user){
        return userService.addDate(user);
    }

    @GetMapping("/update")
    public Object update(){
        User user = new User("xxx","xxx","xxx");
        user.setId(20L);
        return userService.updateDate(user);
    }
    @GetMapping("/select")
    public Object select(){
        User user = new User();
        user.setId(5L);
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getSession());
        return userService.selectAll(user);
    }
    @PostMapping("/login")
    @RequiresPermissions("test:add")
    public Object login(@Valid @NotBlank String  loginToken){
        return userService.login(loginToken);
    }


}
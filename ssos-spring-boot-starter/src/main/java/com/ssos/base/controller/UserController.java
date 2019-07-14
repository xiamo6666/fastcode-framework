package com.ssos.base.controller;

import com.ssos.base.dto.UserDTO;
import com.ssos.base.dto.UserLoginDTO;
import com.ssos.base.entity.User;
import com.ssos.base.model.DataResult;
import com.ssos.base.model.PageRequest;
import com.ssos.base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public DataResult register(@RequestBody @Valid UserDTO user) {
        userService.addDate(user);
        return DataResult.ok("注册成功");
    }

    @GetMapping("/update")
    public Object update() {
        User user = new User("xxx", "xxx", "xxx");
        user.setId(20L);
        return userService.updateDate(user);
    }

    @GetMapping("/find")
    public DataResult<List<User>> select(PageRequest pageRequest) {
        List<User> users = userService.selectAll(new User());
        return DataResult.ok(users);
    }

    @PostMapping("/login")
    public DataResult login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        return DataResult.ok(userService.login(userLoginDTO));
    }


}
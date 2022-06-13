package com.fc.system.user.controller;

import com.fc.common.model.PageResult;
import com.fc.common.model.Result;
import com.fc.common.model.query.PageDTO;
import com.fc.core.utils.PageResultUtils;
import com.fc.core.volid.Insert;
import com.fc.core.volid.Update;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.user.model.dto.UserDTO;
import com.fc.system.user.model.dto.UserModifyPasswordDTO;
import com.fc.system.user.model.vo.UserVO;
import com.fc.system.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:39
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户操作类")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    @Operation(summary = "添加用户", description = "用于管理员添加用户")
    public Result<String> addUser(@Validated(Insert.class) @RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return Result.success();
    }


    @PostMapping("/updateUser/{userId}")
    @Operation(summary = "修改用户", description = "用于管理员修改用户信息(包括重置密码)")
    public Result<String> updateUser(@PathVariable Long userId, @Validated(Update.class) @RequestBody UserDTO userDto) {
        userService.updateUser(userId, userDto);
        return Result.success();
    }

    @GetMapping("/pageUserList")
    @Operation(summary = "分页查看用户列表", description = "username和orgCode模糊查询")
    public Result<PageResult<UserVO>> pageUserList(PageDTO pageDto,
                                                   @Parameter(name = "username", description = "登录名") @RequestParam(required = false) String username,
                                                   @Parameter(name = "orgCode", description = "组织机构code") @RequestParam(required = false) String orgCode) {
        return Result.success(PageResultUtils.convert(userService.pageUserList(pageDto, username, orgCode)));
    }


    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用于用户自己注册账号")
    public Result<String> register(@Validated(Insert.class) @RequestBody UserDTO userDTO) {
        userService.register(userDTO);
        return Result.success();
    }

    @PostMapping("/modifyPassword")
    @Operation(summary = "修改密码")
    public Result<String> modifyPassword(@Validated @RequestBody UserModifyPasswordDTO userModifyPasswordDto) {
        userService.modifyPassword(userModifyPasswordDto);
        return Result.success();
    }

    @PostMapping("/deleteUser/{userId}")
    @Operation(summary = "用户删除")
    public Result<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return Result.success();
    }

    @GetMapping("/selectPermissionsByUserId/{userId}")
    @Operation(summary = "查询用户权限-树状结构")
    public Result<List<PermissionVO>> selectPermissionsByUserId(@PathVariable Long userId) {
        return Result.success(userService.selectPermissionsByUserId(userId));
    }
}

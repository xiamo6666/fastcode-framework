package com.fc.system.role.controller;

import com.fc.common.model.PageResult;
import com.fc.common.model.Result;
import com.fc.common.model.query.PageDTO;
import com.fc.core.utils.PageResultUtils;
import com.fc.core.validation.Insert;
import com.fc.core.validation.Update;
import com.fc.system.auto.entity.Role;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.role.model.dto.RoleDTO;
import com.fc.system.role.service.RoleService;
import com.fc.system.user.model.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:46
 */
@RequestMapping("/role")
@RestController
@Tag(name = "角色接口")
@Validated
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/addRole")
    @Operation(summary = "添加角色")
    public Result<String> addRole(@Validated(Insert.class) @RequestBody RoleDTO roleDTO) {
        roleService.addRole(roleDTO);
        return Result.success();
    }

    @PostMapping("/deleteRole")
    @Operation(summary = "删除角色")
    public Result<String> deleteRole(Long roleId) {
        roleService.deleteRole(roleId);
        return Result.success();
    }

    @PostMapping("/updateRoleById/{roleId}")
    @Operation(summary = "修改角色信息")
    public Result<String> updateRoleById(@PathVariable Long roleId,@RequestBody @Validated(Update.class) RoleDTO roleDTO) {
        roleService.updateRoleById(roleId, roleDTO);
        return Result.success();
    }


    @GetMapping("/pageRoleList")
    @Operation(summary = "分页查询角色")
    public Result<PageResult<Role>> pageRoleList(PageDTO pageDTO,
                                                 @RequestParam(required = false) @Parameter(name = "角色名称") String roleName) {

        return Result.success(
                PageResultUtils.convert(
                        roleService.pageRoleList(pageDTO, roleName)
                )
        );
    }

    @PostMapping("/changeUserToRole/{roleId}")
    @Operation(summary = "变更用户与角色绑定关系", description = "适用于新增和修改")
    public Result<String> changeUserToRole(@PathVariable Long roleId, @NotEmpty @RequestBody List<Long> userIds) {
        roleService.changeUserToRole(roleId, userIds);
        return Result.success();
    }

    @PostMapping("/changePermissionToRole/{roleId}")
    @Operation(summary = "变更角色与权限绑定关系", description = "适用于新增和修改")
    public Result<String> changePermissionToRole(@PathVariable Long roleId, @NotEmpty @RequestBody List<Long> permissionIds) {
        roleService.changePermissionToRole(roleId, permissionIds);
        return Result.success();
    }


    @GetMapping("/selectPermissionsByRoleId/{roleId}")
    @Operation(summary = "获取角色锁拥有的权限-树状结构")
    public Result<List<PermissionVO>> selectPermissionsByRoleId(@PathVariable Long roleId) {
        return Result.success(roleService.selectPermissionsByRoleId(roleId));
    }

    @GetMapping("/selectUsersByRoleId/{roleId}")
    @Operation(summary = "获取角色下所有的用户信息")
    public Result<List<UserVO>> selectUsersByRoleId(@PathVariable Long roleId) {
        return Result.success(roleService.selectUsersByRoleId(roleId));
    }
}

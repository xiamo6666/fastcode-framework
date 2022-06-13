package com.fc.system.permission.controller;

import com.fc.common.model.Result;
import com.fc.system.auto.entity.Permission;
import com.fc.system.permission.model.dto.PermissionDTO;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.permission.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:07
 */
@RestController
@RequestMapping("/permission")
@Tag(name = "权限接口")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/selectMenuPermissionList")
    @Operation(summary ="获取菜单权限列表")
    public Result<List<PermissionVO>> selectMenuPermissionList() {
        return Result.success(permissionService.selectMenuPermissionList());
    }

    @GetMapping("/selectApiPermissionList")
    @Operation(summary ="获取Api接口权限列表")
    public Result<List<Permission>> selectApiPermissionList() {
        return Result.success(permissionService.selectApiPermissionList());
    }

    @PostMapping("/addPermission")
    @Operation(summary ="添加权限数据")
    public Result<String> addPermission(PermissionDTO permissionDTO) {
        permissionService.addPermission(permissionDTO);
        return Result.success();
    }

    @GetMapping("/deletePermission")
    @Operation(summary ="删除权限数据")
    public Result<String> deletePermission(Long permissionId) {
        permissionService.deletePermission(permissionId);
        return Result.success();
    }
}

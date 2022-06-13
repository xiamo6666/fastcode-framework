package com.fc.system.permission.service.impl;

import com.fc.core.exception.ServiceException;
import com.fc.system.auto.entity.Permission;
import com.fc.system.auto.entity.RolePermission;
import com.fc.system.auto.service.PermissionAutoService;
import com.fc.system.auto.service.RolePermissionAutoService;
import com.fc.system.permission.model.dto.PermissionDTO;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.permission.service.PermissionService;
import com.fc.utils.recursion.RecursionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:11
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionAutoService permissionAutoService;
    @Autowired
    private RolePermissionAutoService rolePermissionAutoService;

    @Override
    public List<PermissionVO> selectMenuPermissionList() {
        List<Permission> list = permissionAutoService.lambdaQuery()
                .in(Permission::getPermissionType, 0, 1)
                .list();
        List<PermissionVO> permissionVOList = list.stream()
                .map(
                        p -> {
                            PermissionVO permissionVO = new PermissionVO();
                            BeanUtils.copyProperties(p, permissionVO);
                            return permissionVO;
                        }
                )
                .collect(Collectors.toList());
        return RecursionUtils.recursionConvert(permissionVOList);

    }

    @Override
    public List<Permission> selectApiPermissionList() {
        return permissionAutoService.lambdaQuery()
                .eq(Permission::getPermissionType, 2)
                .list();
    }

    @Override
    public void addPermission(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(permissionDTO, permission);
        permissionAutoService.save(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        //判断当前权限是否被role归属
        Long rolePermissionCount = rolePermissionAutoService.lambdaQuery()
                .eq(RolePermission::getPermissionId, permissionId)
                .count();
        if (rolePermissionCount > 0) {
            throw new ServiceException("该权限该权限被角色归属,禁止删除!!!");
        }

        Long count = permissionAutoService.lambdaQuery()
                .eq(Permission::getParentId, permissionId)
                .count();

        if (count > 0) {
            throw new ServiceException("该权限下存在子权限,禁止删除!!!");
        }
        permissionAutoService.removeById(permissionId);
    }
}

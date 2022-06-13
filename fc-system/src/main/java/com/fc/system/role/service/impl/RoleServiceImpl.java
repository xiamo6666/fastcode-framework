package com.fc.system.role.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fc.common.model.query.PageDTO;
import com.fc.core.exception.ServiceException;
import com.fc.core.utils.PageDtoUtils;
import com.fc.system.auto.entity.Role;
import com.fc.system.auto.entity.RolePermission;
import com.fc.system.auto.entity.UserRole;
import com.fc.system.auto.service.RoleAutoService;
import com.fc.system.auto.service.RolePermissionAutoService;
import com.fc.system.auto.service.UserRoleAutoService;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.role.mapper.RoleMapper;
import com.fc.system.role.model.dto.RoleDTO;
import com.fc.system.role.service.RoleService;
import com.fc.system.user.model.vo.UserVO;
import com.fc.utils.recursion.RecursionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:44
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleAutoService roleAutoService;
    @Autowired
    private RolePermissionAutoService rolePermissionAutoService;

    @Autowired
    private UserRoleAutoService userRoleAutoService;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void addRole(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        roleAutoService.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long roleId) {
        if (roleMapper.checkRoleUsedByRoleId(roleId) > 0) {
            throw new ServiceException("该角色中有用户存在,不允许删除!!!");
        }
        //删除角色与权限关联
        rolePermissionAutoService.remove(Wrappers.<RolePermission>lambdaQuery().eq(RolePermission::getRoleId, roleId));
        //删除角色本身
        roleAutoService.removeById(roleId);
    }

    @Override
    public void updateRoleById(Long roleId, RoleDTO roleDTO) {
        if (roleMapper.checkRoleUsedByRoleId(roleId) > 0) {
            throw new ServiceException("该角色中有用户存在,不允许修改!!!");
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        role.setId(roleId);
        roleAutoService.lambdaUpdate().update(role);
    }

    @Override
    public IPage<Role> pageRoleList(PageDTO pageDto, String roleName) {
        return roleAutoService.lambdaQuery()
                .like(StringUtils.isNotBlank(roleName), Role::getRoleName, roleName)
                .page(PageDtoUtils.convert(pageDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeUserToRole(Long roleId, List<Long> userIds) {
        //执行变更操作时,删除老数据
        userRoleAutoService.remove(
                Wrappers.<UserRole>lambdaQuery()
                        .eq(UserRole::getRoleId, roleId)
                        .in(UserRole::getUserId, userIds)
        );
        List<UserRole> userRoles = userIds.stream()
                .map(p -> {
                    UserRole userRole = new UserRole();
                    userRole.setRoleId(roleId);
                    userRole.setUserId(p);
                    return userRole;
                })
                .collect(Collectors.toList());
        userRoleAutoService.saveBatch(userRoles);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePermissionToRole(Long roleId, List<Long> permissionIds) {

        //执行变更操作时,删除老数据
        rolePermissionAutoService.remove(
                Wrappers.<RolePermission>lambdaQuery()
                        .eq(RolePermission::getRoleId, roleId)
                        .in(RolePermission::getPermissionId, permissionIds)
        );


        List<RolePermission> rolePermissions = permissionIds.stream()
                .map(p -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(p);
                    return rolePermission;
                })
                .collect(Collectors.toList());
        rolePermissionAutoService.saveBatch(rolePermissions);
    }

    @Override
    public void deleteUserRole(Long userId) {
        userRoleAutoService.remove(Wrappers.<UserRole>lambdaQuery()
                .eq(UserRole::getUserId, userId)
        );
    }

    @Override
    public List<PermissionVO> selectPermissionsByRoleId(Long roleId) {
        //递归变成树状结构
        return RecursionUtils.recursionConvert(roleMapper.selectPermissionsByRoleId(roleId));
    }

    @Override
    public List<UserVO> selectUsersByRoleId(Long roleId) {
        return roleMapper.selectUsersByRoleId(roleId);
    }

    @Override
    public List<PermissionVO> selectPermissionsByUserId(Long userId) {
        return RecursionUtils.recursionConvert(roleMapper.selectPermissionsByUserId(userId));
    }

}

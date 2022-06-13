package com.fc.system.role.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.query.PageDTO;
import com.fc.system.auto.entity.Role;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.role.model.dto.RoleDTO;
import com.fc.system.user.model.vo.UserVO;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:29
 */
public interface RoleService {

    /**
     * 添加角色
     *
     * @param roleDTO {@link RoleDTO}
     */
    void addRole(RoleDTO roleDTO);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    void deleteRole(Long roleId);

    /**
     * 修改角色
     *
     * @param roleId  角色id
     * @param roleDTO {@link RoleDTO}
     */
    void updateRoleById(Long roleId, RoleDTO roleDTO);

    /**
     * 分页查询角色list
     *
     * @param pageDto  分页dto
     * @param roleName 查询可选模糊查询条件 角色名称
     * @return IPage<Role>
     */
    IPage<Role> pageRoleList(PageDTO pageDto, String roleName);

    /**
     * 变更用户与角色的绑定关系
     * 适用于添加或修改
     *
     * @param roleId  角色id
     * @param userIds 用户id
     */
    void changeUserToRole(Long roleId, List<Long> userIds);

    /**
     * 变更角色与权限的绑定关系
     * @param roleId 角色id
     * @param permissionIds 权限id
     */

    void changePermissionToRole(Long roleId, List<Long> permissionIds);

    /**
     * 删除用户与角色的绑定关系
     * @param userId 用户id
     */
    void deleteUserRole(Long userId);

    /**
     * 根据角色查询权限信息
     *
     * @param roleId 角色id
     * @return 权限树状结构
     */
    List<PermissionVO> selectPermissionsByRoleId(Long roleId);

    /**
     * 根据角色查询绑定的用户信息
     * @param roleId 角色id
     * @return 用户信息list
     */
    List<UserVO> selectUsersByRoleId(Long roleId);

    /**
     * 根据用户id查询所拥有的权限
     * @param userId 用户id
     * @return 权限树状结构
     */
    List<PermissionVO> selectPermissionsByUserId(Long userId);
}

package com.fc.system.role.mapper;

import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.user.model.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:46
 */
public interface RoleMapper {
    /**
     * 查询该角色是否被用户使用
     *
     * @param roleId 角色id
     * @return 用户使用数量
     */
    int checkRoleUsedByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色id查询权限
     *
     * @param roleId 角色id
     * @return 树状权限
     */
    List<PermissionVO> selectPermissionsByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色id查询用户
     *
     * @param roleId 角色id
     * @return 用户list
     */
    List<UserVO> selectUsersByRoleId(@Param("roleId") Long roleId);


    /**
     * 根据用户id查询拥有的权限
     *
     * @param userId 用户id
     * @return 树状权限
     */
    List<PermissionVO> selectPermissionsByUserId(@Param("userId") Long userId);
}

package com.fc.system.permission.service;

import com.fc.system.auto.entity.Permission;
import com.fc.system.permission.model.dto.PermissionDTO;
import com.fc.system.permission.model.vo.PermissionVO;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:44
 */
public interface PermissionService {

    /**
     * 查询菜单权限列表
     *
     * @return list
     */
    List<PermissionVO> selectMenuPermissionList();

    /**
     * 查询api接口权限列表
     *
     * @return list
     */
    List<Permission> selectApiPermissionList();

    /**
     * 添加权限
     *
     * @param permissionDTO {@link PermissionDTO}
     */
    void addPermission(PermissionDTO permissionDTO);


    /**
     * 删除权限
     *
     * @param permissionId 权限id
     */
    void deletePermission(Long permissionId);


}

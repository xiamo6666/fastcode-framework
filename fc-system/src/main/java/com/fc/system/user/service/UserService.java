package com.fc.system.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.query.PageDTO;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.user.model.dto.UserDTO;
import com.fc.system.user.model.dto.UserModifyPasswordDTO;
import com.fc.system.user.model.vo.UserVO;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:34
 */
public interface UserService {

    /**
     * 添加用户信息
     *
     * @param userDto 用户dto
     */
    void addUser(UserDTO userDto);

    /**
     * 修改用户信息
     *
     * @param userId  用户id
     * @param userDto 用户dto
     */
    void updateUser(Long userId, UserDTO userDto);

    /**
     * 分页用户列表
     *
     * @param pageDto  分页dto
     * @param username 用户名
     * @param orgCode  机构code
     * @return IPage<UserVO>
     */
    IPage<UserVO> pageUserList(PageDTO pageDto, String username, String orgCode);

    /**
     * 用户注册
     *
     * @param userDto userDTO
     */
    void register(UserDTO userDto);

    /**
     * 修改密码
     *
     * @param userModifyPasswordDto 修改密码
     */
    void modifyPassword(UserModifyPasswordDTO userModifyPasswordDto);

    /**
     * 用户删除
     *
     * @param userId userId
     */
    void deleteUser(Long userId);

    /**
     * 查询用户所拥有的权限
     *
     * @param userId 用户id
     * @return 树状权限
     */
    List<PermissionVO> selectPermissionsByUserId(Long userId);

}

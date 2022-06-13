package com.fc.system.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fc.common.model.query.PageDTO;
import com.fc.core.exception.ServiceException;
import com.fc.core.utils.PageDtoUtils;
import com.fc.core.utils.UserUtils;
import com.fc.system.auto.entity.Org;
import com.fc.system.auto.entity.User;
import com.fc.system.auto.service.OrgAutoService;
import com.fc.system.auto.service.UserAutoService;
import com.fc.system.org.service.OrgService;
import com.fc.system.permission.model.vo.PermissionVO;
import com.fc.system.role.service.RoleService;
import com.fc.system.user.model.dto.UserDTO;
import com.fc.system.user.model.dto.UserModifyPasswordDTO;
import com.fc.system.user.model.vo.UserVO;
import com.fc.system.user.service.UserService;
import com.fc.system.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 14:40
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserAutoService userAutoService;
    @Autowired
    private OrgAutoService orgAutoService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private OrgService orgService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO userDto) {
        userDto.setPassword(MD5Utils.MD5.digestHex(userDto.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        userAutoService.save(user);
        orgService.enableOrg(user.getOrgCode());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(Long userId, UserDTO userDto) {
        //判断是否需要修改密码
        if (StringUtils.hasText(userDto.getPassword())) {
            userDto.setPassword(
                    MD5Utils.MD5.digestHex(userDto.getPassword())
            );
        }
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        //username禁止修改
        user.setUsername(null);
        userAutoService.lambdaUpdate()
                .eq(User::getId, userId)
                .update(user);
    }

    @Override
    public IPage<UserVO> pageUserList(PageDTO pageDto, String username, String orgCode) {
        return userAutoService.lambdaQuery()
                .likeRight(
                        StringUtils.hasText(username),
                        User::getUsername, username
                )
                .likeRight(
                        StringUtils.hasText(orgCode),
                        User::getOrgCode, orgCode
                )
                .page(PageDtoUtils.convert(pageDto))
                .convert(
                        (user) -> {
                            UserVO userVO = new UserVO();
                            BeanUtils.copyProperties(user, userVO);
                            return userVO;
                        }
                );
    }

    @Override
    public void register(UserDTO userDto) {
        String orgCode = userDto.getOrgCode();
        Org one = orgAutoService.lambdaQuery().eq(Org::getOrgCode, orgCode).one();
        if (one == null) {
            throw new ServiceException("注册失败：组织机构代码输入不正确!!!");
        }
        userDto.setOrgName(one.getOrgName());
        addUser(userDto);

    }

    @Override
    public void modifyPassword(UserModifyPasswordDTO userModifyPasswordDto) {
        Long userId = UserUtils.getUserId();
        User user = userAutoService.lambdaQuery()
                .eq(User::getId, userId)
                .eq(User::getPassword, MD5Utils.MD5.digestHex(userModifyPasswordDto.getOriginalPassword()))
                .one();
        if (user == null) {
            throw new ServiceException("原始密码输入不正确");
        }
        userAutoService.lambdaUpdate()
                .set(User::getPassword, MD5Utils.MD5.digestHex(
                        userModifyPasswordDto.getNewPassword()
                ))
                .eq(User::getId, userId)
                .update();


    }

    @Override
    public void deleteUser(Long userId) {
        //删除用户信息
        userAutoService.removeById(userId);
        //删除用户基本信息 TODO

        //删除用户关联角色信息
        roleService.deleteUserRole(userId);
    }

    @Override
    public List<PermissionVO> selectPermissionsByUserId(Long userId) {
        return roleService.selectPermissionsByUserId(userId);
    }
}

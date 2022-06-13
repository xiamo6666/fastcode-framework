package com.fc.system.auto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.system.auto.entity.UserRole;
import com.fc.system.auto.mapper.UserRoleAutoMapper;
import com.fc.system.auto.service.UserRoleAutoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-角色表 服务实现类
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Service
public class UserRoleAutoServiceImpl extends ServiceImpl<UserRoleAutoMapper, UserRole> implements UserRoleAutoService {

}

package com.fc.system.auto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.system.auto.entity.Role;
import com.fc.system.auto.mapper.RoleAutoMapper;
import com.fc.system.auto.service.RoleAutoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Service
public class RoleAutoServiceImpl extends ServiceImpl<RoleAutoMapper, Role> implements RoleAutoService {

}

package com.fc.system.auto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.system.auto.entity.Permission;
import com.fc.system.auto.mapper.PermissionAutoMapper;
import com.fc.system.auto.service.PermissionAutoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统权限 服务实现类
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Service
public class PermissionAutoServiceImpl extends ServiceImpl<PermissionAutoMapper, Permission> implements PermissionAutoService {

}

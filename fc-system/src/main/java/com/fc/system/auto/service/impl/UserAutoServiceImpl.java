package com.fc.system.auto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.system.auto.entity.User;
import com.fc.system.auto.mapper.UserAutoMapper;
import com.fc.system.auto.service.UserAutoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiawl
 * @since 2022-06-07
 */
@Service
public class UserAutoServiceImpl extends ServiceImpl<UserAutoMapper, User> implements UserAutoService {

}

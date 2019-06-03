package com.ssos.base.service.impl;

import com.ssos.base.common.JwtToken;
import com.ssos.base.dto.UserDTO;
import com.ssos.base.entity.User;
import com.ssos.base.mapper.UserMapper;
import com.ssos.base.service.UserService;
import com.ssos.base.utils.JwtUtils;
import com.ssos.base.utils.PasswordUtils;
import com.ssos.exception.BaseException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-25 10:00
 * @Vsersion: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordUtils passwordUtils;

    @Override
    public boolean addDate(UserDTO users) {
        User user = new User();
        BeanUtils.copyProperties(users,user);
        String salt = passwordUtils.getSalt();
        String passwrd = passwordUtils.getPasswordEncryption(users.getPassword(),salt);
        user.setPassword(passwrd);
        user.setSalt(salt);
        return userMapper.insert(user);
    }

    @Override
    public Integer updateDate(User user) {

        return userMapper.update(user);
    }
    @Override
    public List<User> selectAll(User user){
        return userMapper.select(user);
    }

    @Override
    public String login(String loginToken) {
        JwtToken jwtToken = new JwtToken(loginToken);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(jwtToken);
        }catch (Exception e){
            if (e instanceof IncorrectCredentialsException){
                 throw new BaseException("账号或密码错误");
            }else{
                throw new BaseException("未知错误");
            }
        }
        Map<String,Object> map = new HashMap<>(3);
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        map.put("username",user);
        List<String> permissions = userMapper.queryPermission(user.getUsername());
        map.put("permissions",permissions);
        List<String> roles = userMapper.qeuryRole(user.getId());
        map.put("roles",roles);
        String token = JwtUtils.createToken(map);
        return token;
    }
}

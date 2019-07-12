package com.ssos.base.common;


import com.ssos.base.entity.User;
import com.ssos.base.mapper.UserMapper;
import com.ssos.base.utils.JwtUtils;
import com.ssos.exception.BaseException;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: ShiroRealm
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-25 10:28
 * @Vsersion: 1.0
 */
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String token = (String) principalCollection.getPrimaryPrincipal();
        Claims claims = JwtUtils.parseToken(token);
        List<String> permissions = (List) claims.get("permissions");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        if (permissions == null && permissions.size() == 0) {
            throw new BaseException("授权失败,请重新登入获取权限");
        }
        permissions.stream().forEach(
                e -> simpleAuthorizationInfo.addStringPermission(e)
        );
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken jwtToken = (UsernamePasswordToken) authenticationToken;
        User user = new User();
        user.setUsername(jwtToken.getUsername());
        List<User> select = userMapper.select(user);
        if (select == null) {
            throw new UnknownAccountException();
        }
        if (select.size() == 0) {
            throw new UnknownAccountException();
        }
        if (select.get(0).getState() != 0) {
            throw new BaseException("账号已禁用，请联系管理员");
        }

        User resourceUser = select.get(0);
        return new SimpleAuthenticationInfo(resourceUser, resourceUser.getPassword(), ByteSource.Util.bytes(resourceUser.getSalt()), getName());
    }
}

package com.fc.system.login.service.impl;

import com.fc.common.model.GlobalConstant;
import com.fc.common.model.LoginInfo;
import com.fc.common.model.enums.CommonResultEnum;
import com.fc.core.exception.ServiceException;
import com.fc.system.auto.entity.User;
import com.fc.system.auto.service.UserAutoService;
import com.fc.system.login.service.LoginService;
import com.fc.system.utils.MD5Utils;
import com.fc.utils.jwt.JwtUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 13:58
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserAutoService userAutoService;


    @Override
    public String login(String username, String password) {
        String md5password = MD5Utils.MD5.digestHex(password);
        User user = userAutoService.lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getPassword, md5password)
                .one();
        return generateTokenAndCookie(user);
    }

    @Override
    public String loginByUsername(String username) {
        User user = userAutoService.lambdaQuery()
                .eq(User::getUsername, username)
                .one();
        return generateTokenAndCookie(user);
    }

    /**
     * 生成token信息和cookie信息
     *
     * @param user user信息
     * @return  token
     */
    private String generateTokenAndCookie(User user) {
        if (user == null) {
            throw new ServiceException(CommonResultEnum.LOGIN_FAILED);
        }
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(user, loginInfo);
        loginInfo.setUserId(user.getId());
        String token = JwtUtils.generateToken(loginInfo, GlobalConstant.TOKEN_EXPIRES_TIME);
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        //生成cookie信息
        Cookie cookie = new Cookie(GlobalConstant.PARAM_TOKEN, token);
        cookie.setPath("/");
        cookie.setMaxAge(GlobalConstant.TOKEN_EXPIRES_TIME * 24 * 60 * 60);
        if (httpServletResponse != null) {
            httpServletResponse.addCookie(cookie);
        }
        return token;
    }
}

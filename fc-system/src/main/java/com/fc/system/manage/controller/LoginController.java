package com.fc.system.manage.controller;

import com.fc.common.model.LoginInfo;
import com.fc.common.model.Result;
import com.fc.core.common.GlobalConstant;
import com.fc.core.utils.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @ClassName: LoginController
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/4/28 14:48
 * @Vsersion: 1.0
 */
@RestController
public class LoginController {
    @PostMapping("/login")
    public Result<String> login() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId("test");
        loginInfo.setUsername("test");
        loginInfo.setOrgId("test");
        loginInfo.setOrgName("test");
        String token = JwtUtils.generateToken(loginInfo, 1);
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        httpServletResponse.addCookie(new Cookie(GlobalConstant.PARAM_TOKEN, token));
        return Result.success(token);

    }

}

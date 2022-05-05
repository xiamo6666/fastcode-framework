package com.fc.core.filter;

import cn.hutool.http.ContentType;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.core.common.GlobalConstant;
import com.fc.core.utils.JwtUtils;
import com.fc.core.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fc.common.model.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * @ClassName: AuthFilter
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/4/25 15:29
 * @Vsersion: 1.0
 */
@Component
@Slf4j
@Order(1)
public class AuthFilter extends OncePerRequestFilter {


    /**
     * 允许授权url
     */
    private final static Set<String> permissionUrls = Set.of("/login");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (checkPermissionUrl(request) || checkLogin(request)) {
            filterChain.doFilter(request, response);
        } else {
            responseData(response);
        }
        UserUtils.removeLoginInfo();
    }


    /**
     * 验证url是否需要授权
     *
     * @param request
     * @return
     */
    private static boolean checkPermissionUrl(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.debug("请求url:[{}]", requestURI);
        return permissionUrls.contains(requestURI);
    }

    /**
     * 验证用户是否登录
     *
     * @param request
     * @return
     */
    private static boolean checkLogin(HttpServletRequest request) {
        String token = getToken(request);

        if (!StringUtils.hasText(token)) {
            return false;
        }
        try {
            //解析token
            DecodedJWT decodedJWT = JwtUtils.verifyToken(token);
            //验证token是否过期和解析出来用户信息
            LoginInfo loginInfo = JwtUtils.parseToken(decodedJWT, LoginInfo.class);
            //保存用户信息信息
            loginInfo.setToken(token);
            UserUtils.setLoginInfo(loginInfo);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 从header中或者cookie中获取 token信息
     *
     * @param request
     * @return
     */
    private static String getToken(HttpServletRequest request) {
        String tokenValue = null;

        //解析cookies中的验证信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (GlobalConstant.PARAM_TOKEN.equalsIgnoreCase(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        //从header中获取token
        if (!StringUtils.hasText(tokenValue)) {
            tokenValue = request.getHeader(GlobalConstant.PARAM_TOKEN);
        }
        return tokenValue;
    }

    /**
     * 未验证信息输出
     *
     * @param response
     */
    public void responseData(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        response.setContentType(ContentType.JSON.getValue());
        try {
            PrintWriter writer = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(
                    Result.error(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase())
            ));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异常数据返回失败");
        }
    }
}

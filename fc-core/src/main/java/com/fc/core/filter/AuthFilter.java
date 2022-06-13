package com.fc.core.filter;

import cn.hutool.http.ContentType;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.common.model.GlobalConstant;
import com.fc.common.model.LoginInfo;
import com.fc.common.model.Result;
import com.fc.core.utils.UserUtils;
import com.fc.utils.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 16:39
 */
@Component
@Slf4j
@Order(1)
public class AuthFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (checkPermissionUrl(request) || injectUserInfo(request, response)) {
                filterChain.doFilter(request, response);
            } else {
                responseData(response);
            }
        } finally {
            UserUtils.removeLoginInfo();
        }

    }


    private static boolean checkPermissionUrl(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.debug("请求url:[{}]", requestURI);
        return GlobalConstant.PERMISSION_URL.contains(requestURI);
    }


    /**
     * 注入用户信息
     *
     * @param request request
     * @return
     */


    public boolean injectUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String token = getToken(request);
        if (!StringUtils.hasText(token)) {
            return true;
        }
        try {
            //解析token
            DecodedJWT decodedJWT = JwtUtils.verifyToken(token);
            //验证token是否过期和解析出来用户信息
            LoginInfo loginInfo = JwtUtils.parseToken(decodedJWT, LoginInfo.class);
            //保存用户信息信息
            loginInfo.setToken(token);
            UserUtils.setLoginInfo(loginInfo);
            return true;
        } catch (Exception e) {
            log.warn(e.getMessage());
            responseData(response);
            return false;
        }

    }

    /**
     * 从header中或者cookie中获取 token信息
     *
     * @param request request
     * @return token
     */
    private static String getToken(HttpServletRequest request) {
        //从header中获取token
        String tokenValue = request.getHeader(GlobalConstant.PARAM_TOKEN);

        //解析cookies中的验证信息
        if (!StringUtils.hasText(tokenValue)) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (GlobalConstant.PARAM_TOKEN.equalsIgnoreCase(cookie.getName())) {
                        tokenValue = cookie.getValue();
                    }
                }
            }
        }
        return tokenValue;
    }

    /**
     * 未验证信息输出
     *
     * @param response request
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

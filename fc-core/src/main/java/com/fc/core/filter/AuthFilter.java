package com.fc.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
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

    private final static String PARAM_TOKEN = "x-auth-token";

    /**
     * 允许授权url
     */
    private final static Set<String> permissionUrls = Set.of("/login");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //

        if (checkLogin(request) || checkPermissionUrl(request)) {
            filterChain.doFilter(request, response);
        }
        log.debug("登入失效");
        response.setStatus(401);
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

    private static boolean checkLogin(HttpServletRequest request) {
        String token = getToken(request);
        return StringUtils.hasText(token);
    }

    private static String getToken(HttpServletRequest request) {
        String tokenValue = null;

        //解析cookies中的验证信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (PARAM_TOKEN.equalsIgnoreCase(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        //从header中获取token
        if (StringUtils.hasText(tokenValue)) {
            tokenValue = request.getHeader(PARAM_TOKEN);
        }
        return tokenValue;
    }

    /**
     * 无权限是输出
     *
     * @param response
     * @param msg
     */
    public void responseData(HttpServletResponse response, String msg) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            writer.write(objectMapper.writeValueAsString(Result.error(msg)));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异常数据返回失败");
        }
    }
}

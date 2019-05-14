package com.ssos.web.base.fileter;

import com.ssos.web.base.utils.ClassUtils;
import com.ssos.web.base.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: JwtFilter
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018-12-25 13:19
 * @Vsersion: 1.0
 */
public class JwtFilter  extends BasicHttpAuthenticationFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if(httpServletRequest.getServletPath().equalsIgnoreCase("/favicon.ico")){
            return true;
        }
        String authorization = httpServletRequest.getHeader("authorization");
        if (authorization == null){
            try {
                ((HttpServletResponse) response).sendRedirect(super.getLoginUrl());
                log.debug("token不存在");
                return false;
            } catch (IOException e) {
                log.error("getLoginUrl() 页面不存在");
            }
        }
        Claims claims = JwtUtils.parseToken(authorization);
        if (claims == null){
            try {
                ((HttpServletResponse) response).sendRedirect(super.getLoginUrl());
            } catch (IOException e) {
                log.error("login 页面不存在");
            }
            log.debug("token已过期");
            return false;
        }
        ClassUtils.par(authorization);

        return true;
    }
}

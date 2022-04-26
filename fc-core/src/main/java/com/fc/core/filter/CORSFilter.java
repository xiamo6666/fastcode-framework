package com.fc.core.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: CORSFilter
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/4/25 15:34
 * @Vsersion: 1.0
 */
@Component
@Order(0)
public class CORSFilter extends OncePerRequestFilter {


    /**
     * 配置跨域信息
     *
     * @return
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, DELETE, PUT");
        response.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return;
        }
        filterChain.doFilter(request, response);
    }
}

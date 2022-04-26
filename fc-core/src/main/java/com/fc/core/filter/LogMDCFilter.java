package com.fc.core.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @ClassName: LogMDCFilter
 * @Description: 全局日志跟踪
 * @Author: xwl
 * @Date: 2022/4/25 16:11
 * @Vsersion: 1.0
 */
@Component
@Order(-1)
@Slf4j
public class LogMDCFilter extends OncePerRequestFilter {
    private static final String traceId = "traceId";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceIdValue = request.getHeader(traceId);
            MDC.put(traceId, ObjectUtils.isEmpty(traceIdValue) ? UUID.randomUUID().toString().replace("-", "") : traceIdValue);
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(traceId);
        }
    }
}

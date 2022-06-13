package com.fc.core.filter;

import com.fc.common.model.GlobalConstant;
import com.fc.utils.uuid.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String traceIdValue = request.getHeader(GlobalConstant.TRACE_ID);
            String SpanId = request.getHeader(GlobalConstant.SPAN_ID);
            String Deep = request.getHeader(GlobalConstant.DEEP);
            MDC.put(GlobalConstant.TRACE_ID, ObjectUtils.isEmpty(traceIdValue) ? UuidUtils.UUID() : traceIdValue);
            MDC.put(GlobalConstant.PARENT_SPAN_ID, ObjectUtils.isEmpty(SpanId) ? UuidUtils.shortUUID() : SpanId);
            MDC.put(GlobalConstant.SPAN_ID, UuidUtils.shortUUID());
            MDC.put(GlobalConstant.DEEP, String.valueOf(ObjectUtils.isEmpty(Deep) ? 0 : Integer.parseInt(Deep) + 1));
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(GlobalConstant.TRACE_ID);
            MDC.remove(GlobalConstant.PARENT_SPAN_ID);
            MDC.remove(GlobalConstant.SPAN_ID);
            MDC.remove(GlobalConstant.DEEP);

        }
    }
}

package com.fc.gateway.filter;

import com.fc.common.model.GlobalConstant;
import com.fc.utils.uuid.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:34
 */
@Component
@Slf4j
@Order(-2)
public class LogMDCFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //添加logMDC全局跟踪ID
        String spanId = UuidUtils.shortUUID();
        String traceId = UuidUtils.UUID();
        exchange.getRequest()
                .mutate()
                .header(GlobalConstant.TRACE_ID, traceId)
                .header(GlobalConstant.SPAN_ID, spanId)
                .header(GlobalConstant.PARENT_SPAN_ID, "")
                .header(GlobalConstant.DEEP, String.valueOf(0))
                .build();
        MDC.put(GlobalConstant.TRACE_ID, traceId);
        MDC.put(GlobalConstant.PARENT_SPAN_ID, "");
        MDC.put(GlobalConstant.SPAN_ID, spanId);
        MDC.put(GlobalConstant.DEEP, String.valueOf(0));
        return chain.filter(exchange);

    }
}

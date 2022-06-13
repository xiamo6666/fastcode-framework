package com.fc.common.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 全局常量
 *
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 10:11
 */
public interface GlobalConstant {
    /**
     * token head value
     */
    String PARAM_TOKEN = "x-auth-token";

    /**
     * 允许不授权通过url
     */
    Set<String> PERMISSION_URL = Set.of("/sys/login", "/v3/api-docs", "/user/register", "/sys/loginByUsername");

    /**
     * 链路追踪
     */
    String TRACE_ID = "X-B3-TraceId";

    String SPAN_ID = "X-B3-SpanId";

    String PARENT_SPAN_ID = "X-B3-ParentSpanId";

    String DEEP = "Deep";

    /**
     * MD5 加密 salt
     */
    String SALT = ")(*&^%$#@!!x@#$%x^&*(x)";
    /**
     * token过期时间 1天
     */
    int TOKEN_EXPIRES_TIME = 1;

    /**
     * token 刷新时间 30分钟
     */
    long TOKEN_REFRESH_TIME = 30;


}

package com.fc.gateway.filter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fc.common.model.GlobalConstant;
import com.fc.common.model.LoginInfo;
import com.fc.common.model.Result;
import com.fc.utils.jwt.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:19
 */
@Slf4j
@Component
@Order(-1)
public class GatewayAuthFilter implements GlobalFilter {
    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //验证
        if (checkPermissionUrl(request) || checkLogin(request, response)) {
            if (log.isDebugEnabled()) {
                String requestUri = request.getURI().getPath();
                log.debug("url:[{}],允许通行", requestUri);
            }
            return chain.filter(exchange);
        } else {
            if (log.isDebugEnabled()) {
                String requestUri = request.getURI().getPath();
                log.debug("url:[{}],无权限访问", requestUri);
            }
            return responseData(exchange);
        }

    }

    /**
     * 验证授权url
     *
     * @param request request
     * @return Boolean
     */
    private boolean checkPermissionUrl(ServerHttpRequest request) {
        String requestUri = request.getURI().getPath();
        return GlobalConstant.PERMISSION_URL.contains(requestUri);
    }

    /**
     * 验证是否登入
     *
     * @param request request
     * @return boolean 是否登录
     */
    private boolean checkLogin(ServerHttpRequest request, ServerHttpResponse response) {
        String token = getToken(request);
        if (StringUtils.hasText(token) && JwtUtils.verifyTokenStatue(token)) {
            //判断token是否即将过期，刷新token
            if (JwtUtils.verifyTokenExpireTime(token)) {
                String refreshToken = JwtUtils.generateTokenByToken(token, LoginInfo.class);
                //将refreshToken写入cookie
                response.addCookie(
                        ResponseCookie.from(GlobalConstant.PARAM_TOKEN, refreshToken)
                                .maxAge(GlobalConstant.TOKEN_EXPIRES_TIME * 24 * 60 * 60)
                                .path("/")
                                .build()
                );
                //将refreshToken写入header
                response.getHeaders().add(GlobalConstant.PARAM_TOKEN, refreshToken);
                if (log.isDebugEnabled()) {
                    log.debug("token：[{}]即将失效,已经生成新token:[{}]", token, refreshToken);
                }
            }
            return true;
        }
        return false;
    }


    /**
     * 从header中或者cookie中获取 token信息
     *
     * @param request 请求request
     */
    private static String getToken(ServerHttpRequest request) {
        String tokenValue = null;
        //解析cookies中的验证信息
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();
        List<HttpCookie> httpCookies = cookies.get(GlobalConstant.PARAM_TOKEN);
        if (httpCookies != null) {
            for (HttpCookie cookie : httpCookies) {
                if (GlobalConstant.PARAM_TOKEN.equalsIgnoreCase(cookie.getName())) {
                    tokenValue = cookie.getValue();
                }
            }
        }
        //从header中获取token
        if (!StringUtils.hasText(tokenValue)) {
            List<String> tokens = request.getHeaders().get(GlobalConstant.PARAM_TOKEN);
            if (!CollectionUtils.isEmpty(tokens)) {
                tokenValue = tokens.get(0);
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("gateway 获取到token:[{}]", tokenValue);
        }
        return tokenValue;
    }


    /**
     * 未授权输出
     *
     * @param serverWebExchange ServerWebExchange
     * @return Mono<Void>
     */
    private Mono<Void> responseData(ServerWebExchange serverWebExchange) {
        DataBuffer buffer;
        ServerHttpResponse response = serverWebExchange.getResponse();
        try {
            Result<String> data = Result.error(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
            buffer = response.bufferFactory().wrap(OBJECT_MAPPER.writeValueAsString(data).getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));

    }


}

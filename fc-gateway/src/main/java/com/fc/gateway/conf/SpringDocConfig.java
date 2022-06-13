package com.fc.gateway.conf;

import org.springdoc.core.*;
import org.springdoc.webflux.ui.SwaggerConfigResource;
import org.springdoc.webflux.ui.SwaggerWelcomeCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.*;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/9 16:04
 */
@Configuration
public class SpringDocConfig {

    @Autowired
    private DiscoveryClient discoveryClient;


    @Bean
    public SwaggerConfigResource openApiWebfluxResource(SwaggerWelcomeCommon swaggerWelcomeCommon) {
        return new SwaggerConfigResource(swaggerWelcomeCommon) {
            @Override
            public Map<String, Object> getSwaggerUiConfig(ServerHttpRequest request) {
                Map<String, Object> swaggerUiConfig = super.getSwaggerUiConfig(request);
                HashSet<AbstractSwaggerUiConfigProperties.SwaggerUrl> urls = new HashSet<>();
                //获取注册中心所有service
                discoveryClient.getServices().forEach(
                        service -> {
                            AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
                            swaggerUrl.setUrl(Constants.DEFAULT_API_DOCS_URL + "/" + service.toLowerCase(Locale.ENGLISH));
                            swaggerUrl.setDisplayName(service);
                            swaggerUrl.setName(service);
                            urls.add(swaggerUrl);
                        });
                swaggerUiConfig.put(SwaggerUiConfigParameters.URLS_PROPERTY, urls);
                return swaggerUiConfig;
            }
        };

    }
}

package com.ssos.web.base.config;

import com.ssos.web.base.properties.SwaggerPropertis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: BaseConfig
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018年12月13日
 * @Vsersion: 1.0
 */
@Configuration
@ConditionalOnClass(SwaggerConfig.class)
@EnableSwagger2
@EnableConfigurationProperties(SwaggerPropertis.class)
public class SwaggerConfig {
    @Autowired
    SwaggerPropertis swaggerPropertis;

    @Bean
    @ConditionalOnMissingBean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerPropertis.isEnable())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerPropertis.getBasePackage()))
                .build();
    }
}

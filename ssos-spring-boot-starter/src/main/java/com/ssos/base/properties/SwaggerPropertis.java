package com.ssos.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: SwaggerPropertis
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018/12/13 13:54
 * @Vsersion: 1.0
 */
@Data
@ConfigurationProperties(prefix = "web.swagger")
public class SwaggerPropertis {
    private String basePackage = "com";
    private boolean enable = false;
}

package com.ssos.web.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: SwaggerPropertis
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018/12/13 13:54
 * @Vsersion: 1.0
 */
@ConfigurationProperties(prefix = "web.swagger")
public class SwaggerPropertis {
    private String basePackage = "com";
    private boolean enable = false;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }
}

package com.ssos.web.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @ClassName: ShiroProperties
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018/12/13 14:20
 * @Vsersion: 1.0
 */
@ConfigurationProperties(prefix = "web.shiro")
public class ShiroProperties {
    private String loginUrl = "/login";
    private Map<String,String> filterChain ;
    private String algorithm = "md5";
    private Integer iterations = 1024;
    private boolean storedCredentialsHexEncoded = true;
    private boolean jwtEnable = false;

    public ShiroProperties() {
    }
    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public Map<String, String> getFilterChain() {
        return filterChain;
    }

    public void setFilterChain(Map<String, String> filterChain) {
        this.filterChain = filterChain;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public boolean isStoredCredentialsHexEncoded() {
        return storedCredentialsHexEncoded;
    }

    public void setStoredCredentialsHexEncoded(boolean storedCredentialsHexEncoded) {
        this.storedCredentialsHexEncoded = storedCredentialsHexEncoded;
    }

    public boolean isJwtEnable() {
        return jwtEnable;
    }
    public void setJwtEnable(boolean jwtEnable) {
        this.jwtEnable = jwtEnable;
    }
}

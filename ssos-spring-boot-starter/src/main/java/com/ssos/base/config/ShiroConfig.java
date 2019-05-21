package com.ssos.base.config;

import com.ssos.base.common.ShiroRealm;
import com.ssos.base.fileter.JwtFilter;
import com.ssos.base.properties.ShiroProperties;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description: TODD
 * @Author: xwl
 * @Date: 2018/12/13 14:20
 * @Vsersion: 1.0
 */
@Configuration
@EnableConfigurationProperties(ShiroProperties.class)
public class ShiroConfig {
    @Autowired
    ShiroProperties shiroProperties;

    @Bean
    @Order(4)
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        shiroFilterFactoryBean.setLoginUrl(shiroProperties.getLoginUrl());
        if (shiroProperties.isJwtEnable()) {
            Map<String, Filter> jwt = new HashMap<>(1);
            jwt.put("jwt", new JwtFilter());
            shiroFilterFactoryBean.setFilters(jwt);
        }
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroProperties.getFilterChain());
        return shiroFilterFactoryBean;
    }

    @Bean
    @Order(3)
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm());
        return defaultWebSecurityManager;
    }

    @Order(2)
    @Bean(name = "shiroRealm")
    public Realm realm() {
        ShiroRealm realm = new ShiroRealm();
        realm.setName("shiroRealm");
        realm.setAuthorizationCacheName("authorizationCache");
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean
    @Order(1)
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName(shiroProperties.getAlgorithm());
        hashedCredentialsMatcher.setHashIterations(shiroProperties.getIterations());
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(shiroProperties.isStoredCredentialsHexEncoded());
        return hashedCredentialsMatcher;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


}

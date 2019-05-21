package com.ssos.base.properties;

import lombok.Data;
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
@Data
public class ShiroProperties {
    private String loginUrl = "/login";
    private Map<String,String> filterChain ;
    private String algorithm = "md5";
    private Integer iterations = 1024;
    private boolean storedCredentialsHexEncoded = true;
    private boolean jwtEnable = false;

}

package com.ssos.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName: JwtPropertis
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-07-14 17:20
 * @Vsersion: 1.0
 */
@ConfigurationProperties(prefix = "web.jwt")
@Data
public class JwtProperties {
    private Long expireTime = 30L;
}

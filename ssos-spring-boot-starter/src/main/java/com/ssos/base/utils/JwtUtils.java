package com.ssos.base.utils;

import com.ssos.base.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName: JwtUtils
 * @Description: Jwt生成与解析
 * @Author: xwl
 * @Date: 2018-12-24 15:57
 * @Vsersion: 1.0
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtUtils {

    @Autowired
    private JwtProperties jwtProperties;

    private static long expireTime;

    @PostConstruct
    private void init() {
        JwtUtils.expireTime = this.jwtProperties.getExpireTime();
    }


    private static final String SECUREKEY = "dkfdfghjkljfld@%^&*()(*&^%$#ksjflj?....,/sldj@#$%^&*(f@#$%^&*sd#$%^&*/ijfow@#$%^&*in";

    /**
     * 生成Token
     */
    public static String createToken(Map<String, Object> map) {
        Claims claims = new DefaultClaims();
        claims.setIssuer("SSOS Service");
        claims.setSubject("user");
        claims.setExpiration(Date.from(LocalDateTime.now().plusMinutes(expireTime).atZone(ZoneId.systemDefault()).toInstant()));
        claims.putAll(map);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECUREKEY)
                .compact();
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECUREKEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public boolean isExpire(Claims claims) {
        if (claims.getExpiration().before(new Date())) {
            return true;
        } else {
            return false;
        }
    }
}

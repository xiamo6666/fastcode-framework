package com.ssos.web.base.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

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
public class JwtUtils {
    private static final String SECUREKEY = "dkfdfghjkljfld@%^&*()(*&^%$#ksjflj?....,/sldj@#$%^&*(f@#$%^&*sd#$%^&*/ijfow@#$%^&*in";

    /**
     * 生成Token
     */
    public static String createToken(Map<String, Object> map) {
        Claims claims = new DefaultClaims();
        claims.setIssuer("SSOS Service");
        claims.setSubject("user");
        claims.setExpiration(Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant()));
        claims.putAll(map);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SECUREKEY)
                .compact();
    }

    /**
     * 解析token
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
    public static boolean isExpire(Claims claims){
        if (claims.getExpiration().before(new Date())){
            return true;
        }else {
            return false;
        }
    }
}

package com.fc.utils.jwt;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.ObjectMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: JwtUtils
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/4/28 15:54
 * @Vsersion: 1.0
 */
@Slf4j
public class JwtUtils {

    /**
     * 加密算法
     */
    private final static Algorithm ALGORITHM = Algorithm.HMAC256("abcd!@#$%^&*()dcba");

    /**
     * LoginInfo 反射字段缓存
     */
    private final static Map<String, Field> CACHE_FIELDS = new ConcurrentHashMap<>(100);

    private final static String ISSUER = "fast-code";

    /**
     * 生成token
     *
     * @param obj
     * @param expires 分钟
     * @return token
     */

    public static String generateToken(Object obj, int expires) {
        Date expiresDate = DateTime.from(LocalDateTime.now().plusDays(expires).toInstant(ZoneOffset.of("+8")));
        JSONObject entries = new JSONObject(obj);
        ObjectMapper.of(obj).map(entries, null);
        entries.remove("iss");
        entries.remove("exp");
        return JWT.create()
                .withIssuer(ISSUER)
                .withPayload(entries)
                .withExpiresAt(expiresDate)
                .sign(ALGORITHM);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
        return verifier.verify(token);

    }

    public static boolean verifyTokenStatue(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("token:[{}] 已经过期", token);
            }
            return false;
        }


    }

    /**
     * 判断token是否需要刷新
     *
     * @param token
     * @return
     */
    public static boolean verifyTokenExpireTime(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        Date expiresAt = decodedJWT.getExpiresAt();
        long between = DateUtil.between(expiresAt, new Date(), DateUnit.MINUTE);
        if (between < 30) {
            return true;
        }
        return false;
    }


    public static <T> String generateTokenByToken(String token, Class<T> clazz) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        try {
            return generateToken(parseToken(decodedJWT, clazz), 1);
        } catch (Exception e) {
            log.warn("token 续期失败:[{}]", e.getMessage());
            return token;
        }

    }

    /**
     * 解析token
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T parseToken(DecodedJWT verify, Class<T> clazz) throws Exception {
        T obj = clazz.getConstructor().newInstance();
        //第一次使用初始化缓存
        if (CACHE_FIELDS.isEmpty()) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                CACHE_FIELDS.put(field.getName(), field);
            }
        }

        CACHE_FIELDS.forEach((k, v) -> {

            Object tokenObj = verify.getClaim(k).as(v.getType());
            //反射赋值
            v.setAccessible(true);
            try {
                v.set(obj, tokenObj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
        return obj;

    }
}

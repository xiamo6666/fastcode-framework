package com.fc.core.utils;


import cn.hutool.core.date.DateTime;
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

    private final static Algorithm algorithm = Algorithm.HMAC256("abcd!@#$%^&*()dcba");

    /**
     * LoginInfo 反射字段缓存
     */
    private final static Map<String, Field> CACHE_FIELDS = new ConcurrentHashMap<>(100);

    private final static String issuer = "fast-code";

    /**
     * 生成token
     *
     * @param obj
     * @param expires
     * @return
     */

    public static String generateToken(Object obj, int expires) {
        Date expiresDate = DateTime.from(LocalDateTime.now().plusMinutes(expires).toInstant(ZoneOffset.of("+8")));
        JSONObject entries = new JSONObject(obj);
        ObjectMapper.of(obj).map(entries, null);
        return JWT.create()
                .withIssuer(issuer)
                .withPayload(entries)
                .withExpiresAt(expiresDate)
                .sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
        return verifier.verify(token);

    }

    /**
     * 解析token
     *
     * @param verify
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

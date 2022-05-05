package com.fc.core;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fc.common.model.LoginInfo;
import com.fc.core.utils.JwtUtils;

/**
 * @ClassName: JwtUtilsTest
 * @Description: dto
 * @Author: xwl
 * @Date: 2022/4/28 17:44
 * @Vsersion: 1.0
 */
public class JwtUtilsTest {
    public static void main(String[] args) {
//        generate();
        ver();
    }

    public static String generate() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUserId("test");
        loginInfo.setUsername("test");
        loginInfo.setOrgId("test");
        loginInfo.setOrgName("test");
        return JwtUtils.generateToken(loginInfo, 10);
    }

    public static void ver() {
//        String token = "eyJraWQiOiJ0ZXN0IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJvcmdOYW1lIjoidGVzdCIsImlzcyI6ImZhc3QtY29kZSIsImV4cCI6MTY1MTczMjE1NywidXNlcklkIjoidGVzdCIsImp0aSI6InRlc3QiLCJvcmdJZCI6InRlc3QiLCJ1c2VybmFtZSI6InRlc3QifQ.5rveldA25yioKp8sxnr7wfm2AflnJefX0M8FYrWz3WA";
        DecodedJWT decodedJWT = JwtUtils.verifyToken(generate());
        LoginInfo o = null;
        try {
            o = JwtUtils.parseToken(decodedJWT, LoginInfo.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(o);
    }
}

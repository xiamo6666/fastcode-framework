package com.ssos.web.base.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssos.web.entity.User;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;

/**
 * @ClassName: UserUtils
 * @Description:
 * @Author: xwl
 * @Date: 2019-05-09 15:57
 * @Vsersion: 1.0
 */
public class UserUtils {

    public static User getUser() {
        Claims claims = JwtUtils.parseToken((String) SecurityUtils
                .getSubject().getPrincipals()
                .getPrimaryPrincipal());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(claims.get("username"), User.class);

    }
}

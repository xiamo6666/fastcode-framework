package com.ssos.web.base.common;

import com.ssos.web.base.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName: JwtToken
 * @Description: token验证
 * @Author: xwl
 * @Date: 2018-12-29 16:56
 * @Vsersion: 1.0
 */
public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {

        return token;
    }
    public String getUsername(){
        String  username  = (String) JwtUtils.parseToken(token).get("username");
        return username;
    }

    @Override
    public String getCredentials() {
        String  password = (String) JwtUtils.parseToken(token).get("password");
        return password;
    }
}

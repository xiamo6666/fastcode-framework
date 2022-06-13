package com.fc.system.login.service;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:33
 */
public interface LoginService {
    /**
     * 用户登入
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * 通过用户名获取token信息
     * 主要运用于第三方调用，无token的情况
     *
     * @param username 用户名
     * @return token
     */
    String loginByUsername(String username);
}

package com.fc.core.utils;

import com.fc.common.model.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName: UserUtils
 * @Description: 获取用户信息
 * @Author: xwl
 * @Date: 2022/5/5 16:14
 * @Vsersion: 1.0
 */
@Slf4j
public class UserUtils {
    /**
     * 登录用户集合
     */
    private final static ThreadLocal<LoginInfo> threadLocal = new ThreadLocal<>();

    public static String getUserId() {
        return getLoginInfo().getUserId();

    }

    public static String getUsername() {
        return getLoginInfo().getUsername();
    }

    public static String getOrgId() {
        return getLoginInfo().getOrgId();
    }

    public static String getOrgName() {
        return getLoginInfo().getOrgName();
    }

    public static void setLoginInfo(LoginInfo loginInfo) {
        threadLocal.set(loginInfo);
    }

    public static void removeLoginInfo() {
        threadLocal.remove();
    }

    private static LoginInfo getLoginInfo() {
        LoginInfo loginInfo = threadLocal.get();
        if (ObjectUtils.isEmpty(loginInfo)) {
            throw new RuntimeException("登录失效,请重新登录");
        }
        return loginInfo;
    }
}

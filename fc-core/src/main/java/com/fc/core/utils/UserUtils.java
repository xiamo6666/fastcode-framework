package com.fc.core.utils;

import com.fc.common.model.LoginInfo;
import com.fc.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 15:28
 */
@Slf4j
public class UserUtils {
    /**
     * 登录用户集合
     */
    private final static ThreadLocal<LoginInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static Long getUserId() {
        return getLoginInfo().getUserId();

    }

    public static String getUsername() {
        return getLoginInfo().getUsername();
    }

    public static String getOrgCode() {
        return getLoginInfo().getOrgCode();
    }

    public static String getOrgName() {
        return getLoginInfo().getOrgName();
    }

    public static void setLoginInfo(LoginInfo loginInfo) {
        THREAD_LOCAL.set(loginInfo);
    }

    public static void removeLoginInfo() {
        THREAD_LOCAL.remove();
    }

    private static LoginInfo getLoginInfo() {
        LoginInfo loginInfo = THREAD_LOCAL.get();
        if (ObjectUtils.isEmpty(loginInfo)) {
            throw new ServiceException("登录失效,请重新登录");
        }
        return loginInfo;
    }
}

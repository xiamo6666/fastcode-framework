package com.fc.common.model;

import lombok.Data;

/**
 * @ClassName: LoginInfo
 * @Description: 用户登录信息
 * @Author: xwl
 * @Date: 2022/4/28 15:27
 * @Vsersion: 1.0
 */
@Data
public class LoginInfo {
    private String token;
    private String userId;
    private String username;
    private String orgId;
    private String orgName;

}

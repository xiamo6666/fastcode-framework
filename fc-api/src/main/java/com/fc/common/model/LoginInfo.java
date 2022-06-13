package com.fc.common.model;

import lombok.Data;

/**
 * @author xwl
 * @version 1.0
 * @since 2022/6/8 10:00
 */
@Data

public class LoginInfo {
    private String token;
    private Long userId;
    private String username;
    private String orgCode;
    private String orgName;

}

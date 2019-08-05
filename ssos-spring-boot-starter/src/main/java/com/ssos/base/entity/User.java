package com.ssos.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Description: 登入信息
 * @Author: xwl
 * @Date: 2018/12/13 16:24
 * @Vsersion: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = -754667710L;
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 加密salt
     */
    private String salt;

    /**
     * 创建时间
     */
    private Date createTime;

    private String region;

    /**
     * 名称
     */
    private String name;

    /**
     * 账户状态
     */
    private Integer state;

    /**
     * 是否删除
     */
    private Integer isDelete;

    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
}

package com.ssos.web.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: Permission   
 * @Description: 权限信息
 * @Author: xwl
 * @Date: 2018/12/13 16:30
 * @Vsersion: 1.0
 */
@Data
public class Permission {
    /**
     * id
     */
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     *描述
     */
    private String description;

    /**
     *创建时间
     */
    private Date createTime;

    /**
     *上级id
     */
    private Long parentId;

    /**
     *权限识别
     */
    private String identify;

    /**
     *权限类型
     */
    private String resourceType;

    /**
     *接口地址
     */
    private String url;

}

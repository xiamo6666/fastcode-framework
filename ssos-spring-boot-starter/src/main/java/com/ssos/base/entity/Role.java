package com.ssos.base.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ClassName: Role
 * @Description: 角色信息
 * @Author: xwl
 * @Date: 2018/12/13 16:27
 * @Vsersion: 1.0
 */
@Data
@NoArgsConstructor
public class Role {
    /**
     * id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

}

package com.ssos.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: BusinessKey
 * @Description:业务流程定义
 * @Author: xwl
 * @Date: 2019-01-15 10:43
 * @Vsersion: 1.0
 */
@Data
public class BusinessKey {
    private Long id;
    private String businessIdentity;
    private String processDefinition;
    private Date createTime;
    private Integer isDelete;
}

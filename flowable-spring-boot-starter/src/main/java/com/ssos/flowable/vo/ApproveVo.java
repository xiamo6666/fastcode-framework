package com.ssos.flowable.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: ApproveVo
 * @Description: 审批列表
 * @Author: xwl
 * @Date: 2019-01-11 14:34
 * @Vsersion: 1.0
 */
@Data
public class ApproveVo {
    /**
     * 当前流程名字
     */
    private String processName;
    /**
     * 申请名字 如2018年计划
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否接受
     */
    private boolean isHandle;

    /**
     * 任务id
     */
    private String id;

    /**
     * 流程实例Id
     */
    private String processInstanceId;
}

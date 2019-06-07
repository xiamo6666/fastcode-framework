package com.ssos.flowable.vo;


import lombok.Data;

import java.util.Date;

/**
 * @ClassName: ProcessInfoVo
 * @Description: 我提交的审批列表
 * @Author: xwl
 * @Date: 2019-01-12 09:34
 * @Vsersion: 1.0
 */
@Data
public class ProcessInfoVo {
    /**
     * 流程名字如2018年计划
     */
    private String processName;
    /**
     *taskId
     */
    private  String  id;
    /**
     * 业务id
     */
    private String businessKey;

    /**
     * processInstanceId
     */
    private String processInstanceId;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 流程定义Id
     */
    private String processDefinitionId;

    /**
     * 当前状态
     */
    private String state;
}

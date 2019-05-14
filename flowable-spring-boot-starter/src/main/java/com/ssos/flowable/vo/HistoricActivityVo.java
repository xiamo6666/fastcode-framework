package com.ssos.flowable.vo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: HistoricActivityVo
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-01-11 16:33
 * @Vsersion: 1.0
 */
@Data
public class HistoricActivityVo {
    /**
     * 执行步骤
     */
    private String activityName;

    /**
     * 执行人
     */
    private String assignee;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 执行耗时
     */
    private Long durationInMillis;

    /**
     * 审批评论
     */
    private String message;
}

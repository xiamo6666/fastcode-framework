package com.ssos.flowable.service;


import com.ssos.flowable.vo.ApproveVo;
import com.ssos.flowable.vo.HistoricActivityVo;
import com.ssos.flowable.vo.ProcessInfoVo;

import java.util.List;

public interface FlowableService {


    /**
     * 添加一个任务流程
     *
     * @param userId      任务创建人
     * @param businessKey 业务Id
     * @param processName 业务流程
     * @return
     */

    String addTask(String userId, String businessKey, String processName);


    /**
     * 获取流程历史信息
     *
     * @param procInsId 流程id
     * @return
     */
    List<HistoricActivityVo> historic(String procInsId);

    /**
     * 签收审批
     *
     * @param taskId
     * @param userId
     * @param role
     */
    void claim(String taskId, String userId, String role);

    /**
     * 查询需要我审的审批
     *
     * @param userId
     * @param role
     * @return
     */
    List<ApproveVo> applyInfo(String userId, String role);

    /**
     * 查询审批状态
     *
     * @param processInstanceId
     * @return
     */
     String state(String processInstanceId);

    /**
     * 查看我提交的审批
     *
     * @param userId
     * @return
     */
    List<ProcessInfoVo> info(String userId);

    /**
     * @ClassName: FlowableService
     * @Description: 这里是进行审批处理
     * @Author: xwl
     * @Date: 2019-01-14 14:51
     * @Vsersion: 1.0
     */
    void approval(String taskId, String procInsId, String userId, String var, String message);


}

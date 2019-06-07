package com.ssos.flowable.service.impl;

import com.ssos.flowable.service.FlowableService;
import com.ssos.flowable.vo.ApproveVo;
import com.ssos.flowable.vo.HistoricActivityVo;
import com.ssos.flowable.vo.ProcessInfoVo;
import org.flowable.common.engine.api.FlowableObjectNotFoundException;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Comment;
import org.flowable.task.api.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: FlowableServicelmpl
 * @Description: TODD
 * @Author: xwl
 * @Date: 2019-04-24 12:07
 * @Vsersion: 1.0
 */
//@Service
public class FlowableServicelmpl implements FlowableService {
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IdentityService identityService;


    /**
     * 添加一个任务流程
     * @param userId 任务创建人
     * @param businessKey 业务Id
     * @param processName  业务流程
     * @return
     */

    @Override
    public String addTask(String userId,String businessKey,String processName){
        ProcessInstance processInstance;
        try {
            identityService.setAuthenticatedUserId(userId);
            processInstance = runtimeService.startProcessInstanceByKey(processName, businessKey);
        }catch (FlowableObjectNotFoundException e){
            throw new RuntimeException("业务流程不存在哦");
        }
        return processInstance.getId();
    }

    /**
     * 获取流程历史信息
     * @param procInsId 流程id
     * @return
     */
    @Override
    public List<HistoricActivityVo> historic(String procInsId){

        List<HistoricActivityVo> historics = new ArrayList();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(procInsId).orderByHistoricActivityInstanceStartTime()
                .asc().orderByHistoricActivityInstanceEndTime()
                .asc().list();
        if (list.size() == 0){
            throw  new RuntimeException("流程不存在");
        }
        list.forEach(e->{
            HistoricActivityVo historicActivityVo = new HistoricActivityVo();
            if (e.getActivityType().equalsIgnoreCase("startEvent")){
                String userId = historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(procInsId).list().get(0).getStartActivityId();
                BeanUtils.copyProperties(e,historicActivityVo);
                historicActivityVo.setAssignee(userId);
                historicActivityVo.setActivityName("开始审批");
                historics.add(historicActivityVo);
            }else if (e.getActivityType().equalsIgnoreCase("endEvent")){
                BeanUtils.copyProperties(e,historicActivityVo);
                historicActivityVo.setActivityName("完成审批");
                historics.add(historicActivityVo);
            }else if(e.getActivityType().equalsIgnoreCase("userTask")){
                List<Comment> taskComments = taskService.getTaskComments(e.getTaskId());
                if (taskComments.size() != 0){
                   historicActivityVo.setMessage(taskComments.get(0).getFullMessage());
                }
                BeanUtils.copyProperties(e,historicActivityVo);
                historics.add(historicActivityVo);
            }else { }

        });
        return historics;
    }

    /**
     * 签收审批
     * @param taskId
     * @param userId
     * @param role
     */
    @Override
    public void  claim(String taskId,String userId,String role){
        List<Task> roleTask = taskService.createTaskQuery().taskCandidateGroup(role).list();
        if (roleTask.size() == 0){
            throw  new RuntimeException("你目前还没有待办审批哦");
        }
        roleTask.forEach(e->{
           if (e.getId().equalsIgnoreCase(taskId)){
               List<Task> list = taskService.createTaskQuery().taskId(taskId).list();
               if (list.size()==0){
                   throw  new RuntimeException("taskId不存在");
               }
               if(list.get(0).getAssignee() != null){
                   throw  new RuntimeException("该审批已经被签收");
               }
               try {
                   taskService.claim(taskId, userId);
               }catch (Exception ee){
                   throw new RuntimeException("审批签收失败");
               }
           }else {
               throw new RuntimeException("请不要签收不属于自己的审批哦");
           }
        });

    }

    /**
     * 查询需要我审的审批
     * @param userId
     * @param role
     * @return
     */
    @Override
    public List<ApproveVo> applyInfo(String userId, String role){
        List<ApproveVo> approveVos = new ArrayList<>();
        //已经接受还未处理的审批
        taskService.createTaskQuery().taskAssignee(userId).active()
                .orderByTaskCreateTime().desc().list().forEach(e->{
            ApproveVo approveVo = new ApproveVo();
            BeanUtils.copyProperties(e,approveVo);
            approveVo.setName("任务名字");
            approveVo.setHandle(true);
            approveVos.add(approveVo);
        });
        //未接受的审批
        List<Task> tasks = taskService
                .createTaskQuery().taskCandidateGroup(role).active()
                .orderByTaskCreateTime().desc().list();
        tasks.forEach(e->{
            ApproveVo approveVo = new ApproveVo();
            BeanUtils.copyProperties(e,approveVo);
            approveVo.setName("任务名字");
            approveVo.setHandle(false);
            approveVos.add(approveVo);
        });
        return approveVos;
    }

    /**
     * 查询审批状态
     * @param processInstanceId
     * @return
     */
    @Override
    public String state(String processInstanceId ){
        List<HistoricProcessInstance> historicProcessInstances =
                historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(processInstanceId).list();
        if (historicProcessInstances.size() == 0){
            throw  new RuntimeException("流程id不存在");
        }
        List<Task> list = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
        if (list.size() == 0){
            return "已完成";
        }
        return list.get(0).getName();
    }

    /**
     * 查看我提交的审批
     * @param userId
     * @return
     */
    @Override
    public List<ProcessInfoVo> info(String userId){
        List<ProcessInfoVo> processInfoVos = new ArrayList<>();
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                .startedBy(userId).orderByProcessInstanceStartTime().desc().list();
        if (list.size() == 0){
            throw new  RuntimeException("您还有提交过申请额");
        }
        list.forEach(e->{
            ProcessInfoVo processInfoVo = new ProcessInfoVo();
            BeanUtils.copyProperties(e,processInfoVo);
            processInfoVo.setState(state(processInfoVo.getProcessInstanceId()));
            processInfoVos.add(processInfoVo);
        });
        return processInfoVos;
    }

    /**
     * @ClassName: FlowableService
     * @Description: 这里是进行审批处理
     * @Author: xwl
     * @Date: 2019-01-14 14:51
     * @Vsersion: 1.0
     */
    @Override
   public  void approval(String taskId,String procInsId,String userId,String var,String message){
       List<Task> list = taskService.createTaskQuery().taskId(taskId).list();
       if (list.size() == 0){
           throw new RuntimeException("审批taskId不存在");
       }
       if (list.get(0).getAssignee().equals(userId)){
           throw new RuntimeException("该流程不该你审批哦");
       }
       if (var.equalsIgnoreCase("true")){
           taskService.addComment(taskId,procInsId,"【通过】 "+message);
       }else if (var.equalsIgnoreCase("false")){
           taskService.addComment(taskId, procInsId, "【不通过】 " + message);
       }else{
           throw new RuntimeException("审批结果只能是TRUE or FALSE");
       }
       Map<String,Object> map = new HashMap<>(1);
       map.put("mssg",var);
       try {
           taskService.complete(taskId,map);
       }catch (FlowableObjectNotFoundException e){
           throw  new RuntimeException("处理失败,请稍后重试");
       }
   }
}

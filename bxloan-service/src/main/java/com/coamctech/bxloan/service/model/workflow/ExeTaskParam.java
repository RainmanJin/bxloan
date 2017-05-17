package com.coamctech.bxloan.service.model.workflow;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;

/**
 * <b>流程接口——执行任务参数</b>
 * 注：该类仅限于用于流程中执行任务传递参数，请慎重修改
 * @author Acore
 *
 */
public class ExeTaskParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 流程
	 */
	private WorkFlowCode workflowCode;
	/**
	 * 流程id
	 */
	private String workflowId;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 登录账号
	 */
	private String logName;
	/**
	 * 环节
	 */
	private WorkFlowNode node;
	/**
	 * 动作
	 */
	private ActionCode actionCode;
	/**
	 * 下一环节任务执行人
	 */
	private String taskReceiver;
	/**
	 * 下一环节任务执行机构
	 */
	private String orgId;
	/**
	 * 任务主题
	 */
	private String taskSubject;
	/**
	 * 意见
	 */
	private String comments;
	
	/**
	 * 构建参数（）
	 * @param workflowCode	流程编号
	 * @param workflowId 	流程id
	 * @param taskId	任务id
	 * @param logName 登录用户名	如果为null自动转换为""
	 * @param node	环节
	 * @param actionCode	按钮动作类型
	 * @param taskReceiver 下一环节接收人	如果为null自动转换为""
	 * @param comments	意见	如果为null自动转换为""
	 * @param taskSubject 任务主题	如果为null自动转换为""
	 * @param orgId 下一环节接收人机构id	 如果为null自动转换为""
	 * @return
	 * @since 2015-01-15
	 */
	public static ExeTaskParam newExeTaskParam(WorkFlowCode workflowCode,
			String workflowId, String taskId, String logName,
			WorkFlowNode node, ActionCode actionCode, String taskReceiver,
			String comments, String taskSubject, String orgId){
		ExeTaskParam param=new ExeTaskParam();
		param.workflowCode=workflowCode;
		param.workflowId=workflowId;
		param.taskId=taskId;
		param.logName=StringUtils.defaultString(logName);
		param.node=node;
		param.actionCode=actionCode;
		param.taskReceiver=StringUtils.defaultString(taskReceiver);
		param.comments=StringUtils.defaultString(comments);
		param.taskSubject=StringUtils.defaultString(taskSubject);
		param.orgId=StringUtils.defaultString(orgId);
		return param;
	} 
	
	public WorkFlowCode getWorkflowCode() {
		return workflowCode;
	}
	public void setWorkflowCode(WorkFlowCode workflowCode) {
		this.workflowCode = workflowCode;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getLogName() {
		return logName;
	}
	public void setLogName(String logName) {
		this.logName = logName;
	}
	public WorkFlowNode getNode() {
		return node;
	}
	public void setNode(WorkFlowNode node) {
		this.node = node;
	}
	public ActionCode getActionCode() {
		return actionCode;
	}
	public void setActionCode(ActionCode actionCode) {
		this.actionCode = actionCode;
	}
	public String getTaskReceiver() {
		return taskReceiver;
	}
	public void setTaskReceiver(String taskReceiver) {
		this.taskReceiver = taskReceiver;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getTaskSubject() {
		return taskSubject;
	}
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}

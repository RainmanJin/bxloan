package com.coamctech.bxloan.service.model;

/**
 * 历史记录查询结果
 * @author xc
 */
public class WorkListTask {
	
	private String actionCode; // 按钮类型
	private String actionNameCn; // 按钮中文名称
	private String actionNameEn; // 按钮ID
	private String comments; // 意见简述
	private String stageNameCn; // 环节中文名称
	private String stageNameEN; // 环节英文名称
	private String taskAssignDate; // 任务接收时间
	private String taskAssignee; // 任务接收人
	private String taskClaimer; // 任务申领者
	private String taskEndDate; // 任务结束时间
	private String taskId; // 任务ID
	private String taskStartDate; // 任务开始时间
	private String taskStatus; // 任务状态字符标识
	private String taskTypeId; // 环节ID
	private String workflowId; // 流程ID
	private String workflowInitiator; // 任务创建人
	private String workflowType; // 任务类型
	private String workflowTypeNameCn; // 任务流程中文名称
	private String workflowTypeNameEn; // 任务流程英文名称

	public WorkListTask() {
		super();
	}

	////////////////////////
	/////GETTERS&SETTERS/////
	////////////////////////
	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionNameCn() {
		return actionNameCn;
	}

	public void setActionNameCn(String actionNameCn) {
		this.actionNameCn = actionNameCn;
	}

	public String getActionNameEn() {
		return actionNameEn;
	}

	public void setActionNameEn(String actionNameEn) {
		this.actionNameEn = actionNameEn;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStageNameCn() {
		return stageNameCn;
	}

	public void setStageNameCn(String stageNameCn) {
		this.stageNameCn = stageNameCn;
	}

	public String getStageNameEN() {
		return stageNameEN;
	}

	public void setStageNameEN(String stageNameEN) {
		this.stageNameEN = stageNameEN;
	}

	public String getTaskAssignDate() {
		return taskAssignDate;
	}

	public void setTaskAssignDate(String taskAssignDate) {
		this.taskAssignDate = taskAssignDate;
	}

	public String getTaskAssignee() {
		return taskAssignee;
	}

	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}

	public String getTaskClaimer() {
		return taskClaimer;
	}

	public void setTaskClaimer(String taskClaimer) {
		this.taskClaimer = taskClaimer;
	}

	public String getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(String taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskStartDate() {
		return taskStartDate;
	}

	public void setTaskStartDate(String taskStartDate) {
		this.taskStartDate = taskStartDate;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(String taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}

	public String getWorkflowInitiator() {
		return workflowInitiator;
	}

	public void setWorkflowInitiator(String workflowInitiator) {
		this.workflowInitiator = workflowInitiator;
	}

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	public String getWorkflowTypeNameCn() {
		return workflowTypeNameCn;
	}

	public void setWorkflowTypeNameCn(String workflowTypeNameCn) {
		this.workflowTypeNameCn = workflowTypeNameCn;
	}

	public String getWorkflowTypeNameEn() {
		return workflowTypeNameEn;
	}

	public void setWorkflowTypeNameEn(String workflowTypeNameEn) {
		this.workflowTypeNameEn = workflowTypeNameEn;
	}

}

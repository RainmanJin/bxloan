package com.coamctech.bxloan.service.model.workflow;

public class HandledWorkflows {
	
	private String createTime;
	private String stageNameCn;
	private String taskAssignee;
	private String taskAssigneeCn;
	private String taskStatus;
	private String taskSubject;
	private String nodeId;
	private String workflowId;
	private String workflowName;
	/*new add by gph 20150521*/
	private String workflowTypeCode;//流程code
	private String taskId;//任务id
	private String taskStageCode;//任务阶段code
	private String endTime;//结束时间
	private String taskEndDateStart;//流程结束时间起
	private String taskEndDateEnd;//流程结束时间止
	private String comments;//评论
	private String actionCode;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStageNameCn() {
		return stageNameCn;
	}
	public void setStageNameCn(String stageNameCn) {
		this.stageNameCn = stageNameCn;
	}
	public String getTaskAssignee() {
		return taskAssignee;
	}
	public void setTaskAssignee(String taskAssignee) {
		this.taskAssignee = taskAssignee;
	}
	public String getTaskAssigneeCn() {
		return taskAssigneeCn;
	}
	public void setTaskAssigneeCn(String taskAssigneeCn) {
		this.taskAssigneeCn = taskAssigneeCn;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskSubject() {
		return taskSubject;
	}
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskStageCode() {
		return taskStageCode;
	}
	public void setTaskStageCode(String taskStageCode) {
		this.taskStageCode = taskStageCode;
	}
	
	public String getTaskEndDateStart() {
		return taskEndDateStart;
	}
	public void setTaskEndDateStart(String taskEndDateStart) {
		this.taskEndDateStart = taskEndDateStart;
	}
	public String getTaskEndDateEnd() {
		return taskEndDateEnd;
	}
	public void setTaskEndDateEnd(String taskEndDateEnd) {
		this.taskEndDateEnd = taskEndDateEnd;
	}
	public String getWorkflowTypeCode() {
		return workflowTypeCode;
	}
	public void setWorkflowTypeCode(String workflowTypeCode) {
		this.workflowTypeCode = workflowTypeCode;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	
}

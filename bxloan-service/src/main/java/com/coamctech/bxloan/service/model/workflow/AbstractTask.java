package com.coamctech.bxloan.service.model.workflow;

public abstract class AbstractTask {

	protected String message;
	/**
	 * 待办|已办环节名称
	 */
	protected String stageName;
	/**
	 * 当前所处环节名称
	 */
	protected String currentName;
	protected String taskAssignTime;
	protected String taskCreateTime;
	protected String taskCreator;
	protected String taskCreatorName;
	protected String taskId;
	protected String taskStatus;
	protected String taskSubject;
	protected String nodeId;
	protected String wfTaskId;
	protected String workflowId;
	protected String workflowName;
	protected String workflowCode;
	protected String sysCode;
	protected String taskConfirmTime;
	protected String taskConfirmor;
	protected String taskConfirmorName;
	protected String workflowCreator;
	protected String workflowCreatorName;
	protected String orgId;
	protected String orgName;
	protected String currentAssigneer;
	protected String currentAssigneerName;
	protected String currentStage;
	
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getTaskAssignTime() {
		return taskAssignTime;
	}
	public void setTaskAssignTime(String taskAssignTime) {
		this.taskAssignTime = taskAssignTime;
	}
	public String getTaskCreateTime() {
		return taskCreateTime;
	}
	public void setTaskCreateTime(String taskCreateTime) {
		this.taskCreateTime = taskCreateTime;
	}
	public String getTaskCreator() {
		return taskCreator;
	}
	public void setTaskCreator(String taskCreator) {
		this.taskCreator = taskCreator;
	}
	public String getTaskCreatorName() {
		return taskCreatorName;
	}
	public void setTaskCreatorName(String taskCreatorName) {
		this.taskCreatorName = taskCreatorName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
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
	public String getWfTaskId() {
		return wfTaskId;
	}
	public void setWfTaskId(String wfTaskId) {
		this.wfTaskId = wfTaskId;
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
	public String getWorkflowCode() {
		return workflowCode;
	}
	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getTaskConfirmTime() {
		return taskConfirmTime;
	}
	public void setTaskConfirmTime(String taskConfirmTime) {
		this.taskConfirmTime = taskConfirmTime;
	}
	public String getTaskConfirmor() {
		return taskConfirmor;
	}
	public void setTaskConfirmor(String taskConfirmor) {
		this.taskConfirmor = taskConfirmor;
	}
	public String getTaskConfirmorName() {
		return taskConfirmorName;
	}
	public void setTaskConfirmorName(String taskConfirmorName) {
		this.taskConfirmorName = taskConfirmorName;
	}
	public String getCurrentName() {
		return currentName;
	}
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}
	public String getWorkflowCreator() {
		return workflowCreator;
	}
	public void setWorkflowCreator(String workflowCreator) {
		this.workflowCreator = workflowCreator;
	}
	public String getWorkflowCreatorName() {
		return workflowCreatorName;
	}
	public void setWorkflowCreatorName(String workflowCreatorName) {
		this.workflowCreatorName = workflowCreatorName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCurrentAssigneer() {
		return currentAssigneer;
	}
	public void setCurrentAssigneer(String currentAssigneer) {
		this.currentAssigneer = currentAssigneer;
	}
	public String getCurrentAssigneerName() {
		return currentAssigneerName;
	}
	public void setCurrentAssigneerName(String currentAssigneerName) {
		this.currentAssigneerName = currentAssigneerName;
	}
	public String getCurrentStage() {
		return currentStage;
	}
	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}
	
}

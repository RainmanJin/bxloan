package com.coamctech.bxloan.service.model.workflow;

import com.coamctech.bxloan.commons.vo.BasicVO;

public class TaskTransferProcess extends BasicVO{

	private String actionCode;
	private String actionNameCn;
	private String actionNameEn;
	private String comments;
	private String createTime;
	private String endTime;
	private String remark;
	private String stageNameCn;
	private String taskAssignee;
	private String taskAssigneeCn;
	private String taskCreator;
	private String taskCreatorCn;
	private String taskId;
	private String taskResultCn;
	private String taskResultEn;
	private String taskStatus;
	private String nodeId;
	private String workflowFlag;
	private String workflowId;
	private String taskClaimer;//任务处理人
	private String taskClaimerCn;//任务处理人中文名称
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getTaskCreator() {
		return taskCreator;
	}
	public void setTaskCreator(String taskCreator) {
		this.taskCreator = taskCreator;
	}
	public String getTaskCreatorCn() {
		return taskCreatorCn;
	}
	public void setTaskCreatorCn(String taskCreatorCn) {
		this.taskCreatorCn = taskCreatorCn;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskResultCn() {
		return taskResultCn;
	}
	public void setTaskResultCn(String taskResultCn) {
		this.taskResultCn = taskResultCn;
	}
	public String getTaskResultEn() {
		return taskResultEn;
	}
	public void setTaskResultEn(String taskResultEn) {
		this.taskResultEn = taskResultEn;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getWorkflowFlag() {
		return workflowFlag;
	}
	public void setWorkflowFlag(String workflowFlag) {
		this.workflowFlag = workflowFlag;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getTaskClaimer() {
		return taskClaimer;
	}
	public void setTaskClaimer(String taskClaimer) {
		this.taskClaimer = taskClaimer;
	}
	public String getTaskClaimerCn() {
		return taskClaimerCn;
	}
	public void setTaskClaimerCn(String taskClaimerCn) {
		this.taskClaimerCn = taskClaimerCn;
	}
}

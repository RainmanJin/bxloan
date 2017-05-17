package com.coamctech.bxloan.service.model.workflowmonitor;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.entity.BaseEntity;

/**
 * 流程监控查看详细VO
 * @author xc
 */
public class WorkflowMonitorDetailVO extends BaseEntity{
	
	/***完成时间*/
	private String submitTime;
	/***审批意见*/
	private String comments;
	/***环节名称*/
	private String stageName;
	/***经办人Id*/
	private String assigneerId;
	/***经办人*/
	private String assigneerName;
	/***状态ID*/
	private String taskStateId;
	/***操作名称*/
	private String actionName;
	
	private String taskType;
	private String actionCode;
	
	public WorkflowMonitorDetailVO(){super();}
	public WorkflowMonitorDetailVO(Object[] dataSet){
		super();
		int index  =0;
		this.setSubmitTime(str(dataSet[index++]));
		this.setComments(str(dataSet[index++]));
		this.setStageName(str(dataSet[index++]));
		this.setAssigneerId(str(dataSet[index++]));
		this.setAssigneerName(str(dataSet[index++]));
		this.setTaskStateId(str(dataSet[index++]));
		this.setActionName(str(dataSet[index++]));
		this.setTaskType(str(dataSet[index++]));
		this.setActionCode(str(dataSet[index++]));
	}
	
	private String str(Object ori){
		return ori==null?"":StringUtils.trimToEmpty(ori.toString());
	}
	///////////////////////
	////GETTERS&SETTERS////
	///////////////////////
	public String getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getAssigneerName() {
		return assigneerName;
	}
	public void setAssigneerName(String assigneerName) {
		this.assigneerName = assigneerName;
	}
	public String getTaskStateId() {
		return taskStateId;
	}
	public void setTaskStateId(String taskStateId) {
		this.taskStateId = taskStateId;
	}
	public String getAssigneerId() {
		return assigneerId;
	}
	public void setAssigneerId(String assigneerId) {
		this.assigneerId = assigneerId;
	}
	public String getActionName() {
		return actionName;
	}
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	
}

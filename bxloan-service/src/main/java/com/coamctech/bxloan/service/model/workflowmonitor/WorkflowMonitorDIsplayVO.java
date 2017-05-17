package com.coamctech.bxloan.service.model.workflowmonitor;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.entity.BaseEntity;

/**
 * 流程监控列表显示数据VO
 * @author xc
 */
public class WorkflowMonitorDIsplayVO extends BaseEntity{
	
	/***流程名称(流程类型)*/
	private String processType;
	/***流程名称(流程类型ID)*/
	private String processTypeId;
	/***当前环节名称*/
	private String stageId;
	/***当前环节名称*/
	private String stageName;
	/***客户名称*/
	private String customerName;
	/***开始时间*/
	private String startTime;
	/***流程进行状态*/
	private String status;
	/***流程进行状态ID*/
	private String statusId;
	/***客户经理*/
	private String customerManagerName;
	/***工作流ID*/
	private String workflowId;
	private String projectId;
	private String taskId;
	
	public WorkflowMonitorDIsplayVO(){super();}
	
	public WorkflowMonitorDIsplayVO(Object[] resultSet){
		super();
		int index = 0;
		this.setProcessTypeId(str(resultSet[index++]));
		this.setProcessType(str(resultSet[index++]));
		this.setStageId(str(resultSet[index++]));
		this.setStageName(str(resultSet[index++]));
		this.setStartTime(str(resultSet[index++]));
		this.setStatusId(str(resultSet[index++]));
		this.setWorkflowId(str(resultSet[index++]));
		this.setCustomerName(str(resultSet[index++]));
		this.setCustomerManagerName(str(resultSet[index++]));
		this.setTaskId(str(resultSet[index++]));
		this.setProjectId(str(resultSet[index++]));
		this.setStatus("");//TODO
	}
	
	private String str(Object ori){
		return ori==null?"":StringUtils.trimToEmpty(ori.toString());
	}
	///////////////////////
	/////GETTERS&SETTERS///
	///////////////////////
	public String getProcessType() {
		return processType;
	}
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustomerManagerName() {
		return customerManagerName;
	}
	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}
	public String getWorkflowId() {
		return workflowId;
	}
	public void setWorkflowId(String workflowId) {
		this.workflowId = workflowId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getProcessTypeId() {
		return processTypeId;
	}
	public void setProcessTypeId(String processTypeId) {
		this.processTypeId = processTypeId;
	}
	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
}

package com.coamctech.bxloan.web.vo.dashboard;

import com.coamctech.bxloan.commons.entity.BaseEntity;

/**
 * 待办列表显示项
 * @author xc
 */
public class TodoListVO extends BaseEntity {
	
	protected String taskId;
	protected String taskSubject;//任务类型
	protected String workFlowId;
	protected String projectId;
	protected String customerName;
	protected String customerManagerName;
	protected String taskSubmitTime;
	protected String taskStageName;//所处环节名称
	protected String taskStageCode;//所处环节号
	
	public TodoListVO(){}
	public TodoListVO(Object[] dataSet){
		this.setTaskId(String.valueOf(dataSet[0]));
		this.setTaskSubject(String.valueOf(dataSet[1]));
		this.setTaskSubmitTime(String.valueOf(dataSet[2]));
		this.setTaskStageName(String.valueOf(dataSet[3]));
		this.setWorkFlowId(String.valueOf(dataSet[4]));
		this.setProjectId(String.valueOf(dataSet[5]));
		this.setCustomerName(String.valueOf(dataSet[6]));
		this.setCustomerManagerName(String.valueOf(dataSet[7]));
		this.setTaskStageCode(String.valueOf((dataSet[8])));
	}
	
	/////////////////////////
	//////GETTERS&SETTERS////
	/////////////////////////
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerManagerName() {
		return customerManagerName;
	}
	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}
	public String getTaskSubmitTime() {
		return taskSubmitTime;
	}
	public void setTaskSubmitTime(String taskSubmitTime) {
		this.taskSubmitTime = taskSubmitTime;
	}
	public String getTaskStageName() {
		return taskStageName;
	}
	public void setTaskStageName(String taskStageName) {
		this.taskStageName = taskStageName;
	}
	public String getTaskSubject() {
		return taskSubject;
	}
	public void setTaskSubject(String taskSubject) {
		this.taskSubject = taskSubject;
	}
	public String getTaskStageCode() {
		return taskStageCode;
	}
	public void setTaskStageCode(String taskStageCode) {
		this.taskStageCode = taskStageCode;
	}
	
	
}

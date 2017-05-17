package com.coamctech.bxloan.service.model;

public class WorkflowProcess {
	private String workflowId;
	private String taskId;
	
	public WorkflowProcess() {
		super();
	}

	public WorkflowProcess(String workflowId, String taskId) {
		super();
		this.workflowId = workflowId;
		this.taskId = taskId;
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
	
}

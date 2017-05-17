package com.coamctech.bxloan.web.vo.dashboard;

/**
 * 已办任务列表显示VO
 * @author xc
 */
public class TaskDoneListVO extends TodoListVO{

	/**
	 * 处理时间
	 */
	protected String taskConfirmTime;
	public TaskDoneListVO(){}
	public TaskDoneListVO(Object[] dataSet){
		super(dataSet);
		this.setTaskConfirmTime(String.valueOf(dataSet[9]));
	}
	
	//////////////////////
	////GETTERS&SETTERS///
	//////////////////////
	public String getTaskConfirmTime() {
		return taskConfirmTime;
	}
	public void setTaskConfirmTime(String taskConfirmTime) {
		this.taskConfirmTime = taskConfirmTime;
	}
	
	
}

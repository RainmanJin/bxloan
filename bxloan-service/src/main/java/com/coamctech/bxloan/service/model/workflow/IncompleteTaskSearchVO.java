package com.coamctech.bxloan.service.model.workflow;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * 查询待办列表VO
 * @author xc
 */
public class IncompleteTaskSearchVO {
	
	String search="";
	String curUser=""; 
	List<String> workflowTypeList;
	String taskCreator="";
	String workflowTypeId=""; 
	String taskCreateTimeStart="";
	String taskCreateTimeEnd=""; 
	Integer pageNumber; 
	Integer pageSize;
	
	public Object[] toArray(){
		List<Object> result = new ArrayList<Object>();
		PropertyDescriptor[] propDescArr = BeanUtils.getPropertyDescriptors(this.getClass());
		for (PropertyDescriptor propDesc : propDescArr) {
			Method read = propDesc.getReadMethod();
			read.setAccessible(Boolean.TRUE);
			try {
				Object prop = read.invoke(this);
				result.add(prop);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
		}
		return result.toArray();
	}
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getCurUser() {
		return curUser;
	}
	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}
	public List<String> getWorkflowTypeList() {
		return workflowTypeList;
	}
	public void setWorkflowTypeList(List<String> workflowTypeList) {
		this.workflowTypeList = workflowTypeList;
	}
	public String getTaskCreator() {
		return taskCreator;
	}
	public void setTaskCreator(String taskCreator) {
		this.taskCreator = taskCreator;
	}
	public String getWorkflowTypeId() {
		return workflowTypeId;
	}
	public void setWorkflowTypeId(String workflowTypeId) {
		this.workflowTypeId = workflowTypeId;
	}
	public String getTaskCreateTimeStart() {
		return taskCreateTimeStart;
	}
	public void setTaskCreateTimeStart(String taskCreateTimeStart) {
		this.taskCreateTimeStart = taskCreateTimeStart;
	}
	public String getTaskCreateTimeEnd() {
		return taskCreateTimeEnd;
	}
	public void setTaskCreateTimeEnd(String taskCreateTimeEnd) {
		this.taskCreateTimeEnd = taskCreateTimeEnd;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}

package com.coamctech.bxloan.service.model.statistics;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;

public class BizWfNodeVo implements Serializable {
	
	private Long workflowId;
	private String nodeCode;
	private Date receiveTime;
	private Date handleTime;
	private String handleResult;
	private String handlePerson;
	private String handlePersonName;
	
	

	public BizWfNodeVo(Object[] objs) {
		super();
		int i=0;
		this.workflowId=CommonHelper.toLong(objs[i++]);
		this.nodeCode=CommonHelper.toStr(objs[i++]);
		this.receiveTime=(Date)objs[i++];
		this.handleTime=(Date)objs[i++];
		this.handlePerson=CommonHelper.toStr(objs[i++]);
		this.handlePersonName=CommonHelper.toStr(objs[i++]);
		this.handleResult=toHandleResult(CommonHelper.toStr(objs[i++]));
	}
	private String toHandleResult(String handleResult){
		String result=StringUtils.EMPTY;
		if(StringUtils.isBlank(handleResult)){
			return result;
		}
		ActionCode ac=ActionCode.getActionCodeById(handleResult);
		switch (ac) {
		case COMMIT:
			result="同意";
			break;
		case SEND_BACK:
			result="退回";
			break;
		case CANCEL:
			result="撤销";
			break;

		default:
			result=ac.getCodeName();
			break;
		}
		return result;
	}
	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getNodeCode() {
		return nodeCode;
	}

	public void setNodeCode(String nodeCode) {
		this.nodeCode = nodeCode;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Date getHandleTime() {
		return handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getHandlePerson() {
		return handlePerson;
	}

	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	public String getHandlePersonName() {
		return handlePersonName;
	}

	public void setHandlePersonName(String handlePersonName) {
		this.handlePersonName = handlePersonName;
	}
}

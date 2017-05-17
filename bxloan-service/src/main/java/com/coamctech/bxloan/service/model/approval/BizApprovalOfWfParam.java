package com.coamctech.bxloan.service.model.approval;

import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;

/**
 * <b>审批流程中相关参数，主要用于流程相关参数</b>
 * @author Acore
 *
 */
public class BizApprovalOfWfParam {
	/**
	 * 流程id
	 */
	private String workflowId;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * 意见
	 */
	private String comments;
	/**
	 * 当前用户id
	 */
	private String curUserId;
	/**
	 * 当前用户登录名
	 */
	private String curUserLogName;
	
	/**
	 * 当前用户名
	 */
	private String curUserName;
	/**
	 * 下一环节执行人
	 */
	private String nextUser;
	/**
	 * 下一环节执行人机构id
	 */
	private String nextUserOrgId;
	
	/** 流程类型 */
	WorkFlowCode workFlowCode;
	
	/** 节点类型 */
	WorkFlowNode workFlowNode;
	
	public BizApprovalOfWfParam() {
		super();
	}
	
	/**
	 * 负责页面参数传递
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserLogname
	 * @param nextUser
	 * @param nextUserOrgId
	 * @return
	 */
	public static BizApprovalOfWfParam newBizApprovalOfWfParam(
			String workflowId, String taskId, String comments,
			String curUserLogname, String curUserName,
			String nextUser, String nextUserOrgId) {
		BizApprovalOfWfParam wfParam=new BizApprovalOfWfParam();
		wfParam.workflowId=workflowId;
		wfParam.taskId=taskId;
		wfParam.comments=comments;
		wfParam.curUserLogName=curUserLogname;
		wfParam.curUserName=curUserName;
		wfParam.nextUser=nextUser;
		wfParam.nextUserOrgId=nextUserOrgId;
		return wfParam;
	}
	
	/**
	 * 负责页面参数传递
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserLogname
	 * @param nextUser
	 * @param nextUserOrgId
	 * @param workFlowCode
	 * @param workFlowNode
	 * @return
	 */
	public static BizApprovalOfWfParam newBizApprovalOfWfParam(
			String workflowId, String taskId, String comments,
			String curUserLogname, String curUserName,
			String nextUser, String nextUserOrgId, WorkFlowCode workFlowCode,
			WorkFlowNode workFlowNode) {
		BizApprovalOfWfParam wfParam = new BizApprovalOfWfParam();
		wfParam.workflowId=workflowId;
		wfParam.taskId=taskId;
		wfParam.comments=comments;
		wfParam.curUserLogName=curUserLogname;
		wfParam.curUserName=curUserName;
		wfParam.nextUser=nextUser;
		wfParam.nextUserOrgId=nextUserOrgId;
		wfParam.workFlowCode = workFlowCode;
		wfParam.workFlowNode = workFlowNode;
		return wfParam;
	}
	
	//功能方法
	/**
	 * 负责对流程接口数据传递
	 * 参数还包括：curUserId,nextUser,nextUserOrgId
	 * @param workFlowCode
	 * @param node
	 * @param actionCode
	 * @return
	 */
	public ExeTaskParam toExeTaskParam(WorkFlowCode workFlowCode,WorkFlowNode node,ActionCode actionCode){
		return ExeTaskParam.newExeTaskParam(workFlowCode, workflowId, taskId, this.curUserLogName, node, actionCode, this.nextUser, this.comments, null, this.nextUserOrgId);
	}
	/**
	 * 
	 * 参数还包括：curUserId,nextUser,nextUserOrgId
	 * @param workFlowCode
	 * @param node
	 * @param actionCode
	 * @param taskSubject
	 * @return
	 */
	public ExeTaskParam toExeTaskParam(WorkFlowCode workFlowCode,WorkFlowNode node,ActionCode actionCode,String taskSubject){
		return ExeTaskParam.newExeTaskParam(workFlowCode, workflowId, taskId, this.curUserLogName, node, actionCode, this.nextUser, this.comments, taskSubject, this.nextUserOrgId);
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getCurUserId() {
		return curUserId;
	}
	public void setCurUserId(String curUserId) {
		this.curUserId = curUserId;
	}
	public String getNextUser() {
		return nextUser;
	}
	public void setNextUser(String nextUser) {
		this.nextUser = nextUser;
	}
	public String getNextUserOrgId() {
		return nextUserOrgId;
	}
	public void setNextUserOrgId(String nextUserOrgId) {
		this.nextUserOrgId = nextUserOrgId;
	}
	public String getCurUserLogName() {
		return curUserLogName;
	}
	public void setCurUserLogName(String curUserLogName) {
		this.curUserLogName = curUserLogName;
	}
	public String getCurUserName() {
		return curUserName;
	}
	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

	public WorkFlowCode getWorkFlowCode() {
		return workFlowCode;
	}

	public void setWorkFlowCode(WorkFlowCode workFlowCode) {
		this.workFlowCode = workFlowCode;
	}

	public WorkFlowNode getWorkFlowNode() {
		return workFlowNode;
	}

	public void setWorkFlowNode(WorkFlowNode workFlowNode) {
		this.workFlowNode = workFlowNode;
	}
}

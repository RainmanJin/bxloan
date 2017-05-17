package com.coamctech.bxloan.service.message;

public interface MessageCenterService {
	
	/**
	 * 发送拒绝消息
	 * 给此环节之前的所有审批岗位人员
	 * @param workflowId
	 * @param curUserLogName 当前用户的登录名
	 * @param curUserName 当前用户名
	 * */
	void sendRefusedMsg(Long workflowId, String curUserLogName, String curUserName);
	
	/**
	 * 发送通过消息
	 * 给此环节之前的所有审批岗位人员
	 * @param workflowId
	 * @param curUserLogName 当前用户的登录名
	 * @param curUserName 当前用户名
	 * */
	void sendPassedMsg(Long workflowId, String curUserLogName, String curUserName);
	
	/**
	 * 发送退回消息
	 * 给此环节之前的所有审批岗位人员
	 * @param workflowId
	 * @param curUserLogName 当前用户的登录名
	 * @param curUserName 当前用户名
	 * */
	void sendRetreatedMsg(Long workflowId, String curUserLogName, String curUserName);
}

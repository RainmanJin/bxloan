package com.coamctech.bxloan.service.dashboard;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.EcFunctiontreenode;


public interface DashboardService {
	
	List<EcFunctiontreenode> getMenusByPermission(Long personid, Long orgid);

	
	/**
	 * 查询整体消息列表
	 * messFlag 消息类型  
	 * applicant 申报人
	 * orgId 机构Id
	 * search    模糊查询
	 * */
	Page<Object[]> findAllMessageList(String applicant, String orgId , Integer pageNumber, Integer pageSize);
	
	/***
	 * 查询单个消息类型列表
	 * @param messFlag
	 * @param applicant
	 * @param orgId
	 * @param search
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	Page<Object[]> findOneTypeMessageList(String messFlag, String receiver, Integer pageNumber, Integer pageSize);
	
	/**
	 * 设置消息为已读
	 * @param msgId
	 * @author xc
	 */
	void doAlreadyReadMsg(Long msgId);
}

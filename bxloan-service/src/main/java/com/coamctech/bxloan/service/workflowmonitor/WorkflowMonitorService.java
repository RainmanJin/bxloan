package com.coamctech.bxloan.service.workflowmonitor;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.service.model.workflowmonitor.WorkflowMonitorDetailVO;


public interface WorkflowMonitorService {

	/**
	 * 流程监控分页
	 * @param customerName 客户名称
	 * @param processType 流程类型名称
	 * @param processStatus 流程状态(中止或进行中)
	 * @param submitTimeStart 提交时间
	 * @param submitTimeEnd
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @author xc
	 */
	Page<Object[]> search(String customerName,
			String processType, String processStatus, String submitTimeStart,
			String submitTimeEnd,String managerUserNum,Long managerOrgId, Integer pageNo, Integer pageSize);

	/**
	 * 查看单个工作流的详细过程
	 * @param workflowId
	 * @return
	 * @author xc
	 */
	List<WorkflowMonitorDetailVO> findDetaiByWorkId(String workflowId);
	
	AdminLevel findUserAdminLevel(String userNum);
	
	/**
	 * 管理员级别
	 * @author xc
	 */
	public static enum AdminLevel{
		/**
		 * 一级管理员
		 */
		CLASS_ONE,
		/**
		 * 二级管理员
		 */
		CLASS_TWO,
		/**
		 * 业务人员
		 */
		COMMON;
	}
	
}

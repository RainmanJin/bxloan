package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;

public interface ApprovalHistoryRepayPlanDao extends
		PagingAndSortingRepository<ApprovalHistoryRepayPlan, Long>,
		JpaSpecificationExecutor<ApprovalHistoryRepayPlan> {

	void deleteByProjectId(Long projectId);

	List<ApprovalHistoryRepayPlan> findByApprovalHistoryId(Long approvalHistoryId);
	
	List<ApprovalHistoryRepayPlan> findByApprovalHistoryIdAndProjectId(Long approvalHistoryId, Long projectId);
	
	List<ApprovalHistoryRepayPlan> findByProjectId(Long projectId);
	
	/**
	 * 查询审批最终的自定义还款计划
	 * @param projectId
	 * @return
	 */
	@Query("from ApprovalHistoryRepayPlan ahrp where ahrp.approvalHistoryId=(select max(temp.approvalHistoryId) from ApprovalHistoryRepayPlan temp where temp.projectId=?1) order by ahrp.approvalHistoryRepayPlanId")
	List<ApprovalHistoryRepayPlan> findLastGroupListByProjId(Long projectId);
	
	void deleteByProjectIdAndRepayAmtAndRepayDate(Long projectId, BigDecimal repayAmt, Date repayDate);
	
	void deleteByProjectIdAndRepayAmtAndRepayDateAndApprovalHistoryId(Long projectId, BigDecimal repayAmt, Date repayDate, Long approvalHistoryId);

	void deleteByProjectNo(String projectNo);

}

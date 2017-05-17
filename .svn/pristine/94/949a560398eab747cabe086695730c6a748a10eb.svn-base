package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayingPlan;

public interface RepayingPlanDao extends PagingAndSortingRepository<RepayingPlan, Long>,
JpaSpecificationExecutor<RepayingPlan>{
	/**
	 * 查询还款计划
	 * @param repayingPlanId
	 * @return
	 */
	RepayingPlan findByRepayingPlanId(Long repayingPlanId);
	/**
	 * 查询还款计划列表
	 * @param transNo
	 * @return
	 */
	@Query("from RepayingPlan where transactionNo = ?1")
	List<RepayingPlan> findListByTransNo(String transNo);
	
	/**
	 * 查询还款计划列表
	 * @param contractId	合同id
	 * @param payLoanId		放款id
	 * @return
	 */
	@Query("from RepayingPlan where contractId = ?1 AND payLoanId =?2")
	List<RepayingPlan> findListByContPl(Long contractId,Long payLoanId);
	/**
	 * @param transNo
	 * @param transStatus
	 * @return
	 */
	@Query("from RepayingPlan rp where rp.transactionNo = ?1 AND rp.transactionStatus !=?2")
	List<RepayingPlan> findListByTransNoStatus(String transNo,String transStatus);
}

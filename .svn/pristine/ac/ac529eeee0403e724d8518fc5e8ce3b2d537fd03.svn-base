package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayingPlanDetail;

public interface RepayingPlanDetailDao extends
		PagingAndSortingRepository<RepayingPlanDetail, Long>,
		JpaSpecificationExecutor<RepayingPlanDetail> {
	/**
	 * @param repayingPlanDetailId
	 * @return
	 */
	RepayingPlanDetail findByRepayingPlanDetailId(Long repayingPlanDetailId);
	
	/**
	 * 查询还款计划明细
	 * @param repayingPlanId	还款计划id	
	 * @return
	 */
	@Query("FROM RepayingPlanDetail where repayingPlanId=?1")
	List<RepayingPlanDetail> findListByRplId(Long repayingPlanId);
	
	/**
	 * 查询还款计划明细(currentPeriod asc排序)
	 * @param transNo
	 * @return
	 */
	@Query("FROM RepayingPlanDetail rpd where rpd.transactionNo=?1 order by rpd.currentPeriod asc ")
	List<RepayingPlanDetail> findListByTransNo(String transNo);
}

package com.coamctech.bxloan.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayingDetail;

public interface RepayingDetailDao extends
		PagingAndSortingRepository<RepayingDetail, Long>,
		JpaSpecificationExecutor<RepayingDetail> {
	/**
	 * @param repayingDetailId
	 * @return
	 */
	RepayingDetail findByRepayingDetailId(Long repayingDetailId);
	/**
	 * @param repayingPlanDetailIds
	 * @return
	 */
	@Query("from RepayingDetail where repayingPlanDetailId in (?1)  order by repayingNum asc ")
	List<RepayingDetail> findListByRpdIds(Collection<Long> repayingPlanDetailIds);
	
	/**
	 * 查询还款明细（repayingNum asc）
	 * @param repayingPlanDetailIds
	 * @return
	 */
	@Query("from RepayingDetail rd where rd.transactionNo =?1  order by rd.repayingNum asc ")
	List<RepayingDetail> findListByTransNo(String transNo);
}

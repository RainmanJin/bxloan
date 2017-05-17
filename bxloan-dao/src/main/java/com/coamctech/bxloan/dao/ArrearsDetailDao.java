package com.coamctech.bxloan.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ArrearsDetail;

public interface ArrearsDetailDao extends
		PagingAndSortingRepository<ArrearsDetail, Long>,
		JpaSpecificationExecutor<ArrearsDetail> {
	/**
	 * 查询还款明细
	 * @param arrearsDetailId	还款明细id
	 * @return
	 */
	ArrearsDetail findByArrearsDetailId(Long arrearsDetailId);
	/**
	 * @param repayingPlanDetailIds
	 * @return
	 */
	@Query("from ArrearsDetail where repayingPlanDetailId in (?1)  order by period asc ")
	List<ArrearsDetail> findListByRpdIds(Collection<Long> repayingPlanDetailIds);
	/**
	 * @param repayingPlanDetailIds
	 * @return
	 */
	@Query("from ArrearsDetail ad where ad.transactionNo = ?1  order by ad.period asc ")
	List<ArrearsDetail> findListByTransNo(String transNo);

}

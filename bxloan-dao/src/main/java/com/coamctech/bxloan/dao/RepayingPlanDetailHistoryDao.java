package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayingPlanDetailHistory;
import com.coamctech.bxloan.entity.RepayingPlanDetailHistoryPk;

public interface RepayingPlanDetailHistoryDao
		extends
		PagingAndSortingRepository<RepayingPlanDetailHistory, RepayingPlanDetailHistoryPk>,
		JpaSpecificationExecutor<RepayingPlanDetailHistory> {
	/**
	 * @param repayingPlanId
	 * @param serialNum
	 * @return
	 */
	@Query("from RepayingPlanDetailHistory rpdh where rpdh.repayingPlanId=?1 and rpdh.rpdhPk.serialNum=?2")
	List<RepayingPlanDetailHistory> findListByTransNo(Long repayingPlanId,Long serialNum);
}

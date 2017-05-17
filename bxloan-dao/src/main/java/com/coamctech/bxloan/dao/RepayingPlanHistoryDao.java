package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayingPlanHistory;
import com.coamctech.bxloan.entity.RepayingPlanHistoryPk;

public interface RepayingPlanHistoryDao extends
		PagingAndSortingRepository<RepayingPlanHistory, RepayingPlanHistoryPk>,
		JpaSpecificationExecutor<RepayingPlanHistory> {
	/**
	 * 查询还款计划列表
	 * @param transNo
	 * @return
	 */
	@Query("From RepayingPlanHistory where transactionNo=?1")
	List<RepayingPlanHistory> findListByTransNo(String transNo);
	
	/**
	 * 查询还款计划列表
	 * @param transNo	业务编号
	 * @param serialNum	序列号
	 * @return
	 */
	@Query("from RepayingPlanHistory rph where rph.transactionNo = ?1  and rph.id.serialNum = ?2")
	List<RepayingPlanHistory> findListByTransNoSerialNum(String transNo,Integer serialNum);
	
}

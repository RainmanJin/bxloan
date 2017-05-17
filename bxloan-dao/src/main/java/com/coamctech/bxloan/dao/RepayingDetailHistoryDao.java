package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayingDetailHistory;
import com.coamctech.bxloan.entity.RepayingDetailHistoryPk;

public interface RepayingDetailHistoryDao
		extends
		PagingAndSortingRepository<RepayingDetailHistory, RepayingDetailHistoryPk>,
		JpaSpecificationExecutor<RepayingDetailHistory> {
	/**
	 * 查询还款明细历史
	 * @param transNo	业务编号
	 * @param srialNum	序列号
	 * @return
	 */
	@Query("from RepayingDetailHistory rdh where rdh.transactionNo = ?1  and rdh.rdhPk.serialNum = ?2  order by rdh.repayingNum asc ")
	List<RepayingDetailHistory> findListByTransNoSerialNum(String transNo,Long srialNum);
	/**
	 * @param transNo
	 * @return
	 */
	@Query("from RepayingDetailHistory rdh where rdh.transactionNo = ?1 order by rdh.repayingNum asc ")
	List<RepayingDetailHistory> findListByTransNo(String transNo);

}

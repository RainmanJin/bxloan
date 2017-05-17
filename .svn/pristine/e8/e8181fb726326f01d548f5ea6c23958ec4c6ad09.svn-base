package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ArrearsDetailHistory;
import com.coamctech.bxloan.entity.ArrearsDetailHistoryPk;

public interface ArrearsDetailHistoryDao
		extends
		PagingAndSortingRepository<ArrearsDetailHistory, ArrearsDetailHistoryPk>,
		JpaSpecificationExecutor<ArrearsDetailHistory> {
	/**
	 * 查询欠款明细历史
	 * @param transNo	业务编号
	 * @param serialNums	序列号
	 * @return
	 */
	@Query("from ArrearsDetailHistory adh where adh.transactionNo = ?1 and adh.adhPk.serialNum = ?2  order by adh.period asc ")
	List<ArrearsDetailHistory> findListByTransNoSerialNum(String transNo,Long serialNum);
	/**
	 * @param transNo
	 * @return
	 */
	@Query("from ArrearsDetailHistory adh where adh.transactionNo = ?1 order by adh.period asc ")
	List<ArrearsDetailHistory> findListByTransNo(String transNo);
}

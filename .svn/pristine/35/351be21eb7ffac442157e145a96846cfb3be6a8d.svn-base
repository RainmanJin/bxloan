package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.BatchRecode;
import com.coamctech.bxloan.entity.BatchRecodePk;

public interface BatchRecodeDao extends PagingAndSortingRepository<BatchRecode, BatchRecodePk>, JpaSpecificationExecutor<BatchRecode> {
	/**
	 * 根据业务编号查询跑批记录
	 * @param transactionNo	业务编号
	 * @return
	 */
	@Query("FROM BatchRecode WHERE transactionNo=?1")
	List<BatchRecode> findListByTransNo(String transactionNo);
}

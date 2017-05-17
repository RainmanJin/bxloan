package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.LoanCancle;

public interface LoanCancleDao extends PagingAndSortingRepository<LoanCancle, Long>,
	JpaSpecificationExecutor<LoanCancle>{
	/**
	 * @param transNo
	 * @return
	 */
	@Query("from LoanCancle lc where lc.transactionNo=?1")
	List<LoanCancle> findListByTransNo(String transNo);

}

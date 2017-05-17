package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayLoan;

public interface RepayLoanDao extends
		PagingAndSortingRepository<RepayLoan, Long>,
		JpaSpecificationExecutor<RepayLoan> {
	/**
	 * @param transNo
	 * @return
	 */
	@Query("from RepayLoan rl where rl.transactionNo=?1 order by rl.repayDate desc")
	List<RepayLoan> findListByTransNo(String transNo);
	
	@Query("from RepayLoan rl where rl.contractId=?1 and  (rl.transactionNo is null or rl.transactionNo=?2) order by id desc")
	List<RepayLoan> findListByContractIdTransNo(Long contractId,String transNo);
}

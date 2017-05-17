package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.OverdueState;

public interface OverdueStateDao extends
		PagingAndSortingRepository<OverdueState, Long>,
		JpaSpecificationExecutor<OverdueState> {
	/**
	 * @param contractId
	 * @param payLoanStatus
	 * @return
	 */
	@Query("from OverdueState where contractId = ?1 AND payLoanStatus = ?2")
	List<OverdueState> findListByContractIdLoanStatus(Long contractId,String payLoanStatus);
}

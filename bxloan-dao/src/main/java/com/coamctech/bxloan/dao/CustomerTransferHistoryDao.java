package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CustomerTransferHistory;

public interface CustomerTransferHistoryDao extends
		PagingAndSortingRepository<CustomerTransferHistory, Long>,
		JpaSpecificationExecutor<CustomerTransferHistory> {
	
}

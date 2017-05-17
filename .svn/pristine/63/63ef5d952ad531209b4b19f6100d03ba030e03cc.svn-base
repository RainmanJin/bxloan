package com.coamctech.bxloan.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CustomerBrieflyFinace;

public interface CustomerBrieflyFinaceDao extends PagingAndSortingRepository<CustomerBrieflyFinace, BigDecimal>,
		JpaSpecificationExecutor<CustomerBrieflyFinace> {

	CustomerBrieflyFinace findByCustomerFinanceId(BigDecimal customerFinanceId);

	CustomerBrieflyFinace findByPartyId(Long partyId);

}

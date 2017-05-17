package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CustomerAccountManagent;

public interface CustomerAccountManagentDao extends PagingAndSortingRepository<CustomerAccountManagent, BigDecimal>,
		JpaSpecificationExecutor<CustomerAccountManagent> {

	CustomerAccountManagent findByAccountId(BigDecimal accountId);

	CustomerAccountManagent findByPartyId(BigDecimal partyId);
	
	@Query("from CustomerAccountManagent c where c.partyId = ?1 and c.accountNum = ?2 ")
	CustomerAccountManagent findByPartyIdAndAccountNum(BigDecimal partyId,
			String accountNum);

	@Query("from CustomerAccountManagent c where c.partyId = ?1 ")
	List<CustomerAccountManagent> findListByPartyId(BigDecimal partyId);
	
}

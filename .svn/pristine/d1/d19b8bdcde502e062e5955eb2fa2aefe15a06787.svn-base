package com.coamctech.bxloan.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerAccountManagent;

public interface CorporationCustomerDao extends PagingAndSortingRepository<CorporationCustomer, Long>,
JpaSpecificationExecutor<CorporationCustomer> {
	
	@Query(value = "select name from ec_org_person p where p.usernum = ?1", nativeQuery = true)
	String getUserName(String userNum);
}

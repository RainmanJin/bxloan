package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CustomerBusinessInfo;


public interface CustomerBusinessInfoDao extends PagingAndSortingRepository<CustomerBusinessInfo, Long>,
JpaSpecificationExecutor<CustomerBusinessInfo> {
	
	CustomerBusinessInfo findById(Long id);
	List<CustomerBusinessInfo> findByPartyId(Long partyId);
	
}

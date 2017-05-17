package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.Contract;

public interface CollateralDao extends PagingAndSortingRepository<Collateral, Long>,
JpaSpecificationExecutor<Collateral>{

	Collateral findByGuarantyId(Long guarantyId);
	
}

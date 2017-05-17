package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.AssTranContract;
import com.coamctech.bxloan.entity.AssTranProjectApplication;

public interface AssTranContractDao extends
PagingAndSortingRepository<AssTranContract, Long>,
JpaSpecificationExecutor<AssTranContract>{

	AssTranContract findByContractId(Long contractId);
	
}

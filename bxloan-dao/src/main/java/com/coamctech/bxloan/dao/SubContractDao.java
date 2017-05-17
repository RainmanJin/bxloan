package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.SubContract;

public interface SubContractDao extends PagingAndSortingRepository<SubContract, Long>, JpaSpecificationExecutor<SubContract> {

	List<SubContract> findByProjectId(Long projectId);

	SubContract findByAssurerId(Long projectAssurerInfoId);
	
	@Query("select sc from SubContract sc where sc.documentNum = ?1")
	List<SubContract> findByDocumentNum(String documentNum);
	
}
package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.FamilyConsume;

public interface FamilyConsumeDao extends PagingAndSortingRepository<FamilyConsume, Long>, JpaSpecificationExecutor<FamilyConsume> {

	FamilyConsume findByProjectIdAndType(Long projectId, String type);

	
}

package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.FamilyAssetsDetail;

public interface FamilyAssetsDetailDao extends
		PagingAndSortingRepository<FamilyAssetsDetail, String>,
		JpaSpecificationExecutor<FamilyAssetsDetail> {

	FamilyAssetsDetail findByProjectId(Long projectId);

}

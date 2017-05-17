package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.AssTranProjectApplication;

public interface AssTranProjectApplicationDao extends
PagingAndSortingRepository<AssTranProjectApplication, Long>,
JpaSpecificationExecutor<AssTranProjectApplication>{

	AssTranProjectApplication findByProjectId(Long projectId);
}

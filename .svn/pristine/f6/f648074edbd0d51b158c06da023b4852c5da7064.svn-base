package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.IndustryBiz;

public interface IndustryBizDao extends PagingAndSortingRepository<IndustryBiz, Long>, JpaSpecificationExecutor<IndustryBiz> {

	IndustryBiz findByProjectId(Long projectId);
}

package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coamctech.bxloan.entity.QuotaMeasure;

@Repository
public interface QuotaMeasureDao extends PagingAndSortingRepository<QuotaMeasure, Long>,
		JpaSpecificationExecutor<QuotaMeasure> {

	@Query("select q from QuotaMeasure q where q.projectId = :projectId ")
	QuotaMeasure findByProjectId(@Param(value = "projectId") Long projectId);
	
}

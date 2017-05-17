package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coamctech.bxloan.entity.QuotaMeasure;
import com.coamctech.bxloan.entity.WorkDraft;

@Repository
public interface WorkDraftDao extends PagingAndSortingRepository<WorkDraft, Long>,
		JpaSpecificationExecutor<WorkDraft> {
//
//	@Query("from WorkDraft where projectId=?1")
//	WorkDraft findByProjectId(Long projectId);

	@Query("select q from WorkDraft q where q.projectId = :projectId ")
	WorkDraft findByProjectId(@Param(value = "projectId") Long projectId);
}

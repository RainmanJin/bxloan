package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.MonthDominateIncludeMeasure;

public interface MonthDominateIncludeMeasureDao extends
		PagingAndSortingRepository<MonthDominateIncludeMeasure, Long>,
		JpaSpecificationExecutor<MonthDominateIncludeMeasure> {

	MonthDominateIncludeMeasure findByProjectId(Long projectId);

}

package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.InfoValues;

public interface InfoValuesDao extends PagingAndSortingRepository<InfoValues, Long>,JpaSpecificationExecutor<InfoValues>{

	List<InfoValues> findBySubjectId(Long subjectId);
}
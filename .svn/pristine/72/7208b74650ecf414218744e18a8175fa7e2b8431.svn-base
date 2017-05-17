package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.OtherIncomeCommon;

public interface OtherIncomeCommonDao extends PagingAndSortingRepository<OtherIncomeCommon, Long>, JpaSpecificationExecutor<OtherIncomeCommon> {

	List<OtherIncomeCommon> findByProjectIdAndType(Long projectId, String type);
}

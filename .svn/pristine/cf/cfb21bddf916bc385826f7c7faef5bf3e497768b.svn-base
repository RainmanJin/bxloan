package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;

public interface SalaBusiCustFinalcialDao extends
		PagingAndSortingRepository<SalaBusiCustFinalcial, Long>,
		JpaSpecificationExecutor<SalaBusiCustFinalcial> {

	SalaBusiCustFinalcial findByProjectId(Long projectId);

}

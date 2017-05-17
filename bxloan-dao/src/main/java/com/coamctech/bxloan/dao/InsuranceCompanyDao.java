package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.InsuranceCompany;

public interface InsuranceCompanyDao extends
		PagingAndSortingRepository<InsuranceCompany, Long>,
		JpaSpecificationExecutor<InsuranceCompany> {
	@Query("from InsuranceCompany ic where ic.parentId is not null and ic.parentId != 0")
	List<InsuranceCompany> findEffectiveCompanies();
}

package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.InsuranceRebate;

public interface InsuranceRebateDao extends
		PagingAndSortingRepository<InsuranceRebate, Long>,
		JpaSpecificationExecutor<InsuranceRebate> {

	@Query("select ir from InsuranceRebate ir where ir.insuranceId = ?1 and ir.guaranteeMode = ?2 and " +
			"ir.termUnit = ?3 and ir.startTerm <= ?4 and ir.endTerm > ?4")
	InsuranceRebate findUniqueOne(Long insuranceId, String guaranteeMode,
			String termUnit, Long term);

}

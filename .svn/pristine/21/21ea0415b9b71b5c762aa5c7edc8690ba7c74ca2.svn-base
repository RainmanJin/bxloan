package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.RepayPlanTemp;

public interface RepayPlanTempDao extends
		PagingAndSortingRepository<RepayPlanTemp, Long>,
		JpaSpecificationExecutor<RepayPlanTemp> {

	void deleteByProjectNo(String projectNo);

	@Query(value = "select sum(t.repay_amt) from repay_plan_temp t where t.project_no = ?1", nativeQuery = true)
	BigDecimal findTotalByProjectNo(String projectNo);

	List<RepayPlanTemp> findByProjectNo(String projectNo);

}

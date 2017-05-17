package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.IndustryBizIncomeCost;

public interface IndustryBizIncomeCostDao extends PagingAndSortingRepository<IndustryBizIncomeCost, Long>, JpaSpecificationExecutor<IndustryBizIncomeCost> {

	List<IndustryBizIncomeCost> findByProjectIdAndTypeAndFuturePastType(Long projectId, String type, String futurePastType);
	
	@Query(value = "select nvl(sum(i.year_income_total), 0) from industry_biz_income_cost i where i.project_id = ?1 and i.type = ?2", nativeQuery = true)
	BigDecimal sumYearIncomeTotal(Long projectId, String type);
	
	@Query(value = "select nvl(sum(i.year_changeable_cost_total), 0) + nvl(sum(i.cost_total), 0) from industry_biz_income_cost i where i.project_id = ?1 and i.type = ?2", nativeQuery = true)
	BigDecimal sumCostTotal(Long projectId, String type);
	
	@Query(value = "select nvl(sum(i.year_income_total), 0) from industry_biz_income_cost i where i.project_id = ?1 and i.type = ?2 and i.future_past_type = ?3", nativeQuery = true)
	BigDecimal sumYearIncomeTotal(Long projectId, String type, String futurePastType);
	
	@Query(value = "select max(id) from industry_biz_income_cost order by id desc", nativeQuery = true)
	BigDecimal getMaxId();
}

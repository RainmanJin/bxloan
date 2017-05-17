package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.OtherIncome;

public interface OtherIncomeDao extends JpaSpecificationExecutor<OtherIncome>,
		PagingAndSortingRepository<OtherIncome, Long>{

	@Query(value = "select nvl(sum(o.year_income), 0) from other_income o where o.project_id = ?1 and o.type = ?2", nativeQuery = true)
	BigDecimal sumYearIncome(Long projectId, String type);
	
	@Query(value = "select nvl(sum(o.year_income), 0) from other_income o where o.project_id = ?1 and o.type = ?2 and o.future_past_type = ?3", nativeQuery = true)
	BigDecimal sumYearIncome(Long projectId, String type, String futurePastType);
	
	/**
	 *  其他收入
	 * @param projectId
	 * @param type
	 * @param futurePastType
	 * @return
	 */
	@Query(value = "FROM OtherIncome oi where oi.projectId=?1 AND oi.type=?2 AND oi.futurePastType=?3 ORDER BY oi.otherIncomeType,oi.id")
	List<OtherIncome> findList(Long projectId,String type,String futurePastType);
}

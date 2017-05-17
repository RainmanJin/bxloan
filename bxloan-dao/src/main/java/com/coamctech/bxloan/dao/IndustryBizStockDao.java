package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.IndustryBizStock;

public interface IndustryBizStockDao extends PagingAndSortingRepository<IndustryBizStock, Long>, JpaSpecificationExecutor<IndustryBizStock> {

	List<IndustryBizStock> findByProjectId(Long projectId);
	
	@Query(value = "select nvl(sum(i.total), 0) from industry_biz_stock i where i.project_id = ?1", nativeQuery = true)
	BigDecimal sumTotal(Long projectId);
}

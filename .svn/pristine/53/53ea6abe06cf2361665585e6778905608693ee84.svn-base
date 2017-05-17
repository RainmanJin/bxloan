package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.BizRate;

public interface BizRateDao extends PagingAndSortingRepository<BizRate, Long>,
		JpaSpecificationExecutor<BizRate> {

	/**
	 * Description 根据customerNum查询利率表
	 * 
	 * @return
	 */
	@Query("select bz from BizRate bz,ProjectApplication pa where bz.projectId = pa.projectId and pa.customerNum = ?1")
	BizRate findBizByCusNum(String customerNum);

	BizRate findByProjectId(String projectId);
}

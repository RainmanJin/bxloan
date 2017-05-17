package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.BizExpenseRate;

public interface BizExpenseRateDao extends
		JpaSpecificationExecutor<BizExpenseRate>,
		PagingAndSortingRepository<BizExpenseRate, Long> {
	
	List<BizExpenseRate> findByProjectId(Long projectId);
	
	@Query("from BizExpenseRate ber where ber.projectId = ?1 order by ber.sysUpdateDate desc ")
	List<BizExpenseRate> findByProjectIdOrderBySysUpDate(Long projectId);
	/**
	 * 查询BizExpenseRate
	 * @param transNo	业务Id
	 * @return
	 */
	@Query("from BizExpenseRate ber where ber.transactionNo=?1 order by ber.id desc")
	List<BizExpenseRate> findListByTransNo(String transNo);


	@Query("from BizExpenseRate b where b.projectId = ?1 and b.expenseName = ?2")
	BizExpenseRate findByProjectIdAndExpenseName(Long projectId,
			String expenseName);

}

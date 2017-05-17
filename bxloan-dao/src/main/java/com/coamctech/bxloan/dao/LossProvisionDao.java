package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.coamctech.bxloan.entity.LossProvision;
@Repository
public interface LossProvisionDao extends
		PagingAndSortingRepository<LossProvision, Long>,
		JpaSpecificationExecutor<LossProvision> {
	@Query("select lp from LossProvision lp where lp.orgIdOrContractId = ?1 and lp.objectDimensionType = 2 and  lp.transactionStatus = ?2 ")
	List<LossProvision> findByOrgIdOrContractId(Long contractId, String transactionStatus);
	
	@Query("from LossProvision lp where lp.transactionNo = ?1 and lp.transactionStatus !=?2")
	List<LossProvision> findListBy(String transNo,String transStatus);
	
	@Query("from LossProvision lp where lp.orgIdOrContractId = ?1 and  lp.objectDimensionType = ?2 and  lp.transactionStatus = ?3 order by lp.serialNum desc ")
	List<LossProvision> findListByContractId(Long contractId, String objectDimensionType, String transactionStatus);

}

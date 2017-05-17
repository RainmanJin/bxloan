package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Contract;

public interface ContractDao extends PagingAndSortingRepository<Contract, Long>, JpaSpecificationExecutor<Contract> {
	
	Contract findByContractId(Long contractId);

	List<Contract> findByApplyUserNum(String applyUserNum);

	Contract findByProjectId(Long projectId);

	/**
	 * 根据工作流ID更新合同状态
	 * 
	 * @param workFlowId
	 * @param contractStatusCd
	 * @author xc
	 */
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE CONTRACT SET CONTRACT_STATUS_CD=?2  WHERE PROJECT_ID IN (SELECT pa.PROJECT_ID FROM PROJECT_APPLICATION pa WHERE pa.WORKFLOW_ID=?1)")
	void updateContractStatusCdByWorkFlowId(Long workFlowId, String contractStatusCd);
	
	/**
	 * 获取某产品在某机构的已用额度
	 * @return
	 */
	@Query(value = "select nvl(sum(c.contract_balance), 0) from contract c where c.product_type = ?1 and c.apply_org_id = ?2 and c.contract_status_cd in(?3)", nativeQuery = true)
	BigDecimal getUsedAmountOfProduct(String productType, Long applyOrgId, List<String> status);
}

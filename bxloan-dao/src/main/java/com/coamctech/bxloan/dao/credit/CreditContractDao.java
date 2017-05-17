package com.coamctech.bxloan.dao.credit;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.credit.CreditContract;

/**
 * 类名称：CreditContractDao 类描述 ：授信合同管理Dao 创建人: gph 创建时间：2015年5月13日 上午10:53:55 修改人：gph
 * 修改时间：2015年5月13日 上午10:53:55 修改备注： 版本： V1.0
 */
public interface CreditContractDao extends
		PagingAndSortingRepository<CreditContract, Long>,
		JpaSpecificationExecutor<CreditContract> {
	
	/** 
	 * 根据业务ID查询授信合同对象
	 * 
	 * @param projectId
	 * @return 授信合同对象
	 */
	@Query(value = "from CreditContract c where c.projectId = ?1")
	CreditContract findCreditContractByProjectId(Long projectId);
	
	/** 
	 * 通过业务ID更新合同状态
	 * 
	 * @param projectId 业务ID
	 * @param contractStatusCd 合同状态
	 */
	@Modifying
	@Query(value = "update credit_contract cc set cc.contract_status_cd = ?2 where cc.project_id = ?1", nativeQuery = true)
	void updateCreditContractByProjectId(Long projectId, String contractStatusCd);
	/** 
	 * 通过业务ID更新授信合同剩余可用金额
	 * 
	 * @param projectId 业务ID
	 * @param credit_avaiable_amt 剩余可用金额
	 */
	@Modifying
	@Query(value = "update credit_contract cc set cc.credit_avaiable_amt = ?2 where cc.project_id = ?1", nativeQuery = true)
	void updateCreditAvaiableAmtByPId(Long projectId, BigDecimal amount);
}

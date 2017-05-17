package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.PayLoanInfo;


public interface PayLoanInfoDao extends PagingAndSortingRepository<PayLoanInfo, Long>,
JpaSpecificationExecutor<PayLoanInfo> {
	
	PayLoanInfo findByPayLoanId(Long payLoanId);
	
	List<PayLoanInfo> findByTransactionNo(String transactionNo);
	
	List<PayLoanInfo> findByPartyId(Long partyId);
	
	List<PayLoanInfo> findByContractId(Long contractId);

	/**
	 * 根据查询放款信息且按payLoanId降序排列 
	 * @param transactionNo	交易编号
	 * @return
	 */
	@Query("FROM PayLoanInfo where transactionNo=?1 ORDER BY payLoanId DESC")
	List<PayLoanInfo> findListByTransactionNo( String transactionNo);
	
	/**
	 * 查询放款列表
	 * @param contractId	合同id
	 * @param payStatusCd	放款状态
	 * @param payLoanIndex	
	 * @return
	 */
	@Query("FROM PayLoanInfo where contractId=?1 AND payStatusCd=?2 AND payLoanIndex=?3")
	List<PayLoanInfo> findListByContStatus(Long contractId,String payStatusCd,Long payLoanIndex);
	/**
	 * 查询不在指定状态的放款列表
	 * @param contractId	合同id
	 * @param payStatusCd	放款状态
	 * @param payLoanIndex	
	 * @return
	 */
	@Query("FROM PayLoanInfo where contractId=?1 AND payStatusCd not in (?2)")
	List<PayLoanInfo> findByContractIdAndStatus(Long contractId, List<String> status);
}

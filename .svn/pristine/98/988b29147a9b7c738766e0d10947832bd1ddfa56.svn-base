package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.AccountingInfo;
import com.coamctech.bxloan.entity.AccountingInfoPk;

public interface AccountingInfoDao extends PagingAndSortingRepository<AccountingInfo, AccountingInfoPk>,JpaSpecificationExecutor<AccountingInfo>{
	
	/**
	 * 根据单据编号查询账务信息
	 * @param txrefNo	单据编号
	 * @return
	 */
	@Query("from AccountingInfo ai where ai.aiPk.txrefNo=?1")
	List<AccountingInfo> findListByTxrefNo(String txrefNo);
}

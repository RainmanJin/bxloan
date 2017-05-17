package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.AccountingConfig;
import com.coamctech.bxloan.entity.AccountingConfigPk;

public interface AccountingConfigDao extends
		PagingAndSortingRepository<AccountingConfig, AccountingConfigPk>,
		JpaSpecificationExecutor<AccountingConfig> {
	/**
	 * 查询账务配置
	 * @param bussType
	 * @param eventType
	 * @param itemId
	 * @return
	 */
	@Query("from AccountingConfig ac where ac.acPk.bussType=?1 and ac.acPk.eventType=?2 and ac.acPk.itemId=?3 order by ac.acPk.suitNo,ac.acPk.refCode ")
	List<AccountingConfig> findListByBussEventItem(String bussType, int eventType, int itemId);
}

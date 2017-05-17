package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.VerificatPersonInfo;

public interface VerificatPersonInfoDao extends PagingAndSortingRepository<VerificatPersonInfo, Long>,
		JpaSpecificationExecutor<VerificatPersonInfo> {


	VerificatPersonInfo findVerificatPersonInfoById(Long Id);

	@Query("from VerificatPersonInfo ff where ff.partyId = ?1 ")
	List<VerificatPersonInfo> findListByPartyId(Long partyId);
	
	
	
	
	
	/**
	 * 查询该业务的核实人数
	 * @param projectId
	 * @return
	 */
	@Query("select count(distinct ff) from VerificatPersonInfo ff where ff.projectId = ?1 ")
	long findVeriPersonListCount(Long projectId);
	
	
	/**
	 * 查询该客户的核实人总数
	 * @param partyId
	 * @since 2015-06-03
	 * @author HWL
	 * @return
	 */
	@Query("select count(distinct ff) from VerificatPersonInfo ff where ff.partyId = ?1 ")
	long findVeriPersonListCountByPartyId(Long partyId);
}

package com.coamctech.bxloan.dao;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Party;

public interface CustomerPartyDao extends PagingAndSortingRepository<Party, Long>,
		JpaSpecificationExecutor<Party> {

	Party findByPartyId(Long partyId);

	Party findByPartyName(String partyName);

	Party findByCertificateNum(String certificateNum);

	Party findByCustomerNum(String customerNum);

	@Query("select p from Party p where p.certificateTypeCd = ?1 and  p.certificateNum = ?2 ")
	Party findByCertificateNumAndCertificateType(String addCertificateType, String addCertificateNum);

	@Modifying
	@Query("update Party p set p.status = 2, p.sysUpdateTime = ?2 where p.partyId = ?1 ")
	void updatePartyStatus(Long partyId, Timestamp dbTime);
	
	@Modifying
	@Query("update Party p set p.partyName = ?3, p.sysUpdateTime = ?2 where p.partyId = ?1 ")
	void updatePartyName(Long partyId, Timestamp dbTime, String partyName);

}

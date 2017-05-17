package com.coamctech.bxloan.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Individual;

public interface CustomerIndividualDao extends PagingAndSortingRepository<Individual, Long>,
		JpaSpecificationExecutor<Individual> {

	Individual findByPartyId(Long partyId);

	Individual findByCustomerName(String customerName);

	@Query("select i from Individual i where i.customerName = ?1 order by i.partyId")
	Page<Individual> pagingQueryByCustomerName(String customerName, Pageable pageable);

	@Query("select i from Individual i where i.customerNum = ?1")
	Individual findByCustomerNum(String customerNum);

	@Modifying
	@Query("update Individual i set i.status = 2, i.sysUpdateTime = ?2 where i.partyId = ?1 ")
	void updateIndividualStatus(Long valueOf,Date dbTime);
	
	@Query("select i.marriageCd from Individual i where i.partyId = ?1")
	String findMarriageCdByPartyId(Long partyId);

}

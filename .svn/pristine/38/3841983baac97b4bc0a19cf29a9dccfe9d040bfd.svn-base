package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CustomerCorrelativeRelations;


public interface CustomerCorrelativeRelationsDao extends  PagingAndSortingRepository<CustomerCorrelativeRelations, Long>,
JpaSpecificationExecutor<CustomerCorrelativeRelations>{
	
	CustomerCorrelativeRelations findByCorrelativeRelationsId(Long id);
	@Query("select c from CustomerCorrelativeRelations c where c.id.partyId = ?1 ")
	List<CustomerCorrelativeRelations> findListByPartyId(Long partyId);
	@Query("select c from CustomerCorrelativeRelations c where c.correlativeRelationsId= ?1 and c.partyId = ?2 ")
	CustomerCorrelativeRelations findByCorrelativeRelationsIdAndPartyId(Long correlativeRelationsId, Long partyId);
	
}

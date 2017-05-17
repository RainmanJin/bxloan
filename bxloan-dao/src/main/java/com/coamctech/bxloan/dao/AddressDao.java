package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Address;

public interface AddressDao extends JpaSpecificationExecutor<Address>,
		PagingAndSortingRepository<Address, Long> {
	
	@Query("SELECT addr FROM Address addr where addr.partyId = ?1 " )
	List<Address> findByPartyId(Long partyId);

}

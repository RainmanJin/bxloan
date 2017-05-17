package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.Party;

public interface PartyDao extends PagingAndSortingRepository<Party, Long>,
JpaSpecificationExecutor<Party>{

	Party findByPartyId(Long partyId);
}

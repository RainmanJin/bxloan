package com.coamctech.bxloan.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.FamilyFriend;

public interface CustomerFamilyFriendDao extends PagingAndSortingRepository<FamilyFriend, Long>,
		JpaSpecificationExecutor<FamilyFriend> {

	FamilyFriend findByCorrelativeRelationsId(Long correlativeRelationsId);

	FamilyFriend findByPartyId(Long partyId);

	@Query("from FamilyFriend ff where ff.partyId = ?1 ")
	List<FamilyFriend> findListByPartyId(Long partyId);
}

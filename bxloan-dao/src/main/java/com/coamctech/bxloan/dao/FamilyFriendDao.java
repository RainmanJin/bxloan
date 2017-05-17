package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.FamilyFriend;

public interface FamilyFriendDao extends
		PagingAndSortingRepository<FamilyFriend, Long>,
		JpaSpecificationExecutor<FamilyFriend> {

	List<FamilyFriend> findByPartyId(Long partyId);

	@Query("from FamilyFriend f where f.partyId = ?1 and f.familyFriendType = '1'")
	FamilyFriend getSpouseByPartyId(Long partyId);

	/**
	 * FamilyFriend表中子女的个数
	 * 
	 * @param partyId
	 * @return
	 */
	@Query(value = "select count(*) from family_friend ff where ff.party_id = ?1 and ff.family_friend_type = '5'", nativeQuery = true)
	Integer countChildsByPartyId(Long partyId);

	/**
	 * FamilyFriend表中除配偶与子女的亲属个数
	 * 
	 * @param partyId
	 * @return
	 */
	@Query(value = "select count(*) from family_friend ff where ff.party_id = ?1 and ff.family_friend_type in ('2','3','4')", nativeQuery = true)
	Integer countRelationsExceptSpouseAndChildsByPartyId(Long partyId);

	/**
	 * FamilyFriend表中非亲属个数
	 * 
	 * @param partyId
	 * @return
	 */
	@Query(value = "select count(*) from family_friend ff where ff.party_id = ?1 and ff.family_friend_type in ('6','7','8')", nativeQuery = true)
	Integer countNonRelationsByPartyId(Long partyId);

}

package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.CommonBorrow;

public interface CommonBorrowDao extends PagingAndSortingRepository<CommonBorrow, Long>,JpaSpecificationExecutor<CommonBorrow>{

	List<CommonBorrow> findByProjectId(Long projectId);
	
	void deleteByProjectIdAndPartyId(Long projectId, Long partyId);
	
	CommonBorrow findByProjectIdAndPartyId(Long projectId, Long partyId);
}
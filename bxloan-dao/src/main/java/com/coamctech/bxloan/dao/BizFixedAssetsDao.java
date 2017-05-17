package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.BizFixedAssets;

public interface BizFixedAssetsDao extends
		JpaSpecificationExecutor<BizFixedAssets>,
		PagingAndSortingRepository<BizFixedAssets, Long> {
	@Query("FROM BizFixedAssets bfa where bfa.projectId=?1 AND bfa.bizType=?2")
	List<BizFixedAssets> findListByProjId(Long projId,String bizType);
}

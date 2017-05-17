package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.BizNoFixedAssetLiabilities;

public interface BizNoFixedAssetLiabilitiesDao extends
		JpaSpecificationExecutor<BizNoFixedAssetLiabilities>,
		PagingAndSortingRepository<BizNoFixedAssetLiabilities, Long> {
	/**
	 * @param projId
	 * @param bizType
	 * @return
	 */
	@Query("FROM BizNoFixedAssetLiabilities bnfaal where bnfaal.projectId=?1 AND bizType=?2")
	List<BizNoFixedAssetLiabilities> findListByProjId(Long projId,String bizType);

}

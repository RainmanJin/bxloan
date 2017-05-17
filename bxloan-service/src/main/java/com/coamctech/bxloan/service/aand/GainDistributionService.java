package com.coamctech.bxloan.service.aand;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.GainDistribution;
import com.coamctech.bxloan.service.model.aand.GainDistributionVo;

public interface GainDistributionService{
	
	Page<GainDistribution> findBySearch(String search, Integer pageNumber,
			Integer pageSize, String type,Long projectId);

	void save(GainDistributionVo gainDistribution);

	void delete(Long id);

	GainDistribution get(Long id);

	void saveGainDistribution(GainDistribution gainDistribution);

}

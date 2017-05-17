package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.GainDistribution;

public interface GainDistributionDao extends JpaSpecificationExecutor<GainDistribution>,
	PagingAndSortingRepository<GainDistribution, Long>{

}

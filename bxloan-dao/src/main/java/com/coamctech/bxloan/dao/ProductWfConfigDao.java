package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ProductWfConfig;

public interface ProductWfConfigDao extends PagingAndSortingRepository<ProductWfConfig, Long>,JpaSpecificationExecutor<ProductWfConfig>{

	@Modifying
	@Query("delete ProductWfConfig pf where pf.pcId =?1 ")
	void deleteProWfConf(Long pcId);

}
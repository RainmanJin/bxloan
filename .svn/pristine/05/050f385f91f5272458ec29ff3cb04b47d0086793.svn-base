package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ProductConfig;

public interface ProductConfigDao extends
		PagingAndSortingRepository<ProductConfig, Long>,
		JpaSpecificationExecutor<ProductConfig> {

	@Query("select pc.wfCode From ProductConfig pc where pc.productCd = ?1 ")
	String findWfCodeByProductCd(Long productCd);

	ProductConfig findByProductCdAndOrgId(Long productCd, Long orgId);
	
	@Modifying
	@Query("delete ProductConfig pc where pc.productCd =?1 ")
	void delProductConfByPd(Long productCd);
	
	@Query("select pc From ProductConfig pc where pc.productCd =?1")
	List<ProductConfig> getConfByProductCd(Long productCd);

}

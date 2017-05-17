package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.coamctech.bxloan.entity.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Long>,
		JpaSpecificationExecutor<Product> {

	Product findByProductCd(Long productCd);

	@Query("select p from Product p where p.productCd = 283 or p.productCd = 303")
	List<Product> findEasyLoanProduct();

	@Query("select p from Product p , ProductConfig pc where p.productCd = pc.productCd and p.partyTypeCd = ?1 and pc.orgId = ?2")
	List<Product> findByPartyTypeCdAndOrgId(String partyTypeCd, Long orgId);
}

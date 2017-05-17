package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.ProductPrice;

public interface ProductPriceDao extends
		PagingAndSortingRepository<ProductPrice, Long>,
		JpaSpecificationExecutor<ProductPrice> {

	/**
	 * Description 根据id查询具体的产品定价信息
	 * 
	 * @return ProductPrice
	 */
	ProductPrice findById(Long id);

	/**
	 * Description 根据customerNum查询具体的产品定价信息
	 * 
	 * @return ProductPrice
	 */
	@Query("select pp from ProductPrice pp, ProjectApplication pro where pp.loanProduct = pro.productType and pro.customerNum = ?1")
	ProductPrice findByCusNum(String customerNum);

	@Query(value = "select p.product_cd productCd, p.product_name productName, p.party_type_cd partyTypeCd from product p", nativeQuery = true)
	List<Object[]> findProduct();

	/**
	 * Description 根据贷款产品获取产品定价信息
	 * 
	 * @param loanProduct
	 * @return
	 */
	@Query("from ProductPrice pp where pp.loanProduct = ?1")
	ProductPrice findByloanProduct(String loanProduct);
}
package com.coamctech.bxloan.service.sysmng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductPrice;
import com.coamctech.bxloan.service.model.bizapply.EffectiveProductVO;

public interface ProductPriceService {

	/**
	 * Description 查询产品定价信息
	 * 
	 * @return
	 */
	Page<ProductPrice> findBySearch(Integer pageNumber, Integer pageSize);

	/**
	 * Description 添加产品定价信息
	 * 
	 * @return
	 */
	void save(ProductPrice productPrice);

	/**
	 * Description 修改产品定价信息
	 * 
	 * @return
	 */
	ProductPrice get(Long id);

	/**
	 * Description 删除产品定价信息
	 * 
	 * @return
	 */
	void delete(Long id);

	/**
	 * Description 根据customerNum来查询具体的
	 * 
	 * @return
	 */
	ProductPrice getProPriInfo(String customerNum);

	/**
	 * 获取产品列表
	 * 
	 * @return
	 */
	List<Object[]> findProduct();

	/**
	 * 获取产品列表，以对象形式返回
	 * 
	 * @return
	 */
	List<Product> findProductByPartyTypeCdAndOrgId(String partyTypeCd,
			Long orgId);

	/**
	 * 通过贷款产品获取产品定价信息
	 * 
	 * 
	 * @return
	 */
	ProductPrice findByLoanProduct(ProductPrice productPrice);

	/**
	 * 通过产品号获取产品
	 * 
	 * @param productCd
	 * @return
	 */
	Product getProductByProductCd(String productCd);

	/**
	 * 获取当前登陆人所在机构所有有效的产品
	 * 
	 * @param partyId
	 * @param orgId
	 * @param isDesignated
	 * @return
	 */
	List<EffectiveProductVO> findEffectiveProduct(Long partyId, Long orgId, Boolean isDesignated);

}

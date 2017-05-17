package com.coamctech.bxloan.service.sysmng.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.IndividualDao;
import com.coamctech.bxloan.dao.PartyDao;
import com.coamctech.bxloan.dao.ProductDao;
import com.coamctech.bxloan.dao.ProductPriceDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductPrice;
import com.coamctech.bxloan.service.model.bizapply.EffectiveProductVO;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;

/**
 * @Description:
 * @author syy
 * @version V1.0
 * @Date 2014年7月22日
 */
@Transactional
@Service
public class ProductPriceServiceImpl implements ProductPriceService {
	@Autowired
	private ProductPriceDao productPriceDao;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private PartyDao partyDao;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private IndividualDao individualDao;

	/**
	 * Description 查询产品定价信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<ProductPrice> findBySearch(Integer pageNumber, Integer pageSize) {
		List<Object> params = new ArrayList<Object>();
		String nativeSql = "select b.product_name,a.loan_term,a.repayment_cucle,a.rate*100,"
				+ "a.overdue_rate*100,a.percul_nego_rate*100,"
				+ "a.early_repayment*100,a.id "
				+ "from product_price a "
				+ "join product b on a.loan_product=b.product_cd ";
		nativeSql += "order by a.loan_product";
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), nativeSql.toString(), params.toArray());
	}

	/**
	 * Description 添加产品定价
	 * 
	 * @return
	 */
	@Transactional
	public void save(ProductPrice productPrice) {
		productPriceDao.save(productPrice);
	}

	/**
	 * Description 修改产品定价
	 * 
	 * @return
	 */
	@Override
	public ProductPrice get(Long id) {
		return productPriceDao.findById(id);
	}

	/**
	 * Description 删除产品定价
	 * 
	 * @return
	 */
	@Override
	public void delete(Long id) {
		productPriceDao.delete(id);
	}

	/**
	 * Description 根据customerNum查询贷款产品信息
	 * 
	 * @return
	 */
	public ProductPrice getProPriInfo(String customerNum) {
		// return productPriceDao.findByCusNum(customerNum);
		return null;
	}

	@Override
	public List<Object[]> findProduct() {
		return productPriceDao.findProduct();
	}

	@Override
	public ProductPrice findByLoanProduct(ProductPrice productPrice) {
		if (productPrice.getId() != null) {
			ProductPrice persistentProductPrice = productPriceDao
					.findOne(productPrice.getId());
			if (persistentProductPrice.getLoanProduct().equals(
					productPrice.getLoanProduct())) {
				return null;
			}
		}
		return productPriceDao.findByloanProduct(productPrice.getLoanProduct());
	}

	@Override
	public List<Product> findProductByPartyTypeCdAndOrgId(String partyTypeCd,
			Long orgId) {
		return productDao.findByPartyTypeCdAndOrgId(partyTypeCd, orgId);
	}

	@Override
	public Product getProductByProductCd(String productCd) {
		return productDao.findOne(Long.valueOf(productCd));
	}

	@Override
	public List<EffectiveProductVO> findEffectiveProduct(Long partyId, Long orgId, Boolean isDesignated) {
		
		Boolean isAdmin = orgId == null ? true : false;
		int i = 0;
		
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		sql.append("select ");
		sql.append("	p.product_cd, ");
		sql.append("	p.parent_product_cd, ");
		sql.append("	p.product_name, ");
		
		if (isAdmin) {
			sql.append("	'' ");
			sql.append("from ");
			sql.append("	product p ");
			sql.append(" where p.product_control_type_cd = '01' ");
			sql.append(" AND p.product_cd in (select  distinct pc.product_cd from  product_config pc ) ");
		} else {
			sql.append("	pc.product_desc ");
			sql.append("from ");
			sql.append("	product p, ");
			sql.append("	product_config pc ");
			sql.append("where ");
			sql.append("	p.product_cd = pc.product_cd ");
			sql.append("and ");
			sql.append("	p.product_control_type_cd = '01' ");
			sql.append("and ");
			sql.append("	pc.org_id = ?" + ++i + " ");
			
			params.add(orgId);
			
			if (partyId != null) {
				sql.append("and ");
				sql.append("	instr(p.customer_type, ?" + ++i + ") > 0 ");
				
				Party party = partyDao.findOne(partyId);
				
				params.add(party.getPartyTypeCd());
				if (dataDict.getCodeVal("CustomerType", "S2").equals(party.getPartyTypeCd())) {// 个人客户
					sql.append("and ");
					sql.append("	instr(pc.customer_property, ?" + ++i + ") > 0 ");
					Individual individual = individualDao.findOne(partyId);
					params.add(individual.getEmploymentType());
				}
			}
			
		}
		
		if (isDesignated) {
			String productsCd = GlobalConstants.ALLOWED_SET_PRICE_PRODUCTS;
			sql.append("	and pc.product_cd in (" + productsCd + ")");
		}
		
		
		//2015-04-25 shenzuoxin
		if (StringUtils.isNotBlank(sql)) {
			if(isAdmin){
				
				sql.append("	order by p.product_cd     ");
			}else{
				sql.append("	order by pc.wf_code desc,product_cd     ");
			}
		}
		
		
		List<Object[]> list = dynamicQuery.nativeQuery(sql.toString(), params.toArray());
		List<EffectiveProductVO> resultList = new ArrayList<EffectiveProductVO>();
		for (Object[] obj : list) {
			EffectiveProductVO vo = new EffectiveProductVO();
			vo.setProductCd(obj[0] != null ? Long.valueOf(obj[0].toString()) : null);
			vo.setParentProductCd(obj[1] != null ? Long.valueOf(obj[1].toString()) : null);
			vo.setProductName(obj[2] != null ? obj[2].toString() : null);
			vo.setProductDesc(obj[3] != null ? obj[3].toString() : null);
			resultList.add(vo);
		}
		return resultList;
	}
}

package com.coamctech.bxloan.service.sysmng;

import java.util.List;

import org.springframework.data.domain.Page;

import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.service.model.ecOrgDep.EcOrgDepartmentVO;
import com.coamctech.bxloan.service.model.sysmng.ProductMngVO;
import com.coamctech.bxloan.service.model.tipmsg.MsgResult;

public interface ProductMngService {

	/**
	 * 根据条件查询产品配置信息数据
	 * @param pageNumber
	 * @param pageSize
	 * @param productName
	 * @param orgIds 
	 * @return
	 */
	Page<Object[]> findBySearch(Integer pageNumber, Integer pageSize,String productName, String orgIds);

	/**
	 * 根据id获取数据
	 * @param id
	 * @return
	 */
	ProductConfig getConfByProductCd(Long productCd);

	/**
	 * 保存产品 配置信息
	 * @param form
	 * @return
	 */
	MsgResult saveProductMng(ProductMngVO form);

	/**
	 * 通过Product_cd 查找产品
	 * @param id
	 * @return
	 */
	Product getProductByProductCd(Long id);

	/**
	 * 查询适用机构
	 * @return
	 */
	List<EcOrgDepartmentVO> getEcOrgDep();
	
	/** 
	 * 通过业务ID获取产品配置信息
	 * 
	 * @param projectId 业务ID
	 * @return
	 */
	ProductConfig findByProjectId(Long projectId);
	
	/** 
	 * 通过机构ID，贷款产品ID，流程类型和业务类型获取唯一产品配置信息
	 * 
	 * @param orgId 机构ID
	 * @param productCd 贷款产品ID
	 * @param workFlowType 流程类型
	 * @param projectBusinessType 业务类型
	 * @return ProductConfig 产品配置实体
	 */
	ProductConfig findUniqueProductConfig(Long orgId, Long productCd, String workFlowType, String projectBusinessType);
	
	/** 
	 * 通过机构ID，贷款产品ID，流程类型获取唯一产品配置信息
	 * 
	 * @param orgId 机构ID
	 * @param productCd 贷款产品ID
	 * @param workFlowType 流程类型
	 * @return ProductConfig 产品配置实体
	 */
	ProductConfig findUniqueProductConfig(Long orgId, Long productCd, String workFlowType);
}

package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.WfProductConfigRelation;

/**
 * 类名称：WfProductConfigRelationDao 
 * 类描述 ： 流程与产品配置关系Dao类
 * 创建人: wangyawei 
 * 创建时间：2015年7月27日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
public interface WfProductConfigRelationDao extends
		PagingAndSortingRepository<WfProductConfigRelation, Long>,
		JpaSpecificationExecutor<WfProductConfigRelation> {

	/** 
	 * 通过机构ID，贷款产品ID，流程类型和业务类型获取唯一流程与产品配置关系实体
	 * 
	 * @param orgId 机构ID
	 * @param productCd 贷款产品ID
	 * @param workFlowType 流程类型
	 * @param projectBusinessType 业务类型
	 * @return ProductConfig 产品配置实体
	 */
	@Query("from WfProductConfigRelation wpcr where wpcr.orgId = ?1 and productCd = ?2 and workFlowType = ?3 and projectBusinessType = ?4 ")
	public WfProductConfigRelation findUniqueWfProductConfigRelation(Long orgId, Long productCd, String workFlowType, String projectBusinessType);
	
	/** 
	 * 通过机构ID，贷款产品ID，流程类型获取唯一流程与产品配置关系实体
	 * 
	 * @param orgId 机构ID
	 * @param productCd 贷款产品ID
	 * @param workFlowType 流程类型
	 * @param projectBusinessType 业务类型
	 * @return ProductConfig 产品配置实体
	 */
	@Query("from WfProductConfigRelation wpcr where wpcr.orgId = ?1 and productCd = ?2 and workFlowType = ?3 ")
	public WfProductConfigRelation findUniqueWfProductConfigRelation(Long orgId, Long productCd, String workFlowType);
}

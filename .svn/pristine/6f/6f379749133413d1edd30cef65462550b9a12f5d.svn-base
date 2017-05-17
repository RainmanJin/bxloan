package com.coamctech.bxloan.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.WfBusinessRelation;

/**
 * 类名称：WfBusinessRelationDao
 * 类描述 ： 流程与业务关系Dao类
 * 创建人: lp 
 * 创建时间：2015年7月22
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
public interface WfBusinessRelationDao extends PagingAndSortingRepository<WfBusinessRelation, Long>,
		JpaSpecificationExecutor<WfBusinessRelation> {
	
	/** 
	 * 根据流程ID得到流程与业务关系对象
	 * 
	 * @param workFlowId 流程ID
	 * @return WfBusinessRelation 
	 */
	@Query("from WfBusinessRelation wb where wb.workFlowId=?1")
	WfBusinessRelation findWfBusinessRelation(Long workFlowId);
}

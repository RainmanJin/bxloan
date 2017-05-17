package com.coamctech.bxloan.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.coamctech.bxloan.entity.WfActionTreeNode;

/**
 * 类名称：WfActionTreeNodeDao 
 * 类描述 ： 审批流程对应功能页面对应Dao类
 * 创建人: wangyawei 
 * 创建时间：2015年7月20日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
public interface WfActionTreeNodeDao extends PagingAndSortingRepository<WfActionTreeNode, Long>,
		JpaSpecificationExecutor<WfActionTreeNode> {
	
	/** 
	 * 根据treeId获取一组审批流程对应功能页面列表
	 * 
	 * @param treeId 树节点ID
	 * @return List<WfActionTreeNode>  审批流程对应功能页面实体集合
	 */
	@Query("from WfActionTreeNode wa where wa.state = '1' and wa.treeId = ?1")
	List<WfActionTreeNode> findWfActionByTreeId(Long treeId);
	
	/** 
	 * 根据parentNodeId获父审批流程各子环节功能页面列表
	 * 
	 * @param parentNodeId 父级ID
	 * @return List<WfActionTreeNode> 审批流程对应功能页面实体集合
	 */
	@Query("from WfActionTreeNode wa where wa.state = '1' and wa.parentNodeId = ?1")
	List<WfActionTreeNode> findWfActionByParentNodeId(Long parentNodeId);
}

package com.coamctech.bxloan.service.workflow;

import java.util.List;

import com.coamctech.bxloan.entity.WfActionTreeNode;
import com.coamctech.bxloan.entity.WfBusinessRelation;

/**
 * 类名称：WfActionTreeNodeService 
 * 类描述 ： 审批流程对应功能页面对应service接口类
 * 创建人: wangyawei 
 * 创建时间：2015年7月20日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
public interface WfActionTreeNodeService {
	/** 
	 * 根据ID获取一组审批流程对应功能页面实体
	 * 
	 * @param id 主键(流程/环节/功能)ID
	 * @return WfActionTreeNode  审批流程对应功能页面实体集合
	 */
	public WfActionTreeNode findWfActionTreeNodeById(Long id);
	
	/** 
	 * 根据treeId获取一组审批流程对应功能页面列表
	 * 
	 * @param treeId 树节点ID
	 * @return List<WfActionTreeNode>  审批流程对应功能页面实体集合
	 */
	public List<WfActionTreeNode> findWfActionByTreeId(String treeId);
	
	/** 
	 * 根据parentNodeId获父审批流程各子环节功能页面列表
	 * 
	 * @param parentNodeId 父级ID
	 * @return List<WfActionTreeNode> 审批流程对应功能页面实体集合
	 */
	public List<WfActionTreeNode> findWfActionByParentNodeId(String parentNodeId);
	
	/** 
	 * 根据流程ID得到流程与业务关系对象
	 * 
	 * @param workFlowId 流程ID
	 * @return WfBusinessRelation 
	 */
	public WfBusinessRelation findWfBusinessRelation(Long workFlowId);
	
	/** 
	 * 保存流程与业务关系对象
	 * 
	 * @param WfBusinessRelation
	 * @return  
	 */
	public void saveWfBusinessRelation(WfBusinessRelation wfBusinessRelation);
}

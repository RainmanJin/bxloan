package com.coamctech.bxloan.service.workflow.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coamctech.bxloan.dao.WfActionTreeNodeDao;
import com.coamctech.bxloan.dao.WfBusinessRelationDao;
import com.coamctech.bxloan.entity.WfActionTreeNode;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;

/**
 * 类名称：WfActionTreeNodeServiceImpl 
 * 类描述 ： 审批流程对应功能页面对应service实现类
 * 创建人: wangyawei 
 * 创建时间：2015年7月20日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
@Service
public class WfActionTreeNodeServiceImpl implements WfActionTreeNodeService{
	@Autowired
	private WfActionTreeNodeDao wfActionTreeNodeDao;
	@Autowired
	private WfBusinessRelationDao wfBusinessRelationDao;
	@Override
	public WfActionTreeNode findWfActionTreeNodeById(Long id) {
		return wfActionTreeNodeDao.findOne(id);
	}

	@Override
	public List<WfActionTreeNode> findWfActionByTreeId(String treeId) {
		return wfActionTreeNodeDao.findWfActionByTreeId(Long.parseLong(treeId));
	}

	@Override
	public List<WfActionTreeNode> findWfActionByParentNodeId(String parentNodeId) {
		return wfActionTreeNodeDao.findWfActionByParentNodeId(Long.parseLong(parentNodeId));
	}

	@Override
	public WfBusinessRelation findWfBusinessRelation(Long workFlowId) {
		return wfBusinessRelationDao.findWfBusinessRelation(workFlowId);
	}
	@Override
	public void saveWfBusinessRelation(WfBusinessRelation wfBusinessRelation) {
		wfBusinessRelationDao.save(wfBusinessRelation);
	}
}

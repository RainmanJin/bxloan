package com.coamctech.bxloan.service.common;

public interface BizApplyQueryService {
	
	/**
	 * 根据业务id查询业务申请类型（农业或非农业）
	 * @param projId
	 * @return
	 */
	public String findAgroBizTypeByProjId(Long projId);

	/**
	 * 判断所有合同下的抵质押物是否完全释放
	 * 
	 * @author:gph
	 * @createTime:2015年5月21日
	 * @param guaranteeId
	 * @return true：完全释放, false:没有完全释放
	 */
	public boolean checkPawnInfoRelease(Long guaranteeId);
	
	/**
	 * 通过业务ID释放抵质押物（审批过程中撤销流程或拒绝流程使用）
	 * @author:gph
	 * @param projectId 项目Id
	 * @createTime:2015年5月21日
	 * @throws Exception
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月12日 下午5:27:40
	 */
	public void releasePledgeWhenRejectOrCancel(Long projectId) throws Exception;
	
	/**
	 * 通过业务抵质押物中间表ID释放抵质押物（删除抵质押物时使用）
	 * @param projectPawnInfoId 业务抵质押物中间表ID
	 * @throws Exception
	 * 
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月12日 下午5:27:40
	 */
	public void releasePledgeWhenDeletePawnInfo(Long projectPawnInfoId) throws Exception;
}

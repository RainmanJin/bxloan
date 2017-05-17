package com.coamctech.bxloan.service.approval;

import java.math.BigDecimal;
import java.util.List;

import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;

/**
 * 类名称：CreditContractApprovalService
 * 类描述 ： 授信审批流程-批复环节Service接口类
 * 创建人: wangyawei 
 * 创建时间：2015年7月23日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
public interface CreditContractApprovalService {
	/** 
	 * 退回流程
	 * 
	 * @param workFlowCode 流程类型
	 * @param workFlowNode 节点类型
	 * @param workflowId 流程ID
	 * @param taskId 任务ID
	 * @param taskStageCode 节点ID
	 * @param comments	意见
	 * @param curUserLogName 登录用户名
	 * @param curUserName 登录用户姓名
	 */
	public void quitApproval(WorkFlowCode workFlowCode,
			WorkFlowNode workFlowNode,
			String workflowId, 
			String taskId, 
			String comments, 
			String curUserLogName, 
			String curUserName);
	
	/** 
	 * 拒绝流程
	 * 
	 * @param workFlowCode 流程类型
	 * @param workFlowNode 节点类型
	 * @param workflowId 流程ID
	 * @param taskId 任务ID
	 * @param actionCode 操作类型ID
	 * @param taskStageCode 节点ID
	 * @param comments	意见
	 * @param curUserLogName 登录用户名
	 * @param curUserName 登录用户姓名
	 */
	public void dropBackApproval(WorkFlowCode workFlowCode,
			WorkFlowNode workFlowNode,
			String workflowId, 
			String taskId, 
			String actionCode,
			String comments,
			String curUserLogName,
			String curUserName);
	
	/** 
	 * 通过业务ID释放抵质押物（审批过程中撤销流程或拒绝流程使用）
	 * 
	 * @param projectId 业务ID
	 */
	public void handlePledgeWhenRejectOrCancel(Long projectId);
	
	/** 
	 * 通过抵质押物ID判断所有业务是否已解除关联
	 * 
	 * @param guarantyId 抵质押物ID
	 * @return true:已解除关联；false:未解除关联
	 */
	public boolean checkPawnInfoRelease(Long guarantyId);
	
	/**
	 * 通过projectId修改业务状态
	 * 
	 * @param projectId
	 * @param status 要修改的状态
	 */
	public void updateProjectStatusByProjectId(Long projectId, String status);
	
	/**
	 * 通过projectId修改授信合同状态
	 * 
	 * @param projectId
	 * @param contractStatusCd 要修改的状态
	 */
	public void updateCreditContractByProjectId(Long projectId, String contractStatusCd);
	
	/**
	 * 修改文档索引
	 * 
	 * @param workflowId
	 * @param flag
	 */
	public void updateDocumentIndex(Long businessId, boolean flag);
	
	/** 
	 * 根据任务ID获取下一环节（任务少的）操作人
	 * 
	 * @param taskId 任务ID
	 * @param actionCode 操作类型ID
	 * @return
	 */
	public NextTaskReceiver getLeastTaskPerson(String taskId, String actionCode);
	
	/** 
	 * 根据流程ID和节点ID获取操作类型
	 * 
	 * @param workflowId
	 * @param taskStageCode
	 * @return
	 */
	public ActionCode findNextAction(Long workflowId,String taskStageCode);
	
	/** 
	 * 贷款审查  
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitLoanReviewApproval(BizApprovalOfWfParam wfParam);

	/** 
	 * 初审环节（自动分配）
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitBasisCheckApproval(BizApprovalOfWfParam wfParam);

	/** 
	 * 任务分配  
	 *
	 * @param wfParam
	 * @return
	 */
	public String submitOneTaskAssignApproval(BizApprovalOfWfParam wfParam);

	/** 
	 * 一级审批 
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitOneLevelApproval(BizApprovalOfWfParam wfParam);

	/** 
	 * 二级审批岗 
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitTwoLevelApproval(BizApprovalOfWfParam wfParam);
	
	/** 
	 * 制定电子合同
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitSignContract(BizApprovalOfWfParam wfParam);
	

	/** 
	 * 总经理审核 
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitGeMgCheckLoanApproval(BizApprovalOfWfParam wfParam);

	/** 
	 * 签订合同（等同于落实贷款条件）
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitFulfillPayLoanFactApproval(BizApprovalOfWfParam wfParam);
	
	/** 
	 * 初审环节和一级审批环节强制跳转到制定电子合同
	 * 
	 * @param wfParam
	 * @return
	 */
	public String submitToSignContractApproval(BizApprovalOfWfParam wfParam);
	
	/** 
	 * 更新业务批复信息
	 * 
	 * @param projApp 
	 * @param agree 批复结论
	 * @param sessionUser 
	 * @param params 页面参数信息
	 */
	public void updateProjectApplicationApprovalInfo(ProjAppVo projApp, boolean agree, SessionUser sessionUser, List params);
	
	/** 
	 * 根据流程类型和节点类型组装文档内容类型
	 *
	 * @param workFlowCode 流程类型
	 * @param workFlowNode 节点类型
	 * @return List<String> 文档内容类型集合
	 *
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月13日 上午10:52:20
	 */
	public List<String> assembleCustProjectAllDocTypes(WorkFlowCode workFlowCode, WorkFlowNode workFlowNode);
	
	/** 
	 * 根据流程类型和节点类型组装审批过程文档大类
	 *
	 * @param workFlowCode 流程类型
	 * @param workFlowNode 节点类型
	 * @return List<String> 文档类型集合
	 *
	 * @author wangyawei
	 * @lastModified wangyawei 2015年8月13日 上午10:52:20
	 */
	public List<String> assembleApprovalAllDocTypes(WorkFlowCode workFlowCode, WorkFlowNode workFlowNode);
}

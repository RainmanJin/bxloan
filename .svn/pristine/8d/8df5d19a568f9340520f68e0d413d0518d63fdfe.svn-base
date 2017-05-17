package com.coamctech.bxloan.service.approval;

import java.util.List;

import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.entity.ApprovalHistoryBxloan;
import com.coamctech.bxloan.entity.ApprovalOpinion;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.model.ApprovalContentVO;
import com.coamctech.bxloan.service.model.LoanTrialVo;
import com.coamctech.bxloan.service.model.ProcessSuggestionVO;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ElApprInfoVo;
import com.coamctech.bxloan.service.model.approval.ElProjApplVo;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;

public interface ApprovalService {

	/**
	 * 按projectId查询显示VO
	 * @param proId
	 * @return
	 * @author xc
	 */
	ApprovalContentVO findApproValContentByProjectId(Long proId);

	ApprovalContentVO findApproValContentByProjectId_EasyLoan(Long proId);
	
	List<ProcessSuggestionVO> getSuggestion(String workflowId);

	List<DocumentIndex> findDocumentIndexList(Long projectId, String documentType);
	
	/**
	 * 根据projectId查询LoanTrialVo
	 * @param projId	ProjectApplication的Id
	 * @return
	 */
	LoanTrialVo findLoanByProjId(Long projId);
	
	/**
	 * 流程结束修改合同状态
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @return workFlowId
	 * @throws Exception
	 * @author xc
	 */
	@Deprecated
	String endWorkFlowAndUpdateContract(String workflowId, 
										String taskId,
										String comments, 
										String curUserLogname,
										String curUserName, 
										String nextUser) throws Exception;
	/**
	 * 流程结束修改合同状态
	 * @throws Exception
	 * @author xc
	 */
	String endWorkFlowAndUpdateContract(BizApprovalOfWfParam wfParam) throws Exception;
	
	
	/**
	 * 电核网核确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserId
	 * @param curUserName
	 * @return workFlowId
	 * @throws Exception
	 * @author xc
	 */
	String submitInternetAppr(String workflowId, 
							String taskId,
							String comments, 
							String curUserLogname, 
							String nextUser)throws Exception;
	/**
	 * 电核网核确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserId
	 * @param curUserName
	 * @return workFlowId
	 * @throws Exception
	 * @author xc
	 */
	String submitInternetAppr(BizApprovalOfWfParam wfParam)throws Exception;
	
	/**
	 * 审核岗确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserId
	 * @return workFlowId
	 * @throws Exception
	 * @author xc
	 */
	String submitShgAppr(String workflowId, 
						String taskId,
						String comments, 
						String curUserLogname, 
						String nextUser,String nextUserOrgId) throws Exception;
	/**
	 * 稽核岗确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserId
	 * @return workFlowId
	 * @throws Exception
	 * @author xc
	 */
	String submitInspect(String workflowId, 
						String taskId,
						String comments, 
						String curUserLogname, 
						String nextUser) throws Exception;
	/**
	 * 稽核岗确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserId
	 * @return workFlowId
	 * @throws Exception
	 * @author xc
	 */
	String submitInspect(BizApprovalOfWfParam wfParam) throws Exception;
	
	/**
	 * 审核放款确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @param curUserId
	 * @return workFlowId
	 * @throws Exception
	 * @author xc
	 */
	@Deprecated
	String approvalLoan(String workflowId,
					String taskId,
					String comments, 
					String curUserLogname, 
					String nextUser) throws Exception;
	/**
	 * 审核放款确认提交
	 * <p>workflowId 流程id,taskId 任务id,comments 意见,<br/>
	 * curUserLogname 当前用户登录名,nextUser 下一环节执行人,<br/>
	 * nextUserOrgId 下一环节执行人机构编号</p>
	 * @param wfParam
	 * @return
	 * @throws Exception
	 */
	String approvalLoan(BizApprovalOfWfParam wfParam) throws Exception;
	
	/**
	 * 退回申请
	 * @param workflowId
	 * @param taskId
	 * @param taskStageCode
	 * @param comments
	 * @return workFlowId
	 * @author xc
	 * @throws Exception 
	 */
	String sendBackApproval(String workflowId, 
							String curUserLogname,
							String taskId,
							String taskStageCode, 
							String comments,
							String curUserName) throws Exception;
	
	/**
	 * 撤销申请
	 * @param workflowId
	 * @param taskId
	 * @param taskStageCode
	 * @param comments
	 * @return workFlowId
	 * @author xc
	 * @throws Exception 
	 */
	String cancelApproval(String workflowId, 
							String curUserLogname,
							String taskId,
							String taskStageCode, 
							String comments,
							String curUserName,
							String actionCode) throws Exception;
	
	
	ProjectApplication findProjectAppByWorkflowId(Long workFlowId);
	
	
	////////////////////
	/////WD
	////////

	/** 贷款审查 100411 */
	String submitWdscAppr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 贷款审查 100411 */
	String submitWdscAppr(BizApprovalOfWfParam wfParam);

	/** 初审 100412 */
	String submitWdfxzjAppr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 初审 100412 */
	String submitWdfxzjAppr(BizApprovalOfWfParam wfParam);

	/** 资料完整性审核 100413 */
	String submitWdfpgAppr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 资料完整性审核 100413 */
	String submitWdfpgAppr(BizApprovalOfWfParam wfParam);

	/** 按权限分配任务 100414 */
	String submitWdfprwAppr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 按权限分配任务 100414 */
	String submitWdfprwAppr(BizApprovalOfWfParam wfParam);

	/** 贷款审批 100415 */
	String submitWdyjspAppr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 贷款审批 100415 */
	String submitWdyjspAppr(BizApprovalOfWfParam wfParam);

	/** 分配二级审批岗 100416 */
	String submitWdfpg2Appr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 分配二级审批岗 100416 */
	String submitWdfpg2Appr(BizApprovalOfWfParam wfParam);

	/** 二级审批 100417 */
	String submit2jspAppr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 二级审批 100417 */
	String submit2jspAppr(BizApprovalOfWfParam wfParam);

	/** 落实贷款条件 100419 */
	String submitlsfkAppr(String workflowId, String taskId, String comments, String curUserId, String nextUser);
	/** 落实贷款条件 100419 */
	String submitlsfkAppr(BizApprovalOfWfParam wfParam);

	/**
	 * 审核贷款条件 100420
	 * 
	 * @param nextUser2
	 */
	@Deprecated
	String submitshdkAppr(String workflowId, String taskId, String comments, String curUserId, String curUserName, String nextUser);
	/**
	 * 审核贷款条件 100420
	 * 
	 * @param nextUser2
	 */
	String submitshdkAppr(BizApprovalOfWfParam wfParam);
	

	/**
	 * 通过workflowId修改业务状态
	 * @param workflowId
	 * @param status 要修改的状态
	 * */
	void updateProjectStatusByWorkflowId(Long workflowId, String status);

	
	/**
	 * 退回
	 * @param taskStageCode */
	void quitWdApproval(String workflowId, 
						String taskId, 
						String comments,
						String curUserId,
						String taskStageCode,
						String curUserName);
	/**
	 * 撤销
	 * */
	void dropBackApproval(String workflowId, 
							String taskId, 
							String comments,
							String curUserId,
							String taskStageCode,
							String curUserName,
							String actionCode);
	/**
	 * 查询业务信息
	 * @param projectId
	 * @return
	 */
	ProjectApplication findProjectApplication(Long projectId);
	/**
	 * 查询贷款申请信息
	 * @param projectId
	 * @return
	 */
	ProjAppVo findProjAppInfo(Long projectId);
	/**
	 * 更新批复信息
	 * @param projApp
	 * @param agree	true:同意，false：否决
	 * @param user	session中用户信息
	 * @param params 
	 */
	void updateProjAppInfo(ProjAppVo projApp,boolean agree,SessionUser user, List params);
	//Add 2014-11-14
	/**
	 * 查询申请信息（易贷）
	 * @param projectId
	 * @return
	 */
	ElProjApplVo findElProjApplInfo(Long projectId);
	/**
	 * 查询指定环节审批建议
	 * @param projectId
	 * @param node 环节
	 * @return
	 */
	ApprovalOpinion findApprOpinion(Long projectId,WorkFlowNode node);
	/**
	 * 保存审批信息（易贷-电核网核）
	 * @param apprInfo
	 * @return
	 */
	String saveElProjApplOfDhwh(ElApprInfoVo apprInfo);
	/**
	 * 保存审批信息（易贷-审批岗审批）
	 * @param apprInfo
	 * @return
	 */
	String saveElProjApplOfSpg(ElApprInfoVo apprInfo);
	
	public ActionCode findNextAction(Long workflowId,String taskStageCode);
	/**
	 * 根据产品类型查流程类型
	 * @param String productType
	 * @return String workFlowType
	 * */
	String findWorkFlowTypeByProductCd(String productType);
	/**
	 * 检查是否上传文档
	 * @param projectId
	 * @param custDocType
	 * */
	Result checkUploadFiles(Long projectId, String custDocType);
	Result checkUploadFileList(Long projectId, List<String> custDocTypes);
	/**
	 * 查询贷款申请表集合
	 * @param projectId
	 * @param documentType
	 * */
	List<DocumentIndex> findApplyDocuments(Long projectId, String documentType);
	/**
	 * 比较上传从合同文档数量和从合同数量是否一致
	 * @param project ProjectApplication
	 * @return Result
	 * */
	//Result compareSubContractDocNum(ProjectApplication project);
	Result compareSubContractDocNum(Long projectId);
	/**
	 * 查询批复信息 100412以后的
	 * @param projectId
	 * @return
	 */
	ProjAppVo findProjAppInfoApproval(Long projectId);
	/**
	 * 查询历史批复信息
	 * @param List params : projectId, taskStageCode
	 * @return ApprovalHistoryBxloan
	 */
	ApprovalHistoryBxloan findApprovalMsg(List params);

	/**
	 * 按projectId查询显示VO
	 * 批复信息
	 * @param proId
	 * @return
	 * @author xc
	 */
	ApprovalContentVO findFinalApproValContentByProjectId(Long projectId);
	/**
	 * 修改文档索引
	 * @author:gph
	 * @createTime:2015年5月22日
	 * @param workflowId
	 * @param flag
	 */
	void updateDocumentIndex(String workflowId, boolean flag);
	/**
	 * 审核贷款条件 100816
	 * 
	 * @param nextUser2
	 */
	String submitUCshdkAppr(BizApprovalOfWfParam wfParam);
}

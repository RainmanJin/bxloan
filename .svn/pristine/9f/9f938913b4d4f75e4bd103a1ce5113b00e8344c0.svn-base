package com.coamctech.bxloan.web.controller.approval;

import static com.coamctech.bxloan.commons.GlobalConstants.CREDIT_CONTRACT_APPROVAL;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.commons.utils.ServletUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.ApprovalContentVO;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ProductMngService;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.google.common.collect.Lists;

/**
 * 类名称：CreditContractApprovalController 
 * 类描述 ： 授信审批流程Controller
 * 创建人: wangyawei 
 * 创建时间：2015年7月20日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
@Controller
@RequestMapping("/" + CREDIT_CONTRACT_APPROVAL)
public class CreditContractApprovalController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(CreditContractApprovalController.class);
	
	private static final String WORKFLOW_ERR_MSG = "提交时发生异常,请联系系统管理员";
	
	@Autowired
	private BusinessApplicationService businessApplicationService;

	@Autowired
	UniqueCustomerService uniqueCustomerService;
	
	@Autowired 
	private WorkFlowService workFlowService;
	
	@Autowired
	private ApprovalService approvalService;
	
	@Autowired
	private WfActionTreeNodeService wfActionTreeNodeService;
	
	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private CreditContractApprovalService creditContractApprovalService;
	
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private ProductMngService productMngService;
	
	/**
	 * 跳转到授信审批流程批复页面
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @param projectId 业务ID
	 * @param partyType 客户类型
	 * @param productTypeCd 贷款产品代码
	 * @return String 跳转路径
	 */
	@RequestMapping("/toApproval/{wfCode}/{workflowId}/{taskId}/{taskStageCode}")
	public String toApproval(@PathVariable("wfCode") String wfCode, @PathVariable("workflowId") String workflowId,
				@PathVariable("taskId") String taskId, @PathVariable("taskStageCode") String taskStageCode, Model model) {
		//获取流程与业务关系对象
		WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
		//组装批复页面反显信息
		ApprovalContentVO approvalContent = null;
		//总经理审核合同环节和签订合同环节，反显项目信息为批复项目信息；其他环节反显项目信息为录入业务环节的项目信息
		if(WorkFlowNode.CA_GeneralManagerCheckLoan.equals(workFlowNode) || 
				WorkFlowNode.CA_FulfillPayLoanFactor.equals(workFlowNode)){
			approvalContent = approvalService.findFinalApproValContentByProjectId(businessId);
		} else{
			approvalContent = approvalService.findApproValContentByProjectId(businessId);
		}
		//拼接担保方式，使用逗号隔开
		String[] guarantees = approvalContent.getGuaranteeMode().split(",");
		if(guarantees != null && guarantees.length > 0){
			String guaranteeStr = "";
			for (String value : guarantees) {
				guaranteeStr += "," + dataDict.getCodeName("CdsGuarantMode", value);
			}
			approvalContent.setGuaranteeMode(guaranteeStr.substring(1, guaranteeStr.length()));
		}
		//封装贷款利率值
		if (StringUtils.isNotBlank(approvalContent.getBizRate())) {
			String bizRate = (MoneyUtil.formatMoney(new BigDecimal(approvalContent.getBizRate()).multiply(new BigDecimal("100"))));
			approvalContent.setBizRate(bizRate);
		}
		//查找产品配置信息
		ProjectApplication pa = businessApplicationService.searchProjectApplication(Long.valueOf(businessId));
		ProductConfig productConfig = productMngService.findUniqueProductConfig(
						Long.valueOf(pa.getApplyOrg()), 
						Long.valueOf(pa.getProductType()),
						wfCode);
		approvalContent.setCreditType(pa.getCreditType());
		Party party = uniqueCustomerService.findPartyByPartyId(pa.getPartyId().toString());
		//组装批复信息
		ProjAppVo projAppVo = null;
		//贷款审查环节，反显批复信息为录入业务环节的项目信息；其他环节，为上一环节批复信息
		if(WorkFlowNode.CA_LoanReview.equals(workFlowNode)){
			projAppVo = approvalService.findProjAppInfo(businessId);
		} else{
			projAppVo = approvalService.findProjAppInfoApproval(businessId);
		}
		if(projAppVo != null){
			projAppVo.setApproveDate(new Date());
			model.addAttribute("projApp", projAppVo);
		}
		ActionCode code = creditContractApprovalService.findNextAction(Long.parseLong(workflowId), taskStageCode);
		boolean showContract = Boolean.FALSE;
		
		//一级审批环节和初审环节提交到制定电子合同环节
		if(WorkFlowNode.CA_OneLevelApproval.equals(workFlowNode) || WorkFlowNode.CA_BasisCheck.equals(workFlowNode)){
			showContract = ActionCode.CA_SUBMIT_TO_CONTACT.equals(code);
		}
		//存储相关参数
		model.addAttribute("wfCode", wfCode);
		model.addAttribute("workflowId", workflowId);
		model.addAttribute("taskId", taskId);
		model.addAttribute("taskStageCode", taskStageCode);
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		model.addAttribute("partyType", party.getPartyTypeCd());
		model.addAttribute("projectId", businessId);
		model.addAttribute("productTypeCd", pa.getProductType());
		model.addAttribute("productConfig", productConfig);
		model.addAttribute("showContract", showContract);
		model.addAttribute("approvalContent", approvalContent);
		return GlobalConstants.CREDIT_CONTRACT_APPROVAL_WL + "/main";
	}
	
	/**
	 * 提交下一环节
	 * 
	 * @param wfCode 流程类型
	 * @param workflowId 流程ID
	 * @param taskId 任务ID
	 * @param taskStageCode 节点ID
	 * @param comments 意见
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "submitApproval", method = RequestMethod.POST)
	public Result submitApproval(
			@RequestParam("wfCode") String wfCode,
			@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode") String taskStageCode,
			@RequestParam("comments") String comments,
			@RequestParam(value = "nextUser", required = false) String nextUser,
			@RequestParam(value = "nextUserOrgId", required = false) String nextUserOrgId,
			HttpServletRequest request) {
		try {
			//保存批复信息
			request.setAttribute("workflowId", workflowId);
			request.setAttribute("taskId", taskId);
			request.setAttribute("taskStageCode", taskStageCode);
			this.saveReplyInfo(request, true);
			
			//提交到下一环节
			WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
			WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
			//组装参数信息
			BizApprovalOfWfParam bizApprovalOfWfParam = BizApprovalOfWfParam.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId, workFlowCode, workFlowNode);
			switch(workFlowNode) {
				//贷款审查
				case CA_LoanReview: {
					creditContractApprovalService.submitLoanReviewApproval(bizApprovalOfWfParam);
					 break;
				}
				//初审环节-自动分配
				case CA_BasisCheck: {
					this.submitBasisCheckApproval(workflowId, taskId, comments, nextUser, nextUserOrgId, bizApprovalOfWfParam);
					break;
				}
				//任务分配
				case CA_OneTaskAssign: {
					 creditContractApprovalService.submitOneTaskAssignApproval(bizApprovalOfWfParam);
					 break;
				}
				//一级审批
				case CA_OneLevelApproval: {
					creditContractApprovalService.submitOneLevelApproval(bizApprovalOfWfParam);
					break;
				}
				//二级审批-自动分配
				case CA_TwoLevelApproval: {
					creditContractApprovalService.submitTwoLevelApproval(bizApprovalOfWfParam);
					break;
				}
				//总经理审核合同
				case CA_GeneralManagerCheckLoan: {
					creditContractApprovalService.submitGeMgCheckLoanApproval(bizApprovalOfWfParam);
					break;
				}
				//签订合同（等同于落实贷款条件）
				case CA_FulfillPayLoanFactor: {
					creditContractApprovalService.submitFulfillPayLoanFactApproval(bizApprovalOfWfParam);
					break;
				}
				default: {
					return failure(WORKFLOW_ERR_MSG, null);
				}
			}
			return success();
		} 
		catch (Exception e) {
			e.printStackTrace();
			logger.error(WORKFLOW_ERR_MSG + ",error:{}", e);
			return failure(WORKFLOW_ERR_MSG, null);
		}
	}
	
	/** 
	 * 初审环节和一级审批环节强制跳转到制定电子合同
	 * 
	 * @param wfCode 流程类型
	 * @param workflowId 流程ID
	 * @param taskId 任务ID
	 * @param taskStageCode 节点ID
	 * @param comments 意见
	 * @return
	 */
	@RequestMapping("/submitToContractApproval")
	@ResponseBody
	public Result submitToContractApproval(@RequestParam String wfCode,
			@RequestParam String workflowId, @RequestParam String taskId,
			@RequestParam String taskStageCode, @RequestParam String comments,
			HttpServletRequest request) {
		try {
				//保存批复信息
				request.setAttribute("workflowId", workflowId);
				request.setAttribute("taskId", taskId);
				request.setAttribute("taskStageCode", taskStageCode);
				this.saveReplyInfo(request, true);
				
				//提交到下一环节
				WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
				WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
				
				//组装参数
				BizApprovalOfWfParam bizApprovalOfWfParam = BizApprovalOfWfParam.newBizApprovalOfWfParam(workflowId, taskId, comments,
						this.curUserLogName(), this.curUserName(),
						StringUtils.EMPTY, StringUtils.EMPTY, workFlowCode, workFlowNode);
				
				//初审环节和一级审批环节强制跳转到制定电子合同
				creditContractApprovalService.submitToSignContractApproval(bizApprovalOfWfParam);
				return success();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(WORKFLOW_ERR_MSG + ",error:{}", e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 
	 * 初审
	 */
	private void submitBasisCheckApproval(String workflowId, String taskId,
			String comments, String nextUser, String nextUserOrgId,
			BizApprovalOfWfParam bizApprovalOfWfParam) {
		ActionCode actionCode = creditContractApprovalService.findNextAction(Long.parseLong(workflowId), WorkFlowNode.CA_BasisCheck.getId());
		boolean showContract = ActionCode.CA_SUBMIT_TO_CONTACT.equals(actionCode);
		//若下一环节不是"制定电子合同"，则自动分配任务给待办任务较少的任务分配岗
		if(!showContract){
			NextTaskReceiver nextTaskReceiver = creditContractApprovalService.getLeastTaskPerson(taskId, actionCode.getCodeId());
			nextUser = nextTaskReceiver.getLogName();
			nextUserOrgId = nextTaskReceiver.getOrgId();
			bizApprovalOfWfParam.setNextUser(nextUser);
			bizApprovalOfWfParam.setNextUserOrgId(nextUserOrgId);
		}
		creditContractApprovalService.submitBasisCheckApproval(bizApprovalOfWfParam);
	}
	
	/**
	 * 保存批复信息
	 * 
	 * @param request
	 * @param agree	true：同意，false：否决
	 */
	private void saveReplyInfo(HttpServletRequest request, boolean agree){
		Map<String, String> map = ServletUtils.getParameterValueMap(request);
		ProjAppVo projApp = new ProjAppVo(map);
		List params = Lists.newArrayList();
		params.add(request.getAttribute("workflowId"));
		params.add(request.getAttribute("taskId"));
		params.add(request.getAttribute("taskStageCode"));
		if(projApp.getApproveLevel() > 0){
			ShiroUser curUser=(ShiroUser)SecurityUtils.getSubject().getPrincipal();
			SessionUser sessionUser = new SessionUser(curUser.getId(), curUser.getLogOrgid(), null);
			sessionUser.setLoginName(curUser.getLogname());
			sessionUser.setUserNameCN(curUser.getName());
			creditContractApprovalService.updateProjectApplicationApprovalInfo(projApp, agree, sessionUser, params);
		} 
	}
	
	/**
	 * 退回
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @param comments 意见
	 * @return
	 */
	@RequestMapping(value = "/quitApproval", method = RequestMethod.POST)
	@ResponseBody
	public Result quitApproval(@RequestParam("wfCode") String wfCode,
			@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode") String taskStageCode,
			@RequestParam("comments") String comments) {
		try {
			//退回流程
			creditContractApprovalService.quitApproval(
					WorkFlowCode.getById(wfCode),
					WorkFlowNode.getNodeById(taskStageCode), workflowId,
					taskId, comments, curUserLogName(),
					curUserName());
			return this.success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG + ",error:{}", e);
			return failure(WORKFLOW_ERR_MSG, null);
		}
	}
	
	/**
	 * 撤销
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @param comments 意见
	 * @return
	 */
	@RequestMapping("/dropBack")
	@ResponseBody
	public Result dropBack(@RequestParam("wfCode") String wfCode,
			@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode") String taskStageCode,
			@RequestParam("comments") String comments) {
		try {
			creditContractApprovalService.dropBackApproval(
					WorkFlowCode.getById(wfCode),
					WorkFlowNode.getNodeById(taskStageCode), workflowId,
					taskId, null, comments, curUserLogName(), curUserName());
			return this.success();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(WORKFLOW_ERR_MSG + ",error:{}", e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}
	
	/**
	 * 检查上传文档数量
	 * 
	 * @param wfCode 流程类型
	 * @param taskStageCode 节点ID
	 * @param projectId 业务ID
	 * @return 
	 */
	@RequestMapping("/checkUploadFiles")
	@ResponseBody
	public Result checkUploadFiles(@RequestParam("wfCode") String wfCode,
			@RequestParam String taskStageCode, @RequestParam Long projectId) {
		//流程类型
		WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
		//节点类型
		WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
		//根据流程类型和节点类型组装文档内容类型
		List<String> custDocTypes = creditContractApprovalService.assembleCustProjectAllDocTypes(workFlowCode, workFlowNode);
		Result chief =  approvalService.checkUploadFileList(projectId, custDocTypes);
		if(chief.getSuccess()){
			Result sub = approvalService.compareSubContractDocNum(projectId);
			return sub;
		}
		return chief;
	}
	
	/**
	 * 获取上传文档种类
	 * 
	 * @param wfCode 流程类型
	 * @param taskStageCode 节点类型
	 * @param projectId 业务ID
	 * @return 
	 */
	@RequestMapping("/findUploadCustDocTypes")
	@ResponseBody
	public CustDocInfo findUploadCustDocTypes(@RequestParam("wfCode") String wfCode, @RequestParam String taskStageCode, @RequestParam Long projectId) {
		//流程类型
		WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
		//节点类型
		WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
		//根据流程类型和节点类型组装审批过程文档大类
		List<String> allDocTypes = creditContractApprovalService.assembleApprovalAllDocTypes(workFlowCode, workFlowNode);
		String allDocType = "";
		for(String str : allDocTypes){
			allDocType = str;
		}
		return documentService.findUploadCustDocTypes(null, null, allDocType);
	}

	/**
	 * 根据信息获取已上传文档的类型和数量
	 * 
	 * @param wfCode 流程类型
	 * @param taskStageCode 节点类型
	 * @param projectId 业务ID
	 * @return List<String>
	 */
	@RequestMapping("/findDocumentCustDocTypes")
	@ResponseBody
	public List<Object[]> findDocumentCustDocTypes(@RequestParam("wfCode") String wfCode, @RequestParam String taskStageCode, @RequestParam Long projectId) {
		//流程类型
		WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
		//节点类型
		WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
		//根据流程类型和节点类型组装审批过程文档大类
		List<String> allDocTypes = creditContractApprovalService.assembleApprovalAllDocTypes(workFlowCode, workFlowNode);
		String allDocType = "";
		for(String str : allDocTypes){
			allDocType = str;
		}
		return documentService.findDocumentCustDocTypes(projectId, allDocType);
	}

	/**
	 * 获取当前登录人登录名 
	 */
	private String curUserLogName(){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getLogname();
	}
	
	/** 获取当前用户名字 */
	private String curUserName() {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getName();
	}
}

package com.coamctech.bxloan.web.controller.approval;

import static com.coamctech.bxloan.commons.GlobalConstants.APPROVAL;
import static com.coamctech.bxloan.commons.GlobalConstants.UNDER_CREDIT_LOAN_APPROVAL;
import static com.coamctech.bxloan.commons.GlobalConstants.BUSINESS_APPLICATION;
import static com.coamctech.bxloan.commons.GlobalConstants.CONTRACT;
import static com.coamctech.bxloan.commons.GlobalConstants.WDAPPROVAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.commons.utils.ServletUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.ApprovalHistoryBxloan;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.service.approval.ApprovalApplyInfoService;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.ApprovalContentVO;
import com.coamctech.bxloan.service.model.LoanTrialVo;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoTableVO;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ElApprInfoVo;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;
import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TaskTransferProcess;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
/**
 * 授信借款审批流程
 * @author lp
 *
 */
@Controller
@RequestMapping("/" + UNDER_CREDIT_LOAN_APPROVAL)
public class UnderCreditLoanApprovalController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(UnderCreditLoanApprovalController.class);
	private static final String GUARANTEE_MODE = "CdsGuarantMode";
	private static final String WORKFLOW_ERR_MSG = "提交时发生异常,请稍后重试";
	
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private ApprovalService approvalService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private BusinessApplicationService busineApplicationServices;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private ApprovalService wdApprovalService;
	@Autowired
	private ApprovalApplyInfoService appInfoService;
	@Autowired
	private WfActionTreeNodeService wfActionTreeNodeService;
	@Autowired
	private CreditContractApprovalService creditContractApprovalService;
	/**
	 * 跳转到授信下借款审批流程批复页面
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @return String 跳转路径
	 */
	@RequestMapping("/toApproval/{wfCode}/{workflowId}/{taskId}/{taskStageCode}")
	public String toApproval(@PathVariable("wfCode") String wfCode, @PathVariable("workflowId") String workflowId,
				@PathVariable("taskId") String taskId, @PathVariable("taskStageCode") String taskStageCode, Model model) {
			//组装批复页面反显信息
			ApprovalContentVO approvalContent = null;
			//组装批复信息
			ProjAppVo projAppVo= null;
			//获取流程与业务关系对象
			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
			Long businessId = wfBusinessRelation.getBusinessId();
			WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
		//总经理审核合同环节和签订合同环节，反显项目信息为批复项目信息；其他环节反显项目信息为录入业务环节的项目信息
		if(WorkFlowNode.LA_GeneralManagerCheckLoan.equals(workFlowNode) || 
				WorkFlowNode.LA_FulfillPayLoanFactor.equals(workFlowNode) || 
				WorkFlowNode.LA_CheckLoanFactor.equals(workFlowNode)){
			approvalContent = approvalService.findFinalApproValContentByProjectId(businessId);
		} else{
			approvalContent = approvalService.findApproValContentByProjectId(businessId);
		}
			//拼接担保方式，使用逗号隔开
			String[] guarantee = approvalContent.getGuaranteeMode().split(",");
			String gm = "";
		for (String _str : guarantee) {
			gm += ","+dataDict.getCodeName(GUARANTEE_MODE, _str);
		}
			approvalContent.setGuaranteeMode(gm.substring(1,gm.length()));
		//封装贷款利率值
		if (StringUtils.isNotBlank(approvalContent.getBizRate())) {
			String bizRate = (MoneyUtil.formatMoney(new BigDecimal(approvalContent.getBizRate()).multiply(new BigDecimal("100"))));
			approvalContent.setBizRate(bizRate);
		}
			//查找产品配置信息
			ProjectApplication pa = businessApplicationService.searchProjectApplication(Long.valueOf(businessId));
			ProductConfig productConfig = businessApplicationService.getProductConfigByProductCd(Long.valueOf(pa.getProductType()), Long.valueOf(pa.getApplyOrg()));
			approvalContent.setCreditType(pa.getCreditType());
			Party party = uniqueCustomerService.findPartyByPartyId(pa.getPartyId().toString());
		//贷款审查环节，反显批复信息为录入业务环节的项目信息；其他环节，为上一环节批复信息
		if(WorkFlowNode.LA_LoanReview.equals(workFlowNode)){
			projAppVo = wdApprovalService.findProjAppInfo(businessId);
		}else{
			projAppVo = wdApprovalService.findProjAppInfoApproval(businessId);
		}
		if(projAppVo!=null){
			projAppVo.setApproveDate(new Date());
			model.addAttribute("projApp", projAppVo);
		}
			ActionCode code = wdApprovalService.findNextAction(Long.parseLong(workflowId), taskStageCode);
			boolean showContract = Boolean.FALSE;
		//初审环节提交到签订电子合同环节
		if(WorkFlowNode.LA_BasisCheck.equals(WorkFlowNode.getNodeById(taskStageCode))){
			showContract = ActionCode.SUBMIT_CONTACTB.equals(code);
		}
			model.addAttribute("wfCode", wfCode);
			model.addAttribute("workflowId", workflowId);
			model.addAttribute("taskId", taskId);
			model.addAttribute("taskStageCode", taskStageCode);
			model.addAttribute("projectId", businessId);
			model.addAttribute("partyType", party.getPartyTypeCd());
			model.addAttribute("productTypeCd", pa.getProductType());
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("productConfig", productConfig);
			model.addAttribute("showContract",showContract);
			model.addAttribute("approvalContent", approvalContent);
		return UNDER_CREDIT_LOAN_APPROVAL + "/main";
	}
	/**
	 * 提交下一环节
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
			
			WorkFlowCode workFlowCode = WorkFlowCode.getById(wfCode);
			WorkFlowNode workFlowNode = WorkFlowNode.getNodeById(taskStageCode);
			//组装参数信息
			BizApprovalOfWfParam bizApprovalOfWfParam = BizApprovalOfWfParam.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId, workFlowCode, workFlowNode);
		switch (workFlowNode) {
		case LA_LoanReview: {// 贷款审查
			 creditContractApprovalService.submitLoanReviewApproval(bizApprovalOfWfParam);
			break;
		}
		case LA_BasisCheck: {// 初审 
			this.submitBasisCheckApproval(workflowId, taskId, comments, nextUser, nextUserOrgId, bizApprovalOfWfParam);
			break;
		}
		case LA_GeneralManagerCheckLoan: {// 总经理审核合同
			 creditContractApprovalService.submitGeMgCheckLoanApproval(bizApprovalOfWfParam);
			break;
		}
		case LA_FulfillPayLoanFactor: {// 落实贷款条件
			 creditContractApprovalService.submitFulfillPayLoanFactApproval(bizApprovalOfWfParam);
			break;
		}
		case LA_CheckLoanFactor: {// 审核贷款条件
			this.submitShdkAppr(bizApprovalOfWfParam);
			break;
		}
		default: {
			return failure(WORKFLOW_ERR_MSG, null);
		}
		}
		return success();
	}catch (Exception e) {
		e.printStackTrace();
		logger.error(WORKFLOW_ERR_MSG + "error:{}", e);
		return failure(WORKFLOW_ERR_MSG, null);
		}
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
	 * 初审
	 */
	private void submitBasisCheckApproval(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId, BizApprovalOfWfParam bizApprovalOfWfParam) {
		ActionCode code = creditContractApprovalService.findNextAction(Long.parseLong(workflowId), WorkFlowNode.LA_BasisCheck.getId());
		boolean showContract = ActionCode.CA_SUBMIT_TO_CONTACT.equals(code);
		creditContractApprovalService.submitBasisCheckApproval(bizApprovalOfWfParam);
	}
	private NextTaskReceiver getLeastTaskPerson(String taskId){
		TypedResult<List<NextTaskReceiver>> resultList = 
				this.workFlowService.getNextTaskReceivers(taskId,ActionCode.COMMIT);
		NextTaskReceiver receiver = null;
		for(NextTaskReceiver rec : resultList.getData()){
			if(receiver==null
				||Integer.parseInt(receiver.getTotal())>Integer.parseInt(rec.getTotal())){
				receiver = rec;
			}
		}
		return receiver;
	}
	/** 审核贷款条件 100816 */
	private Result submitShdkAppr(BizApprovalOfWfParam bizApprovalOfWfParam) {
		
		try {
			this.wdApprovalService.submitUCshdkAppr(bizApprovalOfWfParam);
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
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
			logger.error(WORKFLOW_ERR_MSG, e);
			return failure(WORKFLOW_ERR_MSG, null);
		}
	}
	/**
	 * 查询批复信息
	 * @param taskStageCode
	 * @param projectId
	 * @return
	 * @author lbq  2015-8-11
	 */
	@RequestMapping("/findApprovalMsg")
	@ResponseBody
	public ProjAppVo findApprovalMsg(@RequestParam String taskStageCode, @RequestParam Long projectId){
		ProjAppVo proApp  = null;
			List params = ImmutableList.of(projectId, taskStageCode);
			ApprovalHistoryBxloan ahb = wdApprovalService.findApprovalMsg(params);
		if(ahb!=null){
			proApp = new ProjAppVo(ahb);
			proApp.setId(ahb.getId());
		}
		return proApp;
	}
	private String curUserLogName(){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getLogname();
	}
	private Long curUserId(){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getId();
	}
	private String curUserName() {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getName();
	}
	private String subTaskId(String origin){
		return origin.substring(origin.lastIndexOf("-")+1,origin.length());
	}
	
}

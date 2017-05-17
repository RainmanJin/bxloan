package com.coamctech.bxloan.web.controller.wdapproval;

import static com.coamctech.bxloan.commons.GlobalConstants.BUSINESS_APPLICATION;
import static com.coamctech.bxloan.commons.GlobalConstants.CONTRACT;
import static com.coamctech.bxloan.commons.GlobalConstants.WDAPPROVAL;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_10;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_11;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_12;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_13;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_14;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_15;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_16;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_17;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_18;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_19;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_WF_TASK_ID_20;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.commons.utils.ServletUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ApprovalHistoryBxloan;
import com.coamctech.bxloan.entity.ApprovalPhoneDetail;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.approval.ApprovalPhoneDetailService;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.ApprovalContentVO;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ProjAppVo;
import com.coamctech.bxloan.service.model.workflow.ExeTaskParam;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TaskAction;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 微贷流程控制器
 * 
 * @author lijing
 * @lastModified lijing 2014-08-13 17:30:24
 */
@Controller
@RequestMapping("/" + WDAPPROVAL)
public class WdApprovalController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(WdApprovalController.class);
	private static final String GUARANTEE_MODE = "CdsGuarantMode";
	private static final String WORKFLOW_ERR_MSG = "提交时发生异常,请稍后重试";
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private ApprovalService wdApprovalService;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private ApprovalPhoneDetailService approvalPhoneDetailService;
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	@Autowired
	private ApprovalService approvalService;
	/*
	 * 录入业务申请信息 100410 - 客户经理 贷款审查 100411 审查岗 初审 100412 -小贷公司风险总监 资料完整性审核 100413
	 * -分配岗 按权限分配任务 100414 -分配岗 贷款审批 100415 -一级审批岗 分配二级审批岗 100416 -分配岗 二级审批
	 * 100417 -二级审批岗 签订合同 100418 -客户经理 落实贷款条件 100419 -信贷综合岗 审核贷款条件 100420 -财务综合岗
	 */
	@SuppressWarnings("serial")
	private Map<String, String> stageCodePath = new HashMap<String, String>() {
		{
			// 新流程
			 put(WD_WF_TASK_ID_10, "/" + BUSINESS_APPLICATION + "/editApplication/");//录入业务申请信息 
			 put(WD_WF_TASK_ID_11, "/" + WDAPPROVAL + "/toApproval/");//贷款审查 
			 put(WD_WF_TASK_ID_12, "/" + WDAPPROVAL + "/toApproval/");//初审 
			 put(WD_WF_TASK_ID_13, "/" + WDAPPROVAL + "/toApproval/");//任务分配 
			 put(WD_WF_TASK_ID_14, "/" + WDAPPROVAL + "/toApproval/");//一级审批
			 put(WD_WF_TASK_ID_15, "/" + WDAPPROVAL + "/toApproval/");//任务分配 
			 put(WD_WF_TASK_ID_16, "/" + WDAPPROVAL + "/toApproval/");//二级审批
			 put(WD_WF_TASK_ID_17, "/" + CONTRACT + "/signToForm/");//签订合同 
			 put(WD_WF_TASK_ID_18, "/" + WDAPPROVAL + "/toApproval/");//总经理审核合同 
			 put(WD_WF_TASK_ID_19, "/" + WDAPPROVAL + "/toApproval/");//落实贷款条件 
			 put(WD_WF_TASK_ID_20, "/" + WDAPPROVAL + "/toApproval/");//审核贷款条件
			 }
	};

	
	
	@RequestMapping
	public String index() {
		return WDAPPROVAL + "/main";
	}

	/**
	 * 根据流程当前状态判断跳转地址
	 * 
	 * @param projectId
	 * @param workflowId
	 * @param taskId
	 * @param model
	 * @return 下一步跳转地址
	 * @author xc
	 */
	@RequestMapping("/todoOperation/{workflowId}/{taskId}/{taskStageCode}/{taskStatus}")
	public String todoOperation(@PathVariable("workflowId") String workflowId, 
			                    @PathVariable("taskId") String taskId, 
			                    @PathVariable("taskStageCode") String taskStageCode,
			                    @PathVariable("taskStatus") String taskStatus,
			                    Model model) {
		
		taskId = taskId.substring(taskId.indexOf("-")+1);
		StringBuilder nextPath = new StringBuilder("redirect:");
		nextPath.append(stageCodePath.get(taskStageCode));
		ProjectApplication project = wdApprovalService.findProjectAppByWorkflowId(Long.parseLong(workflowId));
		if(project == null){
			model.addAttribute("error", "数据错误，流程中并不存在该业务信息，请联系管理员。");
			return GlobalConstants.DASHBOARD + "/main";
		}
		String workFlowType = WorkFlowCode.MICRO_LOAN.getCodeId();
		Party party = uniqueCustomerService.findPartyByPartyId(project.getPartyId()+"");
		if(taskStatus.equals("80")){
			workFlowService.startProcessingTask(workflowId, taskId, curUserLogName());
		}
		
		if(taskStageCode.equals(WD_WF_TASK_ID_17)){//签合同
			model.addAttribute("projectId", project.getProjectId());
			model.addAttribute("workflowId", workflowId);
			model.addAttribute("taskId", taskId);
			model.addAttribute("taskStageCode", taskStageCode);
			model.addAttribute("workFlowType",workFlowType);
			model.addAttribute("partyType", party.getPartyTypeCd());
			model.addAttribute("productTypeCd", project.getProductType());
			
			
		}else{
			nextPath.append(project.getProjectId()).append("/")
					.append(workflowId).append("/")
					.append(taskId);
			if(!taskStageCode.equals(WD_WF_TASK_ID_10)){
				nextPath.append("/").append(taskStageCode);
				nextPath.append("/").append(party.getPartyTypeCd());
				nextPath.append("/").append(project.getProductType());
			}
		}
		
		return  nextPath.toString();
	}

	/**
	 * 跳转到流程审批页面
	 * */
	@RequestMapping("/toApproval/{projectId}/{workflowId}/{taskId}/{taskStageCode}/{partyType}/{productTypeCd}")
	public String toApproval(@PathVariable("projectId") String projectId, 
			                 @PathVariable("workflowId") String workflowId, 
			                 @PathVariable("taskId") String taskId, 
			                 @PathVariable("taskStageCode") String taskStageCode, 
			                 @PathVariable("partyType") String partyType, 
			                 @PathVariable("productTypeCd") String productTypeCd, 
			                 Model model) {
		ApprovalContentVO approvalContent = null;
		if(Long.valueOf(taskStageCode).compareTo(new Long(100417))<0){
			approvalContent = this.wdApprovalService.findApproValContentByProjectId(Long.parseLong(projectId));
		}else{
			approvalContent = this.wdApprovalService.findFinalApproValContentByProjectId(Long.parseLong(projectId));
		}
		String[] guarantee = approvalContent.getGuaranteeMode().split(",");
		String gm = "";
		for (String _str : guarantee) {
			gm += ","+dataDict.getCodeName(GUARANTEE_MODE, _str);
		}
		approvalContent.setGuaranteeMode(gm.substring(1,gm.length()));
		
		if (StringUtils.isNotBlank(approvalContent.getBizRate())) {
			String bizRate = (MoneyUtil.formatMoney(new BigDecimal(approvalContent.getBizRate()).multiply(new BigDecimal("100"))));
			approvalContent.setBizRate(bizRate);
		}
		model.addAttribute("workflowId", workflowId);
		model.addAttribute("projectId", projectId);
		model.addAttribute("taskId", taskId);
		model.addAttribute("taskStageCode", taskStageCode);
		model.addAttribute("approvalContent", approvalContent);
		model.addAttribute("partyType", partyType);
		model.addAttribute("productTypeCd", productTypeCd);
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		
		//贷款审批查找产品配置期限单位 gph modify2015/04/30 
		ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(Long.valueOf(projectId));
		
		ProductConfig productConfig = businessApplicationService
				.getProductConfigByProductCd(Long.valueOf(projectApplication.getProductType()),
						Long.valueOf(projectApplication.getApplyOrg()));
		model.addAttribute("productConfig", productConfig);
		//add by wangyawei 20150701 start 增加邦农贷产品标识，若贷款产品为邦农贷，返回true；否则，返回false
		boolean agriculturalLoan = businessApplicationService.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, projectApplication.getProductType());
		model.addAttribute("agriculturalLoan", agriculturalLoan);
		//add by wangyawei 20150701 end
		
		//批复查询
		ProjAppVo projAppVo= null;
		if(WorkFlowNode.ML_LoanReview.equals(WorkFlowNode.getNodeById(taskStageCode))){
			projAppVo = wdApprovalService.findProjAppInfo(Long.parseLong(projectId));
		}else{
			projAppVo = wdApprovalService.findProjAppInfoApproval(Long.parseLong(projectId));
		}
		if(projAppVo!=null){
			projAppVo.setApproveDate(new Date());
			model.addAttribute("projApp", projAppVo);
		}
		boolean showContract = Boolean.FALSE;
		
		if(WorkFlowNode.ML_OneLevelApproval.equals(WorkFlowNode.getNodeById(taskStageCode))){
			ActionCode code = this.wdApprovalService.findNextAction(Long.parseLong(workflowId), taskStageCode);
			showContract = ActionCode.SUBMIT_CONTACTA.equals(code);
		}
		
		if(WorkFlowNode.ML_BasisCheck.equals(WorkFlowNode.getNodeById(taskStageCode))){
			ActionCode code = this.wdApprovalService.findNextAction(Long.parseLong(workflowId), taskStageCode);
			showContract = ActionCode.SUBMIT_CONTACTB.equals(code);
		}
		
		model.addAttribute("showContract",showContract);
		
		//根据产品代码判断是不是团结贷
		boolean unityLoanProductMark = businessApplicationService.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, projectApplication.getProductType());
		model.addAttribute("unityLoanProductMark", unityLoanProductMark);
		return WDAPPROVAL + "/main";
	}

	/**
	 * 退回
	 * 
	 * @return
	 * @author xc
	 */
	@RequestMapping(value = "/quitApproval", method = RequestMethod.POST)
	@ResponseBody
	public Result quitApproval(@RequestParam("workflowId") String workflowId, @RequestParam("taskId") String taskId, @RequestParam("taskStageCode") String taskStageCode, @RequestParam("comments") String comments) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.quitWdApproval(workflowId, 
												taskId, 
												comments,
												curUserLogName(), 
												taskStageCode, 
												curUserName());
			return this.success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return failure(WORKFLOW_ERR_MSG, null);
		}
	}

	

	/** 撤销 */
	@RequestMapping("/dropBack")
	@ResponseBody
	public Result dropBack(@RequestParam String workflowId, @RequestParam String taskId, @RequestParam String taskTypeId, @RequestParam String comments) {
		String userLogName = this.curUserLogName();
		taskId = this.subTaskId(taskId);
		String curUserName = this.curUserName();
		try {
			this.wdApprovalService.dropBackApproval(workflowId, 
												taskId, 
												comments, 
												userLogName, 
												taskTypeId, 
												curUserName,
												null);
			return this.success();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}
	
	/**
	 * 确认通过
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping(value = "submitApproval", method = RequestMethod.POST)
	public Result submitApproval(@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode") String taskStageCode,
			@RequestParam("comments") String comments,
			@RequestParam(value = "nextUser", required = false) String nextUser,
			@RequestParam(value = "nextUserOrgId", required = false) String nextUserOrgId,
			HttpServletRequest request) {
		Integer taskcode = Integer.parseInt(taskStageCode);
		// 批复
		try {
			request.setAttribute("workflowId", workflowId);
			request.setAttribute("taskId", taskId);
			request.setAttribute("taskStageCode", taskStageCode);
			
			
			this.saveReplyInfo(request,true);
		} catch (Exception e) {
			logger.error("批复信息保存失败",e);
			return failure(e.getMessage() != null ? e.getMessage():"批复信息保存失败", null);
		}
		switch (taskcode) {
			case 100411: {// 贷款审查
				return this.submitWdscAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100412: {// 初审 自动分配
				return this.submitWdfxzjAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100413: {// 任务分配
				return this.submitWdfpgAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100414: {// 一级审批
				return this.submitWdfprwAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100415: {// 任务分配
				return this.submitWdyjspAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100416: {// 二级审批 自动分配
				return this.submitWdFpg2Appr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100418: {// 总经理审核合同
				return this.submit2JspAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100419: {// 落实贷款条件
				return this.submitLsfkAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			case 100420: {// 审核贷款条件
				return this.submitShdkAppr(workflowId, taskId, comments, nextUser,nextUserOrgId);
			}
			default: {
				return failure(WORKFLOW_ERR_MSG, null);
			}
		}
	}

	@RequestMapping("/getNextTaskAssigners/{workflowId}/{taskId}/{taskTypeId}")
	@ResponseBody
	public List<NextTaskReceiver> getNextTaskAssigners(@PathVariable String workflowId, @PathVariable String taskId, @PathVariable String taskTypeId) {
		try {
			
			ActionCode actionCode = ActionCode.COMMIT;
			taskId = this.subTaskId(taskId);
			//modify 提交时改为 按照机构、角色选择人员 2014-12-15
			//return this.workFlowService.getNextTaskReceivers(taskId, actionCode).getData();
			return this.workFlowService.getNextTaskReceivers(taskId,
					actionCode,
					Long.parseLong(workflowId),
					WorkFlowNode.getNodeById(taskTypeId)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getNextTaskUsers工作流报错");
		}
	}


	/** 签订合同 */
	@RequestMapping("/contractAppr")
	@ResponseBody
	public Result contractAppr(@RequestParam String workflowId,
			@RequestParam String taskId, @RequestParam String taskTypeId,
			@RequestParam String comments,
			HttpServletRequest request) {
		// 批复
		try {
			this.saveReplyInfo(request,true);
		} catch (Exception e) {
			logger.error("批复信息保存失败",e);
			return failure("批复信息保存失败", null);
		}
		try {
			String nextUser = "";//下一环节执行人
			String nextUserOrgId = "";//下一环节执行人机构
			String userId = this.curUserLogName();
			taskId = this.subTaskId(taskId);
			
			ActionCode act = null;
			if(WorkFlowNode.ML_BasisCheck.equals(WorkFlowNode.getNodeById(taskTypeId))){
				act = ActionCode.SUBMIT_CONTACTB;
			}else{
				act = ActionCode.SUBMIT_CONTACTA;
			}
			
			List<NextTaskReceiver> ntrList = 
					this.workFlowService.getNextTaskReceivers(taskId, act).getData();
			
			if(CollectionUtils.isNotEmpty(ntrList)){
				NextTaskReceiver nextTaskReceiver=ntrList.get(0);
				nextUser = nextTaskReceiver.getLogName();
				nextUserOrgId=nextTaskReceiver.getOrgId();
			}
			/*workFlowService.executeTask(WorkFlowCode.MICRO_LOAN, workflowId, 
					taskId, userId, WorkFlowNode.getNodeById(taskTypeId), 
					act, nextUser, comments, "");*/
			workFlowService.executeTask(ExeTaskParam.newExeTaskParam(WorkFlowCode.MICRO_LOAN, workflowId, 
					taskId, userId, WorkFlowNode.getNodeById(taskTypeId), 
					act, nextUser, comments, StringUtils.EMPTY, nextUserOrgId));
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	public TaskAction getTaskAction(String taskTypeId, String actionCode) {
		List<TaskAction> ntlist;
		try {
			ntlist = workFlowService.getTaskActions(WorkFlowNode.getNodeById(taskTypeId)).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getNextTaskActions工作流报错");
		}
		for (TaskAction nextTaskAction : ntlist) {
			if (StringUtils.equals(actionCode, nextTaskAction.getActionCode())) {
				return nextTaskAction;
			}
		}
		return null;
	}
	
	/**
	 * 检查上传文档数量
	 * 
	 * @param taskStageCode 节点ID
	 * @param projectId 业务ID
	 * @return 
	 */
	@RequestMapping("/checkUploadFiles")
	@ResponseBody
	public Result checkUploadFiles(@RequestParam String taskStageCode, @RequestParam Long projectId){
		List<String> custDocTypes = new ArrayList<String>();
		if(StringUtils.equals(WorkFlowNode.ML_LoanReview.getId(), taskStageCode) ){
			custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S52"));
		} else if(StringUtils.equals(WorkFlowNode.ML_BasisCheck.getId(), taskStageCode) ){
			custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S53"));
		} else if(StringUtils.equals(WorkFlowNode.ML_FulfillPayLoanFactor.getId(), taskStageCode)){
			custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S57"));
			
			ProjectApplication pa =businessApplicationService.searchProjectApplication(projectId);
			//团结贷则需上传放款核实及放款核实照片
			boolean unityLoanProductMark = businessApplicationService.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, pa.getProductType());
			if(unityLoanProductMark){
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S74"));//放款核实
				custDocTypes.add(dataDict.getCodeVal("CustProjectAllDocType", "S75"));//放款核实照片
			}
		}
		Result chief =  wdApprovalService.checkUploadFileList(projectId, custDocTypes);
		if(chief.getSuccess()){
			Result sub = wdApprovalService.compareSubContractDocNum(projectId);
			return sub;
		}
		return chief;
	}
	
	@RequestMapping("/findApprovalMsg")
	@ResponseBody
	public ProjAppVo findApprovalMsg(@RequestParam String taskStageCode, @RequestParam Long projectId){
		List params = ImmutableList.of(projectId, taskStageCode);
		ApprovalHistoryBxloan ahb = wdApprovalService.findApprovalMsg(params);
		ProjAppVo proApp  = null;
		if(ahb!=null){
			proApp = new ProjAppVo(ahb);
			proApp.setId(ahb.getId());
		}
		return proApp;
	}
	
	// /////////////////////////////////////////////////////////////////
	/* 流程通过相关方法 */
	/** 审核贷款条件 100420 */
	private Result submitShdkAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.submitshdkAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 落实贷款条件 100419 */
	private Result submitLsfkAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.submitlsfkAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(), nextUser,
							nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 二级审批 100417 */
	private Result submit2JspAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.submit2jspAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 分配二级审批岗 100416 */
	private Result submitWdFpg2Appr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		String curUserId = this.curUserLogName();
		try {
			this.wdApprovalService.submitWdfpg2Appr(BizApprovalOfWfParam.newBizApprovalOfWfParam(workflowId, taskId, comments, this.curUserLogName(), this.curUserName(), nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 贷款审批 100415 */
	private Result submitWdyjspAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.submitWdyjspAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 按权限分配任务 100414 */
	private Result submitWdfprwAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.submitWdfprwAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 资料完整性审核 100413 */
	private Result submitWdfpgAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.submitWdfpgAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 初审 100412 */
	private Result submitWdfxzjAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			ActionCode code = this.wdApprovalService.findNextAction(Long.parseLong(workflowId), WorkFlowNode.ML_BasisCheck.getId());
			boolean showContract = ActionCode.SUBMIT_CONTACTB.equals(code);
			if(!showContract){
				NextTaskReceiver nextTaskReceiver = this.getLeastTaskPerson(taskId);
				nextUser=nextTaskReceiver.getLogName();
				nextUserOrgId = nextTaskReceiver.getOrgId();
			}
			this.wdApprovalService.submitWdfxzjAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));

			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	/** 贷款审查 100411 */
	private Result submitWdscAppr(String workflowId, String taskId, String comments, String nextUser,String nextUserOrgId) {
		taskId = subTaskId(taskId);
		try {
			this.wdApprovalService.submitWdscAppr(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return this.failure(WORKFLOW_ERR_MSG, null);
		}
	}

	// ///////////////////////////////////////////////////////////////
	/** 获取当前用户登录名 */
	private String curUserLogName() {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getLogname();
	}

	/** 获取当前用户名字 */
	private String curUserName() {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return curUser.getName();
	}

	/** 获取taskId后半段数字编号 */
	private String subTaskId(String origin) {
		return origin.substring(origin.lastIndexOf("-") + 1, origin.length());
	}
	
	private NextTaskReceiver getLeastTaskPerson(String taskId){
//		String nextPerson = businessApplicationService
//				.getLeastTaskPerson(flowStage);
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
	
	/**拼主题字段*/
	private String writeTaskSubject(String workflowId, String nextSubject){
		String taskSubject = "";
		ProjectApplication project = wdApprovalService.findProjectAppByWorkflowId(Long.valueOf(workflowId));
		String customerName = project.getCustomerName();
		taskSubject = "微贷产品-客户："
					+ customerName 
					+ "-创建人："
					+ this.curUserName()
					+ "-" +nextSubject;
		return taskSubject;
	}
	/**
	 * 保存批复
	 * @param request
	 * @param agree	true：同意，false：否决
	 */
	private void saveReplyInfo(HttpServletRequest request,boolean agree){
		Map<String, String> map=ServletUtils.getParameterValueMap(request);
		ProjAppVo projApp=new ProjAppVo(map);
		List params = Lists.newArrayList();
		params.add(request.getAttribute("workflowId"));
		params.add(request.getAttribute("taskId"));
		params.add(request.getAttribute("taskStageCode"));
		if(projApp.getApproveLevel()>0){
			ShiroUser curUser=(ShiroUser)SecurityUtils.getSubject().getPrincipal();
			//modify by wangyawei 20150702 start 将所属机构修改为登陆机构
			SessionUser sessionUser=new SessionUser(curUser.getId(), curUser.getLogOrgid(), null);
			//modify by wangyawei 20150702 end
			sessionUser.setLoginName(curUser.getLogname());
			sessionUser.setUserNameCN(curUser.getName());
			this.wdApprovalService.updateProjAppInfo(projApp,agree,sessionUser,params);
		}
	}
	/**
	 * 电话审查情况明细列表查询方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param sEcho
	 *            datatables的被请求次数
	 * @param firstIndex
	 *            起始页数
	 * @param pageSize
	 *            每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author libingqing
	 */
	@RequestMapping("/findApprovalPhoneDetailBySearch")
	@ResponseBody
	public DataTablesPage findPhoneInfoBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		List<ApprovalPhoneDetail> queryList = this.approvalPhoneDetailService.findPhoneInfoBySearch(
				firstIndex / pageSize + 1, pageSize, projectId);
		
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), 0);
		return page;
	}
	/**
	 * 保存电话审查情况明细
	 */
	@RequestMapping("/saveApprovalPhoneDetail")
	@ResponseBody
	public Result saveApprovalPhoneDetail(@ModelAttribute ApprovalPhoneDetail approvalPhoneDetail , HttpServletRequest request){
		try {
			ShiroUser curUser=(ShiroUser)SecurityUtils.getSubject().getPrincipal();
			String callTime = request.getParameter("time");
			approvalPhoneDetail.setCallTime(CommonHelper.str2Date(callTime,CommonHelper.DF_DATE));
			approvalPhoneDetail.setCreateTime(CommonHelper.getNow());
			approvalPhoneDetail.setApprovalUserId(curUser.getId());
			approvalPhoneDetail.setApprovalUserName(curUser.getLogname());
			approvalPhoneDetail.setApprovalOrgId(curUser.getLogOrgid());
			approvalPhoneDetailService.saveApprovalPhoneDetail(approvalPhoneDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
		return success("保存成功！");
	}
	/**
	 * 删除电话审查情况明细
	 */
	@RequestMapping("/delApprovalPhoneDetail")
	@ResponseBody
	public Result deleteApprovalPhoneDetail(Long id){
		try {
			approvalPhoneDetailService.deleteApprovalPhoneDetail(id);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
		return success("删除成功！");
	}
	/**
	 * 修改电话审查情况明细
	 */
	@RequestMapping("/editApprovalPhoneDetail")
	@ResponseBody
	public ApprovalPhoneDetail editApprovalPhoneDetail(Long id){
		return approvalPhoneDetailService.editApprovalPhoneDetail(id);
	}
	/**
	 * 查看配偶姓名${approvalContent.partyId }
	 */
	@RequestMapping("/findWifeByPartyId")
	@ResponseBody
	public Result findWifeByPartyId(@RequestParam Long partyId){
		Map<Long, String> map = Maps.newHashMap();
		List<FamilyFriend> fList = this.approvalPhoneDetailService.findWifeByPartyId(partyId);
		if(CollectionUtils.isEmpty(fList)){
			return new Result(false, "没有配偶信息！", null);
		}else{
			for (FamilyFriend ff : fList) {
				map.put(ff.getPartyId(),ff.getName());
			}
			return new Result(true, "查询配偶信息成功！", map);
		}
	}
}

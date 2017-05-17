package com.coamctech.bxloan.web.controller.approval;

import static com.coamctech.bxloan.commons.GlobalConstants.APPROVAL;
import static com.coamctech.bxloan.commons.GlobalConstants.BUSINESS_APPLICATION;
import static com.coamctech.bxloan.commons.GlobalConstants.CONTRACT;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.WfActionTreeNode;
import com.coamctech.bxloan.service.approval.ApprovalApplyInfoService;
import com.coamctech.bxloan.service.approval.ApprovalService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.ApprovalContentVO;
import com.coamctech.bxloan.service.model.LoanTrialVo;
import com.coamctech.bxloan.service.model.applyinfo.ApplyInfoTableVO;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.approval.ElApprInfoVo;
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

@Controller
@RequestMapping("/" + APPROVAL)
public class ApprovalController extends BaseController{
	
	private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);
	
	private static final String WORKFLOW_ERR_MSG = "提交时发生异常,请稍后重试";
	private static final String DOCUMENT_TYPE = "DocumentType";
	private static final String ALL_CUSTDOCTYPE = "CustProjectAllDocType";
	
	@Autowired
	private BusinessApplicationService businessApplicationService;

	/*@Autowired  //旧流程
	InvokeWebservice invoker;	*/
	@Autowired
	ApprovalService approvalService;
	@Autowired
	DocumentService documentService;
	@Autowired
	BusinessApplicationService busineApplicationServices;
	@Autowired
	UniqueCustomerService uniqueCustomerService;
	@Autowired
	DataDict dataDict;
	@Autowired //新流程
	private WorkFlowService workFlowService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	
	@Autowired
	private ApprovalApplyInfoService appInfoService;
	
	@Autowired
	private WfActionTreeNodeService wfActionTreeNodeService;
	
	@SuppressWarnings("serial")
	private Map<String, String> stageCodePath = new HashMap<String, String>(){{
		put(WorkFlowNode.EL_EntryBusiApplInfo.getId(), "/" + BUSINESS_APPLICATION+ "/editApplication/");//录入业务申请信息
		put(WorkFlowNode.EL_PhoneNetworkCheck.getId(), "/" + APPROVAL + "/toApproval/");//电核网核
		put(WorkFlowNode.EL_ApproPostApproval.getId(), "/" + APPROVAL + "/toApproval/");//审批岗审批
		put(WorkFlowNode.EL_InspectPostReview.getId(), "/" + APPROVAL + "/toApproval/");//稽核岗-审查
		put(WorkFlowNode.EL_SignedContract.getId(), "/" + CONTRACT + "/signToForm/");//签订合同
		put(WorkFlowNode.EL_CheckPayLoan.getId(), "/" + APPROVAL + "/toApproval/");//审核放款x
		put(WorkFlowNode.EL_FulfillPayLoan.getId(), "/" + APPROVAL + "/toApproval/");//落实放款
	}};
	
	@RequestMapping
	public String index(){
		return APPROVAL + "/main";
	}
	
	@RequestMapping("review")
	public String review(){
		return APPROVAL + "/review";
	}
	
	/**
	 * 根据流程代码等判断跳转地址（统一入口跳转）
	 * 
	 * @param wfcode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID
	 * @param taskStatus 任务状态
	 * @param model
	 * @return 下一步跳转地址
	 */
	@RequestMapping("/todoOperation/{wfCode}/{workflowId}/{taskId}/{taskStageCode}/{taskStatus}")
	public String todoOperation(@PathVariable("wfCode") String wfCode,
			@PathVariable("workflowId") String workflowId,
			@PathVariable("taskId") String taskId,
			@PathVariable("taskStageCode")String taskStageCode,
			@PathVariable("taskStatus") String taskStatus, Model model) {
		//根据流程类型代码进行枚举转换
		WorkFlowCode workflowCode = WorkFlowCode.getById(wfCode);
		
		//拼接下一环节跳转路径
		StringBuffer nextPath = new StringBuffer("redirect:/");
		
		//拼接目标方法路径，微贷和易贷审批流程使用
		StringBuffer targetMethodPath = new StringBuffer("/todoOperation/");
		targetMethodPath.append(workflowId).append("/").append(taskId).append("/").append(taskStageCode).append("/").append(taskStatus);;
		
		//根据流程类型进行页面跳转
		switch(workflowCode){
			case EASY_LOAN:
				nextPath.append(GlobalConstants.APPROVAL).append(targetMethodPath);
				break;
			case MICRO_LOAN:
				nextPath.append(GlobalConstants.WDAPPROVAL).append(targetMethodPath);
				break;
			default:
				nextPath = new StringBuffer();
				nextPath.append(this.assembleNextPath(wfCode, workflowId, taskId, taskStageCode, taskStatus, model));
				break;
		}
		return nextPath.toString();
	}
	
	/** 
	 * 组装下一环节跳转路径
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID
	 * @param taskStageCode	节点ID
	 * @param taskStatus 任务状态
	 * @param model
	 * @return
	 */
	public String assembleNextPath(String wfCode, String workflowId,  String taskId, String taskStageCode, String taskStatus, Model model){
		StringBuffer nextPath = new StringBuffer("redirect:");
		taskId = taskId.substring(taskId.indexOf("-") + 1);
		/*ProjectApplication pa = approvalService.findProjectAppByWorkflowId(Long.parseLong(workflowId));
		Party party = uniqueCustomerService.findPartyByPartyId(pa.getPartyId()+"");*/
		//通过节点ID（即环节ID）获取该环节对应的页面地址
		WfActionTreeNode wfActionTreeNode = wfActionTreeNodeService.findWfActionTreeNodeById(Long.parseLong(taskStageCode));
		if(wfActionTreeNode == null){
			model.addAttribute("error", "数据错误，流程中并不存在该业务信息，请联系管理员。");
			return GlobalConstants.DASHBOARD + "/main";
		}
		//若任务状态为"未处理"，需先调用"开始处理任务"接口
		if(StringUtils.equals(taskStatus, "80")){
			workFlowService.startProcessingTask(workflowId, taskId, curUserLogName());
		}
		String resourceUrl = wfActionTreeNode.getResources();
		//拼接调转页面地址
		if(StringUtils.isNoneBlank(resourceUrl)){
			nextPath.append(resourceUrl);
		}
		//存储相关参数
		nextPath.append(wfCode).append("/")
				.append(workflowId).append("/")
				.append(taskId).append("/")
				.append(taskStageCode);
		/*model.addAttribute("wfCode", wfCode);
		model.addAttribute("workflowId", workflowId);
		model.addAttribute("taskId", taskId);
		model.addAttribute("taskStageCode", taskStageCode);
		model.addAttribute("projectId", pa.getProjectId());
		model.addAttribute("partyType", party.getPartyTypeCd());
		model.addAttribute("productTypeCd", pa.getProductType());*/
		return nextPath.toString();
	}
	
	/**
	 * 根据流程当前状态判断跳转地址
	 * @param projectId
	 * @param workflowId
	 * @param taskId
	 * @param model
	 * @return 下一步跳转地址
	 * @author xc
	 */
	@RequestMapping("/todoOperation/{workflowId}/{taskId}/{taskStageCode}/{taskStatus}")
	public String todoOperation(
								@PathVariable("workflowId") String workflowId,
								@PathVariable("taskId") String taskId,
								@PathVariable("taskStageCode")String taskStageCode,
								@PathVariable("taskStatus") String taskStatus,
								Model model){
		taskId = taskId.substring(taskId.indexOf("-")+1);
		StringBuilder nextPath = new StringBuilder("redirect:");
		nextPath.append(stageCodePath.get(taskStageCode));
		ProjectApplication project = this.approvalService.findProjectAppByWorkflowId(Long.parseLong(workflowId));
		if(project == null){
			model.addAttribute("error", "数据错误，流程中并不存在该业务信息，请联系管理员。");
			return GlobalConstants.DASHBOARD + "/main";
		}
		Party party = uniqueCustomerService.findPartyByPartyId(project.getPartyId()+"");
		String workFlowType = WorkFlowCode.EASY_LOAN.getCodeId();
		if(taskStatus.equals("80")){
			this.workFlowService.startProcessingTask(workflowId, taskId, curUserLogName());
		}
		
		if(taskStageCode.equals(WorkFlowNode.EL_SignedContract.getId())){//签合同
			model.addAttribute("projectId", project.getProjectId());
			model.addAttribute("workflowId", workflowId);
			model.addAttribute("taskId", taskId);
			model.addAttribute("allDocType", DocStageCode.HT_DOC.getCodeId());
			model.addAttribute("taskStageCode", taskStageCode);
			model.addAttribute("workFlowType",workFlowType);
			model.addAttribute("partyType", party.getPartyTypeCd());
			model.addAttribute("productTypeCd", project.getProductType());
		}else{
			nextPath.append(project.getProjectId()).append("/")
					.append(workflowId).append("/")
					.append(taskId);
			if(!taskStageCode.equals(WorkFlowNode.EL_EntryBusiApplInfo.getId())){
				nextPath.append("/").append(taskStageCode);
				nextPath.append("/").append(party.getPartyTypeCd());
				nextPath.append("/").append(project.getProductType());
			}
		}
		return  nextPath.toString();
	}
	
	/**
	 * 查看
	 * @return
	 * @author xc
	 */
	@RequestMapping("/toApproval/{projectId}/{workflowId}/{taskId}/{taskStageCode}/{partyType}/{productTypeCd}")
	public String toApproval(
			@PathVariable("projectId") String projectId,
			@PathVariable("workflowId") String workflowId,
			@PathVariable("taskId") String taskId,
			@PathVariable("taskStageCode") String taskStageCode,
			@PathVariable("partyType") String partyType,
			@PathVariable("productTypeCd") String productTypeCd,
			Model model){


		ApprovalContentVO approvalContent = 
				this.approvalService.findApproValContentByProjectId_EasyLoan(Long.parseLong(projectId));
		if(StringUtils.isNotBlank(approvalContent.getBizRate())){
			String bizRate = (MoneyUtil.formatMoney(new BigDecimal(approvalContent.getBizRate())
			.multiply(new BigDecimal("100"))));
			approvalContent.setBizRate(bizRate);
		}
		model.addAttribute("workflowId", workflowId);
		model.addAttribute("projectId", projectId);
		model.addAttribute("taskId", taskId);
		model.addAttribute("taskStageCode", taskStageCode);
		model.addAttribute("partyType", partyType);
		model.addAttribute("productTypeCd", productTypeCd);
		model.addAttribute("approvalContent", approvalContent);
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		
		WorkFlowNode wfNode=WorkFlowNode.getNodeById(taskStageCode);
		String result=APPROVAL + "/main";//默认返回
		switch (wfNode) {
		case EL_PhoneNetworkCheck://电核网核
			model.addAttribute("elProjAppl", approvalService.findElProjApplInfo(Long.parseLong(projectId)));
			result= APPROVAL + "/easyloan/wf_pnc";
			
			{//for switch scope
				ApplyInfoTableVO telTable = this.appInfoService
						.getTableByType("1");
				ApplyInfoTableVO netTable = this.appInfoService
						.getTableByType("2");
				model.addAttribute("applyInfoTableTel", telTable);
				model.addAttribute("applyInfoTableNet", netTable);
				model.addAttribute("allDocType", DocStageCode.WHDH_YD_DOC.getCodeId());
			}
			
			break;
		case EL_ApproPostApproval://审批岗审批
			model.addAttribute("elProjAppl", approvalService.findElProjApplInfo(Long.parseLong(projectId)));
			model.addAttribute("elApprComment",approvalService.findApprOpinion(Long.parseLong(projectId), WorkFlowNode.EL_PhoneNetworkCheck));
			result= APPROVAL + "/easyloan/wf_apa";
			{//for switch scope
				ApplyInfoTableVO telTable = this.appInfoService
						.getTableByType("1");
				ApplyInfoTableVO netTable = this.appInfoService
						.getTableByType("2");
				model.addAttribute("applyInfoTableTel", telTable);
				model.addAttribute("applyInfoTableNet", netTable);
				model.addAttribute("allDocType", DocStageCode.DKSC_WD_DOC.getCodeId());
			}
			break;

		default:
			break;
		}
		
//		if(taskStageCode.equals(WorkFlowNode.EL_ApproPostApproval.getId())
//				||taskStageCode.equals(WorkFlowNode.EL_InspectPostReview.getId())){
//			return APPROVAL + "/review";
//		}
		
		
		return result;
	}
	
	/**
	 * 退回
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping(value="/quitApproval",method=RequestMethod.POST)
	public Result quitApproval(@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode")String taskStageCode,
			@RequestParam("comments") String comments){
		
		String curUserLogName = curUserLogName();
		taskId = subTaskId(taskId);
		String curUserName = curUserName();
		
		try {
			this.approvalService.sendBackApproval(workflowId, curUserLogName, taskId,
					taskStageCode, comments,curUserName);
			return this.success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return failure(WORKFLOW_ERR_MSG, null);
		}
		
	}
	
	/**
	 * 撤销
	 * @return
	 * @author xc
	 */
	@ResponseBody
	@RequestMapping(value="/dismissAppr",method=RequestMethod.POST)
	public Result dismissApproval(@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode")String taskStageCode,
			@RequestParam("comments") String comments){
		
		String curUserLogName = curUserLogName();
		taskId = subTaskId(taskId);
		String curUserName = curUserName();
		
		try {
			this.approvalService.cancelApproval(workflowId, curUserLogName, taskId, taskStageCode, comments, curUserName,null);
			return this.success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG, e);
			return failure(WORKFLOW_ERR_MSG, null);
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
	@RequestMapping(value="submitApproval",method=RequestMethod.POST)
	public Result submitApproval(
			@RequestParam("workflowId") String workflowId,
			@RequestParam("taskId") String taskId,
			@RequestParam("taskStageCode")String taskStageCode,
			@RequestParam("comments") String comments,
			HttpServletRequest request){
		taskId = taskId.substring(taskId.indexOf("-")+1);
		
		if(taskStageCode.equals(WorkFlowNode.EL_PhoneNetworkCheck.getId())){//电网提交到审批岗
			
			return this.submitInternetAppr(workflowId, taskId, comments,request);
			
		}else if(taskStageCode.equals(WorkFlowNode.EL_ApproPostApproval.getId())){//审批岗提交到稽查
			
			return this.submitShgAppr(workflowId, taskId, comments,request);
			
		}else if(taskStageCode.equals(WorkFlowNode.EL_InspectPostReview.getId())){//稽查岗确认提交
			
			return this.submitInspect(workflowId, taskId, comments);
			
		} else if(taskStageCode.equals(WorkFlowNode.EL_CheckPayLoan.getId())){//审核放款
			
			return this.approvalLoan(workflowId, taskId, comments,request);
			
		}else if(taskStageCode.equals(WorkFlowNode.EL_FulfillPayLoan.getId())){//落实放款
			
			return this.doLoan(workflowId, taskId, comments);
			
		}else{
			
			return failure(WORKFLOW_ERR_MSG, null);
			
		}
		
	}
	@RequestMapping("/findNextTaskAssigners/{taskId}/{workflowId}/{taskStageCode}")
	@ResponseBody
	public List<NextTaskReceiver> findNextTaskAssigners(@PathVariable("taskId") String taskId,
											@PathVariable("workflowId") String workflowId,
											@PathVariable("taskStageCode") String taskStageCode) {
		//String workFlowId,String taskId,WorkFlowNode node
		try {
			
			ActionCode actionCode = ActionCode.COMMIT;
			taskId = this.subTaskId(taskId);
			WorkFlowNode node = WorkFlowNode.getNodeById(taskStageCode);
			return this.workFlowService.getNextTaskReceivers(taskId, 
					actionCode, 
					Long.parseLong(workflowId), node).getData();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("getNextTaskUsers工作流报错");
		}
	}
	/**
	 * 判断文件是否已上传
	 * @return true 未上传
	 */
	@RequestMapping("/checkUploadFile")
	@ResponseBody
	public Result checkUploadFile(@RequestParam("projectId")Long projectId,@RequestParam("docTypeKey")String docTypeKey){
		boolean flag = false;
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		//modify by HWL start 20150701 机构修改为登录机构
		if(StringUtils.equals(curUser.getLogOrgid().toString(), "10001")){
			return success("已上传");
		}
		//modify by HWL end 20150701 机构修改为登录机构
		if(StringUtils.isNotBlank(docTypeKey)){
			List<DocumentIndex> list = null;
			String[] str = docTypeKey.split(",");
			for (String type : str) {
				list = this.approvalService.findDocumentIndexList(projectId, dataDict.getCodeVal(ALL_CUSTDOCTYPE, type));
				if(CollectionUtils.isEmpty(list)){
					flag = true;
				}
			}
		}
		if(!flag){
			return success("已上传");
		}
		return failure("未上传");
	}
	/**
	 * 初始化上传文档弹窗相关信息
	 * @param partyId
	 * @param projectId
	 * @param docTypeKey Code表对应Code_key如 S40,S41
	 * @return
	 */
	@RequestMapping("/beforeUpload")
	@ResponseBody
	public DocumentUploadVO beforeUploadDoc(@RequestParam String partyId, @RequestParam Long projectId, @RequestParam(required=false) String docTypeKey) {
		DocumentUploadVO docUploadVo = new DocumentUploadVO();
		docUploadVo.setPartyId(String.valueOf(partyId));
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		Party p = uniqueCustomerService.findPartyByPartyId(partyId);
		docUploadVo.setDocumentType("44");
		if(StringUtils.isNotBlank(docTypeKey)){
			String documentType=dataDict.getCodeVal(DOCUMENT_TYPE, docTypeKey);
			docUploadVo.setDocumentType(documentType);
		}
		docUploadVo.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		docUploadVo.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		docUploadVo.setCreateDateTime(DateTools.getSystemDate("yyyy-MM-dd"));
		docUploadVo.setCustomerNum(p.getCustomerNum());
		ProjectApplication pa=approvalService.findProjectApplication(projectId);
		docUploadVo.setBizNum(pa.getProjectNo());
		docUploadVo.setBizId(String.valueOf(pa.getProjectId()));
		docUploadVo.setCreateUserName(curUser.getName());
		docUploadVo.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		//文档创建人修改 modify gph 201506121534
		docUploadVo.setCreateUserNum(curUser.getId().toString());
//		docUploadVo.setCreateUserNum(curUser.getUsernum());
		docUploadVo.setCreateTypeCd("01");
		docUploadVo.setDocumentNum(commonBizNumberBS.generateDocumentNum(
				p.getCustomerNum(), "02"));//业务
		return docUploadVo;
	}
	
	/**
	 * 审核放款
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @return
	 * @author xc
	 */
	private Result approvalLoan(String workflowId, String taskId,
			String comments,HttpServletRequest request) {
		taskId = subTaskId(taskId);
		//String nextUser = this.getLeastTaskPerson(taskId);
		String nextUser = request.getParameter("nextUser");//下一环节执行人
		String nextUserOrgId = request.getParameter("nextUserOrgId");//下一环节执行人机构id
		try {
			//this.approvalService.approvalLoan(workflowId, taskId, comments, curUserId, nextUser);
			this.approvalService.approvalLoan(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser, nextUserOrgId));
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG,e);
			return this.failure(WORKFLOW_ERR_MSG,null);
		}
	}
	
	/**
	 * 落实放款
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @return
	 * @author xc
	 */
	private Result doLoan(String workflowId, String taskId,
			String comments) {
		taskId = subTaskId(taskId);
		String result = "";
		NextTaskReceiver nextUser = this.getLeastTaskPerson(workflowId, taskId,
				WorkFlowNode.EL_FulfillPayLoan);
		try {
			result = this.approvalService
					.endWorkFlowAndUpdateContract(BizApprovalOfWfParam
							.newBizApprovalOfWfParam(workflowId, taskId,
									comments, this.curUserLogName(),
									this.curUserName(), nextUser.getLogName(),
									nextUser.getOrgId()));
			if(StringUtils.isBlank(result)){
				throw new NullPointerException("调用工作流返回结果为空");
			}
			
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG,e);
			return this.failure(WORKFLOW_ERR_MSG,null);
		}
	}

	/**
	 * 稽查岗确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @return
	 * @author xc
	 */
	private Result submitInspect(String workflowId, String taskId,
			String comments) {
		
		taskId = subTaskId(taskId);
		NextTaskReceiver nextUser = this.getLeastTaskPerson(workflowId, taskId,
				WorkFlowNode.EL_InspectPostReview);
		try {
			this.approvalService.submitInspect(BizApprovalOfWfParam
					.newBizApprovalOfWfParam(workflowId, taskId, comments,
							this.curUserLogName(), this.curUserName(),
							nextUser.getLogName(), nextUser.getOrgId()));
			
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG,e);
			return this.failure(WORKFLOW_ERR_MSG,null);
		}
	}

	/**
	 * 审批岗审批确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @return
	 */
	private Result submitShgAppr(String workflowId, String taskId,
			String comments,HttpServletRequest request) {
		taskId = subTaskId(taskId);
		//自动分配任务
		/*NextTaskReceiver nextUser = this.getLeastTaskPerson(workflowId, taskId,
				WorkFlowNode.EL_ApproPostApproval);
		String nextUserLogName=null;
		String nextUserOrgId=null;
		if(nextUser!=null){
			nextUserLogName=nextUser.getLogName();
			nextUserOrgId=nextUser.getOrgId();
			
		}*/
		try {
			String nextUser = request.getParameter("nextUser");
			String nextUserOrgId = request.getParameter("nextUserOrgId");
			if(StringUtils.isBlank(nextUser)){
				throw new NullPointerException("nextUser is null");
			}
			/*this.approvalService.submitShgAppr(workflowId, taskId, 
					comments, curUserId, nextUser);*/
			ElApprInfoVo apprInfo=new ElApprInfoVo();
			this.initElApprInfo(apprInfo, taskId, nextUser,nextUserOrgId, comments, request);
			this.approvalService.saveElProjApplOfSpg(apprInfo);
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG,e);
			return this.failure(e.getMessage() != null ? e.getMessage():WORKFLOW_ERR_MSG,null);
		}
		
	}

	/**
	 * 电核网核确认提交
	 * @param workflowId
	 * @param taskId
	 * @param comments
	 * @return
	 */
	private Result submitInternetAppr(String workflowId, String taskId, 
			String comments,HttpServletRequest request){
		taskId = subTaskId(taskId);
		try {
			String nextUser = request.getParameter("nextUser");
			String nextUserOrgId = request.getParameter("nextUserOrgId");
			if(StringUtils.isBlank(nextUser)){
				throw new NullPointerException("nextUser is null");
			}
			ElApprInfoVo apprInfo=new ElApprInfoVo();
			this.initElApprInfo(apprInfo, taskId, nextUser,nextUserOrgId, comments, request);
			this.approvalService.saveElProjApplOfDhwh(apprInfo);
			return success();
		} catch (Exception e) {
			logger.error(WORKFLOW_ERR_MSG,e);
			return this.failure(e.getMessage() != null ? e.getMessage():WORKFLOW_ERR_MSG,null);
		}
	}
	
	/**
	 * 跳转过程意见页面
	 * @param projectId
	 * @param workflowId
	 * @return
	 * @author lj
	 */
	@RequestMapping("/processSuggestion")
	public String swapSuggestion(@RequestParam String projectId,@RequestParam String workflowId, Model model) {
		model.addAttribute("projectId", projectId);
		model.addAttribute("workflowId", workflowId);
		return APPROVAL + "/processOpinion";
	}
	
	/**
	 * 贷款试算（窗口）
	 * @return
	 * @author hwl
	 */
	@RequestMapping("/loanTrial")
	public String loanTrialByProjId(Long projectId,Model model){
		LoanTrialVo loanTrial= approvalService.findLoanByProjId(projectId);
		model.addAttribute("projApp", loanTrial);
		return APPROVAL+"/loanTrial";
	}
	/**
	 * 获取过程意见
	 * @param workflowId
	 * @return List<ProcessSuggestionVO>对象
	 * @author lj
	 */
	@RequestMapping("/getSuggestion")
	@ResponseBody
	public List<TaskTransferProcess> getSuggestion(@RequestParam String workflowId) {
		List<TaskTransferProcess>  lists = workFlowService.getTaskTransferProcessOfWorkflow(workflowId, "DESC").getData();
		//List<ProcessSuggestionVO> list = approvalService.getSuggestion(workflowId);
		return lists;
	}
	
	/**
	 * 获取贷款申请表下载地址
	 * @param projectId
	 * @param documentType
	 * @return List<String> 
	 * @author lj
	 */
	@RequestMapping("/findDocuments")
	@ResponseBody
	public List<String> findDocuments(@RequestParam Long projectId,@RequestParam(required = false) String documentType) {
		//20150621 szx 修改贷款申请表下载类型错误
		String applyLoanCode = dataDict.getCodeVal("CustProjectAllDocType", "S44");
//		String applyLoanCode = dataDict.getCodeVal("DocumentType", "S45");
		List<DocumentIndex> documentList = approvalService.findApplyDocuments(projectId, applyLoanCode);
    	List<String> list = new ArrayList<String>();
    	if(documentList.size()>0){
    		list.add(documentList.get(0).getDocumentRoute());
    		list.add(documentList.get(0).getDocumentName());
    		return list;
    	}else{
    		return null;
    	}
	}
	
	/**
	 * 获取上传文档的种类
	 * 
	 * @param partyId
	 * @return Map<String,String>
	 * */
	@RequestMapping("/findUploadCustDocTypes")
	@ResponseBody
	public CustDocInfo findUploadCustDocTypes(@RequestParam String taskStageCode, @RequestParam Long projectId) {
		String custDocType = "";
		ProjectApplication pa  = approvalService.findProjectApplication(projectId);
	//String workFlowType =  approvalService.findWorkFlowTypeByProductCd(pa.getProductType());
		String workFlowType="1004";
		try {
			if (Integer.parseInt(workFlowType) == GlobalConstants.WD_WF_TYPE_40_INT) {
				//微贷
				if (StringUtils.equals(WorkFlowNode.ML_LoanReview.getId(),taskStageCode)) {
					custDocType = DocStageCode.DKSC_WD_DOC.getCodeId();
				} else if (StringUtils.equals(WorkFlowNode.ML_BasisCheck.getId(), taskStageCode)) {
					custDocType = DocStageCode.CS_DOC.getCodeId();
				} else if (StringUtils.equals(WorkFlowNode.ML_FulfillPayLoanFactor.getId(),taskStageCode)) {
					custDocType = DocStageCode.LSFK_WD_DOC.getCodeId();
				} 
			} else if (Integer.parseInt(workFlowType) == GlobalConstants.EL_WF_TYPE_30_INT) {
				//易贷
				if (StringUtils.equals(
						WorkFlowNode.EL_PhoneNetworkCheck.getId(),
						taskStageCode)) {
					custDocType = DocStageCode.WHDH_YD_DOC.getCodeId();
				} else if (StringUtils.equals(
						WorkFlowNode.EL_ApproPostApproval.getId(),
						taskStageCode)) {
					custDocType = DocStageCode.DKSC_WD_DOC.getCodeId();
				} else if (StringUtils.equals(
						WorkFlowNode.EL_CheckPayLoan.getId(), taskStageCode)) {
					custDocType = DocStageCode.LSFK_YD_DOC.getCodeId();
				}  
			}
		} catch (Exception e) {
			logger.error("ApprovalController中findUploadCustDocTypes方法获取上传文档的种类失败，错误原因："+e.getMessage());
		}
		return documentService.findUploadCustDocTypes(null, null,
				custDocType);// 易贷
	}

	/**
	 * 获取已经上传文档的种类
	 * 
	 * @param partyId
	 * @return List<String>
	 * */
	@RequestMapping("/findDocumentCustDocTypes")
	@ResponseBody
	public List<Object[]> findDocumentCustDocTypes(@RequestParam String taskStageCode, @RequestParam Long projectId) {
		String custDocType = "";
		ProjectApplication pa  = approvalService.findProjectApplication(projectId);
		String workFlowType =  approvalService.findWorkFlowTypeByProductCd(pa.getProductType());

		if (Integer.parseInt(workFlowType) == GlobalConstants.WD_WF_TYPE_40_INT){
			//微贷
			if(StringUtils.equals(WorkFlowNode.ML_LoanReview.getId(), taskStageCode)){
				custDocType = DocStageCode.DKSC_WD_DOC.getCodeId();
			}else if(StringUtils.equals(WorkFlowNode.ML_BasisCheck.getId(), taskStageCode)){
				custDocType = DocStageCode.CS_DOC.getCodeId();
			}
		} else if(Integer.parseInt(workFlowType) == GlobalConstants.EL_WF_TYPE_30_INT){
			//易贷
			if(StringUtils.equals(WorkFlowNode.EL_PhoneNetworkCheck.getId(), taskStageCode)){
				custDocType = DocStageCode.WHDH_YD_DOC.getCodeId();
			}else if(StringUtils.equals(WorkFlowNode.EL_ApproPostApproval.getId(), taskStageCode)){
				custDocType = DocStageCode.DKSC_WD_DOC.getCodeId();
			}
		}
		
		return documentService.findDocumentCustDocTypes(projectId,
				custDocType);
	}

	///////////////////
	///////PRIVATE////
	//////////////////
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
	
	/**
	 * @param workFlowId
	 * @param taskId
	 * @param node
	 * @return
	 */
	private NextTaskReceiver getLeastTaskPerson(String workFlowId,String taskId,WorkFlowNode node){
		TypedResult<List<NextTaskReceiver>> resultList = 
				this.workFlowService.getNextTaskReceivers(taskId,ActionCode.COMMIT,
						Long.parseLong(workFlowId),node);
															
		NextTaskReceiver receiver = null;
		for(NextTaskReceiver rec : resultList.getData()){
			if(receiver==null
				||Integer.parseInt(receiver.getTotal())>Integer.parseInt(rec.getTotal())){
				receiver = rec;
			}
		}
		return receiver;
	}
	
	/**
	 * @param apprInfo
	 * @param taskId
	 * @param nextUserLogName
	 * @param nextUserOrgId
	 * @param comments
	 * @param request
	 */
	private void initElApprInfo(ElApprInfoVo apprInfo, String taskId,
			String nextUserLogName,String nextUserOrgId, String comments, HttpServletRequest request) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		apprInfo.setCurUserId(curUser.getId());
		apprInfo.setCurUserLoginName(curUser.getLogname());
		apprInfo.setCurUserName(curUser.getName());
		//modify by wangyawei 20150702 start 将所属机构修改为登陆机构
		apprInfo.setCurUserOrgId(curUser.getLogOrgid());
		//modify by wangyawei 20150702 end
		//apprInfo.setCurUserId(curUser.getr);//角色暂不确定
		apprInfo.setProjectId(CommonHelper.toLong(request.getParameter("projectId")));
		apprInfo.setWorkflowId(CommonHelper.toLong(request.getParameter("workflowId")));
		apprInfo.setTaskId(CommonHelper.toLong(taskId));
		apprInfo.setTaskStageCode(request.getParameter("taskStageCode"));
		apprInfo.setComments(comments);
		apprInfo.setNextUser(nextUserLogName);
		apprInfo.setNextUserOrgId(nextUserOrgId);
		apprInfo.setProposalAmt(CommonHelper.toBigDecimal(request.getParameter("proposalAmtStr")));
		BigDecimal bd=CommonHelper.toBigDecimal(request.getParameter("proposalRateStr"));
		if(bd!=null){
			bd=bd.movePointLeft(2);
		}
		apprInfo.setProposalRate(bd);
		apprInfo.setProposalTerm(CommonHelper.toInt(request.getParameter("proposalTerm")));
		apprInfo.setProposalTermUnit("2");//月
	}
}

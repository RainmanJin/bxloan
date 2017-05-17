package com.coamctech.bxloan.web.controller.bizapply;

import static com.coamctech.bxloan.commons.GlobalConstants.DASHBOARD;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.dao.IndustryTypeDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.bizapply.BizCreditApplicationService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.exceptions.ServiceException;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TaskAction;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.sysmng.ProductMngService;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;

/**   
 * 类名称：BizCreditApplicationController
 * 类描述 ：授信业务申请
 * 创建人: 马峥  
 * 创建时间：2015年7月14日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年7月14日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
@Controller
@RequestMapping(GlobalConstants.INIT_BIZ_CREDIT_APPLICATION)
public class BizCreditApplicationController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BizCreditApplicationController.class);
	@Autowired
	private BizCreditApplicationService bizCreditApplicationService;
	
	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	
	@Autowired
	private CorporationCustomerService corporationCustomerService;
	
	@Autowired
	private ProductPriceService productPriceService;
	
	@Autowired
	private BusinessApplicationService businessApplicationService;
	
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private OrgDeptmentService orgDeptmentService;
	
	@Autowired
	private IndustryTypeDao industryTypeDao;
	
	@Autowired
	private WfActionTreeNodeService wfActionTreeNodeService;
	
	@Autowired
	private ProductMngService productMngService;
	
	@Autowired
	private CreditContractApprovalService creditContractApprovalService;
	
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	
	/** 
	 * 发起授信业务申请
	 * 
	 * @param partyId 参与人ID
	 * @param productCd 贷款产品代码
	 * @param model
	 * @return 跳转路径
	 */
	@RequestMapping("/{partyId}/{productCd}")
	public String index(@PathVariable("partyId") Long partyId, @PathVariable("productCd") Long productCd, Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
		BusinessApplicationWdVO vo = null;
		StringBuffer nextPath = new StringBuffer("redirect:/");
		try {
			/** 校验贷款产品是否允许发起授信审批流程 */
			ProductConfig productConfig = productMngService.findUniqueProductConfig(
					curUser.getLogOrgid(), 
					productCd,
					WorkFlowCode.CREDIT_APPROVAL.getCodeId());
			if(productConfig == null){
				model.addAttribute("error", "<strong>此贷款产品不允许发起授信审批流程,请联系系统管理员！</strong>");
				return GlobalConstants.INID_MNG;
			}
			
			//组装登录用户参数
			SessionUser sessionUser = new SessionUser();
			sessionUser.setUserId(curUser.getId());
			sessionUser.setLoginName(curUser.getLogname());
			sessionUser.setUserNameCN(curUser.getName());
			sessionUser.setOrgId(curUser.getLogOrgid());
			sessionUser.setOrgName(curUser.getLogOrgDesc());
			
			/** 发起授信业务申请 */
			vo = bizCreditApplicationService.startMicroloanBusiness(
						productCd, 
						party, 
						sessionUser,
						WorkFlowCode.CREDIT_APPROVAL,
						WorkFlowNode.CA_EntryBusiApplInfo,
						dataDict.getCodeVal("ProjectBussinessType", "S2"));
			
			/** 存储流程与业务关系对象 */
			WfBusinessRelation wfBusinessRelation = new WfBusinessRelation(
					WorkFlowCode.CREDIT_APPROVAL.getCodeId(),
					vo.getWorkflowId(),
					vo.getProjectId(),
					null,
					new Date());
			wfActionTreeNodeService.saveWfBusinessRelation(wfBusinessRelation);
			
			/** 拼接跳转路径 */
			nextPath.append(GlobalConstants.INIT_BIZ_CREDIT_APPLICATION).append("/editApplication/")
				    .append(WorkFlowCode.CREDIT_APPROVAL.getCodeId()).append("/")
				    .append(vo.getWorkflowId()).append("/")
				    .append(vo.getTaskId()).append("/")
				    .append(WorkFlowNode.CA_EntryBusiApplInfo.getId()).toString();
			return nextPath.toString();
		} catch (Exception e) {
			String msg = e.getMessage();
			if(msg.equals("startProcess")) {
				model.addAttribute("error", "<strong>启动流程失败，请联系管理员！</strong>");
			} else if (msg.equals("disposeTask")) {
				model.addAttribute("error", "<strong>任务处理失败，请联系管理员！</strong>");
			}
			e.printStackTrace();
			logger.error("发起授信业务申请失败，error:{}", e.getMessage());
		} 
		return GlobalConstants.INID_MNG;
	}
	
	/** 
	 * 从待办列表跳转到授信业务申请-填报页面
	 * 
	 * @param workflowId 流程ID
	 * @param taskId 任务ID
	 * @param projectId 业务ID
	 * @param model      
	 * @return
	 */
	@RequestMapping("/editApplication/{wfCode}/{workflowId}/{taskId}/{taskStageCode}")
	public String editApplication(@PathVariable("wfCode") String wfCode, @PathVariable("workflowId") String workflowId,
			@PathVariable("taskId") String taskId, @PathVariable("taskStageCode") String taskStageCode, Model model){
		//获取流程与业务关系对象
		WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
		Long businessId = wfBusinessRelation.getBusinessId();
		//获取业务对象
		ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(businessId);
		if (!dataDict.getCodeVal("ProjectStatus", "S0").equals(projectApplication.getProjectStatus())) {
			return "redirect:/" + DASHBOARD;
		}
		TypedResult<List<TaskAction>> buttons = null;
		//获取按钮
		buttons = workFlowService.getTaskActions(WorkFlowNode.ML_EntryBusiApplInfo);
		if (!buttons.getSuccess()) {
			throw new RuntimeException("getNextTaskActions");
		}
		//下一环节所有人
		List<NextTaskReceiver> receiverList = workFlowService.getNextTaskReceivers(taskId, WorkFlowService.ActionCode.COMMIT).getData();
		BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
		//存入流程相关信息
		businessApplicationWdVO.setWorkflowId(Long.valueOf(workflowId));
		businessApplicationWdVO.setTaskId(taskId);
		businessApplicationWdVO.setWfCode(wfCode);
		businessApplicationWdVO.setTaskStageCode(taskStageCode);
		businessApplicationWdVO.setButtons(buttons.getData());
		businessApplicationWdVO.setReceivers(receiverList);
		//查询相关业务信息，将相关信息fill到model和vo中
		this.searchBussiness(model, projectApplication, businessApplicationWdVO);
		model.addAttribute("type", "edit");  //页面为可编辑状态
		return GlobalConstants.BIZ_CREDIT_APPLICATION_WL + "/main";
	}
	
	/**
	 * 已办列表/业务查询列表-查看业务信息
	 * 
	 * @param projectId 业务ID
	 * @param consultLocation 隐藏页头页尾标识
	 * @param model
	 * @return JSP页面路径
	 */
	@RequestMapping("/checkApplication/{workflowId}")
	public String checkApplication(@PathVariable("workflowId") Long workflowId, Model model) {
		return this.forwardToCheckModel(workflowId, null, model);
	}
	
	/**
	 * 授信审批流程中右侧眼睛-查看业务信息
	 * 
	 * @param workflowId 流程ID
	 * @param consultLocation 隐藏页头页尾标识
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkApplicationWindow")
	@ResponseBody
	public String checkApplicationWindow(@RequestParam Long workflowId, 
			@RequestParam String consultLocation, Model model) {
		return GlobalConstants.INIT_BIZ_CREDIT_APPLICATION + "/forwardToCheckModel?workflowId="
				+ workflowId + "&consultLocation=" + consultLocation;
	}
	
	/**
	 * 跳转至查看业务信息页面
	 * 
	 * @param workflowId 流程ID
	 * @param consultLocation 隐藏页头页尾标识
	 * @param model
	 * @return JSP页面路径
	 */
	@RequestMapping("/forwardToCheckModel")
	public String forwardToCheckModel(@RequestParam Long workflowId, 
			@RequestParam String consultLocation, Model model) {
		//获取流程与业务关系对象
		WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(workflowId);
		Long businessId = wfBusinessRelation.getBusinessId();
		
		//组装参数信息
		ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(businessId);
		BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
		businessApplicationWdVO.setWfCode(wfBusinessRelation.getWorkFlowType());
		
		//查询相关业务信息，将相关信息fill到model和vo中
		this.searchBussiness(model, projectApplication, businessApplicationWdVO);
		model.addAttribute("consultLocation", consultLocation);
		model.addAttribute("type", "check");
		return GlobalConstants.BIZ_CREDIT_APPLICATION_WL + "/main";
	}
	
	/**
	 * 录入业务环节-基本项目信息保存
	 * 
	 * @param businessApplicationWdVO
	 * @return 
	 */
	@RequestMapping("/saveBusiness")
	@ResponseBody
	public Result saveBusiness(BusinessApplicationWdVO businessApplicationWdVO) {
		try {
			//保存授信业务申请信息
			businessApplicationWdVO.setGuaranteeMode(businessApplicationWdVO.getGuaranteeMode().substring(0, businessApplicationWdVO.getGuaranteeMode().length() - 1));
			bizCreditApplicationService.saveMicroloanBusiness(businessApplicationWdVO);
			return success("基本项目信息保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("基本项目信息保存失败，error{}", e.getMessage());
			return failure("基本项目信息保存失败！");
		}
	}
	
	/**
	 * 检查业务信息
	 * 
	 * @param projectId 业务ID
	 * @param guaranteeMode 担保方式
	 * @return
	 */
	@RequestMapping("/checkBusinessInfoBeforeSendProcess")
	@ResponseBody
	public Result checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode) {
		try {
			bizCreditApplicationService.checkBusinessInfoBeforeSendProcess(
					projectId, guaranteeMode,
					WorkFlowService.WorkFlowCode.CREDIT_APPROVAL.getCodeId());
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("检查业务信息失败,error:{}", e);
			return failure(e.getMessage());
		} 
	}
	
	/**
	 * 授信审批流程-录入业务环节提交下一环节
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @param opinion 意见
	 * @param actionCode 操作类型
	 * @return
	 */
	@RequestMapping("/submitApply")
	@ResponseBody
	public Result submitApply(String wfCode, String workflowId, String taskId,
			String taskStageCode, String opinion, String actionCode,
			String nextTaskReceiver) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		try {
			ActionCode actCode = ActionCode.getActionCodeById(actionCode);
			/** 提交一下环节 */
			businessApplicationService.sendProccessAndUpdateApplication(
					WorkFlowCode.getById(wfCode), workflowId,
					taskId, curUser.getLogname(),
					WorkFlowNode.getNodeById(taskStageCode), actCode,
					nextTaskReceiver, opinion, "", 
					taskStageCode);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("授信审批流程-录入业务环节提交下一环节失败，error:{}", e);
			return failure("授信审批流程-录入业务环节提交下一环节失败");
		}
	}

	/**
	 * 撤销授信审批流程
	 * 
	 * @param wfCode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID(即环节ID)
	 * @return
	 */
	@RequestMapping("/cancelApply")
	@ResponseBody
	public Result cancelApply(String wfCode, String workflowId, String taskId, String taskStageCode) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		try{
			creditContractApprovalService.dropBackApproval(
					WorkFlowCode.getById(wfCode),
					WorkFlowNode.getNodeById(taskStageCode), workflowId, taskId,
					StringUtils.EMPTY, StringUtils.EMPTY, curUser.getLogname(),
					curUser.getName());
			return success();
		} catch(Exception e){
			e.printStackTrace();
			logger.error("授信审批流程-录入业务环节撤销失败，error:{}", e);
			return failure("授信审批流程-录入业务环节撤销失败");
		}
	}
	
	/**
	 * 查询相关业务信息，将相关信息fill到model和vo中
	 * 
	 * @param pa
	 * @param businessApplicationWdVO
	 */
	public void searchBussiness(Model model, ProjectApplication pa, BusinessApplicationWdVO businessApplicationWdVO){
		if (pa.getApplyDate() != null) {
			pa.setApplyDateStr(CommonHelper.date2Str(pa.getApplyDate(), CommonHelper.DF_DATE));
		}
		Party party = uniqueCustomerService.findPartyByPartyId(pa.getPartyId().toString()); //参与人信息
		CorporationCustomer corporationCustomer = null;
		Individual individual = null; 
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerService.findById(party
					.getPartyId());
			String industryName = industryTypeDao.findIndustryNameByIndustryCd(corporationCustomer.getIndustryLevelTwoCd());
			model.addAttribute("industryName", industryName);
			model.addAttribute("corporationCustomer", corporationCustomer);
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = uniqueCustomerService.getIndividual(party.getPartyId()
					.toString());
			model.addAttribute("individual", individual);
		}
		//将客户信息fill到vo中
		BeanUtils.copyProperties(party.getPartyTypeCd().equals("1") ? corporationCustomer : individual, businessApplicationWdVO);
		
		//将业务信息fill到vo中
		BeanUtils.copyProperties(pa, businessApplicationWdVO); 
		
		//利率信息
		BizRate bizRate = businessApplicationService.getBizRateByProjectId(pa.getProjectId().toString());
		if (bizRate != null) {
			BeanUtils.copyProperties(bizRate, businessApplicationWdVO);  
		}
		if (pa.getApplyDate() != null) {
			businessApplicationWdVO.setApplyDate(CommonHelper.date2Str(pa.getApplyDate(), CommonHelper.DF_DATE));
		}
		if (businessApplicationWdVO.getBizRate() != null) {  //年利率
			businessApplicationWdVO.setBizRate(businessApplicationWdVO
					.getBizRate().multiply(new BigDecimal("100")));
		}
		if (businessApplicationWdVO.getIrNegoSymbCd() != null) {  //上浮比例
			businessApplicationWdVO.setIrNegoSymbCd(businessApplicationWdVO
					.getIrNegoSymbCd().multiply(new BigDecimal("100")));
		}
		//产品配置和产品信息fill到vo中
		String productCd = pa.getProductType();  
		ProductConfig productConfig = productMngService.findUniqueProductConfig(
				Long.valueOf(pa.getApplyOrg()), 
				Long.valueOf(productCd), 
				businessApplicationWdVO.getWfCode());
		if (productConfig != null) {
			String replyPeriodUnit= productConfig.getReplyPeriodUnit();
			String applyTermUnit= businessApplicationWdVO.getApplyTermUnit();
			if(StringUtils.isNoneBlank(replyPeriodUnit) && StringUtils.isBlank(applyTermUnit) ){  //期限单位
				businessApplicationWdVO.setApplyTermUnit(replyPeriodUnit);
			}
		}
		Product product = businessApplicationService.findProductByProductCd(productCd);
		businessApplicationWdVO.setProduct(product);
		
		businessApplicationWdVO.setCurUserOrgId(Long.valueOf(pa.getApplyOrg()));
		//担保方式
		String guaranteeMode = businessApplicationWdVO.getGuaranteeMode(); 
		if(StringUtils.isNotBlank(guaranteeMode)) {
			businessApplicationWdVO.setGuaranteeMode(guaranteeMode + ",");
		}
		
		//产品列表
		List<Object[]> products = productPriceService.findProduct();
		
		//担保方式列表
		Collection<CodeData> guaranteeModes = dataDict.getOneType("CdsGuarantMode");
		
		//所属机构
		String orgName = orgDeptmentService.findDeptNameById(Long.valueOf(pa.getApplyOrg())); 
		
		//根据产品代码判断是不是农贷
		final boolean farmerLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, pa.getProductType());
		if(farmerLoanProductMark) {
			String type = bizApplyQueryService.findAgroBizTypeByProjId(businessApplicationWdVO.getProjectId());
			model.addAttribute("customerId", party.getPartyId());
			model.addAttribute("agriculturalLoan", Boolean.TRUE);
			model.addAttribute("bizType", type);
		}
		
		//存储相关参数
		model.addAttribute("vo", businessApplicationWdVO);
		model.addAttribute("party", party);
		model.addAttribute("products", products);
		model.addAttribute("guaranteeModes", guaranteeModes);
		model.addAttribute("productCd", productCd);
		model.addAttribute("productConfig", productConfig);
		model.addAttribute("allDocType", DocStageCode.YW_WD_DOC.getCodeId());
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		model.addAttribute("customerManagerName", pa.getCustomerManagerName());  //客户经理
		model.addAttribute("orgName", orgName);
	}
}

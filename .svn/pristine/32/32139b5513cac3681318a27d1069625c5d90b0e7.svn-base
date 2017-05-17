package com.coamctech.bxloan.web.controller.creditContractMng;

import static com.coamctech.bxloan.commons.GlobalConstants.DASHBOARD;
import static com.coamctech.bxloan.commons.GlobalConstants.FORM_CONTRACT_EL;
import static com.coamctech.bxloan.commons.GlobalConstants.FORM_CONTRACT_ML;
import static com.coamctech.bxloan.commons.GlobalConstants.RISKMNG_FLAG;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.dao.BizRateDao;
import com.coamctech.bxloan.dao.IndustryTypeDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerManagerTeam;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.FamilyFriend;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.InsuranceCompany;
import com.coamctech.bxloan.entity.LandProduce;
import com.coamctech.bxloan.entity.MachineEquipmentMortgage;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.RealEstateMortgage;
import com.coamctech.bxloan.entity.RepayPlanTemp;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.entity.TrafficCar;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.entity.credit.CreditContract;
import com.coamctech.bxloan.service.approval.CreditContractApprovalService;
import com.coamctech.bxloan.service.bizapply.BizCreditApplicationService;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.collateral.CollateralService;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.creditcontractmng.CreditContractMngService;
import com.coamctech.bxloan.service.creditcontractmng.UnderCreditContractMngService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.approval.BizApprovalOfWfParam;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.bizapply.NewCommonBorrowerVO;
import com.coamctech.bxloan.service.model.credit.CreditContractVo;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TaskAction;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.businessapplicationwd.CollateralAndDetailVO;
import com.coamctech.bxloan.web.vo.contractmng.ContractSaveVO;
import com.coamctech.bxloan.web.vo.contractmng.ContractVO;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;

/**   
 * 类名称：UnderCreditContractMngController
 * 类描述 ：授信项下借款管理
 * 创建人: lbq 
 * 创建时间：2015年7月14日  
 */
@Controller
@RequestMapping(GlobalConstants.UNDER_CREDIT_CONTRACT_MNG)
public class UnderCreditContractMngController extends BaseController{
	@Autowired
	private BizCreditApplicationService bizCreditApplicationService;
	@Autowired
	private CreditContractMngService creditContractMngService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private CorporationCustomerService corporationCustomerService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private UnderCreditContractMngService underCreditContractMngService;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private BizRateDao bizRateDao;
	@Autowired
	private DocumentService documentService;
	@Autowired
	private CollateralService collateralService;
	@Autowired
	WorkFlowService workFlowService;
	@Autowired
	private OrgDeptmentService orgDeptmentService;
	@Autowired
	private IndustryTypeDao industryTypeDao;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private  WfActionTreeNodeService wfActionTreeNodeService;
	@Autowired
	private ContractMngService contractMngService;
	@Autowired
	private CreditContractApprovalService creditContractApprovalService;
	private final String CODETYPE_EMPLOYMENTTYPE = "EmploymentType";
	/** 合同状态 */
	private final String CODETYPE_CONTRACTSTATUS = "ContractStatusCode";
	
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
	/**
	 * 发起授信项下借款申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{partyId}/{creditContractId}")
	public String index(@PathVariable("partyId") Long partyId,
						@PathVariable("creditContractId") Long creditContractId, 
						Model model,
						HttpServletRequest request) {
		    StringBuffer nextPath = new StringBuffer("redirect:/");
			BusinessApplicationWdVO vo = null;
		try {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
			//查询授信合同信息
			CreditContract creditContract=creditContractMngService.getCreditContractById(Long.valueOf(creditContractId));
			Long parentProjectId=creditContract.getProjectId();
			Long productCd=Long.valueOf(creditContract.getProductType());
			//当前登录人信息
				SessionUser sessionUser = new SessionUser();
				sessionUser.setUserId(curUser.getId());
				sessionUser.setLoginName(curUser.getLogname());
				sessionUser.setUserNameCN(curUser.getName());
				sessionUser.setOrgId(curUser.getLogOrgid());
				sessionUser.setOrgName(curUser.getLogOrgDesc());
			
			/** 发起授信项下借款申请 */
			vo = bizCreditApplicationService.startMicroloanBusiness(
						productCd, 
						party, 
						sessionUser,
						WorkFlowService.WorkFlowCode.CREDIT_LOAN,
						WorkFlowNode.LA_EntryBusiApplInfo,
						dataDict.getCodeVal("ProjectBussinessType", "S3")
					);
			
				//组装流程与业务关系对象
				WfBusinessRelation wfBusinessRelation = new WfBusinessRelation();
				wfBusinessRelation.setWorkFlowType(WorkFlowCode.CREDIT_LOAN.getCodeId());
				wfBusinessRelation.setWorkFlowId(vo.getWorkflowId());
				wfBusinessRelation.setBusinessId(vo.getProjectId());
				wfBusinessRelation.setParentBusinessId(parentProjectId);
				wfBusinessRelation.setCreateTime(new Date());
				wfActionTreeNodeService.saveWfBusinessRelation(wfBusinessRelation);
			
				/** 拼接跳转路径 */
				nextPath.append(GlobalConstants.UNDER_CREDIT_CONTRACT_MNG).append("/editApplication/")
					    .append(WorkFlowCode.CREDIT_LOAN.getCodeId()).append("/")
					    .append(vo.getWorkflowId()).append("/")
					    .append(vo.getTaskId()).append("/")
					    .append(WorkFlowNode.LA_EntryBusiApplInfo.getId()).toString();
			return nextPath.toString();
		} catch (Exception e) {
			String msg = e.getMessage();
			if (msg.equals("startProcess")) {
				model.addAttribute("error", "<strong>启动流程失败，请联系管理员！</strong>");
			}
			else if (msg.equals("disposeTask")) {
				model.addAttribute("error", "<strong>任务处理失败，请联系管理员！</strong>");
			}
			e.printStackTrace();
		}
		return GlobalConstants.CREDIT_CONTRACT_MNG  + "/main";
	}
	/**
	 * Description 待办事项编辑跳转
	 * @param wfcode 流程类型代码
	 * @param workflowId 流程ID
	 * @param taskId 任务ID 
	 * @param taskStageCode 节点ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/editApplication/{wfCode}/{workflowId}/{taskId}/{taskStageCode}")
	public String editApplication(@PathVariable("wfCode") String wfCode, @PathVariable("workflowId") String workflowId,
			@PathVariable("taskId") String taskId, @PathVariable("taskStageCode") String taskStageCode, Model model) {
				//获取流程与业务关系对象
	  			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
				Long businessId = wfBusinessRelation.getBusinessId();
				Long parentProjectId=wfBusinessRelation.getParentBusinessId();
				//查询授信下借款业务基础信息
				ProjectApplication underCreditprojectInfo = businessApplicationService.searchProjectApplication(businessId);
				
			//如果业务状态不是0 则进入待办页面
			if (!dataDict.getCodeVal("ProjectStatus", "S0").equals(underCreditprojectInfo.getProjectStatus())) {
				return "redirect:/" + DASHBOARD;
			}
				 //获取按钮
				TypedResult<List<TaskAction>> buttons = null;
				buttons = this.workFlowService.getTaskActions(WorkFlowNode.LA_EntryBusiApplInfo);
			if (!buttons.getSuccess()) {
				throw new RuntimeException("getNextTaskActions");
			}
				//下一环节所有人
				List<NextTaskReceiver> receiverList = this.workFlowService.getNextTaskReceivers(taskId,WorkFlowService.ActionCode.COMMIT).getData();
				//存入流程相关信息
				BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
					businessApplicationWdVO.setWorkflowId(Long.valueOf(workflowId));
					businessApplicationWdVO.setTaskId(taskId);
					businessApplicationWdVO.setWfCode(wfCode);
					businessApplicationWdVO.setTaskStageCode(taskStageCode);
					businessApplicationWdVO.setButtons(buttons.getData());
					businessApplicationWdVO.setReceivers(receiverList);
				//查询相关业务信息，将相关信息fill到model和vo中
				this.searchBussiness(model, underCreditprojectInfo, businessApplicationWdVO,parentProjectId);
					model.addAttribute("type", "edit");  //页面为可编辑状态
		return GlobalConstants.CREDIT_CONTRACT_MNG + "/underCreditContractMng/detail/main";
	}
	/**从已办事项请求*/
	@RequestMapping("/checkApplication/{workflowId}")
	public String checkApplication(@PathVariable("workflowId") Long workflowId,Model model) {
			//获取流程与业务关系对象
			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(workflowId);
			//授信项下借款业务ID
			Long businessId = wfBusinessRelation.getBusinessId();
			//授信合同对应业务ID
			Long parentProjectId=wfBusinessRelation.getParentBusinessId();
		
		return this.forwardToCheckModel(businessId, parentProjectId, null, model);
	}
	/**
	 * 从右侧眼睛中请求查看业务信息
	 * @param projectId
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkApplicationWindow/{workflowId}/{consultLocation}")
	public String checkApplicationWindow(@PathVariable("workflowId") Long workflowId, @PathVariable("consultLocation") String consultLocation,Model model) {
			//获取流程与业务关系对象
			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(workflowId);
			//授信项下借款业务ID
			Long businessId = wfBusinessRelation.getBusinessId();
			//授信合同对应业务ID
			Long parentProjectId=wfBusinessRelation.getParentBusinessId();
		
		return this.forwardToCheckModel(businessId, parentProjectId, consultLocation, model);
	}
	/**跳转至查看业务信息页面*/
	@RequestMapping("/forwardToCheckModel")
	public String forwardToCheckModel(@RequestParam Long projectId, 
			@RequestParam Long parentProjectId,
			@RequestParam String consultLocation, Model model) {
			ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(projectId);
			BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
			//查询相关业务信息，将相关信息fill到model和vo中
			searchBussiness(model, projectApplication, businessApplicationWdVO,parentProjectId);
			model.addAttribute("consultLocation", consultLocation);
			model.addAttribute("type", "check");
		return GlobalConstants.CREDIT_CONTRACT_MNG + "/underCreditContractMng/detail/main";
	}
	/**
	 * 查询相关业务信息，将相关信息fill到model和vo中
	 * @param projectApplication,parentProjectId
	 * @param businessApplicationWdVO
	 */
	public void searchBussiness(Model model, ProjectApplication projectApplication, BusinessApplicationWdVO businessApplicationWdVO,Long parentProjectId){
			//授信合同信息
			CreditContract creditContract=creditContractMngService.findCreditContractByProjectId(parentProjectId);
			//授信合同关联业务申请信息
			ProjectApplication creditProjectInfo = businessApplicationService.searchProjectApplication(parentProjectId);
		//贷款用途
		if("".equals(projectApplication.getPurpose())||projectApplication.getPurpose()==null){
				projectApplication.setPurpose(creditProjectInfo.getPurpose());
			}
		//还款来源
		if("".equals(projectApplication.getPayment())||projectApplication.getPayment()==null){
				projectApplication.setPayment(creditProjectInfo.getPayment());
			}
		//申报日期
		if(projectApplication.getApplyDate() != null) {
			projectApplication.setApplyDateStr(CommonHelper.date2Str(projectApplication.getApplyDate(), CommonHelper.DF_DATE));
		}
			//参与人信息
			Party party = uniqueCustomerService.findPartyByPartyId(projectApplication.getPartyId().toString()); 
			CorporationCustomer corporationCustomer = null;
			Individual individual = null;
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerService.findById(party.getPartyId());
			String industryName = industryTypeDao.findIndustryNameByIndustryCd(corporationCustomer.getIndustryLevelTwoCd());
			model.addAttribute("industryName", industryName);
			model.addAttribute("corporationCustomer", corporationCustomer);
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = uniqueCustomerService.getIndividual(party.getPartyId().toString());
		}
			//利率信息
			BizRate bizRate = businessApplicationService.getBizRateByProjectId(projectApplication.getProjectId().toString());
			//产品信息
			String productCd = projectApplication.getProductType();  //产品类型
			ProductConfig productConfig = businessApplicationService  //产品配置信息
					.getProductConfigByProductCd(Long.valueOf(productCd),
							Long.valueOf(projectApplication.getApplyOrg()));
			model.addAttribute("productConfig", productConfig);
			Product product = businessApplicationService.findProductByProductCd(productCd);
			businessApplicationWdVO.setProduct(product);
			//产品列表
			List<Object[]> products = productPriceService.findProduct();
			//将客户信息fill到vo中
			BeanUtils.copyProperties(party.getPartyTypeCd().equals("1") ? corporationCustomer : individual, businessApplicationWdVO);
			//将业务信息fill到vo中
			BeanUtils.copyProperties(projectApplication, businessApplicationWdVO); 
			businessApplicationWdVO.setCurUserOrgId(Long.valueOf(projectApplication.getApplyOrg()));
			 //担保方式
			String guaranteeMode = businessApplicationWdVO.getGuaranteeMode(); 
		if(StringUtils.isNotBlank(guaranteeMode)) {
			businessApplicationWdVO.setGuaranteeMode(guaranteeMode + ",");
		}
		if (bizRate != null) {
			BeanUtils.copyProperties(bizRate, businessApplicationWdVO);  //利率信息
		}
		if (projectApplication.getApplyDate() != null) {
			businessApplicationWdVO.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").format(projectApplication.getApplyDate()));
		}
		if (businessApplicationWdVO.getBizRate() != null) {  //年利率
			businessApplicationWdVO.setBizRate(businessApplicationWdVO.getBizRate().multiply(new BigDecimal("100")));
		}
		if (businessApplicationWdVO.getIrNegoSymbCd() != null) {  //上浮比例
			businessApplicationWdVO.setIrNegoSymbCd(businessApplicationWdVO.getIrNegoSymbCd().multiply(new BigDecimal("100")));
		}
		if(businessApplicationWdVO.getOvdueRate() != null){//逾期利率上浮比例
			businessApplicationWdVO.setOvdueRate(businessApplicationWdVO.getOvdueRate().multiply(new BigDecimal("100")));
		}
		if(businessApplicationWdVO.getPerculNegoRate() != null){//挪用利率上浮比例
		   businessApplicationWdVO.setPerculNegoRate(businessApplicationWdVO.getPerculNegoRate().multiply(new BigDecimal("100")));
		}
		if (businessApplicationWdVO.getPreRepaymentRate() != null) {//提前还款违约金比例
			businessApplicationWdVO.setPreRepaymentRate(businessApplicationWdVO.getPreRepaymentRate().multiply(new BigDecimal("100")));
			
		}
		
			//保险机构
			List<InsuranceCompany> insuranceCompanys = businessApplicationService.findAllInsuranceCompany();
			 //期限单位
			String replyPeriodUnit= productConfig.getReplyPeriodUnit();
			String applyTermUnit= businessApplicationWdVO.getApplyTermUnit();
		if(StringUtils.isNoneBlank(replyPeriodUnit) && StringUtils.isBlank(applyTermUnit) ){ 
			businessApplicationWdVO.setApplyTermUnit(replyPeriodUnit);
		}
			String orgName = orgDeptmentService.findDeptNameById(Long.valueOf(projectApplication.getApplyOrg())); //所属机构
			 //收集需要反显信息
			model.addAttribute("insuranceCompanys",insuranceCompanys);
			model.addAttribute("vo", businessApplicationWdVO);
			model.addAttribute("products", products);
			model.addAttribute("productCd", productCd);
			model.addAttribute("party", party);
			model.addAttribute("allDocType", DocStageCode.YW_WD_DOC.getCodeId());
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());  //客户经理
			model.addAttribute("orgName", orgName); //所属机构
			model.addAttribute("creditProjectInfo", creditProjectInfo);
			model.addAttribute("creditContract", creditContract);
	}
	
	/**
	 * 保存授信借款基本项目信息
	 * 
	 * @param form creditContractId
	 * @return
	 */
	@RequestMapping("/saveUnderCreditLoan")
	@ResponseBody
	public Result saveUnderCreditLoan(BusinessApplicationWdVO form, @RequestParam Long creditContractId) {
		Long bizRateId = null;
		try {
				ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
				form.setCurUserOrgId(curUser.getLogOrgid());
				String validatorMsg = underCreditContractMngService.checkBusinessInfoBeforeSave(creditContractId, form);
			if(StringUtils.isNotBlank(validatorMsg)){
				return failure(validatorMsg);
			}
				bizRateId = underCreditContractMngService.saveUnderCreditLoanBusiness(form);
			return success("保存成功！", bizRateId);
		} catch (Exception e) {
			logger.error("报错失败，错误原因："+e.getMessage());
			return failure("保存失败！");
		}
	}
	
	/**************************** 费用列表相关方法  ********************************/
	/**
	 * Description 保存费用信息
	 * @return
	 */
	@RequestMapping("/saveBizExpenseRate")
	@ResponseBody
	public Result saveBizExpenseRate(BizExpenseRate form) {
		BizExpenseRate b = businessApplicationService.checkExpenseName(form);
		if (b != null && form.getBizExpenseRateId() == null) {
			return failure("此费用名称已被设置，请重新选择！");
		} else {
			if (form.getBizExpenseRateId() == null) {// 新增
				form.setSysCreateDate(new Date());
				form.setSysUpdateDate(new Date());
				businessApplicationService.saveBizExpenseRate(form);
			} else {//修改
				form.setSysUpdateDate(new Date());
				businessApplicationService.updateBizExpenseRate(form);
			}
			return success("费用信息保存成功！");
		}
	}
	/**
	 * 关联授信合同的费用列表的查询方法
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
	 */
	@RequestMapping("/searchBizExpenseRateList")
	@ResponseBody
	public DataTablesPage searchBizExpenseRateList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request) {
			//授信合同业务编号
			String underProjectNo = (String) request.getParameter("underProjectNo");
			//借款合同业务编号
			String creditProjectNo = (String) request.getParameter("creditProjectNo");
			List<Object> params = new ArrayList<Object>();
			params.add(underProjectNo);
			params.add(creditProjectNo);
			Page<Object[]> page = underCreditContractMngService.findUnderCreditExpenseRateByProject(creditProjectNo, underProjectNo, iDisplayStart/ iDisplayLength + 1, iDisplayLength, params);
			List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * Description 查询新增费用信息
	 * 
	 * @return
	 */
	@RequestMapping("/searchBizExpenseRate/{bizExpenseRateId}")
	@ResponseBody
	public Result searchBizExpensrRate(@PathVariable("bizExpenseRateId") Long bizExpenseRateId) {
		BizExpenseRate bizExpenseRate = businessApplicationService.getBizExpenseRateById(bizExpenseRateId);
		return success(bizExpenseRate);
	}
	/**
	 * Description 删除费用信息
	 * 
	 * @return
	 */
	@RequestMapping("/deleteBizExpenseRate/{bizExpenseRateId}")
	@ResponseBody
	public Result deleteBizExpenseRate(@PathVariable("bizExpenseRateId") Long id) {
		businessApplicationService.deleteBizExpenseRate(id);
		return success();

	}
	/**************************** 保证人列表相关方法  ********************************/
	/**
	 * 查询保证人列表
	 * 业务录入与签订合同
	 * @return
	 */
	@RequestMapping("/searchBailList")
	@ResponseBody
	public DataTablesPage searchBailList(Integer sEcho, Integer iDisplayStart,
			Integer iDisplayLength, String workflowId) {
			//获取流程与业务关系对象
			WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(Long.parseLong(workflowId));
			Long businessId = wfBusinessRelation.getBusinessId();
			Long parentProjectId=wfBusinessRelation.getParentBusinessId();
			Page<Object[]> page = underCreditContractMngService.searchBailList(iDisplayStart / iDisplayLength + 1, iDisplayLength, parentProjectId,businessId);
			List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * 查询可以被关联为保证人的客户列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param customerName
	 * @param customerNum
	 * @param certificateTypeCd
	 * @param certificateNum
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/searchCustomerForBail")
	@ResponseBody
	public DataTablesPage searchCustomerForBail(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String customerName,
			String customerNum, String certificateTypeCd,
			String certificateNum, Integer partyId, Long projectId) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Page<Object[]> page = businessApplicationService.searchCustomerForBailList(
						iDisplayStart / iDisplayLength + 1,iDisplayLength,
						curUser.getId().toString(), partyId,
						customerName, customerNum, certificateTypeCd,
						certificateNum, projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * 通过保证人ID得到此客户信息，目的：点击查看保证人信息时，判断此party的partyType，跳转至个人客户或企业客户查看页面
	 * 
	 * @param projectAssurerInfoId
	 * @return
	 */
	@RequestMapping("/getPartyByProjectAssurerInfoId")
	@ResponseBody
	public Party getPartyByProjectAssurerInfoId(Long projectAssurerInfoId) {
		return businessApplicationService.getPartyByProjectAssurerInfoId(projectAssurerInfoId);
	}
	/**
	 * 删除保证人信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteAssurer")
	@ResponseBody
	public Result deleteAssurer(Long id) {
		try {
			businessApplicationService.deleteProjectAssurerInfo(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}
	/**
	 * 保存保证人表
	 * 
	 * @param projectId
	 * @param partyId
	 * @param guaranteeAmt
	 * @return
	 */
	@RequestMapping("/saveProjectAssurerInfo")
	@ResponseBody
	public Result saveProjectAssurerInfo(Long projectId, String partyId,
			String guaranteeAmt, String bailMateBorrower) {
		if (StringUtils.isBlank(bailMateBorrower)) {
			bailMateBorrower = dataDict.getCodeVal("CommonWhether", "S2");
		}
		try {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

			ProjectAssurerInfo projectAssurerInfo = new ProjectAssurerInfo();

			Party party = uniqueCustomerService.findPartyByPartyId(partyId);
			if (party.getPartyTypeCd().equals("1")) {// 企业客户
				CorporationCustomer corporationCustomer = corporationCustomerService.findById(Long.valueOf(partyId));
				BeanUtils.copyProperties(corporationCustomer,projectAssurerInfo);
				projectAssurerInfo.setCertificateTypeCd("210");
			} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
				Individual individual = uniqueCustomerService.getIndividual(partyId);
				BeanUtils.copyProperties(individual, projectAssurerInfo);
			}

			ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(projectId);

				projectAssurerInfo.setProjectId(projectId);
				projectAssurerInfo.setProjectNo(projectApplication.getProjectNo());
				projectAssurerInfo.setGuaranteeAmt(new BigDecimal(guaranteeAmt));
				projectAssurerInfo.setCreateDate(new Date());
				projectAssurerInfo.setSysUpdateTime(new Date());
				projectAssurerInfo.setCreatePerson(curUser.getId().toString());
				projectAssurerInfo.setWifeGuarante(projectApplication.getMateBorrower());
				projectAssurerInfo.setAssureState("1");
				projectAssurerInfo.setCurrency("156");

			businessApplicationService.saveprojectAssurerInfo(projectAssurerInfo, bailMateBorrower);
		} catch (Exception e) {
			e.printStackTrace();
			String msg = "保存失败！";
			if (StringUtils.isNotBlank(e.getMessage())) {
				msg = e.getMessage();
			}
			return failure(msg);
		}
		return success("保存成功！");
	}
	/**
	 * 查看客户是否已婚
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/isMarried")
	@ResponseBody
	public Result isMarried(Long partyId) {
		try {
			businessApplicationService.isMarried(partyId);
			return success();
		} catch (Exception e) {
			return failure();
		}
	}
	/**************************** 共同借款人列表相关方法  ********************************/
	/**
	 * 查询共同借款人列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/searchCommonBorrowerList")
	@ResponseBody
	public DataTablesPage searchCommonBorrowerList(Integer sEcho,Integer iDisplayStart,
					Integer iDisplayLength, Long creditProjectId,Long underProjectId) {
		Page<Object[]> page = underCreditContractMngService.searchCommonBorrowerList(iDisplayStart / iDisplayLength + 1,iDisplayLength, creditProjectId, underProjectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * 查询可以被关联为共同借款人的客户列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param customerName
	 * @param customerNum
	 * @param certificateTypeCd
	 * @param certificateNum
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/searchCustomerForCommonBorrower")
	@ResponseBody
	public DataTablesPage searchCustomerForCommonBorrower(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String customerName,
			String customerNum, String certificateTypeCd,
			String certificateNum, Integer partyId, Long projectId) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Page<Object[]> page = businessApplicationService
				.searchCustomerForCommonBorrower(iDisplayStart / iDisplayLength
						+ 1, iDisplayLength, curUser.getId().toString(),
						partyId, customerName, customerNum, certificateTypeCd,
						certificateNum, projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * 通过共同借款人ID得到此客户，目的：点击查看保证人信息时，判断此party的partyType，跳转至个人客户或企业客户查看页面
	 * 
	 * @param commonBorrowerId
	 * @return
	 */
	@RequestMapping("/getPartyByCommonBorrowerId")
	@ResponseBody
	public Party getPartyByCommonBorrowerId(Long commonBorrowerId) {
		return businessApplicationService.getPartyByCommonBorrowerId(commonBorrowerId);
	}

	/**
	 * 通过共同借款人ID得到此联系人（查看保证人人（配偶）时用到）
	 * 
	 * @param commonBorrowerId
	 * @return
	 */
	@RequestMapping("/getSpouseByCommonBorrowerId")
	@ResponseBody
	public FamilyFriend getSpouseByCommonBorrowerId(Long commonBorrowerId) {
		return businessApplicationService.getSpouseByCommonBorrowerId(commonBorrowerId);
	}
	/**
	 * 删除共同借款人信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCommonBorrow")
	@ResponseBody
	public Result deleteCommonBorrow(Long id) {
		try {
			businessApplicationService.deleteCommonBorrow(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}/**
	 * 保存共同借款人
	 * 
	 * @param projectId
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/saveCommonBorrower")
	@ResponseBody
	public Result saveCommonBorrower(Long projectId, String partyId) {
		try {
			CommonBorrow commonBorrow = new CommonBorrow();
			Party party = uniqueCustomerService.findPartyByPartyId(partyId);
			if (party.getPartyTypeCd().equals("1")) {// 企业客户
				CorporationCustomer corporationCustomer = corporationCustomerService.findById(Long.valueOf(partyId));
				BeanUtils.copyProperties(corporationCustomer, commonBorrow);
				commonBorrow.setCertificateTypeCd("210");
			} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
				Individual individual = uniqueCustomerService.getIndividual(partyId);
				BeanUtils.copyProperties(individual, commonBorrow);
				commonBorrow.setMobilePhone(individual.getMobileTel());
				commonBorrow.setPhone(individual.getFamilyTel());
				commonBorrow.setAddress(individual.getCompanyAddress());
			}
			commonBorrow.setProjectId(projectId);
			commonBorrow.setFlag("1");
			commonBorrow.setSpouseflag("2");
			businessApplicationService.saveCommonBorrow(commonBorrow);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
		return success("保存成功！");
	}
	/**
	 * 用户新建共同借款人
	 * @param newCommonBorrowerVO
	 * @return
	 */
	@RequestMapping("/saveNewCommonBorrower")
	@ResponseBody
	public Result saveNewCommonBorrower(NewCommonBorrowerVO newCommonBorrowerVO) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		
		CustomerManagerTeam customerManagerTeam = new CustomerManagerTeam();
		customerManagerTeam.setOrgCd(curUser.getOrgid().toString());
		customerManagerTeam.setCreateDate(new Timestamp(new Date().getTime()));
		customerManagerTeam.setUserNum(curUser.getId().toString());
		customerManagerTeam.setManagerType("01");
		
		String addCertificateNum = newCommonBorrowerVO.getCertificateNum();
		String addCertificateType = dataDict.getCodeVal("CertificateType","S100");
		if (uniqueCustomerService.findPartyByCertificateNumAndCertificateType(
				addCertificateType, addCertificateNum) != null) {
			return failure("身份证号码已被登记过！请重新输入！");
		}
		try {
			businessApplicationService.saveNewCommonBorrower(newCommonBorrowerVO, customerManagerTeam);
			return success("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}
	/**************************** 文档相关方法  ********************************/
	/**
	 * Timestamp转化成String的方法
	 * @param st Timestamp类型的变量
	 * @return String 返回时间转换成的字符串
	 */
	public String timeStampToString(Timestamp st) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
		String str = "";
		if (st != null) {
			str = df.format(st);
		} else {
			str = df.format(new Date());
		}
		return str;
	}
	/**
	 * 文档（DocumentIndex）的查询方法
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
	 * @author lbq
	 * @lastModified lbq 
	 */
	@RequestMapping("/findDocuments")
	@ResponseBody
	public DataTablesPage findCustomerDocumentsBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("creditPartyId");
		String projectId = (String) request.getParameter("projectId");
		String creditProjectId = (String) request.getParameter("creditProjectId");
		String documentName = request.getParameter("query_documentName");
		String contentType = request.getParameter("query_contentType");
		List<Object> params = new ArrayList<Object>();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId);
		String customerName = "";
		if (party.getPartyTypeCd().equals("1"))// 企业客户
			customerName = corporationCustomerService.findById(Long.valueOf(partyId)).getCustomerName();
		else if (party.getPartyTypeCd().equals("2"))// 个人客户
			customerName = uniqueCustomerService.getIndividual(partyId).getCustomerName();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		params.add(partyId);
		params.add(projectId);
		params.add(documentName);
		params.add(contentType);
		params.add(creditProjectId);
		List<Object[]> finalList = new ArrayList<Object[]>();

		Page queryList = underCreditContractMngService.findDocumentIndexBySearch(firstIndex / pageSize + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		for (Object[] obj : resultList) {
			obj[4] = customerName;
			obj[6] = timeStampToString((Timestamp) obj[6]);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}
	/**
	 * 获取已经上传文档的种类
	 * 
	 * @param partyId
	 * @return List<String>
	 * */
	@RequestMapping("/findDocumentCustDocTypes")
	@ResponseBody
	public List<Object[]> findDocumentCustDocTypes(@RequestParam Long partyId,
			@RequestParam Long projectId) {
		Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
		String custDocTypeCd = "";
		if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S1"))) {
			custDocTypeCd = DocStageCode.QY_DOC.getCodeId();// 企业
		} else if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S2"))) {
			// 个人客户
			String employmentType = uniqueCustomerService.getEmploymentTypeByPartyId(partyId);
			if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
				custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); // 受薪
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))) {
				custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); // 农户
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
				custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); // 经营
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S4"))) {
				custDocTypeCd = DocStageCode.STUDENT_DOC.getCodeId(); // 学生
			}
		} else {
			custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
		}
		return documentService.findDocumentCustDocTypes(projectId, partyId,
				custDocTypeCd + ","+DocStageCode.YW_WD_DOC.getCodeId());// 微贷
	}
	/**
	 * 查找下载地址与文件名
	 * 
	 * @param documentId
	 *            文档Id
	 * @return List 对象
	 * @author lbq
	 * @lastModified lbq 
	 */
	@RequestMapping("/findDocumentPath")
	@ResponseBody
	public List<String> findDocumentPath(@RequestParam String documentId) {
		DocumentIndex documentIndex = uniqueCustomerService.findDocumentIndexByDocumentId(documentId);
		List<String> list = new ArrayList<String>();
		list.add(documentIndex.getDocumentRoute());
		list.add(documentIndex.getDocumentName());
		return list;
	}
	/**
	 * 根据文档Id删除文档
	 * 
	 * @param id
	 *            文档Id
	 * @return String 删除是否成功
	 * 
	 * @author lbq
	 * @lastModified lbq 
	 */
	@RequestMapping("/delDocument/{id}")
	@ResponseBody
	public String delDocument(@PathVariable String id) {
		DocumentIndex documentIndex = uniqueCustomerService.findDocumentIndexByDocumentId(id);
		if (documentIndex.getCreateTypeCd().equals("2")) {
			return "createTypeCdEquals2Error";
		} else {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			documentIndex.setSystemUpdateTime(new Timestamp(new Date().getTime()));
			documentIndex.setUserNum(curUser.getId().toString());
			documentIndex.setStatus("2");
			uniqueCustomerService.saveDocumentIndex(documentIndex);
			return "success";
		}
	}
	/**
	 * 新增文档前的参数获取
	 * 
	 * @param partyId
	 *            客户Id
	 * @param request
	 *            HttpServletRequest
	 * @return DocumentUploadVO 对象
	 */
	@RequestMapping("/beforeUpdate")
	@ResponseBody
	public DocumentUploadVO beforeUpdate(@RequestParam String partyId,
			@RequestParam String projectId, @RequestParam String uploadType,
			@RequestParam(required = false) String custDocTypeCd,
			HttpServletRequest request) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		documentUploadVO.setPartyId(partyId);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId);
		Individual individual = null;
		CorporationCustomer corporationCustomer = null;
		
		if (party.getPartyTypeCd().equals("1")) {// 企业
			corporationCustomer = corporationCustomerService.findById(Long.valueOf(partyId));
			documentUploadVO.setCustomerNum(corporationCustomer.getCustomerNum());
			documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(corporationCustomer.getCustomerNum(),"01"));
		} else if (party.getPartyTypeCd().equals("2")) {// 个人
			individual = uniqueCustomerService.getIndividual(partyId);
			documentUploadVO.setCustomerNum(individual.getCustomerNum());
			documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(individual.getCustomerNum(), "01"));
		}
		ProjectApplication project = businessApplicationService.searchProjectApplication(Long.valueOf(projectId));
		documentUploadVO.setUserNum(curUser.getId().toString());
		documentUploadVO.setCreateOrgCd(curUser.getOrgid().toString());
		documentUploadVO.setCreateDateTime(timeStampToString(new Timestamp(new Date().getTime())));
		documentUploadVO.setBizNum(project.getProjectNo());
		documentUploadVO.setBizId(projectId);
		if (uploadType.equals("documents")) {
			documentUploadVO.setDocumentType("02");
		} else if (uploadType.equals("loanApply")) {
			documentUploadVO.setDocumentType("06");
		} else {
			documentUploadVO.setDocumentType("02");
		}
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");
		return documentUploadVO;
	}
	/**
	 * 获取上传文档的种类
	 * 
	 * @param partyId
	 * @return Map<String,String>
	 * */
	@RequestMapping("/findUploadCustDocTypes")
	@ResponseBody
	public CustDocInfo findUploadCustDocTypes(@RequestParam Long partyId) {
		Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
		String custDocTypeCd = "";
		if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S1"))) {
			custDocTypeCd = DocStageCode.QY_DOC.getCodeId();// 企业
		} else if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S2"))) {
			// 个人客户
			String employmentType = uniqueCustomerService.getEmploymentTypeByPartyId(partyId);
			if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
				custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); // 受薪
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))) {
				custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); // 农户
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
				custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); // 经营
			}else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S4"))) {
					custDocTypeCd = DocStageCode.STUDENT_DOC.getCodeId(); // 学生
			} 
		} else {
			custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
		}
		return documentService.findUploadCustDocTypes(null, partyId,
				custDocTypeCd + "," + DocStageCode.YW_WD_DOC.getCodeId());// 微贷
	}
	
	/**************************** 抵质押相关方法  ********************************/
	/**
	 * 查询抵质押关联列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/searchProjectPawnInfoList")
	@ResponseBody
	public DataTablesPage searchProjectPawnInfoList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, Long creditProjectId,Long underProjectId) {
		Page<Object[]> page = underCreditContractMngService.searchUCProjectPawnInfoList(iDisplayStart / iDisplayLength + 1,iDisplayLength, creditProjectId ,underProjectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	@RequestMapping("/findCollateralBySearch")
	@ResponseBody
	public DataTablesPage findCollateralBySearch(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request, String guarantorName,
			String guarantyName, String guarantyStatusCd, String guaranteeType,
			String guaranteeMode, Long projectId) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

		Collateral collateral = new Collateral();
		collateral.setGuarantorName(guarantorName);
		collateral.setGuarantyName(guarantyName);
		collateral.setGuarantyStatusCd(guarantyStatusCd);
		collateral.setGuaranteeType(guaranteeType);
		Page<Object[]> page = collateralService.findBySearchForBiz(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, collateral,
				guaranteeMode, curUser.getOrgid(), projectId);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * 查看抵质押关联表，实际就是查看抵质押信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkPawn")
	@ResponseBody
	public CollateralAndDetailVO checkPawn(Long id) {
		ProjectPawnInfo projectPawnInfo = businessApplicationService.getProjectPawnInfo(id);
		Collateral collateral = collateralService.get(projectPawnInfo.getGuarantyId());
		collateral.setSysUpdateDateStr(new SimpleDateFormat("yyyy-MM-dd").format(collateral.getSysUpdateDate()));
		CollateralAndDetailVO collateralAndDetailVO = new CollateralAndDetailVO();
		BeanUtils.copyProperties(collateral, collateralAndDetailVO);
		Integer guarantyTypeCd = Integer.parseInt(collateral
				.getGuarantyTypeCd());
		switch (guarantyTypeCd) {
		case 0:// 房产
			RealEstateMortgage realEstateMortgage = collateralService.getRealEstateMortgage(collateral.getGuarantyId());
			realEstateMortgage.setConstructedDateStr(new SimpleDateFormat(
					"yyyy-MM-dd").format(realEstateMortgage.getConstructedDate()));
			BeanUtils.copyProperties(realEstateMortgage, collateralAndDetailVO);
			break;
		case 1:// 地产
			LandProduce landProduce = collateralService.getLandProduce(collateral.getGuarantyId());
			landProduce.setLandEndDateStr(new SimpleDateFormat("yyyy-MM-dd").format(landProduce.getLandEndDate()));
			BeanUtils.copyProperties(landProduce, collateralAndDetailVO);
			break;
		case 2:// 机器设备
			MachineEquipmentMortgage machineEquipmentMortgage = collateralService.getMachineEquipmentMortgage(collateral.getGuarantyId());
			BeanUtils.copyProperties(machineEquipmentMortgage,collateralAndDetailVO);
			break;
		case 3:// 机动车
			TrafficCar trafficCar = collateralService.getTrafficCar(collateral.getGuarantyId());
			trafficCar.setRegisterDateStr(new SimpleDateFormat("yyyy-MM-dd").format(trafficCar.getRegisterDate()));
			BeanUtils.copyProperties(trafficCar, collateralAndDetailVO);
			break;
		default:
			break;
		}
		return collateralAndDetailVO;
	}
	/**
	 * 删除抵质押关联表信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletePawn")
	@ResponseBody
	public Result deletePawn(Long id) {
		try {
			businessApplicationService.deleteProjectPawnInfo(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}
	/**
	 * 保存抵质押关联表
	 * 
	 * @param guarantyId
	 * @param appGuaDebtInterAmt
	 * @param pawnRatio
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/saveProjectPawnInfo")
	@ResponseBody
	public Result saveProjectPawnInfo(String guarantyId,
			String appGuaDebtInterAmt, String pawnRatio, Long projectId) {
		try {
			businessApplicationService.saveProjectPawnInfo(guarantyId,
					appGuaDebtInterAmt, pawnRatio, projectId);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
		return success("保存成功！");
	}
	@RequestMapping("/findCustomersBySearchForCollateral")
	@ResponseBody
	public DataTablesPage findCustomersBySearchForCollateral(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength,
			HttpServletRequest request) {
		String customerName = request.getParameter("customerName");
		String certificateTypeCd = request.getParameter("certificateTypeCd");
		String certificateNum = request.getParameter("certificateNum");
		Individual individual = new Individual();
		individual.setCustomerName(customerName);
		individual.setCertificateTypeCd(certificateTypeCd);
		individual.setCertificateNum(certificateNum);
		Page<Object[]> page = collateralService.findCustomersBySearchForBiz(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, individual);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	//提交申请前校验必填信息
	@RequestMapping("/checkBusinessInfoBeforeSendProcess")
	@ResponseBody
	public Result checkBusinessInfoBeforeSendProcess(Long projectId,
			String guaranteeMode) {
		try {
			underCreditContractMngService.checkBusinessInfoBeforeSendProcess(projectId, guaranteeMode,WorkFlowService.WorkFlowCode.CREDIT_LOAN.getCodeId());
			return success();
		} catch (Exception e) {
			//e.printStackTrace();
			logger.info(e.getMessage());
			return failure(e.getMessage());
		}
	}
	/**
	 * 提交申请
	 * 
	 * @param projectNo
	 * @param workflowId
	 * @param taskId
	 * @param opinion
	 * @param actionCode
	 * @param productCd
	 * @return
	 */
	@RequestMapping("/submitApply")
	@ResponseBody
	public Result submitApply(String projectNo, String workflowId,
			String taskId, String opinion, String actionCode, String productCd,
			String nextTaskReceiver) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		/**
		 * 发送流程
		 */
		try {
			ActionCode actCode = ActionCode.getActionCodeById(actionCode);
			businessApplicationService.sendProccessAndUpdateApplication(
					WorkFlowService.WorkFlowCode.CREDIT_LOAN, workflowId,
					taskId, curUser.getLogname(),
					WorkFlowNode.LA_EntryBusiApplInfo, actCode,
					nextTaskReceiver, opinion, "", GlobalConstants.WD_WF_TASK_ID_10);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			return failure(e.getMessage());
		}
	}
	/**************************** 贷款试算相关方法  ********************************/
	/**
	 * 查询贷款计算器的还款计划列表
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectNo
	 * @return
	 */
	@RequestMapping("/searchRepaymentPlanTempList")
	@ResponseBody
	public DataTablesPage searchRepaymentPlanTempList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String projectNo) {
		Page<Object[]> page = businessApplicationService.searchRepayPlanTempList(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, projectNo);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * 保存贷款计算器的还款计划
	 * @param form
	 * @return
	 */
	@RequestMapping("/saveRepayPlanTemp")
	@ResponseBody
	public Result saveRepayPlanTemp(RepayPlanTemp form) {
		try {
			if (form.getCustomerId() != null) {
				Party party = uniqueCustomerService.findPartyByPartyId(form.getCustomerId().toString());
				form.setCustomerName(party.getPartyName());
				form.setCustomerNum(party.getCustomerNum());
			}
			if (form.getProjectId() == null) {
				form.setProjectId(Long.valueOf(0));
			}
			form.setRepayDate(new SimpleDateFormat("yyyy-MM-dd").parse(form.getRepayDateStr()));
			form.setSysCreateDate(new Date());
			form.setSysUpdateDate(new Date());
			businessApplicationService.saveRepayPlanTemp(form);
			return success("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}
	/**
	 * 删除贷款计算器的还款计划
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRepayPlanTemp")
	@ResponseBody
	public Result deleteRepayPlanTemp(Long id) {
		try {
			businessApplicationService.deleteRepayPlanTemp(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}
	/**
	 * 修改贷款计算器还款计划
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getRepayPlanTemp")
	@ResponseBody
	public RepayPlanTemp getRepayPlanTemp(Long id) {
		RepayPlanTemp repayPlanTemp = businessApplicationService.getRepayPlanTemp(id);
		repayPlanTemp.setRepayDateStr(new SimpleDateFormat("yyyy-MM-dd").format(repayPlanTemp.getRepayDate()));
		return repayPlanTemp;
	}
	/**
	 * 查询还款计划列表
	 * 
	 * @param sEcho
	 * @param iDisplayStart
	 * @param iDisplayLength
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/searchRepaymentPlanList")
	@ResponseBody
	public DataTablesPage searchRepaymentPlanList(Integer sEcho,
			Integer iDisplayStart, Integer iDisplayLength, String projectNo) {
		Page<Object[]> page = businessApplicationService.searchRepayPlanList(
				iDisplayStart / iDisplayLength + 1, iDisplayLength, projectNo);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
	/**
	 * 保存还款计划
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/saveRepayPlan")
	@ResponseBody
	public Result saveRepayPlan(ApprovalHistoryRepayPlan form) {
		try {
			if (form.getCustomerId() != null) {
				Party party = uniqueCustomerService.findPartyByPartyId(form.getCustomerId().toString());
				form.setCustomerName(party.getPartyName());
				form.setCustomerNum(party.getCustomerNum());
			}
			if (form.getProjectId() == null) {
				form.setProjectId(Long.valueOf(0));
			}
			form.setRepayDate(new SimpleDateFormat("yyyy-MM-dd").parse(form.getRepayDateStr()));
			form.setSysCreateDate(new Date());
			form.setSysUpdateDate(new Date());
			businessApplicationService.saveRepayPlan(form);
			return success("保存成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}
	/**
	 * 修改还款计划
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getRepayPlan")
	@ResponseBody
	public ApprovalHistoryRepayPlan getRepayPlan(Long id) {
		ApprovalHistoryRepayPlan repayPlan = businessApplicationService.getRepayPlan(id);
		repayPlan.setRepayDateStr(new SimpleDateFormat("yyyy-MM-dd").format(repayPlan.getRepayDate()));
		return repayPlan;
	}
	/**
	 * 删除还款计划
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRepayPlan")
	@ResponseBody
	public Result deleteRepayPlan(Long id) {
		try {
			businessApplicationService.deleteRepayPlan(id);
			return success("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("删除失败！");
		}
	}
	/**
	 * 批量初始化还款计划
	 * 
	 * @param projectId
	 * @param applyAmt
	 * @param applyTerm
	 * @param applyTermUnit
	 * @param startDate
	 * @param monthDate
	 * @param repayDate
	 * @return
	 */
	@RequestMapping("/batchInitRepayPlan")
	@ResponseBody
	public Result batchInitRepayPlan(Long projectId, BigDecimal applyAmt,
			Integer applyTerm, String applyTermUnit, String startDate,
			Integer monthDate, Integer repayDateForCount) {
		try {
			businessApplicationService.insertRepayPlanList(projectId, applyAmt,
					applyTerm, applyTermUnit,
					new SimpleDateFormat("yyyy-MM-dd").parse(startDate),
					monthDate, repayDateForCount);
			return success("批量初始化成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return failure("批量初始化失败！");
		}
	}
	@RequestMapping("/cancelApply")
	@ResponseBody
	public Result cancelApply(Long projectId, String workflowId, String taskId) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

		return businessApplicationService.cancelApply(projectId, workflowId,
				taskId, curUser.getLogname(),
				WorkFlowService.WorkFlowCode.EASY_LOAN,
				WorkFlowNode.EL_EntryBusiApplInfo);
	}
	/**
	 * 获取保险费用
	 * 
	 * @param insuranceOrgId 保险机构
	 * @param applyAmt 申报金额
	 * @param bizRate  年利率
	 * @param applyDate 申报日期
	 * @param applyTerm 贷款期限
	 * @param applyTermUnit 贷款期限单位
	 * @param guaranteeMode 担保方式
	 * @return
	 */
	@RequestMapping("/getPrePremium")
	@ResponseBody
	public BigDecimal getPrePremium(Long insuranceOrgId, BigDecimal applyAmt,
			BigDecimal bizRate, String applyDate, String applyTerm,
			String applyTermUnit, String guaranteeMode, String irTypeCd) {
		return businessApplicationService.countPremiumFee(insuranceOrgId,
				applyAmt, bizRate, applyDate, applyTerm, applyTermUnit,
				guaranteeMode, irTypeCd);
	}
}

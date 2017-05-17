package com.coamctech.bxloan.web.controller.bizapply;

import static com.coamctech.bxloan.commons.GlobalConstants.BUSINESS_APPLICATION;
import static com.coamctech.bxloan.commons.GlobalConstants.BUSINESS_APPLICATION_WD;
import static com.coamctech.bxloan.commons.GlobalConstants.DASHBOARD;
import static com.coamctech.bxloan.commons.GlobalConstants.INID_MNG;
import static com.coamctech.bxloan.commons.GlobalConstants.RISKMNG_FLAG;

import java.io.File;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.enums.BizType;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.webservices.WebServices;
import com.coamctech.bxloan.datadict.CodeData;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.document.CustDocInfo;
import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.entity.FamilyAssetsDetail;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.IndustryType;
import com.coamctech.bxloan.entity.InsuranceCompany;
import com.coamctech.bxloan.entity.MonthDominateIncludeMeasure;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.entity.ProductPrice;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.QuotaMeasure;
import com.coamctech.bxloan.entity.SalaBusiCustFinalcial;
import com.coamctech.bxloan.entity.WfBusinessRelation;
import com.coamctech.bxloan.entity.UniteGuNego;
import com.coamctech.bxloan.entity.WorkDraft;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.common.BizApplyQueryService;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.customermng.CustomerManagerService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.customermng.UniteGuCustomerService;
import com.coamctech.bxloan.service.loanprocess.InterestCalCulateServiceForBusiness;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationVO;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.bizapply.EffectiveProductVO;
import com.coamctech.bxloan.service.model.bizapply.LoanCalVO;
import com.coamctech.bxloan.service.model.bizapply.QuotaMeasureVO;
import com.coamctech.bxloan.service.model.bizapply.WorkDraftVO;
import com.coamctech.bxloan.service.model.loanprocess.InterestCalCulateForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestVO;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TaskAction;
import com.coamctech.bxloan.service.model.workflow.TypedResult;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;
import com.coamctech.bxloan.service.workflow.WfActionTreeNodeService;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.ActionCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowNode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.bizapply.CoCustomerManager;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.service.customermng.UniteGuCustomerService;
import com.coamctech.bxloan.entity.UnCustTab;
import com.coamctech.bxloan.entity.UniteGuNego;
/**
 * @Description:
 * @author syy
 * @version V1.0
 * @Date 2014年7月25日
 */
@Controller
@RequestMapping("/" + BUSINESS_APPLICATION)
public class BusinessApplicationController extends BaseController {

	private final String CODETYPE_EMPLOYMENTTYPE = "EmploymentType";

	@Autowired
	private BusinessApplicationService businessApplicationService;
	
	@Autowired
	private UniqueCustomerService uniqueCustomerService;

	@Autowired
	private ProductPriceService productPriceService;

	@Autowired
	private DocumentService documentService;

	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;

	@Autowired
	private WebServices webServices;

	@Autowired
	private InterestCalCulateServiceForBusiness interestCalCulateServiceForBusiness;

	@Autowired
	private DataDict dataDict;

	@Autowired
	private CorporationCustomerService corporationCustomerService;

	@Autowired
	WorkFlowService workFlowService;
	
	@Autowired
	private BizApplyQueryService bizApplyQueryService;
	
	@Autowired
	private OrgDeptmentService orgDeptmentService;
	@Autowired
	private UniteGuCustomerService uniteGuCustomerService;
	
	//add by wangpeng on 2015-07-21 start
	@Autowired
	private CustomerManagerService customerManagerService;
	@Autowired
	private EcOrgPersonService ecOrgPersonService;
	//add by wangpeng on 2015-07-21 end
	
	@Autowired
	private WfActionTreeNodeService wfActionTreeNodeService;
	
	/** 易贷、微贷流程代码 */
	private final int EASY_LOAN = 1003;
	private final int MICRO_LOAN = 1004;

	/**
	 * Description 反显客户和贷款产品的一部分信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{partyId}/{productCd}/{businessType}")
	public String index(@PathVariable("partyId") Long partyId,
						@PathVariable("productCd") Long productCd, 
						@PathVariable("businessType") BizType businessType, 
						Model model,
						HttpServletRequest request) {
		// 判断业务类型
		switch (businessType) {
			case CreditBiz:
				return "redirect:/" + GlobalConstants.INIT_BIZ_CREDIT_APPLICATION + "/" + partyId + "/" + productCd;
			case BatchBiz:
				break;
		}
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		try {
			/**
			 * 防止用户刷新导致出现两条业务申请
			 * （已经没用了）
			 */
			boolean check = businessApplicationService.checkStatus(partyId);

			/**
			 * 检查当前登录人是否有权对此客户发起业务
			 */
			boolean checkManagerType = businessApplicationService.checkManagerType(partyId, curUser.getId().toString());

			if (check && checkManagerType) {
				Party party = uniqueCustomerService.findPartyByPartyId(partyId.toString());
				//modify by wangyawei 20150702 start 将所属机构修改为登陆机构
				ProductConfig productConfig = businessApplicationService.getProductConfigByProductCd(productCd, curUser.getLogOrgid());
				BusinessApplicationWdVO vo = null;
				if(productConfig==null){
					model.addAttribute("error", "当前登录机构无产品配置信息！");
					return INID_MNG;
				}
				switch (Integer.parseInt(productConfig.getWfCode())) {
				case EASY_LOAN:// 易贷流程

					vo = businessApplicationService.startEasyLoanBusiness(
							productCd, party, curUser.getLogname(),
							curUser.getId(), curUser.getName(),
							curUser.getLogOrgid(), curUser.getLogOrgDesc(), model);
					
					return "redirect:/" + BUSINESS_APPLICATION + "/editApplication/" + vo.getProjectId() + "/" + vo.getWorkflowId() + "/" + vo.getTaskId();
				case MICRO_LOAN:// 微贷流程
					
					vo = businessApplicationService.startMicroloanBusiness(productCd, 
																			party, 
																			curUser.getLogname(), 
																			curUser.getId(), 
																			curUser.getName(), 
																			curUser.getLogOrgid(), 
																			curUser.getLogOrgDesc());
					
					return "redirect:/" + BUSINESS_APPLICATION + "/editApplication/" + vo.getProjectId() + "/" + vo.getWorkflowId() + "/" + vo.getTaskId();
				default:
					return INID_MNG;
				}
				//modify by wangyawei 20150702 end
			} else if (!check) {
				model.addAttribute("error", "请从待办列表重新编辑此条业务数据。");
			} else if (!checkManagerType) {
				model.addAttribute("error", "<strong>您没有发起业务权限！</strong>");
			} else {
				model.addAttribute("error", "repeat");
			}
			return INID_MNG;

		} catch (Exception e) {
			e.printStackTrace();

			String msg = e.getMessage();
			if (msg.equals("startProcess")) {
				model.addAttribute("error", "<strong>启动流程失败，请联系管理员！</strong>");
			}
			else if (msg.equals("disposeTask")) {
				model.addAttribute("error", "<strong>任务处理失败，请联系管理员！</strong>");
			}
			else if (msg.equals("getNextTaskActions")) {
				model.addAttribute("error", "<strong>获取按钮失败，请联系管理员！</strong>");
			}
			else if (msg.equals("employmentType")) {
				model.addAttribute("error", "<strong>只允许授薪、经营类客户发起邦易贷产品业务！</strong>");
			}
			else if (msg.equals("childNum")) {
				model.addAttribute("error", "<strong>此客户无子女联系方式！</strong>");
			}
			else if (msg.equals("relationsNum")) {
				model.addAttribute("error", "<strong>此客户无除配偶、儿女外三位亲属的联系方式！</strong>");
			}
			else if (msg.equals("nonRelationsNum")) {
				model.addAttribute("error", "<strong>此客户无三位非亲属的联系方式！</strong>");
			}
			else {
				model.addAttribute("error", "<strong>" + e.getMessage() + "</strong>");
			}
			return INID_MNG;
		}
	}
	/**
	 * Description 获取农户联保体列表
	 * @param vo
	 * @param projectApplication
	 * @param model
	 * @author wangxy 20150625
	 * @return
	 */
	public String getUniteLoanModelParams(ProjectApplication pa,Model model){//TODO
		//获取农户包含该客户且是3-5人的联保体
		List<UniteGuNego> list = uniteGuCustomerService.findUgnCustListByPartyId(pa.getPartyId(), dataDict.getCodeVal("UnType", "S1"), 3, 5);
		model.addAttribute("unite",list);
		//根据联保体id查找联保体名称
		String unId = pa.getUnId();
		if(StringUtils.isNotBlank(unId)) {
//			UniteGuNego uniteGuNego = uniteGuCustomerService.findUniteCustomerById(Long.valueOf(unId));
//			vo.setUnGroupName(uniteGuNego.getUnGroupName());
//			model.addAttribute("unGroupName", vo.getUnGroupName());
			model.addAttribute("unIds", unId);
		}
		return unId;
	}

	/**
	 * Description 待办事项编辑跳转
	 * 
	 * @param projectId
	 * @param model
	 * @return
	 */
	@RequestMapping("/editApplication/{projectId}/{workflowId}/{taskId}")
	public String editApplication(HttpServletRequest request,
			@PathVariable("projectId") String projectId,
			@PathVariable("workflowId") String workflowId,
			@PathVariable("taskId") String taskId, Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();

		ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(Long.valueOf(projectId));
		if (!dataDict.getCodeVal("ProjectStatus", "S0").equals(projectApplication.getProjectStatus())) {
			return "redirect:/" + DASHBOARD;
		}
		
		if (projectApplication.getApplyDate() != null) {
			projectApplication.setApplyDateStr(CommonHelper.date2Str(projectApplication.getApplyDate(), CommonHelper.DF_DATE));
		}

		Party party = uniqueCustomerService.findPartyByPartyId(projectApplication.getPartyId().toString());

		CorporationCustomer corporationCustomer = null;
		Individual individual = null;
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerService.findById(party
					.getPartyId());
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = uniqueCustomerService.getIndividual(party.getPartyId()
					.toString());
		}

		BizRate bizRate = businessApplicationService
				.getBizRateByProjectId(projectApplication.getProjectId()
						.toString());

		String productCd = projectApplication.getProductType();

		BusinessApplicationWdVO vo = new BusinessApplicationWdVO();

		this.getUniteLoanModelParams(projectApplication, model);
		
		//add by wangpeng on 2015-07-21 start
		String personId = projectApplication.getAssistance();
		List<CoCustomerManager> assistancers = new ArrayList<CoCustomerManager>();//协办客户经理VO
		//如果没有选择协办客户经理则初始化一个默认值
		CoCustomerManager ccm = new CoCustomerManager();
		if(StringUtils.isBlank(personId)||StringUtils.isEmpty(personId)){
			ccm.setPersonId("");
			ccm.setPersonName("请选择协办客户经理");
			ccm.setIsSelected(false);
			assistancers.add(ccm);
		}
		//加载协办客户经理下拉列表
		List<EcOrgPerson> persons = customerManagerService.getCoCustomerManager(curUser.getLogOrgid(), 60170L,curUser.getId(),1,1);
		if(persons!=null&&persons.size()>0){
			for(EcOrgPerson eop : persons){
				CoCustomerManager cocm = new CoCustomerManager();
				if(StringUtils.isNotBlank(personId)&&StringUtils.isNotEmpty(personId)
						&&Long.valueOf(personId).compareTo(eop.getId())==0){
					cocm.setPersonId(eop.getId().toString());
					cocm.setPersonName(eop.getName());
					cocm.setIsSelected(true);
				}
				else{
					cocm.setPersonId(eop.getId().toString());
					cocm.setPersonName(eop.getName());
					cocm.setIsSelected(false);
				}
				assistancers.add(cocm);
			}
		}
		else{
			return "未找到协办客户经理信息";
		}
		model.addAttribute("assistancers",assistancers);
		//add by wangpeng on 2015-07-21 end

		TypedResult<List<TaskAction>> buttons = null;

		ProductConfig productConfig = businessApplicationService
				.getProductConfigByProductCd(Long.valueOf(productCd),
						Long.valueOf(projectApplication.getApplyOrg()));
		model.addAttribute("productConfig", productConfig);

		Product product = businessApplicationService
				.findProductByProductCd(productCd);
		
		//add by mz 20150703 start
		String orgName = orgDeptmentService.findDeptNameById(Long.valueOf(projectApplication.getApplyOrg()));
		//add by mz 20150703 end
		
		switch (Integer.parseInt(productConfig.getWfCode())) {
		case EASY_LOAN: {// 易贷流程

			buttons = this.workFlowService
					.getTaskActions(WorkFlowNode.EL_EntryBusiApplInfo);
			if (!buttons.getSuccess()) {
				model.addAttribute("error", "buttons");
				return DASHBOARD + "/main";
			}
			QuotaMeasure qm = businessApplicationService
					.searchQuotaMeasure(Long.valueOf(projectId));

			ProductPrice productPrice = new ProductPrice();
			productPrice.setLoanProduct(projectApplication.getProductType());
			productPrice = productPriceService.findByLoanProduct(productPrice);
			// 还款方式
			if (productPrice != null) {
				List<Object[]> repayingModes = businessApplicationService.findRepayingModesByProductPrice(productPrice);
				model.addAttribute("repayingModes", repayingModes);
			}

			vo.setButtons(buttons.getData());
			model.addAttribute("allDocType", DocStageCode.YW_YD_DOC.getCodeId());
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("quotaMeasure", qm);
			model.addAttribute("curUserLogOrgid", curUser.getLogOrgid());
			model.addAttribute("curUserLogOrgname", curUser.getLogOrgname());
			model.addAttribute("judgeType", "edit");
			model.addAttribute("projectApplication", projectApplication);
			model.addAttribute("party", party);
			model.addAttribute("individual", individual);
			model.addAttribute("productPrice", productPrice);
			model.addAttribute("bizRate", bizRate);
			model.addAttribute("workflowId", workflowId);
			model.addAttribute("taskId", taskId);
			model.addAttribute("vo", vo);
			model.addAttribute("productCd", productCd);
			model.addAttribute("product", product);
			
			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			
			return BUSINESS_APPLICATION + "/main";
		}
		case MICRO_LOAN: {// 微贷流程
			/**
			 * 获取按钮
			 */
			buttons = this.workFlowService.getTaskActions(WorkFlowNode.ML_EntryBusiApplInfo);
			if (!buttons.getSuccess()) {
				throw new RuntimeException("getNextTaskActions");
			}

			/**
			 * 下一环节所有人
			 */
			List<NextTaskReceiver> receiverList = this.workFlowService
					.getNextTaskReceivers(taskId,
							WorkFlowService.ActionCode.COMMIT).getData();

			/**
			 * fill VO
			 */
			BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();

			BeanUtils.copyProperties(
					party.getPartyTypeCd().equals("1") ? corporationCustomer
							: individual, businessApplicationWdVO);
			
			BeanUtils.copyProperties(projectApplication, businessApplicationWdVO);
			
			String guaranteeMode = businessApplicationWdVO.getGuaranteeMode();
			if(StringUtils.isNotBlank(guaranteeMode)) {
				businessApplicationWdVO.setGuaranteeMode(guaranteeMode + ",");
			}
			
			if (bizRate != null) {
				BeanUtils.copyProperties(bizRate, businessApplicationWdVO);
			}
			if (projectApplication.getApplyDate() != null) {
				businessApplicationWdVO
						.setApplyDate(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));

			}
			if (businessApplicationWdVO.getBizRate() != null
					&& businessApplicationWdVO.getIrTypeCd()
							.equals(dataDict.getCodeVal(
									"InterestRateAdjustment", "S1"))) {
				businessApplicationWdVO.setBizRate(businessApplicationWdVO
						.getBizRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getIrNegoSymbCd() != null) {
				businessApplicationWdVO.setIrNegoSymbCd(businessApplicationWdVO
						.getIrNegoSymbCd().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getOvdueRate() != null) {
				businessApplicationWdVO.setOvdueRate(businessApplicationWdVO
						.getOvdueRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPerculNegoRate() != null) {
				businessApplicationWdVO
						.setPerculNegoRate(businessApplicationWdVO
								.getPerculNegoRate().multiply(
										new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPreRepaymentRate() != null) {
				businessApplicationWdVO
						.setPreRepaymentRate(businessApplicationWdVO
								.getPreRepaymentRate().multiply(
										new BigDecimal("100")));
			}
			businessApplicationWdVO.setWorkflowId(Long.valueOf(workflowId));
			businessApplicationWdVO.setTaskId(taskId);
			businessApplicationWdVO.setButtons(buttons.getData());
			businessApplicationWdVO.setReceivers(receiverList);
			businessApplicationWdVO.setProduct(product);

			/**
			 * 产品列表
			 */
			List<Object[]> products = productPriceService.findProduct();

			/**
			 * 担保方式列表
			 */
			Collection<CodeData> guaranteeModes = dataDict.getOneType("CdsGuarantMode");

			/**
			 * 财务信息
			 */
			SalaBusiCustFinalcial salaBusiCustFinalcial = businessApplicationService
					.getFinanceByProjectId(projectId);

			/**
			 * 保险机构
			 */
			List<InsuranceCompany> insuranceCompanys = businessApplicationService
					.findAllInsuranceCompany();
			
			/**
			 * 帮农贷、团结贷
			 */
			//根据产品代码判断是不是农贷
			final boolean farmerLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, projectApplication.getProductType());
			final boolean unityLoanProductMark = businessApplicationService.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, projectApplication.getProductType());
			if(farmerLoanProductMark||unityLoanProductMark) {
				String type = bizApplyQueryService.findAgroBizTypeByProjId(businessApplicationWdVO.getProjectId());
				
				model.addAttribute("industryTypeCd", businessApplicationWdVO.getIndustryCd());
				model.addAttribute("customerId", party.getPartyId());
				model.addAttribute("agriculturalLoan", Boolean.TRUE);
				model.addAttribute("bizType", type);
			}
			model.addAttribute("vo", businessApplicationWdVO);//TODO
			model.addAttribute("salaBusiCustFinalcial", salaBusiCustFinalcial);
			model.addAttribute("products", products);
			model.addAttribute("guaranteeModes", guaranteeModes);
			model.addAttribute("productCd", productCd);
			model.addAttribute("party", party);
			model.addAttribute("insuranceCompanys", insuranceCompanys);
			model.addAttribute("allDocType", DocStageCode.YW_WD_DOC.getCodeId());
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("type", "edit");

			//农贷

			model.addAttribute("industryTypeCd", businessApplicationWdVO.getIndustryCd());
			model.addAttribute("customerId", party.getPartyId());
			
			// 2015-1-26
			Collection<CodeData> termUnitCds = dataDict
					.getOneType("TermUnitCd");
			Collection<CodeData> newTermUnitCds = new ArrayList<CodeData>();

			Iterator<CodeData> it = termUnitCds.iterator();
			while (it.hasNext()) {
				CodeData codeData = it.next();
				if (!dataDict.getCodeVal("TermUnitCd", "S1").equals(
						codeData.getCodeValue())) {
					newTermUnitCds.add(codeData);
				}
			}
			model.addAttribute("termUnits", newTermUnitCds);

			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			
			//2015-04-25 shenzuoxin 
			String replyPeriodUnit= productConfig.getReplyPeriodUnit();
			String applyTermUnit= businessApplicationWdVO.getApplyTermUnit();
			if(StringUtils.isNoneBlank(replyPeriodUnit) && StringUtils.isBlank(applyTermUnit) ){
				businessApplicationWdVO.setApplyTermUnit(replyPeriodUnit);
			}
			return BUSINESS_APPLICATION_WD + "/main";
		}
		default:
			return "";
		}
	}

	/**
	 * 待办事项查看跳转Window
	 * 
	 * @param projectId
	 * @param consultLocation
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkApplicationWindow")
	@ResponseBody
	public String checkApplicationWindow(@RequestParam String projectId,
			@RequestParam String consultLocation, Model model) {
		return BUSINESS_APPLICATION + "/forwardToCheckModel?projectId="
				+ projectId + "&consultLocation=" + consultLocation;
	}

	/**
	 * 待办事项查看跳转
	 * 
	 * @param projectId
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkApplication/{id}/{sign}")
	public String checkApplication(@PathVariable("id") Long id,
			@PathVariable("sign") String sign, Model model) {
		if (sign.equals("taskDone")) {// 从已办事项请求
			id = businessApplicationService.findProjectApplicationByWorkflowId(
					id).getProjectId();
		}
		// add by HWL 20150813 start  跳转来源
		model.addAttribute("jumpSource", sign);
		// add by HWL 20150813 end
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		ProjectApplication projectApplication = businessApplicationService
				.searchProjectApplication(id);
		Product product = productPriceService
				.getProductByProductCd(projectApplication.getProductType());

		Party party = uniqueCustomerService
				.findPartyByPartyId(projectApplication.getPartyId().toString());

		CorporationCustomer corporationCustomer = null;
		Individual individual = null;
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerService.findById(party
					.getPartyId());
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = uniqueCustomerService.getIndividual(party.getPartyId()
					.toString());
		}
		this.getUniteLoanModelParams(projectApplication, model);
		BizRate bizRate = businessApplicationService
				.getBizRateByProjectId(projectApplication.getProjectId()
						.toString());

		ProductConfig productConfig = businessApplicationService
				.getProductConfigByProductCd(product.getProductCd(),
						Long.valueOf(projectApplication.getApplyOrg()));
		model.addAttribute("productConfig", productConfig);

		//add by wangpeng on 2015-07-29 start
		String personId = projectApplication.getAssistance();
		List<CoCustomerManager> assistancers = new ArrayList<CoCustomerManager>();//协办客户经理VO
		//如果没有选择协办客户经理则初始化一个默认值
		CoCustomerManager ccm = new CoCustomerManager();
		if(StringUtils.isBlank(personId)||StringUtils.isEmpty(personId)){
			ccm.setPersonId("");
			ccm.setPersonName("请选择协办客户经理");
			ccm.setIsSelected(false);
			assistancers.add(ccm);
		}
		else{
			ccm.setPersonId(personId);
			EcOrgPerson person = ecOrgPersonService.findById(Long.valueOf(personId));
			ccm.setPersonName(person.getName());
			ccm.setIsSelected(true);
			assistancers.add(ccm);
		}
		model.addAttribute("assistancers",assistancers);
		//add by wangpeng on 2015-07-29 end
		
		//add by mz 20150703 start
		String orgName = orgDeptmentService.findDeptNameById(Long.valueOf(projectApplication.getApplyOrg()));
		//add by mz 20150703 end
				
		switch (Integer.parseInt(productConfig.getWfCode())) {
		case EASY_LOAN:// 易贷流程
			if (projectApplication.getApplyDate() != null) {
				projectApplication
						.setApplyDateStr(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));
			}

			QuotaMeasure qm = businessApplicationService.searchQuotaMeasure(id);
			model.addAttribute("quotaMeasure", qm);
			model.addAttribute("judgeType", "check");
			model.addAttribute("curUserLogOrgid", curUser.getLogOrgid());
			model.addAttribute("curUserLogOrgname", curUser.getLogOrgname());
			model.addAttribute("projectApplication", projectApplication);
			model.addAttribute("party", party);
			model.addAttribute("individual", individual);
			model.addAttribute("bizRate", bizRate);
			model.addAttribute("product", product);

			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			
			return BUSINESS_APPLICATION + "/main";
		case MICRO_LOAN:// 微贷流程
			/**
			 * fill VO
			 */
			BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
			BeanUtils.copyProperties(
					party.getPartyTypeCd().equals("1") ? corporationCustomer
							: individual, businessApplicationWdVO);
			BeanUtils.copyProperties(projectApplication,
					businessApplicationWdVO);
			if (bizRate != null) {
				BeanUtils.copyProperties(bizRate, businessApplicationWdVO);
			}
			if (projectApplication.getApplyDate() != null) {
				businessApplicationWdVO
						.setApplyDate(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));

			}
			if (businessApplicationWdVO.getBizRate() != null) {
				businessApplicationWdVO.setBizRate(businessApplicationWdVO
						.getBizRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getIrNegoSymbCd() != null) {
				businessApplicationWdVO.setIrNegoSymbCd(businessApplicationWdVO
						.getIrNegoSymbCd().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getOvdueRate() != null) {
				businessApplicationWdVO.setOvdueRate(businessApplicationWdVO
						.getOvdueRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPerculNegoRate() != null) {
				businessApplicationWdVO
						.setPerculNegoRate(businessApplicationWdVO
								.getPerculNegoRate().multiply(
										new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPreRepaymentRate() != null) {
				businessApplicationWdVO
						.setPreRepaymentRate(businessApplicationWdVO
								.getPreRepaymentRate().multiply(
										new BigDecimal("100")));
			}

			/**
			 * 产品列表
			 */
			List<Object[]> products = productPriceService.findProduct();

			/**
			 * 担保方式列表
			 */
			Collection<CodeData> guaranteeModes = dataDict
					.getOneType("CdsGuarantMode");

			/**
			 * 财务信息
			 */
			SalaBusiCustFinalcial salaBusiCustFinalcial = businessApplicationService
					.getFinanceByProjectId(id.toString());

			/**
			 * 保险机构
			 */
			List<InsuranceCompany> insuranceCompanys = businessApplicationService
					.findAllInsuranceCompany();
			
			/**
			 * 帮农贷
			 */
			//根据产品代码判断是不是农贷
			final boolean farmerLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, projectApplication.getProductType());//StringUtils.equals(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, pa.getProductType());
			//根据团结贷
			final boolean unityLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, projectApplication.getProductType());
			if(farmerLoanProductMark||unityLoanProductMark){
				String type = bizApplyQueryService.findAgroBizTypeByProjId(businessApplicationWdVO.getProjectId());
				
				model.addAttribute("industryTypeCd", businessApplicationWdVO.getIndustryCd());
				model.addAttribute("customerId", party.getPartyId());
				model.addAttribute("agriculturalLoan", Boolean.TRUE);
				model.addAttribute("bizType", type);
				
			}

			model.addAttribute("vo", businessApplicationWdVO);
			model.addAttribute("salaBusiCustFinalcial", salaBusiCustFinalcial);
			model.addAttribute("products", products);
			model.addAttribute("guaranteeModes", guaranteeModes);
			model.addAttribute("productCd", projectApplication.getProductType());
			model.addAttribute("party", party);
			model.addAttribute("insuranceCompanys", insuranceCompanys);
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("type", "check");

			// 2015-1-26
			Collection<CodeData> termUnitCds = dataDict
					.getOneType("TermUnitCd");
			Collection<CodeData> newTermUnitCds = new ArrayList<CodeData>();

			Iterator<CodeData> it = termUnitCds.iterator();
			while (it.hasNext()) {
				CodeData codeData = it.next();
				if (!dataDict.getCodeVal("TermUnitCd", "S1").equals(
						codeData.getCodeValue())) {
					newTermUnitCds.add(codeData);
				}
			}
			model.addAttribute("termUnits", newTermUnitCds);

			//add by mz 20150706 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150706 end
			
			return BUSINESS_APPLICATION_WD + "/main";
		default:
			return "";
		}
	}

	@RequestMapping("/forwardToCheckModel")
	public String forwardToCheckModel(@RequestParam String projectId,
			@RequestParam String consultLocation, Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		ProjectApplication projectApplication = businessApplicationService
				.searchProjectApplication(Long.valueOf(projectId));
		Product product = productPriceService
				.getProductByProductCd(projectApplication.getProductType());

		BizRate bizRate = businessApplicationService
				.getBizRateByProjectId(projectApplication.getProjectId()
						.toString());
		Party party = uniqueCustomerService
				.findPartyByPartyId(projectApplication.getPartyId().toString());

		CorporationCustomer corporationCustomer = null;
		Individual individual = null;
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerService.findById(party
					.getPartyId());
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = uniqueCustomerService.getIndividual(party.getPartyId()
					.toString());
		}
		this.getUniteLoanModelParams(projectApplication, model);
		ProductConfig productConfig = businessApplicationService
				.getProductConfigByProductCd(product.getProductCd(),
						Long.valueOf(projectApplication.getApplyOrg()));
		//add by mz 20150703 start
		String orgName = orgDeptmentService.findDeptNameById(Long.valueOf(projectApplication.getApplyOrg()));
		//add by mz 20150703 end
		//add by wangpeng on 2015-07-29 start
		String personId = projectApplication.getAssistance();
		List<CoCustomerManager> assistancers = new ArrayList<CoCustomerManager>();//协办客户经理VO
		//如果没有选择协办客户经理则初始化一个默认值
		CoCustomerManager ccm = new CoCustomerManager();
		if(StringUtils.isBlank(personId)||StringUtils.isEmpty(personId)){
			ccm.setPersonId("");
			ccm.setPersonName("请选择协办客户经理");
			ccm.setIsSelected(false);
			assistancers.add(ccm);
		}
		else{
			ccm.setPersonId(personId);
			EcOrgPerson person = ecOrgPersonService.findById(Long.valueOf(personId));
			ccm.setPersonName(person.getName());
			ccm.setIsSelected(true);
			assistancers.add(ccm);
		}
		model.addAttribute("assistancers",assistancers);
		//add by wangpeng on 2015-07-29 end
		switch (Integer.parseInt(productConfig.getWfCode())) {
		case EASY_LOAN:// 易贷流程
			if (projectApplication.getApplyDate() != null) {
				projectApplication
						.setApplyDateStr(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));
			}

			QuotaMeasure qm = businessApplicationService
					.searchQuotaMeasure(Long.valueOf(projectId));
			model.addAttribute("quotaMeasure", qm);
			model.addAttribute("judgeType", "check");
			model.addAttribute("curUserLogOrgid", curUser.getLogOrgid());
			model.addAttribute("curUserLogOrgname", curUser.getLogOrgname());
			model.addAttribute("projectApplication", projectApplication);
			model.addAttribute("individual", individual);
			model.addAttribute("party", party);
			model.addAttribute("bizRate", bizRate);
			model.addAttribute("consultLocation", consultLocation);
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("product", product);
			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			return BUSINESS_APPLICATION + "/main";
		case MICRO_LOAN:// 微贷流程
			/**
			 * fill VO
			 */
			BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
			BeanUtils.copyProperties(
					party.getPartyTypeCd().equals("1") ? corporationCustomer
							: individual, businessApplicationWdVO);
			BeanUtils.copyProperties(projectApplication,
					businessApplicationWdVO);
			if (bizRate != null) {
				BeanUtils.copyProperties(bizRate, businessApplicationWdVO);
			}
			if (projectApplication.getApplyDate() != null) {
				businessApplicationWdVO
						.setApplyDate(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));

			}
			if (businessApplicationWdVO.getBizRate() != null) {
				businessApplicationWdVO.setBizRate(businessApplicationWdVO
						.getBizRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getIrNegoSymbCd() != null) {
				businessApplicationWdVO.setIrNegoSymbCd(businessApplicationWdVO
						.getIrNegoSymbCd().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getOvdueRate() != null) {
				businessApplicationWdVO.setOvdueRate(businessApplicationWdVO
						.getOvdueRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPerculNegoRate() != null) {
				businessApplicationWdVO
						.setPerculNegoRate(businessApplicationWdVO
								.getPerculNegoRate().multiply(
										new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPreRepaymentRate() != null) {
				businessApplicationWdVO
						.setPreRepaymentRate(businessApplicationWdVO
								.getPreRepaymentRate().multiply(
										new BigDecimal("100")));
			}

			/**
			 * 产品列表
			 */
			List<Object[]> products = productPriceService.findProduct();

			/**
			 * 担保方式列表
			 */
			Collection<CodeData> guaranteeModes = dataDict
					.getOneType("CdsGuarantMode");

			/**
			 * 财务信息
			 */
			SalaBusiCustFinalcial salaBusiCustFinalcial = businessApplicationService
					.getFinanceByProjectId(projectId);

			/**
			 * 保险机构
			 */
			List<InsuranceCompany> insuranceCompanys = businessApplicationService
					.findAllInsuranceCompany();
			
			/**
			 * 帮农贷
			 */
			//根据产品代码判断是不是农贷
			final boolean farmerLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, projectApplication.getProductType());
			//根据团结贷
			final boolean unityLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, projectApplication.getProductType());
			if(farmerLoanProductMark||unityLoanProductMark){
				String type = bizApplyQueryService.findAgroBizTypeByProjId(businessApplicationWdVO.getProjectId());
				
				model.addAttribute("industryTypeCd", businessApplicationWdVO.getIndustryCd());
				model.addAttribute("customerId", party.getPartyId());
				model.addAttribute("agriculturalLoan", Boolean.TRUE);
				model.addAttribute("bizType", type);
			}

			model.addAttribute("vo", businessApplicationWdVO);
			model.addAttribute("salaBusiCustFinalcial", salaBusiCustFinalcial);
			model.addAttribute("products", products);
			model.addAttribute("guaranteeModes", guaranteeModes);
			model.addAttribute("productCd", projectApplication.getProductType());
			model.addAttribute("party", party);
			model.addAttribute("insuranceCompanys", insuranceCompanys);
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("type", "check");
			model.addAttribute("consultLocation", consultLocation);

			// 2015-1-26
			Collection<CodeData> termUnitCds = dataDict
					.getOneType("TermUnitCd");
			Collection<CodeData> newTermUnitCds = new ArrayList<CodeData>();

			Iterator<CodeData> it = termUnitCds.iterator();
			while (it.hasNext()) {
				CodeData codeData = it.next();
				if (!dataDict.getCodeVal("TermUnitCd", "S1").equals(
						codeData.getCodeValue())) {
					newTermUnitCds.add(codeData);
				}
			}
			model.addAttribute("termUnits", newTermUnitCds);
			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			return BUSINESS_APPLICATION_WD + "/main";
		default:
			return "";
		}
	}

	@RequestMapping("/showDetailByProjectNo")
	public String showDetailByProjectNo(String projectNo, Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		ProjectApplication projectApplication = businessApplicationService
				.getProjectApplicationByProjectNo(projectNo);
		Product product = productPriceService
				.getProductByProductCd(projectApplication.getProductType());

		Party party = uniqueCustomerService
				.findPartyByPartyId(projectApplication.getPartyId().toString());

		CorporationCustomer corporationCustomer = null;
		Individual individual = null;
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerService.findById(party
					.getPartyId());
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = uniqueCustomerService.getIndividual(party.getPartyId()
					.toString());
		}

		BizRate bizRate = businessApplicationService
				.getBizRateByProjectId(projectApplication.getProjectId()
						.toString());

		ProductConfig productConfig = businessApplicationService
				.getProductConfigByProductCd(product.getProductCd(),
						Long.valueOf(projectApplication.getApplyOrg()));
		//add by mz 20150703 start
		String orgName = orgDeptmentService.findDeptNameById(Long.valueOf(projectApplication.getApplyOrg()));
		//add by mz 20150703 end
		switch (Integer.parseInt(productConfig.getWfCode())) {
		case EASY_LOAN:// 易贷流程
			if (projectApplication.getApplyDate() != null) {
				projectApplication
						.setApplyDateStr(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));
			}

			QuotaMeasure qm = businessApplicationService
					.searchQuotaMeasure(projectApplication.getProjectId());
			model.addAttribute("quotaMeasure", qm);
			model.addAttribute("judgeType", "check");
			model.addAttribute("curUserLogOrgid", curUser.getLogOrgid());
			model.addAttribute("curUserLogOrgname", curUser.getLogOrgname());
			model.addAttribute("projectApplication", projectApplication);
			model.addAttribute("party", party);
			model.addAttribute("individual", individual);
			model.addAttribute("bizRate", bizRate);
			model.addAttribute("product", product);
			model.addAttribute("consultLocation", "consultLocation");

			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			
			return BUSINESS_APPLICATION + "/main";
		case MICRO_LOAN:// 微贷流程
			/**
			 * fill VO
			 */
			BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
			BeanUtils.copyProperties(
					party.getPartyTypeCd().equals("1") ? corporationCustomer
							: individual, businessApplicationWdVO);
			BeanUtils.copyProperties(projectApplication,
					businessApplicationWdVO);
			if (bizRate != null) {
				BeanUtils.copyProperties(bizRate, businessApplicationWdVO);
			}
			if (projectApplication.getApplyDate() != null) {
				businessApplicationWdVO
						.setApplyDate(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));

			}
			if (businessApplicationWdVO.getBizRate() != null) {
				businessApplicationWdVO.setBizRate(businessApplicationWdVO
						.getBizRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getIrNegoSymbCd() != null) {
				businessApplicationWdVO.setIrNegoSymbCd(businessApplicationWdVO
						.getIrNegoSymbCd().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getOvdueRate() != null) {
				businessApplicationWdVO.setOvdueRate(businessApplicationWdVO
						.getOvdueRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPerculNegoRate() != null) {
				businessApplicationWdVO
						.setPerculNegoRate(businessApplicationWdVO
								.getPerculNegoRate().multiply(
										new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPreRepaymentRate() != null) {
				businessApplicationWdVO
						.setPreRepaymentRate(businessApplicationWdVO
								.getPreRepaymentRate().multiply(
										new BigDecimal("100")));
			}

			/**
			 * 产品列表
			 */
			List<Object[]> products = productPriceService.findProduct();

			/**
			 * 担保方式列表
			 */
			Collection<CodeData> guaranteeModes = dataDict
					.getOneType("CdsGuarantMode");

			/**
			 * 财务信息
			 */
			SalaBusiCustFinalcial salaBusiCustFinalcial = businessApplicationService
					.getFinanceByProjectId(projectApplication.getProjectId()
							.toString());

			/**
			 * 保险机构
			 */
			List<InsuranceCompany> insuranceCompanys = businessApplicationService
					.findAllInsuranceCompany();

			model.addAttribute("vo", businessApplicationWdVO);
			model.addAttribute("salaBusiCustFinalcial", salaBusiCustFinalcial);
			model.addAttribute("products", products);
			model.addAttribute("guaranteeModes", guaranteeModes);
			model.addAttribute("productCd", projectApplication.getProductType());
			model.addAttribute("party", party);
			model.addAttribute("insuranceCompanys", insuranceCompanys);
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("type", "check");
			model.addAttribute("consultLocation", "consultLocation");

			// 2015-1-26
			Collection<CodeData> termUnitCds = dataDict
					.getOneType("TermUnitCd");
			Collection<CodeData> newTermUnitCds = new ArrayList<CodeData>();

			Iterator<CodeData> it = termUnitCds.iterator();
			while (it.hasNext()) {
				CodeData codeData = it.next();
				if (!dataDict.getCodeVal("TermUnitCd", "S1").equals(
						codeData.getCodeValue())) {
					newTermUnitCds.add(codeData);
				}
			}
			model.addAttribute("termUnits", newTermUnitCds);
			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			return BUSINESS_APPLICATION_WD + "/main";
		default:
			return "";
		}
	}

	/**
	 * Description 业务员发起新业务时，先检查该客户是否有正在处理中的业务，如果有，则不允许继续发起新业务
	 * 
	 * @param partyId
	 * @return
	 */
	@RequestMapping("/checkStatus")
	@ResponseBody
	public Result checkStatus(@RequestParam String partyId) {
		boolean check = businessApplicationService.checkStatus(Long
				.valueOf(partyId));
		if (check)
			return success(uniqueCustomerService.findPartyByPartyId(partyId));
		return failure("<strong>此客户有正在处理中的业务，不允许发起新业务。</strong>");
	}

	@RequestMapping("/checkCreditAppStatus")
	@ResponseBody
	public Result checkCreditAppStatus(@RequestParam Long partyId) {
		boolean check = businessApplicationService.checkCreditAppStatus(Long
				.valueOf(partyId));
		if (check)
			return success(uniqueCustomerService.findPartyByPartyId(partyId
					.toString()));
		return failure("<strong>此客户有正在处理中的授信业务，不允许发起新业务。</strong>");
	}

	/**
	 * 易贷业务提交前先检查是否符合提交要求
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/checkBusinessInfoBeforeSendProcess")
	@ResponseBody
	public Result checkBusinessInfoBeforeSendProcess(Long projectId) {
		try {
			businessApplicationService.checkBusinessInfoBeforeSendProcess(
					projectId, null,
					WorkFlowService.WorkFlowCode.EASY_LOAN.getCodeId());
			return success();
		} catch (Exception e) {
			return failure(e.getMessage());
		}
	}

	/**
	 * Description 保存基本项目信息、利率、生成费用
	 * 
	 * @return
	 */
	@RequestMapping("/saveBusiness")
	@ResponseBody
	public Result saveBusiness(BusinessApplicationVO vo) {
		try {
			/**
			 * 保存业务申请
			 */
			ProjectApplication p = businessApplicationService
					.searchProjectApplication(Long.valueOf(vo.getProjectId()));
			BeanUtils.copyProperties(vo, p);
			if (StringUtils.isBlank(vo.getMateBorrower())) {
				p.setMateBorrower(dataDict.getCodeVal("CommonWhether", "S2"));
			}
			try {
				p.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").parse(vo
						.getApplyDate()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			p.setApproveAmt(vo.getApplyAmt());
			p.setApplyTermUnit(dataDict.getUniqueOne("TermUnitCd", "S2")
					.getCodeValue());// 期限单位：月
			//判断错误
			if (vo.getReplyPeriodNum()==null) {
				p.setReplyPeriodNum(null);
			} else {
				p.setReplyPeriodNum(vo.getReplyPeriodNum());
			}
			p.setTerm(vo.getApplyTerm());
			p.setTermUnit(dataDict.getUniqueOne("TermUnitCd", "S2")
					.getCodeValue());
			p.setGuaranteeMode("4");
			p.setBizRate(vo.getRateValue().divide(new BigDecimal(100)));
			p.setOvdueRate(vo.getOverFloatRate().divide(new BigDecimal(100)));
			p.setPerculNegoRate(vo.getDivertFloatRate().divide(
					new BigDecimal(100)));
			//add by wangpeng on 2015-07-27 start
			p.setAssistance(vo.getAssistancer());
			//add by wangpeng on 2015-07-27 end
			BizRate bizRate = businessApplicationService.saveEasyLoanBusiness(
					p, vo.getBizRateId(), vo.getInterestRateAdjustment(),
					vo.getOverFloatRate(), vo.getDivertFloatRate(),
					vo.getRateValue());

			return success("保存成功！", bizRate);
		} catch (Exception e) {
			e.printStackTrace();
			return failure(e.getMessage() != null ? e.getMessage() : "保存失败！");
		}
	}

	/**
	 * Description 保存工作底稿
	 * 
	 * @return
	 */
	@RequestMapping("/saveWorkDraft")
	@ResponseBody
	public Result saveWorkDraft(WorkDraftVO form, Model model) {
		try {
			WorkDraft workDraft = businessApplicationService
					.saveWorkDraft(form);
			return success("保存成功！", workDraft);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}

	/**
	 * Description 保存可支配收入测算
	 * 
	 * @return
	 */
	@RequestMapping("/saveMeasure")
	@ResponseBody
	public Result saveMeasure(MonthDominateIncludeMeasure form) {
		try {
			MonthDominateIncludeMeasure measure = businessApplicationService.saveMeasure(form);
			return success("保存成功！", measure);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}

	/**
	 * 查询工作底稿
	 */
	@RequestMapping("/searchWorkDraft")
	@ResponseBody
	public WorkDraft searchWorkDraft(Long projectId, Model model) {
		WorkDraft workDraft = businessApplicationService
				.searchWorkDraft(projectId);
		return workDraft;
	}

	@RequestMapping("/getMeasureByProjectId")
	@ResponseBody
	public MonthDominateIncludeMeasure getMeasureByProjectId(Long projectId) {
		return businessApplicationService.getMeasureByProjectId(projectId);
	}

	/**
	 * 查询客户信息
	 */
	@RequestMapping(value = "/searchIndividual", method = RequestMethod.POST)
	@ResponseBody
	public Result searchIndividual(String partyId) {

		Individual individual = uniqueCustomerService.getIndividual(partyId);

		return success(individual);
	}

	/**
	 * Description 保存额度预算
	 * 
	 * @return
	 */
	@RequestMapping("/saveQuotaMeasure")
	@ResponseBody
	public Result saveQuotaMeasure(QuotaMeasureVO form) {
		try {
			if (form.getBudgetId() == null)
				form.setCreateDate(new Date());
			QuotaMeasure quotaMeasure = businessApplicationService.saveQuotaMeasure(form);
			return success("保存成功！", quotaMeasure);
		} catch (Exception e) {
			return failure(e.getMessage());
		}
	}

	/**
	 * 根据projectId查询额度测算信息
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/getQuotaMeasureByProjectId")
	@ResponseBody
	public QuotaMeasure getQuotaMeasureByProjectId(Long projectId) {
		return businessApplicationService.searchQuotaMeasure(projectId);
	}

	/**
	 * Description 查询新增费用信息
	 * 
	 * @return
	 */
	@RequestMapping("/searchBizExpenseRate/{bizExpenseRateId}")
	@ResponseBody
	public Result searchBizExpensrRate(
			@PathVariable("bizExpenseRateId") Long bizExpenseRateId) {
		BizExpenseRate bizExpenseRate = businessApplicationService
				.getBizExpenseRateById(bizExpenseRateId);
		return success(bizExpenseRate);
	}

	/**
	 * Description 保存费用信息
	 * 
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
			} else {
				form.setSysUpdateDate(new Date());
				businessApplicationService.updateBizExpenseRate(form);
			}
			return success("费用信息保存成功！");
		}
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

	/**
	 * 费用列表的查询方法
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
	public DataTablesPage searchBizExpenseRateList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String projectNo = (String) request.getParameter("projectNo");
		List<Object> params = new ArrayList<Object>();
		Page queryList = businessApplicationService
				.findBizExpenseRateByProjectNo(projectNo, firstIndex / pageSize
						+ 1, pageSize, params);

		List<BizExpenseRate> list = queryList.getContent();
		if (list.size() > 0) {
			for (BizExpenseRate bizExpenseRate : list) {
				bizExpenseRate.setSysCreateDateStr(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(bizExpenseRate
						.getSysCreateDate()));
				bizExpenseRate.setSysUpdateDateStr(new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss").format(bizExpenseRate
						.getSysUpdateDate()));
			}
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 保存家庭资产
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/saveFamilyAssets")
	@ResponseBody
	public Result saveFamilyAssets(FamilyAssetsDetail form) {
		try {
			FamilyAssetsDetail familyAssetsDetail = businessApplicationService
					.saveFamilyAssets(form);
			return success("保存成功！", familyAssetsDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return failure("保存失败！");
		}
	}

	/**
	 * 通过projectId获取家庭资产信息
	 * 
	 * @param projectId
	 * @return
	 */
	@RequestMapping("/getFamilyAssetsByProjectId")
	@ResponseBody
	public FamilyAssetsDetail getFamilyAssetsByProjectId(Long projectId) {
		return businessApplicationService.getFamilyAssetsByProjectId(projectId);
	}

	@RequestMapping("/getAllIndustry")
	@ResponseBody
	public List<IndustryType> getAllIndustry() {
		return uniqueCustomerService.getAllIndustry();
	}

	/**
	 * 待办事项进入回显数据
	 */
	@RequestMapping(value = "/schedule/{projectId}", method = RequestMethod.POST)
	@ResponseBody
	public String scheduleThing(@PathVariable("projectId") Long projectId,
			Model model) {
		WorkDraft wd = businessApplicationService.searchWorkDraft(projectId);
		model.addAttribute("workDraft", wd);
		QuotaMeasure qm = businessApplicationService
				.searchQuotaMeasure(projectId);
		model.addAttribute("quotaMeasure", qm);
		return BUSINESS_APPLICATION + "/main";
	}

	@RequestMapping("/submitApply")
	@ResponseBody
	public Result submitApply(String projectNo, String workflowId,
			String taskId, String opinion, String actionCode, String productCd,String assistance) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();

		if (RISKMNG_FLAG) {
			String msg = "";
			boolean flag = true;
			/**
			 * 风险接口
			 */
			String json = "";
			try {
				json = webServices.invoke("wdService", "antiFraudWebService",
						projectNo, new SimpleDateFormat("yyyy-MM-dd H:m:s").format(new Date()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			if (StringUtils.isBlank(json)) {
				msg = "连接超时。";
				flag = false;
				return failure(msg, null);
			}
			Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
			Object checkResult = map.get("checkResult");
			Object code = map.get("code");
			Object feedbackTime = map.get("feedbackTime");

			if (code.equals("1")) {
				if (checkResult.equals("1")) {
					msg = "该客户不具备申请此业务的条件，系统已强制退回！。";
					flag = false;
				} else if (checkResult.equals("2")) {
					msg = "推荐审核。";
				} else if (checkResult.equals("3")) {
					msg = "通过。";
				} else {
					msg = "不适用。";
				}
			} else {
				msg = "检查失败。";
				flag = false;
			}
			if (!flag) {
				return failure(msg, null);
			}
		}

		/**
		 * 发送流程
		 */
		try {
			ActionCode actCode = ActionCode.getActionCodeById(actionCode);

			TypedResult<List<NextTaskReceiver>> resultList = this.workFlowService
					.getNextTaskReceivers(taskId, ActionCode.COMMIT,
							Long.parseLong(workflowId),
							WorkFlowNode.EL_EntryBusiApplInfo);

			NextTaskReceiver receiver = null;
			for (NextTaskReceiver rec : resultList.getData()) {
				if (receiver == null
						|| Integer.parseInt(receiver.getTotal()) > Integer
								.parseInt(rec.getTotal())) {
					receiver = rec;
				}
			}
			if(receiver == null) {
				return failure("无下一环节接收人！");
			}
			businessApplicationService.sendProccessAndUpdateApplication(
					WorkFlowService.WorkFlowCode.EASY_LOAN, workflowId, taskId,
					curUser.getLogname(), WorkFlowNode.EL_EntryBusiApplInfo,
					actCode, receiver.getLogName() + "," + receiver.getOrgId(),
					opinion, "", GlobalConstants.EL_WF_TASK_ID_10);
			return success();
		} catch (Exception e) {
			e.printStackTrace();
			return failure(e.getMessage());
		}
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
	 * @author lijing
	 * @lastModified lijing 2014-08-20 11:30:50
	 */
	@RequestMapping("/findDocuments")
	@ResponseBody
	public DataTablesPage findCustomerDocumentsBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String partyId = (String) request.getParameter("partyId");
		String projectId = (String) request.getParameter("projectId");
		String documentName = (String) request
				.getParameter("query_documentName");
		String contentType = (String) request.getParameter("query_contentType");
		List<Object> params = new ArrayList<Object>();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId);
		String customerName = "";
		if (party.getPartyTypeCd().equals("1"))// 企业客户
			customerName = corporationCustomerService.findById(
					Long.valueOf(partyId)).getCustomerName();
		else if (party.getPartyTypeCd().equals("2"))// 个人客户
			customerName = uniqueCustomerService.getIndividual(partyId)
					.getCustomerName();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		params.add(partyId);
		params.add(projectId);
		params.add(documentName);
		params.add(contentType);
		// params.add("('02','01','06')");
		Page queryList = businessApplicationService.findDocumentIndexBySearch(
				firstIndex / pageSize + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		for (Object[] obj : resultList) {
			obj[4] = customerName;
			// obj[5] = curUser.getName();
			obj[6] = timeStampToString((Timestamp) obj[6]);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 新增文档前的参数获取
	 * 
	 * @param partyId
	 *            客户Id
	 * @param request
	 *            HttpServletRequest
	 * @return DocumentUploadVO 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/beforeUpdate")
	@ResponseBody
	public DocumentUploadVO beforeUpdate(@RequestParam String partyId,
			@RequestParam String projectId, @RequestParam String uploadType,
			@RequestParam(required = false) String custDocType,
			HttpServletRequest request) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		documentUploadVO.setPartyId(partyId);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		Party party = uniqueCustomerService.findPartyByPartyId(partyId);
		Individual individual = null;
		CorporationCustomer corporationCustomer = null;
		if (party.getPartyTypeCd().equals("1")) {// 企业
			corporationCustomer = corporationCustomerService.findById(Long
					.valueOf(partyId));
			documentUploadVO.setCustomerNum(corporationCustomer
					.getCustomerNum());
			documentUploadVO.setDocumentNum(commonBizNumberBS
					.generateDocumentNum(corporationCustomer.getCustomerNum(),
							"01"));
		} else if (party.getPartyTypeCd().equals("2")) {// 个人
			individual = uniqueCustomerService.getIndividual(partyId);
			documentUploadVO.setCustomerNum(individual.getCustomerNum());
			documentUploadVO.setDocumentNum(commonBizNumberBS
					.generateDocumentNum(individual.getCustomerNum(), "01"));
		}
		ProjectApplication project = businessApplicationService
				.searchProjectApplication(Long.valueOf(projectId));
		documentUploadVO.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		documentUploadVO.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		documentUploadVO.setCreateDateTime(timeStampToString(new Timestamp(
				new Date().getTime())));

		documentUploadVO.setBizNum(project.getProjectNo());
		documentUploadVO.setBizId(projectId);
		documentUploadVO.setDocumentType("02");// 默认存业务
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");

		return documentUploadVO;
	}

	/**
	 * 查找下载地址与文件名
	 * 
	 * @param documentId
	 *            文档Id
	 * @return List 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-19 17:30:50
	 */
	@RequestMapping("/findDocumentPath")
	@ResponseBody
	public List<String> findDocumentPath(@RequestParam String documentId) {
		DocumentIndex documentIndex = uniqueCustomerService
				.findDocumentIndexByDocumentId(documentId);
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
	 * @author lijing
	 * @lastModified lijing 2014-08-19 9:30:50
	 */
	@RequestMapping("/delDocument/{id}")
	@ResponseBody
	public String delDocument(@PathVariable String id) {
		DocumentIndex documentIndex = uniqueCustomerService
				.findDocumentIndexByDocumentId(id);
		if (documentIndex.getCreateTypeCd().equals("2")) {
			return "createTypeCdEquals2Error";
		} else {
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
					.getPrincipal();
			documentIndex.setSystemUpdateTime(new Timestamp(new Date()
					.getTime()));
			documentIndex.setUserNum(curUser.getId().toString());
			documentIndex.setStatus("2");
			uniqueCustomerService.saveDocumentIndex(documentIndex);
			return "success";
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
	public CustDocInfo findUploadCustDocTypes(@RequestParam Long partyId) {
		Party party = uniqueCustomerService.findPartyByPartyId(partyId
				.toString());
		String custDocTypeCd = "";
		if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S1"))) {
			custDocTypeCd = DocStageCode.QY_DOC.getCodeId();// 企业
		} else if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S2"))) {
			// 个人客户
			String employmentType = uniqueCustomerService
					.getEmploymentTypeByPartyId(partyId);
			if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
				custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); // 受薪
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))) {
				custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); // 农户
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
				custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); // 经营
			} else {
				custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
			}
		} else {
			custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
		}
		return documentService.findUploadCustDocTypes(null, partyId,
				custDocTypeCd + ","+ DocStageCode.YW_YD_DOC.getCodeId());// 易贷
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
		Party party = uniqueCustomerService.findPartyByPartyId(partyId
				.toString());
		String custDocTypeCd = "";
		if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S1"))) {
			custDocTypeCd = DocStageCode.QY_DOC.getCodeId();// 企业
		} else if (StringUtils.equals(party.getPartyTypeCd(),
				dataDict.getCodeVal("CustomerType", "S2"))) {
			// 个人客户
			String employmentType = uniqueCustomerService
					.getEmploymentTypeByPartyId(partyId);
			if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S1"))) {
				custDocTypeCd = DocStageCode.SX_DOC.getCodeId(); // 受薪
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S2"))) {
				custDocTypeCd = DocStageCode.NH_DOC.getCodeId(); // 农户
			} else if (StringUtils.equals(employmentType,
					dataDict.getCodeVal(CODETYPE_EMPLOYMENTTYPE, "S3"))) {
				custDocTypeCd = DocStageCode.JY_DOC.getCodeId(); // 经营
			}
		} else {
			custDocTypeCd = DocStageCode.NULL_DOC.getCodeId();
		}
		return documentService.findDocumentCustDocTypes(projectId, partyId,
				custDocTypeCd + "," + DocStageCode.YW_YD_DOC.getCodeId());
	}

	@RequestMapping("/findProductObject")
	@ResponseBody
	public List<Product> findProductObject(String partyTypeCd) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		return productPriceService.findProductByPartyTypeCdAndOrgId(
				partyTypeCd, curUser.getOrgid());
	}

	@RequestMapping("/findEffectiveProduct")
	@ResponseBody
	public List<EffectiveProductVO> findEffectiveProduct(
			@RequestParam(required = false) Long partyId,
			@RequestParam(required = false) Boolean isDesignated) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//modify by wangyawei 20150708 start 将所属机构修改为登陆机构
		Long orgId = curUser.getLogOrgid();
		//modify by wangyawei 20150708 end
		
		String adminRoles = businessApplicationService.findAdminRoles();
		for (String adminRole : adminRoles.split(",")) {
			for (EcOrgPersonconnrole role : curUser.getRoles()) {
				if (role.getEcOrgRole().getRoleNum().equals(adminRole)) {
					orgId = null;
					break;
				}
			}
		}
		
		return productPriceService.findEffectiveProduct(partyId, orgId, isDesignated);
	}

	@RequestMapping("/cancelApply")
	@ResponseBody
	public Result cancelApply(Long projectId, String workflowId, String taskId) {

		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();

		return businessApplicationService.cancelApply(projectId, workflowId,
				taskId, curUser.getLogname(),
				WorkFlowService.WorkFlowCode.EASY_LOAN,
				WorkFlowNode.EL_EntryBusiApplInfo);
	}

	@RequestMapping("/downloadFile")
	public void downloadFile(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String filepath = request.getSession().getServletContext()
				.getRealPath("doc_templet/“邦易贷”个人信用贷款申请表.docx");
		String filename = "“邦易贷”个人信用贷款申请表.docx";
		String fullFileName = new String(filepath.getBytes("UTF-8"), "UTF-8");

		String downLoadName = null;
		String agent = request.getHeader("USER-AGENT");
		if (agent != null && agent.indexOf("Firefox") >= 0) {
			downLoadName = new String(filename.getBytes(), "iso-8859-1");
		} else {
			downLoadName = URLEncoder.encode(filename, "utf-8");
		}

		File file = new File(fullFileName);
		if (file.exists() && file.isFile()) {
			try {
				response.addHeader("Content-Disposition",
						"attachment;filename=" + downLoadName);// 设置文件名
				response.setContentType("application/octet-stream");
				FileUtils.copyFile(file, response.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("%%%%%%%%%%%%%%%%%%%%%%%文档：" + fullFileName
					+ " 不存在！");
			return;
		}
	}

	/**
	 * 贷款试算
	 * 
	 * @param form
	 * @return
	 */
	@RequestMapping("/loanCal")
	@ResponseBody
	public Result loanCal(LoanCalVO form) {
		try {
			InterestCalCulateForm interestForm = new InterestCalCulateForm();
			BeanUtils.copyProperties(form, interestForm);
			if (StringUtils.isBlank(form.getApplyTermUnit()))
				interestForm.setApplyTermUnit(dataDict.getCodeVal("TermUnitCd",
						"S2"));
			interestForm.setLoanStartDate(DateTools.stringToDate(
					form.getLoanStartDate(), "yyyy-MM-dd"));
			interestForm.setProjectId(Integer.parseInt(form.getProjectId()
					.toString()));

			List<InterestVO> list = null;
			if (interestForm != null) {
				Date endDate = DateTools.getEndingDate(
						interestForm.getLoanStartDate(),
						interestForm.getApplyTermUnit(),
						interestForm.getApplyTerm());
				interestForm.setLoanEndDate(endDate);
				interestForm.setRate(interestForm.getRate().divide(
						new BigDecimal(100)));
				list = interestCalCulateServiceForBusiness
						.calCulate(interestForm);
			}
			return success(list);
		} catch (Exception e) {
			return failure(e.getMessage());
		}

	}

	/**
	 * 获取额度测算的本笔贷款月还款额
	 * 
	 * @return
	 */
	@RequestMapping("/getRepaymentThisTime")
	@ResponseBody
	public BigDecimal getRepaymentThisTime(BigDecimal loanAmount,
			Integer applyTerm, BigDecimal rate, Integer repaymentNumber) {
		try {
			InterestCalCulateForm interestForm = new InterestCalCulateForm();
			interestForm.setLoanAmount(loanAmount);
			interestForm.setApplyTerm(applyTerm);
			interestForm.setRepayment(dataDict
					.getCodeVal("RepaymentMode", "S4"));
			interestForm.setRate(rate.divide(new BigDecimal(100)));
			interestForm.setRepaymentNumber(repaymentNumber);
			interestForm.setApplyTermUnit(dataDict.getCodeVal("TermUnitCd",
					"S2"));
			interestForm.setRepaymentDate(1);
			try {
				interestForm
						.setLoanStartDate(new SimpleDateFormat("yyyy-MM-dd")
								.parse(new SimpleDateFormat("yyyy-MM-dd")
										.format(new Date())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date endDate = DateTools.getEndingDate(
					interestForm.getLoanStartDate(),
					interestForm.getApplyTermUnit(),
					interestForm.getApplyTerm());
			interestForm.setLoanEndDate(endDate);
			List<InterestVO> list = interestCalCulateServiceForBusiness
					.calCulate(interestForm);
			return list.get(0).getCurrentPricipalInterest();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Timestamp转化成String的方法
	 * 
	 * @param st
	 *            Timestamp类型的变量
	 * @return String 返回时间转换成的字符串
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
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
	 * 通过workflowid与taskid进行的业务页面跳转,功能同editApplication与checkApplication相同
	 * 
	 * @param workflowId
	 * @param taskId
	 * @param model
	 * @return
	 */
	@RequestMapping("/haveDoneOperation/{workflowId}")
	public String haveDoneOperation(Long workflowId, Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		ProjectApplication projectApplication = businessApplicationService
				.findProjectApplicationByWorkflowId(workflowId);
		Product product = productPriceService
				.getProductByProductCd(projectApplication.getProductType());

		Party party = uniqueCustomerService
				.findPartyByPartyId(projectApplication.getPartyId().toString());

		CorporationCustomer corporationCustomer = null;
		Individual individual = null;
		if (party.getPartyTypeCd().equals("1")) {// 企业客户
			corporationCustomer = corporationCustomerService.findById(party
					.getPartyId());
		} else if (party.getPartyTypeCd().equals("2")) {// 个人客户
			individual = uniqueCustomerService.getIndividual(party.getPartyId()
					.toString());
		}

		BizRate bizRate = businessApplicationService
				.getBizRateByProjectId(projectApplication.getProjectId()
						.toString());

		ProductConfig productConfig = businessApplicationService
				.getProductConfigByProductCd(product.getProductCd(),
						Long.valueOf(projectApplication.getApplyOrg()));
		model.addAttribute("productConfig", productConfig);
		//add by mz 20150703 start
		String orgName = orgDeptmentService.findDeptNameById(Long.valueOf(projectApplication.getApplyOrg()));
		//add by mz 20150703 end
		switch (Integer.parseInt(productConfig.getWfCode())) {
		case EASY_LOAN:// 易贷流程
			if (projectApplication.getApplyDate() != null) {
				projectApplication
						.setApplyDateStr(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));
			}

			QuotaMeasure qm = businessApplicationService
					.searchQuotaMeasure(projectApplication.getProjectId());
			model.addAttribute("quotaMeasure", qm);
			model.addAttribute("judgeType", "check");
			model.addAttribute("curUserLogOrgid", curUser.getLogOrgid());
			model.addAttribute("curUserLogOrgname", curUser.getLogOrgname());
			model.addAttribute("projectApplication", projectApplication);
			model.addAttribute("party", party);
			model.addAttribute("individual", individual);
			model.addAttribute("bizRate", bizRate);
			model.addAttribute("product", product);
			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			return BUSINESS_APPLICATION + "/main";
		case MICRO_LOAN:// 微贷流程
			/**
			 * fill VO
			 */
			BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
			BeanUtils.copyProperties(
					party.getPartyTypeCd().equals("1") ? corporationCustomer
							: individual, businessApplicationWdVO);
			BeanUtils.copyProperties(projectApplication,
					businessApplicationWdVO);
			if (bizRate != null) {
				BeanUtils.copyProperties(bizRate, businessApplicationWdVO);
			}
			if (projectApplication.getApplyDate() != null) {
				businessApplicationWdVO
						.setApplyDate(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));

			}
			if (businessApplicationWdVO.getBizRate() != null) {
				businessApplicationWdVO.setBizRate(businessApplicationWdVO
						.getBizRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getIrNegoSymbCd() != null) {
				businessApplicationWdVO.setIrNegoSymbCd(businessApplicationWdVO
						.getIrNegoSymbCd().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getOvdueRate() != null) {
				businessApplicationWdVO.setOvdueRate(businessApplicationWdVO
						.getOvdueRate().multiply(new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPerculNegoRate() != null) {
				businessApplicationWdVO
						.setPerculNegoRate(businessApplicationWdVO
								.getPerculNegoRate().multiply(
										new BigDecimal("100")));
			}
			if (businessApplicationWdVO.getPreRepaymentRate() != null) {
				businessApplicationWdVO
						.setPreRepaymentRate(businessApplicationWdVO
								.getPreRepaymentRate().multiply(
										new BigDecimal("100")));
			}

			/**
			 * 产品列表
			 */
			List<Object[]> products = productPriceService.findProduct();

			/**
			 * 担保方式列表
			 */
			Collection<CodeData> guaranteeModes = dataDict
					.getOneType("CdsGuarantMode");

			/**
			 * 财务信息
			 */
			SalaBusiCustFinalcial salaBusiCustFinalcial = businessApplicationService
					.getFinanceByProjectId(projectApplication.getProjectId()
							.toString());

			/**
			 * 保险机构
			 */
			List<InsuranceCompany> insuranceCompanys = businessApplicationService
					.findAllInsuranceCompany();
			
			/**
			 * 帮农贷
			 */
			//根据产品代码判断是不是农贷
			final boolean farmerLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, projectApplication.getProductType());//StringUtils.equals(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, pa.getProductType());
			//根据团结贷
			final boolean unityLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, projectApplication.getProductType());
			if(farmerLoanProductMark||unityLoanProductMark){
					String type = bizApplyQueryService.findAgroBizTypeByProjId(businessApplicationWdVO.getProjectId());
					
					model.addAttribute("industryTypeCd", businessApplicationWdVO.getIndustryCd());
					model.addAttribute("customerId", party.getPartyId());
					model.addAttribute("agriculturalLoan", Boolean.TRUE);
					model.addAttribute("bizType", type);
			}

			model.addAttribute("vo", businessApplicationWdVO);
			model.addAttribute("salaBusiCustFinalcial", salaBusiCustFinalcial);
			model.addAttribute("products", products);
			model.addAttribute("guaranteeModes", guaranteeModes);
			model.addAttribute("productCd", projectApplication.getProductType());
			model.addAttribute("party", party);
			model.addAttribute("insuranceCompanys", insuranceCompanys);
			model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			model.addAttribute("type", "check");

			// 2015-1-26
			Collection<CodeData> termUnitCds = dataDict
					.getOneType("TermUnitCd");
			Collection<CodeData> newTermUnitCds = new ArrayList<CodeData>();

			Iterator<CodeData> it = termUnitCds.iterator();
			while (it.hasNext()) {
				CodeData codeData = it.next();
				if (!dataDict.getCodeVal("TermUnitCd", "S1").equals(
						codeData.getCodeValue())) {
					newTermUnitCds.add(codeData);
				}
			}
			model.addAttribute("termUnits", newTermUnitCds);
			//add by mz 20150703 start
			model.addAttribute("customerManagerName", projectApplication.getCustomerManagerName());
			model.addAttribute("orgName", orgName);
			//add by mz 20150703 end
			return BUSINESS_APPLICATION_WD + "/main";
		default:
			return "";
		}
	}
	
	@RequestMapping("validateRate")
	@ResponseBody
	public Boolean validateRate(@RequestParam Long projectId, @RequestParam Integer applyTerm, @RequestParam String applyTermUnit, @RequestParam String approveRateValueStr){
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		ProjectApplication pa = businessApplicationService.searchProjectApplication(projectId);
		if(StringUtils.isBlank(applyTermUnit)||applyTerm==null||StringUtils.isBlank(approveRateValueStr)){
			return Boolean.TRUE;
		}
		return this.businessApplicationService.validateRate(Long.valueOf(pa.getProductType()), new Long(pa.getApplyOrg()), applyTerm, applyTermUnit, new BigDecimal(approveRateValueStr)).getSuccess();
	}
	
	/**
	 * 点击业务编号-查询业务信息(统一跳转)
	 * 
	 * @param workflowId 流程ID
	 * @param sign 标识
	 * @param model
	 * @return
	 */
	@RequestMapping("/checkBusinessApplication/{workflowId}/{sign}")
	public String checkBusinessApplication(@PathVariable("workflowId") Long workflowId,
			@PathVariable("sign") String sign, Model model) {
		Long businessId = null;
		WorkFlowCode workFlowCode = null;
		WfBusinessRelation wfBusinessRelation = wfActionTreeNodeService.findWfBusinessRelation(workflowId);
		//拼接下一环节跳转路径
		StringBuffer nextPath = new StringBuffer("redirect:/");
		
		//兼容邦微贷审批和邦易贷审批
		if(wfBusinessRelation == null){
			businessId = businessApplicationService.findProjectApplicationByWorkflowId(workflowId).getProjectId();
			nextPath.append(GlobalConstants.BUSINESS_APPLICATION).append("/")
					.append("checkApplication").append("/")
					.append(businessId).append("/")
					.append(sign);
			return nextPath.toString();
		} 
		workFlowCode = WorkFlowCode.getById(wfBusinessRelation.getWorkFlowType());
		
		if (workFlowCode != null) {
			switch (workFlowCode) {
				case CREDIT_APPROVAL:
					nextPath.append(GlobalConstants.INIT_BIZ_CREDIT_APPLICATION).append("/")
						    .append("checkApplication").append("/")
							.append(workflowId);
					break;
				case CREDIT_LOAN:
					nextPath.append(GlobalConstants.UNDER_CREDIT_CONTRACT_MNG).append("/")
						    .append("checkApplication").append("/")
							.append(workflowId);
					break;
				default:
					nextPath.append(GlobalConstants.DASHBOARD);
					break;
			}
		}
		return nextPath.toString();
	}
}

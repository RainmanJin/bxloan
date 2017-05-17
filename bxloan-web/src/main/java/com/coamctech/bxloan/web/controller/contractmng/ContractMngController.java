package com.coamctech.bxloan.web.controller.contractmng;

import static com.coamctech.bxloan.commons.GlobalConstants.CONTRACT;
import static com.coamctech.bxloan.commons.GlobalConstants.DASHBOARD;
import static com.coamctech.bxloan.commons.GlobalConstants.DOC_TARGET;
import static com.coamctech.bxloan.commons.GlobalConstants.EXPENSE_RATE_BAK;
import static com.coamctech.bxloan.commons.GlobalConstants.EXPENSE_RATE_PRE;
import static com.coamctech.bxloan.commons.GlobalConstants.FORM_CONTRACT_EL;
import static com.coamctech.bxloan.commons.GlobalConstants.FORM_CONTRACT_ML;
import static com.coamctech.bxloan.commons.GlobalConstants.INIT_CONTRACT;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.GlobalConstants.DocStageCode;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.commons.utils.Utils;
import com.coamctech.bxloan.dao.PartyDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Address;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.CustomerAccountManagent;
import com.coamctech.bxloan.entity.DocumentIndex;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.MoneyRate;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.entity.SubContract;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.collateral.CollateralService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.contractmng.DocumentMngService;
import com.coamctech.bxloan.service.contractmng.RenewaPledgeService;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.sysmng.DocumentService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.workflow.WorkFlowService;
import com.coamctech.bxloan.service.workflow.WorkFlowService.WorkFlowCode;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.contractmng.ContractSaveVO;
import com.coamctech.bxloan.web.vo.contractmng.ContractVO;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

//import com.lowagie.text.DocumentException;
//import com.lowagie.text.pdf.AcroFields;
//import com.lowagie.text.pdf.PdfReader;
//import com.lowagie.text.pdf.PdfStamper;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.pdf.AcroFields;
//import com.lowagie.text.pdf.PdfReader;
//import com.lowagie.text.pdf.PdfStamper;

/**
 * 合同管理控制器
 * 
 * @author lijing
 * @lastModified lijing 2014-08-13 17:30:24
 */
@Controller
@RequestMapping("/" + CONTRACT)
public class ContractMngController extends BaseController {
	/** 合同状态 */
	private final String CODETYPE_CONTRACTSTATUS = "ContractStatusCode";
	/** 文档状态 */
	private final String CODETYPE_DTCREATETYPE = "CreateType";
	/** 个人客户的证件类型 */
	private final String CODETYPE_CERTIFICATETYPECD = "CertificateType";
	/** 下载pdf的文件后缀名 */
	private final String PDF_SUFFIX = "_合同文本.pdf";
	@Autowired
	private ContractMngService contractMngService;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private CorporationCustomerService corpCusService;
	@Autowired
	private CollateralService collateralService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private RenewaPledgeService renewaPledgeService;
	@Autowired
	private DocumentMngService documentMngService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private PartyDao partyDao;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private WorkFlowService workFlowService;

	/*********************************** 跳转方法 *******************************************/
	/**
	 * sideBar上点击合同管理的跳转方法
	 * 
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-13 16:37:51
	 */
	@RequestMapping
	public String init() {
		return INIT_CONTRACT;
	}

	/**
	 * 在跳转合同Form页面之前完成值传递的方法
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * @param projectId
	 *            业务id
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-31 9:37:51
	 */
	@RequestMapping("/signToForm")
	public String signToForm(@RequestParam String projectId,
			@RequestParam String workflowId, @RequestParam String taskId,
			@RequestParam String partyType, @RequestParam String taskStageCode,
			@RequestParam String workFlowType, HttpServletRequest request) {
		return "redirect:/" + CONTRACT + "/perCondition?projectId=" + projectId
				+ "&workflowId=" + workflowId + "&taskId=" + taskId
				+ "&partyType=" + partyType + "&taskStageCode=" + taskStageCode
				+ "&workFlowType=" + workFlowType;
	}

	/**
	 * 跳转到Form页面之前进行合同记录的插入
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-31 9:37:51
	 */
	@RequestMapping("/perCondition")
	public String perCondition(@RequestParam String projectId,
			@RequestParam String workflowId, @RequestParam String taskId,
			@RequestParam String partyType, @RequestParam String taskStageCode,
			@RequestParam String workFlowType, HttpServletRequest request) {
		request.setAttribute("projectId", projectId);
		Contract oldContract = contractMngService.findContractByProjectId(Long
				.valueOf(projectId));
		ProjectApplication project = contractMngService.getProject(Long
				.valueOf(projectId));
		request.setAttribute("partyId", project.getPartyId());
		Party party = uniqueCustomerService.findPartyByPartyId(project
				.getPartyId().toString());
		request.setAttribute("workflowId", workflowId);
		request.setAttribute("taskStageCode", taskStageCode);
		request.setAttribute("productTypeCd", project.getProductType());
		request.setAttribute("taskId", taskId);
		// Properties prop =
		// Utils.loadPropertiesFileFromClassPath(UPLOAD_TARGET);
		request.setAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		if (oldContract == null) {
			// 创建合同
			Contract contract = new Contract();
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
					.getPrincipal();
			// 生成合同编号
			String contractNum;
			if ("1".equals(project.getBusinessType())) { // 个人贷款
				//contractNum = commonBizNumberBS.generateContractNumber(curUser.getOrgid().toString(), "1", "借", 1);
				contractNum = commonBizNumberBS.generateContractNumber(curUser.getLogOrgid().toString(), "1", "借", 1);
				contract.setContractNum(contractNum);//TODO
			} else if ("2".equals(project.getBusinessType())) { // 企业贷款
				//contractNum = commonBizNumberBS.generateContractNumber(curUser.getOrgid().toString(), "0", "借", 1);
				contractNum = commonBizNumberBS.generateContractNumber(curUser.getLogOrgid().toString(), "0", "借", 1);
				contract.setContractNum(contractNum);
			}
			contract.setCustomerType(party.getPartyTypeCd());
			// projectId的插入
			contract.setProjectId(Long.valueOf(projectId));
			// 设置展期笔数
			contract.setContractIndex(new Long(0));
			// 设置projectNo
			contract.setProjectNo(project.getProjectNo());
			// 设置用户名，用户编号
			contract.setCustomerName(project.getCustomerName());
			contract.setCustomerNum(project.getCustomerNum());
			// 设置可用金额
			contract.setContractAmt(project.getApproveAmt());
			contract.setContractAvailableAmt(project.getApproveAmt());
			// 合同期限和期限单位
			if (project.getTerm() != null) {
				contract.setContractTerm(project.getTerm().intValue());
				contract.setContractTermTotal(project.getTerm().intValue());
			}
			contract.setContractTermUnit(project.getTermUnit());
			contract.setContractTermUnitTotal(project.getTermUnit());
			//add 2015-1-30
			contract.setContractNatureCd(dataDict.getCodeVal("BussinessProperty", "S1"));//新增合同
			// 设置发起者
			contract.setApplyUserNum(project.getCustomerManagerNum());
			// 设置贷款产品
			contract.setProductType(project.getProductType());
			// 设置检查状态
			contract.setCheckStatus("1");
			// contract.setApplyDeptId(project.);
			contract.setCurrency(project.getCurrency());
			contract.setPreRepaymentRate(project.getPreRepaymentRate());
			String applyOrg = project.getApplyOrg();
			if (StringUtils.isNotBlank(applyOrg) && !"null".equals(applyOrg)) {
				contract.setApplyOrgId(Long.valueOf(applyOrg));
			}
			// 设置客户Id
			contract.setPartyId(project.getPartyId());

			// 设置批复还款方式
			contract.setRepayModeCd(project.getApproveRepayingMode());
			if (project.getReplyPeriodNum() != null) {
				contract.setRepayPrincipalMonthes(project.getReplyPeriodNum()
						.intValue());
			}
			// 设置合同性质
			if (project.getBusinessProcessType() != null) {
				contract.setContractNatureCd(project.getBusinessProcessType());
			}
			contract.setLoanDateStyle(project.getLoanDateStyle());
			// 设置时间属性
			java.util.Date currency = new Date();
			currency = uniqueCustomerService.getDBTime();
			contract.setSysCreateDate(currency);
			contract.setSysUpdateDate(currency);
			BizRate bizRate = contractMngService.findBizRateByProjectId(project
					.getProjectId());
			// 审批后的贷款年利率
			contract.setContractRate(bizRate.getApproveRateValue());
			// 逾期加减值
			BigDecimal ovdueIrNegoRate = bizRate.getOvdueIrNegoRate();
			ovdueIrNegoRate = MathUtil.BDadd(new BigDecimal("1"),
					(ovdueIrNegoRate == null) ? new BigDecimal("0")
							: ovdueIrNegoRate, 6);
			// 逾期利率=贷款利率*(1+逾期加减值)
			if (bizRate.getApproveRateValue() != null) {
				contract.setContractOvdueRate(MathUtil.BDmultiply(
						bizRate.getApproveRateValue(), ovdueIrNegoRate, 6)); // 逾期贷款利率
			}
			Long contractId = contractMngService.saveContract(contract);
			contract.setInitContractId(contractId);
			contractMngService.saveContract(contract);
			request.setAttribute("contractId", contractId);
		} else {
			request.setAttribute("contractId", oldContract.getContractId());
		}
		request.setAttribute("projectId", projectId);
		request.setAttribute("projectNo", project.getProjectNo());
		request.setAttribute("partyType", partyType);
		request.setAttribute("allDocType", DocStageCode.HT_DOC.getCodeId());
		//add by wangyawei 20150701 start 增加邦农贷产品标识，若贷款产品为邦农贷，返回true；否则，返回false
		//根据产品代码判断是不是农贷
		final boolean farmerLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.FARMER_LOAN_PRODUCT_MARK, project.getProductType());
		//根据团结贷
		final boolean unityLoanProductMark=businessApplicationService.getProductLoanFlag(GlobalConstants.UNITY_LOAN_PRODUCT_MARK, project.getProductType());
		boolean agriculturalLoan = farmerLoanProductMark||unityLoanProductMark;//是否是农贷界面
		request.setAttribute("agriculturalLoan", agriculturalLoan);
		//add by wangyawei 20150701 end
		switch (Integer.parseInt(workFlowType)) {
		case 1004: {// 微贷
			return FORM_CONTRACT_ML;
		}
		case 1003: {// 易贷
			return FORM_CONTRACT_EL;
		}
		default:
			return DASHBOARD;
		}

	}
	@RequestMapping("/checkWorkflowType")
	@ResponseBody
	public Result checkWorkflowType(@RequestParam String projectId){
		ProjectApplication project = contractMngService.getProject(Long
				.valueOf(projectId));
		String workFlowType = contractMngService
				.findWorkFlowTypeByProductCd(project.getProductType());
		if(StringUtils.isNotBlank(workFlowType)){
			return new Result(true, "", workFlowType);
		}else{
			return new Result(false, "该数据为小贷数据，请登录小贷系统查看！", null);
		}
	}
	
	
	/**
	 * 发放贷款的合同详情 跳转到Form页面之前的操作
	 * 
	 * @param request
	 *            HttpServletRequest的对象
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-31 9:37:51
	 */
	@RequestMapping("/viewDetail")
	public String viewDetail(@RequestParam String projectId,
			HttpServletRequest request) {
		Contract oldContract = contractMngService.findContractByProjectId(Long
				.valueOf(projectId));
		ProjectApplication project = contractMngService.getProject(Long
				.valueOf(projectId));
		String workFlowType = contractMngService
				.findWorkFlowTypeByProductCd(project.getProductType());
		request.setAttribute("partyId", project.getPartyId());
		request.setAttribute("contractId", oldContract.getContractId());
		request.setAttribute("projectId", projectId);
		request.setAttribute("workflowId", project.getWorkflowId());
		request.setAttribute("projectNo", project.getProjectNo());
		request.setAttribute("workType", "TODETAIL");
		Party party=partyDao.findOne(project.getPartyId());
		if(party!=null){
			request.setAttribute("partyType",party.getPartyTypeCd());
		}
		// Properties prop =
		// Utils.loadPropertiesFileFromClassPath(UPLOAD_TARGET);
		request.setAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
			switch (Integer.parseInt(workFlowType)) {
			case 1004: {// 微贷
				return FORM_CONTRACT_ML;
			}
			case 1003: {// 易贷
				return  FORM_CONTRACT_EL;
			}
			default:
				return  DASHBOARD;
			}
	}

	@RequestMapping("/viewDetailByContractNum")
	public String viewDetailByContractNum(@RequestParam String contractNum,
			HttpServletRequest request) throws UnsupportedEncodingException {
		contractNum = URLDecoder.decode(contractNum, "UTF-8");
		Contract oldContract = contractMngService
				.findByContractNum(contractNum);
		ProjectApplication project = contractMngService.getProject(oldContract
				.getProjectId());
		String workFlowType = contractMngService
				.findWorkFlowTypeByProductCd(project.getProductType());
		request.setAttribute("partyId", project.getPartyId());
		request.setAttribute("contractId", oldContract.getContractId());
		request.setAttribute("projectId", oldContract.getProjectId());
		request.setAttribute("projectNo", project.getProjectNo());
		request.setAttribute("workType", "TODETAIL");
		request.setAttribute("showBar", false);
		// Properties prop =
		// Utils.loadPropertiesFileFromClassPath(UPLOAD_TARGET);
		request.setAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		switch (Integer.parseInt(workFlowType)) {
		case 1004: {// 微贷
			return FORM_CONTRACT_ML;
		}
		case 1003: {// 易贷
			return FORM_CONTRACT_EL;
		}
		default:
			return DASHBOARD;
		}
	}

	@RequestMapping("/changeContract")
	public String changeContract(@RequestParam String projectId, @RequestParam String contractId,
			ModelMap model){
		model.addAttribute("projectId", projectId);
		model.addAttribute("contractId", contractId);
		return "contract/contract/change/change";
	}
	
	/*********************************** 合同表的相关方法 *******************************************/
	/**
	 * 合同表的查询方法
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
	 * @lastModified lijing 2014-08-23 15:30:50
	 */
	@RequestMapping("/findBySearch")
	@ResponseBody
	public DataTablesPage findBySearch(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String cuserId = request.getParameter("cuserId");
		String customerName = request.getParameter("customerName");
		String payloanStatus = request.getParameter("payloanStatus");
		String hasUploadIou = request.getParameter("hasUploadIou");
		List<Object> params = new ArrayList<Object>();
		params.add(cuserId);
		params.add(customerName);
		params.add(payloanStatus);
		params.add(hasUploadIou);
		Page queryList = contractMngService.findContractBySearch(
				(firstIndex / pageSize) + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	@RequestMapping("/abolishContract")
	@ResponseBody
	public String abolishContract(@RequestParam String contractId) {
		Contract contract = contractMngService.getContract(Long
				.valueOf(contractId));
		if ("3".equals(contract.getContracValidState())) {
			return "contractAlreadyAbolishedError";
		}
		if (!dataDict.getCodeVal(CODETYPE_CONTRACTSTATUS, "S1").equals(
				contract.getContractStatusCd())) {
			return "contractNotSignedError";
		}
		// 项目需要把相关联的文档状态由“引用”改成“创建”
		documentMngService.updateCreateTypeAll(contract.getPartyId(),
				dataDict.getCodeVal(CODETYPE_DTCREATETYPE, "S1"));
		// 释放抵质押金额
		renewaPledgeService.handlePledgeAmtWhenContractEnd(contract
				.getProjectId());
		contract.setIfReleaseFlag("1");// 抵质押物已释放且金额已扣减的标志
		contract.setContracValidState("3"); // 废除状态:3
		contract.setContractStatusCd(dataDict.getCodeVal(
				CODETYPE_CONTRACTSTATUS, "S11")); // 合同失效
		contractMngService.saveContract(contract);
		return "success";
	}

	/**
	 * 查出合同信息
	 * 
	 * @param projectId
	 *            业务id
	 * @param contractId
	 *            合同id
	 * @return contractVO 合同vo
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-14 9:37:51
	 */
	@RequestMapping("/getContractInfo")
	@ResponseBody
	public ContractVO getContractInfo(
			@RequestParam("projectId") String projectId,
			@RequestParam("contractId") String contractId) {
		BizRate bizRate = contractMngService.findBizRateByProjectId(Long
				.valueOf(projectId));
		Contract contract = contractMngService.findContractByProjectId(Long
				.valueOf(projectId));
		ProjectApplication project = businessApplicationService
				.searchProjectApplication(Long.valueOf(projectId));
		ContractVO contractVO = new ContractVO();
		org.springframework.beans.BeanUtils
				.copyProperties(contract, contractVO);
		org.springframework.beans.BeanUtils.copyProperties(bizRate, contractVO);
		org.springframework.beans.BeanUtils.copyProperties(project, contractVO);

		// 处理账户
		if (StringUtils.isBlank(contractVO.getBankName())) {
			CustomerAccountManagent cam = null;
			List<CustomerAccountManagent> list = uniqueCustomerService
					.findAccountManagentListByPartyId(contract.getPartyId()
							.toString());
			if (CollectionUtils.isNotEmpty(list)) {
				for (CustomerAccountManagent _cam : list) {
					if (cam == null
							|| cam.getAccountId().compareTo(cam.getAccountId()) > 0) {
						cam = _cam;
					}
				}
				contractVO.setBankName(cam.getBankAccount());
				contractVO.setLoanNum(cam.getAccountNum());
			}
		}

		contractVO.setArrangeRepayDay(contract.getArrangeRepayDay() + "");
		contractVO.setLoanDateStyle(contract.getLoanDateStyle());

		if (contractVO.getFinalRateValue() != null)
			contractVO.setFinalRateValue(contractVO.getFinalRateValue()
					.movePointRight(2));
		if (contractVO.getApproveRateValue() != null)
			contractVO.setApproveRateValue(contractVO.getApproveRateValue()
					.movePointRight(2));
		// 挪用利率上浮比例(%)
		if (bizRate.getPerculIrNegoRate() != null)
			contractVO.setPerculIrNegoRate(bizRate.getPerculIrNegoRate()
					.movePointRight(2));
		// 逾期利率上浮比例(%)
		if (bizRate.getOvdueIrNegoRate() != null)
			contractVO.setOvdueIrNegoRate(bizRate.getOvdueIrNegoRate()
					.movePointRight(2));
		if (contractVO.getFinalFloatRate() != null)
			contractVO.setFinalFloatRate(contractVO.getFinalFloatRate()
					.movePointRight(2));
		// 翻译
		String workFlowType = contractMngService
				.findWorkFlowTypeByProductCd(contractVO.getProductType());
		if (StringUtils
				.equals(WorkFlowCode.EASY_LOAN.getCodeId(), workFlowType)) {
			contractVO.setPurpose(dataDict.getCodeName("LoanPurpose",
					contractVO.getPurpose()));
		}
		String productName = businessApplicationService
				.findProductNameByProductCd(contractVO.getProductType());
		contractVO.setProductType(productName);
		String repayMode = dataDict.getCodeName("RepaymentMode",
				contractVO.getRepayModeCd());
		if (StringUtils.isNotBlank(repayMode)) {
			contractVO.setRepayModeCd(repayMode);
		}

		// contractVO.setLoanPurposeFeature(dataDict.getCodeName("LoanPurposeKind",
		// contractVO.getLoanPurposeFeature()));
		contractVO.setLoanPurposeKind(dataDict.getCodeName("LoanPurposeKind",
				contractVO.getLoanPurposeKind()));
		String businessType = project.getBusinessType();
		if (dataDict.getCodeVal("BusinessTypeCD", "S2").equals(businessType)) {
			contractVO.setCustomerType(dataDict
					.getCodeName("CustomerType", "1"));
		} else if (dataDict.getCodeVal("BusinessTypeCD", "S1").equals(
				businessType)) {
			contractVO.setCustomerType(dataDict
					.getCodeName("CustomerType", "2"));
		}
		contractVO.setContractTerm(contract.getContractTerm()
				+ dataDict.getCodeName("TermUnitCd",
						contract.getContractTermUnit()));
		// contractVO.setApproveIrTypeCd(dataDict.getCodeName("InterestRateAdjustment",
		// contractVO.getApproveIrTypeCd()));
		// 投放行业
		contractVO.setInvestmentIndustry(contractMngService
				.findIndustryNameByIndustryCd(contractVO
						.getInvestmentIndustry()));
		return contractVO;
	}

	/**
	 * 根据合同Id修改合同
	 * 
	 * @param ContractSaveVO
	 *            前台传来的表单对象，注：接收的不是实体而是VO
	 * @param contractId
	 *            合同Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50 注：这里用VO是为了防止更新时覆盖掉表单中没有的字段数据
	 */
	@RequestMapping("/saveContractInfo")
	@ResponseBody
	public Result saveContractInfo(
			@ModelAttribute ContractSaveVO contractSaveVO,
			@RequestParam String contractId) {
		// Individual motoIndividual = customerIndividualService.get(partyId);
		
		Contract oldContract = contractMngService.getContract(Long.valueOf(contractId));
		ProjectApplication project = businessApplicationService.searchProjectApplication(oldContract.getProjectId());
		try {
			//检查申请金额是否满足批量额度
			businessApplicationService.checkApplyAmountIsAvailable(oldContract.getContractAmt(),
					Long.valueOf(project.getProductType()),
					Long.valueOf(project.getApplyOrg()));
		} catch (Exception e) {
			logger.info("保存合同信息：检查申请金额是否满足批量额度",e.getMessage());
			return failure(e.getMessage() != null ? e.getMessage() : "保存合同信息失败！");
		}
		
		BizRate bizRate = contractMngService.findBizRateByProjectId(oldContract.getProjectId());
		oldContract.setBankName(contractSaveVO.getBankName());
		oldContract.setLoanNum(contractSaveVO.getLoanNum());
		
		if (contractSaveVO.getLoanDateStyle().equals("1")) {
			oldContract.setArrangeRepayDay(Integer.valueOf(contractSaveVO.getArrangeRepayDay()));
		}
		oldContract.setLoanDateStyle(contractSaveVO.getLoanDateStyle());
		oldContract.setContractStatusCd(dataDict.getCodeVal("ContractStatusCode", "S8"));
		oldContract.setSysCreateDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd")); // 合同创建日期
		oldContract.setSysUpdateDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd"));
		BigDecimal interestRate = saveBizInfo(contractSaveVO, project, bizRate);
		if (interestRate != null) {
			oldContract.setInterestRate(interestRate.divide(new BigDecimal("100"))); // 基准利率值
		}

		oldContract.setContractRate(bizRate.getFinalRateValue());// 最终的贷款年利率
		BigDecimal ovdueIrNegoRate = bizRate.getOvdueIrNegoRate(); // 逾期加减值
		
		if (ovdueIrNegoRate != null && oldContract.getContractRate() != null) {
			ovdueIrNegoRate = new BigDecimal(MathUtil.BDadd(1,
					ovdueIrNegoRate.doubleValue(), 6));
			// 逾期利率=贷款利率*(1+逾期加减值)
			oldContract.setContractOvdueRate(MathUtil.BDmultiply(
					oldContract.getContractRate(), ovdueIrNegoRate, 6)); // 逾期贷款利率
		}
		bizRate.setFinalAdjustPeriod("1");
		// bizRate.setFinalRateValue(new
		// BigDecimal(contractSaveVO.getFinalRateValue()));
		// bizRate.setRateAdjustmentReason(contractSaveVO.getRateAdjustmentReason());
		contractMngService.saveContract(oldContract);
		contractMngService.saveBizRate(bizRate);

		// 处理从合同
		// 查询
		List<SubContract> subContractList = contractMngService
				.findSubcontractList(oldContract.getProjectId());
		List<ProjectAssurerInfo> assurerList = contractMngService
				.findProjectAssurerByPid(oldContract.getProjectId());
		// 新增从合同列表
		String subcontractNum = "";
		String businessType = project.getBusinessType();
		String customerType = "";
		if (StringUtils.isNotBlank(businessType)) {
			if (dataDict.getCodeVal("BusinessTypeCD", "S1")
					.equals(businessType)) { // 个贷
				customerType = "1";
			} else if (dataDict.getCodeVal("BusinessTypeCD", "S2").equals(
					businessType)) { // 企贷
				customerType = "0";
			}
		}

		if (CollectionUtils.isEmpty(subContractList)) {
			// 抵质押
			SubContract subcontract = null;
			List<ProjectPawnInfo> pawnList = collateralService
					.findProjectPawnInfoListByProjectId(project.getProjectId());
			if (CollectionUtils.isNotEmpty(pawnList)) {

				ProjectPawnInfo projectPawnInfo = null;
				Integer dycount = 0;
				Integer zycount = 0;
				for (ProjectPawnInfo ppi : pawnList) {
					subcontract = new SubContract();
					String guaranteeType = ppi.getGuaranteeType();
					if (StringUtils.isNotBlank(guaranteeType)
							&& !"null".equals(guaranteeType)) {
						if (dataDict.getCodeVal("MortgageType", "S1").equals(
								guaranteeType)) { // 担保方式--抵押
							dycount = dycount + 1;
							subcontractNum = commonBizNumberBS
									.generateSubContractNumber(
											oldContract.getContractNum(), "抵",
											dycount);
						} else if (dataDict.getCodeVal("MortgageType", "S2")
								.equals(guaranteeType)) { // 担保方式--质押
							zycount = zycount + 1;
							subcontractNum = commonBizNumberBS
									.generateSubContractNumber(
											oldContract.getContractNum(), "质",
											zycount);
						}
					}
					this.assignSubContract(subcontract, oldContract, ppi);
					subcontract.setSubcontractNum(subcontractNum);
					this.contractMngService.saveSubContract(subcontract);
				}
			}
			// 保证人
			if (CollectionUtils.isNotEmpty(assurerList)) {
				int bzcount = 0;
				for (ProjectAssurerInfo pai : assurerList) {
					pai.setActualGuaranteeAmt(oldContract.getContractAmt()); // 实际保证金额
					subcontract = new SubContract();
					this.assignSubContract(subcontract, oldContract, null);
					bzcount = bzcount + 1;
					subcontractNum = commonBizNumberBS
							.generateSubContractNumber(
									oldContract.getContractNum(), "保", bzcount);
					subcontract.setSubcontractNum(subcontractNum);
					subcontract.setGuarantCustomerNum(pai.getCustomerNum());
					subcontract.setGuarantyAmt(oldContract.getContractAmt()); // 本次实际保证债权金额
					if (pai.getProjectAssurerInfoId() != null) {
						subcontract.setAssurerId(pai.getProjectAssurerInfoId());
					}
					subcontract.setGuarantyNum(pai.getCustomerNum());
					subcontract.setGuaranteeTypeCd(pai.getAssurerType()); // 担保方式
					subcontract.setSubcontractTypeCd(dataDict.getCodeVal(
							"SubcontractType", "S2")); // 从合同类型代码 add by youjg
					this.contractMngService.saveSubContract(subcontract);
					this.contractMngService.saveProjectAssurerInfo(pai);
				}
			}
		} else {
			// 抵质押、保证人
			for (SubContract subcontract : subContractList) {
				subcontract.setStartDate(oldContract.getStartDate());
				subcontract.setExpirationDate(oldContract.getExpirationDate());
				subcontract.setSysUpdateDate(DateTools.stringToDate(
						DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd"));
				this.contractMngService.saveSubContract(subcontract);
			}
		}

		// 重新计算担保金额：担保金额=抵质押之和 和保证金额(单个)做比较，取最大值
		BigDecimal guaranteeAmt = contractMngService
				.caculatePledgeSumByProjectId(project.getProjectId(), null,
						dataDict.getCodeVal("CtrlIndicator", "S2"));
		BigDecimal assuerAmt = new BigDecimal("0");
		if (CollectionUtils.isNotEmpty(assurerList)) {
			ProjectAssurerInfo pai = (ProjectAssurerInfo) assurerList.get(0);
			if (pai != null && pai.getActualGuaranteeAmt() != null)
				assuerAmt = pai.getActualGuaranteeAmt();
		}
		if (guaranteeAmt != null && assuerAmt.compareTo(guaranteeAmt) > 0) {
			guaranteeAmt = assuerAmt;
		}
		project.setGuaranteeAmt(guaranteeAmt);
		businessApplicationService.saveProjectApplication(project);
		contractMngService.saveContract(oldContract);

		return success("保存合同信息成功！");
	}

	public BigDecimal saveBizInfo(ContractSaveVO contractSaveVO,
			ProjectApplication project, BizRate bizRate) {
		BigDecimal interestRate = null;
		if (null != bizRate) {
			String irTypeCd = bizRate.getFinalIrTypeCd(); // 利率类型及调整方式
			// bizRate.setFinalIrTypeCd(irTypeCd); // 最终利率类型
			if (StringUtils.isNotBlank(irTypeCd) && !"null".equals(irTypeCd)) {
				if (dataDict.getCodeVal("InterestRateAdjustment", "S2").equals(
						irTypeCd)) { // 浮动利率
					bizRate.setFinalAdjustPeriod(bizRate.getFinalAdjustPeriod()); // 最终调整周期
					Integer term = project.getTerm();
					String termUnit = project.getTermUnit();
					MoneyRate moneyRate = businessApplicationService
							.findValidMoneyRate(term.toString(), termUnit);
					interestRate = moneyRate.getInterestRate(); // 基准利率
					// contractVo.setInterestRate(interestRate);
					BigDecimal finalFloatRate = new BigDecimal(
							contractSaveVO.getFinalFloatRate())
							.divide(new BigDecimal("100")); // 利率加减值
					if (null != finalFloatRate) {
						bizRate.setFinalFloatRate(finalFloatRate); // 最终上浮比例值
						finalFloatRate = MathUtil.BDadd(new BigDecimal("1"),
								finalFloatRate, 6);
						bizRate.setFinalRateValue(MathUtil.BDmultiply(
								interestRate, finalFloatRate, 6)); // 贷款利率
					}
				} else if (dataDict.getCodeVal("InterestRateAdjustment", "S1")
						.equals(irTypeCd)) {
					bizRate.setFinalRateValue(new BigDecimal(contractSaveVO
							.getFinalRateValue()).divide(new BigDecimal("100")));
				}
			} else {
				return null;
			}
		}
		bizRate.setRateAdjustmentReason(contractSaveVO
				.getRateAdjustmentReason());
		contractMngService.saveBizRate(bizRate);
		return interestRate;
	}

	/**
	 * 根据合同Id保存意见
	 * 
	 * @param ContractSaveVO
	 *            前台传来的表单对象，注：接收的不是实体而是VO
	 * @param contractId
	 *            合同Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50 注：这里用VO是为了防止更新时覆盖掉表单中没有的字段数据
	 */
	@RequestMapping("/saveSuggest")
	@ResponseBody
	public String saveSuggest(@ModelAttribute ContractSaveVO contractSaveVO,
			@RequestParam("contractId") String contractId) {
		Contract oldContract = contractMngService.getContract(Long
				.valueOf(contractId));
		oldContract.setFulfillInstructionCd(contractSaveVO
				.getFulfillInstructionCd());
		contractMngService.saveContract(oldContract);
		return "success";
	}

	/**
	 * 检查合同是否具备提交的条件
	 * 
	 * *
	 */

	@RequestMapping("/checkContractReady")
	@ResponseBody
	public Result checkContractReady(
			@RequestParam("contractId") String contractId,
			@RequestParam("taskId") String taskId,
			@RequestParam("workflowId") String workflowId,
			@RequestParam("comments") String comments) {
		Contract contract = contractMngService.getContract(Long
				.valueOf(contractId));
		boolean documentFlag = false;
		List<DocumentIndex> documents = contractMngService
				.findDocumentIndexByPartyId(contract
						.getPartyId(), contract.getProjectId());
		if (documents.size() > 0) {
			for (DocumentIndex documentIndex : documents) {
				if ("27".equals(documentIndex.getDocumentType())) {
					documentFlag = true;
				}
			}
		}
		if (StringUtils.isBlank(comments)) {
			return new Result(false, "请填写意见！", null);
		}
		if (StringUtils.isBlank(contract.getBankName())) {
			return new Result(false, "请先保存合同信息！", null);
		}
		if (!documentFlag) {
			return new Result(false, "请先上传主合同文本！", null);
		}
		// 检查从合同文本是否上传完全
		Result docStatus = this.checkContractDoc(contract.getProjectId());
		if (!docStatus.getSuccess()) {
			return docStatus;
		}
		return new Result(true);
	}

	/**
	 * 提交合同，更改合同状态
	 * 
	 * @param contractId
	 *            合同Id
	 * @return String 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-15 13:30:50
	 */
	@RequestMapping("/submitContract")
	@ResponseBody
	public Result submitContract(
			@RequestParam("contractId") String contractId,
			@RequestParam("taskId") String taskId,
			@RequestParam("workflowId") String workflowId,
			@RequestParam(value = "nextUser", required = false) String nextUser,
			@RequestParam(value = "nextUserOrgId", required = false) String nextUserOrgId,
			@RequestParam("comments") String comments,
			@RequestParam("instruction") String instruction) {
		Contract contract = contractMngService.getContract(Long
				.valueOf(contractId));

		contract.setInitContractId(contract.getContractId());
		contract.setStartDate(uniqueCustomerService.getDBTime());
		contract.setSysUpdateDate(uniqueCustomerService.getDBTime());
		contract.setContractStatusCd(dataDict.getCodeVal(
				CODETYPE_CONTRACTSTATUS, "S8"));
		contract.setFulfillInstructionCd(instruction);
		String userId = ((ShiroUser) SecurityUtils.getSubject().getPrincipal())
				.getLogname();
		taskId = taskId.substring(taskId.lastIndexOf("-") + 1, taskId.length());
		String result;
		Result msg = null;
		try {
			String workFlowType = contractMngService
					.findWorkFlowTypeByProductCd(contract.getProductType());
			// 处理流程
			msg = contractMngService.submitWorkFlow(contract.getProjectId(),
					workFlowType, workflowId, taskId, userId, nextUser,nextUserOrgId,
					comments);

			// nextUsers =
			// businessApplicationService.getLeastTaskPerson(EL_WF_TASK_ID_3015);
			// if (StringUtils.equals("300", contract.getProductType())
			// || StringUtils.equals("263", contract.getProductType())) {
			// // nextUsers = nextUser;
			// nextUsers = nextUser;
			// }
			// result = contractMngService.submitContract(contract, workflowId,
			// userId, taskId, contract.getFulfillInstructionCd().trim(),
			// nextUsers);
			// if (!"success".equals(result)) {
			// return "workFlowCatchError";
			// }
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "提交失败！请联系管理员", null);
		}
		if (msg != null) {
			return msg;
		} else {
			return new Result(false, "提交失败！请联系管理员", null);
		}
	}

	/**
	 * 根据组织机构id查找贷款人信息
	 * 
	 * @param orgId
	 *            组织机构Id
	 * @return List
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	public List<String> findLoanerInfo(Long orgId) {
		List<String> list = new ArrayList<String>();
		list = contractMngService.getLoanerInfo(orgId);
		return list;
	}

	/**
	 * 按下调整还款计划按钮
	 * 
	 * @param contractId
	 *            　合同id
	 * @return List
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/updateContract")
	@ResponseBody
	public String updateContract(String contractId) {
		Contract contract = contractMngService.getContract(Long
				.valueOf(contractId));
		String contractStatusCode = contract.getContractStatusCd();
		if (StringUtils.isNotBlank(contractStatusCode)
				&& !"null".equals(contractStatusCode)) {
			if (!(dataDict.getCodeVal(CODETYPE_CONTRACTSTATUS, "S2").equals(
					contractStatusCode)
					|| "2".equals(contractStatusCode) || "3"
						.equals(contractStatusCode))) {
				return "error";
			}
		}
		return "repayPlan/moveToEdit?contractId=" + contractId;
	}

	/**
	 * 从合同列表的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/searchSubContractList")
	@ResponseBody
	public DataTablesPage searchSubContractList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String projectId = (String) request.getParameter("projectId");
		Contract contract = contractMngService
				.findContractByProjectId(new Long(projectId));
		DataTablesPage page = null;
		if (null != contract && null != contract.getContractId()) {
			Page queryList = contractMngService.getAssureListByContractId(
					contract.getContractId(), (firstIndex / pageSize) + 1,
					pageSize);
			page = new DataTablesPage(sEcho, queryList);
		}
		return page;
	}

	/************************************ 抵质押物表相关方法 *************************************************************/

	/**
	 * 抵质押物列表的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/searchCollateralList")
	@ResponseBody
	public DataTablesPage searchCollateralList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String projectId = (String) request.getParameter("projectId");
		Page queryList = contractMngService.findCollateralsBySearch(projectId,
				(firstIndex / pageSize) + 1, pageSize);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 从合同页面的抵质押列表的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/searchPresentCollateralList")
	@ResponseBody
	public DataTablesPage searchPresentCollateralList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String projectId = (String) request.getParameter("projectId");
		Contract contract = contractMngService
				.findContractByProjectId(new Long(projectId));
		DataTablesPage page = null;
		if (null != contract && null != contract.getContractId()) {
			//modify by 
			/*Page queryList = contractMngService.getPawnListByContractId(
					contract.getContractId(), contract.getProjectId(),
					(firstIndex / pageSize) + 1, pageSize);*/
			Page queryList = contractMngService.getPawnListByContractId(
					contract.getContractId(),(firstIndex / pageSize), pageSize);
			page = new DataTablesPage(sEcho, queryList);
		}
		return page;
	}

	/************************************ 共同借款人相关方法 ********************************************************/
	/**
	 * 共同借款人的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/searchBorrowerList")
	@ResponseBody
	public DataTablesPage searchBorrowerList(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		String projectId = (String) request.getParameter("projectId");
		ProjectApplication pa = businessApplicationService
				.searchProjectApplication(Long.valueOf(projectId));
		if (pa != null && pa.getParentProjectId() != null) {
			Long count = new Long("0");
			count = contractMngService.getCommonCountByProjectId(pa
					.getParentProjectId().toString());
			if (count > 0) {
				projectId = pa.getParentProjectId().toString();
			}
		}
		Page queryList = contractMngService.findBorrowerListBySearch(projectId,
				(firstIndex / pageSize) + 1, pageSize);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 改变从合同上传文本状态
	 * 
	 * @param subcontractId
	 *            从合同状态
	 * @return string 修改是否成功
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/changeIsUpload")
	@ResponseBody
	public String changeIsUpload(@RequestParam String subcontractId) {
		SubContract subcontract = contractMngService
				.findSubContractBySubId(Long.valueOf(subcontractId));
		if (!StringUtils.equals(dataDict.getCodeVal("CommonWhether", "S1"),
				subcontract.getIsTransDocument())) {
			subcontract.setIsTransDocument(dataDict.getCodeVal("CommonWhether",
					"S1"));
			contractMngService.saveSubContract(subcontract);
		}
		return "success";
	}

	/*********************************** 客户账户表的相关方法 *******************************************/
	/**
	 * 查出客户的账户信息
	 * 
	 * @param partyId
	 *            客户id
	 * @return List 账户信息list
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-14 10:37:51
	 */
	@RequestMapping("/getCustomerAccount")
	@ResponseBody
	public List<CustomerAccountManagent> getCustomerAccount(
			@RequestParam("partyId") String partyId) {
		return contractMngService.findAccountsByPartyId(Long.valueOf(partyId));
	}

	/**
	 * 根据账户id查询账号
	 * 
	 * @param partyId
	 *            客户id
	 * @return List 账户信息list
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-14 10:37:51
	 */
	@RequestMapping("/getAccountNum")
	@ResponseBody
	public List<String> getAccountNum(
			@RequestParam("accountId") String accountId) {
		return contractMngService.findAccountNumByAccountId(Long
				.valueOf(accountId));
	}

	/*********************************** 费用表的相关方法 *******************************************/
	/**
	 * 费用列表（）的查询方法
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
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	@RequestMapping("/findExpenseBySearch")
	@ResponseBody
	public DataTablesPage findExpenseBySearch(
			@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String projectId = (String) request.getParameter("projectId");
		List<Object> params = new ArrayList<Object>();
		Page queryList = contractMngService.findExpenseBySearch(projectId,
				(firstIndex / pageSize) + 1, pageSize, params);
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/*********************************** 文档表的相关方法 *******************************************/
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
		String documentName = request.getParameter("query_documentName");
		String contentType = request.getParameter("query_contentType");
		List<Object> params = new ArrayList<Object>();
		// String customerName =
		// uniqueCustomerService.findPartyByPartyId(partyId).getPartyName();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		params.add("('05','27','02','06','10','40','41','42','43','44','45','13','14','15','16','17','29')");
		params.add(projectId);
		params.add(partyId);
		params.add(documentName);
		if (StringUtils.isNotBlank(contentType)) {
			params.add(contentType);
		} else {
			params.add("");
		}

		List<Object[]> finalList = new ArrayList<Object[]>();
		Page queryList = documentService.findDocumentsInApproval(
				(firstIndex / pageSize) + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		for (Object[] obj : resultList) {
			// obj[4] = customerName;
			// obj[5] = curUser.getName();
			obj[6] = timeStampToString((Timestamp) obj[6]);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
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
	public Result delDocument(@PathVariable String id) {
		DocumentIndex documentIndex = uniqueCustomerService
				.findDocumentIndexByDocumentId(id);
		String documentType = documentIndex.getDocumentType();
		// “引用”状态的文档不能删除
		if(StringUtils.equals(dataDict.getCodeVal("DocumentType", "S10"), documentType)){
			//从合同
			SubContract sc = contractMngService.findByDocumentNum(documentIndex.getDocumentNum());
			if(sc!=null && "1".equals(sc.getIsTransDocument())){
				sc.setIsTransDocument("2");//改从合同文本上传为未上传
				sc.setDocumentNum("");
				contractMngService.saveSubContract(sc);
			}
		}
		if (dataDict.getCodeVal(CODETYPE_DTCREATETYPE, "S2").equals(
				documentIndex.getCreateTypeCd())) {
			return new Result(false, "引用状态文档不能删除", null);
		}
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		documentIndex.setSystemUpdateTime(new Timestamp(new Date().getTime()));
		documentIndex.setUserNum(curUser.getId().toString());
		documentIndex.setStatus("2");
		uniqueCustomerService.saveDocumentIndex(documentIndex);
		return new Result(true);
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
	public DocumentUploadVO beforeUpdate(
			@RequestParam String partyId,
			@RequestParam String projectId,
			@RequestParam String uploadType,
			@RequestParam(value = "subcontractId", required = false) String subcontractId,
			HttpServletRequest request) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		documentUploadVO.setPartyId(partyId);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		// Individual individual = uniqueCustomerService.getIndividual(partyId);
		Party party = this.uniqueCustomerService.findPartyByPartyId(partyId);
		ProjectApplication project = businessApplicationService
				.searchProjectApplication(Long.valueOf(projectId));
		documentUploadVO.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		documentUploadVO.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		documentUploadVO.setCreateDateTime(timeStampToString(new Timestamp(
				new Date().getTime())));
		documentUploadVO.setCustomerNum(party.getCustomerNum());
		documentUploadVO.setBizNum(project.getProjectNo());
		documentUploadVO.setBizId(projectId);
		if (uploadType.equals("documents")) {
			documentUploadVO.setDocumentType("05");
		} else if (uploadType.equals("loanApply")) {
			documentUploadVO.setDocumentType("27");
		} else if (uploadType.equals("subcontract")) {
			if(StringUtils.isNotBlank(subcontractId)){
			documentUploadVO.setSubcontractId(subcontractId);
			}
			documentUploadVO.setDocumentType("10");
		}
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");
		documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(
				party.getCustomerNum(), "01"));
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

	/*********************************** 下载pdf的相关方法 *******************************************/
	/**
	 * 下载贷款申请表
	 * 
	 * @param contractId
	 *            合同Id
	 * @param projectId
	 *            业务id
	 * @param partyId
	 *            客户id
	 * @param request
	 *            HttpServletRequest
	 * @return String 是否成功
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/downloadLoanApply")
	public void downloadLoanApply(
			@RequestParam("contractId") String contractId,
			@RequestParam("projectId") String projectId,
			@RequestParam("partyId") String partyId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ProjectApplication project = businessApplicationService
				.searchProjectApplication(Long.valueOf(projectId));
		Contract contract = contractMngService.getContract(Long
				.valueOf(contractId));
		Party party = uniqueCustomerService.findPartyByPartyId(partyId);
		BizRate bizRate = contractMngService.findBizRateByProjectId(Long
				.valueOf(projectId));
		if (StringUtils.isBlank(contract.getBankName())
				|| (contract.getContractAmt() == null)
				|| (bizRate.getFinalRateValue() == null)) {
			System.out.println("*******************合同未保存或必输项没有值，无法生成pdf！！");
		}
		String root = request.getSession().getServletContext().getRealPath("/");
		Properties prop = Utils.loadPropertiesFileFromClassPath(DOC_TARGET);
		String filepath = root + prop.getProperty("pdf_template"); // pdf模板
		String filename = contract.getCustomerName() + "_"
				+ prop.getProperty("fileName");

		String downLoadName = null;
		String agent = request.getHeader("USER-AGENT");
		if (agent != null && agent.indexOf("Firefox") >= 0) {
			downLoadName = new String(filename.getBytes(), "iso-8859-1");
		} else {
			downLoadName = URLEncoder.encode(filename, "utf-8");
		}
		PdfReader reader = null;
		PdfStamper ps = null;
		try {
			response.addHeader("Content-Disposition", "attachment;filename="
					+ downLoadName);// 设置文件名
			response.setContentType("application/octet-stream");

			reader = new PdfReader(filepath);

			ps = new PdfStamper(reader, response.getOutputStream());

			AcroFields fields = ps.getAcroFields();
			fillData(fields, data(project, contract, bizRate, party));
			ps.setFormFlattening(true);

		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (ps != null) {
					ps.close();
				}

			} catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
		}
	}

	/*********************************** 本控制器所需的工具方法 *******************************************/
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 定义格式，不显示毫秒
		String str = "";
		if (st != null) {
			str = df.format(st);
		} else {
			str = df.format(new Date());
		}
		return str;
	}

	/**
	 * String转化成Timestamp的方法
	 * 
	 * @param time
	 *            String类型的变量
	 * @return Timestamp 返回Timestamp对象,格式yyyy-MM-dd
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	public Timestamp stringToTimestamp(String time) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setLenient(false);
		Timestamp ts = null;
		try {
			ts = new Timestamp(format.parse(time).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	/**
	 * 向pdf模板装载map的值
	 * 
	 * @param fields
	 *            pdf模板的文本域
	 * @param data
	 *            要装载的map
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	public void fillData(AcroFields fields, Map<String, String> data)
			throws IOException, DocumentException {
		for (String key : data.keySet()) {
			String value = data.get(key);
			fields.setField(key, value);
		}
	}

	/**
	 * 生成需装载进pdf模板Map对象
	 * 
	 * @param project
	 *            业务对象
	 * @param contract
	 *            合同对象
	 * @param bizRate
	 *            利率对象
	 * @param party
	 *            客户对象
	 * @return Map<String,String>
	 * @author lijing
	 * @lastModified lijing 2014-07-25 9:30:50
	 */
	public Map<String, String> data(ProjectApplication project,
			Contract contract, BizRate bizRate, Party party) {
		Map<String, String> data = new HashMap<String, String>();
		NumberFormat nf = NumberFormat.getInstance();

		if (party.getPartyTypeCd().equals(
				dataDict.getCodeVal("CustomerType", "S2"))) {
			Individual customerMsg = uniqueCustomerService.getIndividual(party
					.getPartyId().toString());
			data.put("brrowerName", customerMsg.getCustomerName());
			data.put("borrowerAddress", customerMsg.getFamilyAddress());
			data.put("borrorwerCompanyAddress", customerMsg.getCompanyAddress());
			data.put(
					"certificateType",
					dataDict.getCodeName(CODETYPE_CERTIFICATETYPECD,
							party.getCertificateTypeCd()));
			data.put("certificateNum", customerMsg.getCertificateNum());
			data.put("telephone", customerMsg.getTelphone2());
			data.put("mobile", customerMsg.getMobileTel());
		} else if (party.getPartyTypeCd().equals(
				dataDict.getCodeVal("CustomerType", "S1"))) {
			CorporationCustomer customerMsg = corpCusService.findById(party
					.getPartyId());
			data.put("brrowerName", customerMsg.getCustomerName());
			List<Address> addressList = corpCusService
					.getAddressByPartyId(party.getPartyId());
			if (CollectionUtils.isNotEmpty(addressList)) {
				Address addr = addressList.get(0);
				String corpAddress = dataDict.getCodeName("NationArea",
						addr.getNationalityCd())
						+ dataDict.getCodeName("NationArea",
								addr.getProvinceCd())
						+ dataDict.getCodeName("NationArea", addr.getCityCd())
						+ dataDict
								.getCodeName("NationArea", addr.getCountyCd());
				data.put("borrowerAddress", corpAddress);
				data.put("borrorwerCompanyAddress", corpAddress);
			}
			data.put(
					"certificateType",
					dataDict.getCodeName(CODETYPE_CERTIFICATETYPECD,
							party.getCertificateTypeCd()));
			data.put("certificateNum", customerMsg.getCertificateNum());
			data.put("telephone", customerMsg.getLinkmanTel());
			// data.put("mobile", customerMsg.getLinkmanTel());
		}

		List loanList = findLoanerInfo(contract.getApplyOrgId());
		if (loanList.size() > 0) {
			data.put("loanerName", (String) loanList.get(0));
			data.put("registedPlace", (String) loanList.get(1));
			data.put("chairman", (String) loanList.get(2));
		}
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		data.put("eyear", calendar.get(Calendar.YEAR) + "");
		data.put("emonth", calendar.get(Calendar.MONTH) + "");
		data.put("eday", calendar.get(Calendar.DATE) + "");
		calendar.add(Calendar.MONTH, contract.getContractTerm());
		data.put("dyear", calendar.get(Calendar.YEAR) + "");
		data.put("dmonth", calendar.get(Calendar.MONTH) + "");
		data.put("dday", calendar.get(Calendar.DATE) + "");
		data.put("loanUsage", project.getPurpose());
		// data.put("loanRateValue", bizRate.getFinalRateValue() + "");
		if (contract.getContractAmt() != null) {
			BigDecimal expense = contract
					.getContractAmt()
					.multiply(new BigDecimal(EXPENSE_RATE_PRE))
					.multiply(new BigDecimal(EXPENSE_RATE_BAK))
					.multiply(
							new BigDecimal(String.valueOf(contract
									.getContractTerm())));
			data.put("loanMoneyUpper",
					MoneyUtil.CNValueOf(contract.getContractAmt() + ""));
			data.put("loanNumLitter",
					nf.format(contract.getContractAmt().movePointLeft(4)) + "");
			data.put("poundageUpper", MoneyUtil.CNValueOf(expense + ""));
			data.put("poundageLitter", nf.format(expense).toString());
		}
		if (contract.getBankName() != null) {
			CustomerAccountManagent cam = uniqueCustomerService
					.findAccountManagentByPartyIdAndAccountNum(contract
							.getPartyId().toString(), contract.getLoanNum());
			if (cam != null) {
				data.put("bankName", cam.getBankAccount());
				data.put("accountName", cam.getAccountName());
				data.put("accountNum", cam.getAccountNum());
			}
		}
		if (contract.getArrangeRepayDate() != null) {
			data.put("jday", contract.getArrangeRepayDate() + "");
		}
		data.put("smonth", "");
		data.put("sday", "");
		data.put("firstRepayUpper", "");
		data.put("firstRepayLitter", "");
		data.put("everyRepayUpper", "");
		data.put("everyRepayLitter", "");
		data.put("lastRepayUpper", "");
		data.put("lastRepayLitter", "");
		return data;
	}

	/**
	 * 装载从合同
	 * 
	 * @param ppi
	 * **/
	private void assignSubContract(SubContract subcontract,
			Contract oldContract, ProjectPawnInfo ppi) {
		ShiroUser cuser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		BeanUtils.copyProperties(oldContract, subcontract);
		subcontract.setCustomerId(oldContract.getPartyId());
		subcontract.setTerm(oldContract.getContractTerm());
		subcontract.setTermUnitCd(oldContract.getContractTermUnit());
		subcontract.setSubcontractStatusCd(dataDict.getCodeVal(
				"ContractStatusCode", "S8"));
		//subcontract.setHandlingOrgCd(cuser.getOrgid());
		//获取当前登录人的登录机构ID lp
		subcontract.setHandlingOrgCd(cuser.getLogOrgid());//TODO
		subcontract.setHandlingUserNum(cuser.getId().toString());
		subcontract.setSysCreateDate(DateTools.stringToDate(
				DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd"));
		subcontract.setSysUpdateDate(DateTools.stringToDate(
				DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd"));
		if (ppi != null) {
			subcontract.setGuarantyRate(ppi.getActualGuaranteeRate()); // 实际担保率
			subcontract.setGuarantyAmt(ppi.getActualCreditAmount()); // 本次实际担保债权金额
			//modify by HWL 20150709从合同与抵质押关联错误  
			//subcontract.setGuarantyId(ppi.getGuarantyId());
			subcontract.setGuarantyId(ppi.getProjectPawnInfoId());
			//modify by HWL 20150709 从合同与抵质押关联错误
			subcontract.setGuarantyNum(ppi.getGuarantyNum());
			subcontract.setGuaranteeTypeCd(ppi.getGuaranteeType()); // 担保方式
			subcontract.setSubcontractTypeCd(dataDict.getCodeVal(
					"SubcontractType", "S1")); // 从合同类型代码 add by yjg
		}
	}

	/**
	 * 校验主合同下的从合同是否全部上传附件
	 * 
	 * @param projectId
	 * @return Result
	 */
	private Result checkContractDoc(Long projectId) {
		Result error = new Result(false, "请上传所有从合同！", null);
		Result success = new Result(true);
		boolean contractexist = false;

		List<SubContract> subContractList = contractMngService
				.findSubcontractList(projectId);
		if (CollectionUtils.isNotEmpty(subContractList)) {
			int count = 0;
			int length = subContractList.size();
			for (SubContract subcontract : subContractList) {
				if (StringUtils.equals(
						dataDict.getCodeVal("CommonWhether", "S1"),
						subcontract.getIsTransDocument())) {
					count++;
				}
			}
			if (count == length) {
				contractexist = true;
			}
		} else {
			contractexist = true;
		}

		if (contractexist) {
			return success;
		} else {
			return error;
		}
	}

}

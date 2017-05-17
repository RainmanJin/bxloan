package com.coamctech.bxloan.web.controller.contractmng;

import static com.coamctech.bxloan.commons.GlobalConstants.ADDRECORD;
import static com.coamctech.bxloan.commons.GlobalConstants.CONTRACTLIST;
import static com.coamctech.bxloan.commons.GlobalConstants.INIT_LOANGRANT;
import static com.coamctech.bxloan.commons.GlobalConstants.LOANGRANT;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JREmptyDataSource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Report;
import com.coamctech.bxloan.commons.result.ReportExtension;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.commons.utils.MathUtil;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.CustomerAccountManagent;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.contractmng.LoanGrantService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.PayLoanInfoVO;
import com.coamctech.bxloan.service.model.RepayLoanVO;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.service.usermng.UserMngService;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.customermng.DocumentUploadVO;

/**
 * 贷款发放控制器
 * 
 * @author lijing
 * @lastModified lijing 2014-09-02 17:30:24
 */
@Controller
@RequestMapping("/" + LOANGRANT)
public class LoanGrantController {
	
	/** 还款方式 */
	private final String CODETYPE_REPAYMENTMODE = "RepaymentMode";
	/** 合同状态 */
	private final String CODETYPE_CONTRACTSTATUS = "ContractStatusCode";
	/** 合同期限单位 */
	private final String CODETYPE_TERMUNIT = "TermUnitCd";
	/** 约定还款方式 */
	private final String CODETYPE_LOANDATESTYLE = "LoanDateStyle";
	/** 放款状态 */
	private final String CODETYPE_PAYLOANSATUS = "PayLoanStatus";
	
	@Autowired
	private ContractMngService contractMngService;
	@Autowired
	private LoanGrantService loanGrantService;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private UserMngService userMngService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private DataDict dataDict;

	/*********************************** 跳转方法 *******************************************/
	/**
	 * sideBar上点击贷款发放的跳转方法
	 * 
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-09-02 16:37:51
	 */
	@RequestMapping
	public String init(Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("cuserId", curUser.getId());
		return INIT_LOANGRANT;
	}

	/**
	 * 点击签署放款按钮后的跳转方法
	 * 
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-09-02 16:37:51
	 */
	@RequestMapping("/signList")
	public String signList(@RequestParam String contractId, @RequestParam String cuserId, Model model) {
		model.addAttribute("cuserId", cuserId);
		model.addAttribute("contractId", contractId);
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		model.addAttribute("currentUserName", curUser.getName());
		//Properties prop = Utils.loadPropertiesFileFromClassPath(UPLOAD_TARGET);
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);
		return CONTRACTLIST;
	}

	/**
	 * 跳转新增放款记录页面
	 * 
	 * @param contractId 合同id
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-09-02 16:37:51
	 */
	@RequestMapping("/addPayRecord")
	public String addPayRecord(@RequestParam String contractId, @RequestParam String cuserId, Model model) {
		model.addAttribute("contractId", contractId);
		model.addAttribute("cuserId", cuserId);
		return ADDRECORD;
	}

	/*********************************** 放款记录表的相关方法 *******************************************/
	/**
	 * 放款记录表的查询方法
	 * 
	 * @param request HttpServletRequest的对象
	 * @param sEcho datatables的被请求次数
	 * @param firstIndex 起始页数
	 * @param pageSize 每页多少条记录
	 * @return page DataTablesPage对象的实例
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-23 15:30:50
	 */
	@RequestMapping("/findContractsBySearch")
	@ResponseBody
	public DataTablesPage findContractsBySearch(@RequestParam("sEcho") Integer sEcho, @RequestParam("iDisplayStart") Integer firstIndex, @RequestParam("iDisplayLength") Integer pageSize, HttpServletRequest request) {
		String contractId = request.getParameter("contractId");
		String cuserId = request.getParameter("cuserId");
		List<Object> params = new ArrayList<Object>();
		params.add(cuserId);
		params.add(contractId);
		Page queryList = loanGrantService.findPayLoanBySearch((firstIndex / pageSize) + 1, pageSize, params);
		List<Object[]> resultList = queryList.getContent();
		for (Object[] obj : resultList) {
			obj[6] = timeStampToString((Timestamp) obj[6]);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList);
		return page;
	}

	/**
	 * 检验合同状态是否允许其跳转
	 * 
	 * @param contractId 合同id
	 * @return String 是否允许
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-09-02 16:37:51
	 */
	@RequestMapping("/checkContractStatus")
	@ResponseBody
	public String checkContractStatus(@RequestParam String contractId) {
		Contract contract = contractMngService.getContract(Long.valueOf(contractId));
		String codeKey = contractMngService.getCodeByTypeAndValue(CODETYPE_CONTRACTSTATUS, contract.getContractStatusCd());
		// 放款中
		if ("S9".equals(codeKey)) {
			return "payLoanExecutingError";
		}
		if (contract.getContractAvailableAmt() != null && contract.getContractAmt()!=null
				&& contract.getContractAvailableAmt().compareTo(contract.getContractAmt()) < 0) {
			return "amtCannotAffordError";
		}
		// 已放款
		else if (!"S1".equals(codeKey)) {
			// 已签订
			if (!(contract.getRepayModeCd() == "1" || contract.getRepayModeCd() == "2")) {
				return "repayModeNotAvaliableError";
			}
			// 此合同未入账或正在冲销中
			if (!loanGrantService.isTallyCertificateCanref(contract.getContractNum())) {
				return "contractNotPayOrCancelError";
			}
			// 此合同正在展期中
			if (!loanGrantService.validateContractInRenewal(contract.getContractNum())) {
				return "contractRenewExecutingError";
			}
			if (!loanGrantService.isAgainPayLoanBy(contract.getContractId())) {
				return "againPayLoanError";
			}
			RepayLoanVO repayLoanVO = new RepayLoanVO();
			repayLoanVO.setContractNum(contract.getContractNum());
			// 此合同正在提前还款中
			if (!loanGrantService.validate(repayLoanVO)) {
				return "repayInAdvanceError";
			}
		}
		return "success";
	}

	/**
	 * 获取初始化表单的数据
	 * 
	 * @param contractId 合同id
	 * @param cuserId 用户id
	 * @return payLoanInfo对象
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-09-10 16:37:51
	 */
	@RequestMapping("/queryMessages")
	@ResponseBody
	public PayLoanInfoVO queryMessages(@RequestParam String contractId, @RequestParam String cuserId) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Contract contract = contractMngService.getContract(Long.valueOf(contractId));
		// AssTranContract contract =
		// loanGrantService.findAssTranContractById(Long.valueOf(contractId));
		// AssTranProjectApplication project =
		// loanGrantService.findAssTranProjectById(contract.getProjectId());
		ProjectApplication project = businessApplicationService.searchProjectApplication(contract.getProjectId());
		Individual individual = uniqueCustomerService.getIndividual(contract.getPartyId()
				+ "");
		PayLoanInfoVO payLoanInfoVO = new PayLoanInfoVO();
		// 客户经理、经办人、经办机构
		payLoanInfoVO.setCustomerManager(curUser.getName());
		payLoanInfoVO.setApplyUserNumMask(curUser.getName());
		payLoanInfoVO.setApplyOrgIdMask(curUser.getLogOrgname());
		//String bankName = uniqueCustomerService.findAccountManagentByAccountId(contract.getBankName()).getAccountId().toString();
		payLoanInfoVO.setBankName(contract.getBankName());// 存accountId
		payLoanInfoVO.setLoanNum(contract.getLoanNum());
		payLoanInfoVO.setContractAmt(contract.getContractAmt());
		// 本次发放金额 = 合同金额(即不允许多次放款)
		payLoanInfoVO.setLoanAmt(contract.getContractAvailableAmt());
		payLoanInfoVO.setContractNum(contract.getContractNum());
		payLoanInfoVO.setCustomerName(contract.getCustomerName());
		payLoanInfoVO.setCustomerNum(contract.getCustomerNum());
		payLoanInfoVO.setCurrency(project.getCurrency());// 币种
		payLoanInfoVO.setArriveDate(contract.getExpirationDate());
		payLoanInfoVO.setArrangeRepayDay(contract.getArrangeRepayDay());
		payLoanInfoVO.setPartyId(contract.getPartyId());
		payLoanInfoVO.setContractId(contract.getContractId());
		// 获取贷款编号
		String payLoanNum = null;
		try {
			//payLoanNum = commonBizNumberBS.generateAppNumber("FK", "", curUser.getOrgid().toString());
			payLoanNum = commonBizNumberBS.generateAppNumber("FK", "", curUser.getLogOrgid().toString());//TODO
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		payLoanInfoVO.setPayLoanNum(payLoanNum);
		// 获取利率
		BigDecimal[] rates = loanGrantService.getLoanRate(contract);
		if (rates == null) {
			return null;
		}
		payLoanInfoVO.setContractRate(rates[0]);
		// 获取产品信息
		payLoanInfoVO.setProductType(project.getProductType());
		try {
			Product product = loanGrantService.findProductByProductCd(Long.valueOf(project.getProductType()));
			if (product == null) {
				return null;
			}
			// 产品类型
			payLoanInfoVO.setProductTypeMask(product.getProductName());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			e.getMessage();
		}
		/*
		 * //导数据 try {
		 * 
		 * ConvertUtils.register(new BigDecimalConverter(null),
		 * BigDecimal.class); ConvertUtils.register(new DateConverter(null),
		 * Date.class); ConvertUtils.register(new Converter() {
		 * 
		 * @Override public Object convert(Class type, Object val) {
		 * if(val==null){ return null; } if(val instanceof Timestamp){ return
		 * val; } throw new ConversionException("NoConversionException"); } },
		 * Timestamp.class);
		 * 
		 * BeanUtils.copyProperties(project, payLoanInfoVO);
		 * BeanUtils.copyProperties(contract, payLoanInfoVO);
		 * BeanUtils.copyProperties(payLoan, payLoanInfoVO); } catch
		 * (IllegalAccessException e) { e.printStackTrace(); e.getMessage(); }
		 * catch (InvocationTargetException e) { e.getMessage();
		 * e.printStackTrace(); }
		 */
		// 币种
		// payLoanInfoVO.setCurrencyMask(dataDict.getCodeName("CurrencyType",
		// project.getCurrency()));
		//payLoanInfoVO.setApplyOrgId(Long.valueOf(curUser.getOrgid()));
		payLoanInfoVO.setApplyOrgId(curUser.getLogOrgid());//TODO
		payLoanInfoVO.setApplyUserNum(curUser.getId() + "");
		payLoanInfoVO.setContractRate(contract.getContractRate());
		// 年利率
		if (payLoanInfoVO.getContractRate() != null) {
			payLoanInfoVO.setContractRateMask(MathUtil.BDmultiply(payLoanInfoVO.getContractRate(), new BigDecimal("100"), 4));
		}
		// 还款方式
		payLoanInfoVO.setRepayModeCdMask(dataDict.getCodeName(CODETYPE_REPAYMENTMODE, contract.getRepayModeCd()));
		// 还款日期
		payLoanInfoVO.setLoanRegistTime(DateTools.dateToString(new Date(), "yyyy-MM-dd"));
		payLoanInfoVO.setLoanActulTime(DateTools.dateToString(new Date(), "yyyy-MM-dd"));
		payLoanInfoVO.setCreateDate(DateTools.dateToString(new Date(), "yyyy-MM-dd"));
		// 本次可用、还款金额
		payLoanInfoVO.setCumulativePayoutAmt(new BigDecimal(contract.getCumulativePayoutAmt() == null ? "0" : contract.getCumulativePayoutAmt()
				+ ""));
		payLoanInfoVO.setCumulativeRepayAmt(new BigDecimal(contract.getCumulativeRepayAmt() == null ? "0" : contract.getCumulativeRepayAmt()
				+ ""));
		// 贷款合同期限
		String contractTermUnit = contract.getContractTerm()
				+ dataDict.getCodeName(CODETYPE_TERMUNIT, contract.getContractTermUnit());
		payLoanInfoVO.setContractTermUnit(contractTermUnit);
		payLoanInfoVO.setRepayPrincipalMonthes(contract.getRepayPrincipalMonthes());
		// 申请费用金额
		payLoanInfoVO.setFreeamtcnt(new BigDecimal(contract.getFreeamtcnt() == null ? "0" : contract.getFreeamtcnt()
				+ ""));
		// 约定方式
		if (StringUtils.isNotBlank(contract.getLoanDateStyle())
				&& !"null".equals(contract.getLoanDateStyle())) {
			payLoanInfoVO.setLoanDateStyleMask(dataDict.getCodeName(CODETYPE_LOANDATESTYLE, contract.getLoanDateStyle()));
		}
		if (contract.getContractAvailableAmt() != null && contract.getContractAmt()!=null) {
			payLoanInfoVO.setContractAvailableAmt(contract.getContractAvailableAmt());
			// 手续费和佣金收入
			if (contract.getContractAmt().compareTo(contract.getContractAvailableAmt() == null ? new BigDecimal("0") : contract.getContractAvailableAmt()) == 0) {
				// payLoanInfoVO.setSumAmt(new
				// BigDecimal(contract.getFreeamtcnt() == null ? "0" :
				// contract.getFreeamtcnt()+""));// 手续费及佣金收入
				if (contract.getFreepayloanamtcnt() != null) {
					payLoanInfoVO.setSumAmt(new BigDecimal(contract.getFreepayloanamtcnt()
							+ ""));
				}
				payLoanInfoVO.setFreePayLoanAmtCnt(new BigDecimal(contract.getFreepayloanamtcnt() == null ? "0" : contract.getFreepayloanamtcnt()
						+ ""));// 手续费及佣金收入
			} else {
				payLoanInfoVO.setFreePayLoanAmtCnt(new BigDecimal(contract.getFreepayloanamtcnt() == null ? "0" : contract.getFreepayloanamtcnt()
						+ ""));// 手续费及佣金收入
			}
		}
		if (payLoanInfoVO.getLoanAmt() != null) {
			payLoanInfoVO.setLoanAmt(payLoanInfoVO.getLoanAmt());
		}
		// 是否允许多次放款，控制两个标志位实现
		/*
		 * if
		 * (GlobalConstants.APPROVE_AGAIN_PAY.indexOf(contract.getRepayModeCd())
		 * <= -1) { payLoanInfoVO.setIsMultipleLoan("0"); }else {
		 * payLoanInfoVO.setIsMultipleLoan("1"); }
		 */
		// 不允许修改本次发放金额(不允许多次放款)
		if (dataDict.getCodeName(CODETYPE_REPAYMENTMODE, "S2").equals(contract.getRepayModeCd())
				|| dataDict.getCodeName(CODETYPE_REPAYMENTMODE, "S3").equals(contract.getRepayModeCd())) {
			payLoanInfoVO.setIsAllowLoan("0");
		} else {
			payLoanInfoVO.setIsAllowLoan("1");
		}
		String[] guaranteeMode = project.getGuaranteeMode().split(",");
		// 若担保方式为信用,则不允许添加保险信息
		if (guaranteeMode.length == 1
				&& dataDict.getCodeVal("CdsGuarantMode", "S4").equals(guaranteeMode[0])) {
			payLoanInfoVO.setIfInsure(dataDict.getCodeVal("IfInsureType", "S2"));
		} else {
			payLoanInfoVO.setIfInsure(project.getIfInsure());
		}
		return payLoanInfoVO;
	}

	/**
	 * 提交表单数据
	 * 
	 * @param contractId 合同id
	 * @param payLoanInfoVO 表单数据
	 * @return String保存是否成功
	 * 
	 * @author lijing
	 * @throws Exception
	 * @lastModified lijing 2014-09-10 16:37:51
	 */
	@RequestMapping("/submitAndPrint")
	@ResponseBody
	public Result submitAndPrint(@RequestParam(required = false) String contractId, @ModelAttribute PayLoanInfoVO payLoanInfoVO) throws Exception {
		String sourceType = payLoanInfoVO.getSourceType();
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		// 后台校验
		if (payLoanInfoVO.getLoanAmt() == null
				|| payLoanInfoVO.getLoanAmt().compareTo(BigDecimal.ZERO) == 0) {
			return new Result(false, "放款金额不能为0,请重新输入!", null);
		}
		// 添加校验本次发放金额不能大于可发放金额
		BigDecimal contractAmt = payLoanInfoVO.getContractAvailableAmt();
		if (payLoanInfoVO.getLoanAmt().compareTo(contractAmt) > 0) {
			return new Result(false, "本次发放金额不能大于可发放金额,请重新输入!", null);
		}
		Contract contract = contractMngService.getContract(payLoanInfoVO.getContractId());
		PayLoanInfo payLoan = new PayLoanInfo();
		try {
			ConvertUtils.register(new SqlDateConverter(null), Date.class);
			ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
			BeanUtils.copyProperties(payLoan, payLoanInfoVO);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		payLoan.setPayLoanId(null);
		payLoan.setContractId(contract.getContractId());
		payLoan.setSysUpdateDate(new Timestamp(new Date().getTime()));
		payLoan.setPayStatusCd(dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S1"));
		payLoan.setIsUpload("0"); // 未上传据据
		payLoan.setManyPayStatus("0"); // 生效
		payLoan.setPartyId(contract.getPartyId());
		payLoan.setApplyUserNum(String.valueOf(curUser.getId()));
		//payLoan.setApplyOrgId(Long.valueOf(curUser.getOrgid()));//TODO
		payLoan.setApplyOrgId(curUser.getLogOrgid());//TODO
		payLoan.setCreateDate(new Date());
		payLoan.setProductType(contract.getProductType());
		//Result msg = loanGrantService.generateLoanIssues(payLoan, contract,payLoanInfoVO,curUser.getOrgid());
		Result msg = loanGrantService.generateLoanIssues(payLoan, contract,payLoanInfoVO,curUser.getLogOrgid());//TODO
		if(msg!=null){
			if(msg.getSuccess()){
				contractMngService.saveContract(contract);
			}
			return msg;
		}
		return new Result(false, "保存发生错误，请联系管理员！", null);
	}

	@RequestMapping("/changeIsUpload")
	@ResponseBody
	public String changeIsUpload(@RequestParam String payLoanId) {
 		PayLoanInfo payLoan = loanGrantService.findPayLoanById(Long.valueOf(payLoanId));
		if (payLoan!=null) {
			String isUpload = payLoan.getIsUpload();
			Contract contract = contractMngService.getContract(payLoan.getContractId());
			isUpload = "1";
			payLoan.setIsUpload(isUpload);
			loanGrantService.changeIsUpload(contract, payLoan);
		}
		return "success";
	}

	@RequestMapping("/testReport")
	public String exportReportPersonalCreditLoanApply(@RequestParam String payLoanId, Model model) {
		PayLoanInfo payLoanInfo = loanGrantService.findPayLoanById(Long.valueOf(payLoanId));
		Report report = getReportPersonalCreditLoanApply(payLoanInfo);
		model.addAllAttributes(report);
		return "reportLoanVoucher";
	}

	/******************************* 文档表的相关方法 *************************************/
	/**
	 * 打印（下载）借据，查找下载信息
	 * 
	 * @param payLoanId 放款记录id
	 * @return List 下载地址的相关信息
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-09-10 16:37:51
	 */
	@RequestMapping("/findDocumentPath")
	@ResponseBody
	public List findDownloadPath(@RequestParam String payLoanId) {
		PayLoanInfo payLoan = loanGrantService.findPayLoanById(Long.valueOf(payLoanId));
		List<String> list = new ArrayList<String>();
		return list;
	}

	/**
	 * 新增文档前的参数获取
	 * 
	 * @param partyId 客户Id
	 * @param request HttpServletRequest
	 * @return DocumentUploadVO 对象
	 * @author lijing
	 * @lastModified lijing 2014-08-18 9:30:50
	 */
	@RequestMapping("/beforeUpdate")
	@ResponseBody
	public DocumentUploadVO beforeUpdate(@RequestParam String payLoanId) {
		DocumentUploadVO documentUploadVO = new DocumentUploadVO();
		PayLoanInfo payLoan = loanGrantService.findPayLoanById(Long.valueOf(payLoanId));
		Contract contract = contractMngService.getContract(payLoan.getContractId());
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Party party = uniqueCustomerService.findPartyByPartyId(contract.getPartyId()
				+ "");
		ProjectApplication project = businessApplicationService.searchProjectApplication(Long.valueOf(contract.getProjectId()));
		documentUploadVO.setUserNum(curUser.getId().toString());
		//modify by HWL start 20150701 修改为登录机构
		documentUploadVO.setCreateOrgCd(curUser.getLogOrgid().toString());
		//modify by HWL end 20150701 修改为登录机构
		documentUploadVO.setCreateDateTime(timeStampToString(new Timestamp(new Date().getTime())));
		documentUploadVO.setCustomerNum(party.getCustomerNum());
		documentUploadVO.setBizNum(project.getProjectNo());
		documentUploadVO.setBizId(contract.getPartyId() + "");
		documentUploadVO.setPartyId(String.valueOf(party.getPartyId()));
		documentUploadVO.setDocumentType("24");
		documentUploadVO.setCreateUserName(curUser.getName());
		documentUploadVO.setFileTypes("doc,docx,xls,xlsx,pdf,jpg,gif,png,rar");
		documentUploadVO.setCreateUserNum(curUser.getId().toString());
		documentUploadVO.setCreateTypeCd("01");
		documentUploadVO.setDocumentNum(commonBizNumberBS.generateDocumentNum(party.getCustomerNum(), "01"));
		return documentUploadVO;
	}

	/********************************************** 账户表的相关方法 *************************************/
	/**
	 * 查出客户的账户信息
	 * 
	 * @param partyId 客户id
	 * @return List 账户信息list
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-14 10:37:51
	 */
	@RequestMapping("/getCustomerAccounts")
	@ResponseBody
	public List<CustomerAccountManagent> getCustomerAccount(@RequestParam("contractId") String contractId) {
		Contract contract = contractMngService.getContract(Long.valueOf(contractId));
		return contractMngService.findAccountsByPartyId(contract.getPartyId());
	}

	/**
	 * 根据账户id查询账号
	 * 
	 * @param partyId 客户id
	 * @return List 账户信息list
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-08-14 10:37:51
	 */
	@RequestMapping("/getAccountNum")
	@ResponseBody
	public List<String> getAccountNum(@RequestParam("accountId") String accountId) {
		return contractMngService.findAccountNumByAccountId(Long.valueOf(accountId));
	}

	/*********************************** 本控制器所用的工具方法 *******************************************/
	

	/**
	 * Timestamp转化成String的方法
	 * 
	 * @param st Timestamp类型的变量
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
	 * @param time String类型的变量
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

	public Report getReportPersonalCreditLoanApply(PayLoanInfo payLoanInfo) {
		Report report = new Report(ReportExtension.XLSX);
		report.addFields(new JREmptyDataSource());
		Contract contract = contractMngService.getContract(payLoanInfo.getContractId());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters = plungeData(contract, payLoanInfo);
		report.addParameters(parameters); //
		return report;
	}

	/**
	 * 下载借据，装配参数
	 * 
	 * @param contract 合同对象
	 * @param payLoanInfo 放款记录对象
	 */
	private Map<String, Object> plungeData(Contract contract, PayLoanInfo payLoanInfo) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("OrgName", curUser.getLogOrgname()); // 机构名称
		parameters.put("loanDuebillNo", payLoanInfo.getTransactionNo()); // 借据编号
		parameters.put("customerName", contract.getCustomerName()); // 借款人全称
		parameters.put("contractNo", contract.getContractNum()); // 合同号
		parameters.put("loanAmtUp", splitNum(String.valueOf(payLoanInfo.getLoanAmt()))); // 借款金额大写
		String amt = "";
		if (payLoanInfo.getLoanAmt() != null) {
			//modify by HWL 20150612 放款金额保留两位小数
			amt = payLoanInfo.getLoanAmt().setScale(2).toPlainString();
			//modify by HWL 20150612 end  放款金额保留两位小数
			int index = amt.indexOf(".");
			String intOnly = "";
			if (index > 0) {
				intOnly = amt.substring(0, index);
				String smallOnly = amt.substring(index + 1);
				//modify by HWL 20150612 字符无法转换为String
				parameters.put("jiao", String.valueOf(smallOnly.charAt(0))); // 角
				parameters.put("fen", String.valueOf(smallOnly.charAt(1))); // 分
				//modify by HWL 20150612 end  字符无法转换为String
			} else {
				intOnly = amt;
			}
			String reverse = StringUtils.reverse(intOnly);
			for (int i = 0; i < reverse.length(); i++) {
				switch (i) {
				case 0: {// 个
					parameters.put("yuan", String.valueOf(reverse.charAt(i))); // 元
					break;
				}
				case 1: {// 十
					parameters.put("ten", String.valueOf(reverse.charAt(i))); // 十
					break;
				}
				case 2: {// 百
					parameters.put("hundred", String.valueOf(reverse.charAt(i))); // 百
					break;
				}
				case 3: {// 千
					parameters.put("thousand", String.valueOf(reverse.charAt(i))); // 千
					break;
				}
				case 4: {// 万
					parameters.put("tenThousand", String.valueOf(reverse.charAt(i))); // 万
					break;
				}
				case 5: {// 十万
					parameters.put("oneHundredThousand", String.valueOf(reverse.charAt(i))); // 十万
					break;
				}
				case 6: {// 百万
					parameters.put("million", String.valueOf(reverse.charAt(i))); // 百万
					break;
				}
				case 7: {// 千万
					parameters.put("tenMillion", String.valueOf(reverse.charAt(i))); // 千万
					break;
				}
				default:
					break;
				}
			}
		}
		BizRate bizRate = contractMngService.findBizRateByProjectId(contract.getProjectId());
		parameters.put("rmb", "1"); // 是否显示￥ 1为显示，否则该值传0，并 在小写数字最大值前面加￥。
		parameters.put("loanStartDate", DateTools.dateToString(payLoanInfo.getLoanActulTime(), "yyyy年MM月dd日")); // 借款起始日
		parameters.put("loanEndDate", DateTools.dateToString(payLoanInfo.getArriveDate(), "yyyy年MM月dd日")); // 借款到期日
		parameters.put("yearRate", bizRate.getFinalRateValue().movePointRight(2)
				+ "%"); // 年利率
		parameters.put("ratetype", bizRate.getFinalIrTypeCd()); // 利率类型
		Pattern pattern = Pattern.compile("[0-9]*");
		if (pattern.matcher(contract.getLoanNum()).matches()) {
			//CustomerAccountManagent cam = uniqueCustomerService.findAccountManagentByAccountId(contract.getBankName());
			parameters.put("payOutName", contract.getCustomerName()); // 借款转入账户 名称
			parameters.put("payOutBank", contract.getBankName()); // 借款转入账户 开户行
			parameters.put("payOutAccount", contract.getLoanNum()); // 借款转入账户 账号
			parameters.put("rePayName", ""); // 借款还款账户 名称
			parameters.put("rePayBank", ""); // 借款还款账户 开户行
			parameters.put("rePayAccount", ""); // 借款还款账户 账号
		}
		return parameters;
	}

	/**
	 * 把用户输入的数以小数点为界分割开来，并调用 numFormat() 方法 进行相应的中文金额大写形式的转换 注：传入的这个数应该是经过
	 * roundString() 方法进行了四舍五入操作的
	 * 
	 * @param s String
	 * @return 转换好的中文金额大写形式的字符串
	 */
	private String splitNum(String s) {
		// 如果传入的是空串则继续返回空串
		if (StringUtils.isBlank(s)) {
			return "";
		}
		String newS = "";
		// 以小数点为界分割这个字符串
		int index = s.indexOf(".");
		if (index > 0) {
			String intOnly = s.substring(0, index);
			String part1 = cleanZero(numFormat(1, intOnly));
			// 截取并转换这个数的小数部分
			String smallOnly = s.substring(index + 1);
			String part2 = cleanZero(numFormat(2, smallOnly));
			// 把转换好了的整数部分和小数部分重新拼凑一个新的字符串
			newS = part1 + part2;
			// 截取并转换这个数的整数部分
		} else {
			newS = cleanZero(numFormat(1, s));
		}
		return newS;
	}

	/**
	 * 把传入的数转换为中文金额大写形式
	 * 
	 * @param flag int 标志位，1 表示转换整数部分，0 表示转换小数部分
	 * @param s String 要转换的字符串
	 * @return 转换好的带单位的中文金额大写形式
	 */
	private String numFormat(int flag, String s) {
		int sLength = s.length();
		int num = Integer.valueOf(s);
		if (num == 0) {
			return "整";
		}
		// 货币大写形式
		String[] bigLetter = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		// 货币单位
		String[] unit = { "元", "拾", "佰", "仟", "万", // 拾万位到仟万位
		"拾", "佰", "仟", // 亿位到万亿位
		"亿", "拾", "佰", "仟", "万" };
		String[] small = { "分", "角" };
		// 用来存放转换后的新字符串
		String newS = "";
		// 逐位替换为中文大写形式
		for (int i = 0; i < sLength; i++) {
			if (flag == 1) {
				// 转换整数部分为中文大写形式（带单位）
				newS = newS + bigLetter[s.charAt(i) - 48]
						+ unit[sLength - i - 1];
			} else if (flag == 2) {
				// 转换小数部分（带单位）
				newS = newS + bigLetter[s.charAt(i) - 48]
						+ small[sLength - i - 1];
			}
		}
		return newS;
	}

	/**
	 * 清零
	 * 
	 * 
	 * @param s String 要转换的字符串
	 * @return 清零的结果
	 */
	private String cleanZero(String s) {
		// 如果传入的是空串则继续返回空串
		if ("".equals(s)) {
			return "";
		}
		// 如果用户开始输入了很多 0 去掉字符串前面多余的'零'，使其看上去更符合习惯
		while (s.charAt(0) == '零') {
			// 将字符串中的 "零" 和它对应的单位去掉
			s = s.substring(2);
			// 如果用户当初输入的时候只输入了 0，则只返回一个 "零"
			if (s.length() == 0) {
				return "零";
			}
		}
		// 字符串中存在多个'零'在一起的时候只读出一个'零'，并省略多余的单位
		String[] regex1 = { "零仟", "零佰", "零拾" };
		String[] regex2 = { "零亿", "零万", "零元" };
		String[] regex3 = { "亿", "万", "元" };
		String[] regex4 = { "零角", "零分" };
		// 第一轮转换把 "零仟", 零佰","零拾"等字符串替换成一个"零"
		for (int i = 0; i < 3; i++) {
			s = s.replaceAll(regex1[i], "零");
		}
		// 第二轮转换考虑 "零亿","零万","零元"等情况
		// "亿","万","元"这些单位有些情况是不能省的，需要保留下来
		for (int i = 0; i < 3; i++) {
			// 当第一轮转换过后有可能有很多个零叠在一起
			// 要把很多个重复的零变成一个零
			s = s.replaceAll("零零零", "零");
			s = s.replaceAll("零零", "零");
			s = s.replaceAll(regex2[i], regex3[i]);
		}
		// 第三轮转换把"零角","零分"字符串省略
		for (int i = 0; i < 2; i++) {
			s = s.replaceAll(regex4[i], "");
		}
		// 当"万"到"亿"之间全部是"零"的时候，忽略"亿万"单位，只保留一个"亿"
		s = s.replaceAll("亿万", "亿");
		return s;
	}
}

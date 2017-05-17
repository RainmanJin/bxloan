package com.coamctech.bxloan.web.controller.statistics;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.BizRate;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.InsuranceCompany;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.contractmng.LoanGrantService;
import com.coamctech.bxloan.service.customermng.CustomerManagerService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.enums.DataAuthorityType;
import com.coamctech.bxloan.service.model.PayLoanInfoVO;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.bizapply.BusinessApplicationWdVO;
import com.coamctech.bxloan.service.model.statistics.BizContractVo;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.bizapply.CoCustomerManager;

/**
 * 合同台账查询
 * 
 * @author AcoreHeng
 */
@Controller
@RequestMapping("/contractQuery")
public class ContractQueryController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(ContractQueryController.class);
	private final String STATISTICS = "statistics/contractQuery/";
	@Autowired
	private UniqueCustomerService uniqueCustomerService;

	@Autowired
	private BusinessApplicationService businessApplicationService;

	@Autowired
	private ContractMngService contractMngService;

	@Autowired
	private LoanGrantService loanGrantService;

	@Autowired
	private OrgDeptmentService orgDeptmentService;

	@Autowired
	private ProductPriceService productPriceService;

	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private EcOrgPersonService ecOrgPersonService;
	
	//add by wangpeng on 2015-07-21 start
	@Autowired
	private CustomerManagerService customerManagerService;
	
	@RequestMapping
	public String index(Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		int orgLevel = 0;
		if(curUser.getDataAuthType() != null){
			switch (curUser.getDataAuthType()) {
			case HeadOffice:// 总部
				orgLevel = 1;
				break;
			default:// 小贷公司或客户经理
				orgLevel = 2;
				break;
			}
		model.addAttribute("orgLevel", orgLevel);
		model.addAttribute("orgs", ecOrgPersonService
				.findEcOrgsInfoByIds(curUser.getDataAuthOrgIds()));
		}else{
			//TODO
			model.addAttribute("orgName", curUser.getLogOrgname());
		}
		
		
		return StringUtils.join(STATISTICS, "main");
	}

	/**
	 * 查看合同详情
	 * 
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam Long projectId,
			@RequestParam Long contractId, @RequestParam Long partyId,Model model){
		logger.info("查看合同详情projectId:{},contractId:{},partyId:{}",projectId,contractId,partyId);
		model.addAttribute("contractId", contractId);
		model.addAttribute("projectId",projectId);
		model.addAttribute("partyId", partyId);
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);//文档服务器地址
		/**
		 * 产品列表
		 */
		List<Object[]> products = productPriceService.findProduct();
		model.addAttribute("products", products);
		/**
		 * 保险机构
		 */
		List<InsuranceCompany> insuranceCompanys = businessApplicationService
				.findAllInsuranceCompany();
		model.addAttribute("insuranceCompanys", insuranceCompanys);
		//add by wangpeng on 2015-07-27 start
		ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(Long.valueOf(projectId));
		String personId = projectApplication.getAssistance();
		List<CoCustomerManager> assistancers = new ArrayList<CoCustomerManager>();//协办客户经理VO
		//如果没有选择协办客户经理则初始化一个默认值
		CoCustomerManager ccm = new CoCustomerManager();
		if(StringUtils.isBlank(personId)||StringUtils.isEmpty(personId)){
			ccm.setPersonId("");
			ccm.setPersonName("");
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
		//add by wangpeng on 2015-07-27 end
		return StringUtils.join(STATISTICS, "detail");
	}
	
	/**
	 * 导出报表验证
	 * @param vo
	 * @return
	 */
	@RequestMapping("/checkDownload")
	@ResponseBody
	public Result checkDownload(@RequestParam(required=false)String loanStartDate,
			@RequestParam(required=false)String loanEndDate,
			BizContractVo vo ){
		try {
			vo.setLoanStartTime(CommonHelper.str2Date(loanStartDate, CommonHelper.DF_DATE));
			vo.setLoanEndTime(CommonHelper.str2Date(loanEndDate, CommonHelper.DF_DATE));
			
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			vo.setLoginUserId(curUser.getId());
			vo.setDataAuthOrgIds(curUser.getDataAuthOrgIds());
			vo.setDataAuthType(curUser.getDataAuthType());
			
			//查询合同台账列表
			Page<BizContractVo> page = contractMngService.findContractBySearch(0 / 1000, 1000, vo);
			if(page == null || page.getContent() == null || page.getContent().size() == 0){
				return failure("导出失败！无资料,不能导出报表,请确认!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return failure("导出失败！"+e.getMessage());
		}
		return success("导出成功！");
	}
	
	/**
	 * 导出Excel
	 * @param req
	 * @param resp
	 */
	@RequestMapping("/downloadExcel")
	@ResponseBody
	public Result downloadExcel(HttpServletRequest request,
			HttpServletResponse response,
			BizContractVo vo ){
		try {
			String loanStartDate = (String) request.getParameter("loanStartDate");
			String loanEndDate = (String) request.getParameter("loanEndDate");
			//时间处理
			vo.setLoanStartTime(CommonHelper.str2Date(loanStartDate, CommonHelper.DF_DATE));
			vo.setLoanEndTime(CommonHelper.str2Date(loanEndDate, CommonHelper.DF_DATE));
			
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
			vo.setLoginUserId(curUser.getId());
			vo.setDataAuthOrgIds(curUser.getDataAuthOrgIds());
			vo.setDataAuthType(curUser.getDataAuthType());
			//先导出文件到服务器
			String fileName = contractMngService.ExportExcel(vo);
			//再从服务器下载文件到本地
			FileUtils.downloadExportFile(fileName, request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return failure("导出失败！"+e.getMessage());
		}
		return success("导出成功！");
	}
	
	/**
	 * 合同台账查询列表
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/findContactList")
	@ResponseBody
	public DataTablesPage findContactList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer pageNumber,
			@RequestParam("iDisplayLength") Integer pageSize,
			@RequestParam(required=false)String loanStartDate,
			@RequestParam(required=false)String loanEndDate,
			BizContractVo vo){
		//时间处理
		vo.setLoanStartTime(CommonHelper.str2Date(loanStartDate, CommonHelper.DF_DATE));
		vo.setLoanEndTime(CommonHelper.str2Date(loanEndDate, CommonHelper.DF_DATE));
		
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		vo.setLoginUserId(curUser.getId());
		vo.setDataAuthOrgIds(curUser.getDataAuthOrgIds());
		vo.setDataAuthType(curUser.getDataAuthType());
		
		//查询合同台账列表
		Page<BizContractVo> page = contractMngService.findContractBySearch(pageNumber / pageSize, pageSize, vo);
		//处理并返回查询结果
		return new DataTablesPage(sEcho, page);
	}
	/**
	 * 合同台账查询列表(客户查询)
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/findContactListForCustomerQuery")
	@ResponseBody
	public DataTablesPage findContactListForCustomerQuery(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer pageNumber,
			@RequestParam("iDisplayLength") Integer pageSize,
			@RequestParam(required=false)String loanStartDate,
			@RequestParam(required=false)String loanEndDate,
			BizContractVo vo,
			HttpServletRequest request){
		
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		//modify by mz 20150702 start
		SessionUser user=new SessionUser(curUser.getId(), curUser.getLogOrgid(), null);
		//modify by mz 20150702 end
		
		String partyId = request.getParameter("partyId");
		//10001表示总部
		String orgLevel=StringUtils.equalsIgnoreCase("10001", String.valueOf(user.getOrgId()))?"1":"2";
		if(vo.getOrgId() == null && !orgLevel.equals("1")){//不是总部
			vo.setOrgId(user.getOrgId());
		}
		//时间处理
		vo.setLoanStartTime(CommonHelper.str2Date(loanStartDate, CommonHelper.DF_DATE));
		vo.setLoanEndTime(CommonHelper.str2Date(loanEndDate, CommonHelper.DF_DATE));
		//查询合同台账列表
		Page<BizContractVo> page = contractMngService.findContractByCondition(pageNumber / pageSize, pageSize, vo, partyId);
		//处理并返回查询结果
		return new DataTablesPage(sEcho, page);
	}

	/**
	 * 查看主合同信息
	 * 
	 * @param projectId 项目申请ID
	 * @param contractId 合同ID
	 * @param partyId 参与人ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/findPrincipalContractInfo")
	@ResponseBody
	public Result findPrincipalContractInfo(@RequestParam Long projectId,
			@RequestParam Long contractId, @RequestParam String partyId) {
		try {
			if (null == projectId || null == contractId || StringUtils.isBlank(partyId)) {
				throw new Exception("项目参数获取异常");
			}
			// 项目申请
			ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(projectId);
			// 业务利率
			BizRate bizRate = businessApplicationService.getBizRateByProjectId(projectApplication.getProjectId().toString());
			// 参与人
			Party party = uniqueCustomerService.findPartyByPartyId(partyId);
			// 合同信息
			Contract contract = contractMngService.getContract(contractId);

			// 组装VO信息
			BusinessApplicationWdVO businessApplicationWdVO = new BusinessApplicationWdVO();
			String applyOrgName = contractMngService.getApplyOrgName(contractId);
			businessApplicationWdVO.setApplyOrgName(applyOrgName);
			
			if (projectApplication != null) {
				BeanUtils.copyProperties(projectApplication, businessApplicationWdVO);
			}
			if (party != null) {
				BeanUtils.copyProperties(party, businessApplicationWdVO);
			}
			if (bizRate != null) {
				BeanUtils.copyProperties(bizRate, businessApplicationWdVO);
			}
			// 贷款申请日期
			if (projectApplication.getApplyDate() != null) {
				businessApplicationWdVO
						.setApplyDate(new SimpleDateFormat("yyyy-MM-dd")
								.format(projectApplication.getApplyDate()));
			}

			//最终年利率（%）
			if (businessApplicationWdVO.getFinalRateValue() != null) {
				businessApplicationWdVO.setFinalRateValue(businessApplicationWdVO
						.getFinalRateValue().multiply(new BigDecimal("100")));
			}

			//最终利率上浮比例（%）
			if (businessApplicationWdVO.getFinalFloatRate() != null) {
				businessApplicationWdVO.setFinalFloatRate(businessApplicationWdVO
						.getFinalFloatRate().multiply(new BigDecimal("100")));
			}

			// 逾期利率上浮比例（%）
			if (businessApplicationWdVO.getOvdueIrNegoRate() != null) {
				businessApplicationWdVO.setOvdueIrNegoRate(businessApplicationWdVO
						.getOvdueIrNegoRate().multiply(new BigDecimal("100")));
			}
			
			// 挪用利率上浮比例（%）
			if (businessApplicationWdVO.getPerculIrNegoRate() != null) {
				businessApplicationWdVO.setPerculIrNegoRate(businessApplicationWdVO
						.getPerculIrNegoRate().multiply(new BigDecimal("100")));
			}
			
			if(contract == null){
				throw new Exception("获取合同信息异常");
			}
			String initContractId = contract.getInitContractId().toString();
			// 放款记录信息
			PayLoanInfo payLoanInfo = loanGrantService.findPayLoanByContractIdAndContractStatus(initContractId, "('2','4')");
			BeanUtils.copyProperties(contract, businessApplicationWdVO);
			
			// 提前还款违约金比例（%）
			if (businessApplicationWdVO.getPreRepaymentRate() != null) {
				businessApplicationWdVO
				.setPreRepaymentRate(businessApplicationWdVO
						.getPreRepaymentRate().multiply(
								new BigDecimal("100")));
			}
			if(payLoanInfo != null){
				businessApplicationWdVO.setLoanIfInsure(payLoanInfo.getIfInsure());
				businessApplicationWdVO.setLoanInsuranceOrgId(payLoanInfo.getInsuranceOrgId());
				businessApplicationWdVO.setLoanPremium(payLoanInfo.getLoanPremium());
				businessApplicationWdVO.setAutualPremium(payLoanInfo.getAutualPremium());
			}
			businessApplicationWdVO.setCustomerNum(party.getCustomerNum());
			businessApplicationWdVO.setCustomerName(party.getPartyName());
			businessApplicationWdVO.setCustomerType(party.getPartyTypeCd());
			//获取投放行业名称
			String industryName = contractMngService.findIndustryNameByIndustryCd(projectApplication.getInvestmentIndustry());
			businessApplicationWdVO.setIndustryName(industryName);
			return success(businessApplicationWdVO);
		} catch (Exception e) {
			logger.error("查看主合同信息异常,项目申请ID={},合同ID={}" + e.getMessage(), projectId, contractId);
			return failure("<strong>数据异常,请联系系统管理员</strong>");
		}
	}
	
	/** 
	 * 查看放款记录信息
	 * 
	 * @param projectId 项目申请ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/findPayLoanRecordSearch")
	@ResponseBody
	public DataTablesPage findPayLoanRecordSearch(@RequestParam("sEcho") Integer sEcho,
								@RequestParam("iDisplayStart") Integer firstIndex,
								@RequestParam("iDisplayLength") Integer pageSize,
								@RequestParam Long contractId) {
		Page<Object[]> page = contractMngService.findPayLoanRecordSearch((firstIndex / pageSize), pageSize, contractId);
		List<PayLoanInfoVO> payLoanInfoList = contractMngService.findPayLoanRecordInfo(page);
		
		DataTablesPage dtPage = new DataTablesPage(sEcho, page);
		dtPage.setAaData(payLoanInfoList);
		return dtPage;
	}
	
	/**
	 * 查看参与人信息
	 * 
	 * @param projectId 项目申请ID
	 * @return
	 */
	@RequestMapping("/getParty")
	@ResponseBody
	public Result getParty(@RequestParam Long projectId) {
		try {
			if (null == projectId) {
				throw new Exception("项目参数获取异常");
			}
			// 项目申请
			ProjectApplication projectApplication = businessApplicationService.searchProjectApplication(projectId);
			String partyId = projectApplication.getPartyId().toString();
			// 参与人
			Party party = uniqueCustomerService.findPartyByPartyId(partyId);
			return success(party);
		} catch (Exception e) {
			logger.error("查看参与人信息异常,项目申请ID={}" + e.getMessage(), projectId);
			return failure("<strong>数据异常,请联系系统管理员</strong>");
		}
	}
}

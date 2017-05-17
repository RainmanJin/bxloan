package com.coamctech.bxloan.web.controller.statistics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.coamctech.bxloan.commons.utils.ReportExcelUtils;
import com.coamctech.bxloan.commons.utils.excel.ExcelUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.CorporationCustomer;
import com.coamctech.bxloan.entity.EcOrgDepartment;
import com.coamctech.bxloan.entity.Individual;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.common.OrgDeptmentService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.contractmng.LoanGrantService;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.PayLoanInfoVO;
import com.coamctech.bxloan.service.model.SessionUser;
import com.coamctech.bxloan.service.model.statistics.BizContractVo;
import com.coamctech.bxloan.service.sysmanager.EcOrgPersonService;
import com.coamctech.bxloan.service.sysmng.ProductPriceService;
import com.coamctech.bxloan.web.security.ShiroUser;
import com.coamctech.bxloan.web.vo.customermng.IndividualVO;
import com.google.common.collect.Lists;

/**
 * 客户查询
 * 
 * @author AcoreHeng
 */
@Controller
@RequestMapping("/customerQuery")
public class CustomerQueryController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(CustomerQueryController.class);
	private final String STATISTICS = "statistics/customerQuery/";
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
	private CorporationCustomerService corpCusService;
	
	@Autowired
	private EcOrgPersonService ecOrgPersonService;
	
	/**
	 * 客户查询
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String index(Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		/*SessionUser user=new SessionUser(curUser.getId(), curUser.getOrgid(), null);
		//10001表示总部
		String orgLevel = StringUtils.equalsIgnoreCase("10001", String.valueOf(user.getOrgId()))?"1":"2";
		model.addAttribute("user", user);
		model.addAttribute("orgLevel", orgLevel);// 当前登录用户的部门等级：1；2 小贷公司
		if(orgLevel.equals("1")){//总部
			List<EcOrgDepartment> list = orgDeptmentService.findAllOrgList();
			model.addAttribute("orgs", list);
		}else if(orgLevel.equals("2")){//小贷机构
			
		}
		
		List<EcOrgDepartment> list = Lists.newArrayList();
		switch (curUser.getDataAuthType()) {
		case CustomerManager:
			String orgName = orgDeptmentService.findDeptNameById(user.getOrgId());
			model.addAttribute("orgLevel", 2);
			model.addAttribute("orgName", orgName);
			break;
		default:
			list = ecOrgPersonService.findEcOrgsInfoByIds(curUser.getDataAuthOrgIds());
			model.addAttribute("orgLevel", 1);
			model.addAttribute("orgs", list);
			break;
		}*/
		
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
	public String detail(@RequestParam Long partyId,Model model){
		logger.info("查看客户详情partyId:{}",partyId);
		
		model.addAttribute("partyId", partyId);
		model.addAttribute("uploadPath", GlobalConstants.UPLOAD_DESTINATION);//文档服务器地址
		
		CorporationCustomer corpCus = this.corpCusService.findById(partyId);
		model.addAttribute("corpCus", corpCus);
		return StringUtils.join(STATISTICS, "detail");
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
			// 查询条件
			List<Object> params = new ArrayList<Object>();
			HttpSession session = request.getSession();
			ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
					.getPrincipal();
			params.add(request.getParameter("customerName"));
			params.add(request.getParameter("markType"));
			params.add(request.getParameter("certificateType"));
			params.add(request.getParameter("certificateCode"));
			params.add(new BigDecimal(Long.valueOf(curUser.getId())));
			params.add(request.getParameter("customerType"));
			params.add(request.getParameter("orgId"));//6	所属机构
			params.add(request.getParameter("customerCd"));//7	客户编号
			params.add(request.getParameter("custMng"));//8	客户经理
			params.add(request.getParameter("employmentType"));//9	雇佣类型
			params.add(request.getParameter("customerStatus"));//10	客户状态
			params.add(curUser.getDataAuthOrgIds());//11	当前登录人拥有所有机构id集合
			params.add(curUser.getDataAuthType());//12	判断是客户经理或者统计查询的角色
			
			//先导出文件到服务器，默认查询10000条
			int PAGE_SIZE = 100000;
			String EXCEL_TPL = "wd_customer_tpl.xlsx";//Excel模板
			String dfzcReportPath = StringUtils.join(ExcelUtils.getTemplatePath(),File.separator);
			String EXCEL_TPL_PATH = dfzcReportPath + EXCEL_TPL;
			
			//查询列表
			Page queryList = uniqueCustomerService.findIndividualQuery(
					1, PAGE_SIZE, params);
			if(queryList == null || queryList.getContent() == null || queryList.getContent().size() == 0){
				throw new RuntimeException("无资料,不能导出报表,请确认!");
			}
			Map map = new HashMap();
			List<Object[]> dataList = queryList.getContent();
			List<IndividualVO> customerReportList = null;
			for(Object[] obj : dataList){
				if(null == customerReportList){
					customerReportList = new ArrayList<IndividualVO>();
				}
				IndividualVO infoVO = new IndividualVO();
				infoVO.setCustomerNum((String) obj[1]);
				infoVO.setCustomerName((String) obj[2]);
				String partyType = (String) obj[3];
				if(StringUtils.isNotBlank(partyType)){//客户类型
					String partyTypeName = dataDict.getCodeName("CustomerType", String.valueOf(partyType));
					infoVO.setPartyType(partyTypeName);
				}
				String certificateTypeCd = (String) obj[4];
				if(StringUtils.isNotBlank(certificateTypeCd)){//证件类型
					String certificateTypeCdName = dataDict.getCodeName("CertificateType", String.valueOf(certificateTypeCd));
					infoVO.setCertificateTypeCd(certificateTypeCdName);
				}
				infoVO.setCertificateNum((String) obj[5]);
				String status = (String) obj[6];
				if(StringUtils.isNotBlank(status)){//客户状态
					String statusName = dataDict.getCodeName("CustomerStatusCode", String.valueOf(status));
					infoVO.setStatus(statusName);
				}
				infoVO.setCustManagerName((String) obj[7]);
				if(obj[8]!=null){
					Date createDate = (Date) obj[8];
					infoVO.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(createDate));
				}
				String markType1 = (String) obj[9];
				if(StringUtils.isNotBlank(markType1)){//担保类型
					if(markType1.equals("01,02")){
						infoVO.setMarkType("借款人/担保人");
					}else{
						String markTypeName = dataDict.getCodeName("CustomerMarkType", String.valueOf(markType1));
						infoVO.setMarkType(markTypeName);
					}
				}
				customerReportList.add(infoVO);
			}
			map.put("customerReportList", customerReportList);
			ReportExcelUtils reportExcelUtils= new ReportExcelUtils();
			File file = new File(EXCEL_TPL_PATH);
			InputStream is = new FileInputStream(file);
			String fileName = reportExcelUtils.genernateExcelFileName(is,dfzcReportPath,"客户查询", map);
			//再从服务器下载文件到本地
			FileUtils.downloadExportFile(fileName, request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
//			try {
//				response.sendError(404, "导出失败");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
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
		
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		SessionUser user=new SessionUser(curUser.getId(), curUser.getOrgid(), null);
		//10001表示总部
		String orgLevel=StringUtils.equalsIgnoreCase("10001", String.valueOf(user.getOrgId()))?"1":"2";
		if(vo.getOrgId() == null && !orgLevel.equals("1")){//不是总部
			vo.setOrgId(user.getOrgId());
		}
		//时间处理
		vo.setLoanStartTime(CommonHelper.str2Date(loanStartDate, CommonHelper.DF_DATE));
		vo.setLoanEndTime(CommonHelper.str2Date(loanEndDate, CommonHelper.DF_DATE));
		//查询合同台账列表
		Page<BizContractVo> page = contractMngService.findContractBySearch(pageNumber / pageSize, pageSize, vo);
		//处理并返回查询结果
		return new DataTablesPage(sEcho, page);
	}

	/**
	 * 查看客户信息
	 * 
	 * @param projectId 项目申请ID
	 * @param contractId 合同ID
	 * @param partyId 参与人ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/findCustomer")
	@ResponseBody
	public Result findCustomer(@RequestParam String partyId) {
		try {
			if (StringUtils.isBlank(partyId)) {
				throw new Exception("项目参数获取异常");
			}
			Individual individual = uniqueCustomerService.getIndividual(partyId);
			return success(individual);
		} catch (Exception e) {
			logger.error("查看客户信息异常,客户ID={}" + e.getMessage(), partyId);
			return failure("<strong>数据异常,请联系系统管理员</strong>");
		}
	}
	
	/**
	 * 查看客户信息
	 * 
	 * @param projectId 项目申请ID
	 * @param contractId 合同ID
	 * @param partyId 参与人ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/findParty")
	@ResponseBody
	public Result findParty(@RequestParam String partyId) {
		try {
			if (StringUtils.isBlank(partyId)) {
				throw new Exception("项目参数获取异常");
			}
			// 参与人
			Party party = uniqueCustomerService.findPartyByPartyId(partyId);
			return success(party);
		} catch (Exception e) {
			logger.error("查看客户信息异常,客户ID={}" + e.getMessage(), partyId);
			return failure("<strong>数据异常,请联系系统管理员</strong>");
		}
	}
	
	/**
	 * 查看客户信息
	 * 
	 * @param projectId 项目申请ID
	 * @param contractId 合同ID
	 * @param partyId 参与人ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/findCorporationCustomer")
	@ResponseBody
	public Result findCorporationCustomer(@RequestParam String partyId) {
		try {
			if (StringUtils.isBlank(partyId)) {
				throw new Exception("项目参数获取异常");
			}
			CorporationCustomer corpCus = this.corpCusService.findById(Long.parseLong(partyId));
			return success(corpCus);
		} catch (Exception e) {
			logger.error("查看企业客户信息异常,客户ID={}" + e.getMessage(), partyId);
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
	
	@RequestMapping("/findBusinessList")
	@ResponseBody
	public DataTablesPage findBusinessList(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		
		String partyId = request.getParameter("partyId");
		Page<Object[]> page = null;
		try {
			page = businessApplicationService.findList(firstIndex
					/ pageSize + 1, pageSize, partyId);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}

}





















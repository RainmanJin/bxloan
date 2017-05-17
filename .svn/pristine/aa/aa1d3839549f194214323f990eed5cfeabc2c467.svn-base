package com.coamctech.bxloan.web.controller.repayplan;

import static com.coamctech.bxloan.commons.GlobalConstants.EDIT_LOANGRANT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.FileUtils;
import com.coamctech.bxloan.commons.utils.ReportExcelUtils;
import com.coamctech.bxloan.commons.utils.excel.ExcelUtils;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.bizapply.BusinessApplicationService;
import com.coamctech.bxloan.service.contractmng.ContractMngService;
import com.coamctech.bxloan.service.contractmng.LoanGrantService;
import com.coamctech.bxloan.service.customermng.UniqueCustomerService;
import com.coamctech.bxloan.service.model.excel.ExRepayingPlanVo;
import com.coamctech.bxloan.service.model.statistics.BizRepayingPlanVo;
import com.coamctech.bxloan.service.repayplan.RepayPlanService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.coamctech.bxloan.web.security.ShiroUser;



/**
 * 还款计划控制器
 * 
 * @author lijing
 * @lastModified lijing 2014-09-02 17:30:24
 */
@Controller
@RequestMapping("/" + GlobalConstants.REPAYPLAN)
public class RepayPlanController extends BaseController{
	
	/**放款状态*/
	private final String CODETYPE_PAYLOANSATUS = "PayLoanStatus";
	
	@Autowired
	private ContractMngService contractMngService;
	@Autowired
	private LoanGrantService loanGrantService;
	@Autowired
	private UniqueCustomerService uniqueCustomerService;
	@Autowired
	private BusinessApplicationService businessApplicationService;
	@Autowired
	private RepayPlanService repayPlanService;
	@Autowired
	private ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	private DataDict dataDict;
	/*********************************** 跳转方法 *******************************************/

	/**
	 * 调整还款计划的跳转
	 * 
	 * 
	 * @return String 跳转路径
	 * 
	 * @author lijing
	 * @lastModified lijing 2014-09-02 16:37:51
	 */
	@RequestMapping("/moveToEdit")
	public String moveToEdit(@RequestParam String contractId, Model model) {
		ShiroUser curUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		model.addAttribute("cuserId", curUser.getId());
		model.addAttribute("contractId", contractId);
		return EDIT_LOANGRANT;
	}
	
	/************************还款计划表相关方法*************************************************/
	
	/**
	 * 还款计划的查询方法
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
		String contractId = request.getParameter("contractId");
		
		Contract contract = contractMngService.getContract(Long.valueOf(contractId));
		String contractStatus = "('"+dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S2")+"','"+dataDict.getCodeVal(CODETYPE_PAYLOANSATUS, "S4")+"')";
		PayLoanInfo payLoan = loanGrantService.findPayLoanByContractIdAndContractStatus(contractId,contractStatus);
		if(payLoan==null){
			return new DataTablesPage(0, null);
		}
		List<Object> params = new ArrayList<Object>();
		params.add(contractId);
		params.add(contract.getProjectId()+"");
		params.add(payLoan.getPayLoanId()+"");
		params.add(cuserId);
		List<RepayingPlanDetail> queryList = repayPlanService.findRepayPlanBySearch(
				(firstIndex / pageSize) + 1, pageSize, params);
		if(queryList!=null){
			BigDecimal principalInterestTotal = new BigDecimal("0");//本息
			BigDecimal principalTotal = new BigDecimal("0");//本金
			BigDecimal InterestTotal = new BigDecimal("0");//利息
		for (RepayingPlanDetail rpd : queryList) {
			principalInterestTotal = principalInterestTotal.add(rpd.getCurrentPrincipalInterest());
			principalTotal = principalTotal.add(rpd.getCurrentPrincipal());
			InterestTotal = InterestTotal.add(rpd.getCurrentInterest());
		 }
		RepayingPlanDetail total = new RepayingPlanDetail();
		total.setCurrentPrincipalInterest(principalInterestTotal);//应还本息
		total.setCurrentPrincipal(principalTotal);//应还本金
		total.setCurrentInterest(InterestTotal);//应还利息
		queryList.add(total);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), 0);
		return page;
	}
	/**
	 * 查看还款情况（分页）
	 * "bPaginate": true
	 * @return
	 */
	@RequestMapping("/findByCondition")
	@ResponseBody
	public DataTablesPage findByCondition(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer pageNumber,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		
		String contractId = request.getParameter("contractId");
		Page<BizRepayingPlanVo> page = repayPlanService.findRepayPlanByCondition(pageNumber / pageSize, pageSize, contractId);
		
		return new DataTablesPage(sEcho, page);
	}
	/**
	 * 查看还款情况（不分页）
	 * "bPaginate": true
	 * @return
	 */
	@RequestMapping("/findByConditionNotPaging")
	@ResponseBody
	public DataTablesPage findByConditionNotPaging(@RequestParam("sEcho") Integer sEcho,
			@RequestParam("iDisplayStart") Integer firstIndex,
			@RequestParam("iDisplayLength") Integer pageSize,
			HttpServletRequest request) {
		// 查询条件
		String contractId = request.getParameter("contractId");
		List<BizRepayingPlanVo> queryList = new ArrayList<BizRepayingPlanVo>(
				repayPlanService.findRepayPlanByConditionNotPaging(contractId));
		/**
		 * 列表项合计
		 */
		if(queryList != null && queryList.size() > 0){
			BigDecimal principalInterestTotal = BigDecimal.ZERO;//本息
			BigDecimal principalTotal = BigDecimal.ZERO;//本金
			BigDecimal InterestTotal = BigDecimal.ZERO;//利息
			
			BigDecimal repayedPrincipal = BigDecimal.ZERO;//实还本金
			BigDecimal repayedInterest = BigDecimal.ZERO;//实还利息
			BigDecimal repayedImposeInterest = BigDecimal.ZERO;//实还逾期利息
			BigDecimal overdueInterest = BigDecimal.ZERO;//逾期利息
			for (BizRepayingPlanVo rpv : queryList) {
				principalInterestTotal = principalInterestTotal.add(rpv.getCurrentPrincipalInterest());
				principalTotal = principalTotal.add(rpv.getCurrentPrincipal());
				InterestTotal = InterestTotal.add(rpv.getCurrentInterest());
				
				repayedPrincipal = repayedPrincipal.add(rpv.getRepayedPrincipal());
				repayedInterest = repayedInterest.add(rpv.getRepayedInterest());
				repayedImposeInterest = repayedImposeInterest.add(rpv.getRepayedImposeInterest());
				overdueInterest = overdueInterest.add(rpv.getSumod());
				
			 }
			BizRepayingPlanVo total = new BizRepayingPlanVo();
			total.setCurrentPrincipalInterest(principalInterestTotal);//应还本息
			total.setCurrentPrincipal(principalTotal);//应还本金
			total.setCurrentInterest(InterestTotal);//应还利息
			
			total.setRepayedPrincipal(repayedPrincipal);
			total.setRepayedInterest(repayedInterest);
			total.setRepayedImposeInterest(repayedImposeInterest);
			total.setSumod(overdueInterest);
			queryList.add(total);
		}
		DataTablesPage page = new DataTablesPage(sEcho, queryList, new Long(queryList.size()), 0);
		return page;
	}
	
	/**
	 * 查看还款情况显示参数
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findRepayPlanParams", method = RequestMethod.POST)
	@ResponseBody
	public Result findRepayPlanParams(String contractId) {
		
		List<Object> list = repayPlanService.findRepayPlanParams(contractId);
		return success(list);
	}
	
	/**
	 * 根据合同id下载还款计划
	 * @param request
	 * @param response
	 * @param contractId
	 */
	@RequestMapping("/downloadRepayingPlan")
	public void downloadRepayingPlan(HttpServletRequest request,
			HttpServletResponse response, Long contractId){
		ExRepayingPlanVo vo= repayPlanService.findRepayingPlanForExcelByContractId(contractId);
		String EXCEL_TPL = "repayingPlanDetailReport.xlsx";//Excel模板
		String dfzcReportPath = StringUtils.join(ExcelUtils.getTemplatePath(),File.separator);
		String EXCEL_TPL_PATH = dfzcReportPath + EXCEL_TPL;
		ReportExcelUtils reportExcelUtils= new ReportExcelUtils();
		File file = new File(EXCEL_TPL_PATH);
		try {
			InputStream is = new FileInputStream(file);
			String fileName = reportExcelUtils.genernateExcelFileName(is,dfzcReportPath,"还款计划", vo.toMap());
			//再从服务器下载文件到本地
			FileUtils.downloadExportFile(fileName, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "下载文件出错");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	

}


















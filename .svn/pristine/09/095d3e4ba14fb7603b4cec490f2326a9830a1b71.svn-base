package com.coamctech.bxloan.web.controller.contractstandingbook;

import static com.coamctech.bxloan.commons.GlobalConstants.CONTRACTSTANDINGBOOK;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.result.DataTablesPage;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.contractstandingbook.ContractStandingBookService;
import com.coamctech.bxloan.service.model.contractstandingbook.CustomerInfoVO;
import com.coamctech.bxloan.web.security.ShiroUser;

@Controller
@RequestMapping("/" + CONTRACTSTANDINGBOOK)
public class ContractStandingBookController {
	@Autowired
	private ContractStandingBookService contractStandingBookService;

	@RequestMapping
	public String index() {
		return CONTRACTSTANDINGBOOK + "/main";
	}

	@RequestMapping("/list")
	@ResponseBody
	public DataTablesPage list(Integer sEcho, Integer iDisplayStart,
			Integer iDisplayLength, HttpServletRequest request) {
		ShiroUser curPerson = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		CustomerInfoVO vo = new CustomerInfoVO();
		// 搜索条件
		vo.setCustomerName(request.getParameter("customerName"));
		vo.setContractStatusCd(request.getParameter("ContractStatusCode"));
		vo.setGuaranteeMode(request.getParameter("guarantee_mode")); // 担保方式

		String payLoanDateFrom = request.getParameter("pay_loan_date_from");// 放款日期起日
		String payLoanDateTo = request.getParameter("pay_loan_date_to");// 放款日期到日

		String applyCustomerName = request.getParameter("applyCustomerName");// 客户经理
		String agencyOrgId = request.getParameter("agencyOrgId");// 所属机构
		String customerType = request.getParameter("CustomerType");// 客户类型
		String termUnitCd = request.getParameter("timelimittype");// 合同期限
		String typeCd = request.getParameter("typeCd");// 客户标志
		String assetsStatusCode = request.getParameter("assetsStatusCode");// 资产状态

		String ifInsure = request.getParameter("ifInsure");// 是否有保险
		String isHeadCol = request.getParameter("isHeadCol");// 是否总部协同业务

		vo.setContractNum(request.getParameter("contract_num")); // 合同编号
		vo.setCredit_contract_num(request.getParameter("credit_contract_num")); // 授信合同编号
		vo.setCustomerNum(request.getParameter("customerNum")); // 客户编码

		if (StringUtils.isNotBlank(payLoanDateFrom)
				&& !"null".equals(payLoanDateFrom)) {
			vo.setPayloanDateFrom(DateTools.stringToDate(payLoanDateFrom,
					"yyyy-MM-dd"));
		}
		if (StringUtils.isNotBlank(payLoanDateTo)
				&& !"null".equals(payLoanDateTo)) {
			vo.setPayloanDateTo(DateTools.stringToDate(payLoanDateTo,
					"yyyy-MM-dd"));
		}

		// vo.setRole(curPerson.getCurrentRole().getId().toString());
		vo.setApplyUserNum(curPerson.getId().toString());
		vo.setOrgId(curPerson.getOrgid().toString());
		vo.setLogName(curPerson.getLogname());

		if (StringUtils.isNotBlank(applyCustomerName)
				&& !"null".equals(applyCustomerName)) {
			vo.setApplyCustomerName(applyCustomerName);// 客户经理
		}

		vo.setAgencyOrgId(curPerson.getOrgid().toString());
		if (StringUtils.isNotBlank(customerType)
				&& !"null".equals(customerType)) {
			vo.setCustomerType(customerType);
		}
		if (StringUtils.isNotBlank(typeCd) && !"null".equals(typeCd)) {
			vo.setTypeCd(typeCd);
		}
		if (StringUtils.isNotBlank(termUnitCd) && !"null".equals(termUnitCd)) {
			vo.setTermUnitCd(termUnitCd);
		}
		if (StringUtils.isNotBlank(assetsStatusCode)
				&& !"null".equals(assetsStatusCode)) {
			vo.setAssetsStatusCode(assetsStatusCode);
		}
		// add by wangpeng on 2014-11-04 start
		if (StringUtils.isNotBlank(ifInsure) && !"null".equals(ifInsure)) {
			vo.setIfInsure(ifInsure);
		}
		if (StringUtils.isNotBlank(isHeadCol) && !"null".equals(isHeadCol)) {
			vo.setIsHeadCol(isHeadCol);
		}
		// add by wangpeng on 2014-11-04 end
		// 组装还款查询SQL
		Page<Object[]> page = contractStandingBookService.findListBySearch(vo,
				iDisplayStart / iDisplayLength + 1, iDisplayLength);
		List<Object[]> list = page.getContent();
		return new DataTablesPage(sEcho, list, page.getTotalElements());
	}
}

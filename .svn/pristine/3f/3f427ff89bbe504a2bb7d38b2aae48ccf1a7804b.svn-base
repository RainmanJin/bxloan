package com.coamctech.bxloan.web.controller.loancalcu;

import static com.coamctech.bxloan.commons.GlobalConstants.LOAN_CALCULATE;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coamctech.bxloan.commons.controller.BaseController;
import com.coamctech.bxloan.commons.result.Result;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.service.loanprocess.InterestCalCulateService;
import com.coamctech.bxloan.service.model.loanprocess.InterestCalCulateForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestVO;

@Controller
@RequestMapping("/" + LOAN_CALCULATE)
public class CalculateController extends BaseController {

	@Autowired
	private InterestCalCulateService interestCalCulateService;

	// 贷款试算
	@RequestMapping(value = "/loanCalc", method = RequestMethod.POST)
	@ResponseBody
	public Result loanCalc(String loanAmount, String loanStartDate,
			String applyTerm, String applyTermUnit, String repaymentDate,
			String repaymentNumber, String repayment, String rate) {

		InterestCalCulateForm interestForm = buildFormBean(loanAmount,
				loanStartDate, applyTerm, applyTermUnit, repaymentDate,
				repaymentNumber, repayment, rate);

		List<InterestVO> result = null;
		try {
			result = interestCalCulateService.loanCounter(interestForm);
			return success("list", result);
		} catch (Exception e) {
			return failure(e.getMessage());
		}
	}

	private InterestCalCulateForm buildFormBean(String loanAmount,
			String loanStartDate, String applyTerm, String applyTermUnit,
			String repaymentDate, String repaymentNumber, String repayment,
			String rate) {
		InterestCalCulateForm interestForm = new InterestCalCulateForm();
		interestForm.setLoanAmount(new BigDecimal(loanAmount)); // 贷款金额
		interestForm.setApplyTerm(new Integer(applyTerm)); // 期限
		interestForm.setApplyTermUnit(applyTermUnit); // 期限单位
		if (StringUtils.isNotBlank(repaymentNumber)) {
			interestForm.setRepaymentNumber(new Integer(repaymentNumber)); // 还款周期月数
		}
		if (StringUtils.isNotBlank(repaymentDate)) {
			interestForm.setRepaymentDate(new Integer(repaymentDate)); // 还款日期
		}
		interestForm.setRepayment(repayment); // // 还款方式

		interestForm.setLoanStartDate(DateTools.stringToDate(loanStartDate,
				"yyyy-MM-dd")); // 贷款开始时间
		interestForm.setRate(new BigDecimal(rate)); // 年利率
		interestForm.setProjectId(null);

		Date endDate = DateTools.getEndingDate(interestForm.getLoanStartDate(),
				interestForm.getApplyTermUnit(), interestForm.getApplyTerm());
		interestForm.setLoanEndDate(endDate);

		interestForm
				.setRate(interestForm.getRate().divide(new BigDecimal(100)));
		return interestForm;
	}

	// 贷款试算
	// @ResponseBody
	// @RequestMapping(value = "/loanCal", method = RequestMethod.POST)
	// public Result loanCal(String loanAmount, String applyTerm,
	// String repayment, String rate, String repaymentNumber,
	// String loanStartDate, String repaymentDate) {
	// InterestCalCulateForm interestForm = new InterestCalCulateForm();
	// interestForm.setLoanAmount(new BigDecimal(loanAmount));
	// interestForm.setApplyTerm(new Integer(applyTerm));
	// interestForm.setApplyTermUnit("2");
	// interestForm.setRepaymentNumber(new Integer(repaymentNumber));
	// interestForm.setRepayment(repayment);
	// interestForm.setRepaymentDate(new Integer(repaymentDate));
	// interestForm.setLoanStartDate(DateTools.stringToDate(loanStartDate,
	// "yyyy-MM-dd"));
	// interestForm.setRate(new BigDecimal(rate));
	//
	// List listReturn = new ArrayList();
	// if (interestForm != null) {
	// interestForm.setProjectId(null);
	// Date endDate = DateTools.getEndingDate(
	// interestForm.getLoanStartDate(),
	// interestForm.getApplyTermUnit(),
	// interestForm.getApplyTerm());
	// interestForm.setLoanEndDate(endDate);
	// interestForm.setRate(interestForm.getRate().divide(
	// new BigDecimal(100)));
	// List<InterestVO> list = interestCalCulateService
	// .calCulate(interestForm);
	// listReturn = list;
	// if (list != null) {
	// for (InterestVO rpp : list) {
	// System.out.println("期数：" + rpp.getRepaymentNumber()
	// + "     计划还款日：" + rpp.getRepaymentDate()
	// + "   本期应还金额：" + rpp.getCurrentPricipalInterest()
	// + "    应还本金：" + rpp.getCurrentPricipal()
	// + "   应还利息：" + rpp.getCurrentInterest() + "累计本金="
	// + rpp.getEndCurrentPricipal() + "累计利息="
	// + rpp.getCurrentInterest() + "   贷款余额"
	// + rpp.getEndCurrentPrincipalBalance());
	// }
	// }
	// }
	//
	// return success(listReturn);
	// }

}

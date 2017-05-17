package com.coamctech.bxloan.service.pettyloan;

import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.entity.BizExpenseRate;
import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.service.pettyloan.bo.LoanResult;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfInMoney;

/**
 * 贷款——还款
 *
 */
public interface LoanRepayService {
	
	/**
	 * 更新费率
	 * @param transactionNO
	 * @param repayLoanStatus
	 */
	BizExpenseRate updateBizExpenseRate(String transactionNO,String repayLoanStatus);
	
	/**
	 * 更新还款
	 * @param transactionNO	业务编号
	 * @param repayLoanStatus	还款状态
	 */
	void updateRepayLoan(String transactionNO,String repayLoanStatus);
	
	
	/**
	 * 每期还款的更新操作
	 * @param rpdId	还款计划明细Id（repayingPlanDetailId）
	 * @param repayingAmount	还款金额
	 * @param norm2over		正常转逾期
	 * @param repayedDate		还款日期
	 * @param fundsSource		资金来源
	 */
	@Deprecated
	String updateRepayLoanOfRpd(Long rpdId, BigDecimal repayingAmount,
			boolean norm2over, Date repayedDate, String fundsSource);
	
	/**
	 * <b>费用登记</b>
	 * @param contractId
	 * @param expenseAmt	费用金额
	 * @param fundsSource	费用来源
	 * @param repayDate		交费日期
	 * @return
	 */
	LoanResult addFeeRegisterInfo(Long contractId,BigDecimal expenseAmt,String fundsSource,String repayDate);
	/**
	 * 还款的相关更新（还款计划、还款计划明细、还款明细、欠款明细、合同）
	 * @param inMoney
	 * @param repayLoan 
	 * @return 
	 */
	LoanResult updateAllInfoOfRepayLoan(ParamsOfInMoney inMoney, RepayLoan repayLoan);
}

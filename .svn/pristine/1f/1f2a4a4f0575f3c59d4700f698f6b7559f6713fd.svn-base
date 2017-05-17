package com.coamctech.bxloan.service.pettyloan;

import java.util.Date;
import java.util.List;

import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.service.model.loanprocess.InterestCalCulateForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestPlanForm;

/**
 * 贷款——放款
 *
 */
public interface LoanPayService {

	/**
	 * 更新贷款放款信息
	 * @param transactionNo 交易编号
	 * @param repayLoanStatus 放款状态
	 * @param busiDate
	 * @return
	 */
	public PayLoanInfo updatePayLoanInfo(String transactionNo,String payLoanStatus,Date busiDate);
	
	/**
	 * 再次放款
	 * @param iccf
	 * @return	业务编号
	 */
	String againPayoutLoan(InterestCalCulateForm iccf,PayLoanInfo payLoan );
	/**
	 *  初始化还款计划
	 * @param iccf
	 * @return	业务编号
	 */
	String initRepayPlanDb(InterestCalCulateForm iccf,PayLoanInfo payLoan );
	
	/**
	 * 根据合同查询自定义还款计划明细
	 * @param contract{projectId,productType}
	 * @return
	 */
	public  List<InterestPlanForm> findCustPlanList(Contract contract);
	
	
	/**
	 * 放款备份（还款计划、还款计划明细、还款明细）
	 * @param billCd 业务编号
	 */
	public void backupData(String billCd);
	
}

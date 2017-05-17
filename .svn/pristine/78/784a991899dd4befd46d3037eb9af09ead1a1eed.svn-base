package com.coamctech.bxloan.service.pettyloan;

import java.util.Date;

import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.service.pettyloan.bo.LoanResult;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfInMoney;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfOutMoney;

/**
 *	贷款功能接口（放款、还款、入账、退单）
 * 	@author AcoreHeng
 */
public interface LoanFunctionService {
	/**
	 * 放款（保存放款记录后调用）
	 * @param outMoney
	 * @return
	 */
	LoanResult outOfMoney(ParamsOfOutMoney outMoney);
	
	/**
	 * 还款（保存还款记录后调用）
	 * @param inMoney
	 * @return
	 */
	LoanResult inOfMoney(ParamsOfInMoney inMoney, RepayLoan repayLoan);
	/**
	 * 处理账单
	 * @param billCd	业务凭证编号
	 * @param opertor	操作人
	 * @param busiDt	业务时间（如果为空则取当前时间）
	 * @param status	1：入账，2：退单
	 * @param backCause	退单原因（退单时不可为空）
	 */
	void dealBill(String billCd,String operator,Date busiDate,String status,String backCause);
	/**
	 * 账单冲正
	 * @param billCd
	 * @param operator
	 */
	void flushesBill(String billCd,String operator);

	
	
	
}

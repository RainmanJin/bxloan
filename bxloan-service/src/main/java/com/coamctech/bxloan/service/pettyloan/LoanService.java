package com.coamctech.bxloan.service.pettyloan;

import com.coamctech.bxloan.entity.RepayingPlan;


/**
 * 贷款
 * @author WallenHeng
 *
 */
public interface LoanService {
	
	
	/**
	 * 审批服务（还款计划等账务信息）
	 * @param transNo	业务编号
	 * @param rp	还款明细
	 * @param payLoanStatus	放款状态
	 * @param event	事件：放款，资产转出
	 */
	void approvalService(String transNo,RepayingPlan rp, String payLoanStatus, String event);
	/**
	 * 审批服务（还款计划等账务信息）
	 * @param transNo	业务编号
	 * @param payLoanStatus	放款状态
	 * @param event	事件：放款，资产转出
	 */
	void approvalService(String transNo, String loanStatus, String event);
}

package com.coamctech.bxloan.service.model.excel;

/**
 * 还款计划明细
 * @author Acore
 *
 */
public class ExRepayingPlanDetailVo {

	/**
	 * 当前期次
	 */
	private Integer curPeriod;
	/**
	 * 计划还款日
	 */
	private String plannedRepaymentDateStr;
	/**
	 * 应还金额
	 */
	private String payableAmt;
	/**
	 * 应还本金
	 */
	private String payablePrincipal;
	/**
	 * 应还利息
	 */
	private String payableInterest;
	
	
	public Integer getCurPeriod() {
		return curPeriod;
	}
	public void setCurPeriod(Integer curPeriod) {
		this.curPeriod = curPeriod;
	}
	public String getPlannedRepaymentDateStr() {
		return plannedRepaymentDateStr;
	}
	public void setPlannedRepaymentDateStr(String plannedRepaymentDateStr) {
		this.plannedRepaymentDateStr = plannedRepaymentDateStr;
	}
	public String getPayableAmt() {
		return payableAmt;
	}
	public void setPayableAmt(String payableAmt) {
		this.payableAmt = payableAmt;
	}
	public String getPayablePrincipal() {
		return payablePrincipal;
	}
	public void setPayablePrincipal(String payablePrincipal) {
		this.payablePrincipal = payablePrincipal;
	}
	public String getPayableInterest() {
		return payableInterest;
	}
	public void setPayableInterest(String payableInterest) {
		this.payableInterest = payableInterest;
	}
}

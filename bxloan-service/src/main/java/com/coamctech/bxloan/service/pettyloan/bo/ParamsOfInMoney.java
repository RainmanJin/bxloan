package com.coamctech.bxloan.service.pettyloan.bo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *	还款时的参数
 *	@since	2014-10-10 13:48:47
 */
public class ParamsOfInMoney implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6013858389119150420L;
	/**
	 * 合同Id
	 */
	private Long contractId;
	/**
	 * 是否逾期（true：逾期还款；false：正常还款，未逾期。默认：false）
	 */
	private boolean overdueFlag=false;
	
	/**
	 * 是否正常转逾期，true是，false：否
	 */
	private boolean normal2Overdue=false;
	/**
	 * 还款Id
	 */
	private Long repayLoanId;
	/**
	 * 当期还款计划Id
	 */
	private Long curRpdId;
	// form 参数
	/**
	 * 
	 */
	private Date repayDate;
	/**
	 * 实还金额
	 */
	private BigDecimal actualAmt=BigDecimal.ZERO;
	
	
	public Long getRepayLoanId() {
		return repayLoanId;
	}
	public void setRepayLoanId(Long repayLoanId) {
		this.repayLoanId = repayLoanId;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public boolean isOverdueFlag() {
		return overdueFlag;
	}
	public void setOverdueFlag(boolean overdueFlag) {
		this.overdueFlag = overdueFlag;
	}
	public boolean isNormal2Overdue() {
		return normal2Overdue;
	}
	public void setNormal2Overdue(boolean normal2Overdue) {
		this.normal2Overdue = normal2Overdue;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public BigDecimal getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}
	public Long getCurRpdId() {
		return curRpdId;
	}
	public void setCurRpdId(Long curRpdId) {
		this.curRpdId = curRpdId;
	}
	
	
}

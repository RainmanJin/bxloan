package com.coamctech.bxloan.service.model.loanprocess;

import java.math.BigDecimal;
import java.util.Date;

public class InterestResultVO {

	/**
	 * 交易编号
	 */
	private String transactionNo;
	
	/**
	 * 实还拖欠本金
	 */
	private BigDecimal loanAmount = BigDecimal.ZERO;

	/**
	 * 计划明细表状态
	 */
	private String status;
	
	/**
	 *  利息
	 */
	private BigDecimal interest = BigDecimal.ZERO;
	
	/**
	 * 实还本金
	 */
	private BigDecimal repayedPricipal = BigDecimal.ZERO;

	/**
	 * 实还逾期利息
	 */
	private BigDecimal repayedImposeInterest = BigDecimal.ZERO;
	
	/**
	 * 实还拖欠本息
	 */
	private BigDecimal repayedPricipalInterest = BigDecimal.ZERO;
	
	/**
	 * 实还当期利息
	 */
	private BigDecimal repayedCurrentInterest = BigDecimal.ZERO;
	
	/**
	 * 实还当期本金
	 */
	private BigDecimal repayedCurrentPricipal = BigDecimal.ZERO;
	
	// 提前还款
	
	/**
	 * 还款计划
	 */
	private Integer repayingPlanId;
	
	/**
	 * 还款日期
	 */
	private Date repayedDate;
	
	/**
	 * 部分提前还款
	 */
	private BigDecimal bcDedit;
	
	/**
	 * 合同ID
	 */
	private Integer contractId;
	
	// 部分提前还款
	/**
	 * 实还总额
	 */
	private BigDecimal repayingRealAmount;
	
	/**
	 * 应还总额
	 */
	private BigDecimal repayingAmount;
	
	/**
	 * 提前还款本金
	 */
	private BigDecimal advancePrincipal;
	
	/**
	 * 资金来源
	 */
	private String sourceFundType;
	
	/**
	 * 表内利息
	 */
	private BigDecimal repayedTableInterest = BigDecimal.ZERO;
	// 部分提前还款
	
	// 提前还款
	
	// 登记费用
	private BigDecimal costAmt; //费用金额
	// 登记费用
	
	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getRepayedPricipal() {
		return repayedPricipal;
	}

	public void setRepayedPricipal(BigDecimal repayedPricipal) {
		this.repayedPricipal = repayedPricipal;
	}

	public BigDecimal getRepayedImposeInterest() {
		return repayedImposeInterest;
	}

	public void setRepayedImposeInterest(BigDecimal repayedImposeInterest) {
		this.repayedImposeInterest = repayedImposeInterest;
	}

	public BigDecimal getRepayedPricipalInterest() {
		return repayedPricipalInterest;
	}

	public void setRepayedPricipalInterest(BigDecimal repayedPricipalInterest) {
		this.repayedPricipalInterest = repayedPricipalInterest;
	}

	public BigDecimal getRepayedCurrentInterest() {
		return repayedCurrentInterest;
	}

	public void setRepayedCurrentInterest(BigDecimal repayedCurrentInterest) {
		this.repayedCurrentInterest = repayedCurrentInterest;
	}

	public BigDecimal getRepayedCurrentPricipal() {
		return repayedCurrentPricipal;
	}

	public void setRepayedCurrentPricipal(BigDecimal repayedCurrentPricipal) {
		this.repayedCurrentPricipal = repayedCurrentPricipal;
	}

	public Integer getRepayingPlanId() {
		return repayingPlanId;
	}

	public void setRepayingPlanId(Integer repayingPlanId) {
		this.repayingPlanId = repayingPlanId;
	}

	public BigDecimal getBcDedit() {
		return bcDedit;
	}

	public void setBcDedit(BigDecimal bcDedit) {
		this.bcDedit = bcDedit;
	}

	public BigDecimal getRepayingRealAmount() {
		return repayingRealAmount;
	}

	public void setRepayingRealAmount(BigDecimal repayingRealAmount) {
		this.repayingRealAmount = repayingRealAmount;
	}

	public BigDecimal getRepayingAmount() {
		return repayingAmount;
	}

	public void setRepayingAmount(BigDecimal repayingAmount) {
		this.repayingAmount = repayingAmount;
	}

	public Date getRepayedDate() {
		return repayedDate;
	}

	public void setRepayedDate(Date repayedDate) {
		this.repayedDate = repayedDate;
	}

	public String getSourceFundType() {
		return sourceFundType;
	}

	public void setSourceFundType(String sourceFundType) {
		this.sourceFundType = sourceFundType;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public BigDecimal getAdvancePrincipal() {
		return advancePrincipal;
	}

	public void setAdvancePrincipal(BigDecimal advancePrincipal) {
		this.advancePrincipal = advancePrincipal;
	}

	public BigDecimal getCostAmt() {
		return costAmt;
	}

	public void setCostAmt(BigDecimal costAmt) {
		this.costAmt = costAmt;
	}

	public BigDecimal getRepayedTableInterest() {
		return repayedTableInterest;
	}

	public void setRepayedTableInterest(BigDecimal repayedTableInterest) {
		this.repayedTableInterest = repayedTableInterest;
	}

}

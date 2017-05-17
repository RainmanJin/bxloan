package com.coamctech.bxloan.web.vo.bizapply;

import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class ExpectCashFlowAgroVo {
	private Long id;
	private Long projectId;
	private BigDecimal familyCost;
	private BigDecimal otherLoanRepayment;
	private BigDecimal costTotal;
	private BigDecimal balance;
	private BigDecimal balanceBeforeLoan;
	private BigDecimal income;
	private BigDecimal cost;
	private BigDecimal balanceAfterLoan;
	private Long month;
	
	private ExpectCashFlowAgroVo() {
		super();
	}
	private ExpectCashFlowAgroVo(Long projectId, BigDecimal familyCost,
			BigDecimal otherLoanRepayment, BigDecimal costTotal,
			BigDecimal balance, BigDecimal balanceBeforeLoan,
			BigDecimal income, BigDecimal cost, BigDecimal balanceAfterLoan,
			Long month) {
		super();
		this.projectId = projectId;
		this.familyCost = familyCost;
		this.otherLoanRepayment = otherLoanRepayment;
		this.costTotal = costTotal;
		this.balance = balance;
		this.balanceBeforeLoan = balanceBeforeLoan;
		this.income = income;
		this.cost = cost;
		this.balanceAfterLoan = balanceAfterLoan;
		this.month = month;
	}
	public ExpectCashFlowAgroVo(Object[] input) {
		int i = 0;
		this.id = CommonHelper.toLong(input[i++]);
		this.projectId = CommonHelper.toLong(input[i++]);
		this.familyCost = CommonHelper.toBigDecimal(input[i++]);
		this.otherLoanRepayment = CommonHelper.toBigDecimal(input[i++]);
		this.costTotal = CommonHelper.toBigDecimal(input[i++]);
		this.balance = CommonHelper.toBigDecimal(input[i++]);
		this.balanceBeforeLoan = CommonHelper.toBigDecimal(input[i++]);
		this.income = CommonHelper.toBigDecimal(input[i++]);
		this.cost = CommonHelper.toBigDecimal(input[i++]);
		this.balanceAfterLoan = CommonHelper.toBigDecimal(input[i++]);
		this.month = CommonHelper.toLong(input[i++]);
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public BigDecimal getFamilyCost() {
		return familyCost;
	}
	public void setFamilyCost(BigDecimal familyCost) {
		this.familyCost = familyCost;
	}
	public BigDecimal getOtherLoanRepayment() {
		return otherLoanRepayment;
	}
	public void setOtherLoanRepayment(BigDecimal otherLoanRepayment) {
		this.otherLoanRepayment = otherLoanRepayment;
	}
	public BigDecimal getCostTotal() {
		return costTotal;
	}
	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getBalanceBeforeLoan() {
		return balanceBeforeLoan;
	}
	public void setBalanceBeforeLoan(BigDecimal balanceBeforeLoan) {
		this.balanceBeforeLoan = balanceBeforeLoan;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public BigDecimal getBalanceAfterLoan() {
		return balanceAfterLoan;
	}
	public void setBalanceAfterLoan(BigDecimal balanceAfterLoan) {
		this.balanceAfterLoan = balanceAfterLoan;
	}
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}

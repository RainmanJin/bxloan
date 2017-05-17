package com.coamctech.bxloan.entity;
// default package
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * ExpectCashFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EXPECT_CASH_FLOW", schema = GlobalConstants.WD_SCHEMA)
public class ExpectCashFlow implements java.io.Serializable {
	// Fields
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

	// Constructors
	/** default constructor */
	public ExpectCashFlow() {
	}

	/** minimal constructor */
	public ExpectCashFlow(Long id) {
		this.id = id;
	}

	/** full constructor */
	public ExpectCashFlow(Long id, Long projectId,
			BigDecimal familyCost, BigDecimal otherLoanRepayment,
			BigDecimal costTotal, BigDecimal balance,
			BigDecimal balanceBeforeLoan, BigDecimal income, BigDecimal cost,
			BigDecimal balanceAfterLoan, Long month) {
		this.id = id;
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

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_EXPECT_CASH_FLOW", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 20, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "FAMILY_COST", precision = 20, scale = 0)
	public BigDecimal getFamilyCost() {
		return this.familyCost;
	}

	public void setFamilyCost(BigDecimal familyCost) {
		this.familyCost = familyCost;
	}

	@Column(name = "OTHER_LOAN_REPAYMENT", precision = 20, scale = 0)
	public BigDecimal getOtherLoanRepayment() {
		return this.otherLoanRepayment;
	}

	public void setOtherLoanRepayment(BigDecimal otherLoanRepayment) {
		this.otherLoanRepayment = otherLoanRepayment;
	}

	@Column(name = "COST_TOTAL", precision = 20, scale = 0)
	public BigDecimal getCostTotal() {
		return this.costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	@Column(name = "BALANCE", precision = 20, scale = 0)
	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Column(name = "BALANCE_BEFORE_LOAN", precision = 20, scale = 0)
	public BigDecimal getBalanceBeforeLoan() {
		return this.balanceBeforeLoan;
	}

	public void setBalanceBeforeLoan(BigDecimal balanceBeforeLoan) {
		this.balanceBeforeLoan = balanceBeforeLoan;
	}

	@Column(name = "INCOME", precision = 20, scale = 0)
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@Column(name = "COST", precision = 20, scale = 0)
	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Column(name = "BALANCE_AFTER_LOAN", precision = 20, scale = 0)
	public BigDecimal getBalanceAfterLoan() {
		return this.balanceAfterLoan;
	}

	public void setBalanceAfterLoan(BigDecimal balanceAfterLoan) {
		this.balanceAfterLoan = balanceAfterLoan;
	}

	@Column(name = "MONTH", precision = 20, scale = 0)
	public Long getMonth() {
		return this.month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}
}

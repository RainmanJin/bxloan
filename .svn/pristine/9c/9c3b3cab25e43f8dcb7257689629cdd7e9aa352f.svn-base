package com.coamctech.bxloan.entity;

// default package

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * QuotaMeasure entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "QUOTA_MEASURE", schema = WD_SCHEMA)
public class QuotaMeasure implements java.io.Serializable {

	// Fields

	private Long budgetId;
	private Long projectId;
	private BigDecimal monthIncome;// 月均收入
	private BigDecimal repaymentThisTime;// 本笔贷款月还款
	private BigDecimal otherRepayment;// 其他非抵质押款还款额
	private BigDecimal calcLoanAmt;// 计算贷款金额
	private BigDecimal calcLoanTerm;// 计算贷款期限
	private Date createDate;// 创建日期
	private Long partyId;// 客户Id
	private String orgId;// 机构Id
	private String orgName;// 机构名称
	private BigDecimal controllableIncome;// 月可支配收入

	// Constructors

	/** default constructor */
	public QuotaMeasure() {
	}

	/** minimal constructor */
	public QuotaMeasure(Long budgetId, Long projectId) {
		this.budgetId = budgetId;
		this.projectId = projectId;
	}

	/** full constructor */
	public QuotaMeasure(Long budgetId, Long projectId, BigDecimal monthIncome,
			BigDecimal repaymentThisTime, BigDecimal otherRepayment,
			BigDecimal calcLoanAmt, BigDecimal calcLoanTerm, Date createDate,
			Long partyId, String orgId, String orgName,
			BigDecimal controllableIncome) {
		this.budgetId = budgetId;
		this.projectId = projectId;
		this.monthIncome = monthIncome;
		this.repaymentThisTime = repaymentThisTime;
		this.otherRepayment = otherRepayment;
		this.calcLoanAmt = calcLoanAmt;
		this.calcLoanTerm = calcLoanTerm;
		this.createDate = createDate;
		this.partyId = partyId;
		this.orgId = orgId;
		this.orgName = orgName;
		this.controllableIncome = controllableIncome;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_QUOTA_MEASURE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "BUDGET_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getBudgetId() {
		return this.budgetId;
	}

	public void setBudgetId(Long budgetId) {
		this.budgetId = budgetId;
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "MONTH_INCOME", precision = 20, scale = 2)
	public BigDecimal getMonthIncome() {
		return this.monthIncome;
	}

	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}

	@Column(name = "REPAYMENT_THIS_TIME", precision = 20, scale = 2)
	public BigDecimal getRepaymentThisTime() {
		return this.repaymentThisTime;
	}

	public void setRepaymentThisTime(BigDecimal repaymentThisTime) {
		this.repaymentThisTime = repaymentThisTime;
	}

	@Column(name = "OTHER_REPAYMENT", precision = 20, scale = 2)
	public BigDecimal getOtherRepayment() {
		return this.otherRepayment;
	}

	public void setOtherRepayment(BigDecimal otherRepayment) {
		this.otherRepayment = otherRepayment;
	}

	@Column(name = "CALC_LOAN_AMT", precision = 20, scale = 2)
	public BigDecimal getCalcLoanAmt() {
		return this.calcLoanAmt;
	}

	public void setCalcLoanAmt(BigDecimal calcLoanAmt) {
		this.calcLoanAmt = calcLoanAmt;
	}

	@Column(name = "CALC_LOAN_TERM", precision = 20, scale = 2)
	public BigDecimal getCalcLoanTerm() {
		return this.calcLoanTerm;
	}

	public void setCalcLoanTerm(BigDecimal calcLoanTerm) {
		this.calcLoanTerm = calcLoanTerm;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "ORG_ID", length = 30)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME", length = 200)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "CONTROLLABLE_INCOME", precision = 20, scale = 2)
	public BigDecimal getControllableIncome() {
		return this.controllableIncome;
	}

	public void setControllableIncome(BigDecimal controllableIncome) {
		this.controllableIncome = controllableIncome;
	}

}
package com.coamctech.bxloan.entity;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WorkDraft entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WORK_DRAFT",schema=WD_SCHEMA)
public class CopyOfWorkDraft implements java.io.Serializable {

	// Fields

	private BigDecimal workPapeId;
	private BigDecimal customerNum;
	private BigDecimal genderCondition;
	private BigDecimal workCondition;
	private BigDecimal creditComdition;
	private BigDecimal ageCondition;
	private BigDecimal workYear;
	private BigDecimal monthIncome;
	private BigDecimal workerAccount;
	private BigDecimal employerAccount;
	private BigDecimal repaymentMonth;
	private BigDecimal repaymentThisTime;
	private BigDecimal applicationQuota;
	private BigDecimal loanTerm;
	private BigDecimal requirementCondition;
	private BigDecimal dataCondition;
	private String remark;

	// Constructors

	/** default constructor */
	public CopyOfWorkDraft() {
	}

	/** minimal constructor */
	public CopyOfWorkDraft(BigDecimal workPapeId) {
		this.workPapeId = workPapeId;
	}

	/** full constructor */
	public CopyOfWorkDraft(BigDecimal workPapeId, BigDecimal customerNum,
			BigDecimal genderCondition, BigDecimal workCondition,
			BigDecimal creditComdition, BigDecimal ageCondition,
			BigDecimal workYear, BigDecimal monthIncome,
			BigDecimal workerAccount, BigDecimal employerAccount,
			BigDecimal repaymentMonth, BigDecimal repaymentThisTime,
			BigDecimal applicationQuota, BigDecimal loanTerm,
			BigDecimal requirementCondition, BigDecimal dataCondition,
			String remark) {
		this.workPapeId = workPapeId;
		this.customerNum = customerNum;
		this.genderCondition = genderCondition;
		this.workCondition = workCondition;
		this.creditComdition = creditComdition;
		this.ageCondition = ageCondition;
		this.workYear = workYear;
		this.monthIncome = monthIncome;
		this.workerAccount = workerAccount;
		this.employerAccount = employerAccount;
		this.repaymentMonth = repaymentMonth;
		this.repaymentThisTime = repaymentThisTime;
		this.applicationQuota = applicationQuota;
		this.loanTerm = loanTerm;
		this.requirementCondition = requirementCondition;
		this.dataCondition = dataCondition;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@Column(name = "WORK_PAPE_ID", unique = true, nullable = false, precision = 20, scale = 0)
	public BigDecimal getWorkPapeId() {
		return this.workPapeId;
	}

	public void setWorkPapeId(BigDecimal workPapeId) {
		this.workPapeId = workPapeId;
	}

	@Column(name = "CUSTOMER_NUM", precision = 20, scale = 0)
	public BigDecimal getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(BigDecimal customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "GENDER_CONDITION", precision = 20, scale = 0)
	public BigDecimal getGenderCondition() {
		return this.genderCondition;
	}

	public void setGenderCondition(BigDecimal genderCondition) {
		this.genderCondition = genderCondition;
	}

	@Column(name = "WORK_CONDITION", precision = 20, scale = 0)
	public BigDecimal getWorkCondition() {
		return this.workCondition;
	}

	public void setWorkCondition(BigDecimal workCondition) {
		this.workCondition = workCondition;
	}

	@Column(name = "CREDIT_COMDITION", precision = 20, scale = 0)
	public BigDecimal getCreditComdition() {
		return this.creditComdition;
	}

	public void setCreditComdition(BigDecimal creditComdition) {
		this.creditComdition = creditComdition;
	}

	@Column(name = "AGE_CONDITION", precision = 20, scale = 0)
	public BigDecimal getAgeCondition() {
		return this.ageCondition;
	}

	public void setAgeCondition(BigDecimal ageCondition) {
		this.ageCondition = ageCondition;
	}

	@Column(name = "WORK_YEAR", precision = 20, scale = 0)
	public BigDecimal getWorkYear() {
		return this.workYear;
	}

	public void setWorkYear(BigDecimal workYear) {
		this.workYear = workYear;
	}

	@Column(name = "MONTH_INCOME", precision = 20, scale = 0)
	public BigDecimal getMonthIncome() {
		return this.monthIncome;
	}

	public void setMonthIncome(BigDecimal monthIncome) {
		this.monthIncome = monthIncome;
	}

	@Column(name = "WORKER_ACCOUNT", precision = 20, scale = 0)
	public BigDecimal getWorkerAccount() {
		return this.workerAccount;
	}

	public void setWorkerAccount(BigDecimal workerAccount) {
		this.workerAccount = workerAccount;
	}

	@Column(name = "EMPLOYER_ACCOUNT", precision = 20, scale = 0)
	public BigDecimal getEmployerAccount() {
		return this.employerAccount;
	}

	public void setEmployerAccount(BigDecimal employerAccount) {
		this.employerAccount = employerAccount;
	}

	@Column(name = "REPAYMENT_MONTH", precision = 20, scale = 0)
	public BigDecimal getRepaymentMonth() {
		return this.repaymentMonth;
	}

	public void setRepaymentMonth(BigDecimal repaymentMonth) {
		this.repaymentMonth = repaymentMonth;
	}

	@Column(name = "REPAYMENT_THIS_TIME", precision = 20, scale = 0)
	public BigDecimal getRepaymentThisTime() {
		return this.repaymentThisTime;
	}

	public void setRepaymentThisTime(BigDecimal repaymentThisTime) {
		this.repaymentThisTime = repaymentThisTime;
	}

	@Column(name = "APPLICATION_QUOTA", precision = 20, scale = 0)
	public BigDecimal getApplicationQuota() {
		return this.applicationQuota;
	}

	public void setApplicationQuota(BigDecimal applicationQuota) {
		this.applicationQuota = applicationQuota;
	}

	@Column(name = "LOAN_TERM", precision = 20, scale = 0)
	public BigDecimal getLoanTerm() {
		return this.loanTerm;
	}

	public void setLoanTerm(BigDecimal loanTerm) {
		this.loanTerm = loanTerm;
	}

	@Column(name = "REQUIREMENT_CONDITION", precision = 20, scale = 0)
	public BigDecimal getRequirementCondition() {
		return this.requirementCondition;
	}

	public void setRequirementCondition(BigDecimal requirementCondition) {
		this.requirementCondition = requirementCondition;
	}

	@Column(name = "DATA_CONDITION", precision = 20, scale = 0)
	public BigDecimal getDataCondition() {
		return this.dataCondition;
	}

	public void setDataCondition(BigDecimal dataCondition) {
		this.dataCondition = dataCondition;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
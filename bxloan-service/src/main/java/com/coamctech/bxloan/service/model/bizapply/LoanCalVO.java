package com.coamctech.bxloan.service.model.bizapply;

import java.math.BigDecimal;

public class LoanCalVO {
	private BigDecimal loanAmount;
	private Integer applyTerm;
	private String repayment;
	private BigDecimal rate;
	private Integer repaymentNumber;
	private String loanStartDate;
	private Integer repaymentDate;
	private String applyTermUnit;// 期限单位
	private Long projectId;

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getApplyTerm() {
		return applyTerm;
	}

	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}

	public String getRepayment() {
		return repayment;
	}

	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getRepaymentNumber() {
		return repaymentNumber;
	}

	public void setRepaymentNumber(Integer repaymentNumber) {
		this.repaymentNumber = repaymentNumber;
	}

	public String getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(String loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public Integer getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(Integer repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getApplyTermUnit() {
		return applyTermUnit;
	}

	public void setApplyTermUnit(String applyTermUnit) {
		this.applyTermUnit = applyTermUnit;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}

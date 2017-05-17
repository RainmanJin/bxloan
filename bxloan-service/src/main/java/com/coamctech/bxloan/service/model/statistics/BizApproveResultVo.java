package com.coamctech.bxloan.service.model.statistics;

import java.io.Serializable;
import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class BizApproveResultVo implements Serializable{
	
	private Long projectId;
	private BigDecimal loanAmt;
	private BigDecimal yearIrRate;
	private Integer term;
	private Integer termUnit;
	private String repaymentMode;
	
	
	public BizApproveResultVo(Object[] objs) {
		super();
		int i=0;
		this.projectId=CommonHelper.toLong(objs[i++]);
		this.loanAmt=CommonHelper.toBigDecimal(objs[i++]);
		this.repaymentMode=CommonHelper.toStr(objs[i++]);
		this.term=CommonHelper.toInt(objs[i++]);
		this.termUnit=CommonHelper.toInt(objs[i++]);
		this.yearIrRate=CommonHelper.toBigDecimal(objs[i++]);
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public BigDecimal getLoanAmt() {
		return loanAmt;
	}
	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}
	public BigDecimal getYearIrRate() {
		return yearIrRate;
	}
	public void setYearIrRate(BigDecimal yearIrRate) {
		this.yearIrRate = yearIrRate;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public Integer getTermUnit() {
		return termUnit;
	}
	public void setTermUnit(Integer termUnit) {
		this.termUnit = termUnit;
	}
	public String getRepaymentMode() {
		return repaymentMode;
	}
	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}
}

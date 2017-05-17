package com.coamctech.bxloan.service.model.bizapply;

import java.math.BigDecimal;

public class IndustryBizIncomeCostAgrVO {
	private Long id;
	private Long projectId;
	private String type;
	private String businessContent;
	private String businessStartDate;
	private String businessPlace;
	private String futurePastType;
	private BigDecimal month_slack;
	private BigDecimal month_peak;
	private BigDecimal monthlyIncome_slack;
	private BigDecimal monthlyIncome_peak;
	private BigDecimal yearIncomeTotal;
	private BigDecimal yearChangeableCostTotal;
	//and by HWL start 调查时库存
	private BigDecimal stockWhileSurveying;
	//and by HWL end 调查时库存
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusinessContent() {
		return businessContent;
	}

	public void setBusinessContent(String businessContent) {
		this.businessContent = businessContent;
	}

	public String getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(String businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public String getBusinessPlace() {
		return businessPlace;
	}

	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}

	public String getFuturePastType() {
		return futurePastType;
	}

	public void setFuturePastType(String futurePastType) {
		this.futurePastType = futurePastType;
	}

	public BigDecimal getMonth_slack() {
		return month_slack;
	}

	public void setMonth_slack(BigDecimal month_slack) {
		this.month_slack = month_slack;
	}

	public BigDecimal getMonth_peak() {
		return month_peak;
	}

	public void setMonth_peak(BigDecimal month_peak) {
		this.month_peak = month_peak;
	}

	public BigDecimal getMonthlyIncome_slack() {
		return monthlyIncome_slack;
	}

	public void setMonthlyIncome_slack(BigDecimal monthlyIncome_slack) {
		this.monthlyIncome_slack = monthlyIncome_slack;
	}

	public BigDecimal getMonthlyIncome_peak() {
		return monthlyIncome_peak;
	}

	public void setMonthlyIncome_peak(BigDecimal monthlyIncome_peak) {
		this.monthlyIncome_peak = monthlyIncome_peak;
	}

	public BigDecimal getYearIncomeTotal() {
		return yearIncomeTotal;
	}

	public void setYearIncomeTotal(BigDecimal yearIncomeTotal) {
		this.yearIncomeTotal = yearIncomeTotal;
	}

	public BigDecimal getYearChangeableCostTotal() {
		return yearChangeableCostTotal;
	}

	public void setYearChangeableCostTotal(BigDecimal yearChangeableCostTotal) {
		this.yearChangeableCostTotal = yearChangeableCostTotal;
	}

	public BigDecimal getStockWhileSurveying() {
		return stockWhileSurveying;
	}

	public void setStockWhileSurveying(BigDecimal stockWhileSurveying) {
		this.stockWhileSurveying = stockWhileSurveying;
	}
}

package com.coamctech.bxloan.service.model.bizapply;

import java.math.BigDecimal;

public class IndustryBizIncomeCostVO {
	private Long id;
	private Long projectId;
	private String type;
	private String businessStartDate;
	private BigDecimal initialCapital;
	private String businessScope;
	private String scaleArea;
	private String settlementMode;
	private String pool;
	private BigDecimal customerPercentage;
	private String scaleWorker;
	private String settlementPeriod;
	private String scaleOther;
	private BigDecimal month_slack;
	private BigDecimal month_peak;
	private BigDecimal dailyIncome_slack;
	private BigDecimal dailyIncome_peak;
	private BigDecimal dailyChangeableCost_slack;
	private BigDecimal dailyChangeableCost_peak;
	private BigDecimal dailyGain_slack;
	private BigDecimal dailyGain_peak;
	private BigDecimal businessDay_slack;
	private BigDecimal businessDay_peak;
	private BigDecimal monthlyIncome_slack;
	private BigDecimal monthlyIncome_peak;
	private BigDecimal monthlyChangeableCost_slack;
	private BigDecimal monthlyChangeableCost_peak;
	private BigDecimal yearIncomeTotal;
	private BigDecimal yearChangeableCostTotal;
	private BigDecimal yearGainTotal;
	private BigDecimal customerDictateGainRatio;
	private String changeableRemarks;
	private BigDecimal salary;
	private BigDecimal rent;
	private BigDecimal hospitality;
	private BigDecimal tranffic;
	private BigDecimal waterElectric;
	private BigDecimal communication;
	private BigDecimal tax;
	private BigDecimal repair;
	private BigDecimal others1;
	private BigDecimal others2;
	private BigDecimal others3;
	private BigDecimal others4;
	private BigDecimal gainTotal;
	private BigDecimal costTotal;
	private String businessContent;
	private String businessPlace;
	private BigDecimal stockWhileSurveying;

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

	public String getBusinessStartDate() {
		return businessStartDate;
	}

	public void setBusinessStartDate(String businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	public BigDecimal getInitialCapital() {
		return initialCapital;
	}

	public void setInitialCapital(BigDecimal initialCapital) {
		this.initialCapital = initialCapital;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getScaleArea() {
		return scaleArea;
	}

	public void setScaleArea(String scaleArea) {
		this.scaleArea = scaleArea;
	}

	public String getSettlementMode() {
		return settlementMode;
	}

	public void setSettlementMode(String settlementMode) {
		this.settlementMode = settlementMode;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	public BigDecimal getCustomerPercentage() {
		return customerPercentage;
	}

	public void setCustomerPercentage(BigDecimal customerPercentage) {
		this.customerPercentage = customerPercentage;
	}

	public String getScaleWorker() {
		return scaleWorker;
	}

	public void setScaleWorker(String scaleWorker) {
		this.scaleWorker = scaleWorker;
	}

	public String getSettlementPeriod() {
		return settlementPeriod;
	}

	public void setSettlementPeriod(String settlementPeriod) {
		this.settlementPeriod = settlementPeriod;
	}

	public String getScaleOther() {
		return scaleOther;
	}

	public void setScaleOther(String scaleOther) {
		this.scaleOther = scaleOther;
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

	public BigDecimal getDailyIncome_slack() {
		return dailyIncome_slack;
	}

	public void setDailyIncome_slack(BigDecimal dailyIncome_slack) {
		this.dailyIncome_slack = dailyIncome_slack;
	}

	public BigDecimal getDailyIncome_peak() {
		return dailyIncome_peak;
	}

	public void setDailyIncome_peak(BigDecimal dailyIncome_peak) {
		this.dailyIncome_peak = dailyIncome_peak;
	}

	public BigDecimal getDailyChangeableCost_slack() {
		return dailyChangeableCost_slack;
	}

	public void setDailyChangeableCost_slack(
			BigDecimal dailyChangeableCost_slack) {
		this.dailyChangeableCost_slack = dailyChangeableCost_slack;
	}

	public BigDecimal getDailyChangeableCost_peak() {
		return dailyChangeableCost_peak;
	}

	public void setDailyChangeableCost_peak(BigDecimal dailyChangeableCost_peak) {
		this.dailyChangeableCost_peak = dailyChangeableCost_peak;
	}

	public BigDecimal getDailyGain_slack() {
		return dailyGain_slack;
	}

	public void setDailyGain_slack(BigDecimal dailyGain_slack) {
		this.dailyGain_slack = dailyGain_slack;
	}

	public BigDecimal getDailyGain_peak() {
		return dailyGain_peak;
	}

	public void setDailyGain_peak(BigDecimal dailyGain_peak) {
		this.dailyGain_peak = dailyGain_peak;
	}

	public BigDecimal getBusinessDay_slack() {
		return businessDay_slack;
	}

	public void setBusinessDay_slack(BigDecimal businessDay_slack) {
		this.businessDay_slack = businessDay_slack;
	}

	public BigDecimal getBusinessDay_peak() {
		return businessDay_peak;
	}

	public void setBusinessDay_peak(BigDecimal businessDay_peak) {
		this.businessDay_peak = businessDay_peak;
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

	public BigDecimal getMonthlyChangeableCost_slack() {
		return monthlyChangeableCost_slack;
	}

	public void setMonthlyChangeableCost_slack(
			BigDecimal monthlyChangeableCost_slack) {
		this.monthlyChangeableCost_slack = monthlyChangeableCost_slack;
	}

	public BigDecimal getMonthlyChangeableCost_peak() {
		return monthlyChangeableCost_peak;
	}

	public void setMonthlyChangeableCost_peak(
			BigDecimal monthlyChangeableCost_peak) {
		this.monthlyChangeableCost_peak = monthlyChangeableCost_peak;
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

	public BigDecimal getYearGainTotal() {
		return yearGainTotal;
	}

	public void setYearGainTotal(BigDecimal yearGainTotal) {
		this.yearGainTotal = yearGainTotal;
	}

	public BigDecimal getCustomerDictateGainRatio() {
		return customerDictateGainRatio;
	}

	public void setCustomerDictateGainRatio(BigDecimal customerDictateGainRatio) {
		this.customerDictateGainRatio = customerDictateGainRatio;
	}

	public String getChangeableRemarks() {
		return changeableRemarks;
	}

	public void setChangeableRemarks(String changeableRemarks) {
		this.changeableRemarks = changeableRemarks;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public BigDecimal getHospitality() {
		return hospitality;
	}

	public void setHospitality(BigDecimal hospitality) {
		this.hospitality = hospitality;
	}

	public BigDecimal getTranffic() {
		return tranffic;
	}

	public void setTranffic(BigDecimal tranffic) {
		this.tranffic = tranffic;
	}

	public BigDecimal getWaterElectric() {
		return waterElectric;
	}

	public void setWaterElectric(BigDecimal waterElectric) {
		this.waterElectric = waterElectric;
	}

	public BigDecimal getCommunication() {
		return communication;
	}

	public void setCommunication(BigDecimal communication) {
		this.communication = communication;
	}

	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getRepair() {
		return repair;
	}

	public void setRepair(BigDecimal repair) {
		this.repair = repair;
	}

	public BigDecimal getOthers1() {
		return others1;
	}

	public void setOthers1(BigDecimal others1) {
		this.others1 = others1;
	}

	public BigDecimal getOthers2() {
		return others2;
	}

	public void setOthers2(BigDecimal others2) {
		this.others2 = others2;
	}

	public BigDecimal getOthers3() {
		return others3;
	}

	public void setOthers3(BigDecimal others3) {
		this.others3 = others3;
	}

	public BigDecimal getOthers4() {
		return others4;
	}

	public void setOthers4(BigDecimal others4) {
		this.others4 = others4;
	}

	public BigDecimal getGainTotal() {
		return gainTotal;
	}

	public void setGainTotal(BigDecimal gainTotal) {
		this.gainTotal = gainTotal;
	}

	public BigDecimal getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	public String getBusinessContent() {
		return businessContent;
	}

	public void setBusinessContent(String businessContent) {
		this.businessContent = businessContent;
	}

	public String getBusinessPlace() {
		return businessPlace;
	}

	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}

	public BigDecimal getStockWhileSurveying() {
		return stockWhileSurveying;
	}

	public void setStockWhileSurveying(BigDecimal stockWhileSurveying) {
		this.stockWhileSurveying = stockWhileSurveying;
	}
}

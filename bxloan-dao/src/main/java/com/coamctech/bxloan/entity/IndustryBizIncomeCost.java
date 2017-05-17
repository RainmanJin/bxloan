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

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * IndustryBizIncomeCost entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INDUSTRY_BIZ_INCOME_COST", schema = GlobalConstants.WD_SCHEMA)
public class IndustryBizIncomeCost implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private String type;
	private Date businessStartDate;
	private BigDecimal initialCapital;
	private String businessScope;
	private String scaleArea;
	private String settlementMode;
	private String pool;
	private BigDecimal customerPercentage;
	private String scaleWorker;
	private String settlementPeriod;
	private String scaleOther;
	private String month;
	private String dailyIncome;
	private String dailyChangeableCost;
	private String dailyGain;
	private String businessDay;
	private String monthlyIncome;
	private String monthlyChangeableCost;
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
	private String futurePastType;
	private BigDecimal stockWhileSurveying;

	// Constructors

	/** default constructor */
	public IndustryBizIncomeCost() {
	}

	/** minimal constructor */
	public IndustryBizIncomeCost(Long id) {
		this.id = id;
	}

	/** full constructor */
	public IndustryBizIncomeCost(Long id, Long projectId, String type,
			Date businessStartDate, BigDecimal initialCapital,
			String businessScope, String scaleArea, String settlementMode,
			String pool, BigDecimal customerPercentage, String scaleWorker,
			String settlementPeriod, String scaleOther, String month,
			String dailyIncome, String dailyChangeableCost, String dailyGain,
			String businessDay, String monthlyIncome,
			String monthlyChangeableCost, BigDecimal yearIncomeTotal,
			BigDecimal yearChangeableCostTotal, BigDecimal yearGainTotal,
			BigDecimal customerDictateGainRatio, String changeableRemarks,
			BigDecimal salary, BigDecimal rent, BigDecimal hospitality,
			BigDecimal tranffic, BigDecimal waterElectric,
			BigDecimal communication, BigDecimal tax, BigDecimal repair,
			BigDecimal others1, BigDecimal others2, BigDecimal others3,
			BigDecimal others4, BigDecimal gainTotal, BigDecimal costTotal,
			String businessContent, String businessPlace, String futurePastType) {
		this.id = id;
		this.projectId = projectId;
		this.type = type;
		this.businessStartDate = businessStartDate;
		this.initialCapital = initialCapital;
		this.businessScope = businessScope;
		this.scaleArea = scaleArea;
		this.settlementMode = settlementMode;
		this.pool = pool;
		this.customerPercentage = customerPercentage;
		this.scaleWorker = scaleWorker;
		this.settlementPeriod = settlementPeriod;
		this.scaleOther = scaleOther;
		this.month = month;
		this.dailyIncome = dailyIncome;
		this.dailyChangeableCost = dailyChangeableCost;
		this.dailyGain = dailyGain;
		this.businessDay = businessDay;
		this.monthlyIncome = monthlyIncome;
		this.monthlyChangeableCost = monthlyChangeableCost;
		this.yearIncomeTotal = yearIncomeTotal;
		this.yearChangeableCostTotal = yearChangeableCostTotal;
		this.yearGainTotal = yearGainTotal;
		this.customerDictateGainRatio = customerDictateGainRatio;
		this.changeableRemarks = changeableRemarks;
		this.salary = salary;
		this.rent = rent;
		this.hospitality = hospitality;
		this.tranffic = tranffic;
		this.waterElectric = waterElectric;
		this.communication = communication;
		this.tax = tax;
		this.repair = repair;
		this.others1 = others1;
		this.others2 = others2;
		this.others3 = others3;
		this.others4 = others4;
		this.gainTotal = gainTotal;
		this.costTotal = costTotal;
		this.businessContent = businessContent;
		this.businessPlace = businessPlace;
		this.futurePastType = futurePastType;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_INDUSTRY_BIZ_INCOME_COST", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_ID", precision = 22, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "TYPE", length = 20)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "BUSINESS_START_DATE", length = 7)
	public Date getBusinessStartDate() {
		return this.businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	@Column(name = "INITIAL_CAPITAL", precision = 22, scale = 0)
	public BigDecimal getInitialCapital() {
		return this.initialCapital;
	}

	public void setInitialCapital(BigDecimal initialCapital) {
		this.initialCapital = initialCapital;
	}

	@Column(name = "BUSINESS_SCOPE", length = 20)
	public String getBusinessScope() {
		return this.businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	@Column(name = "SCALE_AREA", length = 20)
	public String getScaleArea() {
		return this.scaleArea;
	}

	public void setScaleArea(String scaleArea) {
		this.scaleArea = scaleArea;
	}

	@Column(name = "SETTLEMENT_MODE", length = 20)
	public String getSettlementMode() {
		return this.settlementMode;
	}

	public void setSettlementMode(String settlementMode) {
		this.settlementMode = settlementMode;
	}

	@Column(name = "POOL", length = 20)
	public String getPool() {
		return this.pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	@Column(name = "CUSTOMER_PERCENTAGE", precision = 22, scale = 0)
	public BigDecimal getCustomerPercentage() {
		return this.customerPercentage;
	}

	public void setCustomerPercentage(BigDecimal customerPercentage) {
		this.customerPercentage = customerPercentage;
	}

	@Column(name = "SCALE_WORKER", length = 20)
	public String getScaleWorker() {
		return this.scaleWorker;
	}

	public void setScaleWorker(String scaleWorker) {
		this.scaleWorker = scaleWorker;
	}

	@Column(name = "SETTLEMENT_PERIOD", length = 20)
	public String getSettlementPeriod() {
		return this.settlementPeriod;
	}

	public void setSettlementPeriod(String settlementPeriod) {
		this.settlementPeriod = settlementPeriod;
	}

	@Column(name = "SCALE_OTHER", length = 20)
	public String getScaleOther() {
		return this.scaleOther;
	}

	public void setScaleOther(String scaleOther) {
		this.scaleOther = scaleOther;
	}

	@Column(name = "MONTH", precision = 22, scale = 0)
	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Column(name = "DAILY_INCOME", precision = 22, scale = 0)
	public String getDailyIncome() {
		return this.dailyIncome;
	}

	public void setDailyIncome(String dailyIncome) {
		this.dailyIncome = dailyIncome;
	}

	@Column(name = "DAILY_CHANGEABLE_COST", precision = 22, scale = 0)
	public String getDailyChangeableCost() {
		return this.dailyChangeableCost;
	}

	public void setDailyChangeableCost(String dailyChangeableCost) {
		this.dailyChangeableCost = dailyChangeableCost;
	}

	@Column(name = "DAILY_GAIN", precision = 22, scale = 0)
	public String getDailyGain() {
		return this.dailyGain;
	}

	public void setDailyGain(String dailyGain) {
		this.dailyGain = dailyGain;
	}

	@Column(name = "BUSINESS_DAY", precision = 22, scale = 0)
	public String getBusinessDay() {
		return this.businessDay;
	}

	public void setBusinessDay(String businessDay) {
		this.businessDay = businessDay;
	}

	@Column(name = "MONTHLY_INCOME", precision = 22, scale = 0)
	public String getMonthlyIncome() {
		return this.monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	@Column(name = "MONTHLY_CHANGEABLE_COST", precision = 22, scale = 0)
	public String getMonthlyChangeableCost() {
		return this.monthlyChangeableCost;
	}

	public void setMonthlyChangeableCost(String monthlyChangeableCost) {
		this.monthlyChangeableCost = monthlyChangeableCost;
	}

	@Column(name = "YEAR_INCOME_TOTAL", precision = 22, scale = 0)
	public BigDecimal getYearIncomeTotal() {
		return this.yearIncomeTotal;
	}

	public void setYearIncomeTotal(BigDecimal yearIncomeTotal) {
		this.yearIncomeTotal = yearIncomeTotal;
	}

	@Column(name = "YEAR_CHANGEABLE_COST_TOTAL", precision = 22, scale = 0)
	public BigDecimal getYearChangeableCostTotal() {
		return this.yearChangeableCostTotal;
	}

	public void setYearChangeableCostTotal(BigDecimal yearChangeableCostTotal) {
		this.yearChangeableCostTotal = yearChangeableCostTotal;
	}

	@Column(name = "YEAR_GAIN_TOTAL", precision = 22, scale = 0)
	public BigDecimal getYearGainTotal() {
		return this.yearGainTotal;
	}

	public void setYearGainTotal(BigDecimal yearGainTotal) {
		this.yearGainTotal = yearGainTotal;
	}

	@Column(name = "CUSTOMER_DICTATE_GAIN_RATIO", precision = 22, scale = 0)
	public BigDecimal getCustomerDictateGainRatio() {
		return this.customerDictateGainRatio;
	}

	public void setCustomerDictateGainRatio(BigDecimal customerDictateGainRatio) {
		this.customerDictateGainRatio = customerDictateGainRatio;
	}

	@Column(name = "CHANGEABLE_REMARKS", length = 20)
	public String getChangeableRemarks() {
		return this.changeableRemarks;
	}

	public void setChangeableRemarks(String changeableRemarks) {
		this.changeableRemarks = changeableRemarks;
	}

	@Column(name = "SALARY", precision = 22, scale = 0)
	public BigDecimal getSalary() {
		return this.salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	@Column(name = "RENT", precision = 22, scale = 0)
	public BigDecimal getRent() {
		return this.rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	@Column(name = "HOSPITALITY", precision = 22, scale = 0)
	public BigDecimal getHospitality() {
		return this.hospitality;
	}

	public void setHospitality(BigDecimal hospitality) {
		this.hospitality = hospitality;
	}

	@Column(name = "TRANFFIC", precision = 22, scale = 0)
	public BigDecimal getTranffic() {
		return this.tranffic;
	}

	public void setTranffic(BigDecimal tranffic) {
		this.tranffic = tranffic;
	}

	@Column(name = "WATER_ELECTRIC", precision = 22, scale = 0)
	public BigDecimal getWaterElectric() {
		return this.waterElectric;
	}

	public void setWaterElectric(BigDecimal waterElectric) {
		this.waterElectric = waterElectric;
	}

	@Column(name = "COMMUNICATION", precision = 22, scale = 0)
	public BigDecimal getCommunication() {
		return this.communication;
	}

	public void setCommunication(BigDecimal communication) {
		this.communication = communication;
	}

	@Column(name = "TAX", precision = 22, scale = 0)
	public BigDecimal getTax() {
		return this.tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	@Column(name = "REPAIR", precision = 22, scale = 0)
	public BigDecimal getRepair() {
		return this.repair;
	}

	public void setRepair(BigDecimal repair) {
		this.repair = repair;
	}

	@Column(name = "OTHERS1", precision = 22, scale = 0)
	public BigDecimal getOthers1() {
		return this.others1;
	}

	public void setOthers1(BigDecimal others1) {
		this.others1 = others1;
	}

	@Column(name = "OTHERS2", precision = 22, scale = 0)
	public BigDecimal getOthers2() {
		return this.others2;
	}

	public void setOthers2(BigDecimal others2) {
		this.others2 = others2;
	}

	@Column(name = "OTHERS3", precision = 22, scale = 0)
	public BigDecimal getOthers3() {
		return this.others3;
	}

	public void setOthers3(BigDecimal others3) {
		this.others3 = others3;
	}

	@Column(name = "OTHERS4", precision = 22, scale = 0)
	public BigDecimal getOthers4() {
		return this.others4;
	}

	public void setOthers4(BigDecimal others4) {
		this.others4 = others4;
	}

	@Column(name = "GAIN_TOTAL", precision = 22, scale = 0)
	public BigDecimal getGainTotal() {
		return this.gainTotal;
	}

	public void setGainTotal(BigDecimal gainTotal) {
		this.gainTotal = gainTotal;
	}

	@Column(name = "COST_TOTAL", precision = 22, scale = 0)
	public BigDecimal getCostTotal() {
		return this.costTotal;
	}

	public void setCostTotal(BigDecimal costTotal) {
		this.costTotal = costTotal;
	}

	@Column(name = "BUSINESS_CONTENT", precision = 22, scale = 0)
	public String getBusinessContent() {
		return businessContent;
	}

	public void setBusinessContent(String businessContent) {
		this.businessContent = businessContent;
	}

	@Column(name = "BUSINESS_PLACE", precision = 22, scale = 0)
	public String getBusinessPlace() {
		return businessPlace;
	}

	public void setBusinessPlace(String businessPlace) {
		this.businessPlace = businessPlace;
	}

	@Column(name = "FUTURE_PAST_TYPE", precision = 22, scale = 0)
	public String getFuturePastType() {
		return futurePastType;
	}

	public void setFuturePastType(String futurePastType) {
		this.futurePastType = futurePastType;
	}

	@Column(name = "STOCK_WHILE_SURVEYING", precision = 12, scale = 2)
	public BigDecimal getStockWhileSurveying() {
		return stockWhileSurveying;
	}

	public void setStockWhileSurveying(BigDecimal stockWhileSurveying) {
		this.stockWhileSurveying = stockWhileSurveying;
	}

}
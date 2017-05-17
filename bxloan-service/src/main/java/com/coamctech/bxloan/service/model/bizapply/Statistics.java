package com.coamctech.bxloan.service.model.bizapply;

import java.math.BigDecimal;

import com.coamctech.bxloan.service.bizapply.StatisticsService;

public class Statistics {
	private String     typeValue;               	// 类型值
	private String     typeName;                	// 类型名称
	private StatisticsService.IncomeType incomeType;// 类型
	
	private BigDecimal incomeAmount;            	// 收入金额
	private BigDecimal incomePercent;           	// 收入占比
	private BigDecimal costAmount;              	// 成本金额
	private BigDecimal costPercent;             	// 成本占比
	private BigDecimal gainAmount;              	// 利润金额
	private BigDecimal gainPercent;             	// 利润占比
	private BigDecimal stock;                   	// 存货
	
	
	private BigDecimal pastIncomeAmount;        	// 过去收入金额
	private BigDecimal pastIncomePercent;       	// 过去收入占比
	private BigDecimal predictableIncomeAmount; 	// 预测收入金额
	private BigDecimal predictableIncomePercent;	// 预测收入占比
	private BigDecimal predictableGainAmount;   	// 预测利润金额
	private BigDecimal predictableGainPercent;  	// 预测利润占比
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public StatisticsService.IncomeType getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(StatisticsService.IncomeType incomeType) {
		this.incomeType = incomeType;
	}
	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}
	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	public BigDecimal getIncomePercent() {
		return incomePercent;
	}
	public void setIncomePercent(BigDecimal incomePercent) {
		this.incomePercent = incomePercent;
	}
	public BigDecimal getCostAmount() {
		return costAmount;
	}
	public void setCostAmount(BigDecimal costAmount) {
		this.costAmount = costAmount;
	}
	public BigDecimal getCostPercent() {
		return costPercent;
	}
	public void setCostPercent(BigDecimal costPercent) {
		this.costPercent = costPercent;
	}
	public BigDecimal getGainAmount() {
		return gainAmount;
	}
	public void setGainAmount(BigDecimal gainAmount) {
		this.gainAmount = gainAmount;
	}
	public BigDecimal getGainPercent() {
		return gainPercent;
	}
	public void setGainPercent(BigDecimal gainPercent) {
		this.gainPercent = gainPercent;
	}
	public BigDecimal getStock() {
		return stock;
	}
	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}
	public BigDecimal getPastIncomeAmount() {
		return pastIncomeAmount;
	}
	public void setPastIncomeAmount(BigDecimal pastIncomeAmount) {
		this.pastIncomeAmount = pastIncomeAmount;
	}
	public BigDecimal getPastIncomePercent() {
		return pastIncomePercent;
	}
	public void setPastIncomePercent(BigDecimal pastIncomePercent) {
		this.pastIncomePercent = pastIncomePercent;
	}
	public BigDecimal getPredictableIncomeAmount() {
		return predictableIncomeAmount;
	}
	public void setPredictableIncomeAmount(BigDecimal predictableIncomeAmount) {
		this.predictableIncomeAmount = predictableIncomeAmount;
	}
	public BigDecimal getPredictableIncomePercent() {
		return predictableIncomePercent;
	}
	public void setPredictableIncomePercent(BigDecimal predictableIncomePercent) {
		this.predictableIncomePercent = predictableIncomePercent;
	}
	public BigDecimal getPredictableGainAmount() {
		return predictableGainAmount;
	}
	public void setPredictableGainAmount(BigDecimal predictableGainAmount) {
		this.predictableGainAmount = predictableGainAmount;
	}
	public BigDecimal getPredictableGainPercent() {
		return predictableGainPercent;
	}
	public void setPredictableGainPercent(BigDecimal predictableGainPercent) {
		this.predictableGainPercent = predictableGainPercent;
	}
}

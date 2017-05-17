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
 * Transport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRANSPORT", schema = GlobalConstants.WD_SCHEMA)
public class Transport implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private Date businessStartDate;
	private String profitMode;
	private Long carsNum;
	private String transportContent;
	private String carsType;
	private BigDecimal initialCapital;
	private String carsInsurance;
	private String settlementMode;
	private String route;
	private String pool;
	private BigDecimal customerPercentage;
	private String settlementPeriod;

	// Constructors

	/** default constructor */
	public Transport() {
	}

	/** minimal constructor */
	public Transport(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Transport(Long id, Long projectId,
			Date businessStartDate, String profitMode, Long carsNum,
			String transportContent, String carsType,
			BigDecimal initialCapital, String carsInsurance,
			String settlementMode, String route, String pool,
			BigDecimal customerPercentage, String settlementPeriod) {
		this.id = id;
		this.projectId = projectId;
		this.businessStartDate = businessStartDate;
		this.profitMode = profitMode;
		this.carsNum = carsNum;
		this.transportContent = transportContent;
		this.carsType = carsType;
		this.initialCapital = initialCapital;
		this.carsInsurance = carsInsurance;
		this.settlementMode = settlementMode;
		this.route = route;
		this.pool = pool;
		this.customerPercentage = customerPercentage;
		this.settlementPeriod = settlementPeriod;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_TRANSPORT", allocationSize = 1)
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

	@Column(name = "BUSINESS_START_DATE", length = 7)
	public Date getBusinessStartDate() {
		return this.businessStartDate;
	}

	public void setBusinessStartDate(Date businessStartDate) {
		this.businessStartDate = businessStartDate;
	}

	@Column(name = "PROFIT_MODE", length = 20)
	public String getProfitMode() {
		return this.profitMode;
	}

	public void setProfitMode(String profitMode) {
		this.profitMode = profitMode;
	}

	@Column(name = "CARS_NUM", precision = 22, scale = 0)
	public Long getCarsNum() {
		return this.carsNum;
	}

	public void setCarsNum(Long carsNum) {
		this.carsNum = carsNum;
	}

	@Column(name = "TRANSPORT_CONTENT", length = 20)
	public String getTransportContent() {
		return this.transportContent;
	}

	public void setTransportContent(String transportContent) {
		this.transportContent = transportContent;
	}

	@Column(name = "CARS_TYPE", length = 20)
	public String getCarsType() {
		return this.carsType;
	}

	public void setCarsType(String carsType) {
		this.carsType = carsType;
	}

	@Column(name = "INITIAL_CAPITAL", precision = 22, scale = 0)
	public BigDecimal getInitialCapital() {
		return this.initialCapital;
	}

	public void setInitialCapital(BigDecimal initialCapital) {
		this.initialCapital = initialCapital;
	}

	@Column(name = "CARS_INSURANCE", length = 20)
	public String getCarsInsurance() {
		return this.carsInsurance;
	}

	public void setCarsInsurance(String carsInsurance) {
		this.carsInsurance = carsInsurance;
	}

	@Column(name = "SETTLEMENT_MODE", length = 20)
	public String getSettlementMode() {
		return this.settlementMode;
	}

	public void setSettlementMode(String settlementMode) {
		this.settlementMode = settlementMode;
	}

	@Column(name = "ROUTE", length = 20)
	public String getRoute() {
		return this.route;
	}

	public void setRoute(String route) {
		this.route = route;
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

	@Column(name = "SETTLEMENT_PERIOD", length = 20)
	public String getSettlementPeriod() {
		return this.settlementPeriod;
	}

	public void setSettlementPeriod(String settlementPeriod) {
		this.settlementPeriod = settlementPeriod;
	}

}
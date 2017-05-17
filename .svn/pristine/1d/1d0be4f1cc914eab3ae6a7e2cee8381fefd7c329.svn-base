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
 * CultivateNonAgriculture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CULTIVATE_NON_AGRICULTURE", schema = GlobalConstants.WD_SCHEMA)
public class CultivateNonAgriculture implements java.io.Serializable {
	// Fields
	private Long id;
	private Long projectId;
	private String cultivateContent;
	private BigDecimal cultivateArea;
	private BigDecimal income;
	private BigDecimal cost;
	private BigDecimal cropValue;
	private BigDecimal agricultureCapitalValue;
	private BigDecimal workingYears;
	private BigDecimal initialCapital;
	private String cultivateType;
	// Constructors
	/** default constructor */
	public CultivateNonAgriculture() {
	}

	/** minimal constructor */
	public CultivateNonAgriculture(Long id) {
		this.id = id;
	}

	/** full constructor */
	public CultivateNonAgriculture(Long id, Long projectId,
			String cultivateContent, BigDecimal cultivateArea,
			BigDecimal income, BigDecimal cost, BigDecimal cropValue,
			BigDecimal agricultureCapitalValue, BigDecimal workingYears,
			BigDecimal initialCapital) {
		this.id = id;
		this.projectId = projectId;
		this.cultivateContent = cultivateContent;
		this.cultivateArea = cultivateArea;
		this.income = income;
		this.cost = cost;
		this.cropValue = cropValue;
		this.agricultureCapitalValue = agricultureCapitalValue;
		this.workingYears = workingYears;
		this.initialCapital = initialCapital;
	}

	@SequenceGenerator(name = "generator", sequenceName = "SEQ_CULTIVATE_NON_AGRICULTURE", allocationSize = 1)
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

	@Column(name = "CULTIVATE_CONTENT", length = 20)
	public String getCultivateContent() {
		return this.cultivateContent;
	}

	public void setCultivateContent(String cultivateContent) {
		this.cultivateContent = cultivateContent;
	}

	@Column(name = "CULTIVATE_AREA", precision = 22, scale = 0)
	public BigDecimal getCultivateArea() {
		return this.cultivateArea;
	}

	public void setCultivateArea(BigDecimal cultivateArea) {
		this.cultivateArea = cultivateArea;
	}

	@Column(name = "INCOME", precision = 22, scale = 0)
	public BigDecimal getIncome() {
		return this.income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	@Column(name = "COST", precision = 22, scale = 0)
	public BigDecimal getCost() {
		return this.cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Column(name = "CROP_VALUE", precision = 22, scale = 0)
	public BigDecimal getCropValue() {
		return this.cropValue;
	}

	public void setCropValue(BigDecimal cropValue) {
		this.cropValue = cropValue;
	}

	@Column(name = "AGRICULTURE_CAPITAL_VALUE", length = 18)
	public BigDecimal getAgricultureCapitalValue() {
		return this.agricultureCapitalValue;
	}

	public void setAgricultureCapitalValue(BigDecimal agricultureCapitalValue) {
		this.agricultureCapitalValue = agricultureCapitalValue;
	}

	@Column(name = "WORKING_YEARS", precision = 22, scale = 0)
	public BigDecimal getWorkingYears() {
		return this.workingYears;
	}

	public void setWorkingYears(BigDecimal workingYears) {
		this.workingYears = workingYears;
	}

	@Column(name = "INITIAL_CAPITAL", precision = 22, scale = 0)
	public BigDecimal getInitialCapital() {
		return this.initialCapital;
	}

	public void setInitialCapital(BigDecimal initialCapital) {
		this.initialCapital = initialCapital;
	}
	@Column(name = "CULTIVATE_TYPE", length = 10)
	public String getCultivateType() {
		return cultivateType;
	}

	public void setCultivateType(String cultivateType) {
		this.cultivateType = cultivateType;
	}
	
}

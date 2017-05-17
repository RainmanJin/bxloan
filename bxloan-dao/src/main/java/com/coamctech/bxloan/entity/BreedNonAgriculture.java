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
 * BreedNonAgriculture entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BREED_NON_AGRICULTURE", schema = GlobalConstants.WD_SCHEMA)
public class BreedNonAgriculture implements java.io.Serializable {
	// Fields
	private Long id;
	private Long projectId;
	private String breedContent;
	private String breedMode;
	private String breedScale;
	private BigDecimal income;
	private BigDecimal cost;
	private String investigateBreedScale;
	private BigDecimal breedStockValue;
	private BigDecimal forageValue;
	private BigDecimal workingYears;
	private BigDecimal initialCapital;
	private String breedType;

	// Constructors
	/** default constructor */
	public BreedNonAgriculture() {
	}

	/** minimal constructor */
	public BreedNonAgriculture(Long id) {
		this.id = id;
	}

	/** full constructor */
	public BreedNonAgriculture(Long id, Long projectId,
			String breedContent, String breedMode, String breedScale,
			BigDecimal income, BigDecimal cost, String investigateBreedScale,
			BigDecimal breedStockValue, BigDecimal forageValue,
			BigDecimal workingYears, BigDecimal initialCapital) {
		this.id = id;
		this.projectId = projectId;
		this.breedContent = breedContent;
		this.breedMode = breedMode;
		this.breedScale = breedScale;
		this.income = income;
		this.cost = cost;
		this.investigateBreedScale = investigateBreedScale;
		this.breedStockValue = breedStockValue;
		this.forageValue = forageValue;
		this.workingYears = workingYears;
		this.initialCapital = initialCapital;
	}

	@SequenceGenerator(name = "generator", sequenceName = "SEQ_BREED_NON_AGRICULTURE", allocationSize = 1)
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

	@Column(name = "BREED_CONTENT", length = 20)
	public String getBreedContent() {
		return this.breedContent;
	}

	public void setBreedContent(String breedContent) {
		this.breedContent = breedContent;
	}

	@Column(name = "BREED_MODE", length = 20)
	public String getBreedMode() {
		return this.breedMode;
	}

	public void setBreedMode(String breedMode) {
		this.breedMode = breedMode;
	}

	@Column(name = "BREED_SCALE", length = 20)
	public String getBreedScale() {
		return this.breedScale;
	}

	public void setBreedScale(String breedScale) {
		this.breedScale = breedScale;
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

	@Column(name = "INVESTIGATE_BREED_SCALE", length = 20)
	public String getInvestigateBreedScale() {
		return this.investigateBreedScale;
	}

	public void setInvestigateBreedScale(String investigateBreedScale) {
		this.investigateBreedScale = investigateBreedScale;
	}

	@Column(name = "BREED_STOCK_VALUE", precision = 22, scale = 0)
	public BigDecimal getBreedStockValue() {
		return this.breedStockValue;
	}

	public void setBreedStockValue(BigDecimal breedStockValue) {
		this.breedStockValue = breedStockValue;
	}

	@Column(name = "FORAGE_VALUE", precision = 22, scale = 0)
	public BigDecimal getForageValue() {
		return this.forageValue;
	}

	public void setForageValue(BigDecimal forageValue) {
		this.forageValue = forageValue;
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
	@Column(name = "BREED_TYPE", length = 10)
	public String getBreedType() {
		return breedType;
	}

	public void setBreedType(String breedType) {
		this.breedType = breedType;
	}
	
}

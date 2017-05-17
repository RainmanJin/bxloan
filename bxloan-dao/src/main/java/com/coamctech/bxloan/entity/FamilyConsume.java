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
 * FamilyConsume entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FAMILY_CONSUME", schema = GlobalConstants.WD_SCHEMA)
public class FamilyConsume implements java.io.Serializable {

	// Fields

	private Long id;
	private Long projectId;
	private String type;
	private BigDecimal lifeConsume;
	private BigDecimal tuition;
	private BigDecimal medical;
	private BigDecimal insurance;
	private BigDecimal others1;
	private BigDecimal others2;
	private BigDecimal others3;
	private BigDecimal familyConsumeTotal;
	private BigDecimal interestCost;
	private BigDecimal repaymentCost;
	private String remarks;
	private String debtChangeExplain;

	// Constructors

	/** default constructor */
	public FamilyConsume() {
	}

	/** minimal constructor */
	public FamilyConsume(Long id) {
		this.id = id;
	}

	/** full constructor */
	public FamilyConsume(Long id, Long projectId, String type,
			BigDecimal lifeConsume, BigDecimal tuition, BigDecimal medical,
			BigDecimal insurance, BigDecimal others1, BigDecimal others2,
			BigDecimal others3, BigDecimal familyConsumeTotal,
			BigDecimal interestCost, BigDecimal repaymentCost, String remarks,
			String debtChangeExplain) {
		this.id = id;
		this.projectId = projectId;
		this.type = type;
		this.lifeConsume = lifeConsume;
		this.tuition = tuition;
		this.medical = medical;
		this.insurance = insurance;
		this.others1 = others1;
		this.others2 = others2;
		this.others3 = others3;
		this.familyConsumeTotal = familyConsumeTotal;
		this.interestCost = interestCost;
		this.repaymentCost = repaymentCost;
		this.remarks = remarks;
		this.debtChangeExplain = debtChangeExplain;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_FAMILY_CONSUME", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
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

	@Column(name = "LIFE_CONSUME", precision = 22, scale = 0)
	public BigDecimal getLifeConsume() {
		return this.lifeConsume;
	}

	public void setLifeConsume(BigDecimal lifeConsume) {
		this.lifeConsume = lifeConsume;
	}

	@Column(name = "TUITION", precision = 22, scale = 0)
	public BigDecimal getTuition() {
		return this.tuition;
	}

	public void setTuition(BigDecimal tuition) {
		this.tuition = tuition;
	}

	@Column(name = "MEDICAL", precision = 22, scale = 0)
	public BigDecimal getMedical() {
		return this.medical;
	}

	public void setMedical(BigDecimal medical) {
		this.medical = medical;
	}

	@Column(name = "INSURANCE", precision = 22, scale = 0)
	public BigDecimal getInsurance() {
		return this.insurance;
	}

	public void setInsurance(BigDecimal insurance) {
		this.insurance = insurance;
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

	@Column(name = "FAMILY_CONSUME_TOTAL", precision = 22, scale = 0)
	public BigDecimal getFamilyConsumeTotal() {
		return this.familyConsumeTotal;
	}

	public void setFamilyConsumeTotal(BigDecimal familyConsumeTotal) {
		this.familyConsumeTotal = familyConsumeTotal;
	}

	@Column(name = "INTEREST_COST", precision = 22, scale = 0)
	public BigDecimal getInterestCost() {
		return this.interestCost;
	}

	public void setInterestCost(BigDecimal interestCost) {
		this.interestCost = interestCost;
	}

	@Column(name = "REPAYMENT_COST", precision = 22, scale = 0)
	public BigDecimal getRepaymentCost() {
		return this.repaymentCost;
	}

	public void setRepaymentCost(BigDecimal repaymentCost) {
		this.repaymentCost = repaymentCost;
	}

	@Column(name = "REMARKS", length = 200)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "DEBT_CHANGE_EXPLAIN", length = 200)
	public String getDebtChangeExplain() {
		return this.debtChangeExplain;
	}

	public void setDebtChangeExplain(String debtChangeExplain) {
		this.debtChangeExplain = debtChangeExplain;
	}

}
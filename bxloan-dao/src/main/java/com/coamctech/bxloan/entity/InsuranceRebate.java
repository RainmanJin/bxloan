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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * InsuranceRebate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INSURANCE_REBATE", schema = WD_SCHEMA)
public class InsuranceRebate implements java.io.Serializable {

	// Fields

	private Long id;
	private BigDecimal discount;
	private Long startTerm;
	private Long endTerm;
	private String guaranteeMode;
	private String termUnit;
	private Long insuranceId;
	private Date sysCreateDate;
	private Date sysUpdateDate;

	// Constructors

	/** default constructor */
	public InsuranceRebate() {
	}

	/** minimal constructor */
	public InsuranceRebate(Long id) {
		this.id = id;
	}

	/** full constructor */
	public InsuranceRebate(Long id, BigDecimal discount, Long startTerm,
			Long endTerm, String guaranteeMode, String termUnit,
			Long insuranceId, Date sysCreateDate, Date sysUpdateDate) {
		this.id = id;
		this.discount = discount;
		this.startTerm = startTerm;
		this.endTerm = endTerm;
		this.guaranteeMode = guaranteeMode;
		this.termUnit = termUnit;
		this.insuranceId = insuranceId;
		this.sysCreateDate = sysCreateDate;
		this.sysUpdateDate = sysUpdateDate;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_INSURANCE_REBATE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "DISCOUNT", precision = 20)
	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Column(name = "START_TERM", precision = 12, scale = 0)
	public Long getStartTerm() {
		return this.startTerm;
	}

	public void setStartTerm(Long startTerm) {
		this.startTerm = startTerm;
	}

	@Column(name = "END_TERM", precision = 12, scale = 0)
	public Long getEndTerm() {
		return this.endTerm;
	}

	public void setEndTerm(Long endTerm) {
		this.endTerm = endTerm;
	}

	@Column(name = "GUARANTEE_MODE", length = 10)
	public String getGuaranteeMode() {
		return this.guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	@Column(name = "TERM_UNIT", length = 1)
	public String getTermUnit() {
		return this.termUnit;
	}

	public void setTermUnit(String termUnit) {
		this.termUnit = termUnit;
	}

	@Column(name = "INSURANCE_ID", precision = 12, scale = 0)
	public Long getInsuranceId() {
		return this.insuranceId;
	}

	public void setInsuranceId(Long insuranceId) {
		this.insuranceId = insuranceId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_CREATE_DATE", length = 7)
	public Date getSysCreateDate() {
		return this.sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_UPDATE_DATE", length = 7)
	public Date getSysUpdateDate() {
		return this.sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

}
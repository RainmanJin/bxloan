package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BizRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHARGE_INFO", schema = WD_SCHEMA)
public class ChargeInfo implements java.io.Serializable {

	// Fields

	private Long chargeId;
	private String chargeType;
	private String productId;
	private String orgId;
	private String chargeCompType;
	private Double chargeAmt;
	private String yearLimit;
	private String partyTypeCd;
	private Double chargeRatio;
	
	// Constructors

	/** default constructor */
	public ChargeInfo() {

	}

	/** minimal constructor */
	public ChargeInfo(Long chargeId, String productId) {
		this.chargeId = chargeId;
		this.productId = productId;
	}

	/** full constructor */
	public ChargeInfo(Long chargeId, String chargeType, String productId,
			String orgId, String chargeCompType, Double chargeAmt,
			String yearLimit, String partyTypeCd, Double chargeRatio) {
		this.chargeId = chargeId;
		this.chargeType = chargeType;
		this.productId = productId;
		this.orgId = orgId;
		this.chargeCompType = chargeCompType;
		this.chargeAmt = chargeAmt;
		this.yearLimit = yearLimit;
		this.partyTypeCd = partyTypeCd;
		this.chargeRatio = chargeRatio;
	}
	
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "CHARGE_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	@Column(name = "CHARGE_TYPE", length = 30)
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	@Column(name = "PRODUCT_ID", length = 200)
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Column(name = "ORG_ID", length = 2000)
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "CHARGE_COMP_TYPE", length = 20)
	public String getChargeCompType() {
		return chargeCompType;
	}

	public void setChargeCompType(String chargeCompType) {
		this.chargeCompType = chargeCompType;
	}

	@Column(name = "CHARGE_AMT", precision = 14, scale = 2)
	public Double getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(Double chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	@Column(name = "YEAR_LIMIT", length = 20)
	public String getYearLimit() {
		return yearLimit;
	}

	public void setYearLimit(String yearLimit) {
		this.yearLimit = yearLimit;
	}

	@Column(name = "PARTY_TYPE_CD", length = 20)
	public String getPartyTypeCd() {
		return partyTypeCd;
	}

	public void setPartyTypeCd(String partyTypeCd) {
		this.partyTypeCd = partyTypeCd;
	}

	@Column(name = "CHARGE_RATIO", precision = 20, scale = 6)
	public Double getChargeRatio() {
		return chargeRatio;
	}

	public void setChargeRatio(Double chargeRatio) {
		this.chargeRatio = chargeRatio;
	}

}

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
 * InsuranceCompany entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INSURANCE_COMPANY", schema = WD_SCHEMA)
public class InsuranceCompany implements java.io.Serializable {

	// Fields

	private Long id;
	private String customerNum;
	private String customerName;
	private String certificateType;
	private String certificateNum;
	private String businessLicenseNum;
	private String orgScale;
	private BigDecimal pledgeRate;
	private BigDecimal impawnRate;
	private BigDecimal assureRate;
	private BigDecimal creditRate;
	private Long parentId;
	private Date sysCreateDate;
	private Date sysUpdateDate;
	private String status;

	// Constructors

	/** default constructor */
	public InsuranceCompany() {
	}

	/** minimal constructor */
	public InsuranceCompany(Long id) {
		this.id = id;
	}

	/** full constructor */
	public InsuranceCompany(Long id, String customerNum, String customerName,
			String certificateType, String certificateNum,
			String businessLicenseNum, String orgScale, BigDecimal pledgeRate,
			BigDecimal impawnRate, BigDecimal assureRate,
			BigDecimal creditRate, Long parentId, Date sysCreateDate,
			Date sysUpdateDate, String status) {
		this.id = id;
		this.customerNum = customerNum;
		this.customerName = customerName;
		this.certificateType = certificateType;
		this.certificateNum = certificateNum;
		this.businessLicenseNum = businessLicenseNum;
		this.orgScale = orgScale;
		this.pledgeRate = pledgeRate;
		this.impawnRate = impawnRate;
		this.assureRate = assureRate;
		this.creditRate = creditRate;
		this.parentId = parentId;
		this.sysCreateDate = sysCreateDate;
		this.sysUpdateDate = sysUpdateDate;
		this.status = status;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PK_SEQ_TBL", sequenceName = "SEQ_INSURANCE_COMPANY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_SEQ_TBL")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CUSTOMER_NUM", length = 30)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "CUSTOMER_NAME", length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Column(name = "CERTIFICATE_TYPE", length = 30)
	public String getCertificateType() {
		return this.certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	@Column(name = "CERTIFICATE_NUM", length = 60)
	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	@Column(name = "BUSINESS_LICENSE_NUM", length = 50)
	public String getBusinessLicenseNum() {
		return this.businessLicenseNum;
	}

	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}

	@Column(name = "ORG_SCALE", length = 20)
	public String getOrgScale() {
		return this.orgScale;
	}

	public void setOrgScale(String orgScale) {
		this.orgScale = orgScale;
	}

	@Column(name = "PLEDGE_RATE", precision = 20)
	public BigDecimal getPledgeRate() {
		return this.pledgeRate;
	}

	public void setPledgeRate(BigDecimal pledgeRate) {
		this.pledgeRate = pledgeRate;
	}

	@Column(name = "IMPAWN_RATE", precision = 20)
	public BigDecimal getImpawnRate() {
		return this.impawnRate;
	}

	public void setImpawnRate(BigDecimal impawnRate) {
		this.impawnRate = impawnRate;
	}

	@Column(name = "ASSURE_RATE", precision = 20)
	public BigDecimal getAssureRate() {
		return this.assureRate;
	}

	public void setAssureRate(BigDecimal assureRate) {
		this.assureRate = assureRate;
	}

	@Column(name = "CREDIT_RATE", precision = 20)
	public BigDecimal getCreditRate() {
		return this.creditRate;
	}

	public void setCreditRate(BigDecimal creditRate) {
		this.creditRate = creditRate;
	}

	@Column(name = "PARENT_ID", precision = 12, scale = 0)
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
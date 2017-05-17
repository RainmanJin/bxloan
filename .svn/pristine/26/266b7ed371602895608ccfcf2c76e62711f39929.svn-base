package com.coamctech.bxloan.entity;
// default package

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
 * Party entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PARTY", schema = GlobalConstants.WD_SCHEMA)
public class Party  implements java.io.Serializable {

	// Fields
	
	private Long partyId;
	private String customerNum;
	private String partyTypeCd;
	private String partyName;
	private Date sysUpdateTime;
	private String certificateNum;
	private String certificateTypeCd;
	private String customerSource;
	private String status;
	private String markType;
	// Constructors

	/** default constructor */
	public Party() {
	}

	/** minimal constructor */
	public Party(Long partyId) {
		this.partyId = partyId;
	}

	/** full constructor */
	public Party(Long partyId, String customerNum, String partyTypeCd,
			String partyName, Date sysUpdateTime, String certificateNum,
			String certificateTypeCd, String customerSource) {
		this.partyId = partyId;
		this.customerNum = customerNum;
		this.partyTypeCd = partyTypeCd;
		this.partyName = partyName;
		this.sysUpdateTime = sysUpdateTime;
		this.certificateNum = certificateNum;
		this.certificateTypeCd = certificateTypeCd;
		this.customerSource = customerSource;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_PARTY", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "PARTY_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CUSTOMER_NUM", length = 21)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "PARTY_TYPE_CD", length = 20)
	public String getPartyTypeCd() {
		return this.partyTypeCd;
	}

	public void setPartyTypeCd(String partyTypeCd) {
		this.partyTypeCd = partyTypeCd;
	}

	@Column(name = "PARTY_NAME", length = 200)
	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Date getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "CERTIFICATE_NUM", length = 60)
	public String getCertificateNum() {
		return this.certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	@Column(name = "CERTIFICATE_TYPE_CD", length = 20)
	public String getCertificateTypeCd() {
		return this.certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	@Column(name = "CUSTOMER_SOURCE", length = 1)
	public String getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "MARK_TYPE", length = 20)
	public String getMarkType() {
		return markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}
	
}
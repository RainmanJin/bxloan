package com.coamctech.bxloan.entity;

// default package

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Collateral entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COLLATERAL", schema = WD_SCHEMA)
public class Collateral implements java.io.Serializable {

	// Fields

	private Long guarantyId;
	private String guarantyNum;
	private String guarantyName;
	private String guarantyTypeCd;
	private BigDecimal marketValue;
	private BigDecimal evalValue;
	private String guarantyStatusCd;
	private String guarantyDispose;
	private String acquireWayOfGuarantyCd;
	private String commonAssetsInd;
	private String guarantySetupInd;
	private BigDecimal setGuarantyAmt;
	private String otherNote;
	private String rightCertTypeCd;
	private String rightCertificationNum;
	private Date sysUpdateDate;
	private Date sysCreateDate;
	private String guarantorName;
	private String guarantorCertificateNum;
	private String guarantorTypeCd;
	private String partOwnerName;
	private String registerUserNum;
	private Long registerOrgCd;
	private String propertyName;
	private String guaranteeType;
	private String collateralType;
	private BigDecimal lockingAmount;

	private String sysUpdateDateStr;

	// Constructors

	/** default constructor */
	public Collateral() {
	}

	/** minimal constructor */
	public Collateral(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	/** full constructor */
	public Collateral(Long guarantyId, String guarantyNum, String guarantyName,
			String guarantyTypeCd, BigDecimal marketValue,
			BigDecimal evalValue, String guarantyStatusCd,
			String guarantyDispose, String acquireWayOfGuarantyCd,
			String commonAssetsInd, String guarantySetupInd,
			BigDecimal setGuarantyAmt, String otherNote,
			String rightCertTypeCd, String rightCertificationNum,
			Date sysUpdateDate, Date sysCreateDate, String guarantorName,
			String guarantorCertificateNum, String guarantorTypeCd,
			String partOwnerName, String registerUserNum, Long registerOrgCd,
			String propertyName, String guaranteeType, String collateralType,
			BigDecimal lockingAmount) {
		this.guarantyId = guarantyId;
		this.guarantyNum = guarantyNum;
		this.guarantyName = guarantyName;
		this.guarantyTypeCd = guarantyTypeCd;
		this.marketValue = marketValue;
		this.evalValue = evalValue;
		this.guarantyStatusCd = guarantyStatusCd;
		this.guarantyDispose = guarantyDispose;
		this.acquireWayOfGuarantyCd = acquireWayOfGuarantyCd;
		this.commonAssetsInd = commonAssetsInd;
		this.guarantySetupInd = guarantySetupInd;
		this.setGuarantyAmt = setGuarantyAmt;
		this.otherNote = otherNote;
		this.rightCertTypeCd = rightCertTypeCd;
		this.rightCertificationNum = rightCertificationNum;
		this.sysUpdateDate = sysUpdateDate;
		this.sysCreateDate = sysCreateDate;
		this.guarantorName = guarantorName;
		this.guarantorCertificateNum = guarantorCertificateNum;
		this.guarantorTypeCd = guarantorTypeCd;
		this.partOwnerName = partOwnerName;
		this.registerUserNum = registerUserNum;
		this.registerOrgCd = registerOrgCd;
		this.propertyName = propertyName;
		this.guaranteeType = guaranteeType;
		this.collateralType = collateralType;
		this.lockingAmount = lockingAmount;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_COLLATERAL", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "GUARANTY_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getGuarantyId() {
		return this.guarantyId;
	}

	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	@Column(name = "GUARANTY_NUM", length = 30)
	public String getGuarantyNum() {
		return this.guarantyNum;
	}

	public void setGuarantyNum(String guarantyNum) {
		this.guarantyNum = guarantyNum;
	}

	@Column(name = "GUARANTY_NAME", length = 100)
	public String getGuarantyName() {
		return this.guarantyName;
	}

	public void setGuarantyName(String guarantyName) {
		this.guarantyName = guarantyName;
	}

	@Column(name = "GUARANTY_TYPE_CD", length = 20)
	public String getGuarantyTypeCd() {
		return this.guarantyTypeCd;
	}

	public void setGuarantyTypeCd(String guarantyTypeCd) {
		this.guarantyTypeCd = guarantyTypeCd;
	}

	@Column(name = "MARKET_VALUE", precision = 20)
	public BigDecimal getMarketValue() {
		return this.marketValue;
	}

	public void setMarketValue(BigDecimal marketValue) {
		this.marketValue = marketValue;
	}

	@Column(name = "EVAL_VALUE", precision = 20)
	public BigDecimal getEvalValue() {
		return this.evalValue;
	}

	public void setEvalValue(BigDecimal evalValue) {
		this.evalValue = evalValue;
	}

	@Column(name = "GUARANTY_STATUS_CD", length = 20)
	public String getGuarantyStatusCd() {
		return this.guarantyStatusCd;
	}

	public void setGuarantyStatusCd(String guarantyStatusCd) {
		this.guarantyStatusCd = guarantyStatusCd;
	}

	@Column(name = "GUARANTY_DISPOSE", length = 1)
	public String getGuarantyDispose() {
		return this.guarantyDispose;
	}

	public void setGuarantyDispose(String guarantyDispose) {
		this.guarantyDispose = guarantyDispose;
	}

	@Column(name = "ACQUIRE_WAY_OF_GUARANTY_CD", length = 20)
	public String getAcquireWayOfGuarantyCd() {
		return this.acquireWayOfGuarantyCd;
	}

	public void setAcquireWayOfGuarantyCd(String acquireWayOfGuarantyCd) {
		this.acquireWayOfGuarantyCd = acquireWayOfGuarantyCd;
	}

	@Column(name = "COMMON_ASSETS_IND", length = 1)
	public String getCommonAssetsInd() {
		return this.commonAssetsInd;
	}

	public void setCommonAssetsInd(String commonAssetsInd) {
		this.commonAssetsInd = commonAssetsInd;
	}

	@Column(name = "GUARANTY_SETUP_IND", length = 1)
	public String getGuarantySetupInd() {
		return this.guarantySetupInd;
	}

	public void setGuarantySetupInd(String guarantySetupInd) {
		this.guarantySetupInd = guarantySetupInd;
	}

	@Column(name = "SET_GUARANTY_AMT", precision = 20)
	public BigDecimal getSetGuarantyAmt() {
		return this.setGuarantyAmt;
	}

	public void setSetGuarantyAmt(BigDecimal setGuarantyAmt) {
		this.setGuarantyAmt = setGuarantyAmt;
	}

	@Column(name = "OTHER_NOTE", length = 1000)
	public String getOtherNote() {
		return this.otherNote;
	}

	public void setOtherNote(String otherNote) {
		this.otherNote = otherNote;
	}

	@Column(name = "RIGHT_CERT_TYPE_CD", length = 20)
	public String getRightCertTypeCd() {
		return this.rightCertTypeCd;
	}

	public void setRightCertTypeCd(String rightCertTypeCd) {
		this.rightCertTypeCd = rightCertTypeCd;
	}

	@Column(name = "RIGHT_CERTIFICATION_NUM", length = 200)
	public String getRightCertificationNum() {
		return this.rightCertificationNum;
	}

	public void setRightCertificationNum(String rightCertificationNum) {
		this.rightCertificationNum = rightCertificationNum;
	}

	@Column(name = "SYS_UPDATE_DATE", length = 7)
	public Date getSysUpdateDate() {
		return this.sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	@Column(name = "SYS_CREATE_DATE", length = 7)
	public Date getSysCreateDate() {
		return this.sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	@Column(name = "GUARANTOR_NAME", length = 100)
	public String getGuarantorName() {
		return this.guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	@Column(name = "GUARANTOR_CERTIFICATE_NUM", length = 40)
	public String getGuarantorCertificateNum() {
		return this.guarantorCertificateNum;
	}

	public void setGuarantorCertificateNum(String guarantorCertificateNum) {
		this.guarantorCertificateNum = guarantorCertificateNum;
	}

	@Column(name = "GUARANTOR_TYPE_CD", length = 20)
	public String getGuarantorTypeCd() {
		return this.guarantorTypeCd;
	}

	public void setGuarantorTypeCd(String guarantorTypeCd) {
		this.guarantorTypeCd = guarantorTypeCd;
	}

	@Column(name = "PART_OWNER_NAME", length = 100)
	public String getPartOwnerName() {
		return this.partOwnerName;
	}

	public void setPartOwnerName(String partOwnerName) {
		this.partOwnerName = partOwnerName;
	}

	@Column(name = "REGISTER_USER_NUM", length = 40)
	public String getRegisterUserNum() {
		return this.registerUserNum;
	}

	public void setRegisterUserNum(String registerUserNum) {
		this.registerUserNum = registerUserNum;
	}

	@Column(name = "REGISTER_ORG_CD", precision = 12, scale = 0)
	public Long getRegisterOrgCd() {
		return this.registerOrgCd;
	}

	public void setRegisterOrgCd(Long registerOrgCd) {
		this.registerOrgCd = registerOrgCd;
	}

	@Column(name = "PROPERTY_NAME", length = 100)
	public String getPropertyName() {
		return this.propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	@Column(name = "GUARANTEE_TYPE", length = 20)
	public String getGuaranteeType() {
		return this.guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	@Column(name = "COLLATERAL_TYPE", length = 20)
	public String getCollateralType() {
		return this.collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	@Column(name = "LOCKING_AMOUNT", precision = 20)
	public BigDecimal getLockingAmount() {
		return this.lockingAmount;
	}

	public void setLockingAmount(BigDecimal lockingAmount) {
		this.lockingAmount = lockingAmount;
	}

	@Transient
	public String getSysUpdateDateStr() {
		return sysUpdateDateStr;
	}

	public void setSysUpdateDateStr(String sysUpdateDateStr) {
		this.sysUpdateDateStr = sysUpdateDateStr;
	}

}
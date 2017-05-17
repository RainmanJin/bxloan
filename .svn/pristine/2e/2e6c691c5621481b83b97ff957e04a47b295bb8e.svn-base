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

/**
 * ProjectPawnInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROJECT_PAWN_INFO", schema =WD_SCHEMA)
public class ProjectPawnInfo implements java.io.Serializable {

	// Fields

	private Long projectPawnInfoId;
	private String guarantyNum;
	private String guarantyName;
	private BigDecimal evalValue;
	private BigDecimal setGuaranteeAmt;
	private BigDecimal appGuaDebtInterAmt;
	private String guaranteeType;
	private String currency;
	private String unchainInd;
	private Date createDate;
	private Date sysUpdateTime;
	private Long guarantyId;
	private Long projectId;
	private String guarantyTypeCd;
	private String guarantorName;
	private String guarantorTypeCd;
	private String guarantorCertificateNum;
	private BigDecimal pawnRatio;
	private String collateralType;
	private BigDecimal actualGuaranteeRate;
	private BigDecimal actualCreditAmount;
	private String pawnState;

	// Constructors

	/** default constructor */
	public ProjectPawnInfo() {
	}

	/** minimal constructor */
	public ProjectPawnInfo(Long projectPawnInfoId) {
		this.projectPawnInfoId = projectPawnInfoId;
	}

	/** full constructor */
	public ProjectPawnInfo(Long projectPawnInfoId, String guarantyNum,
			String guarantyName, BigDecimal evalValue, BigDecimal setGuaranteeAmt,
			BigDecimal appGuaDebtInterAmt, String guaranteeType, String currency,
			String unchainInd, Date createDate, Date sysUpdateTime,
			Long guarantyId, Long projectId, String guarantyTypeCd,
			String guarantorName, String guarantorTypeCd,
			String guarantorCertificateNum, BigDecimal pawnRatio,
			String collateralType, BigDecimal actualGuaranteeRate,
			BigDecimal actualCreditAmount, String pawnState) {
		this.projectPawnInfoId = projectPawnInfoId;
		this.guarantyNum = guarantyNum;
		this.guarantyName = guarantyName;
		this.evalValue = evalValue;
		this.setGuaranteeAmt = setGuaranteeAmt;
		this.appGuaDebtInterAmt = appGuaDebtInterAmt;
		this.guaranteeType = guaranteeType;
		this.currency = currency;
		this.unchainInd = unchainInd;
		this.createDate = createDate;
		this.sysUpdateTime = sysUpdateTime;
		this.guarantyId = guarantyId;
		this.projectId = projectId;
		this.guarantyTypeCd = guarantyTypeCd;
		this.guarantorName = guarantorName;
		this.guarantorTypeCd = guarantorTypeCd;
		this.guarantorCertificateNum = guarantorCertificateNum;
		this.pawnRatio = pawnRatio;
		this.collateralType = collateralType;
		this.actualGuaranteeRate = actualGuaranteeRate;
		this.actualCreditAmount = actualCreditAmount;
		this.pawnState = pawnState;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_PROJECT_PAWN_INFO", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "PROJECT_PAWN_INFO_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getProjectPawnInfoId() {
		return this.projectPawnInfoId;
	}

	public void setProjectPawnInfoId(Long projectPawnInfoId) {
		this.projectPawnInfoId = projectPawnInfoId;
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

	@Column(name = "EVAL_VALUE", precision = 20)
	public BigDecimal getEvalValue() {
		return this.evalValue;
	}

	public void setEvalValue(BigDecimal evalValue) {
		this.evalValue = evalValue;
	}

	@Column(name = "SET_GUARANTEE_AMT", precision = 20)
	public BigDecimal getSetGuaranteeAmt() {
		return this.setGuaranteeAmt;
	}

	public void setSetGuaranteeAmt(BigDecimal setGuaranteeAmt) {
		this.setGuaranteeAmt = setGuaranteeAmt;
	}

	@Column(name = "APP_GUA_DEBT_INTER_AMT", precision = 20)
	public BigDecimal getAppGuaDebtInterAmt() {
		return this.appGuaDebtInterAmt;
	}

	public void setAppGuaDebtInterAmt(BigDecimal appGuaDebtInterAmt) {
		this.appGuaDebtInterAmt = appGuaDebtInterAmt;
	}

	@Column(name = "GUARANTEE_TYPE", length = 30)
	public String getGuaranteeType() {
		return this.guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	@Column(name = "CURRENCY", length = 30)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "UNCHAIN_IND", length = 1)
	public String getUnchainInd() {
		return this.unchainInd;
	}

	public void setUnchainInd(String unchainInd) {
		this.unchainInd = unchainInd;
	}

	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Date getSysUpdateTime() {
		return this.sysUpdateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "GUARANTY_ID", precision = 12, scale = 0)
	public Long getGuarantyId() {
		return this.guarantyId;
	}

	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "GUARANTY_TYPE_CD", length = 20)
	public String getGuarantyTypeCd() {
		return this.guarantyTypeCd;
	}

	public void setGuarantyTypeCd(String guarantyTypeCd) {
		this.guarantyTypeCd = guarantyTypeCd;
	}

	@Column(name = "GUARANTOR_NAME", length = 100)
	public String getGuarantorName() {
		return this.guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	@Column(name = "GUARANTOR_TYPE_CD", length = 20)
	public String getGuarantorTypeCd() {
		return this.guarantorTypeCd;
	}

	public void setGuarantorTypeCd(String guarantorTypeCd) {
		this.guarantorTypeCd = guarantorTypeCd;
	}

	@Column(name = "GUARANTOR_CERTIFICATE_NUM", length = 40)
	public String getGuarantorCertificateNum() {
		return this.guarantorCertificateNum;
	}

	public void setGuarantorCertificateNum(String guarantorCertificateNum) {
		this.guarantorCertificateNum = guarantorCertificateNum;
	}

	@Column(name = "PAWN_RATIO", precision = 20, scale = 6)
	public BigDecimal getPawnRatio() {
		return this.pawnRatio;
	}

	public void setPawnRatio(BigDecimal pawnRatio) {
		this.pawnRatio = pawnRatio;
	}

	@Column(name = "COLLATERAL_TYPE", length = 20)
	public String getCollateralType() {
		return this.collateralType;
	}

	public void setCollateralType(String collateralType) {
		this.collateralType = collateralType;
	}

	@Column(name = "ACTUAL_GUARANTEE_RATE", precision = 20, scale = 6)
	public BigDecimal getActualGuaranteeRate() {
		return this.actualGuaranteeRate;
	}

	public void setActualGuaranteeRate(BigDecimal actualGuaranteeRate) {
		this.actualGuaranteeRate = actualGuaranteeRate;
	}

	@Column(name = "ACTUAL_CREDIT_AMOUNT", precision = 20)
	public BigDecimal getActualCreditAmount() {
		return this.actualCreditAmount;
	}

	public void setActualCreditAmount(BigDecimal actualCreditAmount) {
		this.actualCreditAmount = actualCreditAmount;
	}

	@Column(name = "PAWN_STATE", length = 20)
	public String getPawnState() {
		return this.pawnState;
	}

	public void setPawnState(String pawnState) {
		this.pawnState = pawnState;
	}

}
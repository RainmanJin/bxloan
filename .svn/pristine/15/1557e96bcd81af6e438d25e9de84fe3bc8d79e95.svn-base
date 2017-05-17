package com.coamctech.bxloan.entity;

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
 * BizRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BIZ_RATE", schema = WD_SCHEMA)
public class BizRate implements java.io.Serializable {

	// Fields

	private Long bizRateId;
	private String projectId;
	private String projectNo;
	private String productCd;
	private String irTypeCd;//利率类型
	private String irGistStyleCd;
	private BigDecimal irNegoSymbCd;//利率上浮比例
	private String irSettlemtStyleCd;
	private String ovdueIrGistStyleCd;
	private BigDecimal ovdueIrNegoRate;//逾期利率上浮比例
	private String peculIrGistStyleCd;
	private BigDecimal perculIrNegoRate;//挪用利率上浮比例
	private Date sysCreateDate;
	private Date sysUpdateDate;
	private String brandRateTypeCd;
	private String interestSettlementStyle;
	private BigDecimal rateValue; // 利率值
	private BigDecimal perculirNegoRate;
	private String adjustPeriod;//调整周期
	private String approveIrTypeCd;
	private String approveAdjustPeriod;
	private BigDecimal approveFloatRate;
	private BigDecimal approveRateValue;
	private String rateAdjustmentReason;
	private String finalIrTypeCd;
	private String finalAdjustPeriod;
	private BigDecimal finalFloatRate;
	private BigDecimal finalRateValue;

	// Constructors

	/** default constructor */
	public BizRate() {
	}

	/** minimal constructor */
	public BizRate(Long bizRateId, String projectId) {
		this.bizRateId = bizRateId;
		this.projectId = projectId;
	}

	/** full constructor */
	public BizRate(Long bizRateId, String projectId, String projectNo,
			String productCd, String irTypeCd, String irGistStyleCd,
			BigDecimal irNegoSymbCd, String irSettlemtStyleCd,
			String ovdueIrGistStyleCd, BigDecimal ovdueIrNegoRate,
			String peculIrGistStyleCd, BigDecimal perculIrNegoRate,
			Date sysCreateDate, Date sysUpdateDate, String brandRateTypeCd,
			String interestSettlementStyle, BigDecimal rateValue,
			BigDecimal perculirNegoRate, String adjustPeriod,
			String approveIrTypeCd, String approveAdjustPeriod,
			BigDecimal approveFloatRate, BigDecimal approveRateValue,
			String rateAdjustmentReason, String finalIrTypeCd,
			String finalAdjustPeriod, BigDecimal finalFloatRate,
			BigDecimal finalRateValue) {
		this.bizRateId = bizRateId;
		this.projectId = projectId;
		this.projectNo = projectNo;
		this.productCd = productCd;
		this.irTypeCd = irTypeCd;
		this.irGistStyleCd = irGistStyleCd;
		this.irNegoSymbCd = irNegoSymbCd;
		this.irSettlemtStyleCd = irSettlemtStyleCd;
		this.ovdueIrGistStyleCd = ovdueIrGistStyleCd;
		this.ovdueIrNegoRate = ovdueIrNegoRate;
		this.peculIrGistStyleCd = peculIrGistStyleCd;
		this.perculIrNegoRate = perculIrNegoRate;
		this.sysCreateDate = sysCreateDate;
		this.sysUpdateDate = sysUpdateDate;
		this.brandRateTypeCd = brandRateTypeCd;
		this.interestSettlementStyle = interestSettlementStyle;
		this.rateValue = rateValue;
		this.perculirNegoRate = perculirNegoRate;
		this.adjustPeriod = adjustPeriod;
		this.approveIrTypeCd = approveIrTypeCd;
		this.approveAdjustPeriod = approveAdjustPeriod;
		this.approveFloatRate = approveFloatRate;
		this.approveRateValue = approveRateValue;
		this.rateAdjustmentReason = rateAdjustmentReason;
		this.finalIrTypeCd = finalIrTypeCd;
		this.finalAdjustPeriod = finalAdjustPeriod;
		this.finalFloatRate = finalFloatRate;
		this.finalRateValue = finalRateValue;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_BIZ_RATE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "BIZ_RATE_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getBizRateId() {
		return this.bizRateId;
	}

	public void setBizRateId(Long bizRateId) {
		this.bizRateId = bizRateId;
	}

	@Column(name = "PROJECT_ID", nullable = false, length = 256)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_NO", length = 30)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PRODUCT_CD", length = 20)
	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	@Column(name = "IR_TYPE_CD", length = 20)
	public String getIrTypeCd() {
		return this.irTypeCd;
	}

	public void setIrTypeCd(String irTypeCd) {
		this.irTypeCd = irTypeCd;
	}

	@Column(name = "IR_GIST_STYLE_CD", length = 20)
	public String getIrGistStyleCd() {
		return this.irGistStyleCd;
	}

	public void setIrGistStyleCd(String irGistStyleCd) {
		this.irGistStyleCd = irGistStyleCd;
	}

	@Column(name = "IR_NEGO_SYMB_CD", precision = 20, scale = 6)
	public BigDecimal getIrNegoSymbCd() {
		return this.irNegoSymbCd;
	}

	public void setIrNegoSymbCd(BigDecimal irNegoSymbCd) {
		this.irNegoSymbCd = irNegoSymbCd;
	}

	@Column(name = "IR_SETTLEMT_STYLE_CD", length = 20)
	public String getIrSettlemtStyleCd() {
		return this.irSettlemtStyleCd;
	}

	public void setIrSettlemtStyleCd(String irSettlemtStyleCd) {
		this.irSettlemtStyleCd = irSettlemtStyleCd;
	}

	@Column(name = "OVDUE_IR_GIST_STYLE_CD", length = 30)
	public String getOvdueIrGistStyleCd() {
		return this.ovdueIrGistStyleCd;
	}

	public void setOvdueIrGistStyleCd(String ovdueIrGistStyleCd) {
		this.ovdueIrGistStyleCd = ovdueIrGistStyleCd;
	}

	@Column(name = "OVDUE_IR_NEGO_RATE", precision = 20, scale = 6)
	public BigDecimal getOvdueIrNegoRate() {
		return this.ovdueIrNegoRate;
	}

	public void setOvdueIrNegoRate(BigDecimal ovdueIrNegoRate) {
		this.ovdueIrNegoRate = ovdueIrNegoRate;
	}

	@Column(name = "PECUL_IR_GIST_STYLE_CD", length = 20)
	public String getPeculIrGistStyleCd() {
		return this.peculIrGistStyleCd;
	}

	public void setPeculIrGistStyleCd(String peculIrGistStyleCd) {
		this.peculIrGistStyleCd = peculIrGistStyleCd;
	}

	@Column(name = "PERCUL_IR_NEGO_RATE", precision = 20, scale = 6)
	public BigDecimal getPerculIrNegoRate() {
		return this.perculIrNegoRate;
	}

	public void setPerculIrNegoRate(BigDecimal perculIrNegoRate) {
		this.perculIrNegoRate = perculIrNegoRate;
	}

	@Column(name = "SYS_CREATE_DATE", length = 7)
	public Date getSysCreateDate() {
		return this.sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	@Column(name = "SYS_UPDATE_DATE", length = 7)
	public Date getSysUpdateDate() {
		return this.sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	@Column(name = "BRAND_RATE_TYPE_CD", length = 20)
	public String getBrandRateTypeCd() {
		return this.brandRateTypeCd;
	}

	public void setBrandRateTypeCd(String brandRateTypeCd) {
		this.brandRateTypeCd = brandRateTypeCd;
	}

	@Column(name = "INTEREST_SETTLEMENT_STYLE", length = 20)
	public String getInterestSettlementStyle() {
		return this.interestSettlementStyle;
	}

	public void setInterestSettlementStyle(String interestSettlementStyle) {
		this.interestSettlementStyle = interestSettlementStyle;
	}

	@Column(name = "RATE_VALUE", precision = 20, scale = 6)
	public BigDecimal getRateValue() {
		return this.rateValue;
	}

	public void setRateValue(BigDecimal rateValue) {
		this.rateValue = rateValue;
	}

	@Column(name = "PERCULIR_NEGO_RATE", precision = 20, scale = 6)
	public BigDecimal getPerculirNegoRate() {
		return this.perculirNegoRate;
	}

	public void setPerculirNegoRate(BigDecimal perculirNegoRate) {
		this.perculirNegoRate = perculirNegoRate;
	}

	@Column(name = "ADJUST_PERIOD", length = 20)
	public String getAdjustPeriod() {
		return this.adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	@Column(name = "APPROVE_IR_TYPE_CD", length = 20)
	public String getApproveIrTypeCd() {
		return this.approveIrTypeCd;
	}

	public void setApproveIrTypeCd(String approveIrTypeCd) {
		this.approveIrTypeCd = approveIrTypeCd;
	}

	@Column(name = "APPROVE_ADJUST_PERIOD", length = 20)
	public String getApproveAdjustPeriod() {
		return this.approveAdjustPeriod;
	}

	public void setApproveAdjustPeriod(String approveAdjustPeriod) {
		this.approveAdjustPeriod = approveAdjustPeriod;
	}

	@Column(name = "APPROVE_FLOAT_RATE", precision = 20, scale = 6)
	public BigDecimal getApproveFloatRate() {
		return this.approveFloatRate;
	}

	public void setApproveFloatRate(BigDecimal approveFloatRate) {
		this.approveFloatRate = approveFloatRate;
	}

	@Column(name = "APPROVE_RATE_VALUE", precision = 20, scale = 6)
	public BigDecimal getApproveRateValue() {
		return this.approveRateValue;
	}

	public void setApproveRateValue(BigDecimal approveRateValue) {
		this.approveRateValue = approveRateValue;
	}

	@Column(name = "RATE_ADJUSTMENT_REASON", length = 500)
	public String getRateAdjustmentReason() {
		return this.rateAdjustmentReason;
	}

	public void setRateAdjustmentReason(String rateAdjustmentReason) {
		this.rateAdjustmentReason = rateAdjustmentReason;
	}

	@Column(name = "FINAL_IR_TYPE_CD", length = 20)
	public String getFinalIrTypeCd() {
		return this.finalIrTypeCd;
	}

	public void setFinalIrTypeCd(String finalIrTypeCd) {
		this.finalIrTypeCd = finalIrTypeCd;
	}

	@Column(name = "FINAL_ADJUST_PERIOD", length = 20)
	public String getFinalAdjustPeriod() {
		return this.finalAdjustPeriod;
	}

	public void setFinalAdjustPeriod(String finalAdjustPeriod) {
		this.finalAdjustPeriod = finalAdjustPeriod;
	}

	@Column(name = "FINAL_FLOAT_RATE", precision = 20, scale = 6)
	public BigDecimal getFinalFloatRate() {
		return this.finalFloatRate;
	}

	public void setFinalFloatRate(BigDecimal finalFloatRate) {
		this.finalFloatRate = finalFloatRate;
	}

	@Column(name = "FINAL_RATE_VALUE", precision = 20, scale = 6)
	public BigDecimal getFinalRateValue() {
		return this.finalRateValue;
	}

	public void setFinalRateValue(BigDecimal finalRateValue) {
		this.finalRateValue = finalRateValue;
	}

}
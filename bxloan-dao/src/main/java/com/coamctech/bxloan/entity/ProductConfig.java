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

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * ProductConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PRODUCT_CONFIG", schema = WD_SCHEMA)
public class ProductConfig implements java.io.Serializable {

	// Fields
	private Long pcId; //配置表主键
	private String wfCode;//流程类型代码
	private BigDecimal minApplyAmt;//最小申请金额
	private BigDecimal maxApplyAmt;//最大申请金额
	private Integer minimalTerm;//最小申请期限
	private Integer maximalTerm;//最大申请期限
	private Long orgId;//机构号
	private Integer rateMultiple;//利率倍数
	private String customerType; //Customer类型
	private String loanTermMode; //贷款期限模式
	private Integer minLoanTerm; //最小贷款期限
	private Integer maxLoanTerm; //最大贷款期限
	private String specialLoanTerm; //特殊贷款期限
	private BigDecimal minRate; //最小年利率
	private BigDecimal maxRate; //最大年利率
	private BigDecimal minOverdueRate; //最小逾期利率上浮比
	private BigDecimal maxOverdueRate; //最大逾期利率上浮比
	private BigDecimal minPerculNegoRate; //最小挪用利率上浮比
	private BigDecimal maxPerculNegoRate; //最大挪用利率上浮比
	private BigDecimal minPreRepaymentRate; //最小提前还款违约比
	private BigDecimal maxPreRepaymentRate; //最大提前还款违约比
	private String repayingMode; //还款方式
	private Integer repayPeriodNum; //还款周期
	private String replyPeriodUnit; //还款周期单位
	private String guaranteeMode; //担保方式
	private String isBatch; //是否批量
	private BigDecimal batchLimit; //批量额度
	private String isStart; //是否启用
	private String description; //产品描述
	private String remarks; //备注
	private Long productCd; //产品代码
	private String productDesc;//产品描述
	private String customerProperty;//客户性质
	private Date createDate;
	private Date updateDate;
	

	// Constructors

	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/** default constructor */
	public ProductConfig() {
	}
	
	public ProductConfig(Long pcId) {
		super();
		this.pcId = pcId;
	}
	
	/** minimal constructor */
	public ProductConfig(Long pcId, Long productCd) {
		this.pcId = pcId;
		this.productCd = productCd;
	}

	/** full constructor */
	

	// Property accessors
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_PRODUCT_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "PC_ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getPcId() {
		return this.pcId;
	}

	public ProductConfig(Long pcId, String wfCode, BigDecimal minApplyAmt,
			BigDecimal maxApplyAmt, Integer minimalTerm, Integer maximalTerm,
			Long orgId, Integer rateMultiple, String customerType,
			String loanTermMode, Integer minLoanTerm,
			Integer maxLoanTerm, String specialLoanTerm, BigDecimal minRate,
			BigDecimal maxRate, BigDecimal minOverdueRate,
			BigDecimal maxOverdueRate, BigDecimal minPerculNegoRate,
			BigDecimal maxPerculNegoRate, BigDecimal minPreRepaymentRate,
			BigDecimal maxPreRepaymentRate, String repayingMode,
			Integer repayPeriodNum, String replyPeriodUnit,
			String guaranteeMode, String isBatch, BigDecimal batchLimit,
			String isStart, String description, String remarks, Long productCd,
			String productDesc) {
		super();
		this.pcId = pcId;
		this.wfCode = wfCode;
		this.minApplyAmt = minApplyAmt;
		this.maxApplyAmt = maxApplyAmt;
		this.minimalTerm = minimalTerm;
		this.maximalTerm = maximalTerm;
		this.orgId = orgId;
		this.rateMultiple = rateMultiple;
		this.customerType = customerType;
		this.loanTermMode = loanTermMode;
		this.minLoanTerm = minLoanTerm;
		this.maxLoanTerm = maxLoanTerm;
		this.specialLoanTerm = specialLoanTerm;
		this.minRate = minRate;
		this.maxRate = maxRate;
		this.minOverdueRate = minOverdueRate;
		this.maxOverdueRate = maxOverdueRate;
		this.minPerculNegoRate = minPerculNegoRate;
		this.maxPerculNegoRate = maxPerculNegoRate;
		this.minPreRepaymentRate = minPreRepaymentRate;
		this.maxPreRepaymentRate = maxPreRepaymentRate;
		this.repayingMode = repayingMode;
		this.repayPeriodNum = repayPeriodNum;
		this.replyPeriodUnit = replyPeriodUnit;
		this.guaranteeMode = guaranteeMode;
		this.isBatch = isBatch;
		this.batchLimit = batchLimit;
		this.isStart = isStart;
		this.description = description;
		this.remarks = remarks;
		this.productCd = productCd;
		this.productDesc = productDesc;
	}

	public void setPcId(Long pcId) {
		this.pcId = pcId;
	}

	@Column(name = "PRODUCT_CD", nullable = false, precision = 8, scale = 0)
	public Long getProductCd() {
		return this.productCd;
	}

	public void setProductCd(Long productCd) {
		this.productCd = productCd;
	}

	@Column(name = "PRODUCT_DESC", length = 1000)
	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@Column(name = "WF_CODE", length = 10)
	public String getWfCode() {
		return this.wfCode;
	}

	public void setWfCode(String wfCode) {
		this.wfCode = wfCode;
	}

	@Column(name = "MIN_APPLY_AMT", precision = 20, scale = 2)
	public BigDecimal getMinApplyAmt() {
		return this.minApplyAmt;
	}

	public void setMinApplyAmt(BigDecimal minApplyAmt) {
		this.minApplyAmt = minApplyAmt;
	}

	@Column(name = "MAX_APPLY_AMT", precision = 20, scale = 2)
	public BigDecimal getMaxApplyAmt() {
		return this.maxApplyAmt;
	}

	public void setMaxApplyAmt(BigDecimal maxApplyAmt) {
		this.maxApplyAmt = maxApplyAmt;
	}

	@Column(name = "MINIMAL_TERM", precision = 6, scale = 0)
	public Integer getMinimalTerm() {
		return this.minimalTerm;
	}

	public void setMinimalTerm(Integer minimalTerm) {
		this.minimalTerm = minimalTerm;
	}

	@Column(name = "MAXIMAL_TERM", precision = 6, scale = 0)
	public Integer getMaximalTerm() {
		return this.maximalTerm;
	}

	public void setMaximalTerm(Integer maximalTerm) {
		this.maximalTerm = maximalTerm;
	}

	@Column(name = "ORG_ID", precision = 22, scale = 0)
	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Column(name = "RATE_MULTIPLE", precision = 2, scale = 0)
	public Integer getRateMultiple() {
		return rateMultiple;
	}

	public void setRateMultiple(Integer rateMultiple) {
		this.rateMultiple = rateMultiple;
	}
	@Column(name = "CUSTOMER_TYPE", length = 20)
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	@Column(name = "LOAN_TERM_MODE", length = 20)
	public String getLoanTermMode() {
		return loanTermMode;
	}

	public void setLoanTermMode(String loanTermMode) {
		this.loanTermMode = loanTermMode;
	}
	@Column(name = "MIN_LOAN_TERM", precision = 22, scale = 0)
	public Integer getMinLoanTerm() {
		return minLoanTerm;
	}

	public void setMinLoanTerm(Integer minLoanTerm) {
		this.minLoanTerm = minLoanTerm;
	}
	@Column(name = "MAX_LOAN_TERM", precision = 22, scale = 0)
	public Integer getMaxLoanTerm() {
		return maxLoanTerm;
	}
	
	public void setMaxLoanTerm(Integer maxLoanTerm) {
		this.maxLoanTerm = maxLoanTerm;
	}
	@Column(name = "SPECIAL_LOAN_TERM", length = 200)
	public String getSpecialLoanTerm() {
		return specialLoanTerm;
	}

	public void setSpecialLoanTerm(String specialLoanTerm) {
		this.specialLoanTerm = specialLoanTerm;
	}
	@Column(name = "MIN_RATE", precision = 12, scale = 4)
	public BigDecimal getMinRate() {
		return minRate;
	}

	public void setMinRate(BigDecimal minRate) {
		this.minRate = minRate;
	}
	@Column(name = "MAX_RATE", precision = 12, scale = 4)
	public BigDecimal getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(BigDecimal maxRate) {
		this.maxRate = maxRate;
	}
	@Column(name = "MIN_OVERDUE_RATE", precision = 12, scale = 4)
	public BigDecimal getMinOverdueRate() {
		return minOverdueRate;
	}

	public void setMinOverdueRate(BigDecimal minOverdueRate) {
		this.minOverdueRate = minOverdueRate;
	}
	@Column(name = "MAX_OVERDUE_RATE", precision = 12, scale = 4)
	public BigDecimal getMaxOverdueRate() {
		return maxOverdueRate;
	}

	public void setMaxOverdueRate(BigDecimal maxOverdueRate) {
		this.maxOverdueRate = maxOverdueRate;
	}
	@Column(name = "MIN_PERCUL_NEGO_RATE", precision = 12, scale = 4)
	public BigDecimal getMinPerculNegoRate() {
		return minPerculNegoRate;
	}

	public void setMinPerculNegoRate(BigDecimal minPerculNegoRate) {
		this.minPerculNegoRate = minPerculNegoRate;
	}
	@Column(name = "MAX_PERCUL_NEGO_RATE", precision = 12, scale = 4)
	public BigDecimal getMaxPerculNegoRate() {
		return maxPerculNegoRate;
	}

	public void setMaxPerculNegoRate(BigDecimal maxPerculNegoRate) {
		this.maxPerculNegoRate = maxPerculNegoRate;
	}
	@Column(name = "MIN_PRE_REPAYMENT_RATE", precision = 12, scale = 4)
	public BigDecimal getMinPreRepaymentRate() {
		return minPreRepaymentRate;
	}

	public void setMinPreRepaymentRate(BigDecimal minPreRepaymentRate) {
		this.minPreRepaymentRate = minPreRepaymentRate;
	}
	@Column(name = "MAX_PRE_REPAYMENT_RATE", precision = 12, scale = 4)
	public BigDecimal getMaxPreRepaymentRate() {
		return maxPreRepaymentRate;
	}

	public void setMaxPreRepaymentRate(BigDecimal maxPreRepaymentRate) {
		this.maxPreRepaymentRate = maxPreRepaymentRate;
	}
	@Column(name = "REPAYING_MODE", length = 20)
	public String getRepayingMode() {
		return repayingMode;
	}

	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}
	@Column(name = "REPAY_PERIOD_NUM", precision = 22, scale = 0)
	public Integer getRepayPeriodNum() {
		return repayPeriodNum;
	}

	public void setRepayPeriodNum(Integer repayPeriodNum) {
		this.repayPeriodNum = repayPeriodNum;
	}
	@Column(name = "REPLY_PERIOD_UNIT", length = 20)
	public String getReplyPeriodUnit() {
		return replyPeriodUnit;
	}

	public void setReplyPeriodUnit(String replyPeriodUnit) {
		this.replyPeriodUnit = replyPeriodUnit;
	}
	@Column(name = "GUARANTEE_MODE", length = 20)
	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	@Column(name = "IS_BATCH", length = 20)
	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}
	@Column(name = "BATCH_LIMIT", precision = 12, scale = 2)
	public BigDecimal getBatchLimit() {
		return batchLimit;
	}

	public void setBatchLimit(BigDecimal batchLimit) {
		this.batchLimit = batchLimit;
	}

	@Column(name = "IS_START", length = 20)
	public String getIsStart() {
		return isStart;
	}

	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}
	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "REMARKS", length = 200)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "CUSTOMER_PROPERTY", length = 20)
	public String getCustomerProperty() {
		return customerProperty;
	}

	public void setCustomerProperty(String customerProperty) {
		this.customerProperty = customerProperty;
	}

}
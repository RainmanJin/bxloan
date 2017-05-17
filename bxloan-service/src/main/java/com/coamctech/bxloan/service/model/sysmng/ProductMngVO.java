package com.coamctech.bxloan.service.model.sysmng;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductMngVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//配置
	private Long pcId; //配置表主键
	private String wfCode;//流程类型代码
	private BigDecimal minApplyAmt;//最小申请金额
	private BigDecimal maxApplyAmt;//最大申请金额
	private Integer minimalTerm;//最小申请期限
	private Integer maximalTerm;//最大申请期限
	private String orgId;//机构号
	private Integer rateMultiple;//利率倍数
	private String customerType; //Customer类型
	private String loanTermMode; //贷款期限模式
	private Integer minLoanTerm; //最小贷款期限
	private Integer minLoanTerm1; //固定
	private Integer maxLoanTerm; //最小贷款期限
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
	private String productName; //产品名称
	private Long parentProductCd;//一级产品
	private String customerProperty;//产品性质
	
	

	public ProductMngVO() {
		super();
	}
	
	public Long getPcId() {
		return pcId;
	}

	public void setPcId(Long pcId) {
		this.pcId = pcId;
	}

	public String getWfCode() {
		return wfCode;
	}

	public void setWfCode(String wfCode) {
		this.wfCode = wfCode;
	}

	public BigDecimal getMinApplyAmt() {
		return minApplyAmt;
	}

	public void setMinApplyAmt(BigDecimal minApplyAmt) {
		this.minApplyAmt = minApplyAmt;
	}

	public BigDecimal getMaxApplyAmt() {
		return maxApplyAmt;
	}

	public void setMaxApplyAmt(BigDecimal maxApplyAmt) {
		this.maxApplyAmt = maxApplyAmt;
	}

	public Integer getMinimalTerm() {
		return minimalTerm;
	}

	public void setMinimalTerm(Integer minimalTerm) {
		this.minimalTerm = minimalTerm;
	}

	public Integer getMaximalTerm() {
		return maximalTerm;
	}

	public void setMaximalTerm(Integer maximalTerm) {
		this.maximalTerm = maximalTerm;
	}

	public Integer getRateMultiple() {
		return rateMultiple;
	}

	public void setRateMultiple(Integer rateMultiple) {
		this.rateMultiple = rateMultiple;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getLoanTermMode() {
		return loanTermMode;
	}

	public void setLoanTermMode(String loanTermMode) {
		this.loanTermMode = loanTermMode;
	}

	public Integer getMinLoanTerm() {
		return minLoanTerm;
	}

	public void setMinLoanTerm(Integer minLoanTerm) {
		this.minLoanTerm = minLoanTerm;
	}

	public Integer getMaxLoanTerm() {
		return maxLoanTerm;
	}

	public void setMaxLoanTerm(Integer maxLoanTerm) {
		this.maxLoanTerm = maxLoanTerm;
	}

	public String getSpecialLoanTerm() {
		return specialLoanTerm;
	}

	public void setSpecialLoanTerm(String specialLoanTerm) {
		this.specialLoanTerm = specialLoanTerm;
	}

	public BigDecimal getMinRate() {
		return minRate;
	}

	public void setMinRate(BigDecimal minRate) {
		this.minRate = minRate;
	}

	public BigDecimal getMaxRate() {
		return maxRate;
	}

	public void setMaxRate(BigDecimal maxRate) {
		this.maxRate = maxRate;
	}

	public BigDecimal getMinOverdueRate() {
		return minOverdueRate;
	}

	public void setMinOverdueRate(BigDecimal minOverdueRate) {
		this.minOverdueRate = minOverdueRate;
	}

	public BigDecimal getMaxOverdueRate() {
		return maxOverdueRate;
	}

	public void setMaxOverdueRate(BigDecimal maxOverdueRate) {
		this.maxOverdueRate = maxOverdueRate;
	}

	public String getRepayingMode() {
		return repayingMode;
	}

	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}

	public String getReplyPeriodUnit() {
		return replyPeriodUnit;
	}

	public void setReplyPeriodUnit(String replyPeriodUnit) {
		this.replyPeriodUnit = replyPeriodUnit;
	}

	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

	public BigDecimal getBatchLimit() {
		return batchLimit;
	}

	public void setBatchLimit(BigDecimal batchLimit) {
		this.batchLimit = batchLimit;
	}

	public String getIsStart() {
		return isStart;
	}

	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getProductCd() {
		return productCd;
	}

	public void setProductCd(Long productCd) {
		this.productCd = productCd;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getParentProductCd() {
		return parentProductCd;
	}

	public void setParentProductCd(Long parentProductCd) {
		this.parentProductCd = parentProductCd;
	}

	public BigDecimal getMinPerculNegoRate() {
		return minPerculNegoRate;
	}

	public void setMinPerculNegoRate(BigDecimal minPerculNegoRate) {
		this.minPerculNegoRate = minPerculNegoRate;
	}

	public BigDecimal getMaxPerculNegoRate() {
		return maxPerculNegoRate;
	}

	public void setMaxPerculNegoRate(BigDecimal maxPerculNegoRate) {
		this.maxPerculNegoRate = maxPerculNegoRate;
	}

	public BigDecimal getMinPreRepaymentRate() {
		return minPreRepaymentRate;
	}

	public void setMinPreRepaymentRate(BigDecimal minPreRepaymentRate) {
		this.minPreRepaymentRate = minPreRepaymentRate;
	}

	public BigDecimal getMaxPreRepaymentRate() {
		return maxPreRepaymentRate;
	}

	public void setMaxPreRepaymentRate(BigDecimal maxPreRepaymentRate) {
		this.maxPreRepaymentRate = maxPreRepaymentRate;
	}

	public Integer getRepayPeriodNum() {
		return repayPeriodNum;
	}

	public void setRepayPeriodNum(Integer repayPeriodNum) {
		this.repayPeriodNum = repayPeriodNum;
	}

	public Integer getMinLoanTerm1() {
		return minLoanTerm1;
	}

	public void setMinLoanTerm1(Integer minLoanTerm1) {
		this.minLoanTerm1 = minLoanTerm1;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCustomerProperty() {
		return customerProperty;
	}

	public void setCustomerProperty(String customerProperty) {
		this.customerProperty = customerProperty;
	}
}

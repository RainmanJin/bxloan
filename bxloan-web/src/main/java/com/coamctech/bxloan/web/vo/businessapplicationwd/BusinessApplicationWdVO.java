package com.coamctech.bxloan.web.vo.businessapplicationwd;

import java.math.BigDecimal;

public class BusinessApplicationWdVO {
	private Long projectId;// ID
	private String customerName;// 客户名称
	private Long partyId;// 客户ID
	private String marriageCd;// 婚姻状况
	private String degreeCd;// 最高学历
	private String industryCd;// 所属行业
	private String familyMonIncome;// 家庭实际月收入（元）
	private String employmentType;// 雇佣类型
	private BigDecimal maintainPersonNum;// 供养人数
	private String applyDate;// 申报日期
	private String projectNo;// 业务编号
	private String productType;// 贷款产品
	private BigDecimal applyAmt;// 申报金额
	private BigDecimal renewalTerm;// 贷款期限
	private String renewalTermUnit;// 期限单位
	private String repayingMode;// 还款方式
	private BigDecimal replyPeriodNum;// 还款周期月数
	private String agricultureInd;// 是否涉农
	// private String currency;// 币种
	private String guaranteeMode;// 担保方式
	private String investmentIndustry;// 投放行业
	private String mateBorrower;// 配偶是否作为共同借款人
	private BigDecimal preRepaymentRate;// 提前还款违约比例（%）
	private String purpose;// 贷款用途详细描述
	private String payment;// 还款来源
	private BigDecimal bizRate;// 年利率
	private BigDecimal ovdueRate;// 逾期利率上浮比例（%）
	private BigDecimal perculNegoRate;// 挪用利率上浮比例（%）
	private Long workflowId;
	private String taskId;

	/**
	 * 利率表
	 */
	private Long bizRateId;
	private String productCd;
	private String irGistStyleCd;// 利率类型
	private String adjustPeriod;// 调整周期
	private BigDecimal floatRate;// 利率上浮比例（%）

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getMarriageCd() {
		return marriageCd;
	}

	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

	public String getDegreeCd() {
		return degreeCd;
	}

	public void setDegreeCd(String degreeCd) {
		this.degreeCd = degreeCd;
	}

	public String getIndustryCd() {
		return industryCd;
	}

	public void setIndustryCd(String industryCd) {
		this.industryCd = industryCd;
	}

	public String getFamilyMonIncome() {
		return familyMonIncome;
	}

	public void setFamilyMonIncome(String familyMonIncome) {
		this.familyMonIncome = familyMonIncome;
	}
	
	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public BigDecimal getMaintainPersonNum() {
		return maintainPersonNum;
	}

	public void setMaintainPersonNum(BigDecimal maintainPersonNum) {
		this.maintainPersonNum = maintainPersonNum;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getApplyAmt() {
		return applyAmt;
	}

	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}

	public BigDecimal getRenewalTerm() {
		return renewalTerm;
	}

	public void setRenewalTerm(BigDecimal renewalTerm) {
		this.renewalTerm = renewalTerm;
	}

	public String getRenewalTermUnit() {
		return renewalTermUnit;
	}

	public void setRenewalTermUnit(String renewalTermUnit) {
		this.renewalTermUnit = renewalTermUnit;
	}

	public String getRepayingMode() {
		return repayingMode;
	}

	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}

	public BigDecimal getReplyPeriodNum() {
		return replyPeriodNum;
	}

	public void setReplyPeriodNum(BigDecimal replyPeriodNum) {
		this.replyPeriodNum = replyPeriodNum;
	}

	public String getAgricultureInd() {
		return agricultureInd;
	}

	public void setAgricultureInd(String agricultureInd) {
		this.agricultureInd = agricultureInd;
	}

	// public String getCurrency() {
	// return currency;
	// }
	//
	// public void setCurrency(String currency) {
	// this.currency = currency;
	// }

	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	public String getInvestmentIndustry() {
		return investmentIndustry;
	}

	public void setInvestmentIndustry(String investmentIndustry) {
		this.investmentIndustry = investmentIndustry;
	}

	public String getMateBorrower() {
		return mateBorrower;
	}

	public void setMateBorrower(String mateBorrower) {
		this.mateBorrower = mateBorrower;
	}

	public BigDecimal getPreRepaymentRate() {
		return preRepaymentRate;
	}

	public void setPreRepaymentRate(BigDecimal preRepaymentRate) {
		this.preRepaymentRate = preRepaymentRate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getIrGistStyleCd() {
		return irGistStyleCd;
	}

	public void setIrGistStyleCd(String irGistStyleCd) {
		this.irGistStyleCd = irGistStyleCd;
	}

	public BigDecimal getBizRate() {
		return bizRate;
	}

	public void setBizRate(BigDecimal bizRate) {
		this.bizRate = bizRate;
	}

	public String getAdjustPeriod() {
		return adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	public BigDecimal getFloatRate() {
		return floatRate;
	}

	public void setFloatRate(BigDecimal floatRate) {
		this.floatRate = floatRate;
	}

	public BigDecimal getOvdueRate() {
		return ovdueRate;
	}

	public void setOvdueRate(BigDecimal ovdueRate) {
		this.ovdueRate = ovdueRate;
	}

	public BigDecimal getPerculNegoRate() {
		return perculNegoRate;
	}

	public void setPerculNegoRate(BigDecimal perculNegoRate) {
		this.perculNegoRate = perculNegoRate;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Long getBizRateId() {
		return bizRateId;
	}

	public void setBizRateId(Long bizRateId) {
		this.bizRateId = bizRateId;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
}

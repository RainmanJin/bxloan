package com.coamctech.bxloan.web.vo.contractmng;

import java.math.BigDecimal;

public class ContractVO {
	//合同信息
	private Long contractId; //主键 合同Id
	private Long projectId;
	private String projectNo;//业务编号
	private String contractNum;
	private String customerNum;
	private String customerName;
	private String customerType;
	private BigDecimal contractAmt; //申请金额（元）
	private String productType;//贷款产品
	private String repayModeCd;//还款方式
	private Integer repayPrincipalMonthes;//还本周期月数
	private String investmentIndustry;//投放行业-from业务表
	private String payment;//还款来源-from业务表
	private String loanPurposeFeature;//贷款用途特征
	private String contractTerm; //期限
	private String fulfillInstructionCd;//落实情况意见
	private String bankName;
	private String loanNum;
	private String applyUserNum;
	private String purpose;//贷款用途-from业务表
	private String loanDateStyle;//约定方式
	private String arrangeRepayDay;//约定还款日
	private String contractRate; //最终利率
	private String contractTermUnit;//期限单位
	//批复利率
	private String approveIrTypeCd;//批复利率类型 from利率表
	private String approveAdjustPeriod;//调整方式
	private BigDecimal approveFloatRate;//上浮比例
	private BigDecimal approveRateValue;//批复利率
	//最终利率
	private BigDecimal finalRateValue;//最终年利率值
	private String finalIrTypeCd;
	private String finalAdjustPeriod;
	private BigDecimal finalFloatRate;
	private String rateAdjustmentReason;//利率调整原因
	private String loanPurposeKind;
	//逾期利率
	private BigDecimal ovdueIrNegoRate; //逾期利率上浮比例
	private BigDecimal perculIrNegoRate; //挪用利率上浮比例
	
	private String guaranteeMode; //担保方式
	
	public ContractVO() {
		super();
	}

	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public BigDecimal getContractAmt() {
		return contractAmt;
	}
	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getRepayModeCd() {
		return repayModeCd;
	}
	public void setRepayModeCd(String repayModeCd) {
		this.repayModeCd = repayModeCd;
	}
	public Integer getRepayPrincipalMonthes() {
		return repayPrincipalMonthes;
	}
	public void setRepayPrincipalMonthes(Integer repayPrincipalMonthes) {
		this.repayPrincipalMonthes = repayPrincipalMonthes;
	}
	public String getInvestmentIndustry() {
		return investmentIndustry;
	}
	public void setInvestmentIndustry(String investmentIndustry) {
		this.investmentIndustry = investmentIndustry;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getLoanPurposeFeature() {
		return loanPurposeFeature;
	}
	public void setLoanPurposeFeature(String loanPurposeFeature) {
		this.loanPurposeFeature = loanPurposeFeature;
	}
	public String getContractTerm() {
		return contractTerm;
	}
	public void setContractTerm(String contractTerm) {
		this.contractTerm = contractTerm;
	}
	public String getFulfillInstructionCd() {
		return fulfillInstructionCd;
	}
	public void setFulfillInstructionCd(String fulfillInstructionCd) {
		this.fulfillInstructionCd = fulfillInstructionCd;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getLoanNum() {
		return loanNum;
	}
	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}
	public String getApplyUserNum() {
		return applyUserNum;
	}
	public void setApplyUserNum(String applyUserNum) {
		this.applyUserNum = applyUserNum;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getLoanDateStyle() {
		return loanDateStyle;
	}
	public void setLoanDateStyle(String loanDateStyle) {
		this.loanDateStyle = loanDateStyle;
	}
	public String getArrangeRepayDay() {
		return arrangeRepayDay;
	}
	public void setArrangeRepayDay(String arrangeRepayDay) {
		this.arrangeRepayDay = arrangeRepayDay;
	}
	public String getContractRate() {
		return contractRate;
	}
	public void setContractRate(String contractRate) {
		this.contractRate = contractRate;
	}
	public String getContractTermUnit() {
		return contractTermUnit;
	}
	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}
	public String getApproveIrTypeCd() {
		return approveIrTypeCd;
	}
	public void setApproveIrTypeCd(String approveIrTypeCd) {
		this.approveIrTypeCd = approveIrTypeCd;
	}
	public String getApproveAdjustPeriod() {
		return approveAdjustPeriod;
	}
	public void setApproveAdjustPeriod(String approveAdjustPeriod) {
		this.approveAdjustPeriod = approveAdjustPeriod;
	}
	public BigDecimal getApproveFloatRate() {
		return approveFloatRate;
	}
	public void setApproveFloatRate(BigDecimal approveFloatRate) {
		this.approveFloatRate = approveFloatRate;
	}
	public BigDecimal getApproveRateValue() {
		return approveRateValue;
	}
	public void setApproveRateValue(BigDecimal approveRateValue) {
		this.approveRateValue = approveRateValue;
	}
	public BigDecimal getFinalRateValue() {
		return finalRateValue;
	}
	public void setFinalRateValue(BigDecimal finalRateValue) {
		this.finalRateValue = finalRateValue;
	}
	public String getFinalIrTypeCd() {
		return finalIrTypeCd;
	}
	public void setFinalIrTypeCd(String finalIrTypeCd) {
		this.finalIrTypeCd = finalIrTypeCd;
	}
	public String getFinalAdjustPeriod() {
		return finalAdjustPeriod;
	}
	public void setFinalAdjustPeriod(String finalAdjustPeriod) {
		this.finalAdjustPeriod = finalAdjustPeriod;
	}
	public BigDecimal getFinalFloatRate() {
		return finalFloatRate;
	}
	public void setFinalFloatRate(BigDecimal finalFloatRate) {
		this.finalFloatRate = finalFloatRate;
	}
	public String getRateAdjustmentReason() {
		return rateAdjustmentReason;
	}
	public void setRateAdjustmentReason(String rateAdjustmentReason) {
		this.rateAdjustmentReason = rateAdjustmentReason;
	}
	public String getLoanPurposeKind() {
		return loanPurposeKind;
	}
	public void setLoanPurposeKind(String loanPurposeKind) {
		this.loanPurposeKind = loanPurposeKind;
	}
	public String getGuaranteeMode() {
		return guaranteeMode;
	}
	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}
	public BigDecimal getOvdueIrNegoRate() {
		return ovdueIrNegoRate;
	}
	public void setOvdueIrNegoRate(BigDecimal ovdueIrNegoRate) {
		this.ovdueIrNegoRate = ovdueIrNegoRate;
	}
	public BigDecimal getPerculIrNegoRate() {
		return perculIrNegoRate;
	}
	public void setPerculIrNegoRate(BigDecimal perculIrNegoRate) {
		this.perculIrNegoRate = perculIrNegoRate;
	}
}

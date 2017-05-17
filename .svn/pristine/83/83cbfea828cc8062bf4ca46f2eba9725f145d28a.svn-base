package com.coamctech.bxloan.service.model.credit;

import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;
/**   
 * 类名称：CreditContractVo
 * 类描述 ：授信合同VO
 * 创建人: gph 
 * 创建时间：2015年5月14日 上午11:28:03  
 * 修改人：gph
 * 修改时间：2015年5月14日 上午11:28:03  
 * 修改备注：
 * 版本： V1.0
 */
public class CreditContractVo {

	/** 授信合同编号，主键 */
	private Long creditContractId;

	/** 项目Id */
	private Long projectId;

	/** 项目编号 */
	private String projectNo;

	/** 合同编号 */
	private String contractNum;

	/** 客户编号 */
	private String customerNum;

	/** 客户名称 */
	private String customerName;

	/** 授信合同金额 */
	private BigDecimal contractAmt;

	/** 授信合同期限 */
	private Integer contractTerm;

	/** 申请人编号 */
	private String applyUserNum;

	/** 申请人所在机构 */
	private Long applyOrgId;

	/** 合同条件落实情况说明 */
	private String fulfillInstructionCd;

	/** 创建时间 */
	private Date sysCreateDate;

	/** 更新时间 */
	private Date sysUpdateDate;

	/** 授信合同状态 1：未生效，2：生效 */
	private String contractStatusCd;

	/** 客户Id */
	private Long partyId;

	/** 授信合同期限单位 */
	private String contractTermUnit;

	/** 币种 */
	private String currency;

	/** 提前还款违约比例 */
	private BigDecimal preRepaymentRate;

	/** 贷款产品 */
	private String productType;
	
	/** 产品名称 */
	private String productName;

	/** 落实放款条件 */
	private String fulfillLoanCondition;

	/** 相关手续类型 */
	private String documentType;

	/** 授信类型 */
	private String creditType;

	/** 授信累计借款金额 */
	private BigDecimal creditLoanAmt;

	/** 授信剩余可用金额 */
	private BigDecimal creditAvaiableAmt;

	/** 授信还款金额 */
	private BigDecimal creditRepayment;

	/** 申报日期 */
	private Date applyDate;

	/** 授信合同利率 */
	private BigDecimal bizRate;

	/** 合同顺序 */
	private Long contractIndex;
	
	/** 利率类型 */
	private String finalIrTypeCd;
	
	/** 调整周期 */
	private String finalAdjustPeriod;
	
	/** 年利率 */
	private BigDecimal finalRateValue;
	
	/** 利率上浮比例 */
	private BigDecimal finalFloatRate;
	
	/** 客户类型codeType=CustomerType */
	private String customerType;
	
	/** 授信合同期限字符串=授信合同期限+授信合同期限单位 */
	private String creditContractTerm;
	
	/** 担保方式 */
	private String guaranteeMode;
	
	/** 投放行业 */
	private String investmentIndustry;
	
	/** 贷款用途 */
	private String purpose;
	
	public CreditContractVo() {}
	public CreditContractVo(Object[] objs){
		super();
		int i = 0;
		this.creditContractId = CommonHelper.toLong(objs[i++]);
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.customerName = CommonHelper.toStr(objs[i++]);
		this.customerNum = CommonHelper.toStr(objs[i++]);
		this.productName = CommonHelper.toStr(objs[i++]);
		this.contractAmt = CommonHelper.toBigDecimal(objs[i++]);
		this.contractTerm = CommonHelper.toInt(objs[i++]);
		this.contractTermUnit = CommonHelper.toStr(objs[i++]);
		this.bizRate = CommonHelper.toBigDecimal(objs[i++]);
		this.creditType = CommonHelper.toStr(objs[i++]);
		this.contractStatusCd = CommonHelper.toStr(objs[i++]);
		this.partyId = CommonHelper.toLong(objs[i++]);
	}

	public Long getCreditContractId() {
		return creditContractId;
	}

	public void setCreditContractId(Long creditContractId) {
		this.creditContractId = creditContractId;
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

	public BigDecimal getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}

	public Integer getContractTerm() {
		return contractTerm;
	}

	public void setContractTerm(Integer contractTerm) {
		this.contractTerm = contractTerm;
	}

	public String getApplyUserNum() {
		return applyUserNum;
	}

	public void setApplyUserNum(String applyUserNum) {
		this.applyUserNum = applyUserNum;
	}

	public Long getApplyOrgId() {
		return applyOrgId;
	}

	public void setApplyOrgId(Long applyOrgId) {
		this.applyOrgId = applyOrgId;
	}

	public String getFulfillInstructionCd() {
		return fulfillInstructionCd;
	}

	public void setFulfillInstructionCd(String fulfillInstructionCd) {
		this.fulfillInstructionCd = fulfillInstructionCd;
	}

	public Date getSysCreateDate() {
		return sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	public Date getSysUpdateDate() {
		return sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	public String getContractStatusCd() {
		return contractStatusCd;
	}

	public void setContractStatusCd(String contractStatusCd) {
		this.contractStatusCd = contractStatusCd;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getContractTermUnit() {
		return contractTermUnit;
	}

	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getPreRepaymentRate() {
		return preRepaymentRate;
	}

	public void setPreRepaymentRate(BigDecimal preRepaymentRate) {
		this.preRepaymentRate = preRepaymentRate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getFulfillLoanCondition() {
		return fulfillLoanCondition;
	}

	public void setFulfillLoanCondition(String fulfillLoanCondition) {
		this.fulfillLoanCondition = fulfillLoanCondition;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public BigDecimal getCreditLoanAmt() {
		return creditLoanAmt;
	}

	public void setCreditLoanAmt(BigDecimal creditLoanAmt) {
		this.creditLoanAmt = creditLoanAmt;
	}

	public BigDecimal getCreditAvaiableAmt() {
		return creditAvaiableAmt;
	}

	public void setCreditAvaiableAmt(BigDecimal creditAvaiableAmt) {
		this.creditAvaiableAmt = creditAvaiableAmt;
	}

	public BigDecimal getCreditRepayment() {
		return creditRepayment;
	}

	public void setCreditRepayment(BigDecimal creditRepayment) {
		this.creditRepayment = creditRepayment;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public BigDecimal getBizRate() {
		return bizRate;
	}

	public void setBizRate(BigDecimal bizRate) {
		this.bizRate = bizRate;
	}

	public Long getContractIndex() {
		return contractIndex;
	}

	public void setContractIndex(Long contractIndex) {
		this.contractIndex = contractIndex;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
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
	
	public BigDecimal getFinalRateValue() {
		return finalRateValue;
	}
	
	public void setFinalRateValue(BigDecimal finalRateValue) {
		this.finalRateValue = finalRateValue;
	}
	
	public BigDecimal getFinalFloatRate() {
		return finalFloatRate;
	}
	
	public void setFinalFloatRate(BigDecimal finalFloatRate) {
		this.finalFloatRate = finalFloatRate;
	}
	
	public String getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	
	public String getCreditContractTerm() {
		return creditContractTerm;
	}
	
	public void setCreditContractTerm(String creditContractTerm) {
		this.creditContractTerm = creditContractTerm;
	}
	
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
	
	public String getPurpose() {
		return purpose;
	}
	
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
}

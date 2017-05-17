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

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 放款
 */
@Entity
@Table(name = "PAY_LOAN_INFO", schema = GlobalConstants.WD_SCHEMA)
public class PayLoanInfo implements java.io.Serializable {

	private Long payLoanId;//贷款发放信息ID
	private String payLoanNum;//放款编号
	private String productType;
	private String customerNum;
	private String customerName;
	private String contractNum;
	private String currency;
	private BigDecimal loanAmt;//放款金额
	private Date loanRegistTime;//申请放款日期
	private Date arriveDate;
	private BigDecimal contractRate;
	private String bankName;
	private String loanNum;
	private Long applyOrgId;
	private String applyUserNum;
	private String remark;
	private BigDecimal loanResAmt;
	private Date loanActulTime;//实际放款日期
	private BigDecimal loanAmtSum;
	private BigDecimal contractAmt;
	private Long partyId;
	private Long contractId;
	private Date createDate;
	private Date sysUpdateDate;
	private BigDecimal sumAmt;//手续费及佣金收入
	private String payStatusCd;
	private String transactionNo;
	private BigDecimal overduerate;
	private String loanEvidenceNum;
	private Date fillInDate;
	private String manyPayStatus;
	private Long payLoanIndex;
	private String isUpload;
	private String ifInsure;//是否有保险
	private String insuranceOrgId;//保险机构ID
	private BigDecimal autualPremium;//实收保费
	private String insuranceRemark;
	private BigDecimal loanPremium;//预收保费

	// Constructors

	/** default constructor */
	public PayLoanInfo() {
	}

	/** minimal constructor */
	public PayLoanInfo(Long payLoanId) {
		this.payLoanId = payLoanId;
	}

	/** full constructor */
	public PayLoanInfo(Long payLoanId, String payLoanNum, String productType,
			String customerNum, String customerName, String contractNum,
			String currency, BigDecimal loanAmt, Date loanRegistTime,
			Date arriveDate, BigDecimal contractRate, String bankName,
			String loanNum, Long applyOrgId, String applyUserNum,
			String remark, BigDecimal loanResAmt, Date loanActulTime,
			BigDecimal loanAmtSum, BigDecimal contractAmt, Long partyId,
			Long contractId, Date createDate, Date sysUpdateDate,
			BigDecimal sumAmt, String payStatusCd, String transactionNo,
			BigDecimal overduerate, String loanEvidenceNum, Date fillInDate,
			String manyPayStatus, Long payLoanIndex, String isUpload,
			String ifInsure, String insuranceOrgId, BigDecimal autualPremium,
			String insuranceRemark, BigDecimal loanPremium) {
		this.payLoanId = payLoanId;
		this.payLoanNum = payLoanNum;
		this.productType = productType;
		this.customerNum = customerNum;
		this.customerName = customerName;
		this.contractNum = contractNum;
		this.currency = currency;
		this.loanAmt = loanAmt;
		this.loanRegistTime = loanRegistTime;
		this.arriveDate = arriveDate;
		this.contractRate = contractRate;
		this.bankName = bankName;
		this.loanNum = loanNum;
		this.applyOrgId = applyOrgId;
		this.applyUserNum = applyUserNum;
		this.remark = remark;
		this.loanResAmt = loanResAmt;
		this.loanActulTime = loanActulTime;
		this.loanAmtSum = loanAmtSum;
		this.contractAmt = contractAmt;
		this.partyId = partyId;
		this.contractId = contractId;
		this.createDate = createDate;
		this.sysUpdateDate = sysUpdateDate;
		this.sumAmt = sumAmt;
		this.payStatusCd = payStatusCd;
		this.transactionNo = transactionNo;
		this.overduerate = overduerate;
		this.loanEvidenceNum = loanEvidenceNum;
		this.fillInDate = fillInDate;
		this.manyPayStatus = manyPayStatus;
		this.payLoanIndex = payLoanIndex;
		this.isUpload = isUpload;
		this.ifInsure = ifInsure;
		this.insuranceOrgId = insuranceOrgId;
		this.autualPremium = autualPremium;
		this.insuranceRemark = insuranceRemark;
		this.loanPremium = loanPremium;
	}

	// Property accessors
	
	@SequenceGenerator(name = "generator", sequenceName="SEQ_PAY_LOAN_INFO", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "PAY_LOAN_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getPayLoanId() {
		return this.payLoanId;
	}

	public void setPayLoanId(Long payLoanId) {
		this.payLoanId = payLoanId;
	}

	@Column(name = "PAY_LOAN_NUM", length = 30)
	public String getPayLoanNum() {
		return this.payLoanNum;
	}

	public void setPayLoanNum(String payLoanNum) {
		this.payLoanNum = payLoanNum;
	}

	@Column(name = "PRODUCT_TYPE", length = 30)
	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
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

	@Column(name = "CONTRACT_NUM", length = 30)
	public String getContractNum() {
		return this.contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	@Column(name = "CURRENCY", length = 30)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "LOAN_AMT", precision = 20)
	public BigDecimal getLoanAmt() {
		return this.loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	@Column(name = "LOAN_REGIST_TIME", length = 7)
	public Date getLoanRegistTime() {
		return this.loanRegistTime;
	}

	public void setLoanRegistTime(Date loanRegistTime) {
		this.loanRegistTime = loanRegistTime;
	}

	@Column(name = "ARRIVE_DATE", length = 7)
	public Date getArriveDate() {
		return this.arriveDate;
	}

	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	@Column(name = "CONTRACT_RATE", precision = 20, scale = 6)
	public BigDecimal getContractRate() {
		return this.contractRate;
	}

	public void setContractRate(BigDecimal contractRate) {
		this.contractRate = contractRate;
	}

	@Column(name = "BANK_NAME", length = 100)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "LOAN_NUM", length = 30)
	public String getLoanNum() {
		return this.loanNum;
	}

	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}

	@Column(name = "APPLY_ORG_ID", precision = 12, scale = 0)
	public Long getApplyOrgId() {
		return this.applyOrgId;
	}

	public void setApplyOrgId(Long applyOrgId) {
		this.applyOrgId = applyOrgId;
	}

	@Column(name = "APPLY_USER_NUM", length = 30)
	public String getApplyUserNum() {
		return this.applyUserNum;
	}

	public void setApplyUserNum(String applyUserNum) {
		this.applyUserNum = applyUserNum;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "LOAN_RES_AMT", precision = 20)
	public BigDecimal getLoanResAmt() {
		return this.loanResAmt;
	}

	public void setLoanResAmt(BigDecimal loanResAmt) {
		this.loanResAmt = loanResAmt;
	}

	@Column(name = "LOAN_ACTUL_TIME", length = 7)
	public Date getLoanActulTime() {
		return this.loanActulTime;
	}

	public void setLoanActulTime(Date loanActulTime) {
		this.loanActulTime = loanActulTime;
	}

	@Column(name = "LOAN_AMT_SUM", precision = 20)
	public BigDecimal getLoanAmtSum() {
		return this.loanAmtSum;
	}

	public void setLoanAmtSum(BigDecimal loanAmtSum) {
		this.loanAmtSum = loanAmtSum;
	}

	@Column(name = "CONTRACT_AMT", precision = 20)
	public BigDecimal getContractAmt() {
		return this.contractAmt;
	}

	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CONTRACT_ID", precision = 12, scale = 0)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "SYS_UPDATE_DATE", length = 7)
	public Date getSysUpdateDate() {
		return this.sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	@Column(name = "SUM_AMT", precision = 20)
	public BigDecimal getSumAmt() {
		return this.sumAmt;
	}

	public void setSumAmt(BigDecimal sumAmt) {
		this.sumAmt = sumAmt;
	}

	@Column(name = "PAY_STATUS_CD", length = 20)
	public String getPayStatusCd() {
		return this.payStatusCd;
	}

	public void setPayStatusCd(String payStatusCd) {
		this.payStatusCd = payStatusCd;
	}

	@Column(name = "TRANSACTION_NO", length = 100)
	public String getTransactionNo() {
		return this.transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	@Column(name = "OVERDUERATE", precision = 20, scale = 6)
	public BigDecimal getOverduerate() {
		return this.overduerate;
	}

	public void setOverduerate(BigDecimal overduerate) {
		this.overduerate = overduerate;
	}

	@Column(name = "LOAN_EVIDENCE_NUM", length = 30)
	public String getLoanEvidenceNum() {
		return this.loanEvidenceNum;
	}

	public void setLoanEvidenceNum(String loanEvidenceNum) {
		this.loanEvidenceNum = loanEvidenceNum;
	}

	@Column(name = "FILL_IN_DATE", length = 7)
	public Date getFillInDate() {
		return this.fillInDate;
	}

	public void setFillInDate(Date fillInDate) {
		this.fillInDate = fillInDate;
	}

	@Column(name = "MANY_PAY_STATUS", length = 1)
	public String getManyPayStatus() {
		return this.manyPayStatus;
	}

	public void setManyPayStatus(String manyPayStatus) {
		this.manyPayStatus = manyPayStatus;
	}

	@Column(name = "PAY_LOAN_INDEX", precision = 12, scale = 0)
	public Long getPayLoanIndex() {
		return this.payLoanIndex;
	}

	public void setPayLoanIndex(Long payLoanIndex) {
		this.payLoanIndex = payLoanIndex;
	}

	@Column(name = "IS_UPLOAD", length = 1)
	public String getIsUpload() {
		return this.isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	@Column(name = "IF_INSURE", length = 1)
	public String getIfInsure() {
		return this.ifInsure;
	}

	public void setIfInsure(String ifInsure) {
		this.ifInsure = ifInsure;
	}

	@Column(name = "INSURANCE_ORG_ID", length = 30)
	public String getInsuranceOrgId() {
		return this.insuranceOrgId;
	}

	public void setInsuranceOrgId(String insuranceOrgId) {
		this.insuranceOrgId = insuranceOrgId;
	}

	@Column(name = "AUTUAL_PREMIUM", precision = 20)
	public BigDecimal getAutualPremium() {
		return this.autualPremium;
	}

	public void setAutualPremium(BigDecimal autualPremium) {
		this.autualPremium = autualPremium;
	}

	@Column(name = "INSURANCE_REMARK", length = 500)
	public String getInsuranceRemark() {
		return this.insuranceRemark;
	}

	public void setInsuranceRemark(String insuranceRemark) {
		this.insuranceRemark = insuranceRemark;
	}

	@Column(name = "LOAN_PREMIUM", precision = 20)
	public BigDecimal getLoanPremium() {
		return this.loanPremium;
	}
	
	public void setLoanPremium(BigDecimal loanPremium) {
		this.loanPremium = loanPremium;
	}


	
}
package com.coamctech.bxloan.entity.credit;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.entity.BaseEntity;

/**
 * 类名称：CreditContract 类描述 ： 创建人: gph 创建时间：2015年5月13日 上午10:54:52 修改人：gph
 * 修改时间：2015年5月13日 上午10:54:52 修改备注： 版本： V1.0
 */
@Entity
@Table(name = "CREDIT_CONTRACT", schema = WD_SCHEMA)
public class CreditContract extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 5520899086827930522L;

	/** 授信合同编号，主键 */
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_CONTRACT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "CREDIT_CONTRACT_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long creditContractId;

	/** 项目Id */
	@Column(name = "PROJECT_ID", nullable = false, precision = 12, scale = 0)
	private Long projectId;

	/** 项目编号 */
	@Column(name = "PROJECT_NO", length = 40)
	private String projectNo;

	/** 合同编号 */
	@Column(name = "CONTRACT_NUM", length = 30)
	private String contractNum;

	/** 客户编号 */
	@Column(name = "CUSTOMER_NUM", length = 30)
	private String customerNum;

	/** 客户名称 */
	@Column(name = "CUSTOMER_NAME", length = 100)
	private String customerName;

	/** 授信合同金额 */
	@Column(name = "CONTRACT_AMT", precision = 20)
	private BigDecimal contractAmt;

	/** 授信合同期限 */
	@Column(name = "CONTRACT_TERM", length = 6)
	private Integer contractTerm;

	/** 申请人编号 */
	@Column(name = "APPLY_USER_NUM", length = 20)
	private String applyUserNum;

	/** 申请人所在机构 */
	@Column(name = "APPLY_ORG_ID", length = 12)
	private Long applyOrgId;

	/** 合同条件落实情况说明 */
	@Column(name = "FULFILL_INSTRUCTION_CD", length = 4000)
	private String fulfillInstructionCd;

	/** 创建时间 */
	@Column(name = "SYS_CREATE_DATE")
	private Date sysCreateDate;

	/** 更新时间 */
	@Column(name = "SYS_UPDATE_DATE")
	private Date sysUpdateDate;

	/** 授信合同状态 1：未生效，2：生效 */
	@Column(name = "CONTRACT_STATUS_CD", length = 20)
	private String contractStatusCd;

	/** 客户Id */
	@Column(name = "PARTY_ID", length = 12)
	private Long partyId;

	/** 授信合同期限单位 */
	@Column(name = "CONTRACT_TERM_UNIT", length = 20)
	private String contractTermUnit;

	/** 币种 */
	@Column(name = "CURRENCY", length = 30)
	private String currency;

	/** 提前还款违约比例 */
	@Column(name = "PRE_REPAYMENT_RATE", precision = 20)
	private BigDecimal preRepaymentRate;

	/** 贷款产品 */
	@Column(name = "PRODUCT_TYPE", length = 30)
	private String productType;

	/** 落实放款条件 */
	@Column(name = "FULFIL_LOAN_CONDITION", length = 4000)
	private String fulfillLoanCondition;

	/** 相关手续类型 */
	@Column(name = "DOCUMENT_TYPE", length = 20)
	private String documentType;

	/** 授信类型 */
	@Column(name = "CREDIT_TYPE", length = 20)
	private String creditType;

	/** 授信累计借款金额 */
	@Column(name = "CREDIT_LOAN_AMT", precision = 20)
	private BigDecimal creditLoanAmt;

	/** 授信剩余可用金额 */
	@Column(name = "CREDIT_AVAIABLE_AMT", precision = 20)
	private BigDecimal creditAvaiableAmt;

	/** 授信还款金额 */
	@Column(name = "CREDIT_REPAYMENT", precision = 20)
	private BigDecimal creditRepayment;

	/** 申报日期 */
	@Column(name = "APPLY_DATE")
	private Date applyDate;

	/** 授信合同利率 */
	@Column(name = "BIZ_RATE", precision = 20)
	private BigDecimal bizRate;

	/** 合同顺序 */
	@Column(name = "CONTRACT_INDEX", length = 20)
	private Long contractIndex;
	
	public CreditContract() {
		super();
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
	
}

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
 * Contract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTRACT", schema = WD_SCHEMA)
public class Contract implements java.io.Serializable {

	// Fields

	private Long contractId; //主键 合同Id
	private Long projectId;//业务Id
	private String projectNo;//业务编号
	private String contractNum;//合同编号
	private String customerNum;//客户编号
	private String customerName;//客户名称
	private String customerType;//客户类型
	private BigDecimal contractAmt; //申请金额（元）
	private Integer contractTerm; //合同期限
	private Date startDate;//合同起始日
	private Date expirationDate;//合同到期日
	private String repayModeCd;//*还款方式
	private Integer repayPrincipalMonthes;//还本周期月数
	private String bankName;//开户行名称
	private String contractNatureCd;//合同性质
	private String loanNum;//贷款帐号
	private String applyUserNum;//申请人编号
	private Long applyOrgId;//申请人机构
	private Long applyDeptId;//申请人部门
	private String fulfillInstructionCd;//合同条件落实情况说明
	private Date sysCreateDate;
	private Date sysUpdateDate;
	private String contractStatusCd;
	private String processStatusCd;
	private Long partyId;
	private BigDecimal contractBalance;//合同余额
	private BigDecimal contractAvailableAmt;//合同可用金额
	private BigDecimal cumulativePayoutAmt;//合同累计发放金额
	private BigDecimal cumulativeRepayAmt;//合同累计还款金额
	private Date lastRepayDate;
	private Long bizContractRelId;
	private String contractTermUnit;
	private String checkStatus;
	private String whetherAgreedLoan;
	private String explanationLoan;
	private String whetherAgreedContract;
	private String explanationContract;
	private String instructionsSuggestions;
	private BigDecimal contractOvdueRate;
	private BigDecimal contractRate; //利率（%）
	private Date arrangeRepayDate;
	private Date firstOvdueDate;
	private Date lastOvdueDate;
	private Long ovdueTime;
	private BigDecimal ovdueAmt;
	private Integer arrangeRepayDay;//约定还款日
	private String currency;
	private BigDecimal preRepaymentRate;
	private String productType;
	private BigDecimal ovdueInterest;
	private Long ovdueDays;
	private Date checkTime;
	private String fiveclassification;
	private String documentType;
	private String fulfilLoanCondition;
	private Date payloanDate;
	private BigDecimal interestRate;
	private Date lastUploadTime;
	private BigDecimal freeamtcnt;
	private BigDecimal freepayloanamtcnt;
	private String ifReleaseFlag;
	private Long initContractId;
	private Integer contractTermTotal;
	private String contractTermUnitTotal;
	private Long contractIndex;
	private String loanDateStyle;
	private Long parentContractId;
	private String contracValidState;
	private String fpoolFlag;
	private Long fpoolProjectId;
	private String txno;
	private String quotaContractAuditStatus;
	private Long creditContractId;//授信合同ID
	// Constructors

	/** default constructor */
	public Contract() {
	}

	/** minimal constructor */
	public Contract(Long contractId, Long projectId) {
		this.contractId = contractId;
		this.projectId = projectId;
	}

	/** full constructor */
	public Contract(Long contractId, Long projectId, String projectNo,
			String contractNum, String customerNum, String customerName,String customerType,
			BigDecimal contractAmt, Integer contractTerm, Date startDate,
			Date expirationDate, String repayModeCd,
			Integer repayPrincipalMonthes, String bankName,
			String contractNatureCd, String loanNum, String applyUserNum,
			Long applyOrgId, Long applyDeptId, String fulfillInstructionCd,
			Date sysCreateDate, Date sysUpdateDate,
			String contractStatusCd, String processStatusCd, Long partyId,
			BigDecimal contractBalance, BigDecimal contractAvailableAmt,
			BigDecimal cumulativePayoutAmt, BigDecimal cumulativeRepayAmt,
			Date lastRepayDate, Long bizContractRelId,
			String contractTermUnit, String checkStatus,
			String whetherAgreedLoan, String explanationLoan,
			String whetherAgreedContract, String explanationContract,
			String instructionsSuggestions, BigDecimal contractOvdueRate,
			BigDecimal contractRate, Date arrangeRepayDate,
			Date firstOvdueDate, Date lastOvdueDate, Long ovdueTime,
			BigDecimal ovdueAmt, Integer arrangeRepayDay, String currency,
			BigDecimal preRepaymentRate, String productType, BigDecimal ovdueInterest,
			Long ovdueDays, Date checkTime, String fiveclassification,
			String documentType, String fulfilLoanCondition,
			Date payloanDate, BigDecimal interestRate,
			Date lastUploadTime, BigDecimal freeamtcnt,
			BigDecimal freepayloanamtcnt, String ifReleaseFlag,
			Long initContractId, Integer contractTermTotal,
			String contractTermUnitTotal, Long contractIndex,
			String loanDateStyle, Long parentContractId,
			String contracValidState, String fpoolFlag, Long fpoolProjectId,
			String txno, String quotaContractAuditStatus ,Long creditContractId) {
		this.contractId = contractId;
		this.projectId = projectId;
		this.projectNo = projectNo;
		this.contractNum = contractNum;
		this.customerNum = customerNum;
		this.customerName = customerName;
		this.customerType = customerType;
		this.contractAmt = contractAmt;
		this.contractTerm = contractTerm;
		this.startDate = startDate;
		this.expirationDate = expirationDate;
		this.repayModeCd = repayModeCd;
		this.repayPrincipalMonthes = repayPrincipalMonthes;
		this.bankName = bankName;
		this.contractNatureCd = contractNatureCd;
		this.loanNum = loanNum;
		this.applyUserNum = applyUserNum;
		this.applyOrgId = applyOrgId;
		this.applyDeptId = applyDeptId;
		this.fulfillInstructionCd = fulfillInstructionCd;
		this.sysCreateDate = sysCreateDate;
		this.sysUpdateDate = sysUpdateDate;
		this.contractStatusCd = contractStatusCd;
		this.processStatusCd = processStatusCd;
		this.partyId = partyId;
		this.contractBalance = contractBalance;
		this.contractAvailableAmt = contractAvailableAmt;
		this.cumulativePayoutAmt = cumulativePayoutAmt;
		this.cumulativeRepayAmt = cumulativeRepayAmt;
		this.lastRepayDate = lastRepayDate;
		this.bizContractRelId = bizContractRelId;
		this.contractTermUnit = contractTermUnit;
		this.checkStatus = checkStatus;
		this.whetherAgreedLoan = whetherAgreedLoan;
		this.explanationLoan = explanationLoan;
		this.whetherAgreedContract = whetherAgreedContract;
		this.explanationContract = explanationContract;
		this.instructionsSuggestions = instructionsSuggestions;
		this.contractOvdueRate = contractOvdueRate;
		this.contractRate = contractRate;
		this.arrangeRepayDate = arrangeRepayDate;
		this.firstOvdueDate = firstOvdueDate;
		this.lastOvdueDate = lastOvdueDate;
		this.ovdueTime = ovdueTime;
		this.ovdueAmt = ovdueAmt;
		this.arrangeRepayDay = arrangeRepayDay;
		this.currency = currency;
		this.preRepaymentRate = preRepaymentRate;
		this.productType = productType;
		this.ovdueInterest = ovdueInterest;
		this.ovdueDays = ovdueDays;
		this.checkTime = checkTime;
		this.fiveclassification = fiveclassification;
		this.documentType = documentType;
		this.fulfilLoanCondition = fulfilLoanCondition;
		this.payloanDate = payloanDate;
		this.interestRate = interestRate;
		this.lastUploadTime = lastUploadTime;
		this.freeamtcnt = freeamtcnt;
		this.freepayloanamtcnt = freepayloanamtcnt;
		this.ifReleaseFlag = ifReleaseFlag;
		this.initContractId = initContractId;
		this.contractTermTotal = contractTermTotal;
		this.contractTermUnitTotal = contractTermUnitTotal;
		this.contractIndex = contractIndex;
		this.loanDateStyle = loanDateStyle;
		this.parentContractId = parentContractId;
		this.contracValidState = contracValidState;
		this.fpoolFlag = fpoolFlag;
		this.fpoolProjectId = fpoolProjectId;
		this.txno = txno;
		this.quotaContractAuditStatus = quotaContractAuditStatus;
		this.creditContractId=creditContractId;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_CONTRACT", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "CONTRACT_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_NO", length = 40)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "CONTRACT_NUM", length = 30)
	public String getContractNum() {
		return this.contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
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

	@Column(name = "CONTRACT_AMT", precision = 20)
	public BigDecimal getContractAmt() {
		return this.contractAmt;
	}

	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}

	@Column(name = "CONTRACT_TERM", precision = 6, scale = 0)
	public Integer getContractTerm() {
		return this.contractTerm;
	}

	public void setContractTerm(Integer contractTerm) {
		this.contractTerm = contractTerm;
	}

	@Column(name = "START_DATE", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "EXPIRATION_DATE", length = 7)
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "REPAY_MODE_CD", length = 20)
	public String getRepayModeCd() {
		return this.repayModeCd;
	}

	public void setRepayModeCd(String repayModeCd) {
		this.repayModeCd = repayModeCd;
	}

	@Column(name = "REPAY_PRINCIPAL_MONTHES", precision = 6, scale = 0)
	public Integer getRepayPrincipalMonthes() {
		return this.repayPrincipalMonthes;
	}

	public void setRepayPrincipalMonthes(Integer repayPrincipalMonthes) {
		this.repayPrincipalMonthes = repayPrincipalMonthes;
	}

	@Column(name = "BANK_NAME", length = 100)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "CONTRACT_NATURE_CD", length = 20)
	public String getContractNatureCd() {
		return this.contractNatureCd;
	}

	public void setContractNatureCd(String contractNatureCd) {
		this.contractNatureCd = contractNatureCd;
	}

	@Column(name = "LOAN_NUM", length = 50)
	public String getLoanNum() {
		return this.loanNum;
	}

	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}

	@Column(name = "APPLY_USER_NUM", length = 20)
	public String getApplyUserNum() {
		return this.applyUserNum;
	}

	public void setApplyUserNum(String applyUserNum) {
		this.applyUserNum = applyUserNum;
	}

	@Column(name = "APPLY_ORG_ID", precision = 12, scale = 0)
	public Long getApplyOrgId() {
		return this.applyOrgId;
	}

	public void setApplyOrgId(Long applyOrgId) {
		this.applyOrgId = applyOrgId;
	}

	@Column(name = "APPLY_DEPT_ID", precision = 12, scale = 0)
	public Long getApplyDeptId() {
		return this.applyDeptId;
	}

	public void setApplyDeptId(Long applyDeptId) {
		this.applyDeptId = applyDeptId;
	}

	@Column(name = "FULFILL_INSTRUCTION_CD", length = 4000)
	public String getFulfillInstructionCd() {
		return this.fulfillInstructionCd;
	}

	public void setFulfillInstructionCd(String fulfillInstructionCd) {
		this.fulfillInstructionCd = fulfillInstructionCd;
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

	@Column(name = "CONTRACT_STATUS_CD", length = 20)
	public String getContractStatusCd() {
		return this.contractStatusCd;
	}

	public void setContractStatusCd(String contractStatusCd) {
		this.contractStatusCd = contractStatusCd;
	}

	@Column(name = "PROCESS_STATUS_CD", length = 20)
	public String getProcessStatusCd() {
		return this.processStatusCd;
	}

	public void setProcessStatusCd(String processStatusCd) {
		this.processStatusCd = processStatusCd;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CONTRACT_BALANCE", precision = 20)
	public BigDecimal getContractBalance() {
		return this.contractBalance;
	}

	public void setContractBalance(BigDecimal contractBalance) {
		this.contractBalance = contractBalance;
	}

	@Column(name = "CONTRACT_AVAILABLE_AMT", precision = 20)
	public BigDecimal getContractAvailableAmt() {
		return this.contractAvailableAmt;
	}

	public void setContractAvailableAmt(BigDecimal contractAvailableAmt) {
		this.contractAvailableAmt = contractAvailableAmt;
	}

	@Column(name = "CUMULATIVE_PAYOUT_AMT", precision = 20)
	public BigDecimal getCumulativePayoutAmt() {
		return this.cumulativePayoutAmt;
	}

	public void setCumulativePayoutAmt(BigDecimal cumulativePayoutAmt) {
		this.cumulativePayoutAmt = cumulativePayoutAmt;
	}

	@Column(name = "CUMULATIVE_REPAY_AMT", precision = 20)
	public BigDecimal getCumulativeRepayAmt() {
		return this.cumulativeRepayAmt;
	}

	public void setCumulativeRepayAmt(BigDecimal cumulativeRepayAmt) {
		this.cumulativeRepayAmt = cumulativeRepayAmt;
	}

	@Column(name = "LAST_REPAY_DATE", length = 7)
	public Date getLastRepayDate() {
		return this.lastRepayDate;
	}

	public void setLastRepayDate(Date lastRepayDate) {
		this.lastRepayDate = lastRepayDate;
	}

	@Column(name = "BIZ_CONTRACT_REL_ID", precision = 12, scale = 0)
	public Long getBizContractRelId() {
		return this.bizContractRelId;
	}

	public void setBizContractRelId(Long bizContractRelId) {
		this.bizContractRelId = bizContractRelId;
	}

	@Column(name = "CONTRACT_TERM_UNIT", length = 20)
	public String getContractTermUnit() {
		return this.contractTermUnit;
	}

	public void setContractTermUnit(String contractTermUnit) {
		this.contractTermUnit = contractTermUnit;
	}

	@Column(name = "CHECK_STATUS", length = 4)
	public String getCheckStatus() {
		return this.checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Column(name = "WHETHER_AGREED_LOAN", length = 5)
	public String getWhetherAgreedLoan() {
		return this.whetherAgreedLoan;
	}

	public void setWhetherAgreedLoan(String whetherAgreedLoan) {
		this.whetherAgreedLoan = whetherAgreedLoan;
	}

	@Column(name = "EXPLANATION_LOAN", length = 250)
	public String getExplanationLoan() {
		return this.explanationLoan;
	}

	public void setExplanationLoan(String explanationLoan) {
		this.explanationLoan = explanationLoan;
	}

	@Column(name = "WHETHER_AGREED_CONTRACT", length = 5)
	public String getWhetherAgreedContract() {
		return this.whetherAgreedContract;
	}

	public void setWhetherAgreedContract(String whetherAgreedContract) {
		this.whetherAgreedContract = whetherAgreedContract;
	}

	@Column(name = "EXPLANATION_CONTRACT", length = 250)
	public String getExplanationContract() {
		return this.explanationContract;
	}

	public void setExplanationContract(String explanationContract) {
		this.explanationContract = explanationContract;
	}

	@Column(name = "INSTRUCTIONS_SUGGESTIONS", length = 250)
	public String getInstructionsSuggestions() {
		return this.instructionsSuggestions;
	}

	public void setInstructionsSuggestions(String instructionsSuggestions) {
		this.instructionsSuggestions = instructionsSuggestions;
	}

	@Column(name = "CONTRACT_OVDUE_RATE", precision = 20, scale = 6)
	public BigDecimal getContractOvdueRate() {
		return this.contractOvdueRate;
	}

	public void setContractOvdueRate(BigDecimal contractOvdueRate) {
		this.contractOvdueRate = contractOvdueRate;
	}

	@Column(name = "CONTRACT_RATE", precision = 20, scale = 6)
	public BigDecimal getContractRate() {
		return this.contractRate;
	}

	public void setContractRate(BigDecimal contractRate) {
		this.contractRate = contractRate;
	}

	@Column(name = "ARRANGE_RELAY_DATE", length = 7)
	public Date getArrangeRepayDate() {
		return this.arrangeRepayDate;
	}

	public void setArrangeRepayDate(Date arrangeRepayDate) {
		this.arrangeRepayDate = arrangeRepayDate;
	}

	@Column(name = "FIRST_OVDUE_DATE", length = 7)
	public Date getFirstOvdueDate() {
		return this.firstOvdueDate;
	}

	public void setFirstOvdueDate(Date firstOvdueDate) {
		this.firstOvdueDate = firstOvdueDate;
	}

	@Column(name = "LAST_OVDUE_DATE", length = 7)
	public Date getLastOvdueDate() {
		return this.lastOvdueDate;
	}

	public void setLastOvdueDate(Date lastOvdueDate) {
		this.lastOvdueDate = lastOvdueDate;
	}

	@Column(name = "OVDUE_TIME", precision = 12, scale = 0)
	public Long getOvdueTime() {
		return this.ovdueTime;
	}

	public void setOvdueTime(Long ovdueTime) {
		this.ovdueTime = ovdueTime;
	}

	@Column(name = "OVDUE_AMT", precision = 20)
	public BigDecimal getOvdueAmt() {
		return this.ovdueAmt;
	}

	public void setOvdueAmt(BigDecimal ovdueAmt) {
		this.ovdueAmt = ovdueAmt;
	}

	@Column(name = "ARRANGE_REPAY_DAY", precision = 6, scale = 0)
	public Integer getArrangeRepayDay() {
		return this.arrangeRepayDay;
	}

	public void setArrangeRepayDay(Integer arrangeRepayDay) {
		this.arrangeRepayDay = arrangeRepayDay;
	}

	@Column(name = "CURRENCY", length = 30)
	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "PRE_REPAYMENT_RATE", precision = 20, scale = 6)
	public BigDecimal getPreRepaymentRate() {
		return this.preRepaymentRate;
	}

	public void setPreRepaymentRate(BigDecimal preRepaymentRate) {
		this.preRepaymentRate = preRepaymentRate;
	}

	@Column(name = "PRODUCT_TYPE", length = 30)
	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name = "OVDUE_INTEREST", precision = 20)
	public BigDecimal getOvdueInterest() {
		return this.ovdueInterest;
	}

	public void setOvdueInterest(BigDecimal ovdueInterest) {
		this.ovdueInterest = ovdueInterest;
	}

	@Column(name = "OVDUE_DAYS", precision = 12, scale = 0)
	public Long getOvdueDays() {
		return this.ovdueDays;
	}

	public void setOvdueDays(Long ovdueDays) {
		this.ovdueDays = ovdueDays;
	}

	@Column(name = "CHECK_TIME", length = 7)
	public Date getCheckTime() {
		return this.checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Column(name = "FIVECLASSIFICATION", length = 1)
	public String getFiveclassification() {
		return this.fiveclassification;
	}

	public void setFiveclassification(String fiveclassification) {
		this.fiveclassification = fiveclassification;
	}

	@Column(name = "DOCUMENT_TYPE", length = 20)
	public String getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@Column(name = "FULFIL_LOAN_CONDITION", length = 4000)
	public String getFulfilLoanCondition() {
		return this.fulfilLoanCondition;
	}

	public void setFulfilLoanCondition(String fulfilLoanCondition) {
		this.fulfilLoanCondition = fulfilLoanCondition;
	}

	@Column(name = "PAYLOAN_DATE", length = 7)
	public Date getPayloanDate() {
		return this.payloanDate;
	}

	public void setPayloanDate(Date payloanDate) {
		this.payloanDate = payloanDate;
	}

	@Column(name = "INTEREST_RATE", precision = 20, scale = 6)
	public BigDecimal getInterestRate() {
		return this.interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	@Column(name = "LAST_UPLOAD_TIME", length = 7)
	public Date getLastUploadTime() {
		return this.lastUploadTime;
	}

	public void setLastUploadTime(Date lastUploadTime) {
		this.lastUploadTime = lastUploadTime;
	}

	@Column(name = "FREEAMTCNT", precision = 20)
	public BigDecimal getFreeamtcnt() {
		return this.freeamtcnt;
	}

	public void setFreeamtcnt(BigDecimal freeamtcnt) {
		this.freeamtcnt = freeamtcnt;
	}

	@Column(name = "FREEPAYLOANAMTCNT", precision = 20)
	public BigDecimal getFreepayloanamtcnt() {
		return this.freepayloanamtcnt;
	}

	public void setFreepayloanamtcnt(BigDecimal freepayloanamtcnt) {
		this.freepayloanamtcnt = freepayloanamtcnt;
	}

	@Column(name = "IF_RELEASE_FLAG", length = 1)
	public String getIfReleaseFlag() {
		return this.ifReleaseFlag;
	}

	public void setIfReleaseFlag(String ifReleaseFlag) {
		this.ifReleaseFlag = ifReleaseFlag;
	}

	@Column(name = "INIT_CONTRACT_ID", precision = 12, scale = 0)
	public Long getInitContractId() {
		return this.initContractId;
	}

	public void setInitContractId(Long initContractId) {
		this.initContractId = initContractId;
	}

	@Column(name = "CONTRACT_TERM_TOTAL", precision = 6, scale = 0)
	public Integer getContractTermTotal() {
		return this.contractTermTotal;
	}

	public void setContractTermTotal(Integer contractTermTotal) {
		this.contractTermTotal = contractTermTotal;
	}

	@Column(name = "CONTRACT_TERM_UNIT_TOTAL", length = 20)
	public String getContractTermUnitTotal() {
		return this.contractTermUnitTotal;
	}

	public void setContractTermUnitTotal(String contractTermUnitTotal) {
		this.contractTermUnitTotal = contractTermUnitTotal;
	}

	@Column(name = "CONTRACT_INDEX", precision = 12, scale = 0)
	public Long getContractIndex() {
		return this.contractIndex;
	}

	public void setContractIndex(Long contractIndex) {
		this.contractIndex = contractIndex;
	}

	@Column(name = "LOAN_DATE_STYLE", length = 20)
	public String getLoanDateStyle() {
		return this.loanDateStyle;
	}

	public void setLoanDateStyle(String loanDateStyle) {
		this.loanDateStyle = loanDateStyle;
	}

	@Column(name = "PARENT_CONTRACT_ID", precision = 12, scale = 0)
	public Long getParentContractId() {
		return this.parentContractId;
	}

	public void setParentContractId(Long parentContractId) {
		this.parentContractId = parentContractId;
	}

	@Column(name = "CONTRAC_VALID_STATE", length = 20)
	public String getContracValidState() {
		return this.contracValidState;
	}

	public void setContracValidState(String contracValidState) {
		this.contracValidState = contracValidState;
	}

	@Column(name = "FPOOL_FLAG", length = 1)
	public String getFpoolFlag() {
		return this.fpoolFlag;
	}

	public void setFpoolFlag(String fpoolFlag) {
		this.fpoolFlag = fpoolFlag;
	}

	@Column(name = "FPOOL_PROJECT_ID", precision = 12, scale = 0)
	public Long getFpoolProjectId() {
		return this.fpoolProjectId;
	}

	public void setFpoolProjectId(Long fpoolProjectId) {
		this.fpoolProjectId = fpoolProjectId;
	}

	@Column(name = "TXNO", length = 20)
	public String getTxno() {
		return this.txno;
	}

	public void setTxno(String txno) {
		this.txno = txno;
	}

	@Column(name = "QUOTA_CONTRACT_AUDIT_STATUS", length = 30)
	public String getQuotaContractAuditStatus() {
		return this.quotaContractAuditStatus;
	}

	public void setQuotaContractAuditStatus(String quotaContractAuditStatus) {
		this.quotaContractAuditStatus = quotaContractAuditStatus;
	}
	
	@Column(name = "CUSTOMER_TYPE", length = 30)
	public String getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	@Column(name = "CREDIT_CONTRACT_ID", precision = 12, scale = 0)
	public Long getCreditContractId() {
		return creditContractId;
	}

	public void setCreditContractId(Long creditContractId) {
		this.creditContractId = creditContractId;
	}
	
	
}
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
import javax.persistence.Transient;


import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 还款
 */
@Entity
@Table(name = "REPAY_LOAN", schema = GlobalConstants.WD_SCHEMA)
public class RepayLoan implements java.io.Serializable {

	// Fields

	private Long repayLoanId;
	private String repayLoanNum;
	private String transactionNo;
	private String customerNum;
	private String customerName;
	private Long projectId;
	private String projectNo;
	private Long contractId;
	private String contractNum;
	private Date startDate;
	private Date expirationDate;
	private String loanNum;
	private BigDecimal loanAmt;
	private BigDecimal contractBalance;
	private String currencyCd;
	private String creaditProduct;
	private BigDecimal loanInt;
	private String applyOrgCd;
	private String applyUserNum;
	private Date repayDate;
	private Date payableDate;
	private Date lastDate;
	private BigDecimal payableTotalAmt;
	private BigDecimal payableCurrPrincipal;
	private BigDecimal payableCurrInt;
	private BigDecimal actualAmt;
	private BigDecimal actualCurrPrincipal;
	private BigDecimal actualCurrInt;
	private BigDecimal overduePi;
	private BigDecimal overdueInt;
	private BigDecimal actualOverdueInt;
	private BigDecimal actualOverduePi;
	private Date perRepayDate;
	private BigDecimal perRepayAmt;
	private BigDecimal penalty;
	private BigDecimal perRepaymentRate;
	private String remarks;
	private String perRepaymentReasons;
	private String repaymentStatusCd;
	private String cleanCutCd;
	private String valid;
	private Date createDate;
	private Date updateTime;
	private String origConStatusCd;
	private Date origLastRepayDate;
	private BigDecimal actualPrincipalAmt;
	private BigDecimal payablePrincipalAmt;
	private Date origLastOvdueDate;
	private Long origOvdueDays;
	private Long payLoanId;
	private BigDecimal promisePenalty;
	private String fundsSource;
	private String inadvancePaymentNo;
	private BigDecimal tableinterest;//表内利息
	//非持久化属性
	
	
	private BigDecimal loanAmount;// 实还拖欠本金
	
	// Constructors

	/** default constructor */
	public RepayLoan() {
	}

	/** minimal constructor */
	public RepayLoan(Long repayLoanId) {
		this.repayLoanId = repayLoanId;
	}

	/** full constructor */
	public RepayLoan(Long repayLoanId, String repayLoanNum,
			String transactionNo, String customerNum, String customerName,
			Long projectId, String projectNo, Long contractId,
			String contractNum, Date startDate, Date expirationDate,
			String loanNum, BigDecimal loanAmt, BigDecimal contractBalance,
			String currencyCd, String creaditProduct, BigDecimal loanInt,
			String applyOrgCd, String applyUserNum, Date repayDate,
			Date payableDate, Date lastDate, BigDecimal payableTotalAmt,
			BigDecimal payableCurrPrincipal, BigDecimal payableCurrInt,
			BigDecimal actualAmt, BigDecimal actualCurrPrincipal, BigDecimal actualCurrInt,
			BigDecimal overduePi, BigDecimal overdueInt, BigDecimal actualOverdueInt,
			BigDecimal actualOverduePi, Date perRepayDate, BigDecimal perRepayAmt,
			BigDecimal penalty, BigDecimal perRepaymentRate, String remarks,
			String perRepaymentReasons, String repaymentStatusCd,
			String cleanCutCd, String valid, Date createDate,
			Date updateTime, String origConStatusCd,
			Date origLastRepayDate, BigDecimal actualPrincipalAmt,
			BigDecimal payablePrincipalAmt, Date origLastOvdueDate,
			Long origOvdueDays, Long payLoanId, BigDecimal promisePenalty,
			String fundsSource, String inadvancePaymentNo, BigDecimal tableinterest) {
		this.repayLoanId = repayLoanId;
		this.repayLoanNum = repayLoanNum;
		this.transactionNo = transactionNo;
		this.customerNum = customerNum;
		this.customerName = customerName;
		this.projectId = projectId;
		this.projectNo = projectNo;
		this.contractId = contractId;
		this.contractNum = contractNum;
		this.startDate = startDate;
		this.expirationDate = expirationDate;
		this.loanNum = loanNum;
		this.loanAmt = loanAmt;
		this.contractBalance = contractBalance;
		this.currencyCd = currencyCd;
		this.creaditProduct = creaditProduct;
		this.loanInt = loanInt;
		this.applyOrgCd = applyOrgCd;
		this.applyUserNum = applyUserNum;
		this.repayDate = repayDate;
		this.payableDate = payableDate;
		this.lastDate = lastDate;
		this.payableTotalAmt = payableTotalAmt;
		this.payableCurrPrincipal = payableCurrPrincipal;
		this.payableCurrInt = payableCurrInt;
		this.actualAmt = actualAmt;
		this.actualCurrPrincipal = actualCurrPrincipal;
		this.actualCurrInt = actualCurrInt;
		this.overduePi = overduePi;
		this.overdueInt = overdueInt;
		this.actualOverdueInt = actualOverdueInt;
		this.actualOverduePi = actualOverduePi;
		this.perRepayDate = perRepayDate;
		this.perRepayAmt = perRepayAmt;
		this.penalty = penalty;
		this.perRepaymentRate = perRepaymentRate;
		this.remarks = remarks;
		this.perRepaymentReasons = perRepaymentReasons;
		this.repaymentStatusCd = repaymentStatusCd;
		this.cleanCutCd = cleanCutCd;
		this.valid = valid;
		this.createDate = createDate;
		this.updateTime = updateTime;
		this.origConStatusCd = origConStatusCd;
		this.origLastRepayDate = origLastRepayDate;
		this.actualPrincipalAmt = actualPrincipalAmt;
		this.payablePrincipalAmt = payablePrincipalAmt;
		this.origLastOvdueDate = origLastOvdueDate;
		this.origOvdueDays = origOvdueDays;
		this.payLoanId = payLoanId;
		this.promisePenalty = promisePenalty;
		this.fundsSource = fundsSource;
		this.inadvancePaymentNo = inadvancePaymentNo;
		this.tableinterest = tableinterest;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_REPAY_LOAN", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "REPAY_LOAN_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getRepayLoanId() {
		return this.repayLoanId;
	}

	public void setRepayLoanId(Long repayLoanId) {
		this.repayLoanId = repayLoanId;
	}

	@Column(name = "REPAY_LOAN_NUM", length = 30)
	public String getRepayLoanNum() {
		return this.repayLoanNum;
	}

	public void setRepayLoanNum(String repayLoanNum) {
		this.repayLoanNum = repayLoanNum;
	}

	@Column(name = "TRANSACTION_NO", length = 100)
	public String getTransactionNo() {
		return this.transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
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

	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
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

	@Column(name = "CONTRACT_ID", precision = 12, scale = 0)
	public Long getContractId() {
		return this.contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@Column(name = "CONTRACT_NUM", length = 30)
	public String getContractNum() {
		return this.contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
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

	@Column(name = "LOAN_NUM", length = 50)
	public String getLoanNum() {
		return this.loanNum;
	}

	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}

	@Column(name = "LOAN_AMT", precision = 20)
	public BigDecimal getLoanAmt() {
		return this.loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	@Column(name = "CONTRACT_BALANCE", precision = 20)
	public BigDecimal getContractBalance() {
		return this.contractBalance;
	}

	public void setContractBalance(BigDecimal contractBalance) {
		this.contractBalance = contractBalance;
	}

	@Column(name = "CURRENCY_CD", length = 30)
	public String getCurrencyCd() {
		return this.currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	@Column(name = "CREADIT_PRODUCT", length = 20)
	public String getCreaditProduct() {
		return this.creaditProduct;
	}

	public void setCreaditProduct(String creaditProduct) {
		this.creaditProduct = creaditProduct;
	}

	@Column(name = "LOAN_INT", precision = 20, scale = 6)
	public BigDecimal getLoanInt() {
		return this.loanInt;
	}

	public void setLoanInt(BigDecimal loanInt) {
		this.loanInt = loanInt;
	}

	@Column(name = "APPLY_ORG_CD", length = 20)
	public String getApplyOrgCd() {
		return this.applyOrgCd;
	}

	public void setApplyOrgCd(String applyOrgCd) {
		this.applyOrgCd = applyOrgCd;
	}

	@Column(name = "APPLY_USER_NUM", length = 20)
	public String getApplyUserNum() {
		return this.applyUserNum;
	}

	public void setApplyUserNum(String applyUserNum) {
		this.applyUserNum = applyUserNum;
	}

	@Column(name = "REPAY_DATE", length = 7)
	public Date getRepayDate() {
		return this.repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	@Column(name = "PAYABLE_DATE", length = 7)
	public Date getPayableDate() {
		return this.payableDate;
	}

	public void setPayableDate(Date payableDate) {
		this.payableDate = payableDate;
	}

	@Column(name = "LAST_DATE", length = 7)
	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Column(name = "PAYABLE_TOTAL_AMT", precision = 20)
	public BigDecimal getPayableTotalAmt() {
		return this.payableTotalAmt;
	}

	public void setPayableTotalAmt(BigDecimal payableTotalAmt) {
		this.payableTotalAmt = payableTotalAmt;
	}

	@Column(name = "PAYABLE_CURR_PRINCIPAL", precision = 20)
	public BigDecimal getPayableCurrPrincipal() {
		return this.payableCurrPrincipal;
	}

	public void setPayableCurrPrincipal(BigDecimal payableCurrPrincipal) {
		this.payableCurrPrincipal = payableCurrPrincipal;
	}

	@Column(name = "PAYABLE_CURR_INT", precision = 20)
	public BigDecimal getPayableCurrInt() {
		return this.payableCurrInt;
	}

	public void setPayableCurrInt(BigDecimal payableCurrInt) {
		this.payableCurrInt = payableCurrInt;
	}

	@Column(name = "ACTUAL_AMT", precision = 20)
	public BigDecimal getActualAmt() {
		return this.actualAmt;
	}

	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}

	@Column(name = "ACTUAL_CURR_PRINCIPAL", precision = 20)
	public BigDecimal getActualCurrPrincipal() {
		return this.actualCurrPrincipal;
	}

	public void setActualCurrPrincipal(BigDecimal actualCurrPrincipal) {
		this.actualCurrPrincipal = actualCurrPrincipal;
	}

	@Column(name = "ACTUAL_CURR_INT", precision = 20)
	public BigDecimal getActualCurrInt() {
		return this.actualCurrInt;
	}

	public void setActualCurrInt(BigDecimal actualCurrInt) {
		this.actualCurrInt = actualCurrInt;
	}

	@Column(name = "OVERDUE_PI", precision = 20)
	public BigDecimal getOverduePi() {
		return this.overduePi;
	}

	public void setOverduePi(BigDecimal overduePi) {
		this.overduePi = overduePi;
	}

	@Column(name = "OVERDUE_INT", precision = 20)
	public BigDecimal getOverdueInt() {
		return this.overdueInt;
	}

	public void setOverdueInt(BigDecimal overdueInt) {
		this.overdueInt = overdueInt;
	}

	@Column(name = "ACTUAL_OVERDUE_INT", precision = 20)
	public BigDecimal getActualOverdueInt() {
		return this.actualOverdueInt;
	}

	public void setActualOverdueInt(BigDecimal actualOverdueInt) {
		this.actualOverdueInt = actualOverdueInt;
	}

	@Column(name = "ACTUAL_OVERDUE_PI", precision = 20)
	public BigDecimal getActualOverduePi() {
		return this.actualOverduePi;
	}

	public void setActualOverduePi(BigDecimal actualOverduePi) {
		this.actualOverduePi = actualOverduePi;
	}

	@Column(name = "PER_REPAY_DATE", length = 7)
	public Date getPerRepayDate() {
		return this.perRepayDate;
	}

	public void setPerRepayDate(Date perRepayDate) {
		this.perRepayDate = perRepayDate;
	}

	@Column(name = "PER_REPAY_AMT", precision = 20)
	public BigDecimal getPerRepayAmt() {
		return this.perRepayAmt;
	}

	public void setPerRepayAmt(BigDecimal perRepayAmt) {
		this.perRepayAmt = perRepayAmt;
	}

	@Column(name = "PENALTY", precision = 20)
	public BigDecimal getPenalty() {
		return this.penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	@Column(name = "PER_REPAYMENT_RATE", precision = 20, scale = 6)
	public BigDecimal getPerRepaymentRate() {
		return this.perRepaymentRate;
	}

	public void setPerRepaymentRate(BigDecimal perRepaymentRate) {
		this.perRepaymentRate = perRepaymentRate;
	}

	@Column(name = "REMARKS", length = 4000)
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "PER_REPAYMENT_REASONS", length = 4000)
	public String getPerRepaymentReasons() {
		return this.perRepaymentReasons;
	}

	public void setPerRepaymentReasons(String perRepaymentReasons) {
		this.perRepaymentReasons = perRepaymentReasons;
	}

	@Column(name = "REPAYMENT_STATUS_CD", length = 20)
	public String getRepaymentStatusCd() {
		return this.repaymentStatusCd;
	}

	public void setRepaymentStatusCd(String repaymentStatusCd) {
		this.repaymentStatusCd = repaymentStatusCd;
	}

	@Column(name = "CLEAN_CUT_CD", length = 20)
	public String getCleanCutCd() {
		return this.cleanCutCd;
	}

	public void setCleanCutCd(String cleanCutCd) {
		this.cleanCutCd = cleanCutCd;
	}

	@Column(name = "VALID", length = 1)
	public String getValid() {
		return this.valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_TIME", length = 11)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "ORIG_CON_STATUS_CD", length = 20)
	public String getOrigConStatusCd() {
		return this.origConStatusCd;
	}

	public void setOrigConStatusCd(String origConStatusCd) {
		this.origConStatusCd = origConStatusCd;
	}

	@Column(name = "ORIG_LAST_REPAY_DATE", length = 7)
	public Date getOrigLastRepayDate() {
		return this.origLastRepayDate;
	}

	public void setOrigLastRepayDate(Date origLastRepayDate) {
		this.origLastRepayDate = origLastRepayDate;
	}

	@Column(name = "ACTUAL_PRINCIPAL_AMT", precision = 20)
	public BigDecimal getActualPrincipalAmt() {
		return this.actualPrincipalAmt;
	}

	public void setActualPrincipalAmt(BigDecimal actualPrincipalAmt) {
		this.actualPrincipalAmt = actualPrincipalAmt;
	}

	@Column(name = "PAYABLE_PRINCIPAL_AMT", precision = 20)
	public BigDecimal getPayablePrincipalAmt() {
		return this.payablePrincipalAmt;
	}

	public void setPayablePrincipalAmt(BigDecimal payablePrincipalAmt) {
		this.payablePrincipalAmt = payablePrincipalAmt;
	}

	@Column(name = "ORIG_LAST_OVDUE_DATE", length = 7)
	public Date getOrigLastOvdueDate() {
		return this.origLastOvdueDate;
	}

	public void setOrigLastOvdueDate(Date origLastOvdueDate) {
		this.origLastOvdueDate = origLastOvdueDate;
	}

	@Column(name = "ORIG_OVDUE_DAYS", precision = 12, scale = 0)
	public Long getOrigOvdueDays() {
		return this.origOvdueDays;
	}

	public void setOrigOvdueDays(Long origOvdueDays) {
		this.origOvdueDays = origOvdueDays;
	}

	@Column(name = "PAY_LOAN_ID", precision = 12, scale = 0)
	public Long getPayLoanId() {
		return this.payLoanId;
	}

	public void setPayLoanId(Long payLoanId) {
		this.payLoanId = payLoanId;
	}

	@Column(name = "PROMISE_PENALTY", precision = 20)
	public BigDecimal getPromisePenalty() {
		return this.promisePenalty;
	}

	public void setPromisePenalty(BigDecimal promisePenalty) {
		this.promisePenalty = promisePenalty;
	}

	@Column(name = "FUNDS_SOURCE", length = 1)
	public String getFundsSource() {
		return this.fundsSource;
	}

	public void setFundsSource(String fundsSource) {
		this.fundsSource = fundsSource;
	}

	@Column(name = "INADVANCE_PAYMENT_NO", length = 30)
	public String getInadvancePaymentNo() {
		return this.inadvancePaymentNo;
	}

	public void setInadvancePaymentNo(String inadvancePaymentNo) {
		this.inadvancePaymentNo = inadvancePaymentNo;
	}

	@Column(name = "TABLEINTEREST", precision = 20)
	public BigDecimal getTableinterest() {
		return this.tableinterest;
	}

	public void setTableinterest(BigDecimal tableinterest) {
		this.tableinterest = tableinterest;
	}
	
	
	@Transient 
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	
	
}
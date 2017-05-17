package com.coamctech.bxloan.entity;

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

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * 还款计划
 * @author Acore
 *
 */
@Entity
@Table(name = "REPAYING_PLAN", schema = WD_SCHEMA)
public class RepayingPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6080964695910220442L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_REPAYING_PLAN", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "REPAYING_PLAN_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long repayingPlanId;
	@Column(name = "PARTY_ID")
	private Long partyId;
	@Column(name="PROJECT_ID")
	private Long projectId;
	@Column(name="CONTRACT_ID")
	private Long contractId;
	@Column(name="REPAYING_MODE",length=1)
	private String repayingMode;
	@Column(name="REPAYING_CYCLE")
	private Integer repayingCycle;
	@Column(name="TOTAL_PERIOD")
	private Integer totalPeriod;
	@Column(name="REPAYED_PERIOD")
	private Integer repayedPeriod;
	@Column(name="SURPLUS_PERIOD")
	private Integer surplusPeriod;
	@Column(name="START_DATE")
	private Date startDate;
	@Column(name="END_DATE")
	private Date endDate;
	@Column(name="YEAR_RATE",precision=12,scale=6)
	private BigDecimal yearRate;
	@Column(name="LOAN_AMOUNT",precision=20,scale=2)
	private BigDecimal loanAmount;
	@Column(name="REPAYED_AMOUNT",precision=20,scale=2)
	private BigDecimal repayedAmount;
	@Column(name="VERSION")
	private Integer version;
	@Column(name="SYSTEM_DATE")
	private Date systemDate;
	@Column(name="CYCLE_UNIT",length=20)
	private String cycleUnit;
	@Column(name="OPERATOR_USER",length=20)
	private String operatorUser;
	@Column(name="OPERATOR_MECHANISM",length=30)
	private String operatorMechanism;
	@Column(name="PAY_LOAN_ID",precision=20,scale=0)
	private Long payLoanId;
	@Column(name="OVERDUE_RATE",precision=12,scale=6)
	private BigDecimal overdueRate;
	@Column(name="TRANSACTION_NO",length=100)
	private String transactionNo;
	/**
	 * 单据状态(1:发送未入账2:已入账3:已退单4:冲销未入账5:冲销已入账)
	 */
	@Column(name="TRANSACTION_STATUS",length=1)
	private String transactionStatus;
	@Column(name="EVENT_TYPE",length=1)
	private String eventType;
	@Column(name="SERIAL_NUM",precision=12,scale=0)
	private Integer serialNum;
	@Column(name="REPAYING_NUM",length=100)
	private String repayingNum;
	@Column(name="CONT_LAST_OVDUE_DATE")
	private Date contFirstOvdueDate;
	@Column(name="CONT_FIRST_OVDUE_DATE")
	private Date contLastOvdueDate;
	@Column(name="CONT_OVDUE_TIME")
	private Integer contOvdueTime;
	@Column(name="CONT_OVDUE_AMT",precision=20,scale=2)
	private BigDecimal contOvdueAmt;
	@Column(name="CONT_OVDUE_INTEREST",precision=20,scale=2)
	private BigDecimal contOvdueInterest;
	@Column(name="CONT_OVDUE_DAYS")
	private Integer contOvdueDays;
	@Column(name="CONT_FIVECLASSIFICATION",length=1)
	private String contFiveclassification;
	@Column(name="CONT_CONTRACT_STATUS_CD",length=20)
	private String contContractStatusCd;
	@Column(name="PRIVISION_AMT",precision=20,scale=0)
	private BigDecimal privisionAmt;
	@Column(name="TABLEINTEREST",precision=20,scale=2)
	private BigDecimal tableInterest;
	public Long getRepayingPlanId() {
		return repayingPlanId;
	}
	public void setRepayingPlanId(Long repayingPlanId) {
		this.repayingPlanId = repayingPlanId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public String getRepayingMode() {
		return repayingMode;
	}
	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}
	public Integer getRepayingCycle() {
		return repayingCycle;
	}
	public void setRepayingCycle(Integer repayingCycle) {
		this.repayingCycle = repayingCycle;
	}
	public Integer getTotalPeriod() {
		return totalPeriod;
	}
	public void setTotalPeriod(Integer totalPeriod) {
		this.totalPeriod = totalPeriod;
	}
	public Integer getRepayedPeriod() {
		return repayedPeriod;
	}
	public void setRepayedPeriod(Integer repayedPeriod) {
		this.repayedPeriod = repayedPeriod;
	}
	public Integer getSurplusPeriod() {
		return surplusPeriod;
	}
	public void setSurplusPeriod(Integer surplusPeriod) {
		this.surplusPeriod = surplusPeriod;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public BigDecimal getYearRate() {
		return yearRate;
	}
	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getRepayedAmount() {
		return repayedAmount;
	}
	public void setRepayedAmount(BigDecimal repayedAmount) {
		this.repayedAmount = repayedAmount;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Date getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}
	public String getCycleUnit() {
		return cycleUnit;
	}
	public void setCycleUnit(String cycleUnit) {
		this.cycleUnit = cycleUnit;
	}
	public String getOperatorUser() {
		return operatorUser;
	}
	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}
	public String getOperatorMechanism() {
		return operatorMechanism;
	}
	public void setOperatorMechanism(String operatorMechanism) {
		this.operatorMechanism = operatorMechanism;
	}
	public Long getPayLoanId() {
		return payLoanId;
	}
	public void setPayLoanId(Long payLoanId) {
		this.payLoanId = payLoanId;
	}
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Integer getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}
	public String getRepayingNum() {
		return repayingNum;
	}
	public void setRepayingNum(String repayingNum) {
		this.repayingNum = repayingNum;
	}
	public Date getContFirstOvdueDate() {
		return contFirstOvdueDate;
	}
	public void setContFirstOvdueDate(Date contFirstOvdueDate) {
		this.contFirstOvdueDate = contFirstOvdueDate;
	}
	public Date getContLastOvdueDate() {
		return contLastOvdueDate;
	}
	public void setContLastOvdueDate(Date contLastOvdueDate) {
		this.contLastOvdueDate = contLastOvdueDate;
	}
	public Integer getContOvdueTime() {
		return contOvdueTime;
	}
	public void setContOvdueTime(Integer contOvdueTime) {
		this.contOvdueTime = contOvdueTime;
	}
	public BigDecimal getContOvdueAmt() {
		return contOvdueAmt;
	}
	public void setContOvdueAmt(BigDecimal contOvdueAmt) {
		this.contOvdueAmt = contOvdueAmt;
	}
	public BigDecimal getContOvdueInterest() {
		return contOvdueInterest;
	}
	public void setContOvdueInterest(BigDecimal contOvdueInterest) {
		this.contOvdueInterest = contOvdueInterest;
	}
	public Integer getContOvdueDays() {
		return contOvdueDays;
	}
	public void setContOvdueDays(Integer contOvdueDays) {
		this.contOvdueDays = contOvdueDays;
	}
	public String getContFiveclassification() {
		return contFiveclassification;
	}
	public void setContFiveclassification(String contFiveclassification) {
		this.contFiveclassification = contFiveclassification;
	}
	public String getContContractStatusCd() {
		return contContractStatusCd;
	}
	public void setContContractStatusCd(String contContractStatusCd) {
		this.contContractStatusCd = contContractStatusCd;
	}
	public BigDecimal getPrivisionAmt() {
		return privisionAmt;
	}
	public void setPrivisionAmt(BigDecimal privisionAmt) {
		this.privisionAmt = privisionAmt;
	}
	public BigDecimal getTableInterest() {
		return tableInterest;
	}
	public void setTableInterest(BigDecimal tableInterest) {
		this.tableInterest = tableInterest;
	}
}

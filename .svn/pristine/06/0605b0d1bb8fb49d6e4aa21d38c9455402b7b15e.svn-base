package com.coamctech.bxloan.entity;

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

/**
 * 还款计划明细
 * @author Acore
 *
 */
@Entity
@Table(name = "REPAYING_PLAN_DETAIL", schema = WD_SCHEMA)
public class RepayingPlanDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5038983573517620595L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_REPAYING_PLAN_DETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="REPAYING_PLAN_DETAIL_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long repayingPlanDetailId;
	@Column(name="REPAYING_PLAN_ID")
	private Long repayingPlanId;
	@Column(name="CURRENT_PERIOD")
	private Integer currentPeriod;
	@Column(name="CURRENT_END_DATE")
	private Date currentEndDate;
	@Column(name="CURRENT_PRINCIPAL",precision=20,scale=2)
	private BigDecimal currentPrincipal;
	@Column(name="CURRENT_INTEREST",precision=20,scale=2)
	private BigDecimal currentInterest;
	@Column(name="CURRENT_PRINCIPAL_INTEREST",precision=20,scale=2)
	private BigDecimal currentPrincipalInterest;
	@Column(name="END_CURRENT_PRINCIPAL",precision=20,scale=2)
	private BigDecimal endCurrentPrincipal;
	@Column(name="END_CURRENT_INTEREST",precision=20,scale=2)
	private BigDecimal endCurrentInterest;
	@Column(name="END_CURRENT_PRINCIPALBALANCE",precision=20,scale=2)
	private BigDecimal endCurrentPrincipalbalance;
	@Column(name="SYSTEM_DATE")
	private Date systemDate;
	@Column(name="OPERATOR_MECHANISM",length=20)
	private String operatorMechanism;
	@Column(name="OPERATOR_USER",length=20)
	private String operatorUser;
	@Column(name="CURRENT_START_DATE")
	private Date currentStartDate;
	@Column(name="YEAR_RATE",precision=12,scale=6)
	private BigDecimal yearRate;
	@Column(name="OVERDUE_RATE",precision=12,scale=6)
	private BigDecimal overdueRate;
	@Column(name="STATUS",length=1)
	private String status; 
	@Column(name="LAST_DATE")
	private Date lastDate;
	@Column(name="TRANSACTION_NO",length=100)
	private String transactionNo;
	@Column(name="TRANSACTION_STATUS",length=1)
	private String transactionStatus;
	@Column(name="IS_INTERESTINCONTRACTRECORD",length=1)
	private String isInterestInContractRecord;
	@Column(name="ADJUSTDATE")
	private Date adjustDate;
	@Column(name="VERSION")
	private Integer version;
	@Column(name="SERIAL_NUM")
	private Long serialNum;
	public Long getRepayingPlanDetailId() {
		return repayingPlanDetailId;
	}
	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	public Long getRepayingPlanId() {
		return repayingPlanId;
	}
	public void setRepayingPlanId(Long repayingPlanId) {
		this.repayingPlanId = repayingPlanId;
	}
	public Integer getCurrentPeriod() {
		return currentPeriod;
	}
	public void setCurrentPeriod(Integer currentPeriod) {
		this.currentPeriod = currentPeriod;
	}
	public Date getCurrentEndDate() {
		return currentEndDate;
	}
	public void setCurrentEndDate(Date currentEndDate) {
		this.currentEndDate = currentEndDate;
	}
	public BigDecimal getCurrentPrincipal() {
		return currentPrincipal;
	}
	public void setCurrentPrincipal(BigDecimal currentPrincipal) {
		this.currentPrincipal = currentPrincipal;
	}
	public BigDecimal getCurrentInterest() {
		return currentInterest;
	}
	public void setCurrentInterest(BigDecimal currentInterest) {
		this.currentInterest = currentInterest;
	}
	public BigDecimal getCurrentPrincipalInterest() {
		return currentPrincipalInterest;
	}
	public void setCurrentPrincipalInterest(BigDecimal currentPrincipalInterest) {
		this.currentPrincipalInterest = currentPrincipalInterest;
	}
	public BigDecimal getEndCurrentPrincipal() {
		return endCurrentPrincipal;
	}
	public void setEndCurrentPrincipal(BigDecimal endCurrentPrincipal) {
		this.endCurrentPrincipal = endCurrentPrincipal;
	}
	public BigDecimal getEndCurrentInterest() {
		return endCurrentInterest;
	}
	public void setEndCurrentInterest(BigDecimal endCurrentInterest) {
		this.endCurrentInterest = endCurrentInterest;
	}
	public BigDecimal getEndCurrentPrincipalbalance() {
		return endCurrentPrincipalbalance;
	}
	public void setEndCurrentPrincipalbalance(BigDecimal endCurrentPrincipalbalance) {
		this.endCurrentPrincipalbalance = endCurrentPrincipalbalance;
	}
	public Date getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}
	public String getOperatorMechanism() {
		return operatorMechanism;
	}
	public void setOperatorMechanism(String operatorMechanism) {
		this.operatorMechanism = operatorMechanism;
	}
	public String getOperatorUser() {
		return operatorUser;
	}
	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}
	public Date getCurrentStartDate() {
		return currentStartDate;
	}
	public void setCurrentStartDate(Date currentStartDate) {
		this.currentStartDate = currentStartDate;
	}
	public BigDecimal getYearRate() {
		return yearRate;
	}
	public void setYearRate(BigDecimal yearRate) {
		this.yearRate = yearRate;
	}
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
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
	public String getIsInterestInContractRecord() {
		return isInterestInContractRecord;
	}
	public void setIsInterestInContractRecord(String isInterestInContractRecord) {
		this.isInterestInContractRecord = isInterestInContractRecord;
	}
	public Date getAdjustDate() {
		return adjustDate;
	}
	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}
	
}

package com.coamctech.bxloan.entity;
// default package

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 逾期情况
 */
@Entity
@Table(name = "OVERDUE_STATE", schema =WD_SCHEMA)
public class OverdueState implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long overdueStateId;
	@Column(name="PROJECT_ID")
	private Long projectId;
	@Column(name="CONTRACT_ID")
	private Long contractId;
	@Column(name="LOAN_ID")
	private Long loanId;
	@Column(name="PARTY_ID")
	private Long partyId;
	@Column(name="OVERDUE_DATE")
	@Temporal(TemporalType.DATE)
	private Date overdueDate;
	@Column(name="REPAYMENT_DATE")
	@Temporal(TemporalType.DATE)
	private Date repaymentDate;
	@Column(name="DUE_DAY")
	private Integer dueDay;
	@Column(name="REPAYING_PLAN_DETAIL_ID")
	private Long repayingPlanDetailId;
	@Column(name="SYS_UPDATE_DATE")
	@Temporal(TemporalType.DATE)
	private Date sysUpdateDate;
	/**
	 * 放款状态(1:发送未入账2:已入账3:已退单4:冲销未入账5:冲销已入账)
	 */
	
	@Column(name="PAY_LOAN_STATUS")
	private String payLoanStatus;
	
	@SequenceGenerator(name = "generator", sequenceName="SEQ_OVERDUE_STATE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="OVERDUESTATE_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getOverdueStateId() {
		return overdueStateId;
	}
	public void setOverdueStateId(Long overdueStateId) {
		this.overdueStateId = overdueStateId;
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
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Date getOverdueDate() {
		return overdueDate;
	}
	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}
	public Date getRepaymentDate() {
		return repaymentDate;
	}
	public void setRepaymentDate(Date repaymentDate) {
		this.repaymentDate = repaymentDate;
	}
	public Integer getDueDay() {
		return dueDay;
	}
	public void setDueDay(Integer dueDay) {
		this.dueDay = dueDay;
	}
	public Long getRepayingPlanDetailId() {
		return repayingPlanDetailId;
	}
	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	public Date getSysUpdateDate() {
		return sysUpdateDate;
	}
	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}
	public String getPayLoanStatus() {
		return payLoanStatus;
	}
	public void setPayLoanStatus(String payLoanStatus) {
		this.payLoanStatus = payLoanStatus;
	}

}
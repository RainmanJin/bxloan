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
 * 还款明细
 * @author Acore
 *
 */
@Entity
@Table(name = "REPAYING_DETAIL", schema = WD_SCHEMA)
public class RepayingDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4362156532879022512L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_REPAYING_DETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="REPAYING_DETAIL_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long repayingDetailId;
	@Column(name="REPAYING_PLAN_DETAIL_ID")
	private Long repayingPlanDetailId;
	@Column(name="REPAYING_DATE")
	private Date repayingDate;
	@Column(name="REPAYING_NUM",precision=20,scale=2)
	private Integer repayingNum;
	@Column(name="REPAYED_PRINCIPAL",precision=20,scale=2)
	private BigDecimal repayedPrincipal;
	@Column(name="REPAYED_INTEREST",precision=20,scale=2)
	private BigDecimal repayedInterest;
	@Column(name="REPAYED_IMPOSE_INTEREST",precision=20,scale=2)
	private BigDecimal repayedImposeInterest;
	@Column(name="REPAYED_TOTAL_AMOUNT",precision=20,scale=2)
	private BigDecimal repayedTotalAmount;
	@Column(name="END_CURRENT_INTERESTCNT",precision=20,scale=2)
	private BigDecimal endCurrentInterestcnt;
	@Column(name="END_CURRENT_PRICIPALCNT",precision=20,scale=2)
	private BigDecimal endCurrentPricipalcnt;
	@Column(name="REPAYED_TOTAL_PRICIPAL",precision=20,scale=2)
	private BigDecimal repayedTotalPricipal;
	@Column(name="SYSTEM_DATE")
	private Date systemDate;
	@Column(name="TRANSACTION_NO",length=100)
	private String transactionNo;
	@Column(name="TRANSACTION_STATUS",length=1)
	private String transactionStatus;
	@Column(name="SERIAL_NUM")
	private Long serialNum;
	public Long getRepayingDetailId() {
		return repayingDetailId;
	}
	public void setRepayingDetailId(Long repayingDetailId) {
		this.repayingDetailId = repayingDetailId;
	}
	public Long getRepayingPlanDetailId() {
		return repayingPlanDetailId;
	}
	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	public Date getRepayingDate() {
		return repayingDate;
	}
	public void setRepayingDate(Date repayingDate) {
		this.repayingDate = repayingDate;
	}
	public Integer getRepayingNum() {
		return repayingNum;
	}
	public void setRepayingNum(Integer repayingNum) {
		this.repayingNum = repayingNum;
	}
	public BigDecimal getRepayedPrincipal() {
		return repayedPrincipal;
	}
	public void setRepayedPrincipal(BigDecimal repayedPrincipal) {
		this.repayedPrincipal = repayedPrincipal;
	}
	public BigDecimal getRepayedInterest() {
		return repayedInterest;
	}
	public void setRepayedInterest(BigDecimal repayedInterest) {
		this.repayedInterest = repayedInterest;
	}
	public BigDecimal getRepayedImposeInterest() {
		return repayedImposeInterest;
	}
	public void setRepayedImposeInterest(BigDecimal repayedImposeInterest) {
		this.repayedImposeInterest = repayedImposeInterest;
	}
	public BigDecimal getRepayedTotalAmount() {
		return repayedTotalAmount;
	}
	public void setRepayedTotalAmount(BigDecimal repayedTotalAmount) {
		this.repayedTotalAmount = repayedTotalAmount;
	}
	public BigDecimal getEndCurrentInterestcnt() {
		return endCurrentInterestcnt;
	}
	public void setEndCurrentInterestcnt(BigDecimal endCurrentInterestcnt) {
		this.endCurrentInterestcnt = endCurrentInterestcnt;
	}
	public BigDecimal getEndCurrentPricipalcnt() {
		return endCurrentPricipalcnt;
	}
	public void setEndCurrentPricipalcnt(BigDecimal endCurrentPricipalcnt) {
		this.endCurrentPricipalcnt = endCurrentPricipalcnt;
	}
	public BigDecimal getRepayedTotalPricipal() {
		return repayedTotalPricipal;
	}
	public void setRepayedTotalPricipal(BigDecimal repayedTotalPricipal) {
		this.repayedTotalPricipal = repayedTotalPricipal;
	}
	public Date getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
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
	public Long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}

}

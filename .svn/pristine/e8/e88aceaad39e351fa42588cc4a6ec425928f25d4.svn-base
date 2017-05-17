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
 * 欠款明细
 * @author WallenHeng
 * @since 2014-09-03 13:23:13
 *
 */
@Entity
@Table(name = "ARREARS_DETAIL",schema=WD_SCHEMA)
public class ArrearsDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_ARREARS_DETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="ARREARS_DETAIL_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long arrearsDetailId;
	@Column(name="REPAYING_PLAN_DETAIL_ID")
	private Long repayingPlanDetailId;
	@Column(name="PLAN_REPAYINT_DATE")
	private Date planRepayintDate;
	@Column(name="NOTYET_PRICIPAL",precision=20,scale=2)
	private BigDecimal notyetPricipal;
	@Column(name="NOTYET_INTEREST",precision=20,scale=2)
	private BigDecimal notyetInterest;
	@Column(name="NOTYET_IMPOSE_INTEREST",precision=20,scale=2)
	private BigDecimal notyetImposeInterest;
	@Column(name="SYSTEM_DATE")
	private Date systemDate;
	@Column(name="CARRYOVER_IMPOSE_INTEREST",precision=20,scale=2)
	private BigDecimal carryoverImposeInterest;
	@Column(name="RECENTLY_DATE")
	private Date recentlyDate;
	@Column(name="PERIOD")
	private Integer period;
	@Column(name="SERIAL_NUM")
	private Integer serialNum;
	/**
	 * 状态(0:未还1:已还2:逾期)
	 */
	@Column(name="STATUS",length=1)
	private String status; 
	@Column(name="TRANSACTION_NO",length=100)
	private String transactionNo;
	/**
	 * 单据状态(1:发送未入账2:已入账3:已退单4:冲销未入账5:冲销已入账)
	 */
	@Column(name="TRANSACTION_STATUS",length=1)
	private String transactionStatus;
	/**
	 * 还款标识(0:未逾期1:逾期90天内2:逾期90天以上)
	 */
	@Column(name="REPAYING_FLAG",length=1)
	private String repayingFlag;
	/**
	 * 计算标识(1:逾期90天内,紧接后面0逾期90天以上)
	 */
	@Column(name="REPAYING_CAL_FLAG",length=1)
	private String repayingCalFlag;
	/**
	 * 首次逾期标识(1:首次逾期)
	 */
	@Column(name="REPAYING_FIRST_FLAG",length=1)
	private String repayingFirstFlag;
	@Column(name="YEAR_IMPOSE_INTEREST",precision=20,scale=2)
	private BigDecimal yearImposeInterest;
	@Column(name="YEAR_IMPOSE_90_INTEREST",precision=20,scale=2)
	private BigDecimal yearImpose90Interest;
	public Long getArrearsDetailId() {
		return arrearsDetailId;
	}
	public void setArrearsDetailId(Long arrearsDetailId) {
		this.arrearsDetailId = arrearsDetailId;
	}
	public Long getRepayingPlanDetailId() {
		return repayingPlanDetailId;
	}
	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	public Date getPlanRepayintDate() {
		return planRepayintDate;
	}
	public void setPlanRepayintDate(Date planRepayintDate) {
		this.planRepayintDate = planRepayintDate;
	}
	public BigDecimal getNotyetPricipal() {
		return notyetPricipal;
	}
	public void setNotyetPricipal(BigDecimal notyetPricipal) {
		this.notyetPricipal = notyetPricipal;
	}
	public BigDecimal getNotyetInterest() {
		return notyetInterest;
	}
	public void setNotyetInterest(BigDecimal notyetInterest) {
		this.notyetInterest = notyetInterest;
	}
	public BigDecimal getNotyetImposeInterest() {
		return notyetImposeInterest;
	}
	public void setNotyetImposeInterest(BigDecimal notyetImposeInterest) {
		this.notyetImposeInterest = notyetImposeInterest;
	}
	public Date getSystemDate() {
		return systemDate;
	}
	public void setSystemDate(Date systemDate) {
		this.systemDate = systemDate;
	}
	public BigDecimal getCarryoverImposeInterest() {
		return carryoverImposeInterest;
	}
	public void setCarryoverImposeInterest(BigDecimal carryoverImposeInterest) {
		this.carryoverImposeInterest = carryoverImposeInterest;
	}
	public Date getRecentlyDate() {
		return recentlyDate;
	}
	public void setRecentlyDate(Date recentlyDate) {
		this.recentlyDate = recentlyDate;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getRepayingFlag() {
		return repayingFlag;
	}
	public void setRepayingFlag(String repayingFlag) {
		this.repayingFlag = repayingFlag;
	}
	public String getRepayingCalFlag() {
		return repayingCalFlag;
	}
	public void setRepayingCalFlag(String repayingCalFlag) {
		this.repayingCalFlag = repayingCalFlag;
	}
	public String getRepayingFirstFlag() {
		return repayingFirstFlag;
	}
	public void setRepayingFirstFlag(String repayingFirstFlag) {
		this.repayingFirstFlag = repayingFirstFlag;
	}
	public BigDecimal getYearImposeInterest() {
		return yearImposeInterest;
	}
	public void setYearImposeInterest(BigDecimal yearImposeInterest) {
		this.yearImposeInterest = yearImposeInterest;
	}
	public BigDecimal getYearImpose90Interest() {
		return yearImpose90Interest;
	}
	public void setYearImpose90Interest(BigDecimal yearImpose90Interest) {
		this.yearImpose90Interest = yearImpose90Interest;
	}
	public Integer getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}
	
}

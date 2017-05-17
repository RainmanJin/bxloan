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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * ApprovalHistoryRepayPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REPAY_PLAN_Temp", schema = GlobalConstants.WD_SCHEMA)
public class RepayPlanTemp implements java.io.Serializable {

	// Fields

	private Long id;
	private String projectNo;
	private Long projectId;
	private Date repayDate;
	private BigDecimal repayAmt;
	private String customerNum;
	private Long customerId;
	private String customerName;
	private Date sysUpdateDate;
	private Date sysCreateDate;

	private String repayDateStr;

	// Constructors

	/** default constructor */
	public RepayPlanTemp() {
	}

	/** minimal constructor */
	public RepayPlanTemp(Long id, Long projectId, Long approvalHistoryId) {
		this.id = id;
		this.projectId = projectId;
	}

	/** full constructor */
	public RepayPlanTemp(Long id, String projectNo, Long projectId,
			Date repayDate, BigDecimal repayAmt, String customerNum,
			Long customerId, String customerName, Date sysUpdateDate,
			Date sysCreateDate) {
		this.id = id;
		this.projectNo = projectNo;
		this.projectId = projectId;
		this.repayDate = repayDate;
		this.repayAmt = repayAmt;
		this.customerNum = customerNum;
		this.customerId = customerId;
		this.customerName = customerName;
		this.sysUpdateDate = sysUpdateDate;
		this.sysCreateDate = sysCreateDate;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_REPAY_PLAN_TEMP", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PROJECT_NO", length = 40)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REPAY_DATE", length = 7)
	public Date getRepayDate() {
		return this.repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	@Column(name = "REPAY_AMT", precision = 20)
	public BigDecimal getRepayAmt() {
		return this.repayAmt;
	}

	public void setRepayAmt(BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	@Column(name = "CUSTOMER_NUM", length = 40)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "CUSTOMER_ID", precision = 12, scale = 0)
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CUSTOMER_NAME", length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_UPDATE_DATE", length = 7)
	public Date getSysUpdateDate() {
		return this.sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SYS_CREATE_DATE", length = 7)
	public Date getSysCreateDate() {
		return this.sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	@Transient
	public String getRepayDateStr() {
		return repayDateStr;
	}

	public void setRepayDateStr(String repayDateStr) {
		this.repayDateStr = repayDateStr;
	}

}
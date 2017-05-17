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
import javax.persistence.Transient;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * 还款计划
 */
@Entity
@Table(name = "REPAY_PLAN", schema = WD_SCHEMA)
public class RepayPlan implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6186187711692700111L;
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_REPAY_PLAN", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "REPAY_PLAN_ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long repayPlanId;
	@Column(name = "PROJECT_NO", length = 40)
	private String projectNo;
	@Column(name = "PROJECT_ID")
	private Long projectId;
	@Column(name = "REPAY_DATE")
	private Date repayDate; // 计划还款日期
	@Column(name = "REPAY_AMT", precision = 20, scale = 2)
	private BigDecimal repayAmt; // 计划还款金额
	@Column(name = "CUSTOMER_NUM", length = 40)
	private String customerNum;
	@Column(name = "CUSTOMER_ID")
	private Long customerId;
	@Column(name = "CUSTOMER_NAME", length = 100)
	private String customerName;
	@Column(name = "SYS_UPDATE_DATE")
	private Date sysUpdateDate;
	@Column(name = "SYS_CREATE_DATE")
	private Date sysCreateDate;

	@Transient
	private String repayDateStr;

	public Long getRepayPlanId() {
		return repayPlanId;
	}

	public void setRepayPlanId(Long repayPlanId) {
		this.repayPlanId = repayPlanId;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public BigDecimal getRepayAmt() {
		return repayAmt;
	}

	public void setRepayAmt(BigDecimal repayAmt) {
		this.repayAmt = repayAmt;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getSysUpdateDate() {
		return sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	public Date getSysCreateDate() {
		return sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	public String getRepayDateStr() {
		return repayDateStr;
	}

	public void setRepayDateStr(String repayDateStr) {
		this.repayDateStr = repayDateStr;
	}

}

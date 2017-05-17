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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * 征信记录负债明细（借款人）
 * @author WallenHeng
 */
@Entity
@Table(name = "DR_CREDIT_LIABILITY_DETAIL", schema = GlobalConstants.WD_SCHEMA)
public class DrCreditLiabilityDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6146695942811472327L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_DR_CREDIT_LIABILITY_DETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="DR_CREDIT_LIABILITY_DETAIL_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long drCreditLiabilityDetailId;
	/**
	 * 业务id
	 */
	@Column(name="PROJECT_ID",precision = 12, scale = 0)
	private Long projectId;
	/**
	 * 借款人姓名
	 */
	@Column(name="DR_NAME",length=30)
	private String drName;
	
	/**
	 * 金融机构
	 */
	@Column(name="FINANCIAL_ORG_NAME",length=50)
	private String financialOrgName;
	/**
	 * 贷款金额
	 */
	@Column(name="LOAN_AMT", precision = 20, scale = 2)
	private BigDecimal loanAmt;
	/**
	 * 贷款开始时间
	 */
	@Temporal(TemporalType.DATE)
	@Column(name="LOAN_START_DATE")
	private Date loanStartDate;
	/**
	 * 抵押物（1：房；2：车；3：其他；0：无）可多选
	 */
	@Column(name="PAWNS",length=200)
	private String pawns;
	/**
	 * 贷款剩余金额
	 */
	@Column(name="LOAN_OVER_AMT", precision = 20, scale = 2)
	private BigDecimal loanOverAmt;
	
	/**
	 * 还款方式
	 */
	@Column(name="REPAYMENT_MODE_CD",length=20)
	private String repaymentModeCd;
	
	/**
	 * 折合月均还款金额
	 */
	@Column(name="MONTH_AVG_REPAY_AMT", precision = 20, scale = 2)
	private BigDecimal monthAvgRepayAmt;
	/**
	 * 0:不显示，1：显示
	 */
	@Column(name="IS_SHOW", precision = 1, scale = 0)
	private Integer isShow;


	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getFinancialOrgName() {
		return financialOrgName;
	}

	public void setFinancialOrgName(String financialOrgName) {
		this.financialOrgName = financialOrgName;
	}

	public BigDecimal getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(BigDecimal loanAmt) {
		this.loanAmt = loanAmt;
	}

	public Date getLoanStartDate() {
		return loanStartDate;
	}

	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	public String getPawns() {
		return pawns;
	}

	public void setPawns(String pawns) {
		this.pawns = pawns;
	}

	public BigDecimal getLoanOverAmt() {
		return loanOverAmt;
	}

	public void setLoanOverAmt(BigDecimal loanOverAmt) {
		this.loanOverAmt = loanOverAmt;
	}


	public BigDecimal getMonthAvgRepayAmt() {
		return monthAvgRepayAmt;
	}

	public void setMonthAvgRepayAmt(BigDecimal monthAvgRepayAmt) {
		this.monthAvgRepayAmt = monthAvgRepayAmt;
	}

	public String getRepaymentModeCd() {
		return repaymentModeCd;
	}

	public void setRepaymentModeCd(String repaymentModeCd) {
		this.repaymentModeCd = repaymentModeCd;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Long getDrCreditLiabilityDetailId() {
		return drCreditLiabilityDetailId;
	}

	public void setDrCreditLiabilityDetailId(Long drCreditLiabilityDetailId) {
		this.drCreditLiabilityDetailId = drCreditLiabilityDetailId;
	}
	
	

}

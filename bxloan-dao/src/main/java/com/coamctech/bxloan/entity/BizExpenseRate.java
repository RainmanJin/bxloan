package com.coamctech.bxloan.entity;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

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

/**
 * BizExpenseRate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BIZ_EXPENSE_RATE",schema=WD_SCHEMA)
public class BizExpenseRate implements java.io.Serializable {

	// Fields

	private Long bizExpenseRateId;
	private Long projectId;
	private String projectNo;
	private String productCd;
	private String expenseName;//费用名称
	private BigDecimal standardExpenseRate;//标准费率
	private BigDecimal expenseRate;//实际费率
	private BigDecimal expenseAmt;
	private String currencyCd;
	private String expenseCollectionType;//收费方式
	private Date sysUpdateDate;
	private Date sysCreateDate;
	private BigDecimal standaredAmt;
	private String fundsSource;
	private String remark;
	private String transactionNo;
	private String transactionStatus;
	
	private String sysCreateDateStr;//格式化的日期
	private String sysUpdateDateStr;//格式化的日期

	// Constructors

	/** default constructor */
	public BizExpenseRate() {
	}

	/** minimal constructor */
	public BizExpenseRate(Long bizExpenseRateId, Long projectId) {
		this.bizExpenseRateId = bizExpenseRateId;
		this.projectId = projectId;
	}

	/** full constructor */
	public BizExpenseRate(Long bizExpenseRateId, Long projectId,
			String projectNo, String productCd, String expenseName,
			BigDecimal standardExpenseRate, BigDecimal expenseRate, BigDecimal expenseAmt,
			String currencyCd, String expenseCollectionType,
			Date sysUpdateDate, Date sysCreateDate,
			BigDecimal standaredAmt, String fundsSource, String remark,
			String transactionNo, String transactionStatus) {
		this.bizExpenseRateId = bizExpenseRateId;
		this.projectId = projectId;
		this.projectNo = projectNo;
		this.productCd = productCd;
		this.expenseName = expenseName;
		this.standardExpenseRate = standardExpenseRate;
		this.expenseRate = expenseRate;
		this.expenseAmt = expenseAmt;
		this.currencyCd = currencyCd;
		this.expenseCollectionType = expenseCollectionType;
		this.sysUpdateDate = sysUpdateDate;
		this.sysCreateDate = sysCreateDate;
		this.standaredAmt = standaredAmt;
		this.fundsSource = fundsSource;
		this.remark = remark;
		this.transactionNo = transactionNo;
		this.transactionStatus = transactionStatus;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_BIZ_EXPENSE_RATE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "BIZ_EXPENSE_RATE_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getBizExpenseRateId() {
		return this.bizExpenseRateId;
	}

	public void setBizExpenseRateId(Long bizExpenseRateId) {
		this.bizExpenseRateId = bizExpenseRateId;
	}

	@Column(name = "PROJECT_ID", nullable = false, precision = 12, scale = 0)
	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	@Column(name = "PROJECT_NO", length = 30)
	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PRODUCT_CD", length = 20)
	public String getProductCd() {
		return this.productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	@Column(name = "EXPENSE_NAME", length = 50)
	public String getExpenseName() {
		return this.expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	@Column(name = "STANDARD_EXPENSE_RATE", precision = 20, scale = 6)
	public BigDecimal getStandardExpenseRate() {
		return this.standardExpenseRate;
	}

	public void setStandardExpenseRate(BigDecimal standardExpenseRate) {
		this.standardExpenseRate = standardExpenseRate;
	}

	@Column(name = "EXPENSE_RATE", precision = 20, scale = 6)
	public BigDecimal getExpenseRate() {
		return this.expenseRate;
	}

	public void setExpenseRate(BigDecimal expenseRate) {
		this.expenseRate = expenseRate;
	}

	@Column(name = "EXPENSE_AMT", precision = 20)
	public BigDecimal getExpenseAmt() {
		return this.expenseAmt;
	}

	public void setExpenseAmt(BigDecimal expenseAmt) {
		this.expenseAmt = expenseAmt;
	}

	@Column(name = "CURRENCY_CD", length = 30)
	public String getCurrencyCd() {
		return this.currencyCd;
	}

	public void setCurrencyCd(String currencyCd) {
		this.currencyCd = currencyCd;
	}

	@Column(name = "EXPENSE_COLLECTION_TYPE", length = 30)
	public String getExpenseCollectionType() {
		return this.expenseCollectionType;
	}

	public void setExpenseCollectionType(String expenseCollectionType) {
		this.expenseCollectionType = expenseCollectionType;
	}

	@Column(name = "SYS_UPDATE_DATE", length = 7)
	public Date getSysUpdateDate() {
		return this.sysUpdateDate;
	}

	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}

	@Column(name = "SYS_CREATE_DATE", length = 7)
	public Date getSysCreateDate() {
		return this.sysCreateDate;
	}

	public void setSysCreateDate(Date sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	@Column(name = "STANDARED_AMT", precision = 20)
	public BigDecimal getStandaredAmt() {
		return this.standaredAmt;
	}

	public void setStandaredAmt(BigDecimal standaredAmt) {
		this.standaredAmt = standaredAmt;
	}

	@Column(name = "FUNDS_SOURCE", length = 1)
	public String getFundsSource() {
		return this.fundsSource;
	}

	public void setFundsSource(String fundsSource) {
		this.fundsSource = fundsSource;
	}

	@Column(name = "REMARK", length = 2000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TRANSACTION_NO", length = 100)
	public String getTransactionNo() {
		return this.transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	@Column(name = "TRANSACTION_STATUS", length = 1)
	public String getTransactionStatus() {
		return this.transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Transient
	public String getSysCreateDateStr() {
		return sysCreateDateStr;
	}

	public void setSysCreateDateStr(String sysCreateDateStr) {
		this.sysCreateDateStr = sysCreateDateStr;
	}

	@Transient
	public String getSysUpdateDateStr() {
		return sysUpdateDateStr;
	}

	public void setSysUpdateDateStr(String sysUpdateDateStr) {
		this.sysUpdateDateStr = sysUpdateDateStr;
	}
	

}
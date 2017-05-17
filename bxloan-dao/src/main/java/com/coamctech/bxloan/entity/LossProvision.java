package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 损失计提
 * @author WallenHeng
 * @since 2014-09-03 13:23:13
 */
@Entity
@Table(name = "LOSS_PROVISION", schema =WD_SCHEMA)
public class LossProvision implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2553397244478525707L;
	@Id
	@Column(name="LOSS_PROVISION_ID")
	private Long lossProvisionId;
	@Column(name="LOANAMOUNT",length=20,scale=2)
	private BigDecimal loanamount;
	@Column(name="LOANBALANCE",length=20,scale=2)
	private BigDecimal loanbalance;
	/**
	 * 对象维度类型 (1:按经办机构计提2:按单笔合同计提)
	 */
	@Column(name="OBJECT_DIMENSION_TYPE",length=1)
	private String objectDimensionType;
	@Column(name="SERIAL_NUM")
	private Integer serialNum;
	@Column(name="ORGID_OR_CONTRACTID",precision=20,scale=2)
	private Long orgIdOrContractId;
	@Column(name="DEPRECIATION_RATIO",precision=20,scale=2)
	private BigDecimal depreciationRatio;
	@Column(name="CREATEDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(name="PRIVISION_AMT",precision=20,scale=2)
	private BigDecimal privisionAmt;
	@Column(name="NORMAL_RATIO_AMT",precision=20,scale=2)
	private BigDecimal normalRatioAmt;
	@Column(name="CONCERN_RATIO_AMT",precision=20,scale=2)
	private BigDecimal concernRatioAmt;
	@Column(name="SUBPRIME_RATIO_AMT",precision=20,scale=2)
	private BigDecimal subprimeRatioAmt;
	@Column(name="SUSPICIOUS_RATIO_AMT",precision=20,scale=2)
	private BigDecimal suspiciousRatioAmt;
	@Column(name="LOSS_RATIO_AMT",precision=20,scale=2)
	private BigDecimal lossRatioAmt;
	@Column(name="QUANTITY_AMT",precision=20,scale=2)
	private BigDecimal quantityAmt;
	@Column(name="NORAL_LOANBALANCE",precision=20,scale=2)
	private BigDecimal noralLoanBalance;
	@Column(name="CONCERN_LOANBALANCE",precision=20,scale=2)
	private BigDecimal concernLoanBalance;
	@Column(name="SUBPRIME_LOANBALANCE",precision=20,scale=2)
	private BigDecimal subprimeLoanBalance;
	@Column(name="SUSPICIOUS_LOANBALANCE",precision=20,scale=2)
	private BigDecimal suspiciousLoanBalance;
	@Column(name="LOSS_LOANBALANCE",precision=20,scale=2)
	private BigDecimal lossLoanBalance;
	@Column(name="NORAL_PRIVISION_AMT",precision=20,scale=2)
	private BigDecimal noralPrivisionAmt;
	@Column(name="CONCERN_PRIVISION_AMT",precision=20,scale=2)
	private BigDecimal concernPrivisionAmt;
	@Column(name="SUBPRIME_PRIVISION_AMT",precision=20,scale=2)
	private BigDecimal subprimePrivisionAmt;
	@Column(name="SUSPICIOUS_PRIVISION_AMT",precision=20,scale=2)
	private BigDecimal suspiciousPrivisionAmt;
	@Column(name="LOSS_PRIVISION_AMT",precision=20,scale=2)
	private BigDecimal lossPrivisionAmt;
	@Column(name="MENTIONAMT",precision=20,scale=2)
	private BigDecimal mentionAmt;
	@Column(name="FIRST_MENTIONAMT",precision=20,scale=2)
	private BigDecimal firstMentionAmt;
	@Column(name="TRANSACTION_NO",length=100)
	private String transactionNo;
	/**
	 * 单据状态(1:发送未入账2:已入账3:已退单4:冲销未入账5:冲销已入账)
	 */
	@Column(name="TRANSACTION_STATUS",length=1)
	private String transactionStatus;
	public Long getLossProvisionId() {
		return lossProvisionId;
	}
	public void setLossProvisionId(Long lossProvisionId) {
		this.lossProvisionId = lossProvisionId;
	}
	public BigDecimal getLoanamount() {
		return loanamount;
	}
	public void setLoanamount(BigDecimal loanamount) {
		this.loanamount = loanamount;
	}
	public BigDecimal getLoanbalance() {
		return loanbalance;
	}
	public void setLoanbalance(BigDecimal loanbalance) {
		this.loanbalance = loanbalance;
	}
	public String getObjectDimensionType() {
		return objectDimensionType;
	}
	public void setObjectDimensionType(String objectDimensionType) {
		this.objectDimensionType = objectDimensionType;
	}
	public Integer getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}
	public Long getOrgIdOrContractId() {
		return orgIdOrContractId;
	}
	public void setOrgIdOrContractId(Long orgIdOrContractId) {
		this.orgIdOrContractId = orgIdOrContractId;
	}
	public BigDecimal getDepreciationRatio() {
		return depreciationRatio;
	}
	public void setDepreciationRatio(BigDecimal depreciationRatio) {
		this.depreciationRatio = depreciationRatio;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getPrivisionAmt() {
		return privisionAmt;
	}
	public void setPrivisionAmt(BigDecimal privisionAmt) {
		this.privisionAmt = privisionAmt;
	}
	public BigDecimal getNormalRatioAmt() {
		return normalRatioAmt;
	}
	public void setNormalRatioAmt(BigDecimal normalRatioAmt) {
		this.normalRatioAmt = normalRatioAmt;
	}
	public BigDecimal getConcernRatioAmt() {
		return concernRatioAmt;
	}
	public void setConcernRatioAmt(BigDecimal concernRatioAmt) {
		this.concernRatioAmt = concernRatioAmt;
	}
	public BigDecimal getSubprimeRatioAmt() {
		return subprimeRatioAmt;
	}
	public void setSubprimeRatioAmt(BigDecimal subprimeRatioAmt) {
		this.subprimeRatioAmt = subprimeRatioAmt;
	}
	public BigDecimal getSuspiciousRatioAmt() {
		return suspiciousRatioAmt;
	}
	public void setSuspiciousRatioAmt(BigDecimal suspiciousRatioAmt) {
		this.suspiciousRatioAmt = suspiciousRatioAmt;
	}
	public BigDecimal getLossRatioAmt() {
		return lossRatioAmt;
	}
	public void setLossRatioAmt(BigDecimal lossRatioAmt) {
		this.lossRatioAmt = lossRatioAmt;
	}
	public BigDecimal getQuantityAmt() {
		return quantityAmt;
	}
	public void setQuantityAmt(BigDecimal quantityAmt) {
		this.quantityAmt = quantityAmt;
	}
	public BigDecimal getNoralLoanBalance() {
		return noralLoanBalance;
	}
	public void setNoralLoanBalance(BigDecimal noralLoanBalance) {
		this.noralLoanBalance = noralLoanBalance;
	}
	public BigDecimal getConcernLoanBalance() {
		return concernLoanBalance;
	}
	public void setConcernLoanBalance(BigDecimal concernLoanBalance) {
		this.concernLoanBalance = concernLoanBalance;
	}
	public BigDecimal getSubprimeLoanBalance() {
		return subprimeLoanBalance;
	}
	public void setSubprimeLoanBalance(BigDecimal subprimeLoanBalance) {
		this.subprimeLoanBalance = subprimeLoanBalance;
	}
	public BigDecimal getSuspiciousLoanBalance() {
		return suspiciousLoanBalance;
	}
	public void setSuspiciousLoanBalance(BigDecimal suspiciousLoanBalance) {
		this.suspiciousLoanBalance = suspiciousLoanBalance;
	}
	public BigDecimal getLossLoanBalance() {
		return lossLoanBalance;
	}
	public void setLossLoanBalance(BigDecimal lossLoanBalance) {
		this.lossLoanBalance = lossLoanBalance;
	}
	public BigDecimal getNoralPrivisionAmt() {
		return noralPrivisionAmt;
	}
	public void setNoralPrivisionAmt(BigDecimal noralPrivisionAmt) {
		this.noralPrivisionAmt = noralPrivisionAmt;
	}
	public BigDecimal getConcernPrivisionAmt() {
		return concernPrivisionAmt;
	}
	public void setConcernPrivisionAmt(BigDecimal concernPrivisionAmt) {
		this.concernPrivisionAmt = concernPrivisionAmt;
	}
	public BigDecimal getSubprimePrivisionAmt() {
		return subprimePrivisionAmt;
	}
	public void setSubprimePrivisionAmt(BigDecimal subprimePrivisionAmt) {
		this.subprimePrivisionAmt = subprimePrivisionAmt;
	}
	public BigDecimal getSuspiciousPrivisionAmt() {
		return suspiciousPrivisionAmt;
	}
	public void setSuspiciousPrivisionAmt(BigDecimal suspiciousPrivisionAmt) {
		this.suspiciousPrivisionAmt = suspiciousPrivisionAmt;
	}
	public BigDecimal getLossPrivisionAmt() {
		return lossPrivisionAmt;
	}
	public void setLossPrivisionAmt(BigDecimal lossPrivisionAmt) {
		this.lossPrivisionAmt = lossPrivisionAmt;
	}
	public BigDecimal getMentionAmt() {
		return mentionAmt;
	}
	public void setMentionAmt(BigDecimal mentionAmt) {
		this.mentionAmt = mentionAmt;
	}
	public BigDecimal getFirstMentionAmt() {
		return firstMentionAmt;
	}
	public void setFirstMentionAmt(BigDecimal firstMentionAmt) {
		this.firstMentionAmt = firstMentionAmt;
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
	
}

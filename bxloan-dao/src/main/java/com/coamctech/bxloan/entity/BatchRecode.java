package com.coamctech.bxloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * 跑批记录
 * @author WallenHeng
 *
 */
@Entity
@Table(name = "BATCHRECODE",schema=WD_SCHEMA)
public class BatchRecode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6992270557833836917L;
	@EmbeddedId
	private BatchRecodePk brPk;
	@Column(name="PROJECT_ID")
	private Long projectId;
	@Column(name="LOAN_ID")
	private Long loanId;
	@Column(name="BATCHDATE")
	private Date batchDate;
	@Column(name="BATCHFLAG",length=1)
	private String batchFlag;
	/**
	 * 放款状态(1:发送未入账2:已入账3:已退单4:冲销未入账5:冲销已入账)
	 */
	@Column(name="PAYLOANSTATUS",length=1)
	private String payLoanStatus;
	@Column(name="OPERTIME")
	private Date operTime;
	@Column(name="TRANSACTION_NO",length=30)
	private String transactionNo;
	
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getLoanId() {
		return loanId;
	}
	public void setLoanId(Long loanId) {
		this.loanId = loanId;
	}
	public Date getBatchDate() {
		return batchDate;
	}
	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}
	public String getBatchFlag() {
		return batchFlag;
	}
	public void setBatchFlag(String batchFlag) {
		this.batchFlag = batchFlag;
	}
	public String getPayLoanStatus() {
		return payLoanStatus;
	}
	public void setPayLoanStatus(String payLoanStatus) {
		this.payLoanStatus = payLoanStatus;
	}
	public Date getOperTime() {
		return operTime;
	}
	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public BatchRecodePk getBrPk() {
		return brPk;
	}
	public void setBrPk(BatchRecodePk brPk) {
		this.brPk = brPk;
	}
	
	
	
	
	
	
	


}

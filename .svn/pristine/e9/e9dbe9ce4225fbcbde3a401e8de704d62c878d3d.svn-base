package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 账务信息
 * @author WallenHeng
 *
 */
@Entity
@Table(name = "ACCOUNTING_INFO",schema=WD_SCHEMA)
public class AccountingInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442302930744411538L;
	@EmbeddedId
	private AccountingInfoPk aiPk;
	@Column(name="PARTY_ID")
	private Long partyId;
	@Column(name="CONTRACTID")
	private Long contractId;
	@Column(name="PROJECTID")
	private Long projectId;
	@Column(name="LOANID")
	private Long loanId;
	@Column(name="SUITNO")
	private Integer suitNo;
	@Column(name="TXDATE")
	private Date txDate;
	@Column(name="ACCNO")
	private String accNo;
	@Column(name="ACCDESC")
	private String accDesc;
	@Column(name="DCMARK")
	private String dcmark;
	@Column(name="CCYID")
	private String ccyId;
	@Column(name="ACCAMT")
	private BigDecimal accAmt;
	@Column(name="VCHDATE")
	private Date vchDate;
	@Column(name="EVENTTYPE")
	private Integer eventType;
	@Column(name="ITEMID")
	private Integer itemId;
	@Column(name="BCHID")
	private String bchId;
	@Column(name="TRANSMITFLAG")
	private String transmitFlag;
	@Column(name="RECORDEDVCHFLAG")
	private String recordedVchFlag;
	@Column(name="SUMMARY")
	private String summary;
	@Column(name="LASTMODUSER")
	private String lastModUser;
	@Column(name="LASTMODDATE")
	private Date lastModDate;
	
	
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
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
	public Integer getSuitNo() {
		return suitNo;
	}
	public void setSuitNo(Integer suitNo) {
		this.suitNo = suitNo;
	}
	public Date getTxDate() {
		return txDate;
	}
	public void setTxDate(Date txDate) {
		this.txDate = txDate;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getAccDesc() {
		return accDesc;
	}
	public void setAccDesc(String accDesc) {
		this.accDesc = accDesc;
	}
	public String getCcyId() {
		return ccyId;
	}
	public void setCcyId(String ccyId) {
		this.ccyId = ccyId;
	}
	public BigDecimal getAccAmt() {
		return accAmt;
	}
	public void setAccAmt(BigDecimal accAmt) {
		this.accAmt = accAmt;
	}
	public Date getVchDate() {
		return vchDate;
	}
	public void setVchDate(Date vchDate) {
		this.vchDate = vchDate;
	}
	public Integer getEventType() {
		return eventType;
	}
	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getBchId() {
		return bchId;
	}
	public void setBchId(String bchId) {
		this.bchId = bchId;
	}
	public String getTransmitFlag() {
		return transmitFlag;
	}
	public void setTransmitFlag(String transmitFlag) {
		this.transmitFlag = transmitFlag;
	}
	public String getRecordedVchFlag() {
		return recordedVchFlag;
	}
	public void setRecordedVchFlag(String recordedVchFlag) {
		this.recordedVchFlag = recordedVchFlag;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getLastModUser() {
		return lastModUser;
	}
	public void setLastModUser(String lastModUser) {
		this.lastModUser = lastModUser;
	}
	public Date getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}
	public AccountingInfoPk getAiPk() {
		return aiPk;
	}
	public void setAiPk(AccountingInfoPk aiPk) {
		this.aiPk = aiPk;
	}
	public String getDcmark() {
		return dcmark;
	}
	public void setDcmark(String dcmark) {
		this.dcmark = dcmark;
	}

}

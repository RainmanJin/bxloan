package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 会计科目表字段
 *
 */
@Entity
@Table(name = "ACCOUNTING_SUBJECT",schema=WD_SCHEMA)
public class AccountingSubject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2414691060099923248L;
	@Id
	@Column(name="ACCNO")
	private String accNo;
	@Column(name="ACCDESC")
	private String accDesc;
	@Column(name="BWACCFLAG")
	private String bwaccFlag;
	@Column(name="BALANCE_DCMARK")
	private String balanceDcmark;
	@Column(name="ACCLEVEL")
	private String accLevel;
	@Column(name="ACCVER")
	private String accver;
	@Column(name="PARENT_ACCNO")
	private String parentAccno;
	@Column(name="LASTMODUSER")
	private String lastModUser;
	@Column(name="LASTMODDATE")
	private Date lastModDate;
	@Column(name="ASST_LIST",length=150)
	private String asstList;
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
	public String getBwaccFlag() {
		return bwaccFlag;
	}
	public void setBwaccFlag(String bwaccFlag) {
		this.bwaccFlag = bwaccFlag;
	}
	public String getBalanceDcmark() {
		return balanceDcmark;
	}
	public void setBalanceDcmark(String balanceDcmark) {
		this.balanceDcmark = balanceDcmark;
	}
	public String getAccLevel() {
		return accLevel;
	}
	public void setAccLevel(String accLevel) {
		this.accLevel = accLevel;
	}
	public String getAccver() {
		return accver;
	}
	public void setAccver(String accver) {
		this.accver = accver;
	}
	public String getParentAccno() {
		return parentAccno;
	}
	public void setParentAccno(String parentAccno) {
		this.parentAccno = parentAccno;
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
	public String getAsstList() {
		return asstList;
	}
	public void setAsstList(String asstList) {
		this.asstList = asstList;
	}
	
}

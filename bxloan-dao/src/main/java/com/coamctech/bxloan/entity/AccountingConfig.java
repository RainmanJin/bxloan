package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 账务配置
 */
@Entity
@Table(name = "ACCOUNTING_CONFIG",schema=WD_SCHEMA)
public class AccountingConfig implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2099112404699281372L;
	@EmbeddedId
	private AccountingConfigPk acPk;
	@Column(name="ACCNO",length=20)
	private String AccNo;
	@Column(name="DCMARK",length=1)
	private String dcmark;
	@Column(name="RELATCCY",length=10)
	private String relatccy;
	@Column(name="RELATBCH",length=1)
	private String relatbch;
	@Column(name="BCHID",length=30)
	private String bchId;
	@Column(name="LASTMODUSER",length=20)
	private String lastModUser;
	@Column(name="LASTMODDATE")
	private Timestamp lastModDate;
	@Column(name="FieldID",length=50)
	private String fieldId;
	@Column(name="SUMMARY",length=500)
	private String summary;
	@Column(name="DESCRIBE",length=500)
	private String describe;
	public AccountingConfigPk getAcPk() {
		return acPk;
	}
	public void setAcPk(AccountingConfigPk acPk) {
		this.acPk = acPk;
	}
	public String getAccNo() {
		return AccNo;
	}
	public void setAccNo(String accNo) {
		AccNo = accNo;
	}
	public String getDcmark() {
		return dcmark;
	}
	public void setDcmark(String dcmark) {
		this.dcmark = dcmark;
	}
	public String getRelatccy() {
		return relatccy;
	}
	public void setRelatccy(String relatccy) {
		this.relatccy = relatccy;
	}
	public String getRelatbch() {
		return relatbch;
	}
	public void setRelatbch(String relatbch) {
		this.relatbch = relatbch;
	}
	public String getBchId() {
		return bchId;
	}
	public void setBchId(String bchId) {
		this.bchId = bchId;
	}
	public String getLastModUser() {
		return lastModUser;
	}
	public void setLastModUser(String lastModUser) {
		this.lastModUser = lastModUser;
	}
	public Timestamp getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(Timestamp lastModDate) {
		this.lastModDate = lastModDate;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	
	
	
	

}

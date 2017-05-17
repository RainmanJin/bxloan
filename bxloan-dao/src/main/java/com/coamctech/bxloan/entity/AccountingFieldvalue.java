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
 * 交易栏位值
 *
 */
@Entity
@Table(name = "ACCOUNTING_FIELDVALUE",schema=WD_SCHEMA)
public class AccountingFieldvalue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7470412792064181398L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="ACCOUNTING_FIELDVALUE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="FIELDVALUEID")
	private Long fieldValueId;
	@Column(name="TXREFNO",length=50)
	private String txrefNo;
	@Column(name="FIELDID",length=4)
	private String fieldId;
	@Column(name="FIELDVALUE",precision=16,scale=4)
	private BigDecimal fieldValue;
	@Column(name="LASTMODDATE")
	private Date lastModDate;
	
	
	public AccountingFieldvalue() {
		super();
	}
	
	public AccountingFieldvalue(String fieldId, BigDecimal fieldValue) {
		super();
		this.fieldId = fieldId;
		this.fieldValue = fieldValue;
	}

	public Long getFieldValueId() {
		return fieldValueId;
	}
	public void setFieldValueId(Long fieldValueId) {
		this.fieldValueId = fieldValueId;
	}
	public String getTxrefNo() {
		return txrefNo;
	}
	public void setTxrefNo(String txrefNo) {
		this.txrefNo = txrefNo;
	}
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public BigDecimal getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(BigDecimal fieldValue) {
		this.fieldValue = fieldValue;
	}
	public Date getLastModDate() {
		return lastModDate;
	}
	public void setLastModDate(Date lastModDate) {
		this.lastModDate = lastModDate;
	}

}

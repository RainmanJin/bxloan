package com.coamctech.bxloan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class AccountingInfoPk implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "SEQNO", unique = true, nullable = false, precision = 12, scale = 0)
	private Long seqNo;
	@Column(name="TXREFNO",length=50)
	private String txrefNo;
	public Long getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(Long seqNo) {
		this.seqNo = seqNo;
	}
	public String getTxrefNo() {
		return txrefNo;
	}
	public void setTxrefNo(String txrefNo) {
		this.txrefNo = txrefNo;
	}
}

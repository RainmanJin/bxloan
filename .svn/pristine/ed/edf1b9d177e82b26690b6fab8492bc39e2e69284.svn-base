package com.coamctech.bxloan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BatchRecodePk implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7430510490153838807L;
	@Column(name="CONTRACT_ID")
	private Long contractId;
	@Column(name="BATCHTYPE",length=1)
	private String batchType;
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
}

package com.coamctech.bxloan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class ArrearsDetailHistoryPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7917227952258093085L;
	@Column(name="ARREARS_DETAIL_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long arrearsDetailId;
	
	@Column(name="SERIAL_NUM",precision=12,scale=0)
	private Long serialNum;

	public Long getArrearsDetailId() {
		return arrearsDetailId;
	}

	public void setArrearsDetailId(Long arrearsDetailId) {
		this.arrearsDetailId = arrearsDetailId;
	}

	public Long getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}
}

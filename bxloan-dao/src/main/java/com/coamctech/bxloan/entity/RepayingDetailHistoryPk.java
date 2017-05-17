package com.coamctech.bxloan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class RepayingDetailHistoryPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4664810105605630136L;
	@Column(name="REPAYING_DETAIL_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long repayingDetailId;
	@Column(name="SERIAL_NUM",precision=12,scale=0)
	private Long serialNum;
	public Long getRepayingDetailId() {
		return repayingDetailId;
	}
	public void setRepayingDetailId(Long repayingDetailId) {
		this.repayingDetailId = repayingDetailId;
	}
	public Long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}

}

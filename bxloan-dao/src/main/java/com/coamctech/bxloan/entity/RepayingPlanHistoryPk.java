package com.coamctech.bxloan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RepayingPlanHistoryPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1212849820958058664L;
	@Column(name = "REPAYING_PLAN_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long repayingPlanId;
	@Column(name="SERIAL_NUM",precision=12,scale=0)
	private Integer serialNum;
	@Column(name="VERSION")
	private Integer version;
	public Long getRepayingPlanId() {
		return repayingPlanId;
	}
	public void setRepayingPlanId(Long repayingPlanId) {
		this.repayingPlanId = repayingPlanId;
	}
	public Integer getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Integer serialNum) {
		this.serialNum = serialNum;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}

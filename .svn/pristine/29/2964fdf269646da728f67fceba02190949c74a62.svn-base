package com.coamctech.bxloan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class RepayingPlanDetailHistoryPk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5955489261621032203L;
	@Column(name="REPAYING_PLAN_DETAIL_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long repayingPlanDetailId;
	@Column(name="VERSION")
	private Integer version;
	@Column(name="SERIAL_NUM",precision=12,scale=0)
	private Long serialNum;
	public Long getRepayingPlanDetailId() {
		return repayingPlanDetailId;
	}
	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Long getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(Long serialNum) {
		this.serialNum = serialNum;
	}
}

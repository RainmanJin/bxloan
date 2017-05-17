package com.coamctech.bxloan.service.pettyloan.bo;

import java.io.Serializable;
import java.util.List;

import com.coamctech.bxloan.entity.RepayingPlanDetail;

public class LoanRpdInfoVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5872055157630567664L;
	/**
	 * 当期还款计划明细（还款计划列表中第一次未还）为空：已逾期或全部还清
	 */
	private RepayingPlanDetail curRpd;
	/**
	 * 当前之前的逾期期次(如果为空：没有逾期)
	 */
	private List<RepayingPlanDetail> overduePpds;
	public RepayingPlanDetail getCurRpd() {
		return curRpd;
	}
	public void setCurRpd(RepayingPlanDetail curRpd) {
		this.curRpd = curRpd;
	}
	public List<RepayingPlanDetail> getOverduePpds() {
		return overduePpds;
	}
	public void setOverduePpds(List<RepayingPlanDetail> overduePpds) {
		this.overduePpds = overduePpds;
	}
}

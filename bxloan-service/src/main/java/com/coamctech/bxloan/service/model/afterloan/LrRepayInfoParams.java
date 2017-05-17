package com.coamctech.bxloan.service.model.afterloan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LrRepayInfoParams implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8930635670269516041L;
	/**
	 * 合同Id
	 */
	private Long contractId;
	/**
	 * 当前用户机构Id
	 */
	private Long orgId;
	/**
	 * 逾期标记 true：逾期；false：正常，未逾期
	 */
	private boolean overdueFlag=false;
	
	/**
	 * 正常转逾期 	true：将未还转换为逾期未还 
	 */
	private boolean normal2Overdue=false;
	/**
	 * 还款编号
	 */
	private String repayLoanNum;
	/**
	 * 合同编号
	 */
	private String contractNum;
	/**
	 * 还款计划Id
	 */
	private Long repayingPlanId;
	/**
	 * 还款计划明细Id
	 */
	private Long repayingPlanDetailId;
	/**
	 * 还款日期
	 */
	private Date repayDate;
	
	/**
	 * 实还金额
	 */
	private BigDecimal actualAmt=BigDecimal.ZERO;
	/**
	 * 资金来源
	 */
	private String fundsSource;
	/**
	 * 提交人id
	 */
	private Long applyUserNum;
	/**
	 * 备注
	 */
	private String remark;
	
	public String getFundsSource() {
		return fundsSource;
	}
	public void setFundsSource(String fundsSource) {
		this.fundsSource = fundsSource;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public Long getRepayingPlanId() {
		return repayingPlanId;
	}
	public void setRepayingPlanId(Long repayingPlanId) {
		this.repayingPlanId = repayingPlanId;
	}
	public boolean isOverdueFlag() {
		return overdueFlag;
	}
	public void setOverdueFlag(boolean overdueFlag) {
		this.overdueFlag = overdueFlag;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public boolean isNormal2Overdue() {
		return normal2Overdue;
	}
	public void setNormal2Overdue(boolean normal2Overdue) {
		this.normal2Overdue = normal2Overdue;
	}
	public BigDecimal getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(BigDecimal actualAmt) {
		this.actualAmt = actualAmt;
	}
	public String getRepayLoanNum() {
		return repayLoanNum;
	}
	public void setRepayLoanNum(String repayLoanNum) {
		this.repayLoanNum = repayLoanNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setApplyUserNum(Long applyUserNum) {
		this.applyUserNum = applyUserNum;
	}
	public Long getApplyUserNum() {
		return applyUserNum;
	}
	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	public Long getRepayingPlanDetailId(){
		return repayingPlanDetailId;
	}
}

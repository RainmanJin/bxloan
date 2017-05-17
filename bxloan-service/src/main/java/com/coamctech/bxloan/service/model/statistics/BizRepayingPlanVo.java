package com.coamctech.bxloan.service.model.statistics;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;
/**   
 * 类名称：BizRepayingPlanVo
 * 类描述 ：还款计划具体信息
 * 创建人:王翔宇
 * 创建时间：2015年5月12日 下午5:00:35  
 * 修改人：王翔宇
 * 修改时间：
 * 修改备注：
 * 版本： V1.0
 */
public class BizRepayingPlanVo{
	private BigDecimal contractAmt;//合同金额
	private Long contractId;
	private Long partyId;
	private Long projectId;
	private Long payLoanId;
	private Long repayingPlanDetailId;
	private BigDecimal contractBalance;//合同余额
	private Integer currentPeriod;//期次
	private BigDecimal currentPrincipal;
	private Date currentEndDate;//应还日期
	private String contractStatus;
	private BigDecimal currentInterest;//应还利息
	private BigDecimal repayedPrincipal;//实还本金
	private BigDecimal repayedInterest;//实还利息
	private Date repayingDate;//还息日期
	private BigDecimal repayedImposeInterest;//实还逾期利息
	private BigDecimal currentPrincipalInterest;//应还本息
	private Date recentlyDate;//上次计息日期
	private BigDecimal notyetImposeInterest;//未还罚息
	private BigDecimal carryoverImposeInterest;//90天后逾期罚息
	private Date overdueDate;//逾期日期
	private Integer dueDay;//逾期天数
	private BigDecimal sumod;//逾期利息
	
	private Date payLoanDate;//放款日期
	private BigDecimal contractTermTotal;//合同期限
	private String contractTermUnitTotal;//单位
	private BigDecimal contractRate;//合同利率
	private String contractStatusCd;//合同状态
	
	public BizRepayingPlanVo() {
		super();
	}
	public BizRepayingPlanVo(Object[] objs, String str) {
		super();
		int i = 0;
		this.payLoanDate = CommonHelper.toDate(objs[i++]);
		this.contractTermTotal = CommonHelper.toBigDecimal(objs[i++]);
		this.contractTermUnitTotal = CommonHelper.toStr(objs[i++]);
		this.contractRate = CommonHelper.toBigDecimal(objs[i++]);
		this.contractStatusCd = CommonHelper.toStr(objs[i++]);
	}
	
	public BizRepayingPlanVo(Object[] objs) {
		super();
		int i = 0;
		this.contractAmt = CommonHelper.toBigDecimal(objs[i++]);
		this.contractId = CommonHelper.toLong(objs[i++]);
		this.partyId = CommonHelper.toLong(objs[i++]);
		this.projectId = CommonHelper.toLong(objs[i++]);
		this.payLoanId = CommonHelper.toLong(objs[i++]);
		this.contractBalance = CommonHelper.toBigDecimal(objs[i++]);
		this.currentPeriod = CommonHelper.toInt(objs[i++]);
		this.currentPrincipal = CommonHelper.toBigDecimal(objs[i++]);
		this.currentEndDate = CommonHelper.toDate(objs[i++]);
		this.contractStatus = CommonHelper.toStr(objs[i++]);
		this.currentInterest = CommonHelper.toBigDecimal(objs[i++]);
		this.currentPrincipalInterest = CommonHelper.toBigDecimal(objs[i++]);
		this.repayingPlanDetailId = CommonHelper.toLong(objs[i++]);
		this.repayedPrincipal = CommonHelper.toBigDecimal(objs[i++]);
		this.repayedInterest = CommonHelper.toBigDecimal(objs[i++]);
		this.repayingDate = CommonHelper.toDate(objs[i++]);
		this.repayedImposeInterest = CommonHelper.toBigDecimal(objs[i++]);
		this.sumod = CommonHelper.toBigDecimal(objs[i++]);
		this.recentlyDate = CommonHelper.toDate(objs[i++]);
		this.notyetImposeInterest = CommonHelper.toBigDecimal(objs[i++]);
		this.carryoverImposeInterest = CommonHelper.toBigDecimal(objs[i++]);
		this.overdueDate = CommonHelper.toDate(objs[i++]);
		this.dueDay = CommonHelper.toInt(objs[i++]);
	}
	
	
	public BigDecimal getContractTermTotal() {
		return contractTermTotal;
	}
	public void setContractTermTotal(BigDecimal contractTermTotal) {
		this.contractTermTotal = contractTermTotal;
	}
	
	public BigDecimal getContractRate() {
		return contractRate;
	}
	public void setContractRate(BigDecimal contractRate) {
		this.contractRate = contractRate;
	}
	public Date getPayLoanDate() {
		return payLoanDate;
	}
	public void setPayLoanDate(Date payLoanDate) {
		this.payLoanDate = payLoanDate;
	}
	public String getContractTermUnitTotal() {
		return contractTermUnitTotal;
	}
	public void setContractTermUnitTotal(String contractTermUnitTotal) {
		this.contractTermUnitTotal = contractTermUnitTotal;
	}
	public String getContractStatusCd() {
		return contractStatusCd;
	}
	public void setContractStatusCd(String contractStatusCd) {
		this.contractStatusCd = contractStatusCd;
	}
	public BigDecimal getContractAmt() {
		return contractAmt;
	}
	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public Long getPayLoanId() {
		return payLoanId;
	}
	public void setPayLoanId(Long payLoanId) {
		this.payLoanId = payLoanId;
	}
	
	public BigDecimal getContractBalance() {
		return contractBalance;
	}
	public void setContractBalance(BigDecimal contractBalance) {
		this.contractBalance = contractBalance;
	}
	public Integer getCurrentPeriod() {
		return currentPeriod;
	}
	public void setCurrentPeriod(Integer currentPeriod) {
		this.currentPeriod = currentPeriod;
	}
	public BigDecimal getCurrentPrincipal() {
		return currentPrincipal;
	}
	public void setCurrentPrincipal(BigDecimal currentPrincipal) {
		this.currentPrincipal = currentPrincipal;
	}
	public Date getCurrentEndDate() {
		return currentEndDate;
	}
	public void setCurrentEndDate(Date currentEndDate) {
		this.currentEndDate = currentEndDate;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public BigDecimal getCurrentInterest() {
		return currentInterest;
	}
	public void setCurrentInterest(BigDecimal currentInterest) {
		this.currentInterest = currentInterest;
	}
	public BigDecimal getRepayedPrincipal() {
		return repayedPrincipal;
	}
	public void setRepayedPrincipal(BigDecimal repayedPrincipal) {
		this.repayedPrincipal = repayedPrincipal;
	}
	public BigDecimal getRepayedInterest() {
		return repayedInterest;
	}
	public void setRepayedInterest(BigDecimal repayedInterest) {
		this.repayedInterest = repayedInterest;
	}
	public Date getRepayingDate() {
		return repayingDate;
	}
	public void setRepayingDate(Date repayingDate) {
		this.repayingDate = repayingDate;
	}
	public BigDecimal getRepayedImposeInterest() {
		return repayedImposeInterest;
	}
	public void setRepayedImposeInterest(BigDecimal repayedImposeInterest) {
		this.repayedImposeInterest = repayedImposeInterest;
	}
	public BigDecimal getCurrentPrincipalInterest() {
		return currentPrincipalInterest;
	}
	public void setCurrentPrincipalInterest(BigDecimal currentPrincipalInterest) {
		this.currentPrincipalInterest = currentPrincipalInterest;
	}
	public Date getRecentlyDate() {
		return recentlyDate;
	}
	public void setRecentlyDate(Date recentlyDate) {
		this.recentlyDate = recentlyDate;
	}
	public BigDecimal getNotyetImposeInterest() {
		return notyetImposeInterest;
	}
	public void setNotyetImposeInterest(BigDecimal notyetImposeInterest) {
		this.notyetImposeInterest = notyetImposeInterest;
	}
	public BigDecimal getCarryoverImposeInterest() {
		return carryoverImposeInterest;
	}
	public void setCarryoverImposeInterest(BigDecimal carryoverImposeInterest) {
		this.carryoverImposeInterest = carryoverImposeInterest;
	}
	public Date getOverdueDate() {
		return overdueDate;
	}
	public void setOverdueDate(Date overdueDate) {
		this.overdueDate = overdueDate;
	}
	public Integer getDueDay() {
		return dueDay;
	}
	public void setDueDay(Integer dueDay) {
		this.dueDay = dueDay;
	}
	public BigDecimal getSumod() {
		return sumod;
	}
	public void setSumod(BigDecimal sumod) {
		this.sumod = sumod;
	}
	public Long getRepayingPlanDetailId() {
		return repayingPlanDetailId;
	}
	public void setRepayingPlanDetailId(Long repayingPlanDetailId) {
		this.repayingPlanDetailId = repayingPlanDetailId;
	}
	
	
}











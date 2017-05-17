package com.coamctech.bxloan.service.model.afterloan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**
 * 类名称：NormalRepaymentVo
 * 类描述 ：
 * 创建人: 衡万里
 * 创建时间：2015年5月12日 上午10:39:46  
 * 修改人：衡万里
 * 修改时间：2015年5月12日 上午10:39:46  
 * 修改备注：
 * 版本： V1.0
 */
public class NormalRepaymentVo  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2711165646180669858L;
	//TODO 参数
	
	/**
	 * 开始时间
	 */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	/**
	 * 产品编号
	 */
	private String productCd;
	//TODO 输出
	/**
	 * 客户id
	 */
	private Long partyId;
	/**
	 * 业务申请id
	 */
	private Long projectId;
	/**
	 * 合同id
	 */
	private Long contractId;
	
	/**
	 * 还款计划明细id
	 * repaying_plan_detail id
	 */
	private Long rpdId;
	
	/**
	 * 还款计划id
	 */
	private Long rpId;
	/**
	 * 合同编号
	 */
	private String contractNum;
	
	/**
	 * 合同金额
	 */
	private BigDecimal contractAmt;
	/**
	 * 合同余额
	 */
	private BigDecimal contractBalance;
	/**
	 * 客户编号
	 */
	private String customerNum;
	/**
	 *	客户名称
	 */
	private String customerName;
	
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 联系方式
	 */
	private String contactWay;
	
	/**
	 * 已还期次
	 */
	private Integer repayedPeriod;
	/**
	 * 总期次
	 */
	private Integer totalPeriod;
	
	/**
	 * 当前期次
	 */
	private Integer currentPeriod;
	
	/**
	 * 应还总额
	 */
	private BigDecimal currentPrincipalInterest;
	/**
	 * 当期应还日期
	 */
	private Date currentEndDate;
	
	/**
	 * 本期已还本息
	 */
	private BigDecimal repayedImposeInterest;
	
	
	public NormalRepaymentVo() {
		
	}
	
	public NormalRepaymentVo(Object[] objs) {
		super();
		int i = 0;
		this.partyId=CommonHelper.toLong(objs[i++]);
		this.projectId=CommonHelper.toLong(objs[i++]);
		this.contractId=CommonHelper.toLong(objs[i++]);
		this.rpdId=CommonHelper.toLong(objs[i++]);
		this.rpId=CommonHelper.toLong(objs[i++]);
		this.currentPeriod=CommonHelper.toInt(objs[i++]);
		this.repayedPeriod=CommonHelper.toInt(objs[i++]);
		this.totalPeriod=CommonHelper.toInt(objs[i++]);
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.customerNum = CommonHelper.toStr(objs[i++]);
		this.customerName = CommonHelper.toStr(objs[i++]);
		this.productName=CommonHelper.toStr(objs[i++]);
		this.contractAmt=CommonHelper.toBigDecimal(objs[i++]);
		this.contractBalance=CommonHelper.toBigDecimal(objs[i++]);
		this.currentEndDate=CommonHelper.toDate(objs[i++]);
		this.currentPrincipalInterest=CommonHelper.toBigDecimal(objs[i++]);
		this.repayedImposeInterest=CommonHelper.toBigDecimal(objs[i++]);
		this.contactWay = CommonHelper.toStr(objs[i++]);
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public Integer getRepayedPeriod() {
		return repayedPeriod;
	}

	public void setRepayedPeriod(Integer repayedPeriod) {
		this.repayedPeriod = repayedPeriod;
	}

	public Integer getTotalPeriod() {
		return totalPeriod;
	}

	public void setTotalPeriod(Integer totalPeriod) {
		this.totalPeriod = totalPeriod;
	}

	public Date getCurrentEndDate() {
		return currentEndDate;
	}

	public void setCurrentEndDate(Date currentEndDate) {
		this.currentEndDate = currentEndDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}

	public Integer getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Integer currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public BigDecimal getCurrentPrincipalInterest() {
		return currentPrincipalInterest;
	}

	public void setCurrentPrincipalInterest(BigDecimal currentPrincipalInterest) {
		this.currentPrincipalInterest = currentPrincipalInterest;
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

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getRepayedImposeInterest() {
		return repayedImposeInterest;
	}

	public void setRepayedImposeInterest(BigDecimal repayedImposeInterest) {
		this.repayedImposeInterest = repayedImposeInterest;
	}

	public Long getRpdId() {
		return rpdId;
	}

	public void setRpdId(Long rpdId) {
		this.rpdId = rpdId;
	}

	public BigDecimal getContractBalance() {
		return contractBalance;
	}

	public void setContractBalance(BigDecimal contractBalance) {
		this.contractBalance = contractBalance;
	}

	public Long getRpId() {
		return rpId;
	}

	public void setRpId(Long rpId) {
		this.rpId = rpId;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}
}

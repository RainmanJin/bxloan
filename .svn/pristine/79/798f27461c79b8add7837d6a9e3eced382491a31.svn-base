package com.coamctech.bxloan.service.model.afterloan;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;


public class LoanRecoverContractVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3612282784452220316L;
	
	private Long contractId;
	private Long projectId;
	private String contractNum;
	private String customerName;
	private String contractStatusCd;
	private BigDecimal contractAmt;
	private BigDecimal contractBalance;
	private Date payloanDate;
	private Date lastRepayDate;
	private Integer repayedPeriod;
	private Integer totalPeriod;
	private String productName;
	
	public LoanRecoverContractVo(Object[] input) {
		super();
		this.contractId=CommonHelper.toLong(input[0]);
		this.contractNum=CommonHelper.toStr(input[1]);
		this.customerName=CommonHelper.toStr(input[2]);
		this.contractStatusCd=CommonHelper.toStr(input[3]);
		this.contractAmt=(BigDecimal)input[4];
		this.contractBalance=(BigDecimal)input[5];
		this.payloanDate=(Date)input[6];
		this.lastRepayDate=(Date)input[7];
		this.repayedPeriod=CommonHelper.toInt(input[8]);
		this.totalPeriod=CommonHelper.toInt(input[9]);
		this.productName=CommonHelper.toStr(input[10]);
		this.projectId = CommonHelper.toLong(input[11]);
	}
	public Long getContractId() {
		return contractId;
	}
	public String getContractNum() {
		return contractNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getContractStatusCd() {
		return contractStatusCd;
	}
	public BigDecimal getContractAmt() {
		return contractAmt;
	}
	public BigDecimal getContractBalance() {
		return contractBalance;
	}
	public Date getPayloanDate() {
		return payloanDate;
	}
	public String getPayloanDateStr(){
		return CommonHelper.date2Str(payloanDate, "yyyy-MM-dd");
	}
	public Date getLastRepayDate() {
		return lastRepayDate;
	}
	public String getLastRepayDateStr(){
		return CommonHelper.date2Str(lastRepayDate, "yyyy-MM-dd");
	}
	public Integer getRepayedPeriod() {
		return repayedPeriod;
	}
	public Integer getTotalPeriod() {
		return totalPeriod;
	}
	public String getProductName() {
		return productName;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
}

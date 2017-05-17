package com.coamctech.bxloan.service.model.afterloan;

import java.io.Serializable;
import java.util.Date;

import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;

public class ContractListParams implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6438963440981117788L;
	private String customerName;
	private String customerNum;
	private String contractNum;
	private String contractStatus;
	private Date startDate;
	private Date endDate;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = CommonHelper.str2Date(startDate, "yyyy-MM-dd");
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = CommonHelper.str2Date(endDate, "yyyy-MM-dd");
	}
}

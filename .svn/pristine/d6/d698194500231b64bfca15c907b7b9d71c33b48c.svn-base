package com.coamctech.bxloan.service.model.statistics;

import java.io.Serializable;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class BizPayLoanInfoVo implements Serializable {
	private Long projectId;
	private Long contractId;
	private String contractNo;
	private String customerName;
	/**
	 * 贷款发送日
	 */
	private Date payLoanDate;
	/**
	 * 客户经理
	 */
	private String custManagerName;
	
	
	
	
	public BizPayLoanInfoVo(Object[] objs) {
		super();
		int i=0;
		this.projectId=CommonHelper.toLong(objs[i++]);
		this.contractId=CommonHelper.toLong(objs[i++]);
		this.contractNo=CommonHelper.toStr(objs[i++]);
		this.customerName=CommonHelper.toStr(objs[i++]);
		this.payLoanDate=(Date)objs[i++];
		this.custManagerName=CommonHelper.toStr(objs[i++]);
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
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Date getPayLoanDate() {
		return payLoanDate;
	}
	public void setPayLoanDate(Date payLoanDate) {
		this.payLoanDate = payLoanDate;
	}
	public String getCustManagerName() {
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}
}

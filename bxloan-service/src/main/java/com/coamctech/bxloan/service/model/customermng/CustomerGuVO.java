package com.coamctech.bxloan.service.model.customermng;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class CustomerGuVO {
	private Long partyId;
	private String customerNum;
	private String customerName;
	private String partyTypeCd;
	private String certificateTypeCd;
	private String certificateNum;
	private String status;
	private String custManger;
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
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
	public String getPartyTypeCd() {
		return partyTypeCd;
	}
	public void setPartyTypeCd(String partyTypeCd) {
		this.partyTypeCd = partyTypeCd;
	}
	public String getCertificateTypeCd() {
		return certificateTypeCd;
	}
	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}
	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	
	public CustomerGuVO(Long partyId, String customerNum, String customerName,
			String partyTypeCd, String certificateTypeCd, String certificateNum) {
		super();
		this.partyId = partyId;
		this.customerNum = customerNum;
		this.customerName = customerName;
		this.partyTypeCd = partyTypeCd;
		this.certificateTypeCd = certificateTypeCd;
		this.certificateNum = certificateNum;
	}
	public CustomerGuVO() {
		super();
	}
	public CustomerGuVO(Object[] input) {
		int i = 0;
		this.partyId = CommonHelper.toLong(input[i++]);
		this.customerNum = CommonHelper.toStr(input[i++]);
		this.customerName = CommonHelper.toStr(input[i++]);
		this.partyTypeCd = CommonHelper.toStr(input[i++]);
		this.certificateTypeCd = CommonHelper.toStr(input[i++]);
		this.certificateNum = CommonHelper.toStr(input[i++]);
		this.status = CommonHelper.toStr(input[i++]);
		this.custManger = CommonHelper.toStr(input[i++]);
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCustManger() {
		return custManger;
	}
	public void setCustManger(String custManger) {
		this.custManger = custManger;
	}
	
}

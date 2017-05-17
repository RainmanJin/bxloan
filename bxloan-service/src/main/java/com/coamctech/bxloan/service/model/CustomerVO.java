package com.coamctech.bxloan.service.model;

import java.io.Serializable;

public class CustomerVO implements Serializable{
	private Long partyId;
	private String customerNum;   //客户编号
	private String chineseName;   //客户名称
	private String customerTypeName; //客户类型
	private String orgnNum;      //组织机构代码
	private String businessLicenseNum;   //营业执照号码
	private String loanCardNum;    //贷款卡号
	private String lastMaintainDate;  //更新日期
	private String customerMaintStatCdCN;//客户状态
	
	private String hmName;  //客户高管姓名(法定代表人)
	private String addressNameCN;  //企业中文地址
	private String zipNum;  //企业中文地址对应邮编
	private String certificateTypeCd;  //个人证件类别
	private String certificateNum;//个人证件号码
	private String linkmanName;//联系人姓名--企业信息
	private String linkmanTel;//联系人电话--企业信息
	private String linkmanFax;//联系人传真--企业信息
	private String email;//电子邮件
	private String partyTypeCd;//类型
	
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	public String getOrgnNum() {
		return orgnNum;
	}
	public void setOrgnNum(String orgnNum) {
		this.orgnNum = orgnNum;
	}
	public String getBusinessLicenseNum() {
		return businessLicenseNum;
	}
	public void setBusinessLicenseNum(String businessLicenseNum) {
		this.businessLicenseNum = businessLicenseNum;
	}
	public String getLoanCardNum() {
		return loanCardNum;
	}
	public void setLoanCardNum(String loanCardNum) {
		this.loanCardNum = loanCardNum;
	}
	public String getLastMaintainDate() {
		return lastMaintainDate;
	}
	public void setLastMaintainDate(String lastMaintainDate) {
		this.lastMaintainDate = lastMaintainDate;
	}
	public String getCustomerMaintStatCdCN() {
		return customerMaintStatCdCN;
	}
	public void setCustomerMaintStatCdCN(String customerMaintStatCdCN) {
		this.customerMaintStatCdCN = customerMaintStatCdCN;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getHmName() {
		return hmName;
	}
	public void setHmName(String hmName) {
		this.hmName = hmName;
	}
	public String getAddressNameCN() {
		return addressNameCN;
	}
	public void setAddressNameCN(String addressNameCN) {
		this.addressNameCN = addressNameCN;
	}
	public String getZipNum() {
		return zipNum;
	}
	public void setZipNum(String zipNum) {
		this.zipNum = zipNum;
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
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public String getLinkmanTel() {
		return linkmanTel;
	}
	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}
	public String getLinkmanFax() {
		return linkmanFax;
	}
	public void setLinkmanFax(String linkmanFax) {
		this.linkmanFax = linkmanFax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPartyTypeCd() {
		return partyTypeCd;
	}
	public void setPartyTypeCd(String partyTypeCd) {
		this.partyTypeCd = partyTypeCd;
	}
}

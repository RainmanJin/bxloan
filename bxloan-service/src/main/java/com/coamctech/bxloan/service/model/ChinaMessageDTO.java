package com.coamctech.bxloan.service.model;

public class ChinaMessageDTO {
	private String sendMobile;//发送人号码。
	private String sendUser;//发送人姓名。
	private String[] destMobile;//目的号码，可以有多个。
	private String  sendComtent;//短信内容。
	private Integer orgId;//机构id
	private String productType;//产品类型
	
	private String flag;//标志位
	
	public String getSendMobile() {
		return sendMobile;
	}
	public void setSendMobile(String sendMobile) {
		this.sendMobile = sendMobile;
	}
	public String getSendUser() {
		return sendUser;
	}
	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}
	public String[] getDestMobile() {
		return destMobile;
	}
	public void setDestMobile(String[] destMobile) {
		this.destMobile = destMobile;
	}
	public String getSendComtent() {
		return sendComtent;
	}
	public void setSendComtent(String sendComtent) {
		this.sendComtent = sendComtent;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}

package com.coamctech.bxloan.web.vo.corpcustomer;

import com.coamctech.bxloan.commons.entity.BaseEntity;

public class EntcusDisplayVO extends BaseEntity{

	private String partyId;
	private String cusNum;
	private String partyName;
	private String partyTypeCd;
	private String certificateTypeCd;
	private String certificateNum;
	private String createDate;
	private String markType;
	private String status;
	
	
	private String str(Object obj ){
		return obj==null?"":obj.toString().trim();
	}
	
	
	public EntcusDisplayVO(){
		super();
	}
	
	public EntcusDisplayVO(Object[] data){
		super();
		int index = 0;
		this.setPartyId(str(data[index++]));
		this.setCusNum(str(data[index++]));
		this.setPartyName(str(data[index++]));
		this.setPartyTypeCd(str(data[index++]));
		this.setCertificateTypeCd(str(data[index++]));
		this.setCertificateNum(str(data[index++]));
		this.setCreateDate(str(data[index++]));
		this.setMarkType(str(data[index++]));
		this.setStatus(str(data[index++]));
	}
	
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getCusNum() {
		return cusNum;
	}
	public void setCusNum(String cusNum) {
		this.cusNum = cusNum;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getMarkType() {
		return markType;
	}
	public void setMarkType(String markType) {
		this.markType = markType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}

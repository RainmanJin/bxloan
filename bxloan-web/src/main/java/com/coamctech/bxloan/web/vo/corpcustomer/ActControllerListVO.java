package com.coamctech.bxloan.web.vo.corpcustomer;

import com.coamctech.bxloan.datadict.DataDict;

/**
 * 实际控制人列表显示VO
 * @author xc
 *
 */
public class ActControllerListVO {
	
	private String id;
	private String partyId;
	private String name;
	private String familyAddress;
	private String contactTelNum;
	private String familyTelNum;
	private String certificateCd;
	private String certificateTypeCd;
	private String createTime;
	private String relaType;//法人(c)或自然人(p)
	
	public ActControllerListVO(){super();}
	
	public ActControllerListVO(Object[] input) {
		super();
		int index = 0;
		this.id = str(input[index++]);
		this.partyId = str(input[index++]);
		this.name = str(input[index++]);
		this.certificateTypeCd = str(input[index++]);
		this.certificateCd = str(input[index++]);
		this.familyTelNum = str(input[index++]);
		this.contactTelNum = str(input[index++]);
		this.familyAddress = str(input[index++]);
		this.createTime = str(input[index++]);
		this.relaType = str(input[index++]);
	}

	private String str(Object obj){
		return obj==null?"":obj.toString().trim();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getContactTelNum() {
		return contactTelNum;
	}
	public void setContactTelNum(String contactTelNum) {
		this.contactTelNum = contactTelNum;
	}
	public String getFamilyTelNum() {
		return familyTelNum;
	}
	public void setFamilyTelNum(String familyTelNum) {
		this.familyTelNum = familyTelNum;
	}
	public String getCertificateCd() {
		return certificateCd;
	}
	public void setCertificateCd(String certificateCd) {
		this.certificateCd = certificateCd;
	}
	public String getCertificateTypeCd() {
		return certificateTypeCd;
	}
	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRelaType() {
		return relaType;
	}
	public void setRelaType(String relaType) {
		this.relaType = relaType;
	}
	
}

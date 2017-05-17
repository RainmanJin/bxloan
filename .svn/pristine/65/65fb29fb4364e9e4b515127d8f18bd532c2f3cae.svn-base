package com.coamctech.bxloan.service.model.corpcustomer;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.vo.BasicVO;

/**
 * 选选取已有客户列表VO
 * @author xc
 */
public class PartyListInfo extends BasicVO{

	protected String existingPartyId;
	protected String customerNum;
	protected String partyName;
	protected String certificateTypeCd;
	protected String certificateTypeStr;
	protected String certificateNum;
	protected String status;
	protected String cusManaName;
	
	public PartyListInfo() {
		super();
	}
	public PartyListInfo(Object[] data) {
		super();
		int index = 0;
		this.setExistingPartyId(str(data[index++]));
		this.setCustomerNum(str(data[index++]));
		this.setPartyName(str(data[index++]));
		this.setCertificateTypeCd(str(data[index++]));
		this.setCertificateTypeStr(str(data[index++]));
		this.setCertificateNum(str(data[index++]));
		this.setStatus(str(data[index++]));
		this.setCusManaName(str(data[index++]));
	}

	////////////////////////
	/////GETTERS&SETTERS///
	////////////////////////
	public String getStatusStr(){
		return StringUtils.equals("2", this.getStatus())?"生效":"未生效";
	}
	public String getExistingPartyId() {
		return existingPartyId;
	}
	public void setExistingPartyId(String existingPartyId) {
		this.existingPartyId = existingPartyId;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getCertificateTypeStr() {
		return certificateTypeStr;
	}
	public void setCertificateTypeStr(String certificateTypeStr) {
		this.certificateTypeStr = certificateTypeStr;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCusManaName() {
		return StringUtils.isBlank(cusManaName)?"-":cusManaName;
	}
	public void setCusManaName(String cusManaName) {
		this.cusManaName = cusManaName;
	}
		

}

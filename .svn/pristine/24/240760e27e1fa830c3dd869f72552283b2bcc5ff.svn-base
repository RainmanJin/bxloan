package com.coamctech.bxloan.service.model.customermng;

import com.coamctech.bxloan.commons.vo.BasicVO;

public class CusManagerTeamSaveVO extends BasicVO{
	
	private String cmTeamId;
	private String orgName;
	private String depName;
	private String userName;
	private String userLogonName;
	
	private String managerType;
	private String userNum;
	private String roleCd;
	private String orgCd;
	private String partyId;
	private String customerNum;
	
	private String deptCd;
	private String cmTeamIds;
	
	public CusManagerTeamSaveVO(String orgName, String depName,
			String userName, String userLogonName, String userNum,
			String orgCd, String deptCd) {
		this.orgName = orgName;
		this.depName = depName;
		this.userName = userName;
		this.userLogonName = userLogonName;
		this.userNum = userNum;
		this.deptCd = deptCd;
		this.orgCd = orgCd;
	}
	
	public CusManagerTeamSaveVO(Object[] obj) {
		int index = 0;
		this.cmTeamId = str(obj[index++]);
		this.partyId = str(obj[index++]);
		this.orgCd = str(obj[index++]);
		this.deptCd = str(obj[index++]);
		this.customerNum = str(obj[index++]);
		this.userNum = str(obj[index++]);
		this.userLogonName = str(obj[index++]);
		this.managerType = str(obj[index++]);
		this.roleCd = str(obj[index++]);
		this.userName = str(obj[index++]);
		this.depName = str(obj[index++]);
		this.orgName = str(obj[index++]);
	}
	
	public CusManagerTeamSaveVO() {}
	
	//////////////////
	//////FOR COPY////
	//////////////////
	public Long getCmTeamId() {
		return strToLong(cmTeamId);
	}
	public Long getPartyId() {
		return strToLong(partyId);
	}
	/////////////////////
	////GETTERS&SETTERS///
	//////////////////////
	public void setCmTeamId(String cmTeamId) {
		this.cmTeamId = cmTeamId;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getRoleCd() {
		return roleCd;
	}
	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getDeptCd() {
		return deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLogonName() {
		return userLogonName;
	}
	public void setUserLogonName(String userLogonName) {
		this.userLogonName = userLogonName;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getCmTeamIds() {
		return cmTeamIds;
	}
	public void setCmTeamIds(String cmTeamIds) {
		this.cmTeamIds = cmTeamIds;
	}
}

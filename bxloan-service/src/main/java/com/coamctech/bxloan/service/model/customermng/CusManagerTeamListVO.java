package com.coamctech.bxloan.service.model.customermng;

import com.coamctech.bxloan.commons.vo.BasicVO;

public class CusManagerTeamListVO extends BasicVO{

	private String id;
	private String name;
	private String managerType;
	private String managerTypeName;
	private String orgCd;
	private String orgName;
	
	public CusManagerTeamListVO(){super();}
	
	public CusManagerTeamListVO(Object[] data){
		int index = 0;
		this.setId(str(data[index++]));
		this.setName(str(data[index++]));
		this.setManagerType(str(data[index++]));
		this.setManagerTypeName(str(data[index++]));
		this.setOrgCd(str(data[index++]));
		this.setOrgName(str(data[index++]));
	}
	
	/////////////////////////
	////GETTERS&SETTERS////
	//////////////////////////
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	public String getManagerTypeName() {
		return managerTypeName;
	}
	public void setManagerTypeName(String managerTypeName) {
		this.managerTypeName = managerTypeName;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
}

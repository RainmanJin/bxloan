package com.coamctech.bxloan.web.vo.bizapply;

public class CoCustomerManager {
	private String personId;//协办客户经理id
	private String personName;//协办客户经理名称
	private boolean isSelected;//是否是已选内容
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}

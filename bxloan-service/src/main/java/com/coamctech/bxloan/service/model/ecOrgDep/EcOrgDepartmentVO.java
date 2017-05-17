package com.coamctech.bxloan.service.model.ecOrgDep;

public class EcOrgDepartmentVO {

	private Long id;
	private Long parentdepartmentid;
	private String name;
	private String description;
	private Boolean isSelected;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentdepartmentid() {
		return parentdepartmentid;
	}
	public void setParentdepartmentid(Long parentdepartmentid) {
		this.parentdepartmentid = parentdepartmentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	
}

package com.coamctech.bxloan.service.model.message;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class MessageDTO {
	private Long userId;
	private String personName;
	private String deptName;
	private String stageName;
	private String personLogName;
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getPersonLogName() {
		return personLogName;
	}
	public void setPersonLogName(String personLogName) {
		this.personLogName = personLogName;
	}
	

	private MessageDTO(Long userId, String personName, String deptName,
			String stageName, String personLogName) {
		super();
		this.userId = userId;
		this.personName = personName;
		this.deptName = deptName;
		this.stageName = stageName;
		this.personLogName = personLogName;
	}
	public MessageDTO() {
		super();
	}
	public MessageDTO(Object[] input) {
		int i=0;
		this.userId = CommonHelper.toLong(input[i++]);
		this.personName = CommonHelper.toStr(input[i++]);
		this.personLogName = CommonHelper.toStr(input[i++]);
		this.deptName = CommonHelper.toStr(input[i++]);
		this.stageName = CommonHelper.toStr(input[i++]);
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}

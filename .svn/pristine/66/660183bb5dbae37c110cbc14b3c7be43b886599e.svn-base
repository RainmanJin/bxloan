package com.coamctech.bxloan.web.vo.sysmng;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class CustManagerVO {
	private Long id;
	private String custManagerName;
	private String orgName;
	private Long orgId;
	private String states;
	
	
	
	public CustManagerVO() {
		super();
	}
	public CustManagerVO(Long id, String custManagerName, String orgName,
			Long orgId, String states) {
		super();
		this.id = id;
		this.custManagerName = custManagerName;
		this.orgName = orgName;
		this.orgId = orgId;
		this.states = states;
	}
	public CustManagerVO(Object[] input) {
		int i = 0;
		this.id = CommonHelper.toLong(input[i++]);
		this.custManagerName = CommonHelper.toStr(input[i++]);
		this.orgName = CommonHelper.toStr(input[i++]);
		this.orgId = CommonHelper.toLong(input[i++]);
		this.states = CommonHelper.toStr(input[i++]);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustManagerName() {
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	
	
	
}

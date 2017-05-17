package com.coamctech.bxloan.web.security.cas;

import org.apache.shiro.cas.CasToken;

public class MyCasToken extends CasToken{
	
	private static final long serialVersionUID = 1L;
	
	private String logName = null;
	
	private String logOrgId = null;
	
	
	public MyCasToken(String ticket) {
		super(ticket);
	}
	
	public MyCasToken(String ticket, String logName, String logOrgId) {
		super(ticket);
		this.logName = logName;
		this.logOrgId = logOrgId;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getLogOrgId() {
		return logOrgId;
	}

	public void setLogOrgId(String logOrgId) {
		this.logOrgId = logOrgId;
	}
}

package com.coamctech.bxloan.commons.security;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 在用户名密码令牌的基础上增加验证码支持
 * 
 * @author wh
 *
 */
@SuppressWarnings("serial")
public class OrgUsernamePasswordToken extends UsernamePasswordToken {
	
	private String orgid;

	public OrgUsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String orgid) {
		super(username, password, rememberMe, host);
		this.orgid = orgid;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
}

package com.coamctech.bxloan.service.model.userinfo;

import com.coamctech.bxloan.commons.vo.BasicVO;


/**
 * 查看用户信息VO
 * @author xc
 */
public class UserInfoDetailVO extends BasicVO {
	
	protected String logname;
	protected String fixedphone;
	protected String mobilephone;
	protected String email;
	protected String remarks;
	
	public UserInfoDetailVO(){super();}
	
	public UserInfoDetailVO(Object[] data) {
		int index = 0;
		this.setLogname(str(data[index++]));
		this.setFixedphone(str(data[index++]));
		this.setMobilephone(str(data[index++]));
		this.setEmail(str(data[index++]));
		this.setRemarks(str(data[index++]));
	}
	/////////////////
	///GETTERS&SETTERS////
	/////////////////////////
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getFixedphone() {
		return fixedphone;
	}
	public void setFixedphone(String fixedphone) {
		this.fixedphone = fixedphone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}

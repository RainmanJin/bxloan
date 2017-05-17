package com.coamctech.bxloan.web.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.coamctech.bxloan.entity.EcFunctiontreenode;
import com.coamctech.bxloan.entity.EcOrgPerson;
import com.coamctech.bxloan.entity.EcOrgPersonconnrole;
import com.coamctech.bxloan.service.enums.DataAuthorityType;

@SuppressWarnings("serial")
public class ShiroUser implements Serializable {
	private Long id;
	private String logname;
	private String logpass;
	private String name;
	private Long orgid;				//所属机构ID
	private String orgname;
	private String usernum;
	private Long logOrgid;			//登陆机构ID
	private String  logOrgname; 	//登陆机构名称
	private String logOrgDesc;		//登陆机构简称
	private List<EcFunctiontreenode> list;
	private List<EcOrgPersonconnrole> roles;
	/**
	 * 数据权限类型<br/>
	 * dataAuthType：HeadOffice（总部），dataAuthOrgIds包含一个或多个机构<br/>
	 * 查询时dataAuthOrgIds<br/>
	 * dataAuthType：LoanCompany（小贷公司），dataAuthOrgIds包含一个即logOrgid<br/>
	 * 查询时logOrgid<br/>
	 * dataAuthType：CustomerManager（客户经理），dataAuthOrgIds包含一个即logOrgid<br/>
	 * 查询时logOrgid,id(当前用户id)<br/>
	 */
	private DataAuthorityType dataAuthType;
	
	/**
	 * 数据权限-当前登录人拥有所有机构id集合
	 */
	private List<Long> dataAuthOrgIds;
	
	
	private String loginName;//用户名（工号）
	List<EcOrgPerson> persons = new ArrayList<EcOrgPerson>(); // 工号对应的账号

 	public ShiroUser() {
 		super();
	}
 	
 	///////////////
 	////GETTERS&SETTERS///
 	/////////////////////
 	
	public Long getId() {
		return id;
	}
	public List<EcOrgPersonconnrole> getRoles() {
		return roles;
	}

	public void setRoles(List<EcOrgPersonconnrole> roles) {
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getLogname() {
		return logname;
	}
	public void setLogname(String logname) {
		this.logname = logname;
	}
	public String getLogpass() {
		return logpass;
	}
	public void setLogpass(String logpass) {
		this.logpass = logpass;
	}
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<EcFunctiontreenode> getList() {
		return list;
	}
	public void setList(List<EcFunctiontreenode> list) {
		this.list = list;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getUsernum() {
		return usernum;
	}
	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}
	public Long getLogOrgid() {
		return logOrgid;
	}
	public void setLogOrgid(Long logOrgid) {
		this.logOrgid = logOrgid;
	}
	public String getLogOrgname() {
		return logOrgname;
	}
	public void setLogOrgname(String logOrgname) {
		this.logOrgname = logOrgname;
	}
	public String getLogOrgDesc() {
		return logOrgDesc;
	}
	public void setLogOrgDesc(String logOrgDesc) {
		this.logOrgDesc = logOrgDesc;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public List<EcOrgPerson> getPersons() {
		return persons;
	}

	public void setPersons(List<EcOrgPerson> persons) {
		this.persons = persons;
	}


	/**
	 * 本函数输出将作为默认的<shiro:principal/>输出.
	 */
	@Override
	public String toString() {
		return name;
	}

	public List<Long> getDataAuthOrgIds() {
		return dataAuthOrgIds;
	}

	public void setDataAuthOrgIds(List<Long> dataAuthOrgIds) {
		this.dataAuthOrgIds = dataAuthOrgIds;
	}

	public DataAuthorityType getDataAuthType() {
		return dataAuthType;
	}

	public void setDataAuthType(DataAuthorityType dataAuthType) {
		this.dataAuthType = dataAuthType;
	}
}

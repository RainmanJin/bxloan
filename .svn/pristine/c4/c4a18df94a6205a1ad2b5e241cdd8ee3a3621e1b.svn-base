package com.coamctech.bxloan.entity;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EC_ORG_PERSON", schema = WD_SCHEMA)
public class EcOrgPerson implements java.io.Serializable {

	private Long id;
	private String logname;
	private String logpass;
	private String name;
	private Integer state;
	private Long orgid;
	private String usernum;
	private Integer logineortimes;
	private String deptname;
	private String msg;
	private String empnum;
	
	//部门表里的部门名称
	private String deptName;
	//前台页面操作显示
	private String operation;
	
	/***非持久属性***/
	
	private String catpcha;

	private String credentialsSalt;
	
	private List<EcOrgDepartment> orgs;//当前登录账号对应的机构列表
	// Constructors

	/** default constructor */
	public EcOrgPerson() {
	}

	public EcOrgPerson(String usernum, String logname, String name,
			String deptName) {
		super();
		this.logname = logname;
		this.name = name;
		this.usernum = usernum;
		this.deptName = deptName;
	}
	//add by wangpeng on 2015-07-22 start
	public EcOrgPerson(String name,Long id){
		super();
		this.id = id;
		this.name = name;
	}
	//add by wangpeng on 2015-07-22 end
	public EcOrgPerson(Long id, String logname, String logpass, String name,
			Integer state, Long orgid, String usernum, Integer logineortimes,
			String deptname, String msg, String deptName2, String operation) {
		super();
		this.id = id;
		this.logname = logname;
		this.logpass = logpass;
		this.name = name;
		this.state = state;
		this.orgid = orgid;
		this.usernum = usernum;
		this.logineortimes = logineortimes;
		this.deptname = deptname;
		this.msg = msg;
		deptName = deptName2;
		this.operation = operation;
	}



	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LOGNAME", nullable = false, length = 50)
	public String getLogname() {
		return this.logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	@Column(name = "LOGPASS", length = 50)
	public String getLogpass() {
		return this.logpass;
	}

	public void setLogpass(String logpass) {
		this.logpass = logpass;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATE")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "ORGID")
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "USERNUM", length = 50)
	public String getUsernum() {
		return this.usernum;
	}

	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

	@Column(name = "LOGINEORTIMES")
	public Integer getLogineortimes() {
		return this.logineortimes;
	}

	public void setLogineortimes(Integer logineortimes) {
		this.logineortimes = logineortimes;
	}
	@Column(name = "EMPNUM")
	public String getEmpnum() {
		return empnum;
	}

	public void setEmpnum(String empnum) {
		this.empnum = empnum;
	}
	@Column(name = "DEPTNAME", length = 1020)
	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@Column(name = "MSG", length = 1020)
	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Transient
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Transient
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Transient
	public String getCatpcha() {
		return catpcha;
	}

	public void setCatpcha(String catpcha) {
		this.catpcha = catpcha;
	}

	@Transient
	public String getCredentialsSalt() {
		return logname+logpass;
	}

	public void setCredentialsSalt(String credentialsSalt) {
		this.credentialsSalt = credentialsSalt;
	}
	@Transient
	public List<EcOrgDepartment> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<EcOrgDepartment> orgs) {
		this.orgs = orgs;
	}

	
	
}
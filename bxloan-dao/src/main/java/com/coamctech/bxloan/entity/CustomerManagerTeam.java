package com.coamctech.bxloan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.coamctech.bxloan.commons.GlobalConstants;

@Entity
@Table(name = "CUSTOMER_MANAGER_TEAM", schema = GlobalConstants.WD_SCHEMA)
public class CustomerManagerTeam  implements java.io.Serializable {

	private Long cmTeamId;
	private Long partyId;
	private String customerNum;
	private String userNum; //用户id
	private String orgCd;   //组织id
	private String roleCd;
	private String states;
	private Date createDate;
	private Date sysUpdateTime;
	private String managerType;
	private String deptCd;
	private String teamCd;
	private String id;


	/** default constructor */
	public CustomerManagerTeam() {
	}

	/** minimal constructor */
	public CustomerManagerTeam(Long cmTeamId) {
		this.cmTeamId = cmTeamId;
	}


	@SequenceGenerator(name = "generator", sequenceName="SEQ_CUSTOMER_MANAGER_TEAM", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "CM_TEAM_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getCmTeamId() {
		return this.cmTeamId;
	}
	public void setCmTeamId(Long cmTeamId) {
		this.cmTeamId = cmTeamId;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CUSTOMER_NUM", length = 30)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "USER_NUM", length = 20)
	public String getUserNum() {
		return this.userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(name = "ORG_CD", length = 30)
	public String getOrgCd() {
		return this.orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}

	@Column(name = "ROLE_CD", length = 30)
	public String getRoleCd() {
		return this.roleCd;
	}
	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	@Column(name = "STATES", length = 30)
	public String getStates() {
		return this.states;
	}

	public void setStates(String states) {
		this.states = states;
	}

	@Column(name = "CREATE_DATE", length = 7)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "SYS_UPDATE_TIME", length = 7)
	public Date getSysUpdateTime() {
		return this.sysUpdateTime;
	}
	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	@Column(name = "MANAGER_TYPE", length = 30)
	public String getManagerType() {
		return this.managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

	@Column(name = "DEPT_CD", length = 30)
	public String getDeptCd() {
		return this.deptCd;
	}
	public void setDeptCd(String deptCd) {
		this.deptCd = deptCd;
	}

	@Column(name = "TEAM_CD", length = 30)
	public String getTeamCd() {
		return this.teamCd;
	}
	public void setTeamCd(String teamCd) {
		this.teamCd = teamCd;
	}
	@Column(name = "ID", length = 255)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
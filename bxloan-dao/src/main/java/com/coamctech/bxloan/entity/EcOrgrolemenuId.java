package com.coamctech.bxloan.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EcOrgrolemenuId implements java.io.Serializable {

	// Fields

	private Long menuId;
	private Long roleId;
	private String deptname;
	private Long logineortimes;
	private String msg;
	private Long orgid;
	private Long state;
	private String usernum;


	/** default constructor */
	public EcOrgrolemenuId() {
	}

	/** minimal constructor */
	public EcOrgrolemenuId(Long menuId, Long roleId) {
		this.menuId = menuId;
		this.roleId = roleId;
	}


	@Column(name = "MENU_ID", nullable = false, precision = 6, scale = 0)
	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Column(name = "ROLE_ID", nullable = false, precision = 6, scale = 0)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "DEPTNAME", length = 1020)
	public String getDeptname() {
		return this.deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	@Column(name = "LOGINEORTIMES", precision = 10, scale = 0)
	public Long getLogineortimes() {
		return this.logineortimes;
	}

	public void setLogineortimes(Long logineortimes) {
		this.logineortimes = logineortimes;
	}

	@Column(name = "MSG", length = 1020)
	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Column(name = "ORGID", precision = 10, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "STATE", precision = 10, scale = 0)
	public Long getState() {
		return this.state;
	}

	public void setState(Long state) {
		this.state = state;
	}

	@Column(name = "USERNUM", length = 200)
	public String getUsernum() {
		return this.usernum;
	}

	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EcOrgrolemenuId))
			return false;
		EcOrgrolemenuId castOther = (EcOrgrolemenuId) other;

		return ((this.getMenuId() == castOther.getMenuId()) || (this
				.getMenuId() != null && castOther.getMenuId() != null && this
				.getMenuId().equals(castOther.getMenuId())))
				&& ((this.getRoleId() == castOther.getRoleId()) || (this
						.getRoleId() != null && castOther.getRoleId() != null && this
						.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getDeptname() == castOther.getDeptname()) || (this
						.getDeptname() != null
						&& castOther.getDeptname() != null && this
						.getDeptname().equals(castOther.getDeptname())))
				&& ((this.getLogineortimes() == castOther.getLogineortimes()) || (this
						.getLogineortimes() != null
						&& castOther.getLogineortimes() != null && this
						.getLogineortimes()
						.equals(castOther.getLogineortimes())))
				&& ((this.getMsg() == castOther.getMsg()) || (this.getMsg() != null
						&& castOther.getMsg() != null && this.getMsg().equals(
						castOther.getMsg())))
				&& ((this.getOrgid() == castOther.getOrgid()) || (this
						.getOrgid() != null && castOther.getOrgid() != null && this
						.getOrgid().equals(castOther.getOrgid())))
				&& ((this.getState() == castOther.getState()) || (this
						.getState() != null && castOther.getState() != null && this
						.getState().equals(castOther.getState())))
				&& ((this.getUsernum() == castOther.getUsernum()) || (this
						.getUsernum() != null && castOther.getUsernum() != null && this
						.getUsernum().equals(castOther.getUsernum())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result
				+ (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result
				+ (getDeptname() == null ? 0 : this.getDeptname().hashCode());
		result = 37
				* result
				+ (getLogineortimes() == null ? 0 : this.getLogineortimes()
						.hashCode());
		result = 37 * result
				+ (getMsg() == null ? 0 : this.getMsg().hashCode());
		result = 37 * result
				+ (getOrgid() == null ? 0 : this.getOrgid().hashCode());
		result = 37 * result
				+ (getState() == null ? 0 : this.getState().hashCode());
		result = 37 * result
				+ (getUsernum() == null ? 0 : this.getUsernum().hashCode());
		return result;
	}

}
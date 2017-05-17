package com.coamctech.bxloan.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EcOrgPersonconnroleId implements java.io.Serializable {

	// Fields

	private Long personid;
	private Long roleid;
	private Long deptid;

	// Constructors

	/** default constructor */
	public EcOrgPersonconnroleId() {
	}

	/** full constructor */
	public EcOrgPersonconnroleId(Long personid, Long roleid,
			Long deptid) {
		this.personid = personid;
		this.roleid = roleid;
		this.deptid = deptid;
	}


	@Column(name = "PERSONID", nullable = false, precision = 22, scale = 0)
	public Long getPersonid() {
		return this.personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}

	@Column(name = "ROLEID", nullable = false, precision = 22, scale = 0)
	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	@Column(name = "DEPTID", nullable = false, precision = 22, scale = 0)
	public Long getDeptid() {
		return this.deptid;
	}

	public void setDeptid(Long deptid) {
		this.deptid = deptid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EcOrgPersonconnroleId))
			return false;
		EcOrgPersonconnroleId castOther = (EcOrgPersonconnroleId) other;

		return ((this.getPersonid() == castOther.getPersonid()) || (this
				.getPersonid() != null && castOther.getPersonid() != null && this
				.getPersonid().equals(castOther.getPersonid())))
				&& ((this.getRoleid() == castOther.getRoleid()) || (this
						.getRoleid() != null && castOther.getRoleid() != null && this
						.getRoleid().equals(castOther.getRoleid())))
				&& ((this.getDeptid() == castOther.getDeptid()) || (this
						.getDeptid() != null && castOther.getDeptid() != null && this
						.getDeptid().equals(castOther.getDeptid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPersonid() == null ? 0 : this.getPersonid().hashCode());
		result = 37 * result
				+ (getRoleid() == null ? 0 : this.getRoleid().hashCode());
		result = 37 * result
				+ (getDeptid() == null ? 0 : this.getDeptid().hashCode());
		return result;
	}

}
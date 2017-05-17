package com.coamctech.bxloan.entity;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EC_ORG_PERSONCONNROLE", schema = WD_SCHEMA)
public class EcOrgPersonconnrole implements java.io.Serializable {

	// Fields

	private EcOrgPersonconnroleId id;
	private EcOrgRole ecOrgRole;
	private EcOrgPerson ecOrgPerson;
	private EcOrgDepartment ecOrgDepartment;
	private Integer state;
	private Long orgid;
	private Date startdate;
	private Date enddate;
	private String autor;
	private String description;
	private Long teamid;


	/** default constructor */
	public EcOrgPersonconnrole() {
	}


	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "personid", column = @Column(name = "PERSONID", nullable = false)),
			@AttributeOverride(name = "roleid", column = @Column(name = "ROLEID", nullable = false)),
			@AttributeOverride(name = "deptid", column = @Column(name = "DEPTID", nullable = false)) })
	public EcOrgPersonconnroleId getId() {
		return this.id;
	}

	public void setId(EcOrgPersonconnroleId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLEID", nullable = false, insertable = false, updatable = false)
	public EcOrgRole getEcOrgRole() {
		return this.ecOrgRole;
	}

	public void setEcOrgRole(EcOrgRole ecOrgRole) {
		this.ecOrgRole = ecOrgRole;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PERSONID", nullable = false, insertable = false, updatable = false)
	public EcOrgPerson getEcOrgPerson() {
		return this.ecOrgPerson;
	}

	public void setEcOrgPerson(EcOrgPerson ecOrgPerson) {
		this.ecOrgPerson = ecOrgPerson;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEPTID", nullable = false, insertable = false, updatable = false)
	public EcOrgDepartment getEcOrgDepartment() {
		return this.ecOrgDepartment;
	}

	public void setEcOrgDepartment(EcOrgDepartment ecOrgDepartment) {
		this.ecOrgDepartment = ecOrgDepartment;
	}

	@Column(name = "STATE", precision = 22, scale = 0)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "ORGID", precision = 22, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STARTDATE", length = 7)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", length = 7)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "AUTOR", length = 30)
	public String getAutor() {
		return this.autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TEAMID", precision = 22, scale = 0)
	public Long getTeamid() {
		return this.teamid;
	}

	public void setTeamid(Long teamid) {
		this.teamid = teamid;
	}

}
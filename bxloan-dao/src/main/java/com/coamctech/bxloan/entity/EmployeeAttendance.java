package com.coamctech.bxloan.entity;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.coamctech.bxloan.commons.GlobalConstants;

/**
 * EmployeeAttendance entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "EMPLOYEE_ATTENDANCE", schema = GlobalConstants.WD_SCHEMA)
public class EmployeeAttendance implements java.io.Serializable {

	// Fields

	private Long id;
	private Long personid;
	private String name;
	private String longitude;
	private String latitude;
	private String address;
	private Date logtime;
	private Long orgId;

	// Constructors

	/** default constructor */
	public EmployeeAttendance() {
	}

	/** minimal constructor */
	public EmployeeAttendance(Long id, Long personid) {
		this.id = id;
		this.personid = personid;
	}

	/** full constructor */
	public EmployeeAttendance(Long id, Long personid, String name,
			String longitude, String latitude, String address, Date logtime, Long orgId) {
		this.id = id;
		this.personid = personid;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
		this.logtime = logtime;
		this.orgId = orgId;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_EMPLOYEE_ATTENDANCE", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PERSONID", nullable = false, precision = 22, scale = 0)
	public Long getPersonid() {
		return this.personid;
	}

	public void setPersonid(Long personid) {
		this.personid = personid;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "LONGITUDE")
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "LATITUDE")
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LOGTIME", length = 7)
	public Date getLogtime() {
		return this.logtime;
	}

	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}

	@Column(name = "ORG_ID", nullable = false, precision = 22, scale = 0)
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
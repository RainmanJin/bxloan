package com.coamctech.bxloan.entity;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EC_ORG_DEPARTMENT", schema = WD_SCHEMA)
public class EcOrgDepartment implements java.io.Serializable {

	// Fields

	private Long id;
	private Long parentdepartmentid;
	private String name;
	private String description;
	private Integer levels;
	private Integer orders;
	private Integer state;
	private String bizAttri;
	private String orgAttr;
	private String businessLicense;
	private String principal;
	
	// Constructors

	/** default constructor */
	public EcOrgDepartment() {
	}

	/** minimal constructor */
	public EcOrgDepartment(Long parentdepartmentid) {
		this.parentdepartmentid = parentdepartmentid;
	}
	
	/**new add construction gph*/
	public EcOrgDepartment(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	

	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PARENTDEPARTMENTID", nullable = false)
	public Long getParentdepartmentid() {
		return this.parentdepartmentid;
	}

	public void setParentdepartmentid(Long parentdepartmentid) {
		this.parentdepartmentid = parentdepartmentid;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "LEVELS")
	public Integer getLevels() {
		return this.levels;
	}

	public void setLevels(Integer levels) {
		this.levels = levels;
	}

	@Column(name = "ORDERS")
	public Integer getOrders() {
		return this.orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	@Column(name = "STATE")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "BIZ_ATTRI", length = 20)
	public String getBizAttri() {
		return this.bizAttri;
	}

	public void setBizAttri(String bizAttri) {
		this.bizAttri = bizAttri;
	}

	@Column(name = "ORG_ATTR", length = 40)
	public String getOrgAttr() {
		return this.orgAttr;
	}

	public void setOrgAttr(String orgAttr) {
		this.orgAttr = orgAttr;
	}

	@Column(name = "BUSINESS_LICENSE", length = 20)
	public String getBusinessLicense() {
		return this.businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	@Column(name = "PRINCIPAL", length = 100)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

}
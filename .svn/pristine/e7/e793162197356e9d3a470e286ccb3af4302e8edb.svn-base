package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EC_ORGROLEMENU", schema = WD_SCHEMA)
public class EcOrgrolemenu implements java.io.Serializable {

	// Fields

	private EcOrgrolemenuId id;

	// Constructors

	/** default constructor */
	public EcOrgrolemenu() {
	}

	/** full constructor */
	public EcOrgrolemenu(EcOrgrolemenuId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "menuId", column = @Column(name = "MENU_ID", nullable = false, precision = 6, scale = 0)),
			@AttributeOverride(name = "roleId", column = @Column(name = "ROLE_ID", nullable = false, precision = 6, scale = 0)),
			@AttributeOverride(name = "deptname", column = @Column(name = "DEPTNAME", length = 1020)),
			@AttributeOverride(name = "logineortimes", column = @Column(name = "LOGINEORTIMES", precision = 10, scale = 0)),
			@AttributeOverride(name = "msg", column = @Column(name = "MSG", length = 1020)),
			@AttributeOverride(name = "orgid", column = @Column(name = "ORGID", precision = 10, scale = 0)),
			@AttributeOverride(name = "state", column = @Column(name = "STATE", precision = 10, scale = 0)),
			@AttributeOverride(name = "usernum", column = @Column(name = "USERNUM", length = 200)) })
	public EcOrgrolemenuId getId() {
		return this.id;
	}

	public void setId(EcOrgrolemenuId id) {
		this.id = id;
	}

}
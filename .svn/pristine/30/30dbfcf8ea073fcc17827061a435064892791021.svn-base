package com.coamctech.bxloan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.coamctech.bxloan.commons.entity.UuidEntity;

@Entity
@Table(name = "tt_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class User extends UuidEntity {

	/* private String id; // uuid from UuidEntity */

	@Column(length = 20, nullable = false)
	private String username;

	@Column(length = 40, nullable = false)
	private String password;

	@Column(length = 20, nullable = false)
	private String name;

	@Column(nullable = false)
	private String role;
	
	@Column(length = 50)
	private String usernum;

	// -================== others ==================-
	@Transient
	private String catpcha;

	@Transient
	private String credentialsSalt;

	public User() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public String getUsernum() {
		return usernum;
	}

	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

	// -================= others ====================
	public String getCatpcha() {
		return catpcha;
	}

	public void setCatpcha(String catpcha) {
		this.catpcha = catpcha;
	}

	public String getCredentialsSalt() {
		return credentialsSalt = username + password;
	}

}

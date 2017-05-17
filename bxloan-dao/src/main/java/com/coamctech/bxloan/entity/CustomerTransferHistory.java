package com.coamctech.bxloan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

/**
 * CustomerTransferHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CUSTOMER_TRANSFER_HISTORY", schema = WD_SCHEMA)
public class CustomerTransferHistory implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String oldUser;
	private String newUser;
	private String operator;
	private Date operateTime;

	// Constructors

	/** default constructor */
	public CustomerTransferHistory() {
	}

	/** full constructor */
	public CustomerTransferHistory(String oldUser, String newUser,
			String operator, Date operateTime) {
		this.oldUser = oldUser;
		this.newUser = newUser;
		this.operator = operator;
		this.operateTime = operateTime;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_HISTORY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "OLD_USER", length = 50)
	public String getOldUser() {
		return this.oldUser;
	}

	public void setOldUser(String oldUser) {
		this.oldUser = oldUser;
	}

	@Column(name = "NEW_USER", length = 50)
	public String getNewUser() {
		return this.newUser;
	}

	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}

	@Column(name = "OPERATOR", length = 50)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATE_TIME", length = 7)
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

}
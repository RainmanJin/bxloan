package com.coamctech.bxloan.entity;

import java.io.Serializable;
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
 * BusinessTransferHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BUSINESS_TRANSFER_HISTORY", schema = WD_SCHEMA)
public class BusinessTransferHistory  implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String projectId;
	private String contractId;
	private String oldUser;
	private String newUser;
	private String operator;
	private Date operateTime;
	private String type;
	private String typeName;

	// Constructors

	/** default constructor */
	public BusinessTransferHistory() {
	}

	/** full constructor */
	public BusinessTransferHistory(String projectId, String contractId,
			String oldUser, String newUser, String operator,
			Date operateTime, String type, String typeName) {
		this.projectId = projectId;
		this.contractId = contractId;
		this.oldUser = oldUser;
		this.newUser = newUser;
		this.operator = operator;
		this.operateTime = operateTime;
		this.type = type;
		this.typeName = typeName;
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

	@Column(name = "PROJECT_ID", length = 50)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "CONTRACT_ID", length = 50)
	public String getContractId() {
		return this.contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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

	@Column(name = "TYPE", length = 5)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "TYPE_NAME", length = 50)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
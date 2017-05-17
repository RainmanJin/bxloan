package com.coamctech.bxloan.entity;
import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "EC_ORG_ROLE", schema = WD_SCHEMA)
public class EcOrgRole implements java.io.Serializable {

	// Fields

	private Long id;
	private Long parentroleid;
	private String name;
	private String description;
	private String type;
	private Integer state;
	private String authorizeUserNum;
	private Date startDate;
	private Date endTime;
	private String modifyReason;
	private String remark;
	private String roleNum;
	private Boolean ishave;
	private Boolean steady;
	private String roleDescription;
	//用于回显权限菜单
	private List<String> functionId;
	
	

	/** default constructor */
	public EcOrgRole() {
	}
	
	@Transient
	public List<String> getFunctionId() {
		return functionId;
	}

	public void setFunctionId(List<String> functionId) {
		this.functionId = functionId;
	}

	

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PARENTROLEID")
	public Long getParentroleid() {
		return this.parentroleid;
	}

	public void setParentroleid(Long parentroleid) {
		this.parentroleid = parentroleid;
	}

	@Column(name = "NAME", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "TYPE", length = 100)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STATE")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "AUTHORIZE_USER_NUM", length = 40)
	public String getAuthorizeUserNum() {
		return this.authorizeUserNum;
	}

	public void setAuthorizeUserNum(String authorizeUserNum) {
		this.authorizeUserNum = authorizeUserNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "END_TIME", length = 7)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "MODIFY_REASON", length = 1000)
	public String getModifyReason() {
		return this.modifyReason;
	}

	public void setModifyReason(String modifyReason) {
		this.modifyReason = modifyReason;
	}

	@Column(name = "REMARK", length = 40)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ROLE_NUM", length = 30)
	public String getRoleNum() {
		return this.roleNum;
	}

	public void setRoleNum(String roleNum) {
		this.roleNum = roleNum;
	}

	@Column(name = "ISHAVE")
	public Boolean getIshave() {
		return this.ishave;
	}

	public void setIshave(Boolean ishave) {
		this.ishave = ishave;
	}

	@Column(name = "STEADY")
	public Boolean getSteady() {
		return this.steady;
	}

	public void setSteady(Boolean steady) {
		this.steady = steady;
	}

	@Column(name = "ROLE_DESCRIPTION", length = 200)
	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	//额外的字段用于在前台页面显示操作按钮
	private String operation;
	@Transient
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
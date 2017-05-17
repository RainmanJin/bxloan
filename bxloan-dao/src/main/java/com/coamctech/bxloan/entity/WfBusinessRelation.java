package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

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

import com.coamctech.bxloan.commons.entity.BaseEntity;

/**
 *流程与业务关系
 */
@Entity
@Table(name = "WF_BUSINESS_RELATION",schema=WD_SCHEMA)
public class WfBusinessRelation extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_WF_BUSINESS_RELATION", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	/** 主键ID */
	private Long ID;
	
	/** 流程类型 */
	@Column(name = "WORK_FLOW_TYPE", length = 30)
	private String workFlowType;
	
	/** 流程id */
	@Column(name = "WORK_FLOW_ID", precision = 22)
	private Long workFlowId;
	
	/** 业务id */
	@Column(name = "BUSINESS_ID", precision = 22)
	private Long businessId;
	
	/** 父级业务id */
	@Column(name = "PARENT_BUSINESS_ID", precision = 22)
	private Long parentBusinessId;
	
	/** 系统时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	public WfBusinessRelation() {
		super();
	}
	
	public WfBusinessRelation(String workFlowType, Long workFlowId,
			Long businessId, Long parentBusinessId, Date createTime) {
		this.workFlowType = workFlowType;
		this.workFlowId = workFlowId;
		this.businessId = businessId;
		this.parentBusinessId = parentBusinessId;
		this.createTime = createTime;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getWorkFlowType() {
		return workFlowType;
	}

	public void setWorkFlowType(String workFlowType) {
		this.workFlowType = workFlowType;
	}

	public Long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public Long getParentBusinessId() {
		return parentBusinessId;
	}

	public void setParentBusinessId(Long parentBusinessId) {
		this.parentBusinessId = parentBusinessId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

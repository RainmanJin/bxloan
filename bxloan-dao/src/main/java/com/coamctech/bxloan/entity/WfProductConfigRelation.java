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
 * 类名称：WfProductConfigRelation 
 * 类描述 ： 流程与产品配置关系实体
 * 创建人: wangyawei 
 * 创建时间：2015年7月27日 上午10:54:52 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * 版本： V1.0
 */
@Entity
@Table(name = "WF_PRODUCT_CONFIG_RELATION",schema=WD_SCHEMA)
public class WfProductConfigRelation extends BaseEntity{
	/** 主键ID */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_WF_PRODUCT_CONFIG_RELATION", allocationSize = 1)
	@Column(name = "id", unique = true, nullable = false, precision = 12, scale = 0)
	private Long id;
	
	/** 流程类型 */
	@Column(name = "WORK_FLOW_TYPE", length = 30)
	private String workFlowType;
	
	/** 业务类型，CodeType=ProjectBussinessType */
	@Column(name = "PROJECT_BUSINESS_TYPE", length = 10)
	private String  projectBusinessType;
	
	/** 机构ID */
	@Column(name = "ORG_ID", precision = 22)
	private Long orgId;
	
	/** 贷款产品ID */
	@Column(name = "PRODUCT_CD", precision = 8)
	private Long productCd;
	
	/** 产品配置ID */
	@Column(name = "PRODUCT_CONFIG_ID", precision = 20)
	private Long productConfigId;
	
	/** 系统时间*/
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	public WfProductConfigRelation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkFlowType() {
		return workFlowType;
	}

	public void setWorkFlowType(String workFlowType) {
		this.workFlowType = workFlowType;
	}

	public String getProjectBusinessType() {
		return projectBusinessType;
	}

	public void setProjectBusinessType(String projectBusinessType) {
		this.projectBusinessType = projectBusinessType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getProductCd() {
		return productCd;
	}

	public void setProductCd(Long productCd) {
		this.productCd = productCd;
	}

	public Long getProductConfigId() {
		return productConfigId;
	}

	public void setProductConfigId(Long productConfigId) {
		this.productConfigId = productConfigId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

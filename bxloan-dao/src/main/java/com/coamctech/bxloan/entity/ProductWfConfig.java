package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name = "PRODUCT_WF_CONFIG", schema = WD_SCHEMA)
public class ProductWfConfig implements java.io.Serializable {

	/*** 唯一主键*/
	private Long pwcId;
	/***产品配置ID***/
	private Long pcId;

	/*** 环节号 */
	private String taskStageCode;
	/*** 规则配置,获取下一环节号 */
	private String ruleconfig;
	/**角色配置,取下一环节人员的角色**/
	private String roleconfig;
	
	private Long orgId;


	public ProductWfConfig() {
	}

	public ProductWfConfig(Long pcId) {
		super();
		this.pcId = pcId;
	}
	
	public ProductWfConfig(Long pcId, String taskStageCode, String roleconfig,Long orgId) {
		super();
		this.pcId = pcId;
		this.taskStageCode = taskStageCode;
		this.roleconfig = roleconfig;
		this.orgId = orgId;
	}

	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_PRODUCT_WF_CONFIG", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "PWC_ID", unique = true, nullable = false, precision = 20, scale = 0)
	public Long getPwcId() {
		return this.pwcId;
	}

	public void setPwcId(Long pwcId) {
		this.pwcId = pwcId;
	}

	@Column(name = "PC_ID", precision = 20, scale = 0)
	public Long getPcId() {
		return this.pcId;
	}

	public void setPcId(Long pcId) {
		this.pcId = pcId;
	}

	@Column(name = "TASK_STAGE_CODE", length = 20)
	public String getTaskStageCode() {
		return this.taskStageCode;
	}

	public void setTaskStageCode(String taskStageCode) {
		this.taskStageCode = taskStageCode;
	}

	@Column(name = "RULECONFIG", length = 4000)
	public String getRuleconfig() {
		return this.ruleconfig;
	}

	public void setRuleconfig(String ruleconfig) {
		this.ruleconfig = ruleconfig;
	}

	@Column(name = "ROLECONFIG", length = 500)
	public String getRoleconfig() {
		return this.roleconfig;
	}

	public void setRoleconfig(String roleconfig) {
		this.roleconfig = roleconfig;
	}
	@Column(name = "ORG_ID", precision = 20, scale = 0)
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

}
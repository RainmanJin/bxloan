package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.math.BigDecimal;
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

@Entity
@Table(name = "APPROVAL_PHONE_DETAIL", schema = WD_SCHEMA)
public class ApprovalPhoneDetail implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3602796669003131018L;
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	@SequenceGenerator(name = "generator", sequenceName="SEQ_APPROVAL_PHONE_DETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	private Long id;
	@Column(name = "PROJECT_ID", precision = 12, scale = 0)
	private Long projectId;
	@Column(name = "CHECK_TYPE", length = 10)
	private String checkType;//核实对象（1-借款人  2-借款人配偶  3-信息核实人）
	@Column(name = "CHECK_NAME", length = 50)
	private String checkName;//姓名
	@Column(name = "IS_PHONE", length = 10)
	private String isPhone;//电话号码是否正确（1-是 2-否）
	@Column(name = "IS_INCOME_SOURCE", length = 10)
	private String isIncomeSource;//职业（收入来源）（1-是 2-否）
	@Column(name = "IS_APPLY_MONEY", length = 10)
	private String isApplyMoney;//知悉申请金额（1-是 2-否）
	@Column(name = "IS_APPLY_TERM", length = 10)
	private String isApplyTerm;//知悉申请期限（1-是 2-否）
	@Column(name = "IS_RATE", length = 10)
	private String isRate;//知悉利率（1-是 2-否）
	@Column(name = "IS_BLAME_KNOW", length = 10)
	private String isBlameKnow;//联保责任知悉（1-是 2-否）
	@Column(name = "IS_BLAME_CLEAR", length = 10)
	private String isBlameClear;//联保责任明确（1-是 2-否）
	@Temporal(TemporalType.DATE)
	@Column(name = "CALL_TIME", length = 7)
	private Date callTime;//拨打时间
	@Column(name = "APPROVAL_USER_NAME", length = 50)
	private String approvalUserName;//审查人员签字
	@Column(name = "APPROVAL_USER_ID", precision = 12, scale = 0)
	private Long approvalUserId;//审查人员ID
	@Column(name = "APPROVAL_ORG_ID", precision = 12, scale = 0)
	private Long approvalOrgId;//审查机构
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	private Date createTime;//添加时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public String getIsPhone() {
		return isPhone;
	}
	public void setIsPhone(String isPhone) {
		this.isPhone = isPhone;
	}
	public String getIsIncomeSource() {
		return isIncomeSource;
	}
	public void setIsIncomeSource(String isIncomeSource) {
		this.isIncomeSource = isIncomeSource;
	}
	public String getIsApplyMoney() {
		return isApplyMoney;
	}
	public void setIsApplyMoney(String isApplyMoney) {
		this.isApplyMoney = isApplyMoney;
	}
	public String getIsApplyTerm() {
		return isApplyTerm;
	}
	public void setIsApplyTerm(String isApplyTerm) {
		this.isApplyTerm = isApplyTerm;
	}
	public String getIsRate() {
		return isRate;
	}
	public void setIsRate(String isRate) {
		this.isRate = isRate;
	}
	public String getIsBlameKnow() {
		return isBlameKnow;
	}
	public void setIsBlameKnow(String isBlameKnow) {
		this.isBlameKnow = isBlameKnow;
	}
	public String getIsBlameClear() {
		return isBlameClear;
	}
	public void setIsBlameClear(String isBlameClear) {
		this.isBlameClear = isBlameClear;
	}
	public Date getCallTime() {
		return callTime;
	}
	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}
	public String getApprovalUserName() {
		return approvalUserName;
	}
	public void setApprovalUserName(String approvalUserName) {
		this.approvalUserName = approvalUserName;
	}
	public Long getApprovalUserId() {
		return approvalUserId;
	}
	public void setApprovalUserId(Long approvalUserId) {
		this.approvalUserId = approvalUserId;
	}
	public Long getApprovalOrgId() {
		return approvalOrgId;
	}
	public void setApprovalOrgId(Long approvalOrgId) {
		this.approvalOrgId = approvalOrgId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
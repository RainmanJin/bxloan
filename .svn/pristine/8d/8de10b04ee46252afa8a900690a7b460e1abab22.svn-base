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

/**
 * TbMultiopinion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TB_MULTIOPINION",schema=WD_SCHEMA)
public class TbMultiopinion implements java.io.Serializable {

	// Fields

	private Long opinionid;//唯一标识
	private String opiniontype;//意见类型
	private Long operationid;//操作ID
	private String personid;//签署人ID
	private String personcn;//签署人名称
	private String opinion;//意见
	private Date signtime;//签署时间
	private String processid;//流程ID
	private String currentactivityname;//环节
	private String conclusion;//表决结论
	private Long issueorgid;//发出机构ID
	private Long targetorgid;//目标机构ID
	private String workitemid;
	private Long roleid;//角色ID

	// Constructors

	/** default constructor */
	public TbMultiopinion() {
	}

	/** minimal constructor */
	public TbMultiopinion(Long opinionid) {
		this.opinionid = opinionid;
	}

	/** full constructor */
	public TbMultiopinion(Long opinionid, String opiniontype,
			Long operationid, String personid, String personcn,
			String opinion, Date signtime, String processid,
			String currentactivityname, String conclusion,
			Long issueorgid, Long targetorgid, String workitemid,
			Long roleid) {
		this.opinionid = opinionid;
		this.opiniontype = opiniontype;
		this.operationid = operationid;
		this.personid = personid;
		this.personcn = personcn;
		this.opinion = opinion;
		this.signtime = signtime;
		this.processid = processid;
		this.currentactivityname = currentactivityname;
		this.conclusion = conclusion;
		this.issueorgid = issueorgid;
		this.targetorgid = targetorgid;
		this.workitemid = workitemid;
		this.roleid = roleid;
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_TB_MULTIOPINION", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "OPINIONID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getOpinionid() {
		return this.opinionid;
	}

	public void setOpinionid(Long opinionid) {
		this.opinionid = opinionid;
	}

	@Column(name = "OPINIONTYPE", length = 100)
	public String getOpiniontype() {
		return this.opiniontype;
	}

	public void setOpiniontype(String opiniontype) {
		this.opiniontype = opiniontype;
	}

	@Column(name = "OPERATIONID", precision = 22, scale = 0)
	public Long getOperationid() {
		return this.operationid;
	}

	public void setOperationid(Long operationid) {
		this.operationid = operationid;
	}

	@Column(name = "PERSONID", length = 100)
	public String getPersonid() {
		return this.personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	@Column(name = "PERSONCN", length = 100)
	public String getPersoncn() {
		return this.personcn;
	}

	public void setPersoncn(String personcn) {
		this.personcn = personcn;
	}

	@Column(name = "OPINION", length = 4000)
	public String getOpinion() {
		return this.opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	@Column(name = "SIGNTIME", length = 7)
	public Date getSigntime() {
		return this.signtime;
	}

	public void setSigntime(Date signtime) {
		this.signtime = signtime;
	}

	@Column(name = "PROCESSID", length = 100)
	public String getProcessid() {
		return this.processid;
	}

	public void setProcessid(String processid) {
		this.processid = processid;
	}

	@Column(name = "CURRENTACTIVITYNAME", length = 100)
	public String getCurrentactivityname() {
		return this.currentactivityname;
	}

	public void setCurrentactivityname(String currentactivityname) {
		this.currentactivityname = currentactivityname;
	}

	@Column(name = "CONCLUSION", length = 100)
	public String getConclusion() {
		return this.conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	@Column(name = "ISSUEORGID", precision = 22, scale = 0)
	public Long getIssueorgid() {
		return this.issueorgid;
	}

	public void setIssueorgid(Long issueorgid) {
		this.issueorgid = issueorgid;
	}

	@Column(name = "TARGETORGID", precision = 22, scale = 0)
	public Long getTargetorgid() {
		return this.targetorgid;
	}

	public void setTargetorgid(Long targetorgid) {
		this.targetorgid = targetorgid;
	}

	@Column(name = "WORKITEMID", length = 100)
	public String getWorkitemid() {
		return this.workitemid;
	}

	public void setWorkitemid(String workitemid) {
		this.workitemid = workitemid;
	}

	@Column(name = "ROLEID", precision = 10, scale = 0)
	public Long getRoleid() {
		return this.roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

}
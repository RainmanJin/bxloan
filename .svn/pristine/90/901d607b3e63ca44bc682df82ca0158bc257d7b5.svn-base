package com.coamctech.bxloan.entity;

import static com.coamctech.bxloan.commons.GlobalConstants.WD_SCHEMA;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *	审批意见
 */
@Entity
@Table(name = "APPROVAL_OPINION",schema=WD_SCHEMA)
public class ApprovalOpinion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5083796996051881151L;
	@Id
	@SequenceGenerator(name = "generator", sequenceName="SEQ_APPROVAL_OPINION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name="OPINION_ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long opinionId;
	@Column(name="PROJECT_ID",precision=12,scale=0)
	private Long projectId;
	@Column(name="PROPOSAL_AMT",precision=20,scale=2)
	private BigDecimal proposalAmt;
	@Column(name="PROPOSAL_TERM")
	private Integer proposalTerm;
	@Column(name="PROPOSAL_TERM_UNIT",length=20)
	private String proposalTermUnit;
	@Column(name="PROPOSAL_RATE",precision=20,scale=6)
	private BigDecimal proposalRate;
	@Column(name="OPERATION_ID",precision=20,scale=0)
	private Long operationId;
	@Column(name="OPINION",length=400)
	private String opinion;
	@Column(name="SIGNTIME",length=400)
	private Date signtime;
	@Column(name="TASK_STAGE_CODE",length=20)
	private String taskStageCode;
	
	public Long getOpinionId() {
		return opinionId;
	}
	public void setOpinionId(Long opinionId) {
		this.opinionId = opinionId;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public BigDecimal getProposalAmt() {
		return proposalAmt;
	}
	public void setProposalAmt(BigDecimal proposalAmt) {
		this.proposalAmt = proposalAmt;
	}
	public Integer getProposalTerm() {
		return proposalTerm;
	}
	public void setProposalTerm(Integer proposalTerm) {
		this.proposalTerm = proposalTerm;
	}
	public String getProposalTermUnit() {
		return proposalTermUnit;
	}
	public void setProposalTermUnit(String proposalTermUnit) {
		this.proposalTermUnit = proposalTermUnit;
	}
	public BigDecimal getProposalRate() {
		return proposalRate;
	}
	public void setProposalRate(BigDecimal proposalRate) {
		this.proposalRate = proposalRate;
	}
	public Long getOperationId() {
		return operationId;
	}
	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	public Date getSigntime() {
		return signtime;
	}
	public void setSigntime(Date signtime) {
		this.signtime = signtime;
	}
	public String getTaskStageCode() {
		return taskStageCode;
	}
	public void setTaskStageCode(String taskStageCode) {
		this.taskStageCode = taskStageCode;
	}

}

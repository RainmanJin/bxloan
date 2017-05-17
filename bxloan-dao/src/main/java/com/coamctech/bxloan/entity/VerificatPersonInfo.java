package com.coamctech.bxloan.entity;

// default package

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.utils.CommonHelper;

/**
 * FamilyFriend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VERIFICATPERSONINFO", schema = GlobalConstants.WD_SCHEMA)
public class VerificatPersonInfo implements java.io.Serializable {

	// Fields

	private Long id; //主键ID
	private String name; //核实人名称
	private String relation; //与借款人关系
	private String telphone; //联系电话
	private String familyaddress;//家庭地址
	private String remark;//备注
	/**
	 * 客户id
	 */
	private Long partyId;
	/**
	 * 业务id
	 */
	private Long projectId;//核实人
	private Date createDate;
	private Date updateDate;

	/** default constructor */
	public VerificatPersonInfo() {
	}

	public VerificatPersonInfo(Long id, String name, String relation,
			String telphone, String familyaddress, String remark, Long partyId,
			Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.name = name;
		this.relation = relation;
		this.telphone = telphone;
		this.familyaddress = familyaddress;
		this.remark = remark;
		this.partyId = partyId;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
	public VerificatPersonInfo(Object[] input) {
		int i = 0;
		this.telphone = CommonHelper.toStr(input[i++]);
		this.partyId = CommonHelper.toLong(input[i++]);
		this.id = CommonHelper.toLong(input[i++]);
		this.name = CommonHelper.toStr(input[i++]);
		this.relation = CommonHelper.toStr(input[i++]);
		this.telphone = CommonHelper.toStr(input[i++]);
		this.familyaddress = CommonHelper.toStr(input[i++]);
		this.remark = CommonHelper.toStr(input[i++]);
		this.partyId = CommonHelper.toLong(input[i++]);
	}
	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName = "SEQ_VERIFICATPERSONINFO", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "PARTY_ID", precision = 20, scale = 0)
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	
	@Column(name = "RELATION", length = 2)
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	@Column(name = "telphone", length = 20)
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "FAMILYADDRESS", length = 300)
	public String getFamilyaddress() {
		return familyaddress;
	}

	public void setFamilyaddress(String familyaddress) {
		this.familyaddress = familyaddress;
	}
    
	@Column(name = "REMARK", length = 1000)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "UPDATE_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name = "PROJECT_ID",precision=12,scale=0)
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	
	
}
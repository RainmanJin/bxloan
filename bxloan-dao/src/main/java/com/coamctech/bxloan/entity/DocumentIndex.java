package com.coamctech.bxloan.entity;
// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.coamctech.bxloan.commons.GlobalConstants;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.DateTools;

/**
 * DocumentIndex entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DOCUMENT_INDEX", schema = GlobalConstants.WD_SCHEMA)
public class DocumentIndex  implements java.io.Serializable {
	private Long documentId;
	private Long partyId;
	private String customerNum;
	private String userNum;
	private String documentNum;
	private String documentName;
	private String documentType;
	private Long bizId;
	private String bizNum;
	private Date createDateTime;
	private Date systemUpdateTime;
	private String createUserNum;
	private String createOrgCd;
	private String createTypeCd;
	private String documentRoute;
	private String status;
	private String fileType;
	private String custDocType;
	private String patchTypeCd;
	private String allDocType;
	//add by wangyawei 20150807 start 授信审批流程增加创建人姓名
	/** 创建人姓名 */
	private String createUserName;
	
	/** 创建时间 */
	private String createDate;
	//add by wangyawei 20150807 en

	/** default constructor */
	public DocumentIndex() {
	}

	/** minimal constructor */
	public DocumentIndex(Long documentId) {
		this.documentId = documentId;
	}

	public DocumentIndex(Long documentId, Long partyId, String customerNum,
			String userNum, String documentNum, String documentName,
			String documentType, Long bizId, String bizNum,
			Date createDateTime, Date systemUpdateTime,
			String createUserNum, String createOrgCd, String createTypeCd,
			String documentRoute, String status, String fileType,
			String custDocType) {
		super();
		this.documentId = documentId;
		this.partyId = partyId;
		this.customerNum = customerNum;
		this.userNum = userNum;
		this.documentNum = documentNum;
		this.documentName = documentName;
		this.documentType = documentType;
		this.bizId = bizId;
		this.bizNum = bizNum;
		this.createDateTime = createDateTime;
		this.systemUpdateTime = systemUpdateTime;
		this.createUserNum = createUserNum;
		this.createOrgCd = createOrgCd;
		this.createTypeCd = createTypeCd;
		this.documentRoute = documentRoute;
		this.status = status;
		this.fileType = fileType;
		this.custDocType = custDocType;
	}
	
	public DocumentIndex(Object[] objs) {
		super();
		int i = 0;
		this.partyId = CommonHelper.toLong(objs[i++]);
		this.documentName = CommonHelper.toStr(objs[i++]);
		this.custDocType = CommonHelper.toStr(objs[i++]);
		this.documentType = CommonHelper.toStr(objs[i++]);
		this.customerNum = CommonHelper.toStr(objs[i++]);
		this.createUserName = CommonHelper.toStr(objs[i++]);
		this.createDate = CommonHelper.date2Str(CommonHelper.toDate(objs[i++]), "yyyy-MM-dd");
		this.createTypeCd = CommonHelper.toStr(objs[i++]);
		this.documentId = CommonHelper.toLong(objs[i++]);
		this.allDocType = CommonHelper.toStr(objs[i++]);
	}

	// Property accessors
	@SequenceGenerator(name = "generator", sequenceName="SEQ_DOCUMENT_INDEX", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@Column(name = "DOCUMENT_ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getDocumentId() {
		return this.documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	@Column(name = "PARTY_ID", precision = 12, scale = 0)
	public Long getPartyId() {
		return this.partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	@Column(name = "CUSTOMER_NUM", length = 30)
	public String getCustomerNum() {
		return this.customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	@Column(name = "USER_NUM", length = 20)
	public String getUserNum() {
		return this.userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Column(name = "DOCUMENT_NUM", length = 30)
	public String getDocumentNum() {
		return this.documentNum;
	}

	public void setDocumentNum(String documentNum) {
		this.documentNum = documentNum;
	}

	@Column(name = "DOCUMENT_NAME", length = 100)
	public String getDocumentName() {
		return this.documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	@Column(name = "DOCUMENT_TYPE", length = 30)
	public String getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	@Column(name = "BIZ_ID", precision = 12, scale = 0)
	public Long getBizId() {
		return this.bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	@Column(name = "BIZ_NUM", length = 30)
	public String getBizNum() {
		return this.bizNum;
	}

	public void setBizNum(String bizNum) {
		this.bizNum = bizNum;
	}

	@Column(name = "CREATE_DATE_TIME", length = 7)
	public Date getCreateDateTime() {
		return this.createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	@Column(name = "SYSTEM_UPDATE_TIME", length = 7)
	public Date getSystemUpdateTime() {
		return this.systemUpdateTime;
	}

	public void setSystemUpdateTime(Date systemUpdateTime) {
		this.systemUpdateTime = systemUpdateTime;
	}

	@Column(name = "CREATE_USER_NUM", length = 20)
	public String getCreateUserNum() {
		return this.createUserNum;
	}

	public void setCreateUserNum(String createUserNum) {
		this.createUserNum = createUserNum;
	}

	@Column(name = "CREATE_ORG_CD", length = 30)
	public String getCreateOrgCd() {
		return this.createOrgCd;
	}

	public void setCreateOrgCd(String createOrgCd) {
		this.createOrgCd = createOrgCd;
	}

	@Column(name = "CREATE_TYPE_CD", length = 30)
	public String getCreateTypeCd() {
		return this.createTypeCd;
	}

	public void setCreateTypeCd(String createTypeCd) {
		this.createTypeCd = createTypeCd;
	}

	@Column(name = "DOCUMENT_ROUTE", length = 1024)
	public String getDocumentRoute() {
		return this.documentRoute;
	}

	public void setDocumentRoute(String documentRoute) {
		this.documentRoute = documentRoute;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "FILE_TYPE", length = 20)
	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name = "CUST_DOC_TYPE", length = 2)
	public String getCustDocType() {
		return custDocType;
	}
	
	public void setCustDocType(String custDocType) {
		this.custDocType = custDocType;
	}
	@Column(name = "PATCH_TYPE_CD", length = 3)
	public String getPatchTypeCd() {
		return patchTypeCd;
	}

	public void setPatchTypeCd(String patchTypeCd) {
		this.patchTypeCd = patchTypeCd;
	}
	@Column(name = "ALL_DOC_TYPE", length = 3)
	public String getAllDocType() {
		return allDocType;
	}
	
	public void setAllDocType(String allDocType) {
		this.allDocType = allDocType;
	}

	@Transient
	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@Transient
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
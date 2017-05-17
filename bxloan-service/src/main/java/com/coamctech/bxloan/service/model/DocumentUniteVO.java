package com.coamctech.bxloan.service.model;

import com.coamctech.bxloan.commons.utils.CommonHelper;

public class DocumentUniteVO {
	
	private Long documentId;
	private String documentName;
	private String custDocType;
	private String documentType;
	private String custManagerName;
	private String createDate;
	private String createTypeCd;
	private String allDocType;
	private String unCustTabId;
	private String unGroupName;
	public Long getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public String getDocumentName() {
		return documentName;
	}
	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
	public String getCustDocType() {
		return custDocType;
	}
	public void setCustDocType(String custDocType) {
		this.custDocType = custDocType;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getCustManagerName() {
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTypeCd() {
		return createTypeCd;
	}
	public void setCreateTypeCd(String createTypeCd) {
		this.createTypeCd = createTypeCd;
	}
	public String getAllDocType() {
		return allDocType;
	}
	public void setAllDocType(String allDocType) {
		this.allDocType = allDocType;
	}
	public String getUnCustTabId() {
		return unCustTabId;
	}
	public void setUnCustTabId(String unCustTabId) {
		this.unCustTabId = unCustTabId;
	}
	
	public DocumentUniteVO() {
		super();
	}
	public DocumentUniteVO(Object[] input) {
		int i = 0;
		this.documentId = CommonHelper.toLong(input[i++]);
		this.documentName = CommonHelper.toStr(input[i++]);
		this.custDocType = CommonHelper.toStr(input[i++]);
		this.documentType = CommonHelper.toStr(input[i++]);
		this.custManagerName = CommonHelper.toStr(input[i++]);
		this.createDate = CommonHelper.toStr(input[i++]);
		this.createTypeCd = CommonHelper.toStr(input[i++]);
		this.allDocType = CommonHelper.toStr(input[i++]);
		this.unCustTabId = CommonHelper.toStr(input[i++]);
		this.unGroupName = CommonHelper.toStr(input[i++]);
	}
	public String getUnGroupName() {
		return unGroupName;
	}
	public void setUnGroupName(String unGroupName) {
		this.unGroupName = unGroupName;
	}
	
	
	
}

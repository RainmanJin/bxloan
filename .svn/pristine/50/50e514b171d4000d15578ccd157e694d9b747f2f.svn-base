package com.coamctech.bxloan.web.vo.corpcustomer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.DateTools;

public class CorpDocumentVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long docId;
	private String docNum;
	private String docName;
	private String docTypeCd;
	private String customerNum;
	private String customerName;
	private String createUserNum;
	private String createUserName;
	private Date createTime;
	private String createTimeStr;
	private String createTypeCd;
	private String docPath;
	private String custDocType;
	
	public CorpDocumentVo(Object[] input) {
		super();
		this.docId=toLong(input[0]);
		this.docNum=str(input[1]);
		this.docName=str(input[2]);
		this.docTypeCd=str(input[3]);
		this.customerNum=str(input[4]);
		this.customerName=str(input[5]);
		this.createUserNum=str(input[6]);
		this.createUserName=str(input[7]);
		this.createTime=(Date)input[8];
		this.createTimeStr=DateTools.dateToString(createTime, "yyyy-MM-dd");;
		this.createTypeCd=str(input[9]);
		this.docPath=str(input[10]);
		this.custDocType = str(input[11]);
	}
	private String str(Object obj){
		return obj==null?"":obj.toString().trim();
	}
	private Long toLong(Object obj){
		return (obj!=null&&obj instanceof BigDecimal)?Long.parseLong(String.valueOf(obj)):0L;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocTypeCd() {
		return docTypeCd;
	}
	public void setDocTypeCd(String docTypeCd) {
		this.docTypeCd = docTypeCd;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCreateUserNum() {
		return createUserNum;
	}
	public void setCreateUserNum(String createUserNum) {
		this.createUserNum = createUserNum;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getCreateTypeCd() {
		return createTypeCd;
	}
	public void setCreateTypeCd(String createTypeCd) {
		this.createTypeCd = createTypeCd;
	}
	public String getDocNum() {
		return docNum;
	}
	public void setDocNum(String docNum) {
		this.docNum = docNum;
	}
	public String getDocPath() {
		return docPath;
	}
	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}
	public String getCustDocType() {
		return custDocType;
	}
	public void setCustDocType(String custDocType) {
		this.custDocType = custDocType;
	}
	
}

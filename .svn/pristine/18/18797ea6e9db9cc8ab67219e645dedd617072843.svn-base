package com.coamctech.bxloan.service.model.datatransend;

import java.io.Serializable;
import java.util.Date;

import com.coamctech.bxloan.commons.utils.CommonHelper;
/**   
 * 类名称：CustomerInfoExportVo
 * 类描述 ：导出客户信息vo类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class CustomerInfoExportVo implements Serializable {
	private static final long serialVersionUID = 157453777804786067L;
	private String isAssPer;         //是否关联人
	private String customClass;      //客户类别
	private String documentType;     //证件类型
	private String certificateNum;	 //证件号码
	private String partyName;		 //客户名称
	private String linkmanTel;	     //联系人电话
	private String linkmanName;		 //联系人名称
	private String hmName;			 //法人名称公司
	private String nationArea;		 //户籍注册地址
	private String workingAddress;	 //工作地址营业地址
	private String postalAddress;	 //通讯地址
	private String standDate;		 //出生时间成立时间
	
	public CustomerInfoExportVo() {
		super();  
	}

	public CustomerInfoExportVo(Object[] objs){
		super();
		int i = 0;
		this.isAssPer = CommonHelper.toStr(objs[i++]);
		this.customClass = CommonHelper.toStr(objs[i++]);
		this.documentType = CommonHelper.toStr(objs[i++]);
		this.certificateNum = CommonHelper.toStr(objs[i++]);
		this.partyName = CommonHelper.toStr(objs[i++]);
		this.linkmanTel = CommonHelper.toStr(objs[i++]);
		this.linkmanName = CommonHelper.toStr(objs[i++]);
		this.hmName = CommonHelper.toStr(objs[i++]);
		this.nationArea = CommonHelper.toStr(objs[i++]);
		this.workingAddress = CommonHelper.toStr(objs[i++]);
		this.postalAddress = CommonHelper.toStr(objs[i++]);
		this.standDate = CommonHelper.date2Str((Date) objs[i++], CommonHelper.DF_DATE);
	}
	
	public String getIsAssPer() {
		return isAssPer;
	}

	public void setIsAssPer(String isAssPer) {
		this.isAssPer = isAssPer;
	}

	public String getCustomClass() {
		return customClass;
	}

	public void setCustomClass(String customClass) {
		this.customClass = customClass;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getLinkmanTel() {
		return linkmanTel;
	}
	public void setLinkmanTel(String linkmanTel) {
		this.linkmanTel = linkmanTel;
	}
	public String getLinkmanName() {
		return linkmanName;
	}
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	public String getHmName() {
		return hmName;
	}
	public void setHmName(String hmName) {
		this.hmName = hmName;
	}
	public String getNationArea() {
		return nationArea;
	}
	public void setNationArea(String nationArea) {
		this.nationArea = nationArea;
	}
	public String getWorkingAddress() {
		return workingAddress;
	}
	public void setWorkingAddress(String workingAddress) {
		this.workingAddress = workingAddress;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getStandDate() {
		return standDate;
	}
	public void setStandDate(String standDate) {
		this.standDate = standDate;
	}
	
	
	
}

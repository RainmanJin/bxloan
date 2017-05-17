package com.coamctech.bxloan.service.model.datatransend;

import java.io.Serializable;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：GuarantyInfoExportVo
 * 类描述 ：导出担保物信息vo类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class GuarantyInfoExportVo implements Serializable {
	private static final long serialVersionUID = 8242691261681503803L;
	private String guarantorName;    //合同编号
	private String certificateNum;   //还款金额
	private String contractNum;      //合同编号
	private String regisMark;        //登记号
	private String regisDept;        //登记部门
	private String regisDate;        //登记日期
	private String regisValidityDate;//登记有效期
	private String guarantyName;     //担保物名称
	private String guarantLocation;  //担保物地理位置
	private String guarantPosition;  //担保物顺位
	
	public GuarantyInfoExportVo() {
		super();
	}

	public GuarantyInfoExportVo(Object[] objs){
		super();
		int i = 0;
		this.guarantorName = CommonHelper.toStr(objs[i++]);
		this.certificateNum = CommonHelper.toStr(objs[i++]);
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.regisMark = CommonHelper.toStr(objs[i++]);
		this.regisDept = CommonHelper.toStr(objs[i++]);
		this.regisDate = CommonHelper.toStr(objs[i++]);
		this.regisValidityDate = CommonHelper.toStr(objs[i++]);
		this.guarantyName = CommonHelper.toStr(objs[i++]);
		this.guarantLocation = CommonHelper.toStr(objs[i++]);
		this.guarantPosition = CommonHelper.toStr(objs[i++]);
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getRegisMark() {
		return regisMark;
	}

	public void setRegisMark(String regisMark) {
		this.regisMark = regisMark;
	}

	public String getRegisDept() {
		return regisDept;
	}

	public void setRegisDept(String regisDept) {
		this.regisDept = regisDept;
	}

	public String getRegisDate() {
		return regisDate;
	}

	public void setRegisDate(String regisDate) {
		this.regisDate = regisDate;
	}

	public String getRegisValidityDate() {
		return regisValidityDate;
	}

	public void setRegisValidityDate(String regisValidityDate) {
		this.regisValidityDate = regisValidityDate;
	}

	public String getGuarantyName() {
		return guarantyName;
	}

	public void setGuarantyName(String guarantyName) {
		this.guarantyName = guarantyName;
	}

	public String getGuarantLocation() {
		return guarantLocation;
	}

	public void setGuarantLocation(String guarantLocation) {
		this.guarantLocation = guarantLocation;
	}

	public String getGuarantPosition() {
		return guarantPosition;
	}

	public void setGuarantPosition(String guarantPosition) {
		this.guarantPosition = guarantPosition;
	}
	
}

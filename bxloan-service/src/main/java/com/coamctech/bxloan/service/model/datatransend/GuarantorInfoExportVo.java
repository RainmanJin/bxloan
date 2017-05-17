package com.coamctech.bxloan.service.model.datatransend;

import java.io.Serializable;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：GuarantorInfoExportVo
 * 类描述 ：导出担保人信息vo类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class GuarantorInfoExportVo implements Serializable {
	private static final long serialVersionUID = -223317972035652467L;
	private String contractNum;      //合同编号
	private String guaranteeMode;    //担保方式
	private String customerName;     //客户名称
	private String guarantorType;    //担保人类型
	private String certificateType;  //证件类型
	private String certificateNum;   //证件号码
	private String guarantyMode;     //保证方式
	private String GuaranteeShare;   //保证份额
	
	public GuarantorInfoExportVo() {
		super();
	}

	public GuarantorInfoExportVo(Object[] objs){
		super();
		int i = 0;
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.guaranteeMode = CommonHelper.toStr(objs[i++]);
		this.customerName = CommonHelper.toStr(objs[i++]);
		this.guarantorType = CommonHelper.toStr(objs[i++]);
		this.certificateType = CommonHelper.toStr(objs[i++]);
		this.certificateNum = CommonHelper.toStr(objs[i++]);
		this.guarantyMode = CommonHelper.toStr(objs[i++]);
		this.GuaranteeShare = CommonHelper.toStr(objs[i++]);
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGuarantorType() {
		return guarantorType;
	}

	public void setGuarantorType(String guarantorType) {
		this.guarantorType = guarantorType;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getGuarantyMode() {
		return guarantyMode;
	}

	public void setGuarantyMode(String guarantyMode) {
		this.guarantyMode = guarantyMode;
	}

	public String getGuaranteeShare() {
		return GuaranteeShare;
	}

	public void setGuaranteeShare(String guaranteeShare) {
		GuaranteeShare = guaranteeShare;
	}

	
}

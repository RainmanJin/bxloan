package com.coamctech.bxloan.service.model.contractmng;

import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：ProjectAssurerInfoVo
 * 类描述 ：从合同列表-保证人信息使用VO
 * 创建人: wangyawei  
 * 创建时间：2015年8月6日 下午3:12:05  
 * 修改人：
 * 修改时间：
 * 修改备注：
 * 版本： V1.0
 */
public class ProjectAssurerInfoVo extends SubContractVo {
	/** 保证人姓名 */
	private String customerName;
	
	/** 保证人编号 */
	private String customerNum;
	
	/** 证件类型 */
	private String certificateTypeCd;
	
	/** 证件号码 */
	private String certificateNum;
	
	/** 本次实际保证债权金额 */
	private BigDecimal actualGuaranteeAmt;
	
	/** 客户类型 */
	private String partyTypeCd;
	
	public ProjectAssurerInfoVo() {
		super();
	}
	
	public ProjectAssurerInfoVo(Long subContractId, String guarantyTypeCd) {
		super(subContractId, guarantyTypeCd);
	}
	
	public ProjectAssurerInfoVo(Object[] objs) {
		super();
		int i = 0;
		this.projectId = CommonHelper.toLong(objs[i++]);
		this.partyId = CommonHelper.toLong(objs[i++]);
		this.subContractId = CommonHelper.toLong(objs[i++]);
		this.subcontractNum = CommonHelper.toStr(objs[i++]);
		this.isTransDocument = CommonHelper.toStr(objs[i++]);
		this.documentNum = CommonHelper.toStr(objs[i++]);
		this.customerName = CommonHelper.toStr(objs[i++]);
		this.certificateTypeCd = CommonHelper.toStr(objs[i++]);
		this.certificateNum = CommonHelper.toStr(objs[i++]);
		this.actualGuaranteeAmt = CommonHelper.toBigDecimal(objs[i++]);
		this.partyTypeCd = CommonHelper.toStr(objs[i++]);
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCertificateTypeCd() {
		return certificateTypeCd;
	}

	public void setCertificateTypeCd(String certificateTypeCd) {
		this.certificateTypeCd = certificateTypeCd;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public BigDecimal getActualGuaranteeAmt() {
		return actualGuaranteeAmt;
	}

	public void setActualGuaranteeAmt(BigDecimal actualGuaranteeAmt) {
		this.actualGuaranteeAmt = actualGuaranteeAmt;
	}

	public String getPartyTypeCd() {
		return partyTypeCd;
	}

	public void setPartyTypeCd(String partyTypeCd) {
		this.partyTypeCd = partyTypeCd;
	}
}

package com.coamctech.bxloan.service.model.contractmng;

import java.math.BigDecimal;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：ProjectPawnInfoVo
 * 类描述 ：从合同列表-抵质押信息使用VO
 * 创建人: wangyawei  
 * 创建时间：2015年8月6日 下午3:06:12  
 * 修改人：
 * 修改时间： 
 * 修改备注：
 * 版本： V1.0
 */
public class ProjectPawnInfoVo extends SubContractVo {
	/** 抵质押物ID */
	private Long guarantyId;
	
	/** 抵质押物类型 */
	private String guarantyTypeCd;
	
	/** 抵质押物编号 */
	private String guarantyNum;
	
	/** 抵质押物名称 */
	private String guarantyName;
	
	/** 抵质押人名称 */
	private String guarantorName;
	
	/** 评估价值 */
	private BigDecimal evalValue;
	
	/** 已设定担保额 */
	private BigDecimal setGuaranteeAmt;
	
	/** 本次实际担保债权金额 */
	private BigDecimal actualCreditAmount;
	
	/** 抵质押类型 */
	private String guaranteeType;
	
	public ProjectPawnInfoVo() {
		super();
	}
	
	public ProjectPawnInfoVo(Long subContractId, String guarantyTypeCd) {
		super(subContractId, guarantyTypeCd);
	}
	
	public ProjectPawnInfoVo(Object[] objs) {
		super();
		int i = 0;
		this.projectId = CommonHelper.toLong(objs[i++]);
		this.subContractId = CommonHelper.toLong(objs[i++]);
		this.subcontractNum = CommonHelper.toStr(objs[i++]);
		this.isTransDocument = CommonHelper.toStr(objs[i++]);
		this.documentNum = CommonHelper.toStr(objs[i++]);
		this.guarantyId = CommonHelper.toLong(objs[i++]);
		this.guarantyTypeCd = CommonHelper.toStr(objs[i++]);
		this.guarantyName = CommonHelper.toStr(objs[i++]);
		this.guarantorName = CommonHelper.toStr(objs[i++]);
		this.evalValue = CommonHelper.toBigDecimal(objs[i++]);
		this.setGuaranteeAmt = CommonHelper.toBigDecimal(objs[i++]);
		this.actualCreditAmount = CommonHelper.toBigDecimal(objs[i++]);
		this.guaranteeType = CommonHelper.toStr(objs[i++]);
	}

	public Long getGuarantyId() {
		return guarantyId;
	}

	public void setGuarantyId(Long guarantyId) {
		this.guarantyId = guarantyId;
	}

	public String getGuarantyTypeCd() {
		return guarantyTypeCd;
	}

	public void setGuarantyTypeCd(String guarantyTypeCd) {
		this.guarantyTypeCd = guarantyTypeCd;
	}

	public String getGuarantyNum() {
		return guarantyNum;
	}

	public void setGuarantyNum(String guarantyNum) {
		this.guarantyNum = guarantyNum;
	}

	public String getGuarantyName() {
		return guarantyName;
	}

	public void setGuarantyName(String guarantyName) {
		this.guarantyName = guarantyName;
	}

	public String getGuarantorName() {
		return guarantorName;
	}

	public void setGuarantorName(String guarantorName) {
		this.guarantorName = guarantorName;
	}

	public BigDecimal getEvalValue() {
		return evalValue;
	}

	public void setEvalValue(BigDecimal evalValue) {
		this.evalValue = evalValue;
	}

	public BigDecimal getSetGuaranteeAmt() {
		return setGuaranteeAmt;
	}

	public void setSetGuaranteeAmt(BigDecimal setGuaranteeAmt) {
		this.setGuaranteeAmt = setGuaranteeAmt;
	}

	public BigDecimal getActualCreditAmount() {
		return actualCreditAmount;
	}

	public void setActualCreditAmount(BigDecimal actualCreditAmount) {
		this.actualCreditAmount = actualCreditAmount;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
}

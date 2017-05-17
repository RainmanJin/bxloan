package com.coamctech.bxloan.service.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MoneyUtil;


public class ApprovalContentVO {
	/*** 业务编号 */
	private String projectNo;
	/***客户名称 */
	private String customerName;
	/*** 客户类型 */
	private String customerType;
	/***贷款产品*/
	private String productType;
	/***申报金额*/
	private String applyAmont;
	/***期限*/
	private String applyTerm;
	/***投放金额*/
	private String approveAmont;
	/***经办人*/
	private String applicant;
	/***申报时间*/
	private String applyDate;
	/***年利率*/
	private String bizRate;
	/**partyId*/
	private String partyId;
	/**还款方式*/
	private String repayMode;
	/**担保方式*/
	private String guaranteeMode;
	/**经办人*/
	private String customerManagerName;
	/**投放行业**/
	private String industryName;
	/**经办人所属机构*/
	private String customerOrgName;
	
	//add by mz 20150715 start
	private String creditType;
	//add by mz 20150715 end
	
	public ApprovalContentVO() {super();}
	
	public ApprovalContentVO(Object[] dataSet) {
		super();
		int i = 0;
		this.setProjectNo(str(dataSet[i++]));
		this.setCustomerName(str(dataSet[i++]));
		
		this.setProductType(str(dataSet[i++]));
		
		//申报金额
		this.setApplyAmont(MoneyUtil.formatMoney(scaleHalfUp(dataSet[i++],2)));
		//期限
		this.setApplyTerm(str(dataSet[i++]));
		
		this.setApproveAmont(MoneyUtil.formatMoney(scaleHalfUp(dataSet[i++],2)));
		this.setApplicant(str(dataSet[i++]));
		
		this.setApplyDate(str(dataSet[i++]));
		//年利率
		if(StringUtils.isNotBlank(str(dataSet[i++]))){
			this.setBizRate(scaleHalfUp(dataSet[i-1],6).toPlainString());
		}
		
		this.setPartyId(str(dataSet[i++]));
		this.setCustomerManagerName(str(dataSet[i++]));
		
		this.setRepayMode(str(dataSet[i++]));
		this.setGuaranteeMode(str(dataSet[i++]));
		
		this.setIndustryName(str(dataSet[i++]));
		this.setCustomerOrgName(str(dataSet[i++]));
	}
	
	private String str(Object obj){
		return obj==null?"":String.valueOf(obj).trim();
	}
	
	
	//////////////////////////
	///GETTERS&SETTERS///
	////////////////////////
	public String getProjectNo() {
		return projectNo;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getApplyAmont() {
		return applyAmont;
	}
	public void setApplyAmont(String applyAmont) {
		this.applyAmont = applyAmont;
	}
	public String getApplyTerm() {
		return applyTerm;
	}
	public void setApplyTerm(String applyTerm) {
		this.applyTerm = applyTerm;
	}
	public String getApproveAmont() {
		return approveAmont;
	}
	public void setApproveAmont(String approveAmont) {
		this.approveAmont = approveAmont;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getBizRate() {
		return bizRate;
	}
	public void setBizRate(String bizRate) {
		this.bizRate = bizRate;
	}
	public String getPartyId() {
		return partyId;
	}
	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}
	public String getCustomerManagerName() {
		return customerManagerName;
	}
	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}
	public String getRepayMode() {
		return repayMode;
	}
	public void setRepayMode(String repayMode) {
		this.repayMode = repayMode;
	}
	public String getGuaranteeMode() {
		return guaranteeMode;
	}
	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}
	public String getCustomerOrgName() {
		return customerOrgName;
	}
	public void setCustomerOrgName(String customerOrgName) {
		this.customerOrgName = customerOrgName;
	}
	private BigDecimal scaleHalfUp(Object obj, int scale){
		BigDecimal val = CommonHelper.toBigDecimal(obj);
		if(val!=null){
			return val.setScale(scale, BigDecimal.ROUND_HALF_UP);
		}else{
			return val;
		}
	}
	
	public String getCreditType() {
		return creditType;
	}
	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}
}

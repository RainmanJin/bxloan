package com.coamctech.bxloan.service.model.datatransend;

import java.io.Serializable;

import com.coamctech.bxloan.commons.utils.CommonHelper;

/**   
 * 类名称：ContractInfoExportVo
 * 类描述 ：导出合同信息vo类
 * 创建人: 马峥  
 * 创建时间：2015年6月26日 下午5:07:01  
 * 修改人：马峥
 * 修改时间：2015年6月26日 下午5:07:01  
 * 修改备注：
 * 版本： V1.0
 */
public class ContractInfoExportVo implements Serializable {
	private static final long serialVersionUID = 877814715015893954L;
	private String partyName;        		//客户名称
	private String certificateNum;	 		//证件号码
	private String contractNum;		 		//合同编号
	private String contractAmt;	    		//合同金额元
	private String contractRate;			//贷款利率
	private String contractStartDate;	    //合同开始日期
	private String contractEndDate;	 		//合同结束日期
	private String loanActRelDate;	 		//贷款实际发放日
	private String loanStatus;	    		//贷款状态
	private String fiveclassification;		//五级分类
	private String isMicroEnterprise;		//是否微型企业
	private String loanOrientation;		    //贷款投向
	private String loanApplication;         //借款用途
	private String isVentureEnterprise;		//是否面向创业企业
	private String isTechnologyEnterprise;	//是否面向科技企业
	private String isCulturalEnterprise;	//是否面向文化创意企业
	private String isWomenLoan;		        //是否妇女贷款
	private String credit;		            //信用
	private String guarantee;			    //保证
	private String mortgage;			    //抵押
	private String pledge;		            //质押
	
	public ContractInfoExportVo() {
		super();
	}

	public ContractInfoExportVo(Object[] objs){
		super();
		int i = 0;
		this.partyName = CommonHelper.toStr(objs[i++]);
		this.certificateNum = CommonHelper.toStr(objs[i++]);
		this.contractNum = CommonHelper.toStr(objs[i++]);
		this.contractAmt = CommonHelper.toStr(objs[i++]); 
		this.contractRate = CommonHelper.toStr(objs[i++]);
		this.contractStartDate = CommonHelper.toStr(objs[i++]);
		this.contractEndDate = CommonHelper.toStr(objs[i++]);
		this.loanActRelDate = CommonHelper.toStr(objs[i++]);
		this.loanStatus = CommonHelper.toStr(objs[i++]);
		this.fiveclassification = CommonHelper.toStr(objs[i++]);
		this.isMicroEnterprise = CommonHelper.toStr(objs[i++]);
		this.loanOrientation = CommonHelper.toStr(objs[i++]);
		this.loanApplication = CommonHelper.toStr(objs[i++]);
		this.isVentureEnterprise = CommonHelper.toStr(objs[i++]);
		this.isTechnologyEnterprise = CommonHelper.toStr(objs[i++]);
		this.isCulturalEnterprise = CommonHelper.toStr(objs[i++]);
		this.isWomenLoan = CommonHelper.toStr(objs[i++]);
		this.credit = CommonHelper.toStr(objs[i++]);
		this.guarantee = CommonHelper.toStr(objs[i++]); 
		this.mortgage = CommonHelper.toStr(objs[i++]);
		this.pledge = CommonHelper.toStr(objs[i++]);
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
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

	public String getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(String contractAmt) {
		this.contractAmt = contractAmt;
	}

	public String getContractRate() {
		return contractRate;
	}

	public void setContractRate(String contractRate) {
		this.contractRate = contractRate;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public String getLoanActRelDate() {
		return loanActRelDate;
	}

	public void setLoanActRelDate(String loanActRelDate) {
		this.loanActRelDate = loanActRelDate;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getFiveclassification() {
		return fiveclassification;
	}

	public void setFiveclassification(String fiveclassification) {
		this.fiveclassification = fiveclassification;
	}

	public String getIsMicroEnterprise() {
		return isMicroEnterprise;
	}

	public void setIsMicroEnterprise(String isMicroEnterprise) {
		this.isMicroEnterprise = isMicroEnterprise;
	}

	public String getLoanOrientation() {
		return loanOrientation;
	}

	public void setLoanOrientation(String loanOrientation) {
		this.loanOrientation = loanOrientation;
	}

	public String getIsVentureEnterprise() {
		return isVentureEnterprise;
	}

	public void setIsVentureEnterprise(String isVentureEnterprise) {
		this.isVentureEnterprise = isVentureEnterprise;
	}

	public String getIsTechnologyEnterprise() {
		return isTechnologyEnterprise;
	}

	public void setIsTechnologyEnterprise(String isTechnologyEnterprise) {
		this.isTechnologyEnterprise = isTechnologyEnterprise;
	}

	public String getIsCulturalEnterprise() {
		return isCulturalEnterprise;
	}

	public void setIsCulturalEnterprise(String isCulturalEnterprise) {
		this.isCulturalEnterprise = isCulturalEnterprise;
	}

	public String getIsWomenLoan() {
		return isWomenLoan;
	}

	public void setIsWomenLoan(String isWomenLoan) {
		this.isWomenLoan = isWomenLoan;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}

	public String getMortgage() {
		return mortgage;
	}

	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	public String getPledge() {
		return pledge;
	}

	public void setPledge(String pledge) {
		this.pledge = pledge;
	}

	public String getLoanApplication() {
		return loanApplication;
	}

	public void setLoanApplication(String loanApplication) {
		this.loanApplication = loanApplication;
	}
	
}

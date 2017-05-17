package com.coamctech.bxloan.service.model.bizapply;

import java.math.BigDecimal;
import java.util.List;

import com.coamctech.bxloan.entity.Product;
import com.coamctech.bxloan.entity.ProductConfig;
import com.coamctech.bxloan.service.model.workflow.NextTaskReceiver;
import com.coamctech.bxloan.service.model.workflow.TaskAction;

public class BusinessApplicationWdVO {
	private Long projectId;// ID
	private String customerName;// 客户名称
	private Long partyId;// 客户ID
	private String customerNum;// 客户编号
	/**
	 * 个人客户信息
	 */
	private String marriageCd;// 婚姻状况
	private String degreeCd;// 最高学历
	private String industryCd;// 所属行业
	private String familyMonIncome;// 家庭实际月收入（元）
	private String employmentType;// 雇佣类型
	private BigDecimal maintainPersonNum;// 供养人数
	/**
	 * 企业客户信息
	 */
	private String industryLevelTwoCd;// 行业类别
	private String customerScale;// 客户规模
	private BigDecimal registeredCapital;// 注册资本
	private BigDecimal actualRevAmt;// 实收资本
	private String orgTypeCd;// 所有制类别

	private String applyDate;// 申报日期
	private String projectNo;// 业务编号
	private String productType;// 贷款产品
	private BigDecimal applyAmt;// 申报金额
	private Integer applyTerm;// 贷款期限
	private String applyTermUnit;// 期限单位
	private String repayingMode;// 还款方式
	private Integer replyPeriodNum;// 还款周期月数
	private String agricultureInd;// 是否涉农
	// private String currency;// 币种
	private String guaranteeMode;// 担保方式
	private String investmentIndustry;// 投放行业
	private String mateBorrower;// 配偶是否作为共同借款人
	private BigDecimal preRepaymentRate;// 提前还款违约比例（%）
	private String purpose;// 贷款用途详细描述
	private String payment;// 还款来源
	private BigDecimal bizRate;// 年利率
	private BigDecimal ovdueRate;// 逾期利率上浮比例（%）
	private BigDecimal perculNegoRate;// 挪用利率上浮比例（%）
	private Long workflowId;
	private String taskId;
	private List<TaskAction> buttons;// 按钮
	private List<NextTaskReceiver> receivers;// 下一环节接收人
	private Product product;// 当前产品
	private String ifInsure;// 是否保险
	private String insuranceOrgId;// 保险机构
	private BigDecimal prePremium;// 预收保费
	private String isheadcol;// 是否总部协同业务
	private String argoBizType;// 农业非农业类型
	
	private String unGuId;//联保体协议编号
	private String unGroupName;
	private String unId;
	
	/**
	 * 利率表
	 */
	private Long bizRateId;
	private String productCd;
	private String irTypeCd;// 利率类型
	private String adjustPeriod;// 调整周期
	private BigDecimal irNegoSymbCd;// 利率上浮比例（%）
	
	//add by wangyawei 20150423 start 
	//合同信息
	private String customerType;			//客户类型
	private String contractNum;				//合同编号
	private BigDecimal contractAmt; 		//合同金额（元）
	private BigDecimal	contractBalance;	//合同余额（元）
	private Integer contractTermTotal;		//合同期限
	private String contractTermUnitTotal;	//合同期限单位
	private String bankName;				//开户银行
	private String loanNum;					//贷款帐号
	private String loanDateStyle;			//约定方式
	private Integer arrangeRepayDay;		//约定还款日
	private Integer repayPrincipalMonthes;	//还款周期月数
	private String approveRepayingMode;		//还款方式
	private String industryName;			//投放行业名称
	private String applyOrgName;            //贷款机构
	
	//放款记录信息
	private String loanIfInsure;			//是否保险
	private String loanInsuranceOrgId;		//保险机构ID
	private BigDecimal loanPremium;			//预收保费
	private BigDecimal autualPremium;		//实收保费
	
	//最终利率
	private String	rateAdjustmentReason;	//利率调整原因
	private String finalIrTypeCd; 		 	//最终利率类型
	private BigDecimal finalRateValue; 	 	//最终利率值
	private BigDecimal finalFloatRate; 	 	//最终利率上浮比例
	private String finalAdjustPeriod;   	//最终调整周期
	private BigDecimal ovdueIrNegoRate; 	//逾期利率上浮比例
	private BigDecimal perculIrNegoRate; 	 //挪用利率上浮比例
	//add by wangyawei 20150423 end
	
	//add by mz 20150710 start
	private String creditType;  //授信类型
	private String wfCode;   //流程类型
	private String taskStageCode; //流程节点
	//add by mz 20150710 end
	//add by wangpeng on 2015-07-27 start
	private String assistancer;//协办客户经理
	//add by wangpeng on 2015-07-27 end
	/**
	 * curuser
	 * @return
	 */
	private Long curUserOrgId;
	private Long curUserId;
	
	private ProductConfig productConfig;
	
	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getMarriageCd() {
		return marriageCd;
	}

	public void setMarriageCd(String marriageCd) {
		this.marriageCd = marriageCd;
	}

	public String getDegreeCd() {
		return degreeCd;
	}

	public void setDegreeCd(String degreeCd) {
		this.degreeCd = degreeCd;
	}

	public String getIndustryCd() {
		return industryCd;
	}

	public void setIndustryCd(String industryCd) {
		this.industryCd = industryCd;
	}

	public String getFamilyMonIncome() {
		return familyMonIncome;
	}

	public void setFamilyMonIncome(String familyMonIncome) {
		this.familyMonIncome = familyMonIncome;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public BigDecimal getMaintainPersonNum() {
		return maintainPersonNum;
	}

	public String getIndustryLevelTwoCd() {
		return industryLevelTwoCd;
	}

	public void setIndustryLevelTwoCd(String industryLevelTwoCd) {
		this.industryLevelTwoCd = industryLevelTwoCd;
	}

	public String getCustomerScale() {
		return customerScale;
	}

	public void setCustomerScale(String customerScale) {
		this.customerScale = customerScale;
	}

	public BigDecimal getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public BigDecimal getActualRevAmt() {
		return actualRevAmt;
	}

	public void setActualRevAmt(BigDecimal actualRevAmt) {
		this.actualRevAmt = actualRevAmt;
	}

	public String getOrgTypeCd() {
		return orgTypeCd;
	}

	public void setOrgTypeCd(String orgTypeCd) {
		this.orgTypeCd = orgTypeCd;
	}

	public void setMaintainPersonNum(BigDecimal maintainPersonNum) {
		this.maintainPersonNum = maintainPersonNum;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getApplyAmt() {
		return applyAmt;
	}

	public void setApplyAmt(BigDecimal applyAmt) {
		this.applyAmt = applyAmt;
	}

	public Integer getApplyTerm() {
		return applyTerm;
	}

	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}

	public String getApplyTermUnit() {
		return applyTermUnit;
	}

	public void setApplyTermUnit(String applyTermUnit) {
		this.applyTermUnit = applyTermUnit;
	}

	public String getRepayingMode() {
		return repayingMode;
	}

	public void setRepayingMode(String repayingMode) {
		this.repayingMode = repayingMode;
	}

	public Integer getReplyPeriodNum() {
		return replyPeriodNum;
	}

	public void setReplyPeriodNum(Integer replyPeriodNum) {
		this.replyPeriodNum = replyPeriodNum;
	}

	public String getAgricultureInd() {
		return agricultureInd;
	}

	public void setAgricultureInd(String agricultureInd) {
		this.agricultureInd = agricultureInd;
	}

	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	public String getInvestmentIndustry() {
		return investmentIndustry;
	}

	public void setInvestmentIndustry(String investmentIndustry) {
		this.investmentIndustry = investmentIndustry;
	}

	public String getMateBorrower() {
		return mateBorrower;
	}

	public void setMateBorrower(String mateBorrower) {
		this.mateBorrower = mateBorrower;
	}

	public BigDecimal getPreRepaymentRate() {
		return preRepaymentRate;
	}

	public void setPreRepaymentRate(BigDecimal preRepaymentRate) {
		this.preRepaymentRate = preRepaymentRate;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public List<TaskAction> getButtons() {
		return buttons;
	}

	public void setButtons(List<TaskAction> buttons) {
		this.buttons = buttons;
	}

	public List<NextTaskReceiver> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<NextTaskReceiver> receivers) {
		this.receivers = receivers;
	}

	public BigDecimal getBizRate() {
		return bizRate;
	}

	public void setBizRate(BigDecimal bizRate) {
		this.bizRate = bizRate;
	}

	public BigDecimal getOvdueRate() {
		return ovdueRate;
	}

	public void setOvdueRate(BigDecimal ovdueRate) {
		this.ovdueRate = ovdueRate;
	}

	public BigDecimal getPerculNegoRate() {
		return perculNegoRate;
	}

	public void setPerculNegoRate(BigDecimal perculNegoRate) {
		this.perculNegoRate = perculNegoRate;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getIfInsure() {
		return ifInsure;
	}

	public void setIfInsure(String ifInsure) {
		this.ifInsure = ifInsure;
	}

	public String getInsuranceOrgId() {
		return insuranceOrgId;
	}

	public void setInsuranceOrgId(String insuranceOrgId) {
		this.insuranceOrgId = insuranceOrgId;
	}

	public BigDecimal getPrePremium() {
		return prePremium;
	}

	public void setPrePremium(BigDecimal prePremium) {
		this.prePremium = prePremium;
	}

	public String getIsheadcol() {
		return isheadcol;
	}

	public void setIsheadcol(String isheadcol) {
		this.isheadcol = isheadcol;
	}

	public Long getBizRateId() {
		return bizRateId;
	}

	public void setBizRateId(Long bizRateId) {
		this.bizRateId = bizRateId;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getIrTypeCd() {
		return irTypeCd;
	}

	public void setIrTypeCd(String irTypeCd) {
		this.irTypeCd = irTypeCd;
	}

	public String getAdjustPeriod() {
		return adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	public BigDecimal getIrNegoSymbCd() {
		return irNegoSymbCd;
	}

	public void setIrNegoSymbCd(BigDecimal irNegoSymbCd) {
		this.irNegoSymbCd = irNegoSymbCd;
	}

	public Long getCurUserOrgId() {
		return curUserOrgId;
	}

	public void setCurUserOrgId(Long curUserOrgId) {
		this.curUserOrgId = curUserOrgId;
	}

	public String getArgoBizType() {
		return argoBizType;
	}

	public void setArgoBizType(String argoBizType) {
		this.argoBizType = argoBizType;
	}

	public ProductConfig getProductConfig() {
		return productConfig;
	}

	public void setProductConfig(ProductConfig productConfig) {
		this.productConfig = productConfig;
	}

	public String getContractNum() {
		return contractNum;
	}

	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}

	public BigDecimal getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(BigDecimal contractAmt) {
		this.contractAmt = contractAmt;
	}

	public Integer getContractTermTotal() {
		return contractTermTotal;
	}

	public void setContractTermTotal(Integer contractTermTotal) {
		this.contractTermTotal = contractTermTotal;
	}

	public String getContractTermUnitTotal() {
		return contractTermUnitTotal;
	}

	public void setContractTermUnitTotal(String contractTermUnitTotal) {
		this.contractTermUnitTotal = contractTermUnitTotal;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getLoanNum() {
		return loanNum;
	}

	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}

	public String getLoanDateStyle() {
		return loanDateStyle;
	}

	public void setLoanDateStyle(String loanDateStyle) {
		this.loanDateStyle = loanDateStyle;
	}

	public Integer getArrangeRepayDay() {
		return arrangeRepayDay;
	}

	public void setArrangeRepayDay(Integer arrangeRepayDay) {
		this.arrangeRepayDay = arrangeRepayDay;
	}

	public Integer getRepayPrincipalMonthes() {
		return repayPrincipalMonthes;
	}

	public void setRepayPrincipalMonthes(Integer repayPrincipalMonthes) {
		this.repayPrincipalMonthes = repayPrincipalMonthes;
	}

	public String getLoanIfInsure() {
		return loanIfInsure;
	}

	public void setLoanIfInsure(String loanIfInsure) {
		this.loanIfInsure = loanIfInsure;
	}

	public String getLoanInsuranceOrgId() {
		return loanInsuranceOrgId;
	}

	public void setLoanInsuranceOrgId(String loanInsuranceOrgId) {
		this.loanInsuranceOrgId = loanInsuranceOrgId;
	}

	public BigDecimal getLoanPremium() {
		return loanPremium;
	}

	public void setLoanPremium(BigDecimal loanPremium) {
		this.loanPremium = loanPremium;
	}

	public BigDecimal getAutualPremium() {
		return autualPremium;
	}

	public void setAutualPremium(BigDecimal autualPremium) {
		this.autualPremium = autualPremium;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getRateAdjustmentReason() {
		return rateAdjustmentReason;
	}

	public void setRateAdjustmentReason(String rateAdjustmentReason) {
		this.rateAdjustmentReason = rateAdjustmentReason;
	}

	public String getFinalIrTypeCd() {
		return finalIrTypeCd;
	}

	public void setFinalIrTypeCd(String finalIrTypeCd) {
		this.finalIrTypeCd = finalIrTypeCd;
	}

	public BigDecimal getFinalRateValue() {
		return finalRateValue;
	}

	public void setFinalRateValue(BigDecimal finalRateValue) {
		this.finalRateValue = finalRateValue;
	}

	public BigDecimal getFinalFloatRate() {
		return finalFloatRate;
	}

	public void setFinalFloatRate(BigDecimal finalFloatRate) {
		this.finalFloatRate = finalFloatRate;
	}

	public String getFinalAdjustPeriod() {
		return finalAdjustPeriod;
	}

	public void setFinalAdjustPeriod(String finalAdjustPeriod) {
		this.finalAdjustPeriod = finalAdjustPeriod;
	}

	public BigDecimal getOvdueIrNegoRate() {
		return ovdueIrNegoRate;
	}

	public void setOvdueIrNegoRate(BigDecimal ovdueIrNegoRate) {
		this.ovdueIrNegoRate = ovdueIrNegoRate;
	}

	public BigDecimal getPerculIrNegoRate() {
		return perculIrNegoRate;
	}

	public void setPerculIrNegoRate(BigDecimal perculIrNegoRate) {
		this.perculIrNegoRate = perculIrNegoRate;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public BigDecimal getContractBalance() {
		return contractBalance;
	}

	public void setContractBalance(BigDecimal contractBalance) {
		this.contractBalance = contractBalance;
	}

	public String getApproveRepayingMode() {
		return approveRepayingMode;
	}

	public void setApproveRepayingMode(String approveRepayingMode) {
		this.approveRepayingMode = approveRepayingMode;
	}

	public String getApplyOrgName() {
		return applyOrgName;
	}

	public void setApplyOrgName(String applyOrgName) {
		this.applyOrgName = applyOrgName;
	}

	public String getUnGuId() {
		return unGuId;
	}

	public void setUnGuId(String unGuId) {
		this.unGuId = unGuId;
	}

	public String getUnGroupName() {
		return unGroupName;
	}

	public void setUnGroupName(String unGroupName) {
		this.unGroupName = unGroupName;
	}

	public String getUnId() {
		return unId;
	}

	public void setUnId(String unId) {
		this.unId = unId;
	}

	public String getWfCode() {
		return wfCode;
	}

	public void setWfCode(String wfCode) {
		this.wfCode = wfCode;
	}

	public String getTaskStageCode() {
		return taskStageCode;
	}

	public void setTaskStageCode(String taskStageCode) {
		this.taskStageCode = taskStageCode;
	}
	
	public Long getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(Long curUserId) {
		this.curUserId = curUserId;
	}
	
	public String getAssistancer() {
		return assistancer;
	}

	public void setAssistancer(String assistancer) {
		this.assistancer = assistancer;
	}
	
}

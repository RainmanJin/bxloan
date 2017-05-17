package com.coamctech.bxloan.service.model.statistics;

import java.io.Serializable;
import java.util.List;

/**
 * @author Acore
 *
 */
public class BizExcelExportVo implements Serializable{
	
	/**
	 * 申请信息
	 */
	private BizAppAccItemVo appAccItem;
	
	/**
	 * 流程信息
	 */
	private List<BizWfItemVo> wfItems;
	
	/**
	 * 
	 */
	private BizApproveResultVo approveResult;
	/**
	 * 
	 */
	private BizPayLoanInfoVo payLoanInfo;


	public BizAppAccItemVo getAppAccItem() {
		return appAccItem;
	}

	public void setAppAccItem(BizAppAccItemVo appAccItem) {
		this.appAccItem = appAccItem;
	}

	public List<BizWfItemVo> getWfItems() {
		return wfItems;
	}

	public void setWfItems(List<BizWfItemVo> wfItems) {
		this.wfItems = wfItems;
	}

	public BizApproveResultVo getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(BizApproveResultVo approveResult) {
		this.approveResult = approveResult;
	}

	public BizPayLoanInfoVo getPayLoanInfo() {
		return payLoanInfo;
	}

	public void setPayLoanInfo(BizPayLoanInfoVo payLoanInfo) {
		this.payLoanInfo = payLoanInfo;
	}
	
}

package com.coamctech.bxloan.service.model.accountingmng;

import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanBusinessType;

/**
 * 业务信息查看
 *
 */
public class BizBaseVo {
	/**
	 * 
	 */
	private LoanBusinessType businessType;
	

	public BizBaseVo() {
		super();
	}

	public LoanBusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(LoanBusinessType businessType) {
		this.businessType = businessType;
	}
}

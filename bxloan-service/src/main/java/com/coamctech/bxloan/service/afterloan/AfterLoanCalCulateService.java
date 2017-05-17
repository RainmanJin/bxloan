package com.coamctech.bxloan.service.afterloan;

import com.coamctech.bxloan.service.model.loanprocess.InterestResultVO;

public interface AfterLoanCalCulateService {
	/**
	 * 费用登记
	 * 
	 * @param interestResultVO
	 * @return
	 */
	InterestResultVO registerCostService(InterestResultVO interestResultVO);
	
}

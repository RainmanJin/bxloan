package com.coamctech.bxloan.service.loanprocess;

import java.util.List;

import com.coamctech.bxloan.service.model.loanprocess.InterestCalCulateForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestVO;

public interface InterestCalCulateService {
	/**
	 * 贷款计算
	 * 
	 * @param interForm
	 * @return
	 */
	List<InterestVO> calCulate(InterestCalCulateForm interForm);

	/**
	 * 可计算自定义还款计划的贷款计算
	 * 
	 * @param interForm
	 * @return
	 */
	List<InterestVO> loanCounter(InterestCalCulateForm interForm)
			throws Exception;
}

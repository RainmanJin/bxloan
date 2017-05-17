package com.coamctech.bxloan.service.pettyloan.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.service.pettyloan.LoanFunctionService;
import com.coamctech.bxloan.service.pettyloan.LoanRepayService;
import com.coamctech.bxloan.service.pettyloan.bo.LoanResult;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfInMoney;
import com.coamctech.bxloan.service.pettyloan.bo.ParamsOfOutMoney;
@Transactional
@Service
public class LoanFunctionServiceImpl implements LoanFunctionService {
	@Autowired
	private LoanRepayService loanRepayService;

	@Override
	public LoanResult outOfMoney(ParamsOfOutMoney outMoney) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanResult inOfMoney(ParamsOfInMoney inMoney, RepayLoan repayLoan) {
		LoanResult lf = loanRepayService.updateAllInfoOfRepayLoan(inMoney, repayLoan);
		return lf;
	}

	@Override
	public void dealBill(String billCd, String operator, Date busiDate,
			String status, String backCause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flushesBill(String billCd, String operator) {
		// TODO Auto-generated method stub

	}

}

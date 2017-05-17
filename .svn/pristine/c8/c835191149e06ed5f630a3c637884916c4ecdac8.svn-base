package com.coamctech.bxloan.service.pettyloan.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.LoanCancleDao;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.LoanCancle;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanVerifyCancelService;
@Transactional
@Service("loanVerifyCancelService")
public class LoanVerifyCancelServiceImpl implements LoanVerifyCancelService{
	@Autowired
	private LoanCancleDao loanCancleDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Override
	public void updateLocCancContract(String transNo, String status,
			Date date) {
		List<LoanCancle> loanCancles=loanCancleDao.findListByTransNo(transNo);
		for (LoanCancle lc : loanCancles) {
			Contract contract  = contractDao.findByContractId(lc.getContractId());
			if(loanCommonServeice.getCodeVal("PayLoanStatus", "S3").equals(status)){//退单
				if(!loanCommonServeice.getCodeVal("PayLoanStatus", "S4").equals(lc.getLoanCancleStateCd())){//财务退单
					contract.setContractStatusCd(lc.getHisConState());//返回合历史状态
				}else{//冲销退单
					status = loanCommonServeice.getCodeVal("PayLoanStatus", "S2");
				}
			}else if(loanCommonServeice.getCodeVal("PayLoanStatus", "S2").equals(status)){//财务已入账
				contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S6"));//合同状态变成已核销
			}else if(loanCommonServeice.getCodeVal("PayLoanStatus", "S5").equals(status)){//冲销已入账
				contract.setContractStatusCd(lc.getHisConState());//合同返回历史状态
			}
			lc.setLoanCancleStateCd(status);
			contractDao.save(contract);
			loanCancleDao.save(lc);
		}
	}
	
}

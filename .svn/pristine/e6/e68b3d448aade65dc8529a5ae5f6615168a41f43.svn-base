package com.coamctech.bxloan.service.pettyloan.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.dao.LossProvisionDao;
import com.coamctech.bxloan.entity.LossProvision;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanLossProvisionService;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
@Transactional
@Service("loanLossProvisionService")
public class LoanLossProvisionServiceImpl implements LoanLossProvisionService {
	@Autowired
	private LossProvisionDao lossProvisionDao;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Override
	public void updateLossProvision(String transNo, String transStatus) {
		
		List<LossProvision> lossProvisionList = lossProvisionDao.findListBy(transNo, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
		if(CollectionUtils.isEmpty(lossProvisionList)){
			
		}
		LossProvision updateVO = lossProvisionList.get(0);
		updateVO.setTransactionStatus(transStatus);
		lossProvisionDao.save(updateVO);
	}

}

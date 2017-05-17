package com.coamctech.bxloan.service.assloan.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.AssTranProjectApplicationDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.AssTranProjectApplication;
import com.coamctech.bxloan.service.assloan.AssOperationService;
@Transactional
@Service
public class AssOperationServiceImpl implements AssOperationService{
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private AssTranProjectApplicationDao assTranProjectApplicationDao;
	
	/**
	 * 根据ID更新机构收回本金
	 * 
	 * @param projectId
	 * @param repayedAmt
	 */
	@Override
	public void updateAsstranProjectAppById(Long projectId, BigDecimal repayedAmt) {
		if(null == projectId)
			return ;
		try {
			AssTranProjectApplication assTranProApp = assTranProjectApplicationDao.findByProjectId(projectId);
			if (assTranProApp != null) {
				if (assTranProApp.getOrgrepayamt() == null) {
					assTranProApp.setOrgrepayamt(BigDecimal.ZERO);
				}
				// 更新可购买金额
				assTranProApp.setOrgrepayamt(assTranProApp.getOrgrepayamt().add(repayedAmt));
				// 更新资产包资金利用率
				assTranProApp.setUtilizationOfFunds(assTranProApp.getFpoolAmt().subtract(repayedAmt)
						.divide(assTranProApp.getAssetPackageScale(), 6, BigDecimal.ROUND_HALF_UP));
				assTranProjectApplicationDao.save(assTranProApp);
			}
		} catch (Exception e) {
			throw new RuntimeException("更新资产包出错:" + e.getMessage());
		}
	}		
}

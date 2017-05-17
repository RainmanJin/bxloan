package com.coamctech.bxloan.service.contractmng.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.CollateralDao;
import com.coamctech.bxloan.dao.ProjectPawnInfoDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Collateral;
import com.coamctech.bxloan.entity.ProjectPawnInfo;
import com.coamctech.bxloan.service.contractmng.RenewaPledgeService;
@Transactional
@Service
public class RenewaPledgeServiceImpl implements RenewaPledgeService{

	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private ProjectPawnInfoDao projectPawnInfoDao;
	@Autowired
	private CollateralDao  collateralDao;
	
	
	@Override
	public void handlePledgeAmtWhenContractEnd(Long projectId)
			throws RuntimeException {
		try {
			List listPawn = projectPawnInfoDao.findByProjectId(projectId);
			//将相应的担保物对应的抵质押物进行金额的释放，以及判断是否修改抵质押物的状态，并且删除担保物
			if(null!=listPawn&&listPawn.size()>0){
				ProjectPawnInfo projectPawnInfo=null;
				Collateral collateral=null;
				for(int i=0;i<listPawn.size();i++){
					projectPawnInfo=(ProjectPawnInfo)listPawn.get(i);
					String pawnState=projectPawnInfo.getPawnState();
					//状态为【关联新增】和【关联置换】和【已关联】需要  扣除抵押物累计抵押金额
					collateral = collateralDao.findByGuarantyId(projectPawnInfo.getGuarantyId());
					if(pawnState.equals(dataDict.getUniqueOne("PawnState", "S1").getCodeValue())
							||pawnState.equals(dataDict.getUniqueOne("PawnState", "S2").getCodeValue())
							||pawnState.equals(dataDict.getUniqueOne("PawnState", "S3").getCodeValue())){
						//累计抵押金额=累计抵押金额-贷款放款金额
						//deductSetGuarantyAmt(collateral, projectPawnInfo.getActualCreditAmount());
						//抵押物状态需要改，但是有点复杂，判断（所有合同）已解除关联
						/*if(!this.isPledgeIsUsedByGuarantyId(collateral.getGuarantyId())){
							collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S3").getCodeValue());
						}else{
							collateral.setGuarantyStatusCd(dataDict.getUniqueOne("RevGuaranteeStatusCd", "S2").getCodeValue());
						}*/
						collateralDao.save(collateral);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new  RuntimeException(e.getMessage());
		}
	}

	
}

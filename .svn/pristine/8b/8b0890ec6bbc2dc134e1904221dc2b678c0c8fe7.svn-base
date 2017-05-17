package com.coamctech.bxloan.service.pettyloan.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.TallyCertificateDao;
import com.coamctech.bxloan.entity.AccountingInfo;
import com.coamctech.bxloan.entity.TallyCertificate;
import com.coamctech.bxloan.service.pettyloan.LoanAccountingService;
import com.coamctech.bxloan.service.pettyloan.LoanTallyCertificateService;
import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.BillStatus;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.RecordedVchFlag;
@Transactional
@Service("loanTallyCertificateService")
public class LoanTallyCertificateServiceImpl implements
		LoanTallyCertificateService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private TallyCertificateDao tallyCertificateDao ;
	@Autowired
	private  LoanAccountingService loanAccountingService;
	@Override
	public void dealBill(String billCd, String operator, Date busiDate,String status,
			String backCause) {
		if(this.isNotOpen(billCd)){
			throw new LoanBizException("未联动财务接口");
		}
		List<TallyCertificate> tcList=dynamicQuery.query(TallyCertificate.class,"from TallyCertificate where billCd = ?1", billCd);
		if(CollectionUtils.isEmpty(tcList)){
			throw new LoanBizException("业务凭证单据不存在");
		}
		TallyCertificate tc=tcList.get(0);
		int disFlag = tc.getDisFlag()==null?0: tc.getDisFlag();//类型（资产转让、正常合同）
		String billSts=tc.getBillSts();//业务凭证单据状态
		switch (disFlag) {
		case 1:
			//资产转让
			break;
		case 2:
			//授信放款
			break;
		default:
			//正常
			BillStatus bs=BillStatus.valOf(billSts);
			RecordedVchFlag rvf=RecordedVchFlag.valOf(status);
			if(BillStatus.SEND_NONE.equals(bs)){
				if(RecordedVchFlag.ENTER.equals(rvf)){
					//入账
					loanAccountingService.billEnter(billCd, operator, busiDate);
				}else if(RecordedVchFlag.BACK.equals(rvf)){
					//退单
					loanAccountingService.billBack(billCd, operator, busiDate,backCause);
				}
			}else if(BillStatus.CX_NONE.equals(bs)){
				if(RecordedVchFlag.ENTER.equals(rvf)){
					//冲销入账
					loanAccountingService.billEnterOfCX(billCd, operator, busiDate);
				}else if(RecordedVchFlag.BACK.equals(rvf)){
					//冲销退单
					loanAccountingService.billBackOfCX(billCd, operator, busiDate, backCause);
				}
			}else{
				throw new LoanBizException("未知状态["+bs.toStr()+"]，无法进行入账及退单");
			}
		}
	}
	@Override
	public void updateBillOfFlushes(String billCd, String operator) {

		List<TallyCertificate> tcList=dynamicQuery.query(TallyCertificate.class,"from TallyCertificate where billCd = ?1", billCd);
		if(CollectionUtils.isEmpty(tcList)){
			throw new LoanBizException("业务凭证单据不存在");
		}
		TallyCertificate tc=tcList.get(0);
		int disFlag = tc.getDisFlag()==null?0: tc.getDisFlag();//类型（资产转让、正常合同）
		switch (disFlag) {
		case 1:
			//资产转让
			break;
		case 2:
			//授信放款
			break;
		default:
			//正常
			loanAccountingService.billNoneEnterOfCX(billCd, operator);
		}
		if(this.isNotOpen(billCd)){
			//账务接口断开时，冲销后直接入账
			loanAccountingService.billEnterOfCX(billCd, operator, new Date());
		}
		
	}
	
	/**
	 *  财务接口是否开启
	 * @param billCd
	 * @return	true:关闭，false:开启
	 */
	private boolean isNotOpen(String billCd){
		AccountingInfo accInfo=loanAccountingService.findFirstAccountingInfo(billCd);
		final String orgId=accInfo.getBchId();//机构id
		return StringUtils.isBlank(orgId)||!loanAccountingService.isInterfaceOpenOfAccounting(orgId);
	}
}

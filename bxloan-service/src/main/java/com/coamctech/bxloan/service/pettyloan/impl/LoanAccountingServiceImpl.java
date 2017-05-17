package com.coamctech.bxloan.service.pettyloan.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.AccountingConst;
import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.dao.AccountingConfigDao;
import com.coamctech.bxloan.dao.AccountingFieldvalueDao;
import com.coamctech.bxloan.dao.AccountingInfoDao;
import com.coamctech.bxloan.dao.AccountingSubjectDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.LossProvisionDao;
import com.coamctech.bxloan.dao.PartyDao;
import com.coamctech.bxloan.dao.PayLoanInfoDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.RepayingPlanDao;
import com.coamctech.bxloan.dao.TallyCertificateDao;
import com.coamctech.bxloan.dao.TallyCertificateDetailDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.AccountingConfig;
import com.coamctech.bxloan.entity.AccountingFieldvalue;
import com.coamctech.bxloan.entity.AccountingInfo;
import com.coamctech.bxloan.entity.AccountingInfoPk;
import com.coamctech.bxloan.entity.AccountingSubject;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.LossProvision;
import com.coamctech.bxloan.entity.Party;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.TallyCertificate;
import com.coamctech.bxloan.entity.TallyCertificateDetail;
import com.coamctech.bxloan.service.pettyloan.LoanAccountingService;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanLossProvisionService;
import com.coamctech.bxloan.service.pettyloan.LoanPayService;
import com.coamctech.bxloan.service.pettyloan.LoanRepayService;
import com.coamctech.bxloan.service.pettyloan.LoanService;
import com.coamctech.bxloan.service.pettyloan.LoanVerifyCancelService;
import com.coamctech.bxloan.service.pettyloan.bo.AccountingBo;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchAmtBo;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchResultBo;
import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;
import com.coamctech.bxloan.service.pettyloan.util.AccountingConstants;
import com.coamctech.bxloan.service.pettyloan.util.ExpressionCalc;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.AccountEventType;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.AccountSource;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.BillStatus;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanEvent;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.RecordedVchFlag;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.TransmitFlag;
import com.coamctech.bxloan.service.sysmng.impl.CommonBizNumberBSImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
@Transactional
@Service("loanAccountingService")
public class LoanAccountingServiceImpl implements LoanAccountingService{
	Logger logger=LoggerFactory.getLogger(getClass());
	
	/** 按单笔合同计提 */
	public static String OBJECTDIMENSIONTYPE_CONTRACT = "2";
	
	@Autowired
	private PartyDao partyDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private TallyCertificateDao tallyCertificateDao ;
	@Autowired
	private TallyCertificateDetailDao tallyCertificateDetailDao ;
	@Autowired
	private AccountingInfoDao accountingInfoDao;
	@Autowired
	private AccountingConfigDao accountingConfigDao;
	@Autowired
	AccountingSubjectDao accountingSubjectDao;
	@Autowired
	AccountingFieldvalueDao accountingFieldvalueDao;
	@Autowired
	private RepayingPlanDao repayingPlanDao;
	@Autowired
	private PayLoanInfoDao payLoanInfoDao;
	@Autowired
	private LossProvisionDao lossProvisionDao;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private LoanPayService loanPayService;
	@Autowired
	private LoanService loanService;
	@Autowired
	private LoanLossProvisionService loanLossProvisionService;
	@Autowired
	private LoanRepayService loanRepayService;
	@Autowired
	private LoanVerifyCancelService loanVerifyCancelService;
	@Autowired
	private ProjectApplicationDao projectAppDao;
	@Autowired
	private CommonBizNumberBSImpl commonBizNumberBS;
	
	@Override
	public DoVchResultBo doVchByLoan(DoVchAmtBo doVchAmtVO, Integer bchId,
			Integer lastModUser,PayLoanInfo payLoan ) {
		AccountingBo accBo=new AccountingBo();
		accBo.setBussObject(doVchAmtVO);
		accBo.setBussType(AccountingConstants.ACCOUNTING_PRODUCTS_1110);
		AccountSource accSource=AccountSource.valOf(doVchAmtVO.getBcCostType());
		if(accSource==null){
			accSource=AccountSource.BANK;
		}
		switch (accSource) {
		case BOND:
			accBo.setEventType(AccountEventType.BOND_LOAN.toInt());
			break;
		case STOCK:
			accBo.setEventType(AccountEventType.STOCK_LOAN.toInt());
			break;
		default:
			accBo.setEventType(AccountEventType.LOAN.toInt());
			break;
		}
		final String partyTypeCd= findPartyTypeCd(doVchAmtVO.getPartyId());
		if (StringUtils.equals(loanCommonServeice.getCodeVal("CustomerType", "S1"),partyTypeCd)) {
			accBo.setItemId(1);//对公客户
		}else{
			accBo.setItemId(2);//个人客户
		}
		String txrefNo =loanCommonServeice.getTransNo(String.valueOf(AccountingConstants.ACCOUNTING_PRODUCTS_1110), String.valueOf(bchId), Integer.parseInt(partyTypeCd));
		accBo.setTxrefNo(txrefNo);
		accBo.setBchId(String.valueOf(bchId));
		accBo.setCcyId(findCurrencyType(doVchAmtVO.getContractId()));
		accBo.setPartyId(doVchAmtVO.getPartyId());
		accBo.setProjectId(doVchAmtVO.getProjectId()==0?0:doVchAmtVO.getProjectId());
		accBo.setContractId(doVchAmtVO.getContractId());
		accBo.setLoanId(doVchAmtVO.getLoanId());
		Date date=DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
		accBo.setTxDate(date);
		accBo.setVchDate(doVchAmtVO.getVchdate()!=null?doVchAmtVO.getVchdate():date);
		accBo.setLastModUser(String.valueOf(lastModUser));
		DoVchResultBo resultBo=doVch(accBo);
		resultBo.setTransNo(txrefNo);
		//保存单据凭证
		buildTallyCertificate(resultBo.getAccInfos(),payLoan );
		return resultBo;
	}
	@Override
	public String doVchByPaid(DoVchAmtBo doVchAmt,Long bchId , Long lastModUser){
		if (bchId == null || lastModUser == null)
			throw new LoanBizException("机构或操作人为空");
		AccountingBo accBo = new AccountingBo();
		accBo.setBussObject(doVchAmt);
		accBo.setBussType(AccountingConstants.ACCOUNTING_PRODUCTS_1110); // 小额信贷
		List<LossProvision> lpList=loanCommonServeice.findLossProvisionList(doVchAmt.getContractId(), LoanConstants.OBJECTDIMENSIONTYPE_CONTRACT, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		LossProvision lp=null;
		AccountSource accSource=AccountSource.valOf(doVchAmt.getBcCostType());
		if(accSource==null){
			accSource=AccountSource.BANK;
		}
		if(CollectionUtils.isNotEmpty(lpList)){
			lp=lpList.get(0);
		}
		if(lp!=null&&lp.getPrivisionAmt().compareTo(BigDecimal.ZERO)>0){
			if (doVchAmt.isChangeOverFlag()) {
				if (doVchAmt.getBcOverPrincipal().compareTo(BigDecimal.ZERO) > 0) {
					doVchAmt.setBcOverLoanAmt(lp.getPrivisionAmt());
				} else if (doVchAmt.getBcOverThan90Principal().compareTo(BigDecimal.ZERO) > 0) {
					doVchAmt.setBcOverThan90LoanAmt(lp.getPrivisionAmt());
				}
			}
			if (doVchAmt.getBcPaidCurrPrincipal().compareTo(BigDecimal.ZERO) > 0) {
				if (doVchAmt.getBcPaidCurrPrincipal().compareTo(lp.getPrivisionAmt()) >= 0) {
					doVchAmt.setCurrentAmt(lp.getPrivisionAmt());
					lp.setPrivisionAmt(BigDecimal.ZERO);
				} else {
					doVchAmt.setCurrentAmt(doVchAmt.getBcPaidCurrPrincipal());
					lp.setPrivisionAmt(lp.getPrivisionAmt().subtract(
							doVchAmt.getBcPaidCurrPrincipal()));
				}
			} else if (doVchAmt.getBcPaidOverPrincipal().compareTo(BigDecimal.ZERO) > 0) {
				if (doVchAmt.getBcPaidOverPrincipal().compareTo(lp.getPrivisionAmt()) >= 0) {
					doVchAmt.setBcOverCurrentAmt(lp.getPrivisionAmt());
					lp.setPrivisionAmt(BigDecimal.ZERO);
				} else {
					doVchAmt.setBcOverCurrentAmt(doVchAmt.getBcPaidOverPrincipal());
					lp.setPrivisionAmt(lp.getPrivisionAmt().subtract(
							doVchAmt.getBcPaidOverPrincipal()));
				}
			} else if (doVchAmt.getBcPaidOverThan90Principal().compareTo(BigDecimal.ZERO) > 0) {
				if (doVchAmt.getBcPaidOverThan90Principal().compareTo(lp.getPrivisionAmt()) >= 0) {
					doVchAmt.setBcOverThan90CurrentAmt(lp.getPrivisionAmt());
					lp.setPrivisionAmt(BigDecimal.ZERO);
				} else {
					doVchAmt.setBcOverThan90CurrentAmt(doVchAmt.getBcPaidOverThan90Principal());
					lp.setPrivisionAmt(lp.getPrivisionAmt().subtract(
							doVchAmt.getBcPaidOverThan90Principal()));
				}
			}
			lossProvisionDao.save(lp);//保存
			switch (accSource) {
			case BOND:
				accBo.setEventType(AccountEventType.YES_BOND_PAID.toInt());
				break;
			case STOCK:
				accBo.setEventType(AccountEventType.YES_STOCK_PAID.toInt());
				break;
			default:
				accBo.setEventType(AccountEventType.YES_PAID.toInt());
				break;
			}
		}else{
			switch (accSource) {
			case BOND:
				accBo.setEventType(AccountEventType.NO_BOND_PAID.toInt());
				break;
			case STOCK:
				accBo.setEventType(AccountEventType.NO_STOCK_PAID.toInt());
				break;
			default:
				accBo.setEventType(AccountEventType.PAID.toInt());
				break;
			}
		}
		if ((lp != null && lp.getPrivisionAmt().compareTo(BigDecimal.ZERO) != 0)
				|| lp == null) {
			doVchAmt.setBcNoLossAmt(BigDecimal.ZERO);
			doVchAmt.setBcNoLossInterest(BigDecimal.ZERO);
			doVchAmt.setBcOverThanNoLossAmt(BigDecimal.ZERO);
			doVchAmt.setBcOverThanNoLossInterest(BigDecimal.ZERO);
			doVchAmt.setBcOverThan90NoLossAmt(BigDecimal.ZERO);
			doVchAmt.setBcOverThan90NoLossInterest(BigDecimal.ZERO);
		}
		final String partyTypeCd=this.findPartyTypeCd(doVchAmt.getPartyId());
		if (StringUtils.equals(loanCommonServeice.getCodeVal("CustomerType", "S1"),partyTypeCd)) {
			accBo.setItemId(1);//对公客户
		}else{
			accBo.setItemId(2);//个人客户
		}
		String txrefno = loanCommonServeice.getTransNo(String.valueOf(AccountingConstants.ACCOUNTING_PRODUCTS_1110), String.valueOf(bchId), Integer.parseInt(partyTypeCd));
		accBo.setTxrefNo(txrefno);
		accBo.setBchId(bchId.toString());
		accBo.setCcyId(this.findCurrencyType(doVchAmt.getContractId()));
		accBo.setPartyId(doVchAmt.getPartyId());
		accBo.setProjectId(doVchAmt.getProjectId() == null ? 0 : doVchAmt.getProjectId());
		accBo.setContractId(doVchAmt.getContractId());
		accBo.setLoanId(doVchAmt.getLoanId());
		accBo.setTxDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss"));
		accBo.setVchDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss"));
		if (doVchAmt.getVchdate() == null) {
			accBo.setVchDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
					"yyyy-MM-dd HH:mm:ss"));
		} else {
			accBo.setVchDate(doVchAmt.getVchdate()); // 账务日期
		}
		accBo.setLastModUser(lastModUser.toString());
		doVch(accBo);
		return txrefno;
	}
	@Override
	public DoVchResultBo doVch(AccountingBo accBo) {
		List<AccountingConfig> configList =accountingConfigDao.findListByBussEventItem(String.valueOf(accBo.getBussType()), accBo.getEventType(), accBo.getItemId());
		if(CollectionUtils.isEmpty(configList)){
			 // 如果没有抓到传票帐务，则返回
			printLogOfDebug("busstype=" + String.valueOf(accBo.getBussType()) + ",eventtype=" + accBo.getEventType() + ",itemid=" + accBo.getItemId() + "没有设定对应的传票帐务");
			throw new LoanBizException("相关账务配置不存在");
		}
		DoVchResultBo resultBo=new DoVchResultBo();
		//所有帐务信息
		List<AccountingInfo> accInfos=Lists.newArrayList();
		//所有公式
		List<String[]> formulaResult=Lists.newArrayList();
		buildAccountingData(configList,accBo, accInfos, formulaResult);
		//vchData.setAccountingInfo(accountingInfo);
		// 判断借贷平衡
		String v_balance = checkDCBalance(accInfos)?"1":"0";
		printLogOfDebug("交易编号为" + accBo.getTxrefNo() + "帐务处理完毕");
		// 帐务处理完毕,组装所有帐务信息
		printLogOfDebug("allbussvch==="+accInfos);
		resultBo.setAccInfos(accInfos);
		// 所有的计算公式
		printLogOfDebug("formulaResult==="+formulaResult);
		resultBo.setFormulaResult(formulaResult);
		resultBo.setBalance(v_balance);
		return resultBo;
	}
	@Override
	public void billEnter(String billCd, String operator, Date busiDate) {
		List<AccountingInfo> accountingInfos=accountingInfoDao.findListByTxrefNo(billCd);
		if(CollectionUtils.isEmpty(accountingInfos)||accountingInfos.get(0)==null){
			throw new LoanBizException("账务信息不存在");
		}
		AccountingInfo accountingInfo_0=accountingInfos.get(0);
		String orgId=accountingInfo_0.getBchId();//机构id
		//财务接口是否开启
		if(StringUtils.isBlank(orgId)||!isInterfaceOpenOfAccounting(orgId)){
			throw new LoanBizException("未联动财务接口");
		}
		Long contractId=accountingInfo_0.getContractId();//合同id
		Integer eventType= accountingInfo_0.getEventType();//事件类型
		for (AccountingInfo accountingInfo : accountingInfos) {
			eventType = accountingInfo.getEventType();
			accountingInfo.setRecordedVchFlag(RecordedVchFlag.ENTER.toStr());// 已入账
			if (AccountEventType.LOAN.eq(eventType)
					|| AccountEventType.PAID.eq(eventType)) {
				accountingInfo.setVchDate(busiDate);
			}
			accountingInfoDao.save(accountingInfo);
		}
		//更新凭证
		updateTallyCertificateStatus(billCd, operator, busiDate, BillStatus.ENTER.toStr());
		//更新跑批
		updateBatchRecode(contractId, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
		
		AccountEventType aet=AccountEventType.valOf(eventType);
		switch (aet) {
		//放款
		case LOAN:
		case BOND_LOAN:
		case STOCK_LOAN:
			loanPayService.updatePayLoanInfo(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2), busiDate);
			loanService.approvalService(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2),
					LoanEvent.PAY_LOAN.toStr());
			break;
		//还款
		case PAID:
		case YES_PAID:
		case NO_BOND_PAID:
		case YES_BOND_PAID:
		case NO_STOCK_PAID:
		case YES_STOCK_PAID:
			loanService.approvalService(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2),
					LoanEvent.REPAYED.toStr());
			loanRepayService.updateRepayLoan(billCd, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
			break;
		//核销
		case NO_VERIFICATION:
		case YES_VERIFICATION:
			loanVerifyCancelService.updateLocCancContract(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2),
					busiDate);
			break;
		//单项损失准备金计提
		case NO_SINGLE:
		case YES_SINGLE:
			loanLossProvisionService.updateLossProvision(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
			break;
		//费用登记
		case NO_COST_BANK:
		case YES_COST_BANK:
		case NO_COST_BOND:
		case YES_COST_BOND:
		case NO_COST_STOCK:
		case YES_COST_STOCK:
			loanRepayService.updateBizExpenseRate(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
			break;
		case ATTORN:
			//资产转出
			break;
		default:
			break;
		}
	}
	@Override
	public void billBack(String billCd, String operator, Date busiDate,
			String backCause) {
		List<AccountingInfo> accountingInfos=accountingInfoDao.findListByTxrefNo(billCd);
		if(CollectionUtils.isEmpty(accountingInfos)||accountingInfos.get(0)==null){
			throw new LoanBizException("账务信息不存在");
		}
		AccountingInfo accountingInfo_0=accountingInfos.get(0);
		String orgId=accountingInfo_0.getBchId();//机构id
		//财务接口是否开启
		if(StringUtils.isBlank(orgId)||!isInterfaceOpenOfAccounting(orgId)){
			throw new LoanBizException("未联动财务接口");
		}
		Long contractId=accountingInfo_0.getContractId();//合同id
		Integer eventType= accountingInfo_0.getEventType();//事件类型
		for (AccountingInfo accountingInfo : accountingInfos) {
			eventType = accountingInfo.getEventType();
			accountingInfo.setRecordedVchFlag(RecordedVchFlag.BACK.toStr());// 已退单
			if (AccountEventType.LOAN.eq(eventType)
					|| AccountEventType.PAID.eq(eventType)) {
				accountingInfo.setVchDate(busiDate);
			}
			accountingInfoDao.save(accountingInfo);
		}
		//更新账务信息（TallyCertificate）已退单047004
		updateTallyCertificateStatusOfBack(billCd, operator, busiDate, BillStatus.BACK.toStr(), backCause, false);
		//更新跑批
		updateBatchRecode(contractId, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
		
		AccountEventType aet=AccountEventType.valOf(eventType);
		switch (aet) {
		//放款
		case LOAN:
		case BOND_LOAN:
		case STOCK_LOAN:
			loanPayService.updatePayLoanInfo(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3), busiDate);
			loanService.approvalService(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3),
					LoanEvent.PAY_LOAN.toStr());
			//TODO MessageForm
			break;
		//还款
		case PAID:
		case YES_PAID:
		case NO_BOND_PAID:
		case YES_BOND_PAID:
		case NO_STOCK_PAID:
		case YES_STOCK_PAID:
			loanService.approvalService(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3),
					LoanEvent.REPAYED.toStr());
			loanRepayService.updateRepayLoan(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
			break;
		//核销
		case NO_VERIFICATION:
		case YES_VERIFICATION:
			loanVerifyCancelService.updateLocCancContract(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3),
					busiDate);
			break;
		//单项损失准备金计提
		case NO_SINGLE:
		case YES_SINGLE:
			loanLossProvisionService.updateLossProvision(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
			break;
		//费用登记
		case NO_COST_BANK:
		case YES_COST_BANK:
		case NO_COST_BOND:
		case YES_COST_BOND:
		case NO_COST_STOCK:
		case YES_COST_STOCK:
			loanRepayService.updateBizExpenseRate(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
			break;
		case ATTORN:
			//资产转出
			break;
		default:
			break;
		}
	}
	@Override
	public void billEnterOfCX(String billCd,String operator,Date busiDate){
		List<AccountingInfo> accountingInfos=accountingInfoDao.findListByTxrefNo(billCd);
		if(CollectionUtils.isEmpty(accountingInfos)||accountingInfos.get(0)==null){
			throw new LoanBizException("账务信息不存在");
		}
		AccountingInfo accountingInfo_0=accountingInfos.get(0);
		Long contractId=accountingInfo_0.getContractId();//合同id
		Long payLoanId = accountingInfo_0.getLoanId();//放款id
		Integer eventType= accountingInfo_0.getEventType();//事件类型
		for (AccountingInfo accountingInfo : accountingInfos) {
			accountingInfo.setRecordedVchFlag(RecordedVchFlag.CX_ENTER.toStr());//冲销已入账
			accountingInfoDao.save(accountingInfo);
		}
		//更新账务信息（TallyCertificate）冲销已入账047006
		updateTallyCertificateStatus(billCd, operator, busiDate, BillStatus.CX_ENTER.toStr());
		//更新跑批
		updateBatchRecode(contractId, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
		
		AccountEventType aet=AccountEventType.valOf(eventType);
		switch (aet) {
		//放款
		case LOAN:
		case BOND_LOAN:
		case STOCK_LOAN:
			loanPayService.updatePayLoanInfo(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5), busiDate);
			updateRepayingPlan(contractId, payLoanId, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
			break;
		//还款
		case PAID:
		case YES_PAID:
		case NO_BOND_PAID:
		case YES_BOND_PAID:
		case NO_STOCK_PAID:
		case YES_STOCK_PAID:
			loanService.approvalService(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5),
					LoanEvent.REPAYED.toStr());
			break;
		//核销
		case NO_VERIFICATION:
		case YES_VERIFICATION:
			loanVerifyCancelService.updateLocCancContract(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5),
					busiDate);
			break;
		//单项损失准备金计提
		case NO_SINGLE:
		case YES_SINGLE:
			loanLossProvisionService.updateLossProvision(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
			break;
		//费用登记
		case NO_COST_BANK:
		case YES_COST_BANK:
		case NO_COST_BOND:
		case YES_COST_BOND:
		case NO_COST_STOCK:
		case YES_COST_STOCK:
			loanRepayService.updateBizExpenseRate(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
			break;
		case ATTORN:
			//资产转出
			break;
		default:
			break;
		}
	}
	
	@Override
	public void billBackOfCX(String billCd,String operator,Date busiDate,String backCause){
		List<AccountingInfo> accountingInfos=accountingInfoDao.findListByTxrefNo(billCd);
		if(CollectionUtils.isEmpty(accountingInfos)||accountingInfos.get(0)==null){
			throw new LoanBizException("账务信息不存在");
		}
		AccountingInfo accountingInfo_0=accountingInfos.get(0);
		String orgId=accountingInfo_0.getBchId();//机构id
		//财务接口是否开启
		if(StringUtils.isBlank(orgId)||!isInterfaceOpenOfAccounting(orgId)){
			throw new LoanBizException("未联动财务接口");
		}
		Long contractId=accountingInfo_0.getContractId();//合同id
		Long payLoanId = accountingInfo_0.getLoanId();//放款id
		Integer eventType= accountingInfo_0.getEventType();//事件类型
		for (AccountingInfo accountingInfo : accountingInfos) {
			eventType = accountingInfo.getEventType();
			accountingInfo.setRecordedVchFlag(RecordedVchFlag.ENTER.toStr());// 已入账
			accountingInfoDao.save(accountingInfo);
		}
		//更新账务信息（TallyCertificate）已退单047004
		updateTallyCertificateStatusOfBack(billCd, operator, busiDate, BillStatus.ENTER.toStr(), backCause, true); //已入账
		//更新跑批
		updateBatchRecode(contractId, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		
		updateRepayingPlan(contractId, payLoanId,loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		
		AccountEventType aet=AccountEventType.valOf(eventType);
		switch (aet) {
		//放款
		case LOAN:
		case BOND_LOAN:
		case STOCK_LOAN:
			loanPayService.updatePayLoanInfo(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3), new Date());
			break;
		//还款
		case PAID:
		case YES_PAID:
		case NO_BOND_PAID:
		case YES_BOND_PAID:
		case NO_STOCK_PAID:
		case YES_STOCK_PAID:
			loanService.approvalService(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3),
					LoanEvent.REPAYED.toStr());
			break;
		//核销
		case NO_VERIFICATION:
		case YES_VERIFICATION:
			loanVerifyCancelService.updateLocCancContract(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3),
					busiDate);
			break;
		//单项损失准备金计提
		case NO_SINGLE:
		case YES_SINGLE:
			loanLossProvisionService.updateLossProvision(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
			break;
		//费用登记
		case NO_COST_BANK:
		case YES_COST_BANK:
		case NO_COST_BOND:
		case YES_COST_BOND:
		case NO_COST_STOCK:
		case YES_COST_STOCK:
			loanRepayService.updateBizExpenseRate(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
			break;
		case ATTORN:
			//资产转出
			break;
		default:
			break;
		}
	}
	@Override
	public void billNoneEnterOfCX(String billCd,String operator){
		List<AccountingInfo> accInfoList=accountingInfoDao.findListByTxrefNo(billCd);
		if(CollectionUtils.isEmpty(accInfoList)||accInfoList.get(0)==null){
			throw new LoanBizException("账务信息不存在");
		}
		AccountingInfo accInfo_0=accInfoList.get(0);
		Long contractId=accInfo_0.getContractId();
		Long payLoanId=accInfo_0.getLoanId();
		for (AccountingInfo accInfo : accInfoList) {
			accInfo.setRecordedVchFlag(RecordedVchFlag.CX_NONE.toStr());
			accountingInfoDao.save(accInfo);
		}
		//更新单据凭证
		updateTallyCertificateStatus(billCd, operator,new Date(), BillStatus.CX_NONE.toStr());
		
		AccountEventType aet=AccountEventType.valOf(accInfo_0.getEventType());
		String status=null;
		switch (aet) {
		//放款
		case LOAN:
		case BOND_LOAN:
		case STOCK_LOAN:
			status=loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S4);
			updateRepayingPlan(contractId, payLoanId, status);
			loanPayService.updatePayLoanInfo(billCd, status, new Date());
			break;
		//还款
		case PAID:
		case YES_PAID:
		case NO_BOND_PAID:
		case YES_BOND_PAID:
		case NO_STOCK_PAID:
		case YES_STOCK_PAID:
		case ATTORN_PAID:
			//TODO 未知？
			/*Contract cont=dynamicQuery.querySingleResult(Contract.class,"from Contract where contractId = ?1", contractId);
			if(cont==null||!Arrays.asList("422","316","330").contains(cont.getContractStatusCd())){
				throw new LoanBizException("查无合同资料,请联系管理员!");
			}*/
			
			status=loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S4);
			updateRepayingPlan(contractId, payLoanId, status);
			loanRepayService.updateRepayLoan(billCd, status);
			break;
		//核销
		case NO_VERIFICATION:
		case YES_VERIFICATION:
			loanVerifyCancelService.updateLocCancContract(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S4),
					new Date());
			break;
		//单项损失准备金计提
		case NO_SINGLE:
		case YES_SINGLE:
			loanLossProvisionService.updateLossProvision(billCd,
					loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S4));
			break;
		//费用登记
		case NO_COST_BANK:
		case YES_COST_BANK:
		case NO_COST_BOND:
		case YES_COST_BOND:
		case NO_COST_STOCK:
		case YES_COST_STOCK:
		case NO_TEMP_BANK:
		case NO_TEMP_BOND:
		case NO_TEMP_STOCK:
			loanRepayService.updateBizExpenseRate(billCd, loanCommonServeice
					.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S4));
			break;
		case ATTORN:
			//资产转出
			break;
		default:
			break;
		}
	}
	@Override
	public AccountingInfo findFirstAccountingInfo(String transNo) {
		List<AccountingInfo> infos=accountingInfoDao.findListByTxrefNo(transNo);
		if(CollectionUtils.isEmpty(infos)){
			throw new LoanBizException("编号["+transNo+"]的账务信息不存在");
		}
		return infos.get(0);
	}
	//TODO private
	/**
	 * 更新单据凭证
	 * @param billCd	业务编号
	 * @param operator	操作人
	 * @param busiDate	业务时间
	 * @param billStatus 状态
	 */
	private void updateTallyCertificateStatus(String billCd,String operator,Date busiDate,String billStatus){
		StringBuffer jpql=new StringBuffer();
		jpql.append("update TallyCertificate tc set tc.billSts=?2");
		BillStatus bs=BillStatus.valOf(billStatus);
		List<Object> params=Lists.newArrayList();
		params.add(billCd);
		params.add(billStatus);
		Date curDate=new Date();
		switch (bs) {
		case CX_NONE:
			//冲销未入账
			jpql.append(",tc.sndPrnX=?3,tc.sndDtX=?4,tc.updateTime=?4");
			params.add(operator);
			params.add(curDate);
			break;
		case ENTER:
			//正常入账
			jpql.append(",tc.accPrn=?3,tc.accDt=?4,tc.busiDt=?5,tc.updateTime=?4");
			params.add(operator);
			params.add(curDate);
			params.add(busiDate);
			break;
		case CX_ENTER:
			//冲销入账
			jpql.append(",tc.accPrnX=?3,tc.accDtX=?4,tc.busiDt=?5,tc.updateTime=?4");
			params.add(operator);
			params.add(curDate);
			params.add(busiDate);
			break;
		default:
			return;
		}
		jpql.append(" where tc.billCd = ?1");
		dynamicQuery.executeUpdate(jpql.toString(), params.toArray());
	}
	/**
	 * 更新单据凭证(退单)
	 * @param billCd	业务编号
	 * @param operator	操作人
	 * @param busiDate	业务时间
	 * @param billStatus 状态
	 * @param backCause		退单原因
	 * @param cxFlag		true：冲销，false：不是冲销
	 */
	private void updateTallyCertificateStatusOfBack(String billCd,String operator,Date busiDate,String billStatus,String backCause,boolean cxFlag){
		List<TallyCertificate> tallylist =dynamicQuery.query(TallyCertificate.class, "from TallyCertificate where billCd = ?1 ", billCd);
		if(CollectionUtils.isNotEmpty(tallylist)){
			if(cxFlag){//冲销退单
				for (TallyCertificate tallyCertificate : tallylist) {
					tallyCertificate.setRejDtX(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
							"yyyy-MM-dd HH:mm:ss"));// 退单时间=本系统当前时间
					tallyCertificate.setRejPrnX(operator);// 退单人
					tallyCertificate.setBillSts(billStatus);
					tallyCertificate.setUpdateTime(new Date());
					tallyCertificateDao.save(tallyCertificate);
				}
			}else{//退单
				for (TallyCertificate tallyCertificate : tallylist) {
					if(LoanConstants.IFFEED_NO.equals(tallyCertificate.getCanRef())){//不允许财务退单
						throw new LoanBizException("此单据:" + billCd + " ,不允许财务系统退单,该操作无效");
					}
					tallyCertificate.setRejDt(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
							"yyyy-MM-dd HH:mm:ss"));// 退单时间=本系统当前时间
					tallyCertificate.setRejPrn(operator);// 退单人
					tallyCertificate.setBillSts(billStatus);
					tallyCertificate.setRejCause(backCause);
					tallyCertificate.setUpdateTime(new Date());
					tallyCertificateDao.save(tallyCertificate);
				}
			}
			
		}
	}
	/**
	 * 生成凭证单据
	 */
	@Override
	public void buildTallyCertificate(List<AccountingInfo> accInfos,PayLoanInfo payLoan ){
		if(CollectionUtils.isEmpty(accInfos)){
			return ;
		}
		//
		AccountingInfo accInfo_0=accInfos.get(0);
		if(accInfo_0==null){
			throw new LoanBizException("账务信息不存在");
		}
		TallyCertificate tc=saveTallyCertificate(accInfo_0,payLoan );
		saveTallyCertificateDetailList(accInfos, tc);
	}
	/**
	 * 根据账务信息保存单据凭证
	 * @param accInfo
	 * @return
	 */
	private TallyCertificate saveTallyCertificate(AccountingInfo accInfo,PayLoanInfo payLoan ){
		TallyCertificate tc=new TallyCertificate();
		tc.setBillCd(accInfo.getAiPk().getTxrefNo());
		String customer_num=null;
		if(accInfo.getProjectId().compareTo(0L) == 0){
			tc.setOrgCd(getOrgDepartmentCd(new Long(accInfo.getBchId())));
		}else{
//			Object[] objs=dynamicQuery.nativeQuerySingleResult(Object[].class,"select loan.customer_num,loan.contract_num,pa.customer_manager_name,ht.apply_org_id from pay_loan_info loan,contract ht ,project_application pa where pa.project_id=ht.project_id and loan.contract_id=ht.contract_id and loan.pay_loan_id = ? ", accInfo.getLoanId());
//			customer_num=String.valueOf(objs[0]);
//			tc.setOrgCd(getOrgDepartmentCd(Long.parseLong(String.valueOf(objs[3]))));//机构编号 
//			tc.setCustCd(String.valueOf(objs[0]));// 客户编号
//			tc.setContCd(String.valueOf(objs[1]));// 合同编号
//			tc.setPmPrn(String.valueOf(objs[2]));//项目经理
			
			customer_num = payLoan.getCustomerNum();
			tc.setCustCd(customer_num);
			tc.setContCd(payLoan.getContractNum());
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT  ");
			sql.append("  pa.customer_manager_name, ");
			sql.append("  ht.apply_org_id ");
			sql.append("FROM ");
			sql.append("  contract ht , ");
			sql.append("  project_application pa ");
			sql.append("WHERE pa.project_id  =ht.project_id ");
			sql.append("AND ht.contract_id =?1");
			Object[] resultSet = this.dynamicQuery.nativeQuerySingleResult(Object[].class,sql.toString(),payLoan.getContractId());
			tc.setPmPrn(resultSet[0]==null?"":resultSet[0].toString().trim());
			String orgId=resultSet[0]==null?"0":resultSet[1].toString().trim();//机构id
			tc.setOrgCd(this.getOrgDepartmentCd(Long.parseLong(orgId)));
		}
		String personName=dynamicQuery.nativeQuerySingleResult(String.class, "select per.name from ec_org_person per where per.id= ?", accInfo.getLastModUser());
		if (isInterfaceOpenOfAccounting(accInfo.getBchId())) {
			tc.setBillSts(BillStatus.SEND_NONE.toStr());
		}else{
			tc.setBillSts(BillStatus.ENTER.toStr());// 单据状态:已入帐
			tc.setAccDt(DateTools.stringToDate(DateTools
					.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));// 入账时间
			tc.setAccPrn(personName);// 入账人
		}
		tc.setBusiDt(accInfo.getVchDate());// 业务时间
		tc.setSysCd(AccountingConstants.SYSCD_MICROCREDIT_CODE);// 系统来源编号
		tc.setSysNm(AccountingConstants.SYSCD_MICROCREDIT_NAME);// 系统来源
		final AccountEventType aet=AccountEventType.valOf(accInfo.getEventType());
		switch (aet) {
		case LOAN:
		case BOND_LOAN:
		case STOCK_LOAN:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_LOAN_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_LOAN_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_YES);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case OVERDUE:
		case YES_OVERDUE:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_OVERDUE_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_OVERDUE_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_NO);
			break;
		case PAID:
		case YES_PAID:
		case NO_BOND_PAID:
		case NO_STOCK_PAID:
		case YES_BOND_PAID:
		case YES_STOCK_PAID:
		case ATTORN_PAID:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_PAID_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_PAID_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case SETINT:
		case YES_SETINT:
		case ATTORN_SETINT:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_SETINT_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_SETINT_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_NO);// 财务是否可退单
			break;
		case NO_SINGLE:
		case YES_SINGLE:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_SINGLE_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_SINGLE_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case SPECIAL:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_SPECIAL_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_SPECIAL_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_NO);// 财务是否可退单
			break;
		case NO_VERIFICATION:
		case YES_VERIFICATION:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_VERIFICATION_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_VERIFICATION_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case NO_COST_BANK:
		case YES_COST_BANK:
		case NO_COST_BOND:
		case YES_COST_BOND:
		case NO_COST_STOCK:
		case YES_COST_STOCK:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_COST_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_COST_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case ATTORN:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_ATTORN_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_ATTORN_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case ATTORN_DEAL:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_ATTORN_DEAL_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_ATTORN_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case NO_TEMP_BANK:
		case NO_TEMP_BOND:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_OVERDUE_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_OVERDUE_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		case NO_TEMP_STOCK:
			tc.setBusiTypCd(AccountingConstants.BUSITYPCD_VERIFICATION_CODE);// 业务类型编号
			tc.setBusiTypNm(AccountingConstants.BUSITYPCD_VERIFICATION_NAME);// 业务类型
			tc.setIfFeed(LoanConstants.IFFEED_NO);// 是否财务确定业务日期
			tc.setCanRef(LoanConstants.IFFEED_YES);// 财务是否可退单
			break;
		default:
			break;
		}
		//单据凭证描述
		String customerName = StringUtils.isNoneBlank(customer_num)?loanCommonServeice.getCustomerName(customer_num):loanCommonServeice.getOrgDeptmentName(new Long(accInfo.getBchId()));
		tc.setBillDesc(customerName+tc.getBusiTypNm());
		//单据凭证链接
		String billUrl=loanCommonServeice.findBillUrl(getFlag(aet));
		if(StringUtils.isNotBlank(billUrl)){
			billUrl+=accInfo.getAiPk().getTxrefNo();
		}
		tc.setBillUrl(loanCommonServeice.findBillUrl(getFlag(aet)));
		tc.setSndPrn(personName);// 发送人
		tc.setSndDt(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
				"yyyy-MM-dd HH:mm:ss"));// 发送日期
		final Date curSysTime=new Date();
		tc.setCreateDate(curSysTime);// 创建时间
		tc.setUpdateTime(curSysTime);// 更新时间
		tallyCertificateDao.save(tc);
		return tc;
	}
	/**
	 * 根据状态返回是还款还是放款
	 * @param aet
	 * @return	0：未知,1：放款,2:还款
	 */
	private int getFlag(AccountEventType aet){
		int i=0;
		switch (aet) {
		case LOAN:
		case BOND_LOAN:
		case STOCK_LOAN:
			i=1;
			break;
		case PAID:
		case YES_PAID:
		case NO_BOND_PAID:
		case YES_BOND_PAID:
		case NO_STOCK_PAID:
		case YES_STOCK_PAID:
			i=2;
			break;

		default:
			break;
		}
		return i;
	}
	/**
	 * 获取部门编号
	 * @param departmentId
	 * @return
	 */
	private String getOrgDepartmentCd(Long departmentId){
		if (departmentId == null)
			return StringUtils.EMPTY;
		String sql = " select dep.org_attr from ec_org_department dep where dep.id=? ";
		String org_attr = dynamicQuery.nativeQuerySingleResult(String.class, "select dep.org_attr from ec_org_department dep where dep.id=?1", departmentId);
		if (Arrays.asList("0","1").contains(org_attr)) {// 属性为机构
			sql = " select deps.departmentnumber from ec_org_departmentdetails deps where deps.id=?1 ";
		} else {// 属性为部门
			sql = " select deps.departmentnumber from ec_org_departmentdetails deps  where deps.id = (select dep.parentdepartmentid from ec_org_department dep where dep.id=?1 ) ";
			return dynamicQuery.nativeQuerySingleResult(String.class,sql, departmentId);
		}
		return dynamicQuery.nativeQuerySingleResult(String.class,sql, departmentId);
	}
	/**
	 * 根据partyId查询partyTypeCd
	 * @param partyId
	 * @return
	 */
	private String findPartyTypeCd(Long partyId){
		return dynamicQuery.nativeQuerySingleResult(String.class,"select p.party_type_cd from party p where p.party_id=?1", partyId);
	}
	/**
	 * 保存单据凭证详细
	 * @param accInfos
	 * @param tc
	 */
	private void saveTallyCertificateDetailList(List<AccountingInfo> accInfos,TallyCertificate tc){
		TallyCertificateDetail tcd=null;
		for (AccountingInfo accInfo : accInfos) {
			List<TallyCertificateDetail> list =dynamicQuery.query(TallyCertificateDetail.class,"from TallyCertificateDetail tcd where tcd.billCd = ?1 and tcd.actCd is null and tcd.actNm is null", tc.getBillCd());
			if(CollectionUtils.isNotEmpty(list)){
				tcd=list.get(0);
			}else{
				tcd=new TallyCertificateDetail();
			}
			tcd.setBillCd(tc.getBillCd());// 单据编号
			tcd.setRecNo(String.valueOf(accInfo.getAiPk().getSeqNo())); // 分录序号
			tcd.setRecExp(accInfo.getSummary());// 分录摘要
			tcd.setRuleTyp(LoanConstants.RULETYP_ACCOUNT);// 规则类型
			tcd.setActCd(accInfo.getAccNo());// 科目编号
			tcd.setActNm(accInfo.getAccDesc());// 科目名称
			if ("156".equals(accInfo.getCcyId())) {// 人民币
				tcd.setCuryCd("001");
			} else if ("840".equals(accInfo.getCcyId())) {// 美元
				tcd.setCuryCd("014");
			} else {
				tcd.setCuryCd("");// 币种编码
			}
			if (!LoanConstants.RULETYP_AMT.equals(tcd.getRuleTyp())) {
				tcd.setBlcDir(accInfo.getDcmark().equals("D") ? LoanConstants.DCMARK_D
						: LoanConstants.DCMARK_C);// 借贷方向
			}
			tcd.setRecAmt(accInfo.getAccAmt());// 金额
			tcd.setUpdateTime(DateTools.stringToDate(DateTools
					.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			tcd.setCreateDate(DateTools.stringToDate(DateTools
					.getCurrentDate("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));// 创建时间
			//TODO ?
			initTallyCertificateDetail(tcd, accInfo);
			tallyCertificateDetailDao.save(tcd);
			//更新入账标示
			updateAccInfoTransFlag(accInfo);
		}
	}
	private void initTallyCertificateDetail(TallyCertificateDetail tcd,AccountingInfo accInfo){
		String asst_list=dynamicQuery.nativeQuerySingleResult(String.class, "select a.asst_list from accounting_subject a where a.accno=?1", accInfo.getAccNo());
		if(StringUtils.isBlank(asst_list)){
			throw new LoanBizException("asslist 为空");
		}
		String[] asstArray=asst_list.split(",");
		String astTypCd =null;
		String astTypNm =null;
		String astCd = StringUtils.EMPTY;// 编码
		String astNm = StringUtils.EMPTY;// 名称
		Object[] temStrs=null;
		//将asst(001,002)转换为1,2
		int asst_int=0;
		int k = 0; // ast字段顺序 ，现金流字段单独赋值
		StringBuffer tempBuffer=new StringBuffer();
		for (String asst : asstArray) {
			Object[] codeName=dynamicQuery.nativeQuerySingleResult(Object[].class, "select aal.asst_code,aal.asst_name from ACCOUNTING_ASST_LIST aal where aal.asst_num=?1 and aal.valid=1", asst);
			if(codeName==null){
				continue;
			}
			astTypCd =String.valueOf(codeName[0]);
			astTypNm =String.valueOf(codeName[1]);
			asst_int=Integer.parseInt(asst);
			switch (asst_int) {
			//对公客户,个人客户
			case 1:
			case 2:
				temStrs=dynamicQuery.nativeQuerySingleResult(Object[].class, "select p.customer_num,p.party_name from party p where p.party_id=?1", accInfo.getPartyId());
				if(temStrs==null){
					continue;
				}
				astCd=String.valueOf(temStrs[0]);
				astNm=String.valueOf(temStrs[1]);
				break;
			//对公合同
			case 3:
			case 4:
				temStrs=dynamicQuery.nativeQuerySingleResult(Object[].class, "select ht.contract_num ,ht.customer_name from contract ht where ht.contract_id=?1", accInfo.getContractId());
				if(temStrs==null){
					continue;
				}
				astCd=String.valueOf(temStrs[0]);
				astNm=String.valueOf(temStrs[1]);
				break;
			case 5:// 担保性质
				List<String> list=dynamicQuery.nativeQuery(String.class, "select pa.guarantee_mode FROM project_application pa where pa.project_id in (select  ht.project_id from contract ht where ht.contract_id = ?1)", accInfo.getContractId());
				if(CollectionUtils.isEmpty(list)){
					continue;
				}
				String guarantee_mode_str=list.get(0);
				if(StringUtils.isBlank(guarantee_mode_str)){
					continue;
				}
				temStrs=guarantee_mode_str.split(",");
				if(temStrs==null||temStrs.length==0){
					continue;
				}
				if (temStrs.length == 1) {
					if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S1").equals(temStrs[0])) {// 抵押
						astCd = "01";
						astNm = "抵押";
					} else if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S2").equals(temStrs[0])) {// 质押
						astCd = "02";
						astNm = "质押";
					} else if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S3").equals(temStrs[0])) {// 保证
						astCd = "03";
						astNm = "保证";
					} else if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S4").equals(temStrs[0])) {// 信用
						astCd = "04";
						astNm = "信用";
					}
				} else {
					String guarantee_mode=null;
					for (Object obj : temStrs) {
						guarantee_mode=String.valueOf(obj);
						if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S1").equals(guarantee_mode)) {// 抵押
							if (astCd != null && !"".equals(astCd)) {
								astCd = astCd + "+01";
							} else {
								astCd = "01";
							}
							if (astNm != null && !"".equals(astNm)) {
								astNm = astNm + "+抵押";
							} else {
								astNm = "抵押";
							}
						} else if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S2").equals(guarantee_mode)) {// 质押
							if (astCd != null && !"".equals(astCd)) {
								astCd = astCd + "+02";
							} else {
								astCd = "02";
							}
							if (astNm != null && !"".equals(astNm)) {
								astNm = astNm + "+质押";
							} else {
								astNm = "质押";
							}
						} else if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S3").equals(guarantee_mode)) {// 保证
							if (astCd != null && !"".equals(astCd)) {
								astCd = astCd + "+03";
							} else {
								astCd = "03";
							}
							if (astNm != null && !"".equals(astNm)) {
								astNm = astNm + "+保证";
							} else {
								astNm = "保证";
							}
						} else if (loanCommonServeice.getCodeVal("CdsGuarantMode", "S4").equals(guarantee_mode)) {// 信用
							if (astCd != null && !"".equals(astCd)) {
								astCd = astCd + "+04";
							} else {
								astCd = "04";
							}
							if (astNm != null && !"".equals(astNm)) {
								astNm = astNm + "+信用";
							} else {
								astNm = "信用";
							}
						}
					}
				}
				
				break;
			case 6:// 银行客户
				// TODO 需求未定
				// 银行客户、银行账户对应的ast_cdX上应填”?”
				astCd = "?";
				break;
			case 7:// 银行账户
				// TODO 需求未定
				// 银行客户、银行账户对应的ast_cdX上应填”?”
				astCd = "?";
				break;
			case 8:// 现金流量
				List<AccountingConfig> accConfList=dynamicQuery.query(AccountingConfig.class,"from AccountingConfig ac where ac.acPk.eventType=?1 and ac.acPk.itemId = ?2 and ac.acPk.suitNo=?3", accInfo.getEventType(),accInfo.getItemId(),accInfo.getSuitNo());
				if(CollectionUtils.isEmpty(accConfList)){
					continue;
				}
				for (AccountingConfig accConf : accConfList) {
					if (!StringUtils.equals(accConf.getAccNo(), accInfo.getAccNo())) {
						String strAccno =accConf.getAccNo();//科目编号
						if (strAccno != null
								&& (strAccno.startsWith("1132") || strAccno.startsWith("6011")
										|| strAccno.startsWith("22410205") || strAccno.startsWith("22030101")
										|| strAccno.startsWith("22030102"))) {// 1132,220301-贷款预收账款
							// 应收利息6011利息收入
							astTypCd = AccountingConstants.ACCOUNTING_PAID_INTEREST_CODE;
							astTypNm = AccountingConstants.ACCOUNTING_PAID_INTEREST_NAME;
						} else if (strAccno != null && strAccno.startsWith("1303")
								|| strAccno.startsWith("22030201")
								|| strAccno.startsWith("22030202")) {// 1303银行贷款
							astTypCd = AccountingConstants.ACCOUNTIND_PAID_LOAN_CODE;
							astTypNm = AccountingConstants.ACCOUNTIND_PAID_LOAN_NAME;
							// 6021手续费 6301违约金
						} else if (strAccno != null
								&& (strAccno.startsWith("6021") || strAccno.startsWith("6301"))) {// 手续费及佣金
							astTypCd = AccountingConstants.ACCOUNTING_PAID_FEE_CODE;
							astTypNm = AccountingConstants.ACCOUNTING_PAID_FEE_NAME;
						} else if (strAccno != null
								&& (strAccno.startsWith("231401") || strAccno.startsWith("605101") || strAccno
										.startsWith("640201"))) { // 往来客户
							astTypCd = AccountingConstants.ACCOUNTIND_PAID_OTHER_CODE;
							astTypNm = AccountingConstants.ACCOUNTIND_PAID_OTHER_NAME;
						}
					}
				}
				if (LoanConstants.DCMARK_D.equals(tcd.getBlcDir())) {// 为“1”时
					// 现金流量出在贷方
					// 需要new
					TallyCertificateDetail new_tcd = new TallyCertificateDetail();
					new_tcd.setBillCd(tcd.getBillCd());
					new_tcd.setCashCdM(astTypCd);
					new_tcd.setCashNmM(astTypNm);
					new_tcd.setCashRec(tcd.getRecNo());
					tallyCertificateDetailDao.save(new_tcd);
				} else if (LoanConstants.DCMARK_C.equals(tcd.getBlcDir())) {// 为“2”时
					// 现金流量出在借方
					// 去数据库查
					TallyCertificateDetail new_tcd = null;
					List<TallyCertificateDetail> tcdList = dynamicQuery.query(TallyCertificateDetail.class,"from TallyCertificateDetail tcd where tcd.billCd =?1  order by tcd.recNo desc", tcd.getBillCd());
					new_tcd = (TallyCertificateDetail) tcdList.get(0);
					new_tcd.setCashCdM(astTypCd);
					new_tcd.setCashNmM(astTypNm);
					new_tcd.setCashRec(tcd.getRecNo());
					tallyCertificateDetailDao.save(new_tcd);
				}
				break;
			case 9:// 往来客户
				tempBuffer.setLength(0);
				tempBuffer.append("select atp.customer_num,atp.customer_name from ass_tran_project_application pa");
				tempBuffer.append(",contract c , ass_tran_three_org_customer atp");
				tempBuffer.append(" where pa.project_id = c.fpool_project_id and pa.acquiring_party = to_char(atp.id)");
				tempBuffer.append(" and c.contract_id = ?1");
				temStrs=dynamicQuery.nativeQuerySingleResult(Object[].class, tempBuffer.toString(), accInfo.getContractId());
				if(temStrs==null){
					continue;
				}
				astCd=String.valueOf(temStrs[0]);
				astNm=String.valueOf(temStrs[1]);
				break;
			default:
				break;
			}
			if(!"008".equals(asst)){
				switch (k) {
				case 0:
					tcd.setAstTypCd1(astTypCd);
					tcd.setAstTypNm1(astTypNm);
					tcd.setAstCd1(astCd);
					tcd.setAstNm1(astNm);
					break;
				case 1:
					tcd.setAstTypCd2(astTypCd);
					tcd.setAstTypNm2(astTypNm);
					tcd.setAstCd2(astCd);
					tcd.setAstNm2(astNm);
					break;
				case 2:
					tcd.setAstTypCd3(astTypCd);
					tcd.setAstTypNm3(astTypNm);
					tcd.setAstCd3(astCd);
					tcd.setAstNm3(astNm);
					break;
				case 3:
					tcd.setAstTypCd4(astTypCd);
					tcd.setAstTypNm4(astTypNm);
					tcd.setAstCd4(astCd);
					tcd.setAstNm4(astNm);
					break;
				case 4:
					tcd.setAstTypCd5(astTypCd);
					tcd.setAstTypNm5(astTypNm);
					tcd.setAstCd5(astCd);
					tcd.setAstNm5(astNm);
					break;
				case 5:
					tcd.setAstTypCd6(astTypCd);
					tcd.setAstTypNm6(astTypNm);
					tcd.setAstCd6(astCd);
					tcd.setAstNm6(astNm);
					break;
				default:
					break;
				}
				k++;
			}
		}
	}
	/**
	 * 更新accounting传送标示和入账标示
	 * @param accInfo
	 */
	private void updateAccInfoTransFlag(AccountingInfo accInfo){
		if (isInterfaceOpenOfAccounting(accInfo.getBchId())) {
			accInfo.setTransmitFlag(TransmitFlag.AUTO.toStr());
			accInfo.setRecordedVchFlag(RecordedVchFlag.NONE.toStr());
		}else{
			accInfo.setTransmitFlag(TransmitFlag.HAND.toStr());
			accInfo.setRecordedVchFlag(RecordedVchFlag.ENTER.toStr());
		}
	}
	/**
	 * 根据合同id更新跑批（Batchrecode）状态
	 * @param contractId 合同id
	 */
	private void updateBatchRecode(Long contractId,String payLoanStatus) {
		if(contractId==null){
			return ;
		}
		dynamicQuery.executeUpdate("update BatchRecode br set br.payLoanStatus=?1 where br.brPk.contractId=?2", payLoanStatus,contractId);
	}
	/**
	 * 根据合同ID与放款ID更新还款计划状态
	 * @param contractId
	 * @param payLoanId
	 * @param status
	 */
	private void updateRepayingPlan(Long contractId, Long payLoanId, String status) {
		List<RepayingPlan> rpList=dynamicQuery.query(RepayingPlan.class,"from RepayingPlan where contractId = ?1 and payLoanId = ?2", contractId,payLoanId);
		PayLoanInfo payLoanInfo=payLoanInfoDao.findOne(payLoanId);
		RepayingPlan rp=rpList.get(0);
		if (payLoanInfo.getPayLoanIndex() > 0
				&& loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5).equals(status)) {
			loanService.approvalService(rp.getTransactionNo(), status, LoanEvent.AGAIN_PAY_LOAN.toStr());
		} else {
			rp.setTransactionStatus(status);// 单据状态
			rp.setSystemDate(new Date());
			repayingPlanDao.save(rp);
		}
		
	}
	/**
	 * 判断机构与接口联动是否开启
	 * @param orgId
	 * @return
	 */
	public boolean isInterfaceOpenOfAccounting(String orgId){
		return loanCommonServeice.isOpenOfOrgInterface(orgId, "openAccounting");
	}
	/**
	 * 判断借贷平衡
	 * @param afList
	 * @return	true：平衡 false：不平衡
	 */
	private boolean checkDCBalance(List<AccountingInfo> afList){
		if(CollectionUtils.isEmpty(afList)){
			
		}
		// 存放每个套帐号对应借方的总金额，供判断借贷平衡
		Map<String, BigDecimal> ttlAccAmtD = Maps.newHashMap();
		// 存放每个套帐号对应贷方的总金额，供判断借贷平衡
		Map<String, BigDecimal> ttlAccAmtC =  Maps.newHashMap();
		// 会计科目表对象
		AccountingSubject accSubject = null;
		StringBuffer keyBuffer=new StringBuffer();
		String key=null;
		BigDecimal val=null;
		for (AccountingInfo accInfo : afList) {
			accSubject=accountingSubjectDao.findListByAccNo(accInfo.getAccNo());
			if (accSubject!=null&&"1".equals(accSubject.getBwaccFlag())) {
				printLogOfDebug("表外科目 ，不判断借贷平衡");
				continue;
			}
			keyBuffer.append("suitno").append(accInfo.getSuitNo());
			keyBuffer.append("ccyid").append(accInfo.getCcyId());
			key=keyBuffer.toString();
			keyBuffer.setLength(0);//清空
			if ("D".equals(StringUtils.trim(accInfo.getDcmark()))) {//借方
				val=ttlAccAmtD.get(key);
				ttlAccAmtD.put(key, val==null?accInfo.getAccAmt():val.add(accInfo.getAccAmt()));
			}else{//贷方
				val=ttlAccAmtC.get(key);
				ttlAccAmtC.put(key, val==null?accInfo.getAccAmt():val.add(accInfo.getAccAmt()));
			}
		}
		//比较两个Hashmap中对应的数据，得出是否借贷平衡
		Iterator<String> i=null;
		if (ttlAccAmtC.size() >= ttlAccAmtD.size()) {
			i = ttlAccAmtC.keySet().iterator();
		} else {
			i = ttlAccAmtD.keySet().iterator();
		}
		boolean isblance=false;
		BigDecimal amtD=null;// 获得借贷别为C该套号对应的金额对象
		BigDecimal amtC=null;// 获得借贷别为D该套号对应的金额对象
		while (i.hasNext()) {
			key = (String) i.next();// 按顺序获得借贷别为C中的一个套号
			amtC=ttlAccAmtC.get(key);// 获得借贷别为C该套号对应的金额
			amtD=ttlAccAmtD.get(key);//获得借贷别为D该套号对应的金额
			amtC=amtC==null?new BigDecimal(0):amtC;
			amtD=amtD==null?new BigDecimal(0):amtD;
			if(amtC.compareTo(amtD) == 0){
				isblance=true;
			}else{
				isblance=false;
				break;
			}
		}
		return isblance;
	}
	/**
	 * 构建账务信息
	 * @param configList
	 * @return
	 */
	private void buildAccountingData(List<AccountingConfig> configList,AccountingBo accountingVO,List<AccountingInfo> infoList,List<String[]> formulaResult){
		for (AccountingConfig ac : configList) {
			buildVchData(ac, accountingVO, infoList, formulaResult);
		}
	}
	/**
	 * 生成一笔交易的帐务资料
	 * @return
	 */
	private void buildVchData(AccountingConfig ac,AccountingBo accBo,List<AccountingInfo> accInfoList,List<String[]> formulaResult){
		// 构造帐务表，用来存放一笔帐务资料
		AccountingInfo accountingInfo = new AccountingInfo();
		AccountingInfoPk aiPk = new AccountingInfoPk();
		// 获取会计科目表,校验会计科目表该科目是否存在
		AccountingSubject accountingSubject =accountingSubjectDao.findListByAccNo(ac.getAccNo());
		if (accountingSubject == null)
			throw new LoanBizException("该帐务配置表中对应会计科目代号为" + ac.getAccNo() + "的科目不存在,帐务处理失败");
		// 获取出帐金额
		AccAmtResult accAmtResult=getVchAccAmt(accBo.getBussObject(), ac, accBo.getCcyId());
		accountingInfo.setAccAmt(accAmtResult.getAccAmt());
		// 如果出帐金额大于零，则处理这笔账务
		if (accountingInfo.getAccAmt().compareTo(BigDecimal.ZERO) == 0) { // 出帐金额等于0
			printLogOfDebug("出帐金额等于0,不处理该笔帐务：" + ac.getDcmark() + ac.getAccNo());
			return ;
		} else {
			printLogOfDebug("出帐金额等于0" + accountingInfo.getAccAmt() + ",帐务：" + ac.getDcmark()
						+ ac.getAccNo());
			aiPk.setTxrefNo(accBo.getTxrefNo());// 交易编号
			aiPk.setSeqNo(this.getMaxSeqno(accBo.getTxrefNo()));// 最大序号
			accountingInfo.setAiPk(aiPk);
			accountingInfo.setPartyId(Long.parseLong(String.valueOf(accBo.getPartyId())));// 参与人ID
			accountingInfo.setProjectId(Long.parseLong(String.valueOf(accBo.getProjectId())));// 项目业务ID
			accountingInfo.setContractId(Long.parseLong(String.valueOf(accBo.getContractId())));// 合同ID
			accountingInfo.setLoanId(Long.parseLong(String.valueOf(accBo.getLoanId())));// 放款ID
			accountingInfo.setEventType(accBo.getEventType());// 交易形态
			accountingInfo.setItemId(accBo.getItemId());// 交易项目
			accountingInfo.setLastModDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd"));// 操作时间
			accountingInfo.setLastModUser(accBo.getLastModUser());// 最后操作者
			accountingInfo.setAccNo(ac.getAccNo());// 会计科目号
			accountingInfo.setAccDesc(ac.getDescribe());// 会计科目描述
			accountingInfo.setDcmark(ac.getDcmark());// 借贷别
			accountingInfo.setSuitNo(ac.getAcPk().getSuitNo());// 套号
			if ("BUSS".equalsIgnoreCase(ac.getRelatccy())) {// 币别取值依据业务
				accountingInfo.setCcyId(accBo.getCcyId());// 币别
			} else {
				accountingInfo.setCcyId(AccountingConstants.LOCALCCY);// 依据本国币别
			}
			if ("2".equalsIgnoreCase(ac.getRelatbch())) {// 指定机构
				accountingInfo.setBchId(ac.getBchId());
			} else {
				accountingInfo.setBchId(accBo.getBchId());// 合同机构
			}
			// 摘要
			String summary = this.gainSummary(accBo.getBussObject(), ac.getSummary());
			accountingInfo.setSummary(summary);
			// 默认传送标识：未传送，默认入账标识：未入账
			accountingInfo.setTransmitFlag(TransmitFlag.NONE.toStr());// [0=还未传送(default)/1=系统自动传送/2=人工手动传送]
			accountingInfo.setRecordedVchFlag(RecordedVchFlag.NONE.toStr());// [0=未入帐(default)/1=已入帐]
			// 交易日期：为登记日期，入账日期：为财务返回业务日期
			accountingInfo.setTxDate(accBo.getTxDate());// 交易日期
			accountingInfo.setVchDate(accBo.getVchDate());// 入账日期
		}
		accountingInfoDao.save(accountingInfo);
		accInfoList.add(accountingInfo);
		saveAccountingFieldvalue(accAmtResult.getFieldValues(), accBo.getTxrefNo());
		formulaResult.addAll(accAmtResult.getFormulaResult());
	}
	/**
	 * 保存栏位
	 */
	private void saveAccountingFieldvalue(List<AccountingFieldvalue> list,String txrefNo){
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		for (AccountingFieldvalue af : list) {
			af.setTxrefNo(txrefNo);
			af.setLastModDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd"),
					"yyyy-MM-dd"));
		}
		accountingFieldvalueDao.save(list);
	}
	/**
	 * @param obj
	 * @param ac
	 * @param currccyId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private AccAmtResult getVchAccAmt(Object obj, AccountingConfig ac, String currccyId){
		BigDecimal v_accamt = new BigDecimal(0);
		AccAmtResult accAmtResult=new AccAmtResult();
		// 获得表达式处理对象
		ExpressionCalc Expcalc = new ExpressionCalc();
		if (StringUtils.isBlank(ac.getFieldId())) {
			printLogOfDebug("未设定出帐公式");
			accAmtResult.setAccAmt(v_accamt);
			return accAmtResult;
		}
		Expcalc.setExpression(ac.getFieldId()); // 设置表达式
		// 解析出运算量
		List numeric = Expcalc.getNumeric();
		int num_size = numeric.size();
		// 表示未设定公式
		if (num_size == 0) {
			printLogOfDebug("未设定出帐公式");
			accAmtResult.setAccAmt(v_accamt);
			return accAmtResult;
		}
		String num_name = null;
		String fld_name = null;
		List<String> exp_value = new ArrayList<String>(); // 存放表达式运算量
		List<String> exp_value_Desc = new ArrayList<String>(); // 存放表达式运算量
		Object oamt = null;
		List<AccountingFieldvalue> afList=Lists.newArrayList();
		for (Object n : numeric) {
			// 公式中的运算量
			num_name =String.valueOf(n);
			if (!num_name.startsWith("$")){
				exp_value.add(num_name);
				exp_value_Desc.add(num_name);
				afList.add(new AccountingFieldvalue(num_name,new BigDecimal(num_name)));
			}else{
				// 获得该字段对应信息
				fld_name = dynamicQuery.nativeQuerySingleResult(String.class,"select FLDNAME from ACCOUNTING_FIELD where FieldID =?1",num_name);
				if (fld_name == null || fld_name.length() == 0) {
					throw new LoanBizException("未能找到该栏位所对应的字段");
				}
				try {
					String methodeName = "get" + fld_name.substring(0, 1).toUpperCase();// 获得方法名的第一个字母
					String getMethodeName = methodeName + fld_name.substring(1);// 获得到整个方法名
					Method method = obj.getClass().getMethod(getMethodeName, new Class[] {});// 获得数据库字段对应的金额为oamt
					oamt = method.invoke(obj, new Object[] {});
				} catch (Exception e) {
					printLogOfDebug("获取金额记录出错" + e.getMessage());
					throw new LoanBizException("获取金额记录出错" + e.getMessage());
				}
				if (oamt == null) {
					printLogOfDebug("从栏位值表抓出的纪录为空");
					accAmtResult.setAccAmt(v_accamt);
					return accAmtResult;
				} else {
					exp_value.add(oamt.toString());
					exp_value_Desc.add(oamt.toString());
					afList.add(new AccountingFieldvalue(num_name,new BigDecimal(oamt.toString())));
				}
			}
			Expcalc.setNewNumeric(exp_value);
			double result = Expcalc.calc();
			// 获得该出帐币别的精确位数信息 getDecimalPos(currccyid);
			// 对double类型的数据按照对应的货币别进行数据转换位金额
			v_accamt = new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP);// 最终出帐金额

			// 存储计算公式
			String[] formula = new String[3];
			formula[0] = ac.getAccNo();
			formula[1] = currccyId;// 当前的出帐币别currccyid
			Expcalc.setNewNumeric(exp_value_Desc);// 放入表达式运算量
			formula[2] = Expcalc.getExpression();// 获得表达式
			List<String[]> formulaResult = new ArrayList<String[]>();
			formulaResult.add(formula);// 放入该会计科目对应的计算公式信息
			accAmtResult.setAccAmt(v_accamt);
			accAmtResult.setFieldValues(afList);
			accAmtResult.setFormulaResult(formulaResult);
		}
		return accAmtResult;
	}
	/**
	 * 获取摘要 处理字符串包含 {} 
	 * @param object	
	 * @param summary	如：向{party_name:party:party_id:partyId}客户发放贷款
	 * @return
	 */
	private String gainSummary(Object object, String summary){
		if (object == null ||StringUtils.isBlank(summary)){
			return StringUtils.EMPTY;
		}
		if(StringUtils.contains(summary, "{")&&StringUtils.contains(summary, "}")){
			try {
				final String sub_str= StringUtils.substringBetween(summary, "{", "}");
				String[] strs=sub_str.split(":");
				if(strs.length==1){
					String methodeName = "get" + strs[0].substring(0, 1).toUpperCase();// 获得方法名的第一个字母
					String getMethodeName = methodeName + strs[0].substring(1);// 获得到整个方法名
					Method method = object.getClass().getMethod(getMethodeName, new Class[] {});// 获得数据库字段对应的字段值
					Object tempobj = method.invoke(object, new Object[] {});
					if (!"".equals(tempobj) && tempobj != null) {
						summary = summary.replace("{" + sub_str + "}", tempobj.toString());
					}
				}else if(strs.length>=4){
					String getMethodeName = "get"+StringUtils.capitalize(strs[3]);// 获得到整个方法名
					Method method = object.getClass().getMethod(getMethodeName, new Class[] {});// 获得数据库字段对应的字段值
					Object tempobj = method.invoke(object, new Object[] {});
					String sql=MessageFormat.format("select {0} from {1} where {2} =?1", strs[0],strs[1],strs[2]);
					String newstr = dynamicQuery.nativeQuerySingleResult(String.class,sql,tempobj);
					if (!"".equals(newstr) && newstr != null){
						return StringUtils.replace(summary, "{"+sub_str+"}", newstr);
					}
				}
			} catch (Exception e) {
				printLogOfDebug("获取金额记录出错" + e.getMessage());
			}
		}
		return summary;
	}
	/**
	 * 获取账务信息表最大序号
	 * @param txrefno
	 * @return
	 */
	private synchronized Long getMaxSeqno(String txrefNo){
		return loanCommonServeice.getMaxSeqno(txrefNo);
	}
	/**
	 * 查询货币类型
	 * @param contractId	合同id
	 * @return
	 */
	private String findCurrencyType(Long contractId){
		if (contractId==null)
			throw new LoanBizException("币别不能为空");
		String currency= dynamicQuery.nativeQuerySingleResult(String.class,"select ht.currency from contract ht where ht.contract_id=?1", contractId);
		if (StringUtils.isBlank(currency)){
			throw new LoanBizException("币别不能为空");
		}
		return currency;
	}
	/**
	 * Debug级别的日志打印
	 * @param logInfo
	 */
	private void printLogOfDebug(String logInfo){
		if(logger.isDebugEnabled()){
			logger.debug(logInfo);
		}
	}
	//TODO 内部类
	class AccAmtResult{
		private BigDecimal accAmt;
		private List<AccountingFieldvalue> fieldValues;
		private List<String[]> formulaResult;
		public BigDecimal getAccAmt() {
			return accAmt;
		}

		public void setAccAmt(BigDecimal accAmt) {
			this.accAmt = accAmt;
		}

		public List<AccountingFieldvalue> getFieldValues() {
			return fieldValues;
		}

		public void setFieldValues(List<AccountingFieldvalue> fieldValues) {
			this.fieldValues = fieldValues;
		}

		public List<String[]> getFormulaResult() {
			return formulaResult;
		}

		public void setFormulaResult(List<String[]> formulaResult) {
			this.formulaResult = formulaResult;
		}
		
	}
	
	
	
	@Override
	public Map doVchByCost(DoVchAmtBo amtVO, Long applyOrgId, Long applyUserNum) {
		try {
				if (applyOrgId == null || applyUserNum == null){
					throw new RuntimeException("机构或操作人为空");
			}
			AccountingBo accountingBo = new AccountingBo();
			accountingBo.setBussObject(amtVO);
			accountingBo.setBussType(AccountingConst.ACCOUNTING_PRODUCTS_1110); // 小额信贷
			
			//对象维度类型 (1:按经办机构计提2:按单笔合同计提) 2按单笔合同计提
			String objectDimensionType = OBJECTDIMENSIONTYPE_CONTRACT;
			//单据状态 S2已入账
			String transactionStatus = dataDict.getCodeVal("PayLoanStatus", "S2");
			List<LossProvision> lplist = lossProvisionDao.findListByContractId(amtVO.getContractId(), objectDimensionType, transactionStatus);
			LossProvision lossProvision = null;
			if(!CollectionUtils.isEmpty(lplist)){
				lossProvision = lplist.get(0);
			}
			// 已减值
			//privisionAmt 为当期一般准备金计提金额 or 贷款减值损失
			if (lossProvision != null && lossProvision.getPrivisionAmt().compareTo(BigDecimal.ZERO) > 0) {
				//比较费用来源
				// 银行转账
				if (AccountingConst.ACCOUNTING_SOURCE_BANK.equals(amtVO.getBcCostType())) {
					accountingBo.setEventType(AccountingConst.ACCOUNTING_EVENTTYPE_YES_COST_BANK); // 支用交易形态
				}
				// 现金
				else if (AccountingConst.ACCOUNTING_SOURCE_BOND.equals(amtVO.getBcCostType())) {
					accountingBo.setEventType(AccountingConst.ACCOUNTING_EVENTTYPE_YES_COST_BOND); // 支用交易形态
				}
				// 保证金
				else if (AccountingConst.ACCOUNTING_SOURCE_STOCK.equals(amtVO.getBcCostType())) {
					accountingBo.setEventType(AccountingConst.ACCOUNTING_EVENTTYPE_YES_COST_STOCK); // 支用交易形态
				} else {
					throw new RuntimeException("数据异常,请联系管理员!");
				}
			}
			// 未减值
			else {
				// 银行转账
				if (AccountingConst.ACCOUNTING_SOURCE_BANK.equals(amtVO.getBcCostType())) {
					accountingBo.setEventType(AccountingConst.ACCOUNTING_EVENTTYPE_NO_COST_BANK); // 支用交易形态
				}
				// 现金
				else if (AccountingConst.ACCOUNTING_SOURCE_BOND.equals(amtVO.getBcCostType())) {
					accountingBo.setEventType(AccountingConst.ACCOUNTING_EVENTTYPE_NO_COST_BOND); // 支用交易形态
				}
				// 保证金
				else if (AccountingConst.ACCOUNTING_SOURCE_STOCK.equals(amtVO.getBcCostType())) {
					accountingBo.setEventType(AccountingConst.ACCOUNTING_EVENTTYPE_NO_COST_STOCK); // 支用交易形态
				} else {
					throw new RuntimeException("数据异常,请联系管理员!");
				}
			}
			
			Party party = partyDao.findOne(amtVO.getPartyId());
			if(StringUtils.equals(dataDict.getCodeVal("CustomerType", "S1"), party.getPartyTypeCd())){
				accountingBo.setItemId(1); //企业客户
			}else if(StringUtils.equals(dataDict.getCodeVal("CustomerType", "S2"), party.getPartyTypeCd())){
				accountingBo.setItemId(2); //个人客户
			}
			//TODO
			String txrefno = commonBizNumberBS.getTxRefNo(AccountingConst.ACCOUNTING_PRODUCTS_1110.toString(), applyOrgId
					.toString(), Integer.valueOf(party.getPartyTypeCd()));
			accountingBo.setTxrefNo(txrefno);
			accountingBo.setBchId(applyOrgId.toString());
			accountingBo.setCcyId(this.getcurrencyType(amtVO.getContractId()));
			accountingBo.setPartyId(amtVO.getPartyId());
			accountingBo.setProjectId(amtVO.getProjectId() == null ? 0 : amtVO.getProjectId());
			accountingBo.setContractId(amtVO.getContractId());
			accountingBo.setLoanId(amtVO.getLoanId());
			accountingBo.setTxDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
					"yyyy-MM-dd HH:mm:ss"));
			if (amtVO.getVchdate() == null) {
				accountingBo.setVchDate(DateTools.stringToDate(DateTools.getCurrentDate("yyyy-MM-dd HH:mm:ss"),
						"yyyy-MM-dd HH:mm:ss"));
			} else {
				accountingBo.setVchDate(amtVO.getVchdate()); // 放款实际日期
			}
			accountingBo.setLastModUser(applyUserNum.toString());
			DoVchResultBo vchmap = this.doVch(accountingBo);
			PayLoanInfo payLoanInfo = payLoanInfoDao.findByPayLoanId(amtVO.getLoanId());
			//单据凭证
			this.buildTallyCertificate(vchmap.getAccInfos(),payLoanInfo);
			///////////////////小贷开启////////////////////////
//			if (sendZCVchMap != null && sendZCVchMap.size() > 0) {
//				vchmap.putAll(sendZCVchMap);
//			}
			Map txrefnoMap =  Maps.newHashMap();
			txrefnoMap.put(AccountingConst.ACCOUNTING_TXREFNO, txrefno);
			return txrefnoMap;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	private String getcurrencyType(Long contractId) throws RuntimeException {
		if (contractId == null){
			throw new RuntimeException("币别不能为空");}
		Contract contr = contractDao.findOne(contractId);
		String _currency = contr.getCurrency();
		if(StringUtils.isBlank(_currency)){
			throw new RuntimeException("币别不能为空");
		}
		return _currency;
	}
}

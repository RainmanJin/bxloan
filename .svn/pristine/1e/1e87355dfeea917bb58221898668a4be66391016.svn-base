package com.coamctech.bxloan.service.pettyloan.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.dao.ApprovalHistoryRepayPlanDao;
import com.coamctech.bxloan.dao.ArrearsDetailDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.PayLoanInfoDao;
import com.coamctech.bxloan.dao.RepayPlanDao;
import com.coamctech.bxloan.dao.RepayingDetailDao;
import com.coamctech.bxloan.dao.RepayingPlanDao;
import com.coamctech.bxloan.dao.RepayingPlanDetailDao;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.PayLoanInfo;
import com.coamctech.bxloan.entity.RepayPlan;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.loanprocess.InterestCalCulateService;
import com.coamctech.bxloan.service.model.loanprocess.InterestCalCulateForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestPlanForm;
import com.coamctech.bxloan.service.model.loanprocess.InterestVO;
import com.coamctech.bxloan.service.pettyloan.LoanAccountingService;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanPayService;
import com.coamctech.bxloan.service.pettyloan.LoanService;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchAmtBo;
import com.coamctech.bxloan.service.pettyloan.bo.DoVchResultBo;
import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanEvent;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;
import com.google.common.collect.Lists;
@Transactional
@Service("loanPayService")
public class LoanPayServiceImpl implements LoanPayService{
	private Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	PayLoanInfoDao payLoanInfoDao;
	@Autowired
	ContractDao contractDao;
	@Autowired
	RepayPlanDao repayPlanDao;
	@Autowired
	RepayingPlanDao repayingPlanDao;
	@Autowired
	LoanAccountingService loanAccountingService;
	@Autowired
	LoanCommonServeice loanCommonServeice;
	@Autowired
	ICommonBizNumberBS commonBizNumberBS;
	@Autowired
	InterestCalCulateService interestCalCulateService;
	@Autowired
	RepayingPlanDetailDao repayingPlanDetailDao;
	@Autowired
	private RepayingDetailDao repayingDetailDao;
	@Autowired
	private ArrearsDetailDao arrearsDetailDao;
	@Autowired
	private ApprovalHistoryRepayPlanDao approvalHistoryRepayPlanDao;
	@Autowired
	private LoanService loanService;
	@Autowired
	DynamicQuery dynamicQuery;
	@Override
	public PayLoanInfo updatePayLoanInfo(String transactionNo,
			String payLoanStatus, Date busiDate) {
		//查询合同
		PayLoanInfo plInfo=null;
		LoanStatus loanStatus=loanCommonServeice.getLoanStatus(LoanConstants.PAY_LOAN_STATUS,payLoanStatus);
		switch (loanStatus) {
		case PAY_LOAN_STATUS_S2:
			accountEnter(transactionNo, busiDate);
			break;
		case PAY_LOAN_STATUS_S3:
			accountBack(transactionNo);
			break;
		case PAY_LOAN_STATUS_S4:
			plInfo=findByTransNo(transactionNo);
			plInfo.setPayStatusCd(payLoanStatus);
			payLoanInfoDao.save(plInfo);
			break;
		case PAY_LOAN_STATUS_S5:
			accountEnterOfCX(transactionNo, payLoanStatus);
			break;
		default:
			break;
		}
		return plInfo;
	}
	/**
	 * 入账
	 * @param transNo
	 * @param busiDate
	 * @return
	 */
	private PayLoanInfo accountEnter(String transNo,Date busiDate){
		Timestamp t_busiDate=new Timestamp(busiDate.getTime());
		PayLoanInfo plInfo=findByTransNo(transNo);
		Contract contract =contractDao.findByContractId(plInfo.getContractId());
		contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S2"));// 改变合同的状态为
		// 已放款
		// contract.setContractBalance(contract.getContractAmt());
		if (contract.getContractBalance() == null) {
			contract.setContractBalance(BigDecimal.ZERO);
		}
		// 已放款
		contract.setContractBalance(contract.getContractBalance().add(plInfo.getLoanAmt()));
		// 累计放款金额
		if (contract.getCumulativePayoutAmt() == null) {
			contract.setCumulativePayoutAmt(BigDecimal.ZERO);
		}
		contract.setCumulativePayoutAmt(contract.getCumulativePayoutAmt().add(plInfo.getLoanAmt()));
		// 本次可放款金额
		if (contract.getContractAvailableAmt() == null) {
			contract.setCumulativePayoutAmt(contract.getContractAmt().subtract(contract.getCumulativePayoutAmt()));
		} else {
			contract.setContractAvailableAmt(contract.getContractAvailableAmt().subtract(plInfo.getLoanAmt()));
		}
		// 累计收取手续费及佣金
		if (contract.getFreepayloanamtcnt() == null) {
			contract.setFreepayloanamtcnt(BigDecimal.ZERO);
		}
		if(plInfo.getSumAmt() == null){
			plInfo.setSumAmt(BigDecimal.ZERO);
		}
		contract.setFreepayloanamtcnt(contract.getFreepayloanamtcnt().add(plInfo.getSumAmt()));
		contract.setPayloanDate(t_busiDate); // 放款开始时间
		plInfo.setLoanActulTime(t_busiDate);
		plInfo.setPayStatusCd(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		payLoanInfoDao.save(plInfo); // 更新放款信息
		//生成还款计划
		buildRepayPlan(transNo, contract, plInfo,busiDate);
		sendMsg(contract, plInfo);
		return plInfo;
	}
	/**
	 * 冲销入账
	 * @param transNo
	 * @param payLoanStatus
	 * @return
	 */
	private PayLoanInfo accountEnterOfCX(String transNo,String payLoanStatus){
		PayLoanInfo plInfo=findByTransNo(transNo);
		plInfo.setPayStatusCd(payLoanStatus);
		plInfo.setManyPayStatus("1");
		payLoanInfoDao.save(plInfo);
		Contract contract =contractDao.findByContractId(plInfo.getContractId());
		if (plInfo.getLoanAmt().compareTo(contract.getContractAmt()) == 0) {
			contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S1"));// 改变合同的状态为
			// 已签订
			contract.setContractBalance(null);// 合同余额
			contract.setCumulativePayoutAmt(BigDecimal.ZERO); // 累计放款金额
			contract.setContractAvailableAmt(contract.getContractAmt()); // 可用金额
			contract.setFreepayloanamtcnt(BigDecimal.ZERO);
		} else {
			contract.setContractAvailableAmt(contract.getContractAvailableAmt().add(plInfo.getLoanAmt())); // 可用金额
			contract.setCumulativePayoutAmt(contract.getCumulativePayoutAmt().subtract(plInfo.getLoanAmt())); // 累计放款金额
			contract.setContractBalance(contract.getContractBalance().subtract(plInfo.getLoanAmt()));// 合同余额
			if (contract.getContractAmt().compareTo(contract.getContractAvailableAmt()) == 0) {
				contract.setContractBalance(null);// 合同余额
				contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S1"));// 改变合同的状态为已签订
			} else {
				contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S2"));// 改变合同的状态为已放款
			}
			if (contract.getFreepayloanamtcnt() != null && plInfo.getSumAmt() != null) {
				contract.setFreepayloanamtcnt(contract.getFreepayloanamtcnt().subtract(plInfo.getSumAmt()));
			}
			List<PayLoanInfo> payLoanInfoList = payLoanInfoDao.findListByContStatus(plInfo.getContractId(), loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2), plInfo.getPayLoanIndex() - 1);
			if (payLoanInfoList != null && payLoanInfoList.size() > 0) {
				PayLoanInfo updateVO = payLoanInfoList.get(0);
				updateVO.setManyPayStatus("0");
				payLoanInfoDao.save(updateVO);
			}
		}
		contractDao.save(contract);
		return plInfo;
	}
	/**
	 * 账务-退单
	 * @param transNo
	 * @return
	 */
	private PayLoanInfo accountBack(String transNo){
		PayLoanInfo plInfo=findByTransNo(transNo);
		LoanStatus ls=loanCommonServeice.getLoanStatus(LoanConstants.PAY_LOAN_STATUS, plInfo.getPayStatusCd());
		Contract contract =contractDao.findByContractId(plInfo.getContractId());
		switch (ls) {
		case PAY_LOAN_STATUS_S1:
			plInfo.setPayStatusCd(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
			plInfo.setManyPayStatus("1");
			if (plInfo.getLoanAmt().compareTo(contract.getContractAmt()) == 0){
				contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S1"));//已签订
				contract.setContractBalance(null);// 合同余额
				contract.setCumulativePayoutAmt(BigDecimal.ZERO); // 累计放款金额
				contract.setContractAvailableAmt(contract.getContractAmt()); // 可用金额
				contract.setFreepayloanamtcnt(BigDecimal.ZERO); // 累计收取手续费及佣金
			}else{
				if (contract.getContractAmt().compareTo(contract.getContractAvailableAmt()) == 0) {
					contract.setContractBalance(null);// 合同余额
					contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S1"));//已签订
				} else {
					contract.setContractStatusCd(loanCommonServeice.getCodeVal("ContractStatusCode", "S2"));//已放款
				}
				List<PayLoanInfo> payLoanInfoList = payLoanInfoDao.findListByContStatus(plInfo.getContractId(), loanCommonServeice.getCodeVal("ContractStatusCode", "S2"), plInfo.getPayLoanIndex() - 1);
				if (payLoanInfoList != null && payLoanInfoList.size() > 0) {
					PayLoanInfo updateVO = payLoanInfoList.get(0);
					updateVO.setManyPayStatus("0");
					payLoanInfoDao.save(updateVO);
				}
			}
			contractDao.save(contract);
			break;
		case PAY_LOAN_STATUS_S4:
			plInfo.setPayStatusCd(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
			break;
		default:
			break;
		}
		payLoanInfoDao.save(plInfo);
		return plInfo;
	}
	/**
	 * 根据业务编号查询放款信息
	 * @param transNo
	 * @return
	 */
	private PayLoanInfo findByTransNo(String transNo){
		List<PayLoanInfo> plList = payLoanInfoDao
				.findListByTransactionNo(transNo);
		if (plList == null || plList.isEmpty()) {
			throw new LoanBizException("放款信息不存在");
		}
		return plList.get(0);
	}
	/**
	 * 生成还款计划
	 */
	private void buildRepayPlan(String transNo,Contract contract,PayLoanInfo plInfo,Date busidate){
		//TODO 生成还款计划
		InterestCalCulateForm iccf = new InterestCalCulateForm();
		iccf.setPartyId(contract.getPartyId().intValue());
		iccf.setContractId(contract.getContractId().intValue());
		iccf.setProjectId(contract.getProjectId().intValue());
		iccf.setPayLoanId(plInfo.getPayLoanId().intValue());
		iccf.setOperatorUser(contract.getApplyUserNum());
		iccf.setOperatorMechanism(String.valueOf(contract.getApplyOrgId()));
		iccf.setLoanAmount(plInfo.getLoanAmt());// 贷款金额
		iccf.setLoanStartDate(busidate);
		//合同结束时间
		iccf.setLoanEndDate(DateTools.getEndingDate(busidate, contract.getContractTermUnit(), contract.getContractTerm()));
		if(loanCommonServeice.getCodeVal("LoanDateStyle", "S2").equals(contract.getLoanDateStyle())){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(plInfo.getLoanActulTime());
			iccf.setRepaymentDate(calendar.get(Calendar.DATE));// 约定还款日
			contract.setArrangeRepayDay(calendar.get(Calendar.DATE));
		}else{
			iccf.setRepaymentDate(contract.getArrangeRepayDay());// 约定还款日
		}
		contractDao.save(contract);
		iccf.setRepayment(contract.getRepayModeCd());// 还款方式
		iccf.setChargeAmt(plInfo.getSumAmt());

		iccf.setRate(plInfo.getContractRate());// 年利率 ！！
		iccf.setOverdueRate(plInfo.getOverduerate());// 逾期利率 !!
		iccf.setPlanList(this.findCustPlanList(contract));
		iccf.setRepaymentNumber(contract.getRepayPrincipalMonthes());
		iccf.setFlag(false);
		iccf.setTransactionNo(transNo);
		if (plInfo.getPayLoanIndex() > 0) {
			// 再次放款
			againPayoutLoan(iccf,plInfo );
		} else {
			// 生成还款计划
			initRepayPlanDb(iccf,plInfo);
		}
		//dynamicQuery.flush();
	}
	/**
	 *  初始化还款计划
	 * @param iccf
	 * @return	业务编号
	 */
	@Override
	public String initRepayPlanDb(InterestCalCulateForm iccf,PayLoanInfo payLoan ){
		//用来判断,放款是否多次触发
		List<RepayingPlan>  rpList=repayingPlanDao.findListByContPl(Long.valueOf(iccf.getProjectId()), Long.valueOf(iccf.getPartyId()));
		if(CollectionUtils.isNotEmpty(rpList)){
			throw new LoanBizException("此还款计划已存在,请与管理员联系!");
		}
		String transNo=null;//单据编号
		//账务处理
		if(iccf.isFlag()){
			DoVchAmtBo amtBo=new DoVchAmtBo();
			amtBo.setLoanId(integer2Long(iccf.getPayLoanId())); // 放款ID
			amtBo.setContractId(integer2Long(iccf.getContractId()));// 合同ID
			amtBo.setPartyId(integer2Long(iccf.getPartyId()));// 参与人ID
			amtBo.setProjectId(integer2Long(iccf.getProjectId())); // 业务ID
			amtBo.setVchdate(DateTools.stringToDate((DateTools.dateToString(iccf.getLoanStartDate(),
					"yyyy-MM-dd"))
					+ " " + DateTools.getCurrentDate("HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")); // 账务处理时间
			amtBo.setLoanAmt(iccf.getLoanAmount());
			amtBo.setChargeAmt(iccf.getChargeAmt());
			if (StringUtils.isNotBlank(iccf.getSourceType())
					&& !"null".equals(iccf.getSourceType())) {
				amtBo.setBcCostType(iccf.getSourceType());// 资金来源
			}
			DoVchResultBo resultBo=loanAccountingService.doVchByLoan(amtBo, 
						Integer.valueOf(iccf.getOperatorMechanism()), 
						Integer.valueOf(iccf.getOperatorUser()),
						payLoan );
			transNo=StringUtils.defaultIfEmpty(resultBo.getTransNo(), StringUtils.EMPTY);
			iccf.setTransactionNo(transNo);
		}else{
			transNo=iccf.getTransactionNo();
		}
		//贷款计算
		List<InterestVO> interestList=null;
		try {
			interestList = interestCalCulateService.calCulate(iccf);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RepayingPlan rp=new RepayingPlan();
		initRepayingPlan(rp, iccf,interestList.size());
		repayingPlanDao.save(rp);
		//插入计划明细表,并初始化还款明细表与欠款明细表
		saveRepayingPlanDetail(interestList, rp);
		//TODO 备份数据
		
		
		return transNo;
		
	}
	/**
	 * 保存还款计划详细
	 * @param interestList	贷款试算
	 * @param rp	还款计划
	 */
	private void saveRepayingPlanDetail(List<InterestVO> interestList,RepayingPlan rp) {
		String status =loanCommonServeice.getCodeVal(LoanStatus.PLAN_STATUS_S0);
		Date date=new Date();
		RepayingPlanDetail rpd=null;
		for (InterestVO interestVO : interestList) {
			rpd = new RepayingPlanDetail();
			rpd.setRepayingPlanId(rp.getRepayingPlanId()); // 还款计划ID
			rpd.setCurrentPeriod(interestVO.getRepaymentNumber());// 当前期数
			rpd.setOverdueRate(rp.getOverdueRate());// 逾期利率
			rpd.setYearRate(rp.getYearRate());// 年利率
			rpd.setCurrentStartDate(interestVO.getStartDate());// 当期计息起日
			rpd.setCurrentEndDate(interestVO.getEndDate());// 当期计划还款日
			rpd.setCurrentPrincipal(interestVO.getCurrentPricipal());// 当期应还本金
			rpd.setCurrentInterest(interestVO.getCurrentInterest());// 当期应还利息
			rpd.setCurrentPrincipalInterest(interestVO.getCurrentPricipalInterest());// 当期应还总额
			rpd.setEndCurrentPrincipal(interestVO.getEndCurrentPricipal());// 截止当期累计应还本金
			rpd.setEndCurrentInterest(interestVO.getEndCurrentInterest());// 截止当期累计应还利息
			rpd.setEndCurrentPrincipalbalance(interestVO.getEndCurrentPrincipalBalance());// 截止当期本金余额
			rpd.setOperatorUser(rp.getOperatorUser());// 操作者
			rpd.setOperatorMechanism(rp.getOperatorMechanism());// 操作机构
			rpd.setSystemDate(date);// 系统时间
			rpd.setStatus(status); // 还款计划明细状态(默认未还:0)
			rpd.setLastDate(rp.getEndDate());// 上次计算时间(计算合同外用的)
			rpd.setVersion(rp.getVersion());// 版本号
			rpd.setTransactionNo(rp.getTransactionNo());// 单据编号
			rpd.setSerialNum(1L);// 序号
			rpd.setTransactionStatus(rp.getTransactionStatus());// 单据状态
			rpd.setIsInterestInContractRecord(status);// 是否跑过利息结记
			rpd.setAdjustDate(interestVO.getEndDate());
			repayingPlanDetailDao.save(rpd);
			saveRepayingDetail(rpd, interestVO.getRepaymentNumber(), rp.getTransactionNo(), rp.getTransactionStatus(), rp.getLoanAmount());
			saveArrearsDetail(rpd.getRepayingPlanDetailId(), interestVO.getRepaymentNumber(), date, rp.getTransactionNo(), rp.getTransactionStatus(), status, null);
		}
		
	}
	/**
	 * 保存欠款明细表
	 * @param repayingPlanDetailId
	 * @param repayingNum
	 * @param date
	 * @param transactionNo
	 * @param transactionStatus
	 * @param status
	 * @param repaying_flag
	 */
	private void saveArrearsDetail(Long repayingPlanDetailId, int repayingNum,
			Date date, String transactionNo, String transactionStatus, String status, String repaying_flag) {
		ArrearsDetail arrearsDetail=new ArrearsDetail();
		arrearsDetail.setCarryoverImposeInterest(BigDecimal.ZERO); // 未还90天以上罚息
		arrearsDetail.setNotyetImposeInterest(BigDecimal.ZERO); // 未还90天内罚息
		arrearsDetail.setNotyetInterest(BigDecimal.ZERO); // 未还利息
		arrearsDetail.setNotyetPricipal(BigDecimal.ZERO); // 未还本金
		arrearsDetail.setPeriod(repayingNum); // 期次
		arrearsDetail.setPlanRepayintDate(date); // 约定还款日期
		arrearsDetail.setRecentlyDate(date); // 实际还款日期
		arrearsDetail.setRepayingPlanDetailId(repayingPlanDetailId); // 还款计划明细ID
		arrearsDetail.setSystemDate(new Date());
		arrearsDetail.setSerialNum(1); // 序号
		arrearsDetail.setStatus(status); // 还款计划明细状态
		arrearsDetail.setTransactionNo(transactionNo); // 单据编号
		arrearsDetail.setTransactionStatus(transactionStatus); // 单据状态
		arrearsDetail.setRepayingCalFlag("0"); // 逾期90天内与90天以上之间标记
		arrearsDetail.setYearImpose90Interest(BigDecimal.ZERO); // 改变利率,用到(90天以上罚息)
		arrearsDetail.setYearImposeInterest(BigDecimal.ZERO);// 改变利率,用到(90天内罚息)
		if (StringUtils.isNotBlank(repaying_flag) && !"null".equals(repaying_flag)) {
			arrearsDetail.setRepayingFlag(repaying_flag);
		} else {
			arrearsDetail.setRepayingFlag("0"); // 还款计划状态(0:从未逾期,1:逾期90天内,2:逾期90天以上)
		}
		arrearsDetail.setRepayingFirstFlag("0"); // 标记:首次逾期
		arrearsDetailDao.save(arrearsDetail);
	}
	/**
	 * 保存还款明细
	 * @param rpd
	 * @param repayingNum	还款期次
	 * @param transactionNo	单据编号
	 * @param transactionStatus	单据状态
	 * @param loanAmount	合同金额
	 */
	private void saveRepayingDetail(RepayingPlanDetail rpd,int repayingNum, String transactionNo, String transactionStatus, BigDecimal loanAmount) {
		RepayingDetail rd=new RepayingDetail();
		rd.setRepayingPlanDetailId(rpd.getRepayingPlanDetailId()); // 还款明细ID
		rd.setEndCurrentInterestcnt(BigDecimal.ZERO);// 截止当前累计还息
		rd.setEndCurrentPricipalcnt(loanAmount);// 截止当前累计本金余额
		rd.setRepayedImposeInterest(BigDecimal.ZERO);// 已还罚息
		rd.setRepayedInterest(BigDecimal.ZERO);// 已还利息
		rd.setRepayedPrincipal(BigDecimal.ZERO);// 已还本金
		rd.setRepayedTotalAmount(BigDecimal.ZERO);// 已还总金额
		rd.setRepayedTotalPricipal(BigDecimal.ZERO);// 合计已还款本金
		rd.setRepayingDate(rpd.getCurrentEndDate()); // 还款日期(默认约定还款日)
		rd.setRepayingNum(repayingNum); // 还款期次
		rd.setSystemDate(new Date());
		rd.setSerialNum(1L);// 序号
		rd.setTransactionNo(transactionNo);// 单据编号
		rd.setTransactionStatus(transactionStatus);// 单据状态
		repayingDetailDao.save(rd);
		
	}
	/**
	 * 初始化还款计划
	 * @param rp
	 * @param iccf
	 */
	private void initRepayingPlan(RepayingPlan rp,InterestCalCulateForm iccf,int totalPeriod){
		rp.setPartyId(Long.valueOf(iccf.getPartyId())); // 参与人ID
		rp.setContractId(Long.valueOf(iccf.getContractId()));// 合同ID
		rp.setProjectId(Long.valueOf(iccf.getProjectId()));// 业务ID
		rp.setPayLoanId(Long.valueOf(iccf.getPayLoanId()));// 放款ID
		rp.setRepayingMode(iccf.getRepayment());// 还款方式
		rp.setRepayingCycle(iccf.getRepaymentNumber() == null ? 0 : iccf
				.getRepaymentNumber());// 还款周期
		rp.setCycleUnit(loanCommonServeice.getCodeVal(LoanConstants.TERMUNITCD, "S2"));
		
		rp.setTotalPeriod(totalPeriod);// 总期数
		rp.setRepayedPeriod(0);// 已还期数
		rp.setSurplusPeriod(totalPeriod);// 剩余期数
		rp.setStartDate(iccf.getLoanStartDate());// 起贷时间(放款日期)
		rp.setEndDate(iccf.getLoanEndDate());// //
		// 到期时间(放款日期+期限）
		rp.setYearRate(iccf.getRate());// 贷款年利率
		rp.setOverdueRate(iccf.getOverdueRate());// 逾期利率
		rp.setLoanAmount(iccf.getLoanAmount());// 贷款金额
		rp.setRepayedAmount(iccf.getLoanAmount());// 贷款余额
		rp.setOperatorUser(iccf.getOperatorUser());// 操作者
		rp.setOperatorMechanism(iccf.getOperatorMechanism());// 操作机构
		rp.setVersion(1);// 版本号(默认从1开始)
		String transactionStatus = loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1);
		rp.setTransactionStatus(transactionStatus);// 单据状态
		rp.setTransactionNo(iccf.getTransactionNo());// 交易编号
		rp.setSerialNum(1);// 序号(默认从1开始)
		rp.setSystemDate(new Date());
		rp.setRepayingNum(commonBizNumberBS.generateAppNumber(LoanConstants.REPAYING_RULES,
				"", iccf.getOperatorMechanism()));// 还款编号
		rp.setContContractStatusCd( loanCommonServeice.getCodeVal(LoanConstants.CONTRACTSTATUSCODE,
				"S2")); // 合同放款后的状态(已放款)
		rp.setTableInterest(BigDecimal.ZERO); 
	}
	/**
	 * 再次放款
	 * @param iccf
	 * @return	业务编号
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String againPayoutLoan(InterestCalCulateForm iccf,PayLoanInfo payLoan){
		//Code RepaymentMode 
		if(!Arrays.asList("1","2").contains(iccf.getRepayment())){
			throw new LoanBizException("目前只有周期付息,到期还本与一次性到期付本允许多次放款,请确认!");
		}
		String transNo=null;
		//判断账务开关(关进入)
		if (iccf.isFlag()) { 
			DoVchAmtBo amtBo=new DoVchAmtBo();
			amtBo.setLoanId(integer2Long(iccf.getPayLoanId())); // 放款ID
			amtBo.setContractId(integer2Long(iccf.getContractId()));// 合同ID
			amtBo.setPartyId(integer2Long(iccf.getPartyId()));// 参与人ID
			amtBo.setProjectId(integer2Long(iccf.getProjectId())); // 业务ID
			amtBo.setVchdate(DateTools.stringToDate((DateTools.dateToString(iccf.getLoanStartDate(),
					"yyyy-MM-dd"))
					+ " " + DateTools.getCurrentDate("HH:mm:ss"), "yyyy-MM-dd HH:mm:ss")); // 账务处理时间
			amtBo.setLoanAmt(iccf.getLoanAmount());
			amtBo.setChargeAmt(iccf.getChargeAmt());
			if (StringUtils.isNotBlank(iccf.getSourceType())
					&& !"null".equals(iccf.getSourceType())) {
				amtBo.setBcCostType(iccf.getSourceType());// 资金来源
			}
			DoVchResultBo resultBo=loanAccountingService.doVchByLoan(amtBo, 
						Integer.valueOf(iccf.getOperatorMechanism()), 
						Integer.valueOf(iccf.getOperatorUser()),
						payLoan );
			transNo=StringUtils.defaultIfEmpty(resultBo.getTransNo(), StringUtils.EMPTY);
		}else{
			transNo=iccf.getTransactionNo();
		}
		Contract contract =contractDao.findByContractId(Long.valueOf(iccf.getContractId()));
		if(contract==null){
			throw new LoanBizException("查无合同资料,请确认!");
		}
		Long payLoanId=findPayLoanId(contract.getContractId());
		//查询还款计划
		List<RepayingPlan> rpList=repayingPlanDao.findListByContPl(contract.getContractId(), payLoanId);
		if(CollectionUtils.isEmpty(rpList)){
			throw new LoanBizException("查询无还款计划，请确认");
		}
		RepayingPlan updateRp=rpList.get(0);
		List<RepayingPlanDetail> rpdList=repayingPlanDetailDao.findListByRplId(updateRp.getRepayingPlanId());
		List<Long> rpdIds=Lists.newArrayList();
		for (RepayingPlanDetail rpd : rpdList) {
			rpdIds.add(rpd.getRepayingPlanDetailId());
		}
		List<RepayingDetail> rdList = (List<RepayingDetail>) dynamicQuery
				.query("from RepayingDetail where repayingPlanDetailId in (?1)  order by repayingNum asc",
						rpdIds);
		if(CollectionUtils.isEmpty(rdList)){
			throw new LoanBizException("查无还款明细记录,请联系管理员!");
		}
		List<ArrearsDetail> adList = (List<ArrearsDetail>) dynamicQuery
				.query("from ArrearsDetail where repayingPlanDetailId in (?1)",
						rpdIds);
		if(CollectionUtils.isEmpty(adList)){
			throw new LoanBizException("查无欠款明细记录,请联系管理员!");
		}
		updateRp.setPayLoanId(Long.parseLong(iccf.getPayLoanId().toString()));
		updateRp.setLoanAmount(updateRp.getLoanAmount().add(iccf.getLoanAmount()));
		updateRp.setRepayedAmount(updateRp.getRepayedAmount().add(
				iccf.getLoanAmount()));
		updateRp.setTransactionNo(transNo);
		for (RepayingDetail rd : rdList) {
			rd.setTransactionNo(transNo);
			// 截止当前累计本金余额
			rd.setEndCurrentPricipalcnt(rd.getEndCurrentPricipalcnt().add(
					iccf.getLoanAmount()));
		}
		for (ArrearsDetail ad : adList) {
			ad.setTransactionNo(transNo);
		}
		BigDecimal bd=updateRp.getYearRate().divide(LoanConstants.DCNT, 50, BigDecimal.ROUND_HALF_UP);
		Date startDate = iccf.getLoanStartDate(); // 开始计息
		BigDecimal endCurrentInterest = BigDecimal.ZERO; // 截止利息
		int flag_i = -1;// 标记,第几期
		RepayingPlanDetail rpd=null;
		final int rpdListSize=rpdList.size();
		for (RepayingPlanDetail t_rpd : rpdList) {
			if (t_rpd.getCurrentStartDate().compareTo(startDate) <= 0) {
				flag_i++;
			} else {
				break;
			}
		}
		if (flag_i == -1) { // 放款前
			flag_i = 0;
		} else if (flag_i == 0) {// 首期
			rpd = rpdList.get(0);
			if (rpd.getCurrentStartDate().compareTo(startDate) > 0) {
				startDate = rpd.getCurrentStartDate();
			}
		} else if (flag_i <= (rpdListSize - 1)) { // 中间
			rpd = rpdList.get(flag_i);
			if (startDate.compareTo(rpd.getCurrentEndDate()) >= 0) {
				flag_i++;
			}
		}
		BigDecimal loanAmout = updateRp.getLoanAmount();
		BigDecimal loanAmoutTemp = BigDecimal.ZERO;
		Date payLoanStartDate = null;
		boolean lastPeriodFlag = false;
		if (flag_i == rpdListSize) {
			lastPeriodFlag = true;
			flag_i--;
		}
		for (RepayingPlanDetail t_rpd : rpdList) {
			rpd=t_rpd;
			rpd.setTransactionNo(transNo);
			loanAmout = loanAmout.subtract(rpd.getCurrentPrincipal());
		}
		if (flag_i > 0) {
			endCurrentInterest = rpd.getEndCurrentInterest();
		} else {
			endCurrentInterest = BigDecimal.ZERO;
		}
		rpd = rpdList.get(flag_i);
		
		//还款信息
		List<PayLoanInfo> pliList=null;
		if(!lastPeriodFlag){
			//查询还款信息
			StringBuffer pli_jpql=new StringBuffer();
			pli_jpql.append("from PayLoanInfo pli where pli.contractId =?1")
			.append(" and pli.payStatusCd = ?2 and pli.payLoanId != ?3 ")
			.append("and pli.loanActulTime between to_date(?4,'yyyy-MM-dd') and to_date(?5,'yyyy-MM-dd')")
			.append(" order by pli.payLoanIndex desc");
			List<Object> pli_params=Lists.newArrayList();
			pli_params.add(contract.getContractId());
			pli_params.add(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
			pli_params.add(payLoanId);
			pli_params.add(rpd.getCurrentStartDate());
			pli_params.add(rpd.getCurrentEndDate());
			pliList=dynamicQuery.query(PayLoanInfo.class,pli_jpql.toString(), pli_params.toArray());
		}
		PayLoanInfo payLoanInfo = null;
		Date tempDate = null;
		Date tempStartDate = null;
		BigDecimal interest=null;
		String planStatus = loanCommonServeice.getCodeVal(LoanStatus.PLAN_STATUS_S1); // 已还
		for (int int_i = flag_i; int_i < rpdListSize; int_i++) {
			rpd=rpdList.get(int_i);
			rpd.setTransactionNo(transNo);
			if (!planStatus.equals(rpd.getStatus())) {
				// 计算利息
				interest = loanAmout.multiply(bd).multiply(
						new BigDecimal(DateTools.getDateDiff(startDate, rpd.getCurrentEndDate())));
				if (interest.compareTo(BigDecimal.ZERO) < 0) {
					interest = rpd.getCurrentInterest();
				} else if (DateTools.getDateDiff(rpd.getCurrentStartDate(), startDate) >= 0
						&& DateTools.getDateDiff(startDate, rpd.getCurrentEndDate()) >= 0) {
					loanAmoutTemp = loanAmoutTemp.add(loanAmout);
					// 只进入一次
					loanAmout = loanAmout.subtract(iccf.getLoanAmount());
					if (int_i > 0) {
						tempStartDate = getPrveDate(rpd.getCurrentStartDate());
					} else {
						tempStartDate = rpd.getCurrentStartDate();
					}
					if (CollectionUtils.isEmpty(pliList)) {
						interest = interest.add(loanAmout.multiply(bd).multiply(
								new BigDecimal(DateTools.getDateDiff(tempStartDate, startDate))));
					} else {
						for (int int_j = 0,pliListSize=pliList.size(); int_j < pliListSize; int_j++) {
							payLoanInfo = pliList.get(int_j);
							loanAmout = loanAmout.subtract(payLoanInfo.getLoanAmt());
							if (int_j < (pliList.size() - 1)) {
								payLoanStartDate = payLoanInfo.getLoanActulTime();
								tempDate = pliList.get(int_j + 1).getLoanActulTime();
							} else {
								payLoanStartDate = payLoanInfo.getLoanActulTime();
							}
							if ((int_j + 1) ==pliListSize) {
								interest = interest.add(loanAmout.multiply(bd).multiply(
										new BigDecimal(DateTools.getDateDiff(tempStartDate, payLoanStartDate))));
								loanAmout = loanAmout.add(payLoanInfo.getLoanAmt());
								interest = interest.add(loanAmout.multiply(bd).multiply(
										new BigDecimal(DateTools.getDateDiff(payLoanStartDate, startDate))));
							} else {
								interest = interest.add(loanAmout.multiply(bd).multiply(
										new BigDecimal(DateTools.getDateDiff(tempDate, payLoanStartDate))));
							}
							tempDate = payLoanInfo.getLoanActulTime();
						}
					}

					loanAmout = loanAmoutTemp;
				}
				interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
				// 当期利息
				rpd.setCurrentInterest(interest);
				if ((int_i + 1) ==rpdListSize) {
					// 当期本金
					rpd.setCurrentPrincipal(rpd.getCurrentPrincipal().add(
							iccf.getLoanAmount()));
					// 截止当期累计应还本
					rpd.setEndCurrentPrincipal(rpd.getEndCurrentPrincipal().add(
							iccf.getLoanAmount()));
				} else {
					// 截止当期本金余额
					rpd.setEndCurrentPrincipalbalance(rpd.getEndCurrentPrincipalbalance()
							.add(iccf.getLoanAmount()));
				}
				// 当期本息
				rpd.setCurrentPrincipalInterest(rpd.getCurrentInterest().add(
						rpd.getCurrentPrincipal()));
				if (int_i == 0) {
					endCurrentInterest = endCurrentInterest.add(interest);
				} else {
					endCurrentInterest = endCurrentInterest.add(rpd.getCurrentInterest());
				}
				rpd.setEndCurrentInterest(endCurrentInterest);
			} else {
				endCurrentInterest = endCurrentInterest.add(rpd.getCurrentInterest());
			}
			loanAmout = loanAmout.subtract(rpd.getCurrentPrincipal());
			if (DateTools.getDateDiff(startDate, rpd.getCurrentEndDate()) >= 0) {
				startDate = rpd.getCurrentEndDate();
			}
		}
		repayingPlanDao.save(updateRp);
		repayingPlanDetailDao.save(rpdList);
		repayingDetailDao.save(rdList);
		arrearsDetailDao.save(adList);
		return transNo;
	}
	@Override
	public void backupData(String billCd) {
		loanService.approvalService(billCd, loanCommonServeice
				.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2),
				LoanEvent.PAY_LOAN.toStr());
	}
	private Long findPayLoanId(Long contractId){
		StringBuffer nativeSql = new StringBuffer();
		nativeSql.append("select pl.pay_loan_id from pay_loan_info pl");
		nativeSql.append(",(select max(pay_loan_index) cnt,contract_id from pay_loan_info where contract_id = ?1  and pay_status_cd != ?2  group by contract_id) b");
		nativeSql.append(" where pl.contract_id = b.contract_id and pl.pay_loan_index = (b.cnt - 1) ");
		nativeSql.append(" and pl.pay_status_cd != ?3");
		nativeSql.append(" and pl.pay_status_cd != ?4");
		List<Object> params=Lists.newArrayList();
		params.add(contractId);
		params.add(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
		params.add(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
		params.add(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3));
		List<?> list=dynamicQuery.nativeQuery(nativeSql.toString(), params.toArray());
		if(CollectionUtils.isNotEmpty(list)){
			return Long.valueOf(String.valueOf(list.get(0)));
		}
		throw new LoanBizException("获取还款id出错");
	}
	/**
	 * 查询RepayLoan
	 * @param projectId	项目id
	 * @return
	 */
	@Deprecated
	private  List<InterestPlanForm> getPlanList(Long projectId){
		List<RepayPlan> rpList= repayPlanDao.findListByProjId(projectId);
		List<InterestPlanForm> planList =Lists.newArrayList();
		if(CollectionUtils.isEmpty(rpList)){
			logger.info("查询RepayPlan 为空");
			return null;
		}
		InterestPlanForm ipf=null;
		for (RepayPlan rp : rpList) {
			ipf=new InterestPlanForm();
			ipf.setPlanTime(rp.getRepayDate());
			ipf.setPlanPpal(rp.getRepayAmt());
			planList.add(ipf);
		}
		return planList;
	}
	/**
	 * 根据合同查询自定义还款计划明细
	 * @param contract{projectId,productType}
	 * @return
	 */
	public  List<InterestPlanForm> findCustPlanList(Contract contract){
		final Set<String> prodConf=this.loanCommonServeice.getProductConfForCustRepayPlan();
		List<InterestPlanForm> planList =null;
		InterestPlanForm ipf=null;
		final Long projectId=contract.getProjectId();
		if(prodConf.contains(contract.getProductType())){
			//查询批复最终的自定义还款计划
			List<ApprovalHistoryRepayPlan> ahrpList=approvalHistoryRepayPlanDao.findLastGroupListByProjId(projectId);
			if(CollectionUtils.isEmpty(ahrpList)){
				logger.info("批复最终的自定义还款计划为null");
				return null;
			}
			planList =Lists.newArrayList();
			for (ApprovalHistoryRepayPlan ahrp : ahrpList) {
				ipf=new InterestPlanForm();
				ipf.setPlanTime(ahrp.getRepayDate());
				ipf.setPlanPpal(ahrp.getRepayAmt());
				planList.add(ipf);
			}
			
		}else{
			//查询申请时的自定义还款计划
			List<RepayPlan> rpList= repayPlanDao.findListByProjId(projectId);
			if(CollectionUtils.isEmpty(rpList)){
				logger.info("查询RepayPlan 为空");
				return null;
			}
			planList =Lists.newArrayList();
			for (RepayPlan rp : rpList) {
				ipf=new InterestPlanForm();
				ipf.setPlanTime(rp.getRepayDate());
				ipf.setPlanPpal(rp.getRepayAmt());
				planList.add(ipf);
			}
		}
		return planList;
	}
	/**
	 * 发送信息（手机短信）
	 */
	private void sendMsg(Contract contract,PayLoanInfo plInfo){
		//TODO 短信
	}
	/**
	 * 获取前一天
	 * @param date
	 * @return
	 */
	private Date getPrveDate(Date date){
		Calendar calendar = Calendar.getInstance();
		if (date != null) {
			calendar.setTime(date);
		}
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return DateTools.getDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
	}
	
	private Long integer2Long(Integer i){
		return new Long(i);
	}

}

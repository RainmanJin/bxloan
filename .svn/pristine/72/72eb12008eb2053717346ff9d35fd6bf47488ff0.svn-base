package com.coamctech.bxloan.service.pettyloan.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.ArrearsDetailDao;
import com.coamctech.bxloan.dao.ArrearsDetailHistoryDao;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.LossProvisionDao;
import com.coamctech.bxloan.dao.OverdueStateDao;
import com.coamctech.bxloan.dao.RepayLoanDao;
import com.coamctech.bxloan.dao.RepayingDetailDao;
import com.coamctech.bxloan.dao.RepayingDetailHistoryDao;
import com.coamctech.bxloan.dao.RepayingPlanDao;
import com.coamctech.bxloan.dao.RepayingPlanDetailDao;
import com.coamctech.bxloan.dao.RepayingPlanDetailHistoryDao;
import com.coamctech.bxloan.dao.RepayingPlanHistoryDao;
import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.ArrearsDetailHistory;
import com.coamctech.bxloan.entity.ArrearsDetailHistoryPk;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.LossProvision;
import com.coamctech.bxloan.entity.OverdueState;
import com.coamctech.bxloan.entity.RepayLoan;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingDetailHistory;
import com.coamctech.bxloan.entity.RepayingDetailHistoryPk;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.entity.RepayingPlanDetailHistory;
import com.coamctech.bxloan.entity.RepayingPlanDetailHistoryPk;
import com.coamctech.bxloan.entity.RepayingPlanHistory;
import com.coamctech.bxloan.entity.RepayingPlanHistoryPk;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanService;
import com.coamctech.bxloan.service.pettyloan.exceptions.LoanBizException;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanEvent;
import com.coamctech.bxloan.service.pettyloan.util.LoanConstants.LoanStatus;
import com.google.common.collect.Lists;
@Transactional
@Service("loanService")
public class LoanServiceImpl implements LoanService{
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private RepayingPlanHistoryDao repayingPlanHistoryDao;
	@Autowired
	private RepayingDetailHistoryDao repayingDetailHistoryDao;
	@Autowired
	private RepayingPlanDao repayingPlanDao;
	@Autowired
	private OverdueStateDao overdueStateDao;
	@Autowired
	private RepayingDetailDao repayingDetailDao;
	@Autowired
	private RepayingPlanDetailDao repayingPlanDetailDao;
	@Autowired
	private RepayingPlanDetailHistoryDao repayingPlanDetailHistoryDao;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private ArrearsDetailDao arrearsDetailDao;
	@Autowired
	private ArrearsDetailHistoryDao arrearsDetailHistoryDao;
	@Autowired
	private RepayLoanDao repayLoanDao;
	@Autowired
	private LossProvisionDao lossProvisionDao;
	@Autowired
	private ContractDao contractDao;

	
	
	@Override
	public void approvalService(String transNo, RepayingPlan rp, String loanStatus,
			String event) {
		// 参数验证
		if(!isNotNull(transNo)||!isNotNull(loanStatus)||!isNotNull(event)){
			throw new LoanBizException("交易编号为null或是审批状态为null");
		}
		if (LoanEvent.SYSTEM_CHANGE.eq(loanStatus)
				&& LoanEvent.SYSTEM_CHANGE.eq(event)) {// 系统改变
			changePayLoanStatusOrSys(transNo, rp, loanStatus, event);
		}else{
			if(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2).equals(loanStatus)){
				//已入账
				changePayLoanStatusOrSys(transNo, rp, loanStatus, event);
				
			}else if(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3).equals(loanStatus)
					||loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5).equals(loanStatus)){
				//退单或冲销已入账
				if(!LoanEvent.PAY_LOAN.eq(event)){
					TransNoSerialNum tnsn=findLastTnsnByRph(transNo);
					List<RepayingPlanHistory> rphList=repayingPlanHistoryDao.findListByTransNoSerialNum(tnsn.getTransNo(), tnsn.getSerialNum());
					List<RepayingDetailHistory> rdhList=repayingDetailHistoryDao.findListByTransNoSerialNum(tnsn.getTransNo(), new Long(tnsn.getSerialNum()));
					// 查询欠款明细历史表
					List<ArrearsDetailHistory> adhList=arrearsDetailHistoryDao.findListByTransNoSerialNum(tnsn.getTransNo(),new Long(tnsn.getSerialNum()));
					// 查询还款计划
					List<RepayingPlan> rpList=repayingPlanDao.findListByTransNo(transNo);
					if (CollectionUtils.isEmpty(rpList)||rpList.get(0)==null) {
						throw new LoanBizException("还款计划不存在");
					}
					RepayingPlan repayingPlan=rpList.get(0);
					Long repayingPlanId=rpList.get(0).getRepayingPlanId();
					// 查询还款计划明细历史
					List<RepayingPlanDetailHistory> rpdhList=null;
					if(CollectionUtils.isNotEmpty(rpList)){
						rpdhList=findRepayingPlanDetailHistoryList(rpList.get(0).getRepayingPlanId(),loanStatus);
					}
					//查询还款计划明细
					List<RepayingPlanDetail> rpdList=repayingPlanDetailDao.findListByTransNo(transNo);
					//查询还款明细
					List<RepayingDetail> rdList=repayingDetailDao.findListByTransNo(transNo);
					//查询欠款明细表
					List<ArrearsDetail> adList=arrearsDetailDao.findListByTransNo(transNo);
					List<Long> rpdIds=Lists.newArrayList();//还款计划明细Id集
					for (RepayingPlanDetail rpd : rpdList) {
						rpdIds.add(rpd.getRepayingPlanDetailId());
					}
					// 贷款回收还款明细
					List<RepayLoan> rlList=repayLoanDao.findListByTransNo(transNo);
					boolean result = false;
					if (CollectionUtils.isNotEmpty(rlList)) {
						// 提前部分还款
						if ("3".equals(rlList.get(0).getCleanCutCd())) {
							result = true;
						}
					}
					// 多次放款--冲销
					if (LoanEvent.AGAIN_PAY_LOAN.eq(event)) {
						result = true;
					}
					
					if(CollectionUtils.isNotEmpty(rpdList)&&loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5).equals(loanStatus)){
						// 冲销入账,将历史数据状态都变成冲销已入账--当前单据编号
						List<RepayingPlanHistory> update_rphList=repayingPlanHistoryDao.findListByTransNo(transNo);
						List<RepayingPlanDetailHistory> update_rpdhList=repayingPlanDetailHistoryDao.findListByTransNo(repayingPlanId, new Long(findResultInt(repayingPlanId, loanStatus)));
						List<RepayingDetailHistory> update_rdhList=repayingDetailHistoryDao.findListByTransNo(transNo);
						List<ArrearsDetailHistory> update_adhList=arrearsDetailHistoryDao.findListByTransNo(transNo);
						if(CollectionUtils.isNotEmpty(update_rphList)&&CollectionUtils.isNotEmpty(update_rpdhList)
								&&CollectionUtils.isNotEmpty(update_rdhList)&&CollectionUtils.isNotEmpty(adhList)
								&&update_adhList.size()==update_rdhList.size()){
							RepayingPlanHistory rph=update_rphList.get(0);
							rph.setTransactionNo(loanStatus);
							for (RepayingDetailHistory rdh : update_rdhList) {
								rdh.setTransactionStatus(loanStatus);
							}
							for (ArrearsDetailHistory ad : update_adhList) {
								ad.setTransactionStatus(loanStatus);
							}
							if(result){
								for (RepayingPlanDetailHistory rpdh : update_rpdhList) {
									rpdh.setTransactionStatus(loanStatus);
								}
								repayingPlanDetailHistoryDao.save(update_rpdhList);
							}
							repayingPlanHistoryDao.save(rph);
							repayingDetailHistoryDao.save(update_rdhList);
							arrearsDetailHistoryDao.save(update_adhList);
						}
					}
					// 将历史数据还原--当前单据编号的上一笔(如果是冲销那是历史表上一条,如果是退单那是历史表最后一条)
					if(CollectionUtils.isNotEmpty(rphList)&&CollectionUtils.isNotEmpty(rdhList)
							&&CollectionUtils.isNotEmpty(adhList)&&CollectionUtils.isNotEmpty(rpList)
							&&CollectionUtils.isNotEmpty(rpdList)&&CollectionUtils.isNotEmpty(rdList)
							&&adhList.size()==rdhList.size()&&adList.size()==rdList.size()&&rdList.size()==rpdList.size()){
						int total_period = rpdList.size(); // 总期数
						int repayed_period = 0; // 已还期数
						// 已还状态
						String endStatus = loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1);
						for (ArrearsDetailHistory vo : adhList) {
							if (endStatus.equals(vo.getStatus())) {
								repayed_period++;
							}
						}
						RepayingPlanHistory repayingPlanHistory = rphList.get(0);
						repayingPlanHistory.setTotalPeriod(total_period);
						repayingPlanHistory.setRepayedPeriod(repayed_period);
						repayingPlanHistory.setSurplusPeriod(total_period - repayed_period);
						// 损失计提
						LossProvision updateLossProvisionVO =findLossProvisionByContractId(repayingPlan.getContractId());
						if (updateLossProvisionVO != null) {
							if (loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5).equals(loanStatus)
									&& repayingPlanHistory.getPrivisionAmt() != null) { // 冲销入账
								updateLossProvisionVO.setPrivisionAmt(repayingPlanHistory.getPrivisionAmt());
							} else if (repayingPlan.getPrivisionAmt() != null) {
								updateLossProvisionVO.setPrivisionAmt(repayingPlan.getPrivisionAmt());
							}
							lossProvisionDao.save(updateLossProvisionVO);
						}
						Contract contract = this.findContractById(repayingPlan.getContractId());
						contract.setContractStatusCd(repayingPlanHistory.getContContractStatusCd());
						contract.setLastOvdueDate(repayingPlanHistory.getContLastOvdueDate());
						contract.setFirstOvdueDate(repayingPlanHistory.getContFirstOvdueDate());
						if (repayingPlanHistory.getContFiveclassification() != null) {
							contract.setFiveclassification(repayingPlanHistory.getContFiveclassification());
						}
						contract.setOvdueAmt(repayingPlanHistory.getContOvdueAmt());
						if(repayingPlanHistory.getContOvdueDays()!=null){
							contract.setOvdueDays(new Long(repayingPlanHistory.getContOvdueDays()));
						}
						contract.setOvdueInterest(repayingPlanHistory.getContOvdueInterest());
						if(repayingPlanHistory.getContOvdueTime()!=null){
							contract.setOvdueTime(new Long(repayingPlanHistory.getContOvdueTime()));
						}
						contractDao.save(contract);
						// 还款计划表
						this.updateRepayingPlanByRepayingPlanHistory(repayingPlan, repayingPlanHistory);
						if (result && rpdhList.size() > 0) {
							// 还款计划明细表
							this.updateRepayingPlanDetailByRepayingPlanDetailHistory(rpdList,
									rpdhList, adhList);
						} else {
							// 还款计划明细表
							this.updateRepayingPlanDetailByArrearsDetailHistory(rpdList,
									adhList, event);
						}
						// 还款明细表
						this.updateRepayingDetailByRepayingDetailHistory(rdList,
								rdhList);
						// 欠款明细表
						this.updateArrearsDetailByArrearsDetailHistory(adList, adhList);
						// 还原逾期情况表
						this.updateOverdueState(repayingPlan.getContractId(), loanStatus);
					}
					
				}
				
			}
		}

	}
	/**
	 * @param repayingPlanId
	 * @return
	 */
	private List<RepayingPlanDetailHistory> findRepayingPlanDetailHistoryList(Long repayingPlanId,String loanStatus){
		int resultInt=findResultInt(repayingPlanId, loanStatus);
		List<Object[]> resultList=dynamicQuery.nativeQuery("select distinct transaction_status,serial_num  from repaying_plan_detail_history where repaying_plan_id = ?1  and serial_num < ?2  order by serial_num desc ",repayingPlanId,resultInt);
		long index_int = 0;
		if (CollectionUtils.isNotEmpty(resultList)) {
			index_int = resultList.size();
			for (Object[] objs : resultList) {
				if (String.valueOf(objs[0])!=null&&String.valueOf(objs[0]).equals(loanStatus)) {
					index_int--;
				}else{
					break;
				}
			}
		}
		return dynamicQuery.query(RepayingPlanDetailHistory.class,"from RepayingPlanDetailHistory rpdh where rpdh.repayingPlanId = ?1 and rpdh.id.serialNum = ?2 order by currentPeriod asc ",repayingPlanId,index_int);
	}
	private int findResultInt(Long repayingPlanId,String loanStatus){
		Long max_serial_num=dynamicQuery.nativeQuerySingleResult(BigDecimal.class,"select max(serial_num) from repaying_plan_detail_history  where repaying_plan_id = ?1   and transaction_status !=?2", repayingPlanId,loanStatus).longValue();
		return max_serial_num.intValue();
	}
	/**
	 * 将欠款明细历史表更新到欠款明细表
	 * @param adList
	 * @param adhList
	 */
	private void updateArrearsDetailByArrearsDetailHistory(
			List<ArrearsDetail> adList, List<ArrearsDetailHistory> adhList) {
		for (ArrearsDetailHistory updateArrearsDetailHistoryVO : adhList) {
			for (ArrearsDetail updateArrearsDetailVO : adList) {
				if (updateArrearsDetailHistoryVO.getPeriod().compareTo(updateArrearsDetailVO.getPeriod()) == 0) {
					// 90天后罚息
					updateArrearsDetailVO.setCarryoverImposeInterest(updateArrearsDetailHistoryVO
							.getCarryoverImposeInterest());
					// 未还罚息
					updateArrearsDetailVO.setNotyetImposeInterest(updateArrearsDetailHistoryVO
							.getNotyetImposeInterest());
					updateArrearsDetailVO.setRecentlyDate(updateArrearsDetailHistoryVO.getRecentlyDate());
					updateArrearsDetailVO.setPlanRepayintDate(updateArrearsDetailHistoryVO.getPlanRepayintDate());
					// 未还利息
					updateArrearsDetailVO.setNotyetInterest(updateArrearsDetailHistoryVO.getNotyetInterest());
					// 未还本金
					updateArrearsDetailVO.setNotyetPricipal(updateArrearsDetailHistoryVO.getNotyetPricipal());
					// 期次
					updateArrearsDetailVO.setPeriod(updateArrearsDetailHistoryVO.getPeriod());
					// 还款状态
					updateArrearsDetailVO.setStatus(updateArrearsDetailHistoryVO.getStatus());
					updateArrearsDetailVO.setSystemDate(updateArrearsDetailHistoryVO.getSystemDate());
					// 单据编号
					updateArrearsDetailVO.setTransactionNo(updateArrearsDetailHistoryVO.getTransactionNo());
					// 单据状态
					updateArrearsDetailVO.setTransactionStatus(updateArrearsDetailHistoryVO.getTransactionStatus());
					// 还款标识
					updateArrearsDetailVO.setRepayingFlag(updateArrearsDetailHistoryVO.getRepayingFlag());
					// 计算标识
					updateArrearsDetailVO.setRepayingCalFlag(updateArrearsDetailHistoryVO.getRepayingCalFlag());
					// 首次逾期标识
					updateArrearsDetailVO.setRepayingFirstFlag(updateArrearsDetailHistoryVO.getRepayingFirstFlag());
					// 利率变更,存放逾期90天以上罚息
					updateArrearsDetailVO.setYearImpose90Interest(updateArrearsDetailHistoryVO
							.getYearImpose90Interest());
					// 利率变更,存放逾期90天内罚息
					updateArrearsDetailVO.setYearImposeInterest(updateArrearsDetailHistoryVO.getYearImposeInterest());
					break;
				}
			}
		}
		arrearsDetailDao.save(adList);
		
	}
	/**
	 * 找出还款计划明细历史表更新到还款计划明细表
	 * 
	 * @param repayingPlanDetailList还款计划明细list
	 * @param arrearsDetailHistoryList欠款明细历史list
	 */
	private void updateRepayingPlanDetailByArrearsDetailHistory(
			List<RepayingPlanDetail> rpdList,
			List<ArrearsDetailHistory> adhList, String event) {
		RepayingPlanDetail repayingPlanDetail = null;
		ArrearsDetailHistory arrearsDetailHistory = null;
		for (int int_i = 0; int_i < rpdList.size(); int_i++) {
			repayingPlanDetail = rpdList.get(int_i);
			arrearsDetailHistory = adhList.get(int_i);
			repayingPlanDetail.setStatus(arrearsDetailHistory.getStatus());
			repayingPlanDetail.setTransactionNo(arrearsDetailHistory.getTransactionNo());
			repayingPlanDetail.setTransactionStatus(arrearsDetailHistory.getTransactionStatus());
			if (LoanEvent.INTEREST_RECORD.eq(event)) {
				if ((int_i + 1) < rpdList.size()) {
					if ("0".equals(rpdList.get(int_i + 1).getIsInterestInContractRecord())) {
						repayingPlanDetail.setIsInterestInContractRecord("0");
					}
				} else {
					repayingPlanDetail.setIsInterestInContractRecord("0");
				}
			}
		}
		repayingPlanDetailDao.save(rpdList);
	}
	/**
	 * 找出还款计划明细历史表更新到还款计划明细表
	 * 
	 * @param repayingPlanDetailList还款计划明细list
	 * @param repayingPlanDetailHistoryList还款计划明细历史list
	 * @param arrearsDetailHistoryList欠款明细历史list
	 */

	private void updateRepayingPlanDetailByRepayingPlanDetailHistory(
			List<RepayingPlanDetail> rpdList,
			List<RepayingPlanDetailHistory> rpdhList,
			List<ArrearsDetailHistory> adhList) {
		RepayingPlanDetail updateRepayingPlanDetailVO = null;
		RepayingPlanDetailHistory updateRepayingPlanDetailHistoryVO = null;
		ArrearsDetailHistory arrearsDetailHistory = null;
		for (int int_i = 0; int_i < rpdList.size(); int_i++) {
			updateRepayingPlanDetailVO = rpdList.get(int_i);
			updateRepayingPlanDetailHistoryVO = rpdhList.get(int_i);
			arrearsDetailHistory = adhList.get(int_i);
			// 当期约定还款日期
			updateRepayingPlanDetailVO.setCurrentEndDate(updateRepayingPlanDetailHistoryVO.getCurrentEndDate());
			// 当期利息
			updateRepayingPlanDetailVO.setCurrentInterest(updateRepayingPlanDetailHistoryVO.getCurrentInterest());
			// 当期期次
			updateRepayingPlanDetailVO.setCurrentPeriod(updateRepayingPlanDetailHistoryVO.getCurrentPeriod());
			// 当期本金
			updateRepayingPlanDetailVO.setCurrentPrincipal(updateRepayingPlanDetailHistoryVO.getCurrentPrincipal());
			// 当期本息
			updateRepayingPlanDetailVO.setCurrentPrincipalInterest(updateRepayingPlanDetailHistoryVO
					.getCurrentPrincipalInterest());
			// 当期开始日期
			updateRepayingPlanDetailVO.setCurrentStartDate(updateRepayingPlanDetailHistoryVO.getCurrentStartDate());
			// 截止当期累计应还息
			updateRepayingPlanDetailVO.setEndCurrentInterest(updateRepayingPlanDetailHistoryVO.getEndCurrentInterest());
			// 截止当期累计应还本
			updateRepayingPlanDetailVO.setEndCurrentPrincipal(updateRepayingPlanDetailHistoryVO
					.getEndCurrentPrincipal());
			// 截止当期本金余额
			updateRepayingPlanDetailVO.setEndCurrentPrincipalbalance(updateRepayingPlanDetailHistoryVO
					.getEndCurrentPrincipalbalance());
			// 计息日期
			updateRepayingPlanDetailVO.setLastDate(updateRepayingPlanDetailHistoryVO.getLastDate());
			// 操作机构
			updateRepayingPlanDetailVO.setOperatorMechanism(updateRepayingPlanDetailHistoryVO.getOperatorMechanism());
			// 操作者
			// updateRepayingPlanDetailVO.setOperatorUser(updateRepayingPlanDetailHistoryVO.getOperatorUser());
			// 逾期利率
			updateRepayingPlanDetailVO.setOverdueRate(updateRepayingPlanDetailHistoryVO.getOverdueRate());
			// 还款计划ID
			updateRepayingPlanDetailVO.setRepayingPlanId(updateRepayingPlanDetailHistoryVO.getRepayingPlanId());
			// 还款状态
			updateRepayingPlanDetailVO.setStatus(arrearsDetailHistory.getStatus());
			updateRepayingPlanDetailVO.setSystemDate(updateRepayingPlanDetailHistoryVO.getSystemDate());
			// 单据编号
			updateRepayingPlanDetailVO.setTransactionNo(arrearsDetailHistory.getTransactionNo());
			// 单据状态
			updateRepayingPlanDetailVO.setTransactionStatus(updateRepayingPlanDetailHistoryVO.getTransactionStatus());
			// 版本号
			updateRepayingPlanDetailVO.setVersion(updateRepayingPlanDetailHistoryVO.getRpdhPk().getVersion());
			// 年利率
			updateRepayingPlanDetailVO.setYearRate(updateRepayingPlanDetailHistoryVO.getYearRate());
			// 是否结记利息跑过
			updateRepayingPlanDetailVO.setIsInterestInContractRecord(updateRepayingPlanDetailHistoryVO
					.getIsInterestInContractRecord());
		}
		repayingPlanDetailDao.save(rpdList);
		
	}
	/**
	 * 装还款明细历史表更新到还款明细表
	 * 
	 * @param repayingDetailList还款明细list
	 * @param repayingDetailHistoryList还款明细历史list
	 */
	private void updateRepayingDetailByRepayingDetailHistory(
			List<RepayingDetail> rdList, List<RepayingDetailHistory> rdhList) {
		for (RepayingDetailHistory updateRepayingDetailHistoryVO : rdhList) {
			for (RepayingDetail updateRepayingDetailVO : rdList) {
				if (updateRepayingDetailHistoryVO.getRepayingNum().compareTo(updateRepayingDetailVO.getRepayingNum()) == 0) {
					// 截止当前累计还息
					updateRepayingDetailVO.setEndCurrentInterestcnt(updateRepayingDetailHistoryVO
							.getEndCurrentInterestcnt());
					updateRepayingDetailVO.setRepayingDate(updateRepayingDetailHistoryVO.getRepayingDate());
					// 截止当前累计本金余额
					updateRepayingDetailVO.setEndCurrentPricipalcnt(updateRepayingDetailHistoryVO
							.getEndCurrentPricipalcnt());
					// 已还罚息
					updateRepayingDetailVO.setRepayedImposeInterest(updateRepayingDetailHistoryVO
							.getRepayedImposeInterest());
					// 已还利息
					updateRepayingDetailVO.setRepayedInterest(updateRepayingDetailHistoryVO.getRepayedInterest());
					// 已还本金
					updateRepayingDetailVO.setRepayedPrincipal(updateRepayingDetailHistoryVO.getRepayedPrincipal());
					// 已还总金额
					updateRepayingDetailVO.setRepayedTotalAmount(updateRepayingDetailHistoryVO.getRepayedTotalAmount());
					// 合计已还款本金
					updateRepayingDetailVO.setRepayedTotalPricipal(updateRepayingDetailHistoryVO
							.getRepayedTotalPricipal());
					// 还款期次
					updateRepayingDetailVO.setRepayingNum(updateRepayingDetailHistoryVO.getRepayingNum());
					// 还款计划明细ID
					updateRepayingDetailVO.setRepayingPlanDetailId(updateRepayingDetailHistoryVO
							.getRepayingPlanDetailId());
					updateRepayingDetailVO.setSystemDate(updateRepayingDetailHistoryVO.getSystemDate());
					// 单据编号
					updateRepayingDetailVO.setTransactionNo(updateRepayingDetailHistoryVO.getTransactionNo());
					// 单据状态
					updateRepayingDetailVO.setTransactionStatus(updateRepayingDetailHistoryVO.getTransactionStatus());
					break;
				}
			}
		}
		repayingDetailDao.save(rdList);
		
	}
	/**
	 * 将还款计划历史表数据更新到还款计划表
	 * 
	 * @param repayingPlan还款计划
	 * @param repayingPlanHistory还款计划历史
	 */
	private void updateRepayingPlanByRepayingPlanHistory(
			RepayingPlan repayingPlan, RepayingPlanHistory repayingPlanHistory) {
		repayingPlan.setCycleUnit(repayingPlanHistory.getCycleUnit());// 周期单元
		repayingPlan.setEventType(repayingPlanHistory.getEventType());// 事件
		repayingPlan.setLoanAmount(repayingPlanHistory.getLoanAmount());// 贷款金额
		repayingPlan.setOperatorMechanism(repayingPlanHistory.getOperatorMechanism());// 操作机构
		// repayingPlan.setOperatorUser(repayingPlanHistory.getOperatorUser());//
		// 操作者
		repayingPlan.setPartyId(repayingPlanHistory.getPartyId());// 参与人ID
		repayingPlan.setPayLoanId(repayingPlanHistory.getPayLoanId());// 放款ID
		repayingPlan.setProjectId(repayingPlanHistory.getProjectId());// 业务ID
		repayingPlan.setRepayedAmount(repayingPlanHistory.getRepayedAmount());// 剩余本金
		repayingPlan.setRepayedPeriod(repayingPlanHistory.getRepayedPeriod());// 已还期次
		repayingPlan.setSurplusPeriod(repayingPlanHistory.getSurplusPeriod());// 剩余期次
		repayingPlan.setTotalPeriod(repayingPlanHistory.getTotalPeriod());// 总期次
		repayingPlan.setTransactionNo(repayingPlanHistory.getTransactionNo());// 单据编号
		repayingPlan.setTransactionStatus(repayingPlanHistory.getTransactionStatus());// 单据状态
		repayingPlan.setContFiveclassification(repayingPlanHistory.getContFiveclassification());// 五级分类
		repayingPlan.setContFirstOvdueDate(repayingPlanHistory.getContFirstOvdueDate());// 合同首次逾期
		repayingPlan.setContLastOvdueDate(repayingPlanHistory.getContLastOvdueDate());// 合同最近逾期
		repayingPlan.setContOvdueAmt(repayingPlanHistory.getContOvdueAmt());// 合同逾期本金
		repayingPlan.setContOvdueDays(repayingPlanHistory.getContOvdueDays());// 合同逾期天数
		repayingPlan.setContOvdueInterest(repayingPlanHistory.getContOvdueInterest());// 合同逾期利息
		repayingPlan.setContOvdueTime(repayingPlanHistory.getContOvdueTime());// 逾期逾期次数
		repayingPlan.setContContractStatusCd(repayingPlanHistory.getContContractStatusCd());// 合同状态
		repayingPlan.setPrivisionAmt(repayingPlanHistory.getPrivisionAmt()); // 准备金计提
		repayingPlan.setTableInterest(repayingPlanHistory.getTableInterest()); // 转出表内利息
		repayingPlanDao.save(repayingPlan);
		
	}
	/**
	 * 根据合同ID查询合同
	 * @param contractId
	 * @return
	 */
	private Contract findContractById(Long contractId) {
		List<Contract> contractList = dynamicQuery.query(Contract.class,"from Contract where contractId = ?1",contractId);
		if (contractList == null || contractList.size() == 0) {
			throw new LoanBizException("查无资料,数据异常!");
		}
		return contractList.get(0);
	}
	/**
	 * 根据合同ID查询是否有损失计提记录
	 * @param contractId
	 * @return
	 */
	private LossProvision findLossProvisionByContractId(Long contractId) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("from LossProvision lp where lp.orgIdOrContractId = ?1");
		jpql.append(" and lp.objectDimensionType = ?2");// 类型--合同
		jpql.append(" and lp.transactionStatus = ?3");// 单据状态
		jpql.append(" order by lp.serialNum desc ");
		List<Object> params=Lists.newArrayList();
		params.add(contractId);
		params.add(LoanConstants.OBJECTDIMENSIONTYPE_CONTRACT);
		params.add(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2));
		List<LossProvision> lossProvisionList =dynamicQuery.query(LossProvision.class,jpql.toString(), params.toArray());
		if (lossProvisionList != null && lossProvisionList.size() > 0) {
			return lossProvisionList.get(0);
		}
		return null;
	}
	/**
	 * 已入账或是未入帐或系统改变状态
	 * @param transNo
	 * @param approvalStatus
	 * @param eventType	事件（1:放款，2:多次放款，3：还款，4：利息结算，5：展期，6：冲销，7：转出，sys：系统服务）
	 */
	private void changePayLoanStatusOrSys(String transNo, RepayingPlan _rp, String approvalStatus, String eventType){
		//查询还款计划
		
		RepayingPlan repayingPlan = null;
		if(_rp!=null){
			if(!StringUtils.equals(_rp.getTransactionStatus(), loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5))){
				repayingPlan = _rp;
			}
		}else{
			List<RepayingPlan> rpList=repayingPlanDao.findListByTransNoStatus(transNo, loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
			if(!CollectionUtils.isNotEmpty(rpList)){
				throw new LoanBizException("查无资料");
			}else{
				repayingPlan = rpList.get(0);
			}
		}
		
		StringBuffer nativeSql=new StringBuffer();
		nativeSql.append("select max(serial_num) as serial_num from repaying_plan_history");
		nativeSql.append(" where repaying_plan_id = ?1");
		Number max_serial_num=dynamicQuery.nativeQuerySingleResult(Number.class,nativeSql.toString(), repayingPlan.getRepayingPlanId());
		Integer serial_num = 1; // 序号 (默认从1开始)
		if(max_serial_num!=null){
			serial_num=max_serial_num.intValue();
			serial_num++;
		}
		if (isNotNull(approvalStatus)&& isNotNull(eventType)){
			repayingPlan.setEventType(eventType);
			repayingPlan.setTransactionStatus(approvalStatus);
			updateOverdueState(repayingPlan.getContractId(), approvalStatus);
		}else{
			approvalStatus=repayingPlan.getTransactionStatus();
		}
		//更新还款计划
		repayingPlanDao.save(repayingPlan);
		//插入还款计划历史
		insertRepayingPlanHistory(repayingPlan, serial_num);
		
		final Long repayingPlanId =repayingPlan.getRepayingPlanId();
		List<RepayingPlanDetail> rpdList=dynamicQuery.query(RepayingPlanDetail.class,"from RepayingPlanDetail where repayingPlanId=?1 order by currentPeriod asc", repayingPlanId);
		List<Long> rpdIds=Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(rpdList)){
			for (RepayingPlanDetail rpd : rpdList) {
				rpdIds.add(rpd.getRepayingPlanDetailId());
			}
		}
		List<RepayingDetail> rdList=repayingDetailDao.findListByRpdIds(rpdIds);
		List<ArrearsDetail> adList=arrearsDetailDao.findListByRpdIds(rpdIds);
		if (CollectionUtils.isNotEmpty(rpdList)&&CollectionUtils.isNotEmpty(rdList)
				&&CollectionUtils.isNotEmpty(adList)&&rdList.size()==rpdList.size()&&rdList.size()==adList.size()) {
			for (RepayingPlanDetail rpd : rpdList) {
				rpd.setTransactionStatus(approvalStatus);
			}
			for (RepayingDetail rd : rdList) {
				rd.setTransactionStatus(approvalStatus);
			}
			for (ArrearsDetail ad : adList) {
				ad.setTransactionStatus(approvalStatus);
			}
			repayingPlanDetailDao.save(rpdList);
			repayingDetailDao.save(rdList);
			arrearsDetailDao.save(adList);
			boolean result = false;
			List<RepayLoan> rlList=repayLoanDao.findListByContractIdTransNo(repayingPlan.getContractId(), transNo);
			if (CollectionUtils.isNotEmpty(rlList)) {
				// 提前部分还款
				if ("3".equals(rlList.get(0).getCleanCutCd())) {
					result = true;
				}
			}
			// 放款,多次放款,改变利率,备份还款计划明细
			if (LoanEvent.PAY_LOAN.eq(eventType) || LoanEvent.AGAIN_PAY_LOAN.eq(eventType)
					|| LoanEvent.SYSTEM_CHANGE.eq(eventType) || result) {
				nativeSql.setLength(0);
				nativeSql.append("select max(serial_num) as serial_num from repaying_plan_detail_history where ");
				nativeSql.append("repaying_plan_detail_id = ?1");
				max_serial_num = dynamicQuery.nativeQuerySingleResult(Number.class, nativeSql.toString(), rpdList.get(0).getRepayingPlanDetailId());
				int index_num = 1; // 序号 (默认从1开始)
				if (max_serial_num!=null) {
						index_num = max_serial_num.intValue();
						index_num++;
				}
				this.insertRepayingPlanDetailHistoryList(rpdList, index_num);
			}
			this.insertRepayingDetailHistory(rdList, approvalStatus, serial_num);
			this.insertArrearsDetailHistory(adList, approvalStatus, serial_num);
		}
	}
	/**
	 * 插入欠款明细历史
	 * @param adList
	 * @param approvalStatus
	 * @param serial_num
	 */
	private void insertArrearsDetailHistory(List<ArrearsDetail> adList,
			String approvalStatus, Integer serial_num) {
		for (ArrearsDetail arrearsDetail : adList) {
			arrearsDetail.setTransactionStatus(approvalStatus); // 单据状态
			
			ArrearsDetailHistory adh = new ArrearsDetailHistory();
			ArrearsDetailHistoryPk pk = new ArrearsDetailHistoryPk();
			pk.setArrearsDetailId(arrearsDetail.getArrearsDetailId()); // 欠款明细ID
			pk.setSerialNum(new Long(serial_num)); // 序号
			adh.setCarryoverImposeInterest(arrearsDetail.getCarryoverImposeInterest()); // 未还逾期90天以上罚息
			adh.setAdhPk(pk);;
			adh.setNotyetImposeInterest(arrearsDetail.getNotyetImposeInterest()); // 未还逾期90天内罚息
			adh.setNotyetInterest(arrearsDetail.getNotyetInterest());// 未还利息
			adh.setNotyetPricipal(arrearsDetail.getNotyetPricipal());// 未还本金
			adh.setPeriod(arrearsDetail.getPeriod()); // 期次
			adh.setPlanRepayintDate(arrearsDetail.getPlanRepayintDate());// 约定还款日期
			adh.setRecentlyDate(arrearsDetail.getRecentlyDate());// 上次计息日期
			adh.setRepayingPlanDetailId(arrearsDetail.getRepayingPlanDetailId());// 还款计划明细ID
			adh.setSystemDate(arrearsDetail.getSystemDate());
			adh.setStatus(arrearsDetail.getStatus());// 还款计划明细状态
			adh.setTransactionNo(arrearsDetail.getTransactionNo());// 单据编号
			adh.setTransactionStatus(arrearsDetail.getTransactionStatus());// 单据状态
			adh.setRepayingFlag(arrearsDetail.getRepayingFlag());// 还款标识
			adh.setRepayingCalFlag(arrearsDetail.getRepayingCalFlag()); // 计算标识
			adh.setRepayingFirstFlag(arrearsDetail.getRepayingFirstFlag());// 首次逾期标识
			adh.setYearImpose90Interest(arrearsDetail.getYearImpose90Interest());// 利率变更,存放逾期90天以上罚息
			adh.setYearImposeInterest(arrearsDetail.getYearImposeInterest());// 利率变更,存放逾期90天内罚息
			arrearsDetailHistoryDao.save(adh);
		}
		
	}
	/**
	 * 插入还款明细历史
	 * @param rdList
	 * @param approvalStatus
	 * @param serial_num
	 */
	private void insertRepayingDetailHistory(List<RepayingDetail> rdList,
			String approvalStatus, Integer serial_num) {
		for (RepayingDetail repayingDetail : rdList) {
			repayingDetail.setTransactionStatus(approvalStatus);// 单据状态
			RepayingDetailHistory rdh = new RepayingDetailHistory();
			RepayingDetailHistoryPk pk = new RepayingDetailHistoryPk();
			pk.setRepayingDetailId(repayingDetail.getRepayingDetailId());// 还款明细ID
			pk.setSerialNum(new Long(serial_num));// 序号
			rdh.setRdhPk(pk);
			rdh.setRepayingPlanDetailId(repayingDetail.getRepayingPlanDetailId());// 还款计划明细ID
			rdh.setEndCurrentInterestcnt(repayingDetail.getEndCurrentInterestcnt());// 截止当前累计还息
			rdh.setEndCurrentPricipalcnt(repayingDetail.getEndCurrentPricipalcnt());// 截止当前累计本金余额
			rdh.setRepayedImposeInterest(repayingDetail.getRepayedImposeInterest());// 已还罚息
			rdh.setRepayedInterest(repayingDetail.getRepayedInterest());// 已还利息
			rdh.setRepayedPrincipal(repayingDetail.getRepayedPrincipal());// 已还本金
			rdh.setRepayedTotalAmount(repayingDetail.getRepayedTotalAmount());// 已还总金额
			rdh.setRepayedTotalPricipal(repayingDetail.getRepayedTotalPricipal());// 合计已还款本金
			rdh.setRepayingDate(repayingDetail.getRepayingDate());// 还款日期
			rdh.setRepayingNum(repayingDetail.getRepayingNum());// 还款期次
			rdh.setSystemDate(repayingDetail.getSystemDate());
			rdh.setTransactionNo(repayingDetail.getTransactionNo());// 单据编号
			rdh.setTransactionStatus(repayingDetail.getTransactionStatus());// 单据状态
			repayingDetailHistoryDao.save(rdh);
		}
		
	}
	/**
	 * 插入还款计划明细历史
	 * @param rpdList
	 * @param serial_num
	 */
	private void insertRepayingPlanDetailHistoryList(
			List<RepayingPlanDetail> rpdList, int serial_num) {
		for (RepayingPlanDetail repayingPlanDetail : rpdList) {
			RepayingPlanDetailHistory rpdh = new RepayingPlanDetailHistory();
			RepayingPlanDetailHistoryPk rpdhPk = new RepayingPlanDetailHistoryPk();
			rpdhPk.setRepayingPlanDetailId(repayingPlanDetail.getRepayingPlanDetailId()); // 还款计划明细ID
			rpdhPk.setVersion(repayingPlanDetail.getVersion()); // 版本号
			rpdhPk.setSerialNum(new Long(serial_num)); // 序号
			rpdh.setRepayingPlanId(repayingPlanDetail.getRepayingPlanId());// 还款计划ID
			rpdh.setCurrentEndDate(repayingPlanDetail.getCurrentEndDate());// 当期约定还款日期
			rpdh.setCurrentInterest(repayingPlanDetail.getCurrentInterest()); // 当期利息
			rpdh.setCurrentPeriod(repayingPlanDetail.getCurrentPeriod()); // 当期期次
			rpdh.setCurrentPrincipal(repayingPlanDetail.getCurrentPrincipal()); // 当期本金
			rpdh.setCurrentPrincipalInterest(repayingPlanDetail.getCurrentPrincipalInterest());// 当期利息
			rpdh.setCurrentStartDate(repayingPlanDetail.getCurrentStartDate());// 当期开始日期
			rpdh.setEndCurrentInterest(repayingPlanDetail.getEndCurrentInterest());// 截止当期累计应还息
			rpdh.setEndCurrentPrincipal(repayingPlanDetail.getEndCurrentPrincipal());// 截止当期累计应还本
			rpdh.setEndCurrentPrincipalbalance(repayingPlanDetail.getEndCurrentPrincipalbalance());// 截止当期本金余额
			rpdh.setRpdhPk(rpdhPk);
			rpdh.setOperatorMechanism(repayingPlanDetail.getOperatorMechanism());// 操作机构
			rpdh.setOperatorUser(repayingPlanDetail.getOperatorUser());// 操作者
			rpdh.setOverdueRate(repayingPlanDetail.getOverdueRate());// 逾期利率
			rpdh.setSystemDate(repayingPlanDetail.getSystemDate());
			rpdh.setYearRate(repayingPlanDetail.getYearRate());// 年利率
			rpdh.setStatus(repayingPlanDetail.getStatus());// 还款计划明细状态
			rpdh.setLastDate(repayingPlanDetail.getLastDate());// 上次计息时间
			rpdh.setTransactionNo(repayingPlanDetail.getTransactionNo()); // 单据编号
			rpdh.setTransactionStatus(repayingPlanDetail.getTransactionStatus()); // 单据状态
			rpdh.setIsInterestInContractRecord(repayingPlanDetail.getIsInterestInContractRecord()); // 是否跑过利息结记批
			repayingPlanDetailHistoryDao.save(rpdh);
		}
		
	}
	/**
	 * 插入还款计划历史表
	 * @param repayingPlan还款计划
	 * @param serial_num序号
	 */
	private void insertRepayingPlanHistory(RepayingPlan repayingPlan, Integer serial_num){
		RepayingPlanHistory rph = new RepayingPlanHistory();
		RepayingPlanHistoryPk rphPk = new RepayingPlanHistoryPk();
		rphPk.setVersion(repayingPlan.getVersion()); // 版本号
		rphPk.setRepayingPlanId(repayingPlan.getRepayingPlanId()); // 还款计划ID
		rphPk.setSerialNum(serial_num); // 序号
		rph.setContractId(repayingPlan.getContractId()); // 合同ID
		rph.setCycleUnit(repayingPlan.getCycleUnit());// 周期单元
		rph.setEndDate(repayingPlan.getEndDate());// 到期时间
		rph.setRphPk(rphPk);;
		rph.setLoanAmount(repayingPlan.getLoanAmount());// 贷款金额
		rph.setOperatorMechanism(repayingPlan.getOperatorMechanism());// 操作机构
		rph.setOperatorUser(repayingPlan.getOperatorUser());// 操作者
		rph.setOverdueRate(repayingPlan.getOverdueRate());// 逾期利率
		rph.setPartyId(repayingPlan.getPartyId()); // 参与人ID
		rph.setPayLoanId(repayingPlan.getPayLoanId()); // 放款ID
		rph.setProjectId(repayingPlan.getProjectId()); // 业务ID
		rph.setRepayedAmount(repayingPlan.getRepayedAmount()); // 贷款余额
		rph.setRepayedPeriod(repayingPlan.getRepayedPeriod());// 已还期次
		rph.setRepayingCycle(repayingPlan.getRepayingCycle());// 还款周期
		rph.setRepayingMode(repayingPlan.getRepayingMode());// 还款方式
		rph.setStartDate(repayingPlan.getStartDate());// 放款日期
		rph.setSurplusPeriod(repayingPlan.getSurplusPeriod());// 剩余期数
		rph.setSystemDate(repayingPlan.getSystemDate());
		rph.setTotalPeriod(repayingPlan.getTotalPeriod());// 总期数
		rph.setYearRate(repayingPlan.getYearRate());// 年利率
		rph.setTransactionNo(repayingPlan.getTransactionNo());// 单据编号
		rph.setTransactionStatus(repayingPlan.getTransactionStatus());// 单据状态
		rph.setEventType(repayingPlan.getEventType());// 事件类型
		rph.setRepayingNum(repayingPlan.getRepayingNum());// 还款编号
		rph.setContFirstOvdueDate(repayingPlan.getContFirstOvdueDate());// 合同首次逾期日期
		rph.setContFiveclassification(repayingPlan.getContFiveclassification());// 合同五级分类
		rph.setContLastOvdueDate(repayingPlan.getContLastOvdueDate());// 合同最近逾期日期
		rph.setContOvdueAmt(repayingPlan.getContOvdueAmt());// 合同逾期金额
		rph.setContOvdueDays(repayingPlan.getContOvdueDays());// 合同累计逾期天数
		rph.setContOvdueInterest(repayingPlan.getContOvdueInterest());// 合同逾期利息
		rph.setContOvdueTime(repayingPlan.getContOvdueTime());// 合同逾期次数
		rph.setContContractStatusCd(repayingPlan.getContContractStatusCd());// 合同状态
		rph.setPrivisionAmt(repayingPlan.getPrivisionAmt()); // 准备金计提
		rph.setTableInterest(repayingPlan.getTableInterest()); // 转出表内利息
		repayingPlanHistoryDao.save(rph);
		
	}
	/**
	 * 根据业务编号查询上一次业务编号及序号
	 * @param transNo
	 * @return
	 */
	private TransNoSerialNum findLastTnsnByRph(String transNo){
		List<RepayingPlanHistory> rphs=repayingPlanHistoryDao.findListByTransNo(transNo);
		Integer serial_num = null; // 序号
		Long repayingPlanId = null; // 还款计划ID
		if(CollectionUtils.isNotEmpty(rphs)){
			repayingPlanId=rphs.get(0).getRphPk().getRepayingPlanId();
			serial_num=findLastSerialNumByRph(rphs.get(0));
		}
		int paramsCount=1;
		List<Object> params=Lists.newArrayList();
		StringBuilder nativeSql=new StringBuilder("select r.transaction_no,r.serial_num from repaying_plan_history r");
		nativeSql.append(" where transaction_status != ?").append(paramsCount++);
		params.add(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
		nativeSql.append(" and repaying_plan_id = ");
		if(serial_num==null){
			nativeSql.append("( select distinct repaying_plan_id from repaying_plan where transaction_no = ?").append(paramsCount++).append(")");
			params.add(transNo);
		}else{
			nativeSql.append("?"+paramsCount++);
			params.add(repayingPlanId);
			nativeSql.append(" and r.serial_num = ?").append(paramsCount++);
			params.add(serial_num);
		}
		nativeSql.append(" order by serial_num desc ");
		List<Object[]> result=dynamicQuery.nativeQuery(nativeSql.toString(),params.toArray());
		if(CollectionUtils.isNotEmpty(result)){
			Object[] objs=result.get(0);
			return new TransNoSerialNum(String.valueOf(objs[0]), Integer.parseInt(String.valueOf(objs[1])));
		}
		throw new LoanBizException("查无上一次编号");
	}
	
	/**
	 * 查询RepayingPlanHistory获取上一次SerialNum
	 * @param rph
	 * @return
	 */
	private Integer findLastSerialNumByRph(RepayingPlanHistory rph){
		StringBuffer jpql = new StringBuffer("from RepayingPlanHistory rp");
		jpql.append(" where rp.rphPk.repayingPlanId =?1");
		jpql.append(" and rp.rphPk.serialNum < ?2");
		jpql.append(" and rp.transactionStatus < ?3");
		jpql.append(" order by rp.rphPk.serialNum desc ");
		List<?> list = dynamicQuery.query(jpql.toString(), rph.getRphPk()
				.getRepayingPlanId(), rph.getRphPk().getSerialNum(),
				loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5));
		return ((RepayingPlanHistory)list.get(0)).getRphPk().getSerialNum();
	}
	private boolean isNotNull(String str){
		return StringUtils.isNoneBlank(str)&&!"null".equalsIgnoreCase(str);
	}
	/**
	 * 更新逾期情况表
	 * 
	 * @param contractId合同ID
	 * @param payLoanStatus状态
	 */
	private void updateOverdueState(Long contractId, String payLoanStatus){
		List<OverdueState> overdueStateList =overdueStateDao.findListByContractIdLoanStatus(contractId, LoanConstants.BATCH_ONE);
		if (CollectionUtils.isNotEmpty(overdueStateList)) {
			for (OverdueState overdueState : overdueStateList) {
				overdueState.setPayLoanStatus(payLoanStatus);
				if(LoanConstants.BATCH_TYPE_TWO.equals(payLoanStatus)){// 入账
					overdueStateDao.save(overdueState);
				}else{// 退单
					if (overdueState.getDueDay()!=null&&overdueState.getDueDay()>0) {
						overdueState.setDueDay(null);
						overdueState.setRepaymentDate(null);
						overdueStateDao.save(overdueState);
					}else{
						overdueStateDao.delete(overdueState);
					}
				}
			}
		}
	}
	class TransNoSerialNum{
		private String transNo;
		private Integer serialNum;
		public String getTransNo() {
			return transNo;
		}
		public Integer getSerialNum() {
			return serialNum;
		}
		public TransNoSerialNum(String transNo, Integer serialNum) {
			super();
			this.transNo = transNo;
			this.serialNum = serialNum;
		}
	}
	@Override
	public void approvalService(String transNo, String loanStatus, String event) {
		// 参数验证
				if(!isNotNull(transNo)||!isNotNull(loanStatus)||!isNotNull(event)){
					throw new LoanBizException("交易编号为null或是审批状态为null");
				}
				if (LoanEvent.SYSTEM_CHANGE.eq(loanStatus)
						&& LoanEvent.SYSTEM_CHANGE.eq(event)) {// 系统改变
					changePayLoanStatusOrSys(transNo, null, loanStatus, event);
				}else{
					if(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S2).equals(loanStatus)){
						//已入账
						changePayLoanStatusOrSys(transNo, null, loanStatus, event);
						
					}else if(loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S3).equals(loanStatus)
							||loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5).equals(loanStatus)){
						//退单或冲销已入账
						if(!LoanEvent.PAY_LOAN.eq(event)){
							TransNoSerialNum tnsn=findLastTnsnByRph(transNo);
							List<RepayingPlanHistory> rphList=repayingPlanHistoryDao.findListByTransNoSerialNum(tnsn.getTransNo(), tnsn.getSerialNum());
							List<RepayingDetailHistory> rdhList=repayingDetailHistoryDao.findListByTransNoSerialNum(tnsn.getTransNo(), new Long(tnsn.getSerialNum()));
							// 查询欠款明细历史表
							List<ArrearsDetailHistory> adhList=arrearsDetailHistoryDao.findListByTransNoSerialNum(tnsn.getTransNo(),new Long(tnsn.getSerialNum()));
							// 查询还款计划
							List<RepayingPlan> rpList=repayingPlanDao.findListByTransNo(transNo);
							if (CollectionUtils.isEmpty(rpList)||rpList.get(0)==null) {
								throw new LoanBizException("还款计划不存在");
							}
							RepayingPlan repayingPlan=rpList.get(0);
							Long repayingPlanId=rpList.get(0).getRepayingPlanId();
							// 查询还款计划明细历史
							List<RepayingPlanDetailHistory> rpdhList=null;
							if(CollectionUtils.isNotEmpty(rpList)){
								rpdhList=findRepayingPlanDetailHistoryList(rpList.get(0).getRepayingPlanId(),loanStatus);
							}
							//查询还款计划明细
							List<RepayingPlanDetail> rpdList=repayingPlanDetailDao.findListByTransNo(transNo);
							//查询还款明细
							List<RepayingDetail> rdList=repayingDetailDao.findListByTransNo(transNo);
							//查询欠款明细表
							List<ArrearsDetail> adList=arrearsDetailDao.findListByTransNo(transNo);
							List<Long> rpdIds=Lists.newArrayList();//还款计划明细Id集
							for (RepayingPlanDetail rpd : rpdList) {
								rpdIds.add(rpd.getRepayingPlanDetailId());
							}
							// 贷款回收还款明细
							List<RepayLoan> rlList=repayLoanDao.findListByTransNo(transNo);
							boolean result = false;
							if (CollectionUtils.isNotEmpty(rlList)) {
								// 提前部分还款
								if ("3".equals(rlList.get(0).getCleanCutCd())) {
									result = true;
								}
							}
							// 多次放款--冲销
							if (LoanEvent.AGAIN_PAY_LOAN.eq(event)) {
								result = true;
							}
							
							if(CollectionUtils.isNotEmpty(rpdList)&&loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5).equals(loanStatus)){
								// 冲销入账,将历史数据状态都变成冲销已入账--当前单据编号
								List<RepayingPlanHistory> update_rphList=repayingPlanHistoryDao.findListByTransNo(transNo);
								List<RepayingPlanDetailHistory> update_rpdhList=repayingPlanDetailHistoryDao.findListByTransNo(repayingPlanId, new Long(findResultInt(repayingPlanId, loanStatus)));
								List<RepayingDetailHistory> update_rdhList=repayingDetailHistoryDao.findListByTransNo(transNo);
								List<ArrearsDetailHistory> update_adhList=arrearsDetailHistoryDao.findListByTransNo(transNo);
								if(CollectionUtils.isNotEmpty(update_rphList)&&CollectionUtils.isNotEmpty(update_rpdhList)
										&&CollectionUtils.isNotEmpty(update_rdhList)&&CollectionUtils.isNotEmpty(adhList)
										&&update_adhList.size()==update_rdhList.size()){
									RepayingPlanHistory rph=update_rphList.get(0);
									rph.setTransactionNo(loanStatus);
									for (RepayingDetailHistory rdh : update_rdhList) {
										rdh.setTransactionStatus(loanStatus);
									}
									for (ArrearsDetailHistory ad : update_adhList) {
										ad.setTransactionStatus(loanStatus);
									}
									if(result){
										for (RepayingPlanDetailHistory rpdh : update_rpdhList) {
											rpdh.setTransactionStatus(loanStatus);
										}
										repayingPlanDetailHistoryDao.save(update_rpdhList);
									}
									repayingPlanHistoryDao.save(rph);
									repayingDetailHistoryDao.save(update_rdhList);
									arrearsDetailHistoryDao.save(update_adhList);
								}
							}
							// 将历史数据还原--当前单据编号的上一笔(如果是冲销那是历史表上一条,如果是退单那是历史表最后一条)
							if(CollectionUtils.isNotEmpty(rphList)&&CollectionUtils.isNotEmpty(rdhList)
									&&CollectionUtils.isNotEmpty(adhList)&&CollectionUtils.isNotEmpty(rpList)
									&&CollectionUtils.isNotEmpty(rpdList)&&CollectionUtils.isNotEmpty(rdList)
									&&adhList.size()==rdhList.size()&&adList.size()==rdList.size()&&rdList.size()==rpdList.size()){
								int total_period = rpdList.size(); // 总期数
								int repayed_period = 0; // 已还期数
								// 已还状态
								String endStatus = loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S1);
								for (ArrearsDetailHistory vo : adhList) {
									if (endStatus.equals(vo.getStatus())) {
										repayed_period++;
									}
								}
								RepayingPlanHistory repayingPlanHistory = rphList.get(0);
								repayingPlanHistory.setTotalPeriod(total_period);
								repayingPlanHistory.setRepayedPeriod(repayed_period);
								repayingPlanHistory.setSurplusPeriod(total_period - repayed_period);
								// 损失计提
								LossProvision updateLossProvisionVO =findLossProvisionByContractId(repayingPlan.getContractId());
								if (updateLossProvisionVO != null) {
									if (loanCommonServeice.getCodeVal(LoanStatus.PAY_LOAN_STATUS_S5).equals(loanStatus)
											&& repayingPlanHistory.getPrivisionAmt() != null) { // 冲销入账
										updateLossProvisionVO.setPrivisionAmt(repayingPlanHistory.getPrivisionAmt());
									} else if (repayingPlan.getPrivisionAmt() != null) {
										updateLossProvisionVO.setPrivisionAmt(repayingPlan.getPrivisionAmt());
									}
									lossProvisionDao.save(updateLossProvisionVO);
								}
								Contract contract = this.findContractById(repayingPlan.getContractId());
								contract.setContractStatusCd(repayingPlanHistory.getContContractStatusCd());
								contract.setLastOvdueDate(repayingPlanHistory.getContLastOvdueDate());
								contract.setFirstOvdueDate(repayingPlanHistory.getContFirstOvdueDate());
								if (repayingPlanHistory.getContFiveclassification() != null) {
									contract.setFiveclassification(repayingPlanHistory.getContFiveclassification());
								}
								contract.setOvdueAmt(repayingPlanHistory.getContOvdueAmt());
								if(repayingPlanHistory.getContOvdueDays()!=null){
									contract.setOvdueDays(new Long(repayingPlanHistory.getContOvdueDays()));
								}
								contract.setOvdueInterest(repayingPlanHistory.getContOvdueInterest());
								if(repayingPlanHistory.getContOvdueTime()!=null){
									contract.setOvdueTime(new Long(repayingPlanHistory.getContOvdueTime()));
								}
								contractDao.save(contract);
								// 还款计划表
								this.updateRepayingPlanByRepayingPlanHistory(repayingPlan, repayingPlanHistory);
								if (result && rpdhList.size() > 0) {
									// 还款计划明细表
									this.updateRepayingPlanDetailByRepayingPlanDetailHistory(rpdList,
											rpdhList, adhList);
								} else {
									// 还款计划明细表
									this.updateRepayingPlanDetailByArrearsDetailHistory(rpdList,
											adhList, event);
								}
								// 还款明细表
								this.updateRepayingDetailByRepayingDetailHistory(rdList,
										rdhList);
								// 欠款明细表
								this.updateArrearsDetailByArrearsDetailHistory(adList, adhList);
								// 还原逾期情况表
								this.updateOverdueState(repayingPlan.getContractId(), loanStatus);
							}
							
						}
						
					}
				}
	}
}

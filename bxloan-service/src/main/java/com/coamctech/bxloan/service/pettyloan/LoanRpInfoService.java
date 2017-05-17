package com.coamctech.bxloan.service.pettyloan;

import java.util.List;
import java.util.Set;

import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.OverdueState;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.pettyloan.bo.LoanRpdInfoVo;

/**
 * 还款计划相关接口（还款计划、还款计划明细）
 * @author AcoreHeng
 *
 */
public interface LoanRpInfoService {
	
	/**
	 * 一个合同对应一个还款计划
	 * @param contractId	注：原始合同Id（initContractId），展期后合同id改变，但原始合同id不变
	 * @return
	 */
	RepayingPlan findRepayingPlan(Long contractId);
	/**
	 * 查询该还款计划所有明细（按order by currentPeriod asc）
	 * @param repayingPlanId
	 * @return
	 */
	List<RepayingPlanDetail> findAllRpdListByRpId(Long repayingPlanId);
	/**
	 * 从全部的还款计划明细中选出可以还的还款计划
	 * @param rpdAllList
	 * @return
	 */
	LoanRpdInfoVo findCanYetRpdList(List<RepayingPlanDetail> rpdAllList);
	/**
	 * 查询还款明细
	 * @param rpdIds	还款计划明细Id集合
	 * @return
	 */
	List<RepayingDetail> findRdList(Set<Long> rpdIds);
	/**
	 * 查询欠款明细
	 * @param rpdIds	还款计划明细Id集合
	 * @return
	 */
	List<ArrearsDetail> findAdList(Set<Long> rpdIds);
	/**
	 * 查询逾期明细
	 * @param rpdIds	还款计划明细Id集合
	 * @return
	 */
	List<OverdueState> findOsList(Set<Long> rpdIds);
	
	/**
	 * 当期之后的所有欠款明细
	 * @param repayingPlanId 还款计划Id
	 * @return
	 */
	List<ArrearsDetail> findAdListOfCurPeriodAfter(Long repayingPlanId,Integer curPeriod);
	/**
	 * 通过id查找还款计划明细
	 * */
	RepayingPlanDetail findRepayingPlanDetailById(Long curRpdId);
	/**
	 * 查询当期之前未还还款计划(包含当期)
	 * @param list
	 * 0.repayingPlanId
	 * 1.currentPriod 当期期次
	 * 2.repayedDate 还款日期
	 * 3.还款状态 "PlanStatus", "S0"未还"S1"已还"S2"逾期未还
	 * */
	List<RepayingPlanDetail> findYetRepayedList(List yetRepayedParams);
}

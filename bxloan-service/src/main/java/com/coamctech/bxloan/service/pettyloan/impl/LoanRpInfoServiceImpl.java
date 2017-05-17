package com.coamctech.bxloan.service.pettyloan.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.RepayingDetailDao;
import com.coamctech.bxloan.dao.RepayingPlanDetailDao;
import com.coamctech.bxloan.entity.ArrearsDetail;
import com.coamctech.bxloan.entity.OverdueState;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.pettyloan.LoanRpInfoService;
import com.coamctech.bxloan.service.pettyloan.bo.LoanRpdInfoVo;
import com.coamctech.bxloan.service.pettyloan.util.CommonHelper;
import com.google.common.collect.Lists;
@Transactional
@Service
public class LoanRpInfoServiceImpl implements LoanRpInfoService {
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private RepayingDetailDao repayingDetailDao;
	@Autowired
	private RepayingPlanDetailDao repayingPlanDetailDao;
	@Override
	public RepayingPlan findRepayingPlan(Long contractId) {
		List<RepayingPlan> list=dynamicQuery.query(RepayingPlan.class, "from RepayingPlan rp where rp.contractId=?1", contractId);
		if(CollectionUtils.isNotEmpty(list)){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RepayingPlanDetail> findAllRpdListByRpId(Long repayingPlanId) {
		return dynamicQuery.query(RepayingPlanDetail.class, "from RepayingPlanDetail rpd where rpd.repayingPlanId=?1 order by rpd.currentPeriod asc", repayingPlanId);
	}
	@Override
	public LoanRpdInfoVo findCanYetRpdList(List<RepayingPlanDetail> rpdAllList) {
		if(CollectionUtils.isEmpty(rpdAllList)){
			return null;
		}
		final String NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S0");
		final String OVERDUE_NOT_YET=loanCommonServeice.getCodeVal("PlanStatus", "S2");
		LoanRpdInfoVo rpdInfoVo=new LoanRpdInfoVo();
		List<RepayingPlanDetail> overduePpds=Lists.newArrayList();
		for (RepayingPlanDetail rpd : rpdAllList) {
			if(NOT_YET.equals(rpd.getStatus())){
				rpdInfoVo.setCurRpd(rpd);
			}else if(OVERDUE_NOT_YET.equals(rpd.getStatus())){
				overduePpds.add(rpd);
			}
		}
		if(CollectionUtils.isNotEmpty(overduePpds)){
			rpdInfoVo.setOverduePpds(overduePpds);
		}
		if(CollectionUtils.isEmpty(overduePpds)&&rpdInfoVo.getCurRpd()==null){
			//所有还款计划明细已还清
			return null;
		}
		return rpdInfoVo;
	}
	@Override
	public List<RepayingDetail> findRdList(Set<Long> rpdIds) {
		return dynamicQuery.query(RepayingDetail.class,"from RepayingDetail where repayingPlanDetailId in (?1) order by repayingNum asc",rpdIds);
	}
	@Override
	public List<ArrearsDetail> findAdList(Set<Long> rpdIds) {
		return dynamicQuery.query(ArrearsDetail.class,"from ArrearsDetail where repayingPlanDetailId in (?1)  order by period asc",rpdIds);
	}
	@Override
	public List<OverdueState> findOsList(Set<Long> rpdIds) {
		return dynamicQuery.query(OverdueState.class,"from OverdueState where repayingPlanDetailId in (?1)  and repaymentDate is null ",rpdIds);
	}
	@Override
	public List<ArrearsDetail> findAdListOfCurPeriodAfter(Long repayingPlanId,
			Integer curPeriod) {
		return dynamicQuery.query(ArrearsDetail.class, "from ArrearsDetail where repayingPlanDetailId in (select rpd.repayingPlanDetailId from RepayingPlanDetail rpd  where rpd.repayingPlanId =?1  and rpd.currentPeriod >?2)", repayingPlanId,curPeriod);
	}

	@Override
	public RepayingPlanDetail findRepayingPlanDetailById(Long curRpdId) {
		return repayingPlanDetailDao.findOne(curRpdId);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RepayingPlanDetail> findYetRepayedList(List yetRepayedParams) {
		StringBuffer jpql = new StringBuffer("from RepayingPlanDetail where repayingPlanId = ?1 ");
		jpql.append(" and ( (currentPeriod <= ?2 ) or currentEndDate < to_date(?3,'"+ CommonHelper.DF_DATE +"') )");
		jpql.append(" and status != ?4 ");
		jpql.append(" order by currentPeriod asc ");
		yetRepayedParams.set(2, CommonHelper.date2Str((Date)yetRepayedParams.get(2), CommonHelper.DF_DATE));
		return dynamicQuery.query(RepayingPlanDetail.class, jpql.toString(), yetRepayedParams.toArray());
	}

}

package com.coamctech.bxloan.service.repayplan.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.CommonHelper;
import com.coamctech.bxloan.commons.utils.MoneyUtil;
import com.coamctech.bxloan.dao.ContractDao;
import com.coamctech.bxloan.dao.RepayPlanDao;
import com.coamctech.bxloan.dao.RepayingPlanDetailDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.Contract;
import com.coamctech.bxloan.entity.RepayingPlan;
import com.coamctech.bxloan.entity.RepayingPlanDetail;
import com.coamctech.bxloan.service.model.excel.ExRepayingPlanDetailVo;
import com.coamctech.bxloan.service.model.excel.ExRepayingPlanVo;
import com.coamctech.bxloan.service.model.statistics.BizRepayingPlanVo;
import com.coamctech.bxloan.service.repayplan.RepayPlanService;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Transactional
@Service
public class RepayPlanServiceImpl implements RepayPlanService {
	
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private DataDict dataDict;
	@Autowired
	private RepayPlanDao repayPlanDao;
	@Autowired
	private RepayingPlanDetailDao repayingPlanDetailDao;
	@Autowired
	private ContractDao contractDao;
	
	private String PAY_LOAN_STATUS="PayLoanStatus";

	@Override
	public List<RepayingPlanDetail> findRepayPlanBySearch(int pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer jpql = new StringBuffer(" SELECT r from RepayingPlan r where 1=1 ");
		jpql.append(" and r.contractId = ?1");
		jpql.append(" and r.projectId = ?2");
		jpql.append(" and r.payLoanId = ?3");
		List paramsAttr = new ArrayList();
		paramsAttr.add(new Long((String) params.get(0)));//contractId
		paramsAttr.add(new Long((String) params.get(1)));//projectId
		paramsAttr.add(new Long((String) params.get(2)));//payLoanId
		List<RepayingPlan> queryList = dynamicQuery.query(RepayingPlan.class, jpql.toString(), paramsAttr.toArray());
		if (queryList == null || queryList.size() == 0) {
			return null;
		} else {
			Long id = queryList.get(0).getRepayingPlanId();
			StringBuffer sql = new StringBuffer("from RepayingPlanDetail where repayingPlanId = ?1 order by currentPeriod asc");
			return (List<RepayingPlanDetail>) dynamicQuery.query(sql.toString(), id);
		}
	}
	/**
	 * 查询还款情况参数
	 */
	public List<Object> findRepayPlanParams(String contractId) {
		int index = 1;
		StringBuffer sb = new StringBuffer(
				"select c.payloan_date, "
				+ "c.contract_term_total, "
				+ "c.contract_term_unit_total, "
				+ "round(c.contract_rate * 100, 2) contract_rate, "
				+ "c.contract_status_cd from contract c ");
		List<Object> params = new ArrayList<Object>(0);
		if (StringUtils.isNotEmpty(contractId)) {
			sb.append(" where c.contract_id =?").append(index++);
			params.add(Long.valueOf(contractId));
		}
		List<Object> list = Lists.transform(
				dynamicQuery.nativeQuery(sb.toString(), params),
				new Function<Object[], Object>() {
					@Override
					public BizRepayingPlanVo apply(Object[] objs) {
						return new BizRepayingPlanVo(objs, null);
					}
				});
		return list;
	};
	/**
	 * 查看还款情况（分页）
	 */
	@Override
	public Page<BizRepayingPlanVo> findRepayPlanByCondition(Integer pageNumber, Integer pageSize, String contractId) {
		
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>(0);
		this.dealConditionJudge(params,sb,contractId);
		Page<Object[]> page = dynamicQuery.nativeQuery(Object[].class,new PageRequest(pageNumber, pageSize), sb.toString(),params.toArray());
		Page<BizRepayingPlanVo> resultPage = new PageImpl<BizRepayingPlanVo>(Lists.transform(page.getContent(),
						new Function<Object[], BizRepayingPlanVo>() {
							@Override
							public BizRepayingPlanVo apply(Object[] objs) {
								return new BizRepayingPlanVo(objs);
							}
						}), new PageRequest(pageNumber, pageSize),
				page.getTotalElements());
		return resultPage;
	}
	/**
	 * 查看还款情况（不分页）
	 */
	@Override
	public List<BizRepayingPlanVo> findRepayPlanByConditionNotPaging(String contractId) {
		
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>(0);
		this.dealConditionJudge(params,sb,contractId);

		List<BizRepayingPlanVo> repayingPlanLists = Lists.transform(
				dynamicQuery.nativeQuery(sb.toString(), params),
				new Function<Object[], BizRepayingPlanVo>() {
					@Override
					public BizRepayingPlanVo apply(Object[] objs) {
						return new BizRepayingPlanVo(objs);
					}
				});
		return repayingPlanLists;
	}
	
	public void dealConditionJudge(List<Object> params, StringBuffer sb, String contractId) {
		int index = 1;
		sb.append("select c.contract_amt,rp.contract_id,rp.party_id,rp.project_id,rp.pay_loan_id,c.contract_balance,");
		sb.append("rpd.current_period,rpd.current_principal,rpd.current_end_date,rpd.status,rpd.current_interest,rpd.current_principal_interest,rpd.repaying_plan_detail_id,");
		sb.append("rd.repayed_principal,rd.repayed_interest,rd.repaying_date,rd.repayed_impose_interest,");
		sb.append("(rd.repayed_impose_interest + ad.notyet_impose_interest + ad.carryover_impose_interest + ad.year_impose_interest + ad.year_impose_90_interest) sumod,");
		sb.append("ad.recently_date,ad.notyet_impose_interest,ad.carryover_impose_interest,");
		sb.append("os.overdue_date, case when os.due_day is null then trunc(sysdate) - overdue_date else os.due_day end due_day,nvl(os.due_day, trunc(sysdate) - overdue_date) ");
		sb.append("from repaying_plan rp,repaying_plan_detail rpd,arrears_detail ad,repaying_detail rd,overdue_state os,pay_loan_info pl,contract c ");
		sb.append("where c.init_contract_id = pl.contract_id ");
		sb.append("and rp.pay_loan_id = pl.pay_loan_id ");
		sb.append("and rp.contract_id = c.contract_id ");
		sb.append("and rp.repaying_plan_id = rpd.repaying_plan_id ");
		sb.append("and rpd.repaying_plan_detail_id = rd.repaying_plan_detail_id ");
		sb.append("and rpd.repaying_plan_detail_id = ad.repaying_plan_detail_id ");
		sb.append("and os.repaying_plan_detail_id(+) = rpd.repaying_plan_detail_id ");
		sb.append("and rpd.repaying_plan_detail_id = rd.repaying_plan_detail_id ");
		sb.append("and pl.pay_status_cd in ('2', '4') ");
		sb.append("and pl.many_pay_status = '0' ");
		sb.append("and rp.transaction_status != '5' ");
//		sb.append("and c.contract_status_cd = '330' ");
		if (StringUtils.isNotEmpty(contractId)) {
			sb.append("and rp.contract_id =?").append(index++);
			params.add(Long.valueOf(contractId));
		}
		sb.append(" order by rpd.current_period asc");
	}
	
	/**
	 * @param contractId
	 * @return
	 */
	@Override
	public ExRepayingPlanVo findRepayingPlanForExcelByContractId(Long contractId){
		ExRepayingPlanVo vo=new ExRepayingPlanVo();
		//合同及还款计划信息
		this.initRepayingPlan(vo, contractId);
		Long rpId=vo.getRepayingPlanId();
		if(rpId!=null){
			//还款计划明细
			List<RepayingPlanDetail> rpdList=repayingPlanDetailDao.findListByRplId(rpId);
			List<ExRepayingPlanDetailVo> exRpdList=Lists.transform(rpdList, new Function<RepayingPlanDetail, ExRepayingPlanDetailVo>() {
				@Override
				public ExRepayingPlanDetailVo apply(RepayingPlanDetail rpd) {
					ExRepayingPlanDetailVo vo=new ExRepayingPlanDetailVo();
					vo.setCurPeriod(rpd.getCurrentPeriod());//当前期次
					vo.setPayableAmt(MoneyUtil.formatMoney(rpd.getCurrentPrincipalInterest()));//应还本息
					vo.setPayablePrincipal(MoneyUtil.formatMoney(rpd.getCurrentPrincipal()));//应还本金
					vo.setPayableInterest(MoneyUtil.formatMoney(rpd.getCurrentInterest()));//应还利息
					vo.setPlannedRepaymentDateStr(CommonHelper.date2Str(rpd.getCurrentEndDate(), "yyyy年MM月dd日"));//应还日期
					return vo;
				}
			});
			vo.setExRpdList(exRpdList);
		}
		return vo;
	}
	
	/**
	 * 合同及还款计划信息
	 * @param vo
	 * @param contractId
	 */
	private void initRepayingPlan(ExRepayingPlanVo vo,Long contractId){
		StringBuffer sqlBuffer=new StringBuffer();
		sqlBuffer.append("select c.contract_id,c.contract_num,c.customer_name,");
	    sqlBuffer.append("(select eod.name from ec_org_department eod where eod.id = c.apply_org_id) org_name,");
	    sqlBuffer.append("rp.repaying_plan_id,rp.repaying_num");
	    sqlBuffer.append(" from contract c, repaying_plan rp");
	    sqlBuffer.append(" where c.contract_id = rp.contract_id");
	    sqlBuffer.append(" and c.contract_id= ?1");
	    sqlBuffer.append(" and rp.transaction_status != ?2");
		List<Object[]> list=dynamicQuery.nativeQuery(sqlBuffer.toString(), contractId,dataDict.getCodeVal(PAY_LOAN_STATUS, "S5"));
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		Object[] objs=list.get(0);
		vo.setContractNum(CommonHelper.toStr(objs[1]));
		vo.setCustomerName(CommonHelper.toStr(objs[2]));
		vo.setOrgName(CommonHelper.toStr(objs[3]));
		vo.setRepayingPlanId(CommonHelper.toLong(objs[4]));
		vo.setRepayingNum(CommonHelper.toStr(objs[5]));
	}
	
}



















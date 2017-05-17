package com.coamctech.bxloan.service.approval.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.utils.DateTools;
import com.coamctech.bxloan.dao.ApprovalHistoryRepayPlanDao;
import com.coamctech.bxloan.dao.ProjectApplicationDao;
import com.coamctech.bxloan.dao.RepayPlanDao;
import com.coamctech.bxloan.dao.RepayPlanTempDao;
import com.coamctech.bxloan.entity.ApprovalHistoryRepayPlan;
import com.coamctech.bxloan.entity.ProjectApplication;
import com.coamctech.bxloan.entity.RepayPlan;
import com.coamctech.bxloan.entity.RepayPlanTemp;
import com.coamctech.bxloan.service.approval.ApprovalRepayPlanService;

@Service
@Transactional
public class ApprovalRepayPlanServiceImpl implements ApprovalRepayPlanService {
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private ProjectApplicationDao projectApplicationDao;
	@Autowired
	private ApprovalHistoryRepayPlanDao approvalHistoryRepayPlanDao;
	@Autowired
	private RepayPlanDao repayPlanDao;
		
	@Autowired
	private RepayPlanTempDao repayPlanTempDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Object[]> searchRepayPlanList(Integer pageNumber,
			Integer pageSize, String projectNo, Long approvalId) {
		StringBuilder sql = new StringBuilder(
				"select to_char(r.repay_date,'yyyy-mm-dd'),r.repay_amt,r.APPROVAL_HISTORY_REPAY_PLAN_ID from "
						+ "APPROVAL_HISTORY_REPAY_PLAN r where 1=1");
		List<Object> params = new ArrayList<Object>();
		int i = 0;
		if (StringUtils.isNotBlank(projectNo)) {
			sql.append(" and r.approval_history_id is null and r.project_no = ?" + ++i);
			params.add(projectNo);
		}
		if (approvalId != null) {
			sql.append(" and r.approval_history_id = ?" + ++i);
			params.add(approvalId);
		}
		sql.append(" order by r.repay_date asc");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1,
				pageSize), sql.toString(), params.toArray());
	}

	@Override
	public void insertRepayPlanList(Long projectId, BigDecimal applyAmt,
			Integer applyTerm, String applyTermUnit, Date startDate,
			Integer monthDate, Integer repayDate) throws Exception {
		try {
			ProjectApplication pa = projectApplicationDao
					.findProjectApplicationByProjectId(projectId);
			ApprovalHistoryRepayPlan approvalRepayPlan = null;
			Date endDate = DateTools.getEndingDate(startDate, applyTermUnit,
					applyTerm);
			Date systemDate = DateTools.stringToDate(
					DateTools.getCurrentDate("yyyy-MM-dd"), "yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			int cnt = 0;
			Integer repaymentDate = null;
			while (true) {
				approvalRepayPlan = new ApprovalHistoryRepayPlan();
				approvalRepayPlan.setCustomerId(pa.getPartyId());
				approvalRepayPlan.setCustomerName(pa.getCustomerName());
				approvalRepayPlan.setCustomerNum(pa.getCustomerNum());
				approvalRepayPlan.setProjectNo(pa.getProjectNo());
				approvalRepayPlan.setProjectId(pa.getProjectId());
				approvalRepayPlan.setSysCreateDate(systemDate);
				approvalRepayPlan.setSysUpdateDate(systemDate);
				calendar.setTime(startDate);
				repaymentDate = repayDate;
				cnt = DateTools.getActualMaximum(calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH) + 1 + monthDate);
				if (cnt < repayDate) {
					repaymentDate = cnt;
				}
				startDate = this.getDate(calendar.get(Calendar.YEAR),
						calendar.get(Calendar.MONTH) + 1 + monthDate,
						repaymentDate);
				// 当月只有一次还款
				if (DateTools.getDateDiff(endDate, startDate) >= 0
						|| DateTools.dateToString(startDate, "yyyy-MM").equals(
								DateTools.dateToString(endDate, "yyyy-MM"))) {
					startDate = endDate;
					approvalRepayPlan.setRepayAmt(applyAmt);
				} else {
					approvalRepayPlan.setRepayAmt(BigDecimal.ZERO);
				}
				approvalRepayPlan.setRepayDate(startDate);
				
				RepayPlan repayPlan = new RepayPlan();
				BeanUtils.copyProperties(approvalRepayPlan, repayPlan);
//				RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
//				BeanUtils.copyProperties(approvalRepayPlan, repayPlanTemp);
				
				approvalHistoryRepayPlanDao.save(approvalRepayPlan);
				repayPlanDao.save(repayPlan);
				//repayPlanTempDao.save(repayPlanTemp);
				
				if (DateTools.getDateDiff(endDate, startDate) >= 0) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if ("1".equals(e.getMessage())) {
				throw new Exception(e.getMessage());
			} else {
				throw new Exception("还款计划初始化出错,请联系管理员!");
			}
		}
	}

	@Override
	public ApprovalHistoryRepayPlan getRepayPlan(Long id) {
		return approvalHistoryRepayPlanDao.findOne(id);
	}

	@Override
	public void deleteRepayPlan(Long id) {
		ApprovalHistoryRepayPlan approvalHistoryRepayPlan = approvalHistoryRepayPlanDao.findOne(id);
		Long projectId = approvalHistoryRepayPlan.getProjectId();
		Long approvalHistoryId = approvalHistoryRepayPlan.getApprovalHistoryId();
		approvalHistoryRepayPlanDao.delete(id);
		
		String delRepayPlanSql = "DELETE FROM REPAY_PLAN RP WHERE RP.PROJECT_ID = " + projectId;
		String delRepayPlanTempSql = "DELETE FROM REPAY_PLAN_TEMP RPT WHERE RPT.PROJECT_ID =" + projectId;
		dynamicQuery.nativeExecuteUpdate(delRepayPlanSql);
		dynamicQuery.nativeExecuteUpdate(delRepayPlanTempSql);
		
		List<ApprovalHistoryRepayPlan> approvalRepayPlans = approvalHistoryRepayPlanDao.findByApprovalHistoryIdAndProjectId(approvalHistoryId, projectId);
		List<RepayPlan> newRepayPlans = new ArrayList<RepayPlan>();
//		List<RepayPlanTemp> newRepayPlanTemps = new ArrayList<RepayPlanTemp>();
		for (ApprovalHistoryRepayPlan newApprovalHistoryRepayPlan : approvalRepayPlans) {
			RepayPlan repayPlan = new RepayPlan();
			BeanUtils.copyProperties(newApprovalHistoryRepayPlan, repayPlan);
			newRepayPlans.add(repayPlan);
			
//			RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
//			BeanUtils.copyProperties(repayPlan, repayPlanTemp);
//			newRepayPlanTemps.add(repayPlanTemp);
			
		}
		repayPlanDao.save(newRepayPlans);
//		repayPlanTempDao.save(newRepayPlanTemps);
	}

	@Override
	public void saveRepayPlan(ApprovalHistoryRepayPlan form) {
		approvalHistoryRepayPlanDao.save(form);
		Long projectId = form.getProjectId();
		Long approvalHistoryId = form.getApprovalHistoryId();
		
		String delRepayPlanSql = "DELETE FROM REPAY_PLAN RP WHERE RP.PROJECT_ID = " + form.getProjectId();
		String delRepayPlanTempSql = "DELETE FROM REPAY_PLAN_TEMP RPT WHERE RPT.PROJECT_ID =" + projectId;
		dynamicQuery.nativeExecuteUpdate(delRepayPlanSql);
		dynamicQuery.nativeExecuteUpdate(delRepayPlanTempSql);
		
		List<ApprovalHistoryRepayPlan> approvalRepayPlans = approvalHistoryRepayPlanDao.findByApprovalHistoryIdAndProjectId(approvalHistoryId, projectId);
		List<RepayPlan> newRepayPlans = new ArrayList<RepayPlan>();
//		List<RepayPlanTemp> newRepayPlanTemps = new ArrayList<RepayPlanTemp>();
		for (ApprovalHistoryRepayPlan newApprovalHistoryRepayPlan : approvalRepayPlans) {
			RepayPlan repayPlan = new RepayPlan();
			BeanUtils.copyProperties(newApprovalHistoryRepayPlan, repayPlan);
			newRepayPlans.add(repayPlan);
			
//			RepayPlanTemp repayPlanTemp = new RepayPlanTemp();
//			BeanUtils.copyProperties(repayPlan, repayPlanTemp);
//			newRepayPlanTemps.add(repayPlanTemp);
		}
		repayPlanDao.save(newRepayPlans);
//		repayPlanTempDao.save(newRepayPlanTemps);
	}
	
	public boolean checkRepayDateIsExist(ApprovalHistoryRepayPlan form){
		StringBuffer sb = new StringBuffer();
		List<Object> params = new ArrayList<Object>(0);
		sb.append("select count(1) from APPROVAL_HISTORY_REPAY_PLAN ap ");
		sb.append("where ap.APPROVAL_HISTORY_ID is null");
		
		sb.append(" and ap.PROJECT_ID =?1");
		params.add(form.getProjectId());
		
		sb.append(" and ap.PROJECT_NO =?2");
		params.add(form.getProjectNo());
		
		sb.append(" and ap.REPAY_DATE =?3");
		params.add(form.getRepayDate());
		
		List<Object[]> list = dynamicQuery.nativeQuery(Object[].class,sb.toString(), params.toArray());
		return Integer.parseInt(String.valueOf(list.get(0))) > 0? true : false;
	}

	/**
	 * 字串转成时间
	 * 
	 * @param year年
	 * @param month月
	 * @param day日
	 * @return
	 */
	private Date getDate(int year, int month, int day) {
		StringBuffer sb = new StringBuffer();
		sb.append(year).append("-");
		if (month < 10 && month > 0) {
			sb.append(0).append(month);
		} else {
			sb.append(month);
		}
		sb.append("-");
		if (day < 10 && day > 0) {
			sb.append(0).append(day);
		} else {
			sb.append(day);
		}
		return DateTools.stringToDate(sb.toString(), "yyyy-MM-dd");
	}

	@Override
	public void deleteRepayPlanByProjectId(Long projectId) {
		approvalHistoryRepayPlanDao.deleteByProjectId(projectId);
	}
}

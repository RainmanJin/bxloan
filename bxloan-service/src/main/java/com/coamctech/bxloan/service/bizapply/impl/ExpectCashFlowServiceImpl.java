package com.coamctech.bxloan.service.bizapply.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.dao.ExpectCashFlowDao;
import com.coamctech.bxloan.dao.ExpectCashFlowDetailDao;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.entity.ExpectCashFlow;
import com.coamctech.bxloan.entity.ExpectCashFlowDetail;
import com.coamctech.bxloan.service.bizapply.ExpectCashFlowService;
@Transactional
@Service
public class ExpectCashFlowServiceImpl implements ExpectCashFlowService{
	
	@Autowired
	private DataDict dataDict;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private ExpectCashFlowDao expectCashFlowDao;
	@Autowired
	private ExpectCashFlowDetailDao expectCashFlowDetailDao;
	@Override
	public ExpectCashFlow findExpectCashFlowById(Long id) {
		return this.expectCashFlowDao.findById(id);
	}
	@Override
	public ExpectCashFlowDetail findExpectCashFlowDetailById(Long id) {
		return this.expectCashFlowDetailDao.findById(id);
	}
	@Override
	public List<ExpectCashFlowDetail> findEcfListById(Long expectCashFlowId) {
		return this.expectCashFlowDetailDao.findByExpectCashFlowId(expectCashFlowId);
	}
	@Override
	public Long saveExpectCashFlow(ExpectCashFlow expectCashFlow) {
		this.expectCashFlowDao.save(expectCashFlow);
		return expectCashFlow.getId();
	}
	@Override
	public Long saveExpectCashFlowDetail(ExpectCashFlowDetail expectCashFlowDetail) {
		this.expectCashFlowDetailDao.save(expectCashFlowDetail);
		return expectCashFlowDetail.getId();
	}
	@Override
	public Page findBySearch(Integer pageNumber, Integer pageSize, String projectId) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT ecf.id, " +
						  "ecf.project_id, " +
						  "ecf.family_cost, " +
						  "ecf.other_loan_repayment, " +
						  "ecf.cost_total, " +
						  "ecf.balance, " +
						  "ecf.balance_before_loan, " +
						  "ecf.income, " +
						  "ecf.cost, " +
						  "ecf.balance_after_loan, " +
						  "ecf.month FROM expect_cash_flow ecf ");
		sql.append("WHERE ecf.project_id = ?1 ");
		return dynamicQuery.nativeQuery(new PageRequest(pageNumber - 1, pageSize), sql.toString(), Long.valueOf(projectId));
	}
	
	@Override
	public void deleteExpectCashFlow(Long id) {
		this.expectCashFlowDao.delete(id);
	}
	@Override
	public List<Object[]> findDetailsBySearch(Integer pageNumber, Integer pageSize, List<Object> params) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT ecfd.id, " +
						  "ecfd.expect_cash_flow_id, " +
						  "ecfd.type, " +
						  "ecfd.name, " +
						  "ecfd.money " +
						  "FROM expect_cash_flow_detail ecfd ");
		sql.append("WHERE ecfd.expect_cash_flow_id = to_number(?1) AND ecfd.type = ?2 ");
		
		return dynamicQuery.nativeQuery(sql.toString(), params.toArray());
	}
	@Override
	public void deleteExpectCashFlowDetail(Long id) {
		this.expectCashFlowDetailDao.delete(id);
	}
	@Override
	public List<ExpectCashFlowDetail> findEcfdListByIdAndType(Long expectCashFlowId, String type) {
		return this.expectCashFlowDetailDao.findByExpectCashFlowIdAndType(expectCashFlowId, type);
	}
	
	
}

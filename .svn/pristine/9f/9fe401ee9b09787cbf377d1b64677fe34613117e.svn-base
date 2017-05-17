package com.coamctech.bxloan.web.tests.service.pettyloan;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.dao.ApprovalHistoryRepayPlanDao;
import com.coamctech.bxloan.service.DataDictLoader;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;

public class LoanCommonServiceTests extends BaseSpringJUnit4Tester{
	
	@Autowired
	private DataDictLoader dataDictLoader;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Autowired
	private ApprovalHistoryRepayPlanDao approvalHistoryRepayPlanDao;
	
	@Before
	public void init() {
		dataDictLoader.preLoad();
	}
	@Test
	public void testRepayDateOfLastRepayingPlan() throws Exception {
		try {
			System.out.println(loanCommonServeice.getRepayDateOfLastRepayingPlan(29L));;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testProductConfForCustRepayPlan() throws Exception {
		System.out.println(loanCommonServeice.getProductConfForCustRepayPlan());
	}
	@Test
	public void testIsAllowContractNature() throws Exception {
		System.out.println(loanCommonServeice.isAllowContractNature("622201150009【借】-01"));
	}
	@Test
	public void testFindLastGroupListByProjId() throws Exception {
		System.out.println(approvalHistoryRepayPlanDao.findLastGroupListByProjId(2919L));
	}
}

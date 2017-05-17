package com.coamctech.bxloan.web.tests.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.service.DataDictLoader;
import com.coamctech.bxloan.service.afterloan.LoanRecoverService;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;
import com.coamctech.bxloan.service.repayplan.RepayPlanService;

public class LoanRecoverServiceTests extends BaseSpringJUnit4Tester{
	
	@Autowired
	private DataDictLoader dataDictLoader;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private LoanRecoverService loanRecoverService;
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	RepayPlanService repayPlanService ;
	
	
	@Before
	public void init() {
		dataDictLoader.preLoad();
	}
	@Test
	public void testFindRepayLoanList() throws Exception {
		try {
			loanRecoverService.findRepayLoanList(null, "23423", "345", "1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testName() throws Exception {
		repayPlanService.findRepayingPlanForExcelByContractId(705L);
	}
}

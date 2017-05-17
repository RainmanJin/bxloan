package com.coamctech.bxloan.web.tests.service.pettyloan;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coamctech.bxloan.commons.dynamicquery.DynamicQuery;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.entity.AccountingInfo;
import com.coamctech.bxloan.entity.RepayingDetail;
import com.coamctech.bxloan.service.DataDictLoader;
import com.coamctech.bxloan.service.pettyloan.LoanAccountingService;
import com.coamctech.bxloan.service.pettyloan.LoanCommonServeice;

public class LoanAccountingServiceTests extends BaseSpringJUnit4Tester {
	@Autowired
	private DataDictLoader dataDictLoader;
	@Autowired
	private LoanAccountingService loanAccountingService;
	@Autowired
	private LoanCommonServeice loanCommonServeice;
	@Autowired
	private DynamicQuery dynamicQuery;
	
	@Before
	public void init() {
		dataDictLoader.preLoad();
	}
	@Test
	public void testDoVchByLoan() throws Exception {
		/*DoVchAmtBo amtVO = new DoVchAmtBo();
		amtVO.setLoanId(payLoan.getPayLoanId());
		amtVO.setContractId(contract.getContractId());
		amtVO.setPartyId(contract.getPartyId());
		amtVO.setProjectId(contract.getProjectId());
		amtVO.setVchdate(payLoan.getLoanRegistTime());
		amtVO.setLoanAmt(payLoan.getLoanAmt());
		amtVO.setChargeAmt(payLoan.getSumAmt());
		amtVO.setBcCostType(sourceType);
		loanAccountingService.doVchByLoan(doVchAmt, bchId, lastModUser);*/
	}
	
	@Test
	public void testBuildTallyCertificate() throws Exception {
		List<AccountingInfo> infos=dynamicQuery.query(AccountingInfo.class, "from AccountingInfo where aiPk.txrefNo=?1", "D1619401409160001");
		try {
			loanAccountingService.buildTallyCertificate(infos, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(infos.size());
	}
	@Test
	public void testSCP() throws Exception {
		System.out.println(loanCommonServeice.findBillUrl(1));;
	}
	
	@Test
	public void testAcrossYearFlushes() throws Exception {
		System.out.println(loanCommonServeice.isAcrossYearFlushes("D109011405150006","01011140001【借】-01"));;
	}
	
	@Test
	public void testJpqlNestedQuery() throws Exception {
		dynamicQuery.query(RepayingDetail.class, "from RepayingDetail where repayingPlanDetailId in (select repayingPlanDetailId from RepayingPlanDetail where repayingPlanId =?1)", 21L);
	}
}

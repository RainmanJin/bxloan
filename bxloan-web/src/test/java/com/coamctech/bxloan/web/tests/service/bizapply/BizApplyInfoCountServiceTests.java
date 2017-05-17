package com.coamctech.bxloan.web.tests.service.bizapply;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.service.DataDictLoader;
import com.coamctech.bxloan.service.bizapply.BizApplyInfoCountService;

public class BizApplyInfoCountServiceTests extends BaseSpringJUnit4Tester{
	private Logger logger=LoggerFactory.getLogger(BizApplyInfoCountServiceTests.class);
	@Autowired
	private BizApplyInfoCountService bizApplyInfoCountService;
	@Autowired
	private DataDictLoader dataDictLoader;
	@Before
	public void init() {
		dataDictLoader.preLoad();

	}
	@Test
	public void testInt() throws Exception {
		assertEquals(100L, 200L);
	}
	@Test
	public void testAsseLliabilityInfo() throws Exception {
		logger.info(JSON.toJSONString(bizApplyInfoCountService.buildAsseLliabilityInfo(33308L)));
	}
	@Test
	public void testCashDeposit() throws Exception {
		
		logger.info("CashDeposit:{}",bizApplyInfoCountService.findCashDepositByBizNfal(33308L));
	}
	@Test
	public void test1() throws Exception {
		
		bizApplyInfoCountService.test(33682L);
	}
}

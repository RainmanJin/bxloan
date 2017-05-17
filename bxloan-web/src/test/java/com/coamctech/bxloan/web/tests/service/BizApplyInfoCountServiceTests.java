package com.coamctech.bxloan.web.tests.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.dao.ProduceAreaInfoDao;
import com.coamctech.bxloan.service.bizapply.BizApplyInfoCountService;
import com.coamctech.bxloan.service.bizapply.ExpectCashFlowInfoService;
import com.coamctech.bxloan.service.utils.EcfiObjTypeEnum;

public class BizApplyInfoCountServiceTests extends BaseSpringJUnit4Tester {
	private Logger logger=LoggerFactory.getLogger(BizApplyInfoCountServiceTests.class);
	@Autowired
	private BizApplyInfoCountService applyExcelService;
	@Autowired
	private ProduceAreaInfoDao produceAreaInfoDao;
	
	@Autowired
	private ExpectCashFlowInfoService expectCashFlowInfoService;
	@Test
	public void testAsseLliabilityInfo() throws Exception {
		logger.info(JSON.toJSONString(applyExcelService.buildAsseLliabilityInfo(33308L)));
	}
	@Test
	public void testCashDeposit() throws Exception {
		
		logger.info("CashDeposit:{}",applyExcelService.findCashDepositByBizNfal(33308L));
	}
	@Test
	public void test1() throws Exception {
		
		logger.info("CashDeposit:{}",produceAreaInfoDao.findCountByProjId(33422L,"2"));
	}
	
	@Test
	public void testName1() throws Exception {
		expectCashFlowInfoService.deleteEcfiByObj(230L, EcfiObjTypeEnum.FAMILY_CONSUME, 8734L, "234");
	}
}

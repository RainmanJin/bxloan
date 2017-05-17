package com.coamctech.bxloan.web.tests.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.service.model.statistics.BizExcelExportVo;
import com.coamctech.bxloan.service.statistics.BizApproveAccountService;

public class BizApproveAccountServiceTest extends BaseSpringJUnit4Tester {
	@Autowired
	private BizApproveAccountService approveAccountService;
	@Test
	public void testName() throws Exception {
		Page<BizExcelExportVo> page= approveAccountService.findPageForExcel(0, 100, null, null, null);
		System.out.println(page);
	}
}

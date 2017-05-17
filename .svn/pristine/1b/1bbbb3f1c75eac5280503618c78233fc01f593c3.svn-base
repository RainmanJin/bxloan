package com.coamctech.bxloan.web.tests.service.pettyloan;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.commons.webservices.WebServices;
import com.coamctech.bxloan.service.DataDictLoader;

public class AccountingWebServicesTests extends BaseSpringJUnit4Tester {
	@Autowired
	private WebServices webServices;
	@Autowired
	private DataDictLoader dataDictLoader;
	
	@Before
	public void init() {
		webServices.initClients();
		dataDictLoader.preLoad();
	}
	@Test
	public void test() throws Exception {
		System.out.println("test");
	}
	@Test
	public void testRuZhang() throws Exception {
		String result = webServices.invoke("accountingService", "dealBill", "D1622201505070001","bobo","20150507");

		System.out.println(result);
	}
	
	@Test
	public void testRuZhangOfCX() throws Exception {
		String result = webServices.invoke("accountingService", "dealBill", "D1622661503050003","bobo","20150104");
		System.out.println(result);
	}
	@Test
	public void testTuiDan() throws Exception {
		String result = webServices.invoke("accountingService", "cancBill", "D1619401501210005","王仁","20150120");
		System.out.println(result);
	}
	@Test
	public void testTuiDanOfCX() throws Exception {
		String result = webServices.invoke("accountingService", "cancBill", "D101011409250004","王仁","20140912");
		System.out.println(result);
	}
}

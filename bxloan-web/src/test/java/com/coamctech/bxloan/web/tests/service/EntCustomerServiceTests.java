package com.coamctech.bxloan.web.tests.service;


import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.datadict.DataDict;
import com.coamctech.bxloan.service.DataDictLoader;
import com.coamctech.bxloan.service.corpcustomer.CorporationCustomerService;
import com.coamctech.bxloan.service.sysmng.ICommonBizNumberBS;

public class EntCustomerServiceTests extends BaseSpringJUnit4Tester {

	@Autowired
	private CorporationCustomerService entCusService;
	
	@Autowired
	private ICommonBizNumberBS commBizNumber;
	
	@Autowired
	private DataDict dataDict;
	
	@Autowired
	private DataDictLoader dataDictLoader;
	
	@Before
	public void init(){
		dataDictLoader.preLoad();
	}
	
	@Test
	public void share() {
		Boolean result=null;
		try {
			result = this.entCusService.checkStockShares(
					new BigDecimal("89"), 10349L, null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
	@Test
	public void countCorpCus(){
		String certificateNum = "123123123123123123";
		
		System.out.println(this.entCusService.findCorpCountByCertificateNum(certificateNum));
	}
	
	@Test
	public void createEntCus(){
		String entCustomerTypeCd = 
				this.dataDict.getUniqueOne("CustomerType", "S1").getCodeValue();
		
		
		String certificateNum = "12345678"+"2014"+"09" + "10" + "02";
		
		String customerName = "测试企业客户20140910_02";
		String businessLicenseNum = certificateNum;
		
		
		String customerNum = commBizNumber
				.generateCustomerNumber(entCustomerTypeCd, null, certificateNum);
		
		System.out.println("customerNum========:" + customerNum);
		String managerUserNum = "66752";
		String managerOrgid = "61940";
		
//		this.entCusService.createCorpCustomer(certificateNum, customerName, 
//				businessLicenseNum, customerNum, 
//				managerUserNum, managerOrgid);
	}
	
}

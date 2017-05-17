package com.coamctech.bxloan.web.tests.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.service.customermng.CustomerManagerService;

public class CusManagerServiceTests extends BaseSpringJUnit4Tester {
	
	@Autowired
	private CustomerManagerService service;
	
	@Test
	public void ckDelPri(){
		String cmtId = "1569";
		String userId = "66844";
		try {
			String result = this.service.checkRemovePrivilege(cmtId, userId);
			System.out.println("result is : =========>     " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}

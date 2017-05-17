package com.coamctech.bxloan.web.tests.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.coamctech.bxloan.commons.test.BaseSpringJUnit4Tester;
import com.coamctech.bxloan.dao.CommonBorrowDao;
import com.coamctech.bxloan.dao.ProjectAssurerInfoDao;
import com.coamctech.bxloan.entity.CommonBorrow;
import com.coamctech.bxloan.entity.ProjectAssurerInfo;

public class ProAssuTests extends BaseSpringJUnit4Tester{

	@Autowired
	ProjectAssurerInfoDao dao;
	@Autowired
	CommonBorrowDao cbDao ;
	
	@Test
	public void add(){
		try {
			ProjectAssurerInfo info = new ProjectAssurerInfo();
			info.setProjectId(1234444L);
			info.setPartyId(1234444L);
			ProjectAssurerInfo savedInfo = this.dao.save(info);
			System.out.println(savedInfo.getProjectAssurerInfoId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void cbAdd(){
		try {
			CommonBorrow b = new CommonBorrow();
			b.setPartyId(1234444L);
			this.cbDao.save(b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
